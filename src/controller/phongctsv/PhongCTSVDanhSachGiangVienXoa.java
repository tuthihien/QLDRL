package controller.phongctsv;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.MyUtils;
import model.bean.GiangVien;
import model.bo.GiangVienBO;

/**
 * Servlet implementation class PhongCTSVDanhSachGiangVienXoa
 */
public class PhongCTSVDanhSachGiangVienXoa extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhongCTSVDanhSachGiangVienXoa() {
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
		String maKhoa=request.getParameter("idKhoa");
		String[] listIdGiangVien = request.getParameterValues("chk");
		GiangVienBO giangVienBO=new GiangVienBO();
		String errMsg="";
		if(listIdGiangVien!=null){
		if(giangVienBO.deleteGiangVien(Integer.parseInt(listIdGiangVien[0]))==1){
			request.setAttribute("idKhoa", maKhoa);
			errMsg = "1";
			request.setAttribute("errMsg", errMsg);
			
			RequestDispatcher rd = request.getRequestDispatcher("/phong-ctsv/danhsach-giangvien");
			rd.forward(request, response);
		}else{
			request.setAttribute("idKhoa", maKhoa);
			errMsg = "0";
			request.setAttribute("errMsg", errMsg);
			RequestDispatcher rd = request.getRequestDispatcher("/phong-ctsv/danhsach-giangvien");
			rd.forward(request, response);
		}
		}else{
			request.setAttribute("idKhoa", maKhoa);
			errMsg = "2";
			request.setAttribute("errMsg", errMsg);
			RequestDispatcher rd = request.getRequestDispatcher("/phong-ctsv/danhsach-giangvien");
			rd.forward(request, response);
		}
	}

}
