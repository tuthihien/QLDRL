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

/**
 * Author Minh Kiet
 * Servlet implementation class SinhVienKetQuaDanhGiaServlet
 */
public class SinhVienKetQuaDanhGiaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SinhVienKetQuaDanhGiaServlet() {
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
		DotDanhGiaBO dotDanhGiaBO = new DotDanhGiaBO();
		DanhGiaBO danhGiaBO = new DanhGiaBO();
		
		//Object
		ArrayList<DotDanhGia> listDotDanhGia = null;
		SinhVien sinhVien = null;
		DanhGia danhGia = null;
		String xepLoai ;
		int diemXepLoai;
		
		//Lay sinh vien dang nhap
		sinhVien = MyUtils.getLoginedSinhVien(request.getSession());
		request.setAttribute("sinhVien", sinhVien);
		
		
		//kiem tra dang nhap
		if(sinhVien == null) {
			response.sendRedirect(request.getContextPath() + "/sinh-vien/logout");
			return;
		}
		
		//lay danh sach dot danh gia --> hien thi tren <select>
		listDotDanhGia = dotDanhGiaBO.getAllDotDanhGia();
				
		//Lay ma danh gia
		int maDotDanhGia = 0;
		String id = request.getParameter("id");
		if (listDotDanhGia != null)
			if (id == null && listDotDanhGia.size() > 0 && listDotDanhGia.get(0) != null) {
				//ma dot danh gia mac dinh
				System.out.println("o day roi");
				maDotDanhGia = listDotDanhGia.get(0).getMaDotDanhGia();
			} else 
				maDotDanhGia = Integer.parseInt(id);
		
		System.out.println(maDotDanhGia);
		
	
		//lay danh gia cua sinh vien theo id dot danh gia
		danhGia = danhGiaBO.getDanhGiaTheoMaSinhVien(sinhVien.getMaSinhVien(), maDotDanhGia);
		
		
		//xep loai
		
		if (danhGia != null) {
			if (danhGia.getDiemTapTheLop() > 0)
				diemXepLoai = danhGia.getDiemTapTheLop();
			else
				diemXepLoai = danhGia.getTongDiem();
			
			if (diemXepLoai < 35)
				xepLoai = "Kém";
			else if (diemXepLoai < 50)
				xepLoai = "Yếu";
			else if (diemXepLoai < 65)
				xepLoai = "Trung bình";
			else if (diemXepLoai < 80)
				xepLoai = "Khá";
			else if (diemXepLoai < 90)
				xepLoai = "Tốt";
			else
				xepLoai = "Xuất sắc";
			
			request.setAttribute("xepLoai", xepLoai);
		}
		
	
		//lay action
		String action = (String) request.getParameter("action");
		if (action == null) {
		
			//kiem tra cho hien thi nut sua danh gia
			DotDanhGia dotDanhGia = dotDanhGiaBO.getDotDanhGiaTheoMa(maDotDanhGia);
			Date thoiGianHienTai = new Date();
			if(dotDanhGia != null) {
				if (dotDanhGia.getNgayBatDauSV().before(DateUtils.convertToTimestamp(thoiGianHienTai)) 
						&& dotDanhGia.getNgayKetThucSV().after(DateUtils.convertToTimestamp(thoiGianHienTai))) {
					request.setAttribute("choSua", "choSua");
				}

			}
			
			//set Attribute
			request.setAttribute("danhGia", danhGia);
			request.setAttribute("dotDanhGia", dotDanhGia);
			request.setAttribute("listDotDanhGia", listDotDanhGia);
			
			RequestDispatcher rd = request.getRequestDispatcher("/sinhvien/ket-qua-danh-gia.jsp");
			rd.forward(request, response);
			
		} else {
			
			//action khac null
			switch (action) {
			case "xem":
				if (danhGia != null)
					response.sendRedirect(request.getContextPath() + "/sinh-vien/ket-qua-danh-gia/chi-tiet?id=" + danhGia.getMaDanhGia());
					break;
				
			case "sua":
				if (danhGia != null)
					response.sendRedirect(request.getContextPath() + "/sinh-vien/cap-nhat-danh-gia?id=" + danhGia.getMaDanhGia());
					break;
			}
			
		}
		
	}

}
