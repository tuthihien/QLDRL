package controller.phongctsv;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.GiangVien;
import model.bean.Khoa;
import model.bo.GiangVienBO;
import model.bo.KhoaBO;
import utils.DateUtils;
import utils.MyUtils;
import utils.Validate;

/**
 * Servlet implementation class PhongCTSVDanhSachGiangVienThem
 */
public class PhongCTSVDanhSachGiangVienThem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PhongCTSVDanhSachGiangVienThem() {
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
		String errMsg="";
		GiangVien giangVien = MyUtils.getLoginedGiangVien(request.getSession());
		request.setAttribute("giangVien", giangVien);
		if (giangVien == null || !giangVien.getChucVu().equals("ctsv")) {
			response.sendRedirect(request.getContextPath() + "/giang-vien/logout");
			return;
		}
		
		PrintWriter out = response.getWriter();
		String submit = request.getParameter("submit");
		if (submit != null) {
			GiangVien gv = new GiangVien();
			String ten=request.getParameter("ten");
			String ngaySinh = request.getParameter("ngaySinh");
			String soDienThoai = request.getParameter("soDienThoai");
			String email = request.getParameter("email");
			String chucVu = request.getParameter("chucVu");
			String active = request.getParameter("active");
			String makhoa = request.getParameter("faculty");
//			System.out.println(ten);
//			System.out.println(ngaySinh);
//			System.out.println(soDienThoai);
//			System.out.println(email);
//			System.out.println(chucVu);
//			System.out.println(makhoa);
			
			if(ten==""||ngaySinh==""||soDienThoai==""||!Validate.validateNumber(soDienThoai)||email==""||chucVu==""||makhoa==null){
				System.out.println("here");
				errMsg = "0";
				request.setAttribute("errMsg", errMsg);
				RequestDispatcher rd = request.getRequestDispatcher("/phong-ctsv/danhsach-giangvien");
				rd.forward(request, response);
				return;

			}
			gv=new GiangVien(Integer.parseInt(makhoa), ten, DateUtils.convertToSDate(ngaySinh),email , soDienThoai, email, "123456", chucVu);
			out.println(gv.toString());
		
			GiangVienBO giangVienBO=new GiangVienBO();
			GiangVien gv1=new GiangVien();
			if("truongkhoa".equals(gv.getChucVu())){
				gv1=giangVienBO.getTruongKhoaByChucVuVsKhoa(Integer.parseInt(makhoa));
				
			}
			if("ctsv".equals(gv.getChucVu())){
				gv1=giangVienBO.getTruongPhongCTSV();
			}
			if(giangVienBO.insertGiangVien(gv)==1){
				gv1.setActive(false);
				giangVienBO.updateGiangVien(gv1);
				request.setAttribute("idKhoa", makhoa);
				errMsg = "1";
				request.setAttribute("errMsg", errMsg);
				RequestDispatcher rd = request.getRequestDispatcher("/phong-ctsv/danhsach-giangvien");
				rd.forward(request, response);
			}else{
				request.setAttribute("idKhoa", makhoa);
				
				errMsg = "0";
				request.setAttribute("errMsg", errMsg);
				RequestDispatcher rd = request.getRequestDispatcher("/phong-ctsv/danhsach-giangvien");
				rd.forward(request, response);

			}	
		} else {
			// lay danh sach khoa
			KhoaBO khoaBO = new KhoaBO();
			ArrayList<Khoa> listKhoa = khoaBO.getAllKhoa();
			request.setAttribute("listKhoa", listKhoa);
			RequestDispatcher rd = request.getRequestDispatcher("/phongCTSV/phong-CTSV-danh-sach-giang-vien-them.jsp");
			rd.forward(request, response);
		}
	}

}
