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

import model.bean.GiangVien;
import model.bean.Khoa;
import model.bean.Lop;
import model.bean.SinhVien;
import model.bo.KhoaBO;
import model.bo.LopBO;
import model.bo.SinhVienBO;
import utils.DateUtils;
import utils.MyUtils;
import utils.Validate;

/**
 * Servlet implementation class PhongCTSVDanhSachSinhVienthem
 */
public class PhongCTSVDanhSachSinhVienthem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PhongCTSVDanhSachSinhVienthem() {
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
		String submit = request.getParameter("submit");
		String aIdKhoa = request.getParameter("khoa");
		String aMssv = request.getParameter("aMssv");
		String errMsg = "";
		SinhVienBO sinhVienBO = new SinhVienBO();
		SinhVien sv = new SinhVien();
		ArrayList<SinhVien> listAllSinhVien = sinhVienBO.getAllSinhVien();
		// ajax cho lá»›p theo khoa

		if (aIdKhoa != null) {
			LopBO lopBO = new LopBO();
			ArrayList<Lop> listLopByKhoa = lopBO.getAllLopByKhoa(Integer.parseInt(aIdKhoa));
			String rs = "<label for=\"class_list\" class=\"control-label col-sm-2\">Lớp</label>"
					+ "<div class=\"col-sm-10\">" + "<select id=\"class_list\" name=\"class\" class=\"form-control\">"
					+ "<option value=\"0\" disabled=\"disabled\">===Chọn lớp===</option>";
			if (listLopByKhoa != null) {
				for (Lop lop : listLopByKhoa) {
					rs += "<option value='" + lop.getMaLop() + "'>" + lop.getTen() + "</option>";

				}
				rs += "</select>" + "</div></div>";
				out.println(rs);
				return;
			}
		}
		if (aMssv != null) {
			for (SinhVien sv1 : listAllSinhVien) {
				if (aMssv.equals(sv1.getMssv())) {
					String errMsg1 = "MÃ£ sá»‘ sinh viÃªn bá»‹ trÃ¹ng";
					out.print(errMsg1);
					return;
				}
			}
		}
		// Xá»­ lÃ½ cáº­p nháº­t
		if (submit != null) {

			String maSoSinhVien = request.getParameter("mssv");
			String makhoa = request.getParameter("faculty");
			String maLop = request.getParameter("class");
			String ten = request.getParameter("ten");
			String gioiTinh = request.getParameter("gioiTinh");
			String ngaySinh = request.getParameter("ngaySinh");
			String soDienThoai = request.getParameter("soDienThoai");
			String email = request.getParameter("email");
			String chucVu = request.getParameter("chucVu");
			String active = request.getParameter("active");
			if (active != null) {
				sv.setActive(true);
			} else {
				sv.setActive(false);
			}
			if(maSoSinhVien==""|| !Validate.validateNumber(maSoSinhVien)||makhoa==null||maLop==""||ten==""||gioiTinh==""||ngaySinh==""
					||soDienThoai==""||!Validate.validateNumber(soDienThoai)||email==""||chucVu==""||active==""){
				
				errMsg = "0";
				request.setAttribute("errMsg", errMsg);
				RequestDispatcher rd = request.getRequestDispatcher("/phong-ctsv/danhsach-sinhvien");
				rd.forward(request, response);
				return;
				
			}
			sv.setMaLop(Integer.parseInt(maLop));
			sv.setNgaySinh(DateUtils.convertToSDate(ngaySinh));
			sv.setMssv(maSoSinhVien);
			sv.setMaLop(Integer.parseInt(maLop));
			sv.setGioiTinh(gioiTinh);
			sv.setEmail(email);
			sv.setChucVu(chucVu);
			sv.setTenDangNhap(email);
			sv.setMatKhau("123456");
			sv.setTen(ten);
			sv.setSdt(soDienThoai);
			if (listAllSinhVien != null) {
				for (SinhVien sv1 : listAllSinhVien) {
					if (sv.getMssv().equals(sv1.getMssv())) {
						String errMsg1 = "MÃ£ sá»‘ sinh viÃªn bá»‹ trÃ¹ng";
						request.setAttribute("errMsg1", errMsg1);
						request.setAttribute("sv", sv);
						KhoaBO khoaBO = new KhoaBO();
						ArrayList<Khoa> listKhoa = khoaBO.getAllKhoa();
						request.setAttribute("listKhoa", listKhoa);
						LopBO lopBO = new LopBO();
						// lay lop theo idLop
						Lop lop = lopBO.getLopTheoMa(sv.getMaLop());
						// lay khoa theo idLop
						Khoa khoa = khoaBO.getKhoaTheoMa(lop.getMaKhoa());
						request.setAttribute("khoa", khoa);
						request.setAttribute("lop", lop);
						// lay danh sach lop theo Khoa
						ArrayList<Lop> listLopByKhoa = lopBO.getAllLopByKhoa(lop.getMaKhoa());
						request.setAttribute("listLopByKhoa", listLopByKhoa);
						RequestDispatcher rd = request
								.getRequestDispatcher("/phongCTSV/phong-CTSV-danh-sach-sinh-vien-them.jsp");
						rd.forward(request, response);
						return;
					}
				}
			}

			SinhVien sv1 = new SinhVien();
			if ("loptruong".equals(sv.getChucVu())) {
				sv1 = sinhVienBO.getLopTruongByLop(Integer.parseInt(maLop));

			}
			if (sinhVienBO.insertSinhVien(sv) == 1) {
				sv1.setActive(false);
				sinhVienBO.updateSinhVien(sv1);
				request.setAttribute("aIdKhoa", makhoa);
				request.setAttribute("aIdLop", maLop);
				errMsg = "1";
				request.setAttribute("errMsg", errMsg);
				RequestDispatcher rd = request.getRequestDispatcher("/phong-ctsv/danhsach-sinhvien");
				rd.forward(request, response);
			} else {
				request.setAttribute("idKhoa", makhoa);
				errMsg = "0";
				request.setAttribute("errMsg", errMsg);
				RequestDispatcher rd = request.getRequestDispatcher("/phong-ctsv/danhsach-sinhvien");
				rd.forward(request, response);

			}

		} else {
			// lay tac ca khoa
			KhoaBO khoaBO = new KhoaBO();
			ArrayList<Khoa> listKhoa = khoaBO.getAllKhoa();
			request.setAttribute("listKhoa", listKhoa);
			RequestDispatcher rd = request.getRequestDispatcher("/phongCTSV/phong-CTSV-danh-sach-sinh-vien-them.jsp");
			rd.forward(request, response);
			return;
		}

	}

}
