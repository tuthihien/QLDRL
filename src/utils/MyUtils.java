package utils;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.GiangVien;
import model.bean.SinhVien;




public class MyUtils {

	/**
	 * <h1>storeLoginedGiangVien</h1>
	 * Luu thong tin GiangVien vao session
	 * @param session
	 * @param loginedGiangVien Thong tin GiangVien can luu
	 */
	public static void storeLoginedGiangVien(HttpSession session, GiangVien loginedGiangVien) {
		// Trên JSP có thể truy cập ${loginedUser}
		session.setAttribute("LoginedGiangVien", loginedGiangVien);
	}

	/**
	 * <h1>getLoginedGiangVien</h1>
	 * Lay thong tin GiangVien tu session
	 * @param session
	 * @return loginedGiangVien GiangVien from session
	 */
	public static GiangVien getLoginedGiangVien(HttpSession session) {
		GiangVien loginedGiangVien = (GiangVien) session.getAttribute("LoginedGiangVien");
		return loginedGiangVien;
	}

	 /**
	   *<h1>storeLoginedSinhVien</h1>
	   * Luu thong tin SinhVien vao session
	   * @param arg session
	   * @param arg loginedSinhVien
	   * @return No return value.
	   */
	public static void storeLoginedSinhVien(HttpSession session, SinhVien loginedSinhVien) {
		// Trên JSP có thể truy cập ${loginedUser}
		session.setAttribute("LoginedSinhVien", loginedSinhVien);
	}

	/**
	 * <h1>getLoginedSinhVien</h1>
	 * Lay thong tin sinh vien da luu trong session
	 * @param arg session
	 * @return SinhVien or null if it does not exist
	 */
	public static SinhVien getLoginedSinhVien(HttpSession session) {
		SinhVien loginedUser = (SinhVien) session.getAttribute("LoginedSinhVien");
		return loginedUser;
	}
	
	/**
	 * <h1>invalidateSesstion</h1>
	 * Xoa session
	 * @param arg session
	 * @return No return value.
	 */
	public static void invalidateSesstion(HttpSession session) {
		session.invalidate();
	}
	
	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * 
	 */
	public static void forward(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
		RequestDispatcher rd = servletContext.getRequestDispatcher(url);
		rd.forward(request, response);
	}
}
