package controller.phongctsv;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.MyUtils;
import utils.Validate;
import model.bean.GiangVien;
import model.bean.Khoa;
import model.bean.Lop;
import model.bo.GiangVienBO;
import model.bo.KhoaBO;
import model.bo.LopBO;

/**
 * Servlet implementation class SuaLopServlet
 */
public class SuaLopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuaLopServlet() {
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
		String lopPr = request.getParameter("id");
		String tenLopPr = request.getParameter("tenLop");
		String gvcnPr = request.getParameter("gvcn");
		String khoaPr = request.getParameter("khoa");
		
		//Kiem tra dang nhap
		GiangVien giangVien = MyUtils.getLoginedGiangVien(request.getSession());
		if (giangVien == null || !giangVien.getChucVu().equals("ctsv")) {
			response.sendRedirect(request.getContextPath() + "/giang-vien/logout");
			return;
		}
		request.setAttribute("giangVien", giangVien);
		
		//BO
		GiangVienBO giangVienBO = new GiangVienBO();
		KhoaBO khoaBO = new KhoaBO();
		LopBO lopBO = new LopBO();
		
		//Object
		Lop lop = null;
		ArrayList<Khoa> listKhoa = null;
		ArrayList<GiangVien> listGVCN = null;
		Khoa khoa = null;
		GiangVien gvcn = null;
		
		//Kiem tra action
		if (action == null) {
			
			//Lay lop can sua
			int maLop = 0;
			if (Validate.validateNumber(lopPr)) {
				maLop = Integer.parseInt(lopPr);
			}
			lop = lopBO.getLopTheoMa(maLop);
			
			//lay gvcn, khoa
			if (lop != null) {
				khoa = khoaBO.getKhoaTheoMa(lop.getMaKhoa());
				gvcn = giangVienBO.getGiangVienTheoMa(lop.getMaGiangVien());
			}
			
			//lay danh sach cac khoa, danh sach gv trong khoa
			listKhoa = khoaBO.getAllKhoa();
			if (lop != null)
				listGVCN = giangVienBO.getGiangVienByKhoa(lop.getMaKhoa());
			
			//setAtribute
			request.setAttribute("listKhoa", listKhoa);
			request.setAttribute("listGVCN", listGVCN);
			request.setAttribute("lop", lop);
			request.setAttribute("khoa", khoa);
			request.setAttribute("gvcn", gvcn);
			
			//chuyen huong
			RequestDispatcher rd = request.getRequestDispatcher("/phongCTSV/sua-lop.jsp");
			rd.forward(request, response);
		} else {
			
			//Lay lop can sua
			int maLop = 0, maGVCN = 0, maKhoa = 0;
			if (Validate.validateNumber(lopPr) && Validate.validateNumber(gvcnPr) 
					&& Validate.validateNumber(khoaPr) && tenLopPr != null 
						&& tenLopPr.trim().length() > 0) {
				
				maLop = Integer.parseInt(lopPr);
				maGVCN = Integer.parseInt(gvcnPr);
				maKhoa = Integer.parseInt(khoaPr);
				
				lop = lopBO.getLopTheoMa(maLop);
				if (lop != null) {
					lop.setMaGiangVien(maGVCN);
					lop.setMaKhoa(maKhoa);
					lop.setTen(tenLopPr);
					
					lopBO.updateLop(lop);
				}
							
			}
			response.sendRedirect(request.getContextPath() + "/phong-ctsv/ds-lop");
			
		}
	}

}
