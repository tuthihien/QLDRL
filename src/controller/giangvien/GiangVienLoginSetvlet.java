package controller.giangvien;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.DotDanhGia;
import model.bean.GiangVien;
import model.bo.DotDanhGiaBO;
import model.bo.GiangVienBO;
import utils.MyUtils;

/**
 * Servlet implementation class GiangVienLoginSetvlet
 */
public class GiangVienLoginSetvlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GiangVienLoginSetvlet() {
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
				GiangVienBO giangVienBO = new GiangVienBO();
				DotDanhGiaBO dotDanhGiaBO = new DotDanhGiaBO();
				
				//Object
				GiangVien giangVien = null;
				DotDanhGia dotDanhGia = null;
				
				//action
				String action = (String) request.getParameter("action");
				
				if (action == null) {
				/////////////// load trang //////////////////
					dotDanhGia = dotDanhGiaBO.getDotDanhGiaMoiNhat();
					request.setAttribute("dotDanhGia", dotDanhGia);
					
					RequestDispatcher rd = request.getRequestDispatcher("/giangvien/giang-vien-login.jsp");
					rd.forward(request, response);
					
				} else {
				
				/////////////// kiem tra action //////////
				String tenDangNhap = (String) request.getParameter("username"); 
				String matKhau = (String) request.getParameter("password");

				giangVien = giangVienBO.getGiangVienDangNhap(tenDangNhap, matKhau);
				
				MyUtils.storeLoginedGiangVien(request.getSession(), giangVien);
					
				
				response.sendRedirect(request.getContextPath() + "/giang-vien/thong-tin-ca-nhan");
					
				}
	}

}
