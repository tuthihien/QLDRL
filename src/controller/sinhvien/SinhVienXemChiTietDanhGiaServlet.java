package controller.sinhvien;

import java.io.IOException;
import java.util.ArrayList;








import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.MyUtils;
import utils.Validate;
import model.bean.ChiTietDanhGia;
import model.bean.DanhGia;
import model.bean.DotDanhGia;
import model.bean.Lop;
import model.bean.MucDanhGia;
import model.bean.NoiDungDanhGia;
import model.bean.SinhVien;
import model.bo.ChiTietDanhGiaBO;
import model.bo.DanhGiaBO;
import model.bo.DotDanhGiaBO;
import model.bo.LopBO;
import model.bo.MucDanhGiaBO;
import model.bo.NoiDungDanhGiaBO;
import model.bo.SinhVienBO;

/**
 * Author Minh Kiet
 * Servlet implementation class SinhVienCapNhatDanhGia
 */

public class SinhVienXemChiTietDanhGiaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SinhVienXemChiTietDanhGiaServlet() {
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
		DanhGiaBO danhGiaBO = new DanhGiaBO();
		NoiDungDanhGiaBO noiDungDanhGiaBO = new NoiDungDanhGiaBO();
		MucDanhGiaBO mucDanhGiaBO = new MucDanhGiaBO();
		ChiTietDanhGiaBO chiTietDanhGiaBO = new ChiTietDanhGiaBO();
		LopBO lopBO = new LopBO();
		SinhVienBO sinhVienBO = new SinhVienBO();
		DotDanhGiaBO dotDanhGiaBO = new DotDanhGiaBO();
		
		//Bean
		ArrayList<NoiDungDanhGia> listNoiDungDanhGia = null;
		ArrayList<ChiTietDanhGia> listChiTietDanhGia = null;
		ArrayList<MucDanhGia> listMucDanhGia = null;
		DanhGia danhGia = null;
		DotDanhGia dotDanhGia = null;
		SinhVien sinhVienDanhGia = null;
		Lop lop = null;
		
		// lay sinh vien dang dang nhap
		SinhVien sinhVien = MyUtils.getLoginedSinhVien(request.getSession());
		request.setAttribute("sinhVien", sinhVien);
	
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
			
		
		//lay thong tin sinh vien, lop, dot danh gia cua danh gia
		if (danhGia != null) {
			sinhVienDanhGia = sinhVienBO.getSinhVienTheoMa(danhGia.getMaSinhVien());
			dotDanhGia = dotDanhGiaBO.getDotDanhGiaTheoMa(danhGia.getMaDotDanhGia());
			if (sinhVienDanhGia != null)
				lop = lopBO.getLopTheoMa(sinhVienDanhGia.getMaLop());
			
			request.setAttribute("sinhVienDG", sinhVienDanhGia);
			request.setAttribute("dotDanhGia", dotDanhGia);
			request.setAttribute("lop", lop);
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
		
		RequestDispatcher rd = request.getRequestDispatcher("/sinhvien/xem-chi-tiet-danh-gia.jsp");
		rd.forward(request, response);
	}
}
