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
import model.bean.DotDanhGia;
import model.bean.GiangVien;
import model.bean.Khoa;
import model.bean.Lop;
import model.bean.SinhVien;
import model.bo.DanhGiaBO;
import model.bo.DotDanhGiaBO;
import model.bo.KhoaBO;
import model.bo.LopBO;
import model.bo.SinhVienBO;
import utils.MyUtils;

/**
 * Servlet implementation class PhongCTSVDuLieu
 */
public class PhongCTSVDuLieu extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PhongCTSVDuLieu() {
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
		
		GiangVien giangVien = MyUtils.getLoginedGiangVien(request.getSession());
		request.setAttribute("giangVien", giangVien);
		if (giangVien == null || !giangVien.getChucVu().equals("ctsv")) {
			response.sendRedirect(request.getContextPath() + "/giang-vien/logout");
			return;
		}
		
		
		PrintWriter out = response.getWriter();
		String idDotDanhGia = request.getParameter("dotDanhGia");
		String idKhoa = request.getParameter("faculty");
		String idLop = request.getParameter("aIdLop");
		String errMsg = (String) request.getAttribute("errMsg");
		if (idKhoa != null) {
			if (idLop != null && Integer.parseInt(idLop) > 0) {
				if (idDotDanhGia != null && Integer.parseInt(idDotDanhGia) > 0) {
					request.setAttribute("errMsg", errMsg);
					//lay  danh sach dot bao cao	
					DotDanhGiaBO dotDanhGiaBO = new DotDanhGiaBO();
					DotDanhGia dotDanhGia=dotDanhGiaBO.getDotDanhGiaTheoMa(Integer.parseInt(idDotDanhGia));
					request.setAttribute("dotDanhGia", dotDanhGia);
					ArrayList<DotDanhGia> listDotDanhGia = dotDanhGiaBO.getAllDotDanhGia();
					request.setAttribute("listDotDanhGia", listDotDanhGia);
					// lay danh sach khoa
					KhoaBO khoaBO = new KhoaBO();
					ArrayList<Khoa> listKhoa = khoaBO.getAllKhoa();
					request.setAttribute("listKhoa", listKhoa);
					// lay khoa theo id khoa
					Khoa khoa = khoaBO.getKhoaTheoMa(Integer.parseInt(idKhoa));
					request.setAttribute("khoa", khoa);
					// lay lop theo ma lop
					LopBO lopBO = new LopBO();
					Lop lop = lopBO.getLopTheoMa(Integer.parseInt(idLop));
					request.setAttribute("lop", lop);
					// lay danh sach lop theo Khoa
					ArrayList<Lop> listLopByKhoa = lopBO.getAllLopByKhoa(Integer.parseInt(idKhoa));
					request.setAttribute("listLopByKhoa", listLopByKhoa);
					
					// lay danh sach sinh vien da duoc xac nhan cua truong khoa
					// theo lop
					SinhVienBO sinhVienBO = new SinhVienBO();
					ArrayList<SinhVien> listSinhVienByLop = sinhVienBO.getAllSinhVienTheoLop(Integer.parseInt(idLop));
					request.setAttribute("listSinhVienByLop", listSinhVienByLop);
					// lay danh sach danh gia cua sinh vien da duoc xac nhan cua
					// phong cong tac sinh vien
					DanhGiaBO danhGiaBO = new DanhGiaBO();
					ArrayList<DanhGia> listDanhGiaXacNhanByDotDanhGiaVsLop = danhGiaBO
							.getAllDanhGiaXacNhanByDotDanhGiaVsLop(Integer.parseInt(idDotDanhGia),
									Integer.parseInt(idLop));
					
					ArrayList<DanhGia> listDanhGia =  listDanhGiaXacNhanByDotDanhGiaVsLop;
					String [] listXepLoai = new String [listDanhGia.size()];
					for (int i = 0; i < listDanhGia.size(); i++) {
						
						if (listDanhGia.get(i).getDiemTapTheLop() < 35)
							listXepLoai[i] = "Kém";
						else if (listDanhGia.get(i).getDiemTapTheLop() < 50)
							listXepLoai[i] = "Yếu";
						else if (listDanhGia.get(i).getDiemTapTheLop() < 65)
							listXepLoai[i] = "Trung bình";
						else if (listDanhGia.get(i).getDiemTapTheLop() < 80)
							listXepLoai[i] = "Khá";
						else if (listDanhGia.get(i).getDiemTapTheLop() < 90)
							listXepLoai[i] = "Tốt";
						else
							listXepLoai[i] = "Xuất sắc";
					}
					
					request.setAttribute("listXepLoai", listXepLoai);
					request.setAttribute("listDanhGiaXacNhanByDotDanhGiaVsLop", listDanhGiaXacNhanByDotDanhGiaVsLop);
					RequestDispatcher rd = request
							.getRequestDispatcher("/phongCTSV/phong-CTSV-du-lieu-ket-qua-danh-gia.jsp");
					rd.forward(request, response);
					return;
				} else {
					DotDanhGiaBO dotDanhGiaBO = new DotDanhGiaBO();
					ArrayList<DotDanhGia> listDotDanhGia = dotDanhGiaBO.getAllDotDanhGia();
					request.setAttribute("listDotDanhGia", listDotDanhGia);
					// lay danh sach khoa
					KhoaBO khoaBO = new KhoaBO();
					ArrayList<Khoa> listKhoa = khoaBO.getAllKhoa();
					request.setAttribute("listKhoa", listKhoa);
					// lay khoa theo id khoa
					Khoa khoa = khoaBO.getKhoaTheoMa(Integer.parseInt(idKhoa));
					request.setAttribute("khoa", khoa);
					// lay lop theo ma lop
					LopBO lopBO = new LopBO();
					Lop lop = lopBO.getLopTheoMa(Integer.parseInt(idLop));
					request.setAttribute("lop", lop);
					// lay danh sach lop theo Khoa
					ArrayList<Lop> listLopByKhoa = lopBO.getAllLopByKhoa(Integer.parseInt(idKhoa));
					request.setAttribute("listLopByKhoa", listLopByKhoa);
					RequestDispatcher rd = request
						.getRequestDispatcher("/phongCTSV/phong-CTSV-du-lieu-ket-qua-danh-gia.jsp");
					rd.forward(request, response);
					return;
				}
			} else {
				// lay danh sach khoa
				KhoaBO khoaBO = new KhoaBO();
				ArrayList<Khoa> listKhoa = khoaBO.getAllKhoa();
				request.setAttribute("listKhoa", listKhoa);
				// lay khoa theo id khoa
				Khoa khoa = khoaBO.getKhoaTheoMa(Integer.parseInt(idKhoa));
				request.setAttribute("khoa", khoa);
				// ajax lop theo khoa
				LopBO lopBO = new LopBO();
				ArrayList<Lop> listLopByKhoa = lopBO.getAllLopByKhoa(Integer.parseInt(idKhoa));
				request.setAttribute("listLopByKhoa", listLopByKhoa);
				RequestDispatcher rd = request
						.getRequestDispatcher("/phongCTSV/phong-CTSV-du-lieu-ket-qua-danh-gia.jsp");
				rd.forward(request, response);
				return;
			}

		} else {
			// lay danh sach khoa
			request.setAttribute("errMsg", errMsg);
			KhoaBO khoaBO = new KhoaBO();
			ArrayList<Khoa> listKhoa = khoaBO.getAllKhoa();
			request.setAttribute("listKhoa", listKhoa);
			RequestDispatcher rd = request.getRequestDispatcher("/phongCTSV/phong-CTSV-du-lieu-ket-qua-danh-gia.jsp");
			rd.forward(request, response);
		}

	}

}
