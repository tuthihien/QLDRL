package controller.sinhvien;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.DateUtils;
import utils.MyUtils;
import utils.Validate;
import model.bean.Khoa;
import model.bean.Lop;
import model.bean.SinhVien;
import model.bo.KhoaBO;
import model.bo.LopBO;
import model.bo.SinhVienBO;

/**
 * Author Minh Kiet
 * Servlet implementation class SinhVienCapNhatThongTinServlet
 */

public class SinhVienCapNhatThongTinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SinhVienCapNhatThongTinServlet() {
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
		LopBO lopBO = new LopBO();
		KhoaBO khoaBO = new KhoaBO();
		SinhVienBO sinhVienBO = new SinhVienBO();
		
		//Bean
		SinhVien sinhVien = null;
		Lop lop = null;
		Khoa khoa = null;
		
		//Lay sinh vien dang nhap
		sinhVien = MyUtils.getLoginedSinhVien(request.getSession());
		
		//kiem tra dang nhap
		if (sinhVien == null) {
			response.sendRedirect(request.getContextPath() + "/sinh-vien/logout");
			return;
		}
		
		// lay aciton
		String action = (String) request.getParameter("action");
		
		if(action == null) {
			
		/////////////////// load trang ////////
			
			//Lay lop va khoa
			lop = lopBO.getLopTheoMa(sinhVien.getMaLop());
			if (lop != null)
				khoa = khoaBO.getKhoaTheoMa(lop.getMaKhoa());
			
			//Set Attribute
			request.setAttribute("lop", lop);
			request.setAttribute("khoa", khoa);
			request.setAttribute("sinhVien", sinhVien);
			
			RequestDispatcher rd = request.getRequestDispatcher("/sinhvien/sinh-vien-cap-nhat-thong-tin.jsp");
			rd.forward(request, response);
		} else {
			
			//////////////// cap nhat /////////////
			String gioiTinh = (String) request.getParameter("gioiTinh");
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
						|| !Validate.validatePassword(xnMatKhauMoi) || !sinhVien.getMatKhau().equals(matKhauCu) ||!matKhauMoi.equals(xnMatKhauMoi)) {
					hasError = true;
				}
			} else {
				if (matKhauMoi.trim().length() > 0 || xnMatKhauMoi.trim().length() > 0)
					hasError = true;
			}
			
			//xay ra loi
			if (hasError) {
				response.sendRedirect(request.getContextPath() + "/sinh-vien/thong-tin-ca-nhan?tt=error");
			} else {
			
			//ko co loi
				sinhVien.setNgaySinh(DateUtils.convertToSDate(ngaySinh));
				sinhVien.setGioiTinh(gioiTinh);
				sinhVien.setEmail(email);
				sinhVien.setSdt(sdt);
				if(matKhauMoi.length() > 0)
					sinhVien.setMatKhau(matKhauMoi);
				
				sinhVienBO.updateSinhVien(sinhVien);
				MyUtils.storeLoginedSinhVien(request.getSession(), sinhVien);
				response.sendRedirect(request.getContextPath() + "/sinh-vien/thong-tin-ca-nhan?tt=ok");
			}
		}
	}

}
