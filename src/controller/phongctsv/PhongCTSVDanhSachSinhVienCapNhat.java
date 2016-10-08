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
 * Servlet implementation class PhongCTSVDanhSachSinhVienCapNhat
 */
@WebServlet("/PhongCTSVDanhSachSinhVienCapNhat")
public class PhongCTSVDanhSachSinhVienCapNhat extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PhongCTSVDanhSachSinhVienCapNhat() {
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
		String errMsg = "";
		SinhVienBO sinhVienBO = new SinhVienBO();
		// ajax cho lá»›p theo khoa
		if (aIdKhoa != null) {
			LopBO lopBO = new LopBO();
			ArrayList<Lop> listLopByKhoa = lopBO.getAllLopByKhoa(Integer.parseInt(aIdKhoa));
			String rs = "<label for=\"class_list\" class=\"control-label col-sm-2\">Lớp</label>"
					+ "<div class=\"col-sm-10\">" + "<select id=\"class_list\" name=\"class\" class=\"form-control\">"
					+ "<option value=\"0\" disabled=\"disabled\" selected=\"selected\">===Chọn lớp===</option>";
			if (listLopByKhoa != null) {
				for (Lop lop : listLopByKhoa) {
					rs += "<option value='" + lop.getMaLop() + "'>" + lop.getTen() + "</option>";

				}
				rs += "</select>" + "</div></div>";
				out.println(rs);
				return;
			}
		}
		// Xá»­ lÃ½ cáº­p nháº­t
		if (submit != null) {
			SinhVien sv = new SinhVien();
			String mssv = request.getParameter("mssv");
			String maSinhVien = request.getParameter("maSinhVien");
			String makhoa = request.getParameter("faculty");
			String maLop = request.getParameter("class");
			String ten = request.getParameter("ten");
			String gioiTinh = request.getParameter("gioiTinh");
			String ngaySinh = request.getParameter("ngaySinh");
			String tenDangNhap = request.getParameter("tenDangNhap");
			String matKhau = request.getParameter("matKhau");
			String soDienThoai = request.getParameter("soDienThoai");
			String email = request.getParameter("email");
			String chucVu = request.getParameter("chucVu");
			String active = request.getParameter("active");
			
			if (active != null) {
				sv.setActive(true);
			} else {
				sv.setActive(false);
			}
			if(mssv==""||!Validate.validateNumber(mssv)||makhoa==""||maLop==""||ten==""||gioiTinh==""||ngaySinh==""
					||tenDangNhap==""||matKhau==""||!Validate.validateNumber(soDienThoai)||email==""||chucVu==""||active==""){
				
				request.setAttribute("idKhoa", makhoa);
				errMsg = "0";
				request.setAttribute("errMsg", errMsg);
				RequestDispatcher rd = request.getRequestDispatcher("/phong-ctsv/danhsach-sinhvien");
				rd.forward(request, response);
				return;
				
			}
			sv.setMaLop(Integer.parseInt(maLop));
			sv.setNgaySinh(DateUtils.convertToSDate(ngaySinh));
			sv.setMssv(mssv);
			sv.setMaSinhVien(Integer.parseInt(maSinhVien));
			sv.setMaLop(Integer.parseInt(maLop));
			sv.setGioiTinh(gioiTinh);
			sv.setEmail(email);
			sv.setChucVu(chucVu);
			sv.setTenDangNhap(tenDangNhap);
			sv.setMatKhau(matKhau);
			sv.setTen(ten);
			sv.setSdt(soDienThoai);

			SinhVien sv1 = new SinhVien();
			if ("loptruong".equals(sv.getChucVu())) {
				sv1 = sinhVienBO.getLopTruongByLop(Integer.parseInt(maLop));

			}
			if (sinhVienBO.updateSinhVien(sv) == 1) {
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
			String idLop = request.getParameter("idLop");
			LopBO lopBO = new LopBO();
			// lay lop theo idLop
			Lop lop = lopBO.getLopTheoMa(Integer.parseInt(idLop));
			// lay khoa theo idLop
			Khoa khoa = khoaBO.getKhoaTheoMa(lop.getMaKhoa());
			request.setAttribute("khoa", khoa);
			request.setAttribute("lop", lop);
			// lay danh sach lop theo Khoa
			ArrayList<Lop> listLopByKhoa = lopBO.getAllLopByKhoa(lop.getMaKhoa());
			request.setAttribute("listLopByKhoa", listLopByKhoa);
			// lay Sinh Vien can sua
			String idSV = request.getParameter("idSV");
			SinhVien sv = sinhVienBO.getSinhVienTheoMa(Integer.parseInt(idSV));
			request.setAttribute("sv", sv);
			// out.println(gv.isActive());
			RequestDispatcher rd = request
					.getRequestDispatcher("/phongCTSV/phong-CTSV-danh-sach-sinh-vien-cap-nhat.jsp");
			rd.forward(request, response);
			return;
		}

	}

}
