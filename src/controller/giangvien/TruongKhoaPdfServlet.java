package controller.giangvien;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.DanhGia;
import model.bean.DotDanhGia;
import model.bean.GiangVien;
import model.bean.Lop;
import model.bean.SinhVien;
import model.bo.DanhGiaBO;
import model.bo.DotDanhGiaBO;
import model.bo.KhoaBO;
import model.bo.LopBO;
import model.bo.SinhVienBO;
import utils.DateUtils;
import utils.MyUtils;
import utils.Validate;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Servlet implementation class PdfServlet
 */

public class TruongKhoaPdfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TruongKhoaPdfServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8"); 
		
		String maDotPr = request.getParameter("dot");
		
		
		
		
		//Lay sinh vien dang nhap
		GiangVien giangVien = MyUtils.getLoginedGiangVien(request.getSession());
		
		System.out.println(giangVien);
				
		//kiem tra dang nhap
		if (giangVien == null || !giangVien.getChucVu().equals("truongkhoa")) {
			response.sendRedirect(request.getContextPath() + "/giang-vien/logout");
			return;
		}
		
		
		
		
		// BO
		DanhGiaBO danhGiaBO = new DanhGiaBO();
		SinhVienBO sinhVienBO = new SinhVienBO();
		LopBO lopBO = new LopBO();
		DotDanhGiaBO dotDanhGiaBO = new DotDanhGiaBO();
		KhoaBO khoaBO = new KhoaBO();
		
		//Object
		DotDanhGia dotDanhGia = null;
		String xepLoai = "";
		ArrayList<DanhGia> listDanhGia = null;
		ArrayList<SinhVien> listSinhVien = null;
		ArrayList<Lop> listLop = null; 
		
		// Lay dot danh gia
		int maDot = 0;
		if(Validate.validateNumber(maDotPr)) {
			maDot = Integer.parseInt(maDotPr);
		}
		dotDanhGia = dotDanhGiaBO.getDotDanhGiaTheoMa(maDot);
		
		//Lay danh sach lop cua khoa
		listLop = lopBO.getAllLopByKhoa(giangVien.getMaKhoa());
		
		try {

			BaseFont timesNewRoman = BaseFont.createFont(request.getServletContext().getRealPath("/Resources/fonts/TIMES.TTF"), BaseFont.IDENTITY_H, false); 
			Font f10b = new Font(timesNewRoman, 11, Font.BOLD);
			Font f10 = new Font(timesNewRoman, 11, Font.NORMAL);
			Font f12b = new Font(timesNewRoman, 16, Font.BOLD);
			// step 1
	            Document document = new Document();
	            // step 2
	            ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            PdfWriter.getInstance(document, baos);
	            // step 3
	            document.open();
	            // step 4
	            /*******************************/
	            PdfPTable headTable = new PdfPTable(2);
	            PdfPCell cell = null;
	    		
	            cell = new PdfPCell(new Phrase("Đại Học Đà Nẵng", f10b));
	    		cell.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
	    		cell.setBorder(Rectangle.NO_BORDER);
	    		headTable.addCell(cell);
	    		
	    		cell = new PdfPCell(new Phrase("Cộng Hòa Xã Hội Chủ Nghĩa Việt Nam", f10b));
	    		cell.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
	    		cell.setBorder(Rectangle.NO_BORDER);
	    		headTable.addCell(cell);
	    		
	    		cell = new PdfPCell(new Phrase("TRường Đại Học Bách Khoa", f10b));
	    		cell.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
	    		cell.setBorder(Rectangle.NO_BORDER);
	    		headTable.addCell(cell);
	    		
	    		cell = new PdfPCell(new Phrase("Độc lập - Tự do - Hạnh phúc", f10b));
	    		cell.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
	    		cell.setBorder(Rectangle.NO_BORDER);
	    		headTable.addCell(cell);
	    		
	    		Chunk line = new Chunk("____________________", f10);
	    		line.setTextRise(12);
	    		cell = new PdfPCell(new Phrase(line));
	    		cell.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
	    		cell.setBorder(Rectangle.NO_BORDER);
	    		headTable.addCell(cell);
	    		
	    		headTable.addCell(cell);
	    		
	    		cell = new PdfPCell(new Phrase("DANH SÁCH ĐIỂM RÈN LUYỆN KHOA " + (khoaBO.getKhoaTheoMa(giangVien.getMaKhoa()) != null ? khoaBO.getKhoaTheoMa(giangVien.getMaKhoa()).getTen().toUpperCase() : ""),
	    				f12b));
	    		cell.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
	    		cell.setBorder(Rectangle.NO_BORDER);
	    		cell.setColspan(2);
	    		cell.setPaddingTop(30);
	    		headTable.addCell(cell);
	    		
	    		if (dotDanhGia != null) {
	    			cell = new PdfPCell(new Phrase(dotDanhGia.getTen(), f12b));
		    		cell.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
		    		cell.setBorder(Rectangle.NO_BORDER);
		    		cell.setColspan(2);
		    		cell.setPaddingBottom(40);
		    		headTable.addCell(cell);
	    		}
	    		
	    		document.add(headTable);
	    		
	    		// du lieu
	    		PdfPTable dataTable = new PdfPTable(7);
	    		
	    		dataTable.setWidths(new int[] { 3, 6, 3, 2, 2, 2, 2 });
	    		
	    		cell = new PdfPCell(new Phrase("MSSV", f10b));
	    		cell.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
	    		dataTable.addCell(cell);
	    		
	    		cell = new PdfPCell(new Phrase("Họ và tên", f10b));
	    		cell.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
	    		dataTable.addCell(cell);
	    		
	    		cell = new PdfPCell(new Phrase("Ngày Sinh", f10b));
	    		cell.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
	    		dataTable.addCell(cell);
	    		
	    		cell = new PdfPCell(new Phrase("Lớp", f10b));
	    		cell.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
	    		dataTable.addCell(cell);
	    		
	    		cell = new PdfPCell(new Phrase("Điểm tự rèn luyện", f10b));
	    		cell.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
	    		dataTable.addCell(cell);
	    		
	    		cell = new PdfPCell(new Phrase("Điểm tập thể lớp đánh giá", f10b));
	    		cell.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
	    		dataTable.addCell(cell);
	    		
	    		cell = new PdfPCell(new Phrase("Loại", f10b));
	    		cell.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
	    		dataTable.addCell(cell);
	    		
	    		for (int j = 0 ; j < listLop.size() ; j++) {
	    			listDanhGia = danhGiaBO.getAllDanhGiaTheoLopTheoDot(maDot, listLop.get(j).getMaLop());
	    			listSinhVien = sinhVienBO.getListSinhVienTuListDanhGia(listDanhGia);
		    		for (int i = 0 ; i < listDanhGia.size() ; i++) {
		    			
		    			cell = new PdfPCell(new Phrase(listSinhVien.get(i).getMssv(), f10));
			    		cell.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
			    		cell.setPadding(5);
			    		dataTable.addCell(cell);
			    		
			    		cell = new PdfPCell(new Phrase(listSinhVien.get(i).getTen(), f10));
			    		cell.setPadding(5);
			    		dataTable.addCell(cell);
			    		
			    		cell = new PdfPCell(new Phrase(DateUtils.formatDate(listSinhVien.get(i).getNgaySinh()), f10));
			    		cell.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
			    		cell.setPadding(5);
			    		dataTable.addCell(cell);
			    		
			    		cell = new PdfPCell(new Phrase(listLop.get(j).getTen(), f10));
			    		cell.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
			    		cell.setPadding(5);
			    		dataTable.addCell(cell);
			    		
			    		cell = new PdfPCell(new Phrase(listDanhGia.get(i).getTongDiem() + "", f10));
			    		cell.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
			    		cell.setPadding(5);
			    		dataTable.addCell(cell);
			    		
			    		cell = new PdfPCell(new Phrase(listDanhGia.get(i).getDiemTapTheLop() + "", f10));
			    		cell.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
			    		cell.setPadding(5);
			    		dataTable.addCell(cell);
			    		
			    		
			    		if (listDanhGia.get(i).getDiemTapTheLop() < 35)
							xepLoai = "Kém";
						else if (listDanhGia.get(i).getDiemTapTheLop() < 50)
							xepLoai = "Yếu";
						else if (listDanhGia.get(i).getDiemTapTheLop() < 65)
							xepLoai = "Trung bình";
						else if (listDanhGia.get(i).getDiemTapTheLop() < 80)
							xepLoai = "Khá";
						else if (listDanhGia.get(i).getDiemTapTheLop() < 90)
							xepLoai = "Tốt";
						else
							xepLoai = "Xuất sắc";
			    		
			    		cell = new PdfPCell(new Phrase(xepLoai, f10));
			    		cell.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
			    		cell.setPadding(5);
			    		dataTable.addCell(cell);
		    		}
	    		}
	    		cell = new PdfPCell(new Phrase("Đà Nẵng, ngày ... tháng ... năm ... ", f10b));
//	    		cell.setHorizontalAlignment(Rectangle.RIGHT);
	    		cell.setBorder(Rectangle.NO_BORDER);
	    		cell.setColspan(7);
	    		cell.setPaddingTop(30);
	    		dataTable.addCell(cell);
	    		
	    		cell = new PdfPCell(new Phrase("          Trưởng khoa", f10b));
//	    		cell.setHorizontalAlignment(Rectangle.RIGHT);
	    		cell.setBorder(Rectangle.NO_BORDER);
	    		cell.setColspan(7);
	    		cell.setPaddingBottom(40);
	    		dataTable.addCell(cell);
	    		
	    		
	    		document.add(dataTable);
	    		
	            // step 5
	            document.close();
	 
	            // setting some response headers
	            response.setHeader("Expires", "0");
	            response.setHeader("Cache-Control",
	                "must-revalidate, post-check=0, pre-check=0");
	            response.setHeader("Pragma", "public");
	            // setting the content type
	            response.setContentType("application/pdf ; charset=UTF-8");
	            // the contentlength
	            response.setContentLength(baos.size());
	            // write ByteArrayOutputStream to the ServletOutputStream
	            OutputStream os = response.getOutputStream();
	            baos.writeTo(os);
	            os.flush();
	            os.close();
	        }
	        catch(DocumentException e) {
	            e.printStackTrace();
	        }
	    }	

}
