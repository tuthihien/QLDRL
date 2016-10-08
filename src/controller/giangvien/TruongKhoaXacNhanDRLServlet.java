package controller.giangvien;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.DanhGia;
import model.bean.DotDanhGia;
import model.bean.GiangVien;
import model.bean.Khoa;
import model.bean.Lop;
import model.bean.SinhVien;
import model.bo.DanhGiaBO;
import model.bo.DotDanhGiaBO;
import model.bo.KhoaBO;
import model.bo.LopBO;
import model.bo.SinhVienBO;
import utils.DateUtils;
import utils.MyUtils;

/**
 * Servlet implementation class TruongKhoaXacNhanDRLServlet
 */

public class TruongKhoaXacNhanDRLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TruongKhoaXacNhanDRLServlet() {
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
		LopBO lopBO = new LopBO();
		KhoaBO khoaBO = new KhoaBO();
		DotDanhGiaBO dotDanhGiaBO = new DotDanhGiaBO();
		DanhGiaBO danhGiaBO = new DanhGiaBO();
		SinhVienBO sinhVienBO = new SinhVienBO();
		
		//Object
		GiangVien giangVien = null;
		ArrayList<Lop> listLop = null;
		ArrayList<DanhGia> listDanhGia = null;
		ArrayList<SinhVien> listSinhVien = null;
		Khoa khoa = null;
		DotDanhGia dotDanhGia = null;
		
		
		//get parameter
		String action = (String) request.getParameter("action");
		String maLopHienTaiStr = (String) request.getParameter("lop");
		
		//Kiem tra dang nhap
		giangVien = MyUtils.getLoginedGiangVien(request.getSession());
		if (giangVien == null || !giangVien.getChucVu().equals("truongkhoa")) {
			response.sendRedirect(request.getContextPath() + "/sinh-vien/logout");
			return;
		}
		request.setAttribute("giangVien", giangVien);
		
		//kiem tra co phai thoi gian xac nhan hay khong
		Date thoiGianHienTai = new Date();
		dotDanhGia = dotDanhGiaBO.getDotDanhGiaHienTaiTK(DateUtils.convertToTimestamp(thoiGianHienTai));
		request.setAttribute("dotDanhGia", dotDanhGia);
		
		
		if (action == null) {
			
			/*------------load trang---------------*/
			
			// Lay khoa
			khoa = khoaBO.getKhoaTheoMa(giangVien.getMaKhoa());
			
			//Lay danh sach lop giang vien nay chu nhiem
			listLop = lopBO.getAllLopByKhoa(giangVien.getMaKhoa());
			
			//Lay ma lop hien tai
			int maLopHienTai = 0;
			if (maLopHienTaiStr == null && listLop.get(0) != null) {
				maLopHienTai = listLop.get(0).getMaLop();
			} else {
				maLopHienTai = Integer.parseInt(maLopHienTaiStr);
			}
				
			int page = 1;
			int recordsPerPage = 12;
			if (request.getParameter("page") != null)
				page = Integer.parseInt(request.getParameter("page"));
			if (dotDanhGia != null) {
				//lay du lieu danh sach danh gia ma tap the lop da danh gia
				listDanhGia = danhGiaBO.getAllDanhGiaTheoLopTT(dotDanhGia.getMaDotDanhGia(), maLopHienTai, "truongkhoadaduyet", "gvcndaduyet", (page - 1) * recordsPerPage, recordsPerPage);
				listSinhVien = sinhVienBO.getListSinhVienTuListDanhGia(listDanhGia);
				
				//phan trang
				int noOfRecords = danhGiaBO.getCountDanhGiaTheoLopTT(dotDanhGia.getMaDotDanhGia(), maLopHienTai, "truongkhoadaduyet", "gvcndaduyet");
				int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
				
				
				request.setAttribute("listDanhGia", listDanhGia);
				request.setAttribute("listSinhVien", listSinhVien);
				request.setAttribute("noOfPages", noOfPages);
			    request.setAttribute("currentPage", page);
			    request.setAttribute("maLopHienTai", maLopHienTai);
			}
			
			//set attribute
			request.setAttribute("khoa", khoa);
			request.setAttribute("listLop", listLop);
			
			RequestDispatcher rd = request.getRequestDispatcher("/giangvien/truong-khoa-ds-xac-nhan-drl.jsp");
			rd.forward(request, response);
		
		} else {
			
			/* --------- action-----------------------*/
			if(action.equals("submit")) {
				
				//lay danh sach danh gia duoc chon
				String [] chon = request.getParameterValues("chon");
				listDanhGia = danhGiaBO.getAllDanhGiaTheoId(chon);
				
				//update tinh trang va ngay gvcn xn
				for (int i = 0; i < listDanhGia.size(); i++) {
					listDanhGia.get(i).setTinhTrang("truongkhoadaduyet");
					listDanhGia.get(i).setNgayXacNhanTK(DateUtils.convertToTimestamp(thoiGianHienTai));
				}
				
				//day vao database
				danhGiaBO.updateDanhGia(listDanhGia);
				
				//chuyen huong
				
				response.sendRedirect(request.getContextPath() + "/giang-vien/truong-khoa/danh-sach-xac-nhan?lop=" 
						+ request.getParameter("lop") + "&page=" + request.getParameter("page"));
			}
		}	
	}

}
