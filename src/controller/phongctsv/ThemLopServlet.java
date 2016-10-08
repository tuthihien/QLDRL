package controller.phongctsv;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
import model.bean.Lop;
import model.bo.GiangVienBO;
import model.bo.KhoaBO;
import model.bo.LopBO;

/**
 * Servlet implementation class ThemLopServlet
 */
@WebServlet("/ThemLopServlet")
public class ThemLopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThemLopServlet() {
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
		String makhoaPr = request.getParameter("khoa");
	
		LopBO lopBO = new LopBO();
		KhoaBO khoaBO = new KhoaBO();
		GiangVienBO giangVienBO = new GiangVienBO();
		
		Lop lop = null;
		GiangVien giangVien = null;
		ArrayList<Khoa> listKhoa = null;
		ArrayList<GiangVien> listGVCN = null;
		
		//kiem tra dang nhap
		giangVien = MyUtils.getLoginedGiangVien(request.getSession());
		request.setAttribute("giangVien", giangVien);
		if (giangVien == null || !giangVien.getChucVu().equals("ctsv")) {
			response.sendRedirect(request.getContextPath() + "/giang-vien/logout");
			return;
		}
		
		if (action == null) {
			
			if (makhoaPr != null) {
				int makhoa = 0;
				if (Validate.validateNumber(makhoaPr)) {
					makhoa = Integer.parseInt(makhoaPr);
				}
				
				String rs;
				rs = "<label class=\"control-label col-sm-4\" for=\"name\">GVCN</label>";
				rs += "<div class=\"col-sm-6\">";
				rs += "<select name=\"gvcn\" class=\"form-control\" id=\"sel1\" style=\"width: 50%\">";
				rs += "<option value=\"0\" disabled=\"disabled\">==Chọn GVCN==</option>";
				listGVCN = giangVienBO.getGiangVienByKhoa(makhoa);
				for (GiangVien gvcn : listGVCN) {
					if (gvcn.getChucVu().equals("gvcn"))
					rs += "<option value=\"" + gvcn.getMaGiangVien() + "\">" + gvcn.getTen() + "</option>";
				}
				rs += "</select>" + "</div>" + "</div>";
				PrintWriter out = response.getWriter();
				out.println( rs);
				return;
				
			} else {
				listKhoa = khoaBO.getAllKhoa();
				request.setAttribute("listKhoa", listKhoa);
				RequestDispatcher rd = request.getRequestDispatcher("/phongCTSV/them-lop.jsp");
				rd.forward(request, response);
			}
			
		} else {
			String tenLop = request.getParameter("tenLop");
			String khoa = request.getParameter("khoa");
			String gvcn = request.getParameter("gvcn");
			System.out.println(tenLop + " " + khoa + " " + gvcn);
			boolean hasError = false;
	
			if (tenLop == null || tenLop.trim().length() == 0 || khoa == null || gvcn == null) {
				hasError = true;
			}
			
			if (hasError) {
				response.sendRedirect(request.getContextPath() + "/phong-ctsv/ds-lop");
				return;
			}
			lop = new Lop(Integer.parseInt(khoa), tenLop, Integer.parseInt(gvcn));
			System.out.println(lop.toString());
			lopBO.insertLop(lop);
			response.sendRedirect(request.getContextPath() + "/phong-ctsv/ds-lop");
		}
	}

}
