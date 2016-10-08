package controller.phongctsv;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.GiangVien;
import model.bo.GiangVienBO;
import model.bo.SinhVienBO;
import utils.MyUtils;

/**
 * Servlet implementation class PhongCTSVDanhSachSinhVienXoa
 */
public class PhongCTSVDanhSachSinhVienXoa extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PhongCTSVDanhSachSinhVienXoa() {
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
		// kiem tra dang nhap
		GiangVien giangVien = MyUtils.getLoginedGiangVien(request.getSession());
		request.setAttribute("giangVien", giangVien);
		if (giangVien == null || !giangVien.getChucVu().equals("ctsv")) {
			response.sendRedirect(request.getContextPath() + "/giang-vien/logout");
			return;
		}
		String maKhoa = request.getParameter("idKhoa");
		String maLop = request.getParameter("idLop");
		String[] listIdGiangVien = request.getParameterValues("chk");
		SinhVienBO sinhVienBO = new SinhVienBO();
		String errMsg = "";
		if ("".equals(maKhoa)) {
			maKhoa = null;
		}
		if ("".equals(maLop)) {
			maLop = null;
		}
		if (listIdGiangVien != null) {

			if (sinhVienBO.deleteSinhVienByCheckBox(listIdGiangVien) == 1) {
				request.setAttribute("idLop", maLop);
				request.setAttribute("idKhoa", maKhoa);
				errMsg = "1";
				request.setAttribute("errMsg", errMsg);

				RequestDispatcher rd = request.getRequestDispatcher("/phong-ctsv/danhsach-sinhvien");
				rd.forward(request, response);
			} else {
				request.setAttribute("idLop", maLop);
				request.setAttribute("idKhoa", maKhoa);
				errMsg = "0";
				request.setAttribute("errMsg", errMsg);
				RequestDispatcher rd = request.getRequestDispatcher("/phong-ctsv/danhsach-sinhvien");
				rd.forward(request, response);
			}
		} else {
			request.setAttribute("idLop", maLop);
			request.setAttribute("idKhoa", maKhoa);
			errMsg = "2";
			request.setAttribute("errMsg", errMsg);
			RequestDispatcher rd = request.getRequestDispatcher("/phong-ctsv/danhsach-sinhvien");
			rd.forward(request, response);
		}
	}

}
