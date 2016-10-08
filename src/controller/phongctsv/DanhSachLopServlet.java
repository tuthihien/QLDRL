package controller.phongctsv;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.GiangVien;
import model.bean.Lop;
import model.bo.LopBO;
import utils.MyUtils;
import utils.Validate;

/**
 * Servlet implementation class DanhSachServlet
 */

public class DanhSachLopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DanhSachLopServlet() {
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
		String maLopPr =  request.getParameter("id");
		
		//BO
		LopBO lopBO = new LopBO();
		
		
		//Object
		ArrayList<Lop> listLop = null;
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
			
			listLop = lopBO.getAllLop();
			
			request.setAttribute("listLop", listLop);
			
			RequestDispatcher rd = request.getRequestDispatcher("/phongCTSV/ds-lop.jsp");
			rd.forward(request, response);
			
		} else {
			int maLop = 0;
			if (Validate.validateNumber(maLopPr)) {
				maLop = Integer.parseInt(maLopPr);
			}
			
			lopBO.deleteLop(maLop);
			response.sendRedirect(request.getContextPath() + "/phong-ctsv/ds-lop");
		}
	}

}
