package controller.sinhvien;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.DateUtils;
import utils.MyUtils;
import model.bean.ChiTietDanhGia;
import model.bean.DanhGia;
import model.bean.DotDanhGia;
import model.bean.MucDanhGia;
import model.bean.NoiDungDanhGia;
import model.bean.SinhVien;
import model.bo.ChiTietDanhGiaBO;
import model.bo.DanhGiaBO;
import model.bo.DotDanhGiaBO;
import model.bo.MucDanhGiaBO;
import model.bo.NoiDungDanhGiaBO;
/**
 * Servlet implementation class SinhVienDanhGiaServlet Author Minh Kiet
 */
public class SinhVienDanhGiaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SinhVienDanhGiaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		
		DanhGiaBO danhGiaBO = new DanhGiaBO();
		
		SinhVien sinhVien = MyUtils.getLoginedSinhVien(request.getSession());
		request.setAttribute("sinhVien", sinhVien);
		
		// kiem tra dang nhap
		if(sinhVien == null) {
			response.sendRedirect(request.getContextPath() + "/sinh-vien/logout");
			return;
		}

		// Lay action
		String action = (String) request.getParameter("action");
		
		// Lay dot danh gia hien tai cua sinh vien
		Date thoiGianHienTai = new Date();
		DotDanhGiaBO dotDanhGiaBO = new DotDanhGiaBO();
		DotDanhGia dotDanhGia = dotDanhGiaBO.getDotDanhGiaHienTaiSV(DateUtils.convertToTimestamp(thoiGianHienTai));
		request.setAttribute("dotDanhGia", dotDanhGia);
		
		// kiem tra xem sinh vien da danh gia chua?
		
		if (dotDanhGia != null) {
			DanhGia danhGia = danhGiaBO.getDanhGiaTheoMaSinhVien(sinhVien.getMaSinhVien(), dotDanhGia.getMaDotDanhGia());
			request.setAttribute("danhGia", danhGia);
		}
		// Neu action == null ko lam gi ca, cho load trang
		if (action == null) {

			// Lay muc danh gia va setAtribute
			MucDanhGiaBO mucDanhGiaBO = new MucDanhGiaBO();
			ArrayList<MucDanhGia> listMucDanhGia = mucDanhGiaBO
					.getAllMucDanhGia();
			request.setAttribute("listMucDanhGia", listMucDanhGia);

			// lay cac list noi dung danh gia cua tung muc va setAtribute
			NoiDungDanhGiaBO noiDungDanhGiaBO = new NoiDungDanhGiaBO();
			ArrayList<NoiDungDanhGia> listNoiDungDanhGia = new ArrayList<NoiDungDanhGia>();
			for (int i = 0; i < listMucDanhGia.size(); i++) {
				listNoiDungDanhGia = noiDungDanhGiaBO
						.getAllNoiDungDanhGiaTheoMuc(listMucDanhGia.get(i)
								.getMaMucDanhGia());
				request.setAttribute("listNoiDungDanhGia" + i,
						listNoiDungDanhGia);
			}

			RequestDispatcher rd = request
					.getRequestDispatcher("/sinhvien/sinh-vien-danh-gia.jsp");
			rd.forward(request, response);
		} else {
			
			// action != null
			if (action.equals("submit")){
				
				int tongDiem = 0;
				
				// Tao danh sach chi tiet danh gia de them vao database
				ArrayList<ChiTietDanhGia> listChiTietDanhGia = new ArrayList<ChiTietDanhGia>();
				ChiTietDanhGia ctdg = null;
				
				// Lay diem tu parameter
				MucDanhGiaBO mucDanhGiaBO = new MucDanhGiaBO();
				ArrayList<MucDanhGia> listMucDanhGia = mucDanhGiaBO
						.getAllMucDanhGia();
				NoiDungDanhGiaBO noiDungDanhGiaBO = new NoiDungDanhGiaBO();
				ArrayList<NoiDungDanhGia> listNoiDungDanhGia = new ArrayList<NoiDungDanhGia>();
				
				for (int i = 0; i < listMucDanhGia.size(); i++) {
					listNoiDungDanhGia = noiDungDanhGiaBO.getAllNoiDungDanhGiaTheoMuc(listMucDanhGia.get(i).getMaMucDanhGia());
						for (int j = 0; j < listNoiDungDanhGia.size(); j++) {
							
							//Lay diem sinh vien danh gia
							int diem = Integer.parseInt(request.getParameter("diemTuDanhGia" + i + "." + j));
							tongDiem += diem;
							
							//Tao chi tiet danh gia
							ctdg = new ChiTietDanhGia(listNoiDungDanhGia.get(j).getMaNoiDungDanhGia(), 0, sinhVien.getMaSinhVien(), diem, 0 );
							listChiTietDanhGia.add(ctdg);
						}
					
				}
				
				// Tao doi tuong danhgia
				if (tongDiem > 100)
					tongDiem = 100;
				DanhGia danhGia = new DanhGia(sinhVien.getMaSinhVien(), dotDanhGia.getMaDotDanhGia(), DateUtils.convertToTimestamp(thoiGianHienTai), tongDiem, 0, "tapthelopchuaduyet", "", null, null, null, null);
				danhGiaBO.insertDanhGia(danhGia);
				int maDanhGia = danhGiaBO.lastInserted();
				
				System.out.println("SV danh gia, Ma danh gia: " + maDanhGia);
				
				// Set ma danh gia
				for (ChiTietDanhGia chiTietDanhGia : listChiTietDanhGia) {
					chiTietDanhGia.setMaDanhGia(maDanhGia);
				}
				
				// Day list chi tiet danh gia vao database
				ChiTietDanhGiaBO chiTietDanhGiaBO = new ChiTietDanhGiaBO();
				System.out.println(chiTietDanhGiaBO.insertListChiTietDanhGia(listChiTietDanhGia));
				
				response.sendRedirect(request.getContextPath() + "/sinh-vien/ket-qua-danh-gia?id=" + dotDanhGia.getMaDotDanhGia());
			}
			
		}
	}

}
