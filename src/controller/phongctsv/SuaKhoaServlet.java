package controller.phongctsv;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.MyUtils;
import utils.Validate;
import model.bean.GiangVien;
import model.bean.Khoa;
import model.bo.KhoaBO;

/**
 * Servlet implementation class SuaKhoaServlet
 */
@WebServlet("/SuaKhoaServlet")
public class SuaKhoaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuaKhoaServlet() {
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
		
		String action = request.getParameter("action");
		String maKhoaPr = request.getParameter("id");
		
		KhoaBO khoaBO = new KhoaBO();
		
		Khoa khoa = null;
		
		
		//kiem tra dang nhap
		GiangVien giangVien = MyUtils.getLoginedGiangVien(request.getSession());
		request.setAttribute("giangVien", giangVien);
		if (giangVien == null || !giangVien.getChucVu().equals("ctsv")) {
			response.sendRedirect(request.getContextPath() + "/giang-vien/logout");
			return;
		}
		
		int maKhoa = 0;
		if (Validate.validateNumber(maKhoaPr)) {
			maKhoa = Integer.parseInt(maKhoaPr);
		}
		khoa = khoaBO.getKhoaTheoMa(maKhoa);
		
		if (action == null) {
			
			request.setAttribute("khoa", khoa);
			RequestDispatcher rd = request.getRequestDispatcher("/phongCTSV/sua-khoa.jsp");
			rd.forward(request, response);
		} else {
			String tenKhoa = request.getParameter("tenKhoa");
			
			if (khoa == null || tenKhoa == null || tenKhoa.trim().length() == 0) {
				response.sendRedirect(request.getContextPath() + "/phong-ctsv/ds-khoa");
				return;
			}		
			khoa.setTen(tenKhoa);
			khoaBO.updateKhoa(khoa);
			response.sendRedirect(request.getContextPath() + "/phong-ctsv/ds-khoa");
		}
	}

}
