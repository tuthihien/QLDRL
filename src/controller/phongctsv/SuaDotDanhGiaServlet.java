package controller.phongctsv;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.DotDanhGia;
import model.bean.GiangVien;
import model.bo.DotDanhGiaBO;
import utils.DateUtils;
import utils.MyUtils;
import utils.Validate;

/**
 * Servlet implementation class SuaDotDanhGiaServlet
 */
public class SuaDotDanhGiaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuaDotDanhGiaServlet() {
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
		
		//GetParameter
		String action = request.getParameter("action");
		String maDotDanhGiaPr = request.getParameter("id");
		String ten = request.getParameter("ten");
		String ngayBDSV = request.getParameter("ngaybdsv");
		String ngayKTSV = request.getParameter("ngayktsv");
		String ngayBDTTL = request.getParameter("ngaybdttl");
		String ngayKTTTL = request.getParameter("ngayktttl");
		String ngayBDGVCN = request.getParameter("ngaybdgv");
		String ngayKTGVCN = request.getParameter("ngayktgv");
		String ngayBDTK = request.getParameter("ngaybdtk");
		String ngayKTTK = request.getParameter("ngaykttk");
		
		//Kiem tra dang nhap
		GiangVien giangVien = MyUtils.getLoginedGiangVien(request.getSession());
		if (giangVien == null || !giangVien.getChucVu().equals("ctsv")) {
			response.sendRedirect(request.getContextPath() + "/giang-vien/logout");
			return;
		}
		request.setAttribute("giangVien", giangVien);
		
		//BO
		DotDanhGiaBO dotDanhGiaBO = new DotDanhGiaBO();
		
		//Object
		DotDanhGia dotDanhGia = null;
		
		int maDotDanhGia = 0;
		if (Validate.validateNumber(maDotDanhGiaPr)) {
			maDotDanhGia = Integer.parseInt(maDotDanhGiaPr);
		}
		dotDanhGia = dotDanhGiaBO.getDotDanhGiaTheoMa(maDotDanhGia);
		
		//kiem tra action
		if (action == null) {
			request.setAttribute("dotDanhGia", dotDanhGia);
			
			RequestDispatcher rd = request.getRequestDispatcher("/phongCTSV/sua-dot-danh-gia.jsp");
			rd.forward(request, response);
		} else {
			if (ngayBDSV != null && ngayKTSV != null && ngayBDTTL != null && ngayKTTTL != null
					&& ngayBDGVCN != null && ngayKTGVCN != null && ngayBDTK != null && ngayKTTK != null) {
				
				if (dotDanhGia != null) {
					
					dotDanhGia.setTen(ten);
					dotDanhGia.setNgayBatDauSV(DateUtils.convertToTimestamp(ngayBDSV));
					dotDanhGia.setNgayKetThucSV(DateUtils.convertToTimestamp(ngayKTSV));
					dotDanhGia.setNgayBatDauLT(DateUtils.convertToTimestamp(ngayBDTTL));
					dotDanhGia.setNgayKetThucLT(DateUtils.convertToTimestamp(ngayKTTTL));
					dotDanhGia.setNgayBatDauGV(DateUtils.convertToTimestamp(ngayBDGVCN));
					dotDanhGia.setNgayKetThucGV(DateUtils.convertToTimestamp(ngayKTGVCN));
					dotDanhGia.setNgayBatDauTK(DateUtils.convertToTimestamp(ngayBDTK));
					dotDanhGia.setNgayKetThucTK(DateUtils.convertToTimestamp(ngayKTTK));
					
					dotDanhGiaBO.updateDotDanhGia(dotDanhGia);	
				}
				
			}
			
			response.sendRedirect(request.getContextPath() + "/phong-ctsv/ds-dot-danh-gia");
		}
	}

}
