package controller.giangvien;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.DateUtils;
import utils.MyUtils;
import utils.Validate;
import model.bean.GiangVien;
import model.bean.Khoa;
import model.bo.GiangVienBO;
import model.bo.KhoaBO;

/**
 * Servlet implementation class GiangVienCapNhatThongTinServlet
 */

public class GiangVienCapNhatThongTinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GiangVienCapNhatThongTinServlet() {
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
		KhoaBO khoaBO = new KhoaBO();
		GiangVienBO giangVienBO = new GiangVienBO();
		
		//Object
		Khoa khoa = null;
		GiangVien giangVien = null;
		
		giangVien = MyUtils.getLoginedGiangVien(request.getSession());
		if (giangVien == null) {
			response.sendRedirect(request.getContextPath() + "/giang-vien/logout");
			return;
		}
		
		String action = (String) request.getParameter("action");
		if(action == null) {
			
			/*-----------------load trang------------------*/
			khoa = khoaBO.getKhoaTheoMa(giangVien.getMaKhoa());
			if (khoa != null)
				request.setAttribute("khoa", khoa);
			request.setAttribute("giangVien", giangVien);
			RequestDispatcher rd = request.getRequestDispatcher("/giangvien/giang-vien-cap-nhat-thong-tin.jsp");
			rd.forward(request, response);
			
		} else {
			//////////////// cap nhat /////////////
			
			String email = (String) request.getParameter("email");
			String sdt = (String) request.getParameter("sdt");
			String ngaySinh = (String) request.getParameter("ngaySinh");
			String matKhauCu = (String) request.getParameter("matKhauCu");
			String matKhauMoi = (String) request.getParameter("matKhauMoi");
			String xnMatKhauMoi = (String) request.getParameter("xnMatKhauMoi");
			
			
			
			boolean hasError = false;
			
			//validate
			if (!Validate.validateEmail(email) || !Validate.validateNumber(sdt)) {
				hasError = true;
			}
			
			if(matKhauCu.trim().length() > 0) {
				if(!Validate.validatePassword(matKhauCu) || !Validate.validatePassword(matKhauMoi)
						|| !Validate.validatePassword(xnMatKhauMoi) || !giangVien.getMatKhau().equals(matKhauCu) ||!matKhauMoi.equals(xnMatKhauMoi)) {
					hasError = true;
				}
			} else {
				if (matKhauMoi.trim().length() > 0 || xnMatKhauMoi.trim().length() > 0)
					hasError = true;
			}
			
			//xay ra loi
			if (hasError) {
				response.sendRedirect(request.getContextPath() + "/giang-vien/thong-tin-ca-nhan?tt=error");
			} else {
			
			//ko co loi
				giangVien.setNgaySinh(DateUtils.convertToSDate(ngaySinh));
				giangVien.setEmail(email);
				giangVien.setTenDangNhap(email);
				giangVien.setSdt(sdt);
				if(matKhauMoi.length() > 0)
					giangVien.setMatKhau(matKhauMoi);
				System.out.println(giangVien.toString());
				giangVienBO.updateGiangVien(giangVien);
				MyUtils.storeLoginedGiangVien(request.getSession(), giangVien);
				response.sendRedirect(request.getContextPath() + "/giang-vien/thong-tin-ca-nhan?tt=ok");
			}
		}
	}

}










