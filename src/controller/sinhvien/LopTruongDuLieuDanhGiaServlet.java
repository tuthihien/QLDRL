package controller.sinhvien;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.MyUtils;
import utils.Validate;
import model.bean.DanhGia;
import model.bean.DotDanhGia;
import model.bean.Lop;
import model.bean.SinhVien;
import model.bo.DanhGiaBO;
import model.bo.DotDanhGiaBO;
import model.bo.LopBO;
import model.bo.SinhVienBO;

/**
 * Servlet implementation class LopTruongDuLieuDanhGiaServlet
 */

public class LopTruongDuLieuDanhGiaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LopTruongDuLieuDanhGiaServlet() {
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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		
		//BO
		SinhVienBO sinhVienBO = new SinhVienBO();
		DanhGiaBO danhGiaBO = new DanhGiaBO();
		DotDanhGiaBO dotDanhGiaBO = new DotDanhGiaBO();
		LopBO lopBO = new LopBO();
		
		//Object
		SinhVien sinhVien = null;
		ArrayList<DanhGia> listDanhGia = null;
		ArrayList<DotDanhGia> listDotDanhGia = null;
		ArrayList<SinhVien> listSinhVien = null;
		String [] listXepLoai = null;
		DotDanhGia dotDanhGia = null;
		Lop lop = new Lop(); 
		
		//Kiem tra dang nhap
		sinhVien = MyUtils.getLoginedSinhVien(request.getSession());
		request.setAttribute("sinhVien", sinhVien);
		if(sinhVien == null || !sinhVien.getChucVu().equals("lớp trưởng")) {
			response.sendRedirect(request.getContextPath() + "/sinh-vien/logout");
			return;
		}
		
		//getParameter
		String dotDanhGiaPr = request.getParameter("dot");
		
		//lay danh sach cac dot danh gia
		listDotDanhGia = dotDanhGiaBO.getAllDotDanhGia();
		System.out.println(listDotDanhGia.size());
		
		//lay dot danh gia can xem
		if (dotDanhGiaPr == null) {
			if (listDotDanhGia.size() > 0) {
				dotDanhGia = listDotDanhGia.get(0);
			}
		} else {
			if (Validate.validateNumber(dotDanhGiaPr)) {
				dotDanhGia = dotDanhGiaBO.getDotDanhGiaTheoMa(Integer.parseInt(dotDanhGiaPr));
			}
		}
		
		//lay lop
		lop = lopBO.getLopTheoMa(sinhVien.getMaLop());
		
		//lay listDanhGia
		int page = 1;
		int recordsPerPage = 12;
		int noOfRecords = 0, noOfPages = 0;
		if (request.getParameter("page") != null)
			page = Integer.parseInt(request.getParameter("page"));
		if (lop != null && dotDanhGia != null) {
			listDanhGia = danhGiaBO.getAllDanhGiaTheoLopTheoDotPhanTrang(dotDanhGia.getMaDotDanhGia(), lop.getMaLop(), (page - 1) * recordsPerPage, recordsPerPage);
			listSinhVien = sinhVienBO.getListSinhVienTuListDanhGia(listDanhGia);
			
			//xep loai
			listXepLoai = new String [listDanhGia.size()];
			if (listDanhGia.size() > 0)
			for (int i = 0; i < listDanhGia.size(); i++) {
				
				if (listDanhGia.get(i).getDiemTapTheLop() < 35)
					listXepLoai[i] = "Kém";
				else if (listDanhGia.get(i).getDiemTapTheLop() < 50)
					listXepLoai[i] = "Yếu";
				else if (listDanhGia.get(i).getDiemTapTheLop() < 65)
					listXepLoai[i] = "Trung Bình";
				else if (listDanhGia.get(i).getDiemTapTheLop() < 80)
					listXepLoai[i] = "Khá";
				else if (listDanhGia.get(i).getDiemTapTheLop() < 90)
					listXepLoai[i] = "Tốt";
				else
					listXepLoai[i] = "Xuất sắc";
			}
			
			//phan trang
			noOfRecords = danhGiaBO.getCountDanhGiaTheoLopTheoDot(dotDanhGia.getMaDotDanhGia(), sinhVien.getMaLop());
			noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		}
		
		request.setAttribute("listDanhGia", listDanhGia);
		request.setAttribute("listSinhVien", listSinhVien);
		request.setAttribute("listXepLoai", listXepLoai);
	    request.setAttribute("noOfPages", noOfPages);
	    request.setAttribute("currentPage", page);
	    request.setAttribute("sinhVien", sinhVien);
	    request.setAttribute("listDotDanhGia", listDotDanhGia);
	    request.setAttribute("dotDanhGia", dotDanhGia);
	    request.setAttribute("lop", lop);
	    
	    RequestDispatcher rd = request.getRequestDispatcher("/sinhvien/lop-truong-du-lieu-danh-gia.jsp");
		rd.forward(request, response);
	    
	}

}
