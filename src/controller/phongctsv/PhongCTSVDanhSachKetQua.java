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

import model.bean.DanhGia;
import model.bean.GiangVien;
import model.bean.Khoa;
import model.bean.Lop;
import model.bean.SinhVien;
import model.bo.DanhGiaBO;
import model.bo.KhoaBO;
import model.bo.LopBO;
import model.bo.SinhVienBO;
import utils.DateUtils;
import utils.MyUtils;

/**
 * Servlet implementation class PhongCTSVDanhSachKetQua
 */
public class PhongCTSVDanhSachKetQua extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhongCTSVDanhSachKetQua() {
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
		PrintWriter out = response.getWriter();
		String idKhoa = request.getParameter("faculty");
		String idLop = request.getParameter("class");
		String errMsg=(String)request.getAttribute("errMsg");
		String aIdKhoa=(String)request.getAttribute("idKhoa");
		String aIdLop=(String)request.getAttribute("idLop");
		
		//kiem tra dang nhap
				GiangVien giangVien = MyUtils.getLoginedGiangVien(request.getSession());
				request.setAttribute("giangVien", giangVien);
				if (giangVien == null || !giangVien.getChucVu().equals("ctsv")) {
					response.sendRedirect(request.getContextPath() + "/giang-vien/logout");
					return;
				}
		
		if(aIdKhoa!=null&&aIdLop!=null){
			idKhoa=aIdKhoa;
			idLop=aIdLop;
		}
		if (idKhoa != null) {
			if (idLop != null && Integer.parseInt(idLop) > 0) {
				request.setAttribute("errMsg", errMsg);
				// lay danh sach khoa
				KhoaBO khoaBO = new KhoaBO();
				ArrayList<Khoa> listKhoa = khoaBO.getAllKhoa();
				request.setAttribute("listKhoa", listKhoa);
				// lay khoa theo id khoa
				Khoa khoa=khoaBO.getKhoaTheoMa(Integer.parseInt(idKhoa));
				request.setAttribute("khoa", khoa);
				//lay lop theo ma lop
				LopBO lopBO = new LopBO();
				Lop lop = lopBO.getLopTheoMa(Integer.parseInt(idLop));
				request.setAttribute("lop", lop);
				// lay danh sach lop theo Khoa
				ArrayList<Lop> listLopByKhoa=lopBO.getAllLopByKhoa(Integer.parseInt(idKhoa));
				request.setAttribute("listLopByKhoa", listLopByKhoa);
				// lay danh sach sinh vien da duoc xac nhan cua truong khoa theo lop
				SinhVienBO sinhVienBO = new SinhVienBO();
				ArrayList<SinhVien> listSinhVienXacNhanByLop = sinhVienBO
						.getAllSinhVienXacNhanByLop(Integer.parseInt(idLop));
				request.setAttribute("listSinhVienXacNhanByLop", listSinhVienXacNhanByLop);
				// lay danh sach danh gia cua sinh vien da duoc xac nhan
				DanhGiaBO danhGiaBO = new DanhGiaBO();
				ArrayList<DanhGia> listDanhGiaXacNhan = danhGiaBO.getAllDanhGiaXacNhan();
				request.setAttribute("listDanhGiaXacNhan", listDanhGiaXacNhan);
				RequestDispatcher rd = request.getRequestDispatcher("/phongCTSV/phong-CTSV-ket-qua-danh-gia.jsp");
				rd.forward(request, response);
				return;
			} else {
			LopBO lopBO = new LopBO();
			ArrayList<Lop> listLopByKhoa = lopBO.getAllLopByKhoa(Integer.parseInt(idKhoa));
			String rs = "<label for=\"class_list\">Lớp</label>"
					+ "<select id=\"class_list\" name=\"class\" onchange=\"submit()\"class=\"form-control\">"
					+ "<option value=\"0\" disabled=\"disabled\" selected=\"selected\">===Chọn lớp===</option>";
			if (listLopByKhoa != null) {
				for (Lop lop : listLopByKhoa) {
					rs += "<option value='" + lop.getMaLop() + "'>" + lop.getTen() + "</option>";

				}
				rs += "</select>" + "</div>";
				out.println( rs);
				return;
			}
		}

		} else {
			// lay danh sach khoa
			request.setAttribute("errMsg", errMsg);
			KhoaBO khoaBO = new KhoaBO();
			ArrayList<Khoa> listKhoa = khoaBO.getAllKhoa();
			request.setAttribute("listKhoa", listKhoa);
			RequestDispatcher rd = request.getRequestDispatcher("/phongCTSV/phong-CTSV-ket-qua-danh-gia.jsp");
			rd.forward(request, response);
		}
		
		// lay danh sach khoa
		// KhoaBO khoaBO=new KhoaBO();
		// ArrayList<Khoa> listKhoa=khoaBO.getAllKhoa();
		// request.setAttribute("listKhoa", listKhoa);
		// lay danh sach sinh vien da duoc xac nhan cua truong khoa
		// SinhVienBO sinhVienBO=new SinhVienBO();
		// ArrayList<SinhVien>
		// listSinhVienXacNhan=sinhVienBO.getAllSinhVienXacNhan();
		// request.setAttribute("listSinhVienXacNhan", listSinhVienXacNhan);
		// lay danh sach danh gia cua sinh vien da duoc xac nhan
		// DanhGiaBO danhGiaBO=new DanhGiaBO();
		// ArrayList<DanhGia>
		// listDanhGiaXacNhan=danhGiaBO.getAllDanhGiaXacNhan();
		// request.setAttribute("listDanhGiaXacNhan", listDanhGiaXacNhan);
		// RequestDispatcher
		// rd=request.getRequestDispatcher("/phongCTSV/phong-CTSV-ket-qua-danh-gia.jsp");
		// rd.forward(request, response);

	}

}
