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

import utils.MyUtils;
import model.bean.GiangVien;
import model.bean.Khoa;
import model.bo.GiangVienBO;
import model.bo.KhoaBO;

/**
 * Servlet implementation class PhongCTSVDanhSachGiangVien
 */
public class PhongCTSVDanhSachGiangVien extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhongCTSVDanhSachGiangVien() {
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
		
		GiangVien giangVien = MyUtils.getLoginedGiangVien(request.getSession());
		request.setAttribute("giangVien", giangVien);
		if (giangVien == null || !giangVien.getChucVu().equals("ctsv")) {
			response.sendRedirect(request.getContextPath() + "/giang-vien/logout");
			return;
		}
		
		PrintWriter out = response.getWriter();
		//lay danh sach khoa
		String aIdKhoa = request.getParameter("faculty");
		String errMsg=(String)request.getAttribute("errMsg");
		String aaidKhoa=(String)request.getAttribute("idKhoa");
		if(aaidKhoa!=null){
			aIdKhoa=aaidKhoa;
		}
		if(aIdKhoa!=null){
			request.setAttribute("errMsg", errMsg);
			int idKhoa=Integer.parseInt(aIdKhoa);
			if(idKhoa>0){
				// lay danh sach khoa
				KhoaBO khoaBO = new KhoaBO();
				ArrayList<Khoa> listKhoa = khoaBO.getAllKhoa();
				request.setAttribute("listKhoa", listKhoa);
				//lay khoa theo id khoa
				Khoa khoa=khoaBO.getKhoaTheoMa(idKhoa);
				request.setAttribute("khoa", khoa);
				//lay danh sach Giang vien theo khoa
				GiangVienBO giangVienBO=new GiangVienBO();
				ArrayList<GiangVien> listGiangVienByKhoa=giangVienBO.getGiangVienByKhoa(idKhoa);
				request.setAttribute("listGiangVienByKhoa", listGiangVienByKhoa);
				RequestDispatcher rd = request.getRequestDispatcher("/phongCTSV/phong-CTSV-danhsach-giangvien.jsp");
				rd.forward(request, response);
			}
		}else{
			// lay danh sach khoa
						KhoaBO khoaBO = new KhoaBO();
						ArrayList<Khoa> listKhoa = khoaBO.getAllKhoa();
						request.setAttribute("listKhoa", listKhoa);
						RequestDispatcher rd = request.getRequestDispatcher("/phongCTSV/phong-CTSV-danhsach-giangvien.jsp");
						rd.forward(request, response);
		}
	}

}
