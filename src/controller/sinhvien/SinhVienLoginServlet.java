package controller.sinhvien;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.MyUtils;
import model.bean.DotDanhGia;
import model.bean.SinhVien;
import model.bo.DotDanhGiaBO;
import model.bo.SinhVienBO;

/**
 * Servlet implementation class SinhVienLoginServlet
 */
public class SinhVienLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SinhVienLoginServlet() {
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
		DotDanhGiaBO dotDanhGiaBO = new DotDanhGiaBO();
		
		//Object
		SinhVien sinhVien = null;
		DotDanhGia dotDanhGia = null;
		
		//action
		String action = (String) request.getParameter("action");
		
		if (action == null) {
		/////////////// load trang //////////////////
			dotDanhGia = dotDanhGiaBO.getDotDanhGiaMoiNhat();
			request.setAttribute("dotDanhGia", dotDanhGia);
			
			RequestDispatcher rd = request.getRequestDispatcher("/sinhvien/sinh-vien-login.jsp");
			rd.forward(request, response);
			
		} else {
		
		/////////////// kiem tra action //////////
			String tenDangNhap = (String) request.getParameter("username");
			String matKhau = (String) request.getParameter("password");

			sinhVien = sinhVienBO.getSinhVienDangNhap(tenDangNhap, matKhau);
			MyUtils.storeLoginedSinhVien(request.getSession(), sinhVien);
			
			//kiem tra xem sinh vien la lop truong hay khong
			response.sendRedirect(request.getContextPath() + "/sinh-vien/thong-tin-ca-nhan");
			
		}
		
	}

}
