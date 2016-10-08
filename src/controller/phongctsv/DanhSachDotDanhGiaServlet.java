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
import model.bean.DotDanhGia;
import model.bean.GiangVien;
import model.bo.DotDanhGiaBO;

/**
 * Servlet implementation class DanhSachDotDanhGiaServlet
 */

public class DanhSachDotDanhGiaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DanhSachDotDanhGiaServlet() {
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
		String action = (String) request.getParameter("action");
		String maDotDanhGiaPr = (String) request.getParameter("id"); 
		
		//BO
		DotDanhGiaBO dotDanhGiaBO = new DotDanhGiaBO();
		
		//Object
		ArrayList<DotDanhGia> listDotDanhGia = null;
		GiangVien giangVien = null;
		
		//kiem tra dang nhap
		giangVien = MyUtils.getLoginedGiangVien(request.getSession());
		request.setAttribute("giangVien", giangVien);
		if (giangVien == null || !giangVien.getChucVu().equals("ctsv")) {
			response.sendRedirect(request.getContextPath() + "/giang-vien/logout");
			return;
		}
		
		//kiem tra action
		if (action == null) {
			
			//lay danh sach dot danh gia
			listDotDanhGia = dotDanhGiaBO.getAllDotDanhGia();
			
			//setAtribute
			request.setAttribute("listDotDanhGia", listDotDanhGia);
			
			//dieu huong
			RequestDispatcher rd = request.getRequestDispatcher("/phongCTSV/ds-dot-danh-gia.jsp");
			rd.forward(request, response);
			
		} else {
			int maDotDanhGia = 0;
			if (Validate.validateNumber(maDotDanhGiaPr)) {
					maDotDanhGia = Integer.parseInt(maDotDanhGiaPr);
				}
			dotDanhGiaBO.deleteDotDanhGia(maDotDanhGia);
			response.sendRedirect(request.getContextPath() + "/phong-ctsv/ds-dot-danh-gia");
		}
		
	}

}
