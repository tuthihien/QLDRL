package controller.phongctsv;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.MyUtils;
import model.bean.GiangVien;
import model.bo.DanhGiaBO;

/**
 * Servlet implementation class PhongCTSVXacNhanDanhGia
 */
public class PhongCTSVXacNhanDanhGia extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PhongCTSVXacNhanDanhGia() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String[] listIddanhgia = request.getParameterValues("chk");
		
		//kiem tra dang nhap
				GiangVien giangVien = MyUtils.getLoginedGiangVien(request.getSession());
				request.setAttribute("giangVien", giangVien);
				if (giangVien == null || !giangVien.getChucVu().equals("ctsv")) {
					response.sendRedirect(request.getContextPath() + "/giang-vien/logout");
					return;
				}
	
			String idKhoa = request.getParameter("idKhoa");
			request.setAttribute("idKhoa", idKhoa);
			String idLop = request.getParameter("idLop");
			request.setAttribute("idLop", idLop);
			// xac nhan dnah gia
			String errMsg = "";
			DanhGiaBO danhGiaBO = new DanhGiaBO();
			if (listIddanhgia != null) {
			if (danhGiaBO.xacNhanDanhGia(listIddanhgia) > 0) {
				errMsg = "1";
				request.setAttribute("errMsg", errMsg);
				RequestDispatcher rd = request.getRequestDispatcher("/phong-ctsv/danhsach-ketqua");
				rd.forward(request, response);
			} else {
				errMsg = "0";
				request.setAttribute("errMsg", errMsg);
				RequestDispatcher rd = request.getRequestDispatcher("/phong-ctsv/danhsach-ketqua");
				rd.forward(request, response);
			}
		}else{
			errMsg = "2";
			request.setAttribute("errMsg", errMsg);
			RequestDispatcher rd = request.getRequestDispatcher("/phong-ctsv/danhsach-ketqua");
			rd.forward(request, response);

		}
	}

}
