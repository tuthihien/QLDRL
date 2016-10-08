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
import utils.Validate;
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
 * Author Minh Kiet
 * Servlet implementation class SinhVienCapNhatDanhGia
 */

public class SinhVienCapNhatDanhGiaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SinhVienCapNhatDanhGiaServlet() {
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
		
		// BO
		DotDanhGiaBO dotDanhGiaBO = new DotDanhGiaBO();
		DanhGiaBO danhGiaBO = new DanhGiaBO();
		NoiDungDanhGiaBO noiDungDanhGiaBO = new NoiDungDanhGiaBO();
		MucDanhGiaBO mucDanhGiaBO = new MucDanhGiaBO();
		ChiTietDanhGiaBO chiTietDanhGiaBO = new ChiTietDanhGiaBO();
		
		//Bean
		ArrayList<NoiDungDanhGia> listNoiDungDanhGia = null;
		ArrayList<ChiTietDanhGia> listChiTietDanhGia = null;
		ArrayList<MucDanhGia> listMucDanhGia = null;
		DanhGia danhGia = null;
		DotDanhGia dotDanhGia = null;
		
		// lay sinh vien dang dang nhap
		SinhVien sinhVien = MyUtils.getLoginedSinhVien(request.getSession());
		request.setAttribute("sinhVien", sinhVien);
		
		// lay dot danh gia hien tai
		Date thoiGianHienTai = new Date();
		dotDanhGia = dotDanhGiaBO.getDotDanhGiaHienTaiSV(DateUtils.convertToTimestamp(thoiGianHienTai));
		
		// kiem tra dang nhap
		if(sinhVien == null) {
			response.sendRedirect(request.getContextPath() + "/sinh-vien/logout");
			return;
		}
		
		// lay danh gia
		String id = request.getParameter("id");
		if(!Validate.validateNumber(id)) {
			return;	
		}
		int maDanhGia = Integer.parseInt(id);
		danhGia = danhGiaBO.getDanhGiaTheoMa(maDanhGia);
		request.setAttribute("danhGia", danhGia);
	
		
		//////////////////////////
		// kiem tra co submit chua
		String action = (String) request.getParameter("action");
		if (action == null) { 
		
			// kiem tra cho phep cap nhat
			if (dotDanhGia != null && danhGia != null) {
				if (danhGia.getNgayDanhGia().after(dotDanhGia.getNgayBatDauSV()) 
						&& danhGia.getNgayDanhGia().before(dotDanhGia.getNgayKetThucSV())) {
					request.setAttribute("dotDanhGia", dotDanhGia);
				}
			}
			
			// lay cac muc danh gia can cap nhat
			
			listMucDanhGia = mucDanhGiaBO.getAllMucDanhGia();
			request.setAttribute("listMucDanhGia", listMucDanhGia);
			
			// lay cac noi dung danh gia, chi tiet danh gia cua tung muc can cap nhat
				
			for (int i = 0 ; i < listMucDanhGia.size() ; i++) {
				listNoiDungDanhGia = noiDungDanhGiaBO.getAllNoiDungDanhGiaTheoMucCapNhat(listMucDanhGia.get(i).getMaMucDanhGia(),
						danhGia.getMaDanhGia());
				listChiTietDanhGia = chiTietDanhGiaBO.getAllChiTietDanGiaTheoMucCapNhat(danhGia.getMaDanhGia(), listMucDanhGia.get(i).getMaMucDanhGia());

				
				request.setAttribute("listNoiDungDanhGia" + i, listNoiDungDanhGia);
				request.setAttribute("listChiTietDanhGia" + i, listChiTietDanhGia);
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/sinhvien/sinh-vien-cap-nhat-danh-gia.jsp");
			rd.forward(request, response);
		} else {
			
			/////////////////////////
			// neu action == submit 
			if(action.equals("submit")) {
				
				//lay noi dung muc danh gia va noi dung danh gia can cap nhat 
				//(noi dung danh gia ma sinh vien da danh gia)
				listMucDanhGia = mucDanhGiaBO.getAllMucDanhGia();
				for (int i = 0 ; i < listMucDanhGia.size() ; i++) {
					listNoiDungDanhGia = noiDungDanhGiaBO.getAllNoiDungDanhGiaTheoMucCapNhat(listMucDanhGia.get(i).getMaMucDanhGia(),
							danhGia.getMaDanhGia());
				}
				
				// xoa cac chi tiet danh gia cu
				chiTietDanhGiaBO.deleteChiTietDanhGiaTheoMaDanhGia(maDanhGia);
				
				//tao cac chi tiet danh gia moi
				int tongDiem = 0;
				listChiTietDanhGia = new ArrayList<ChiTietDanhGia>();
				ChiTietDanhGia ctdg = null;
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
				
				// Set ma danh gia
				for (ChiTietDanhGia chiTietDanhGia : listChiTietDanhGia) {
					chiTietDanhGia.setMaDanhGia(maDanhGia);
				}
				
				//cap nhat ngay danh gia va tong diem
				danhGia.setNgayDanhGia(DateUtils.convertToTimestamp(thoiGianHienTai));
				danhGia.setTongDiem(tongDiem);
				danhGiaBO.updateDanhGia(danhGia);
				
				//day cac chi tiet danh gia moi vao database
				chiTietDanhGiaBO.insertListChiTietDanhGia(listChiTietDanhGia);
		
				response.sendRedirect(request.getContextPath() + "/sinh-vien/ket-qua-danh-gia?id=" + danhGia.getMaDotDanhGia());
				
			}
		}
	}

}
