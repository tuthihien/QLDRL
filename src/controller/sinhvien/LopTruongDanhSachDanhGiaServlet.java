package controller.sinhvien;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.DateUtils;
import utils.MyUtils;
import model.bean.DanhGia;
import model.bean.DotDanhGia;
import model.bean.SinhVien;
import model.bo.DanhGiaBO;
import model.bo.DotDanhGiaBO;
import model.bo.SinhVienBO;

/**
 * Author Minh Kiet
 * Servlet implementation class LopTruongDanhGiaServlet
 */
public class LopTruongDanhSachDanhGiaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LopTruongDanhSachDanhGiaServlet() {
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
		
		//Object
		SinhVien sinhVien = null;
		ArrayList<DanhGia> listDanhGia = null;
		ArrayList<SinhVien> listSinhVien = null;
		String [] listXepLoai = null;
		DotDanhGia dotDanhGia = null;
		
		//Kiem tra dang nhap
		sinhVien = MyUtils.getLoginedSinhVien(request.getSession());
		request.setAttribute("sinhVien", sinhVien);
		if(sinhVien == null || !sinhVien.getChucVu().equals("lớp trưởng")) {
			response.sendRedirect(request.getContextPath() + "/sinh-vien/logout");
			return;
		}
		
		//Lay dot danh gia hien tai
		Date thoiGianHienTai = new Date();
		dotDanhGia = dotDanhGiaBO.getDotDanhGiaHienTaiLT(DateUtils.convertToTimestamp(thoiGianHienTai));
		request.setAttribute("dotDanhGia", dotDanhGia);
		
		String action = (String) request.getParameter("action");
		if (action == null) {
		/*--------------------- load trang -------------------------------*/
			int page = 1;
			int recordsPerPage = 12;
			if (request.getParameter("page") != null)
				page = Integer.parseInt(request.getParameter("page"));
			
			if (dotDanhGia != null) {
				// lay danh sach danh gia cua sinh vien cung lop trong dot danh gia
				listDanhGia = danhGiaBO.getAllDanhGiaTheoLopTT(dotDanhGia.getMaDotDanhGia(), sinhVien.getMaLop(), "tapthelopchuaduyet", "tapthelopdaduyet", (page - 1) * recordsPerPage, recordsPerPage);
				listSinhVien = sinhVienBO.getListSinhVienTuListDanhGia(listDanhGia);
				
				
				//xep loai
				listXepLoai = new String [listDanhGia.size()];
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
				int noOfRecords = danhGiaBO.getCountDanhGiaTheoLopTT(dotDanhGia.getMaDotDanhGia(), sinhVien.getMaLop(), "tapthelopchuaduyet", "tapthelopdaduyet");
				int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
				
				request.setAttribute("listDanhGia", listDanhGia);
				request.setAttribute("listSinhVien", listSinhVien);
				request.setAttribute("listXepLoai", listXepLoai);
			    request.setAttribute("noOfPages", noOfPages);
			    request.setAttribute("currentPage", page);
			    
			}			
			RequestDispatcher rd = request.getRequestDispatcher("/sinhvien/lop-truong-danh-sach-danh-gia.jsp");
			rd.forward(request, response);
		
		} else {
		/*--------------------- action -------------------------------*/
			switch (action) {
			case "danhGia":
				
				break;
			}
		}
	}

}
