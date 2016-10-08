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

/**
 * Servlet implementation class PhongCTSVDanhSachGiangVienCapNhat
 */
@WebServlet("/PhongCTSVDanhSachGiangVienCapNhat")
public class PhongCTSVDanhSachGiangVienCapNhat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhongCTSVDanhSachGiangVienCapNhat() {
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
		String submit =request.getParameter("submit");
		String errMsg="";
		GiangVienBO giangVienBO=new GiangVienBO();
		if(submit!=null){
			GiangVien gv=new GiangVien();
			String maGiangVien=request.getParameter("maGiangVien");
			String makhoa=request.getParameter("khoa");
			String ten=request.getParameter("ten");
			String ngaySinh=request.getParameter("ngaySinh");
			String tenDangNhap=request.getParameter("tenDangNhap");
			String matKhau=request.getParameter("matKhau");
			
			System.out.println(tenDangNhap);
			
			String soDienThoai=request.getParameter("soDienThoai");
			String email=request.getParameter("email");
			String chucVu=request.getParameter("chucVu");
			String active=request.getParameter("active");
			if(active!=null){
				gv.setActive(true);
			}else{
				gv.setActive(false);
			}
			if(maGiangVien==""||makhoa==null||ten==""||soDienThoai==""||ngaySinh==null||email==""||chucVu==null||active==null){
				request.setAttribute("idKhoa", makhoa);
				errMsg = "0";
				request.setAttribute("errMsg", errMsg);
				RequestDispatcher rd = request.getRequestDispatcher("/phong-ctsv/danhsach-giangvien");
				rd.forward(request, response);
				return;
				
			}
			gv.setMaGiangVien(Integer.parseInt(maGiangVien));
			gv.setMaKhoa(Integer.parseInt(makhoa));
			gv.setTen(ten);
			gv.setNgaySinh(DateUtils.convertToSDate(ngaySinh));
			gv.setEmail(email);
			gv.setSdt(soDienThoai);
			gv.setTenDangNhap(tenDangNhap);
			gv.setMatKhau(matKhau);
			gv.setChucVu(chucVu);
			
			System.out.println(gv.toString());
			
			GiangVien gv1=new GiangVien();
			if("truongkhoa".equals(gv.getChucVu())){
				gv1=giangVienBO.getTruongKhoaByChucVuVsKhoa(Integer.parseInt(makhoa));
				
			}
			if("truongphongCTSV".equals(gv.getChucVu())){
				gv1=giangVienBO.getTruongPhongCTSV();
			}
			
			if(giangVienBO.updateGiangVien(gv)==1){
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
			
		}else{
			//lay tac ca khoa
			KhoaBO khoaBO=new KhoaBO();
			ArrayList<Khoa> listKhoa=khoaBO.getAllKhoa();
			request.setAttribute("listKhoa", listKhoa);
			// lay Giang Vien can sua
			String idGV=request.getParameter("idGV");
			GiangVien gv=giangVienBO.getGiangVienTheoMa(Integer.parseInt(idGV));
			request.setAttribute("gv", gv);
			//out.println(gv.isActive());
			RequestDispatcher rd = request.getRequestDispatcher("/phongCTSV/phong-CTSV-danh-sach-giang-vien-cap-nhat.jsp");
			rd.forward(request, response);
		}
	}

}
