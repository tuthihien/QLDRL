package model.bo;

import java.util.ArrayList;

import model.bean.GiangVien;
import model.dao.GiangVienDAO;

public class GiangVienBO {
	GiangVienDAO giangVienDAO = new GiangVienDAO();
	
	public ArrayList<GiangVien> getAllGiangVien() {
		return giangVienDAO.getAllGiangVien();
	}
	
	public GiangVien getGiangVienTheoMa(int maGiangVien) {
		return giangVienDAO.getGiangVienTheoMa(maGiangVien);
	}
	
	public int insertGiangVien(GiangVien giangVien) {
		return giangVienDAO.insertGiangVien(giangVien);
	}
	
	public int updateGiangVien(GiangVien giangVien) {
		return giangVienDAO.updateGiangVien(giangVien);
	}
	
	public int deleteGiangVien(int maGiangVien) {
		return giangVienDAO.deleteGiangVien(maGiangVien);
	}

	public GiangVien getGiangVienDangNhap(String tenDangNhap, String matKhau) {
		return giangVienDAO.getGiangVienDangNhap(tenDangNhap, matKhau);
	}
	
	//huy them
	public ArrayList<GiangVien> getGiangVienByKhoa(int idKhoa) {
		return giangVienDAO.getGiangVienByKhoa(idKhoa); 
	}
	
	// huy them
	public ArrayList<GiangVien> getAllGiangVienByName(String name) {
		return giangVienDAO.getAllGiangVienByName(name) ;
	}
	
	//huy them
	public ArrayList<GiangVien> getGiangVienByKhoaVsTen(int idKhoa, String name) {
		return giangVienDAO.getGiangVienByKhoaVsTen(idKhoa,name);
	}
	
	//huy them
	public int deleteGiangVienByCheckBox(String[] listIdGiangVien) {
		return giangVienDAO.deleteGiangVienByCheckBox(listIdGiangVien);
	}

	//huy them moi			
	public GiangVien getTruongKhoaByChucVuVsKhoa(int idKhoa) {
		return giangVienDAO.getTruongKhoaByChucVuVsKhoa(idKhoa) ;
	}
	//huy them moi			
	public GiangVien getTruongPhongCTSV() {
		return giangVienDAO.getTruongPhongCTSV();
	}
}
