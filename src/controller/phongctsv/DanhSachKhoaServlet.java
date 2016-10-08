package controller.phongctsv;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.MyUtils;
import utils.Validate;
import model.bean.GiangVien;
import model.bean.Khoa;
import model.bo.KhoaBO;

/**
 * Servlet implementation class DanhSachKhoaServlet
 */

public class DanhSachKhoaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DanhSachKhoaServlet() {
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
		
		//getParameter
		String action = request.getParameter("action");
		String maKhoaPr =  request.getParameter("id");
		
		//BO
		KhoaBO khoaBO = new KhoaBO();
		
		//Object
		ArrayList<Khoa> listKhoa = null;
		GiangVien giangVien = null;
		
		//Kiem tra dang nhap
		giangVien = MyUtils.getLoginedGiangVien(request.getSession());
		if (giangVien == null || !giangVien.getChucVu().equals("ctsv")) {
			response.sendRedirect(request.getContextPath() + "/giang-vien/logout");
			return;
		}
		request.setAttribute("giangVien", giangVien);
		
		//kiem tra action
		if (action == null) {
			
			listKhoa = khoaBO.getAllKhoa();
			
			request.setAttribute("listKhoa", listKhoa);
			
			RequestDispatcher rd = request.getRequestDispatcher("/phongCTSV/ds-khoa.jsp");
			rd.forward(request, response);
			
		} else {
			int maKhoa = 0;
			if (Validate.validateNumber(maKhoaPr)) {
				maKhoa = Integer.parseInt(maKhoaPr);
			}
			
			khoaBO.deleteKhoa(maKhoa);
			response.sendRedirect(request.getContextPath() + "/phong-ctsv/ds-khoa");
		}
	}

}
