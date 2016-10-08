package model.bo;

import java.util.ArrayList;

import model.bean.DanhGia;
import model.dao.DanhGiaDAO;

public class DanhGiaBO {
DanhGiaDAO danhGiaDAO = new DanhGiaDAO();
	
	public ArrayList<DanhGia> getAllDanhGia() {
		return danhGiaDAO.getAllDanhGia();
	}
	
	public DanhGia getDanhGiaTheoMa(int maDanhGia) {
		return danhGiaDAO.getDanhGiaTheoMa(maDanhGia);
	}
	
	public int insertDanhGia(DanhGia danhGia) {
		return danhGiaDAO.insertDanhGia(danhGia);
	}
	
	public int updateDanhGia(DanhGia danhGia) {
		return danhGiaDAO.updateDanhGia(danhGia);
	}
	
	public int deleteDanhGia(int maDanhGia) {
		return danhGiaDAO.deleteDanhGia(maDanhGia);
	}
	
	//kiet them
	public DanhGia getDanhGiaTheoMaSinhVien(int maSinhVien, int maDotDanhGia) {
		return danhGiaDAO.getDanhGiaTheoMaSinhVien(maSinhVien, maDotDanhGia);
	}
	
	//kiet them
	public ArrayList<DanhGia> getAllDanhGiaTheoLopTT(int maDotDanhGia, int maLop, String tinhTrang1, String tinhTrang2, int offset, int noOfRecords) {
		return danhGiaDAO.getAllDanhGiaTheoLopTT(maDotDanhGia, maLop, tinhTrang1, tinhTrang2, offset, noOfRecords);
	}
	
	//kiet them
	public int getCountDanhGiaTheoLopTT(int maDotDanhGia, int maLop, String tinhTrang1, String tinhTrang2) {
		return danhGiaDAO.getCountDanhGiaTheoLopTT(maDotDanhGia, maLop, tinhTrang1, tinhTrang2);
	}

	//kiet them
	public ArrayList<DanhGia> getAllDanhGiaTheoId(String[] id) {
	 return danhGiaDAO.getAllDanhGiaTheoId(id);	
	}
	
	//kiet them
	public int updateDanhGia(ArrayList<DanhGia> listDanhGia) {
		return danhGiaDAO.updateDanhGia(listDanhGia);
		
	}

	//kiet them
	public ArrayList<DanhGia> getAllDanhGiaTheoLopTheoDotPhanTrang(int maDotDanhGia, int maLop, int offset, int noOfRecords ) {
		return danhGiaDAO.getAllDanhGiaTheoLopTheoDotPhanTrang(maDotDanhGia, maLop, offset, noOfRecords);
	}
	
	//kiet them
	public ArrayList<DanhGia> getAllDanhGiaTheoLopTheoDot(int maDotDanhGia, int maLop) {
		return danhGiaDAO.getAllDanhGiaTheoLopTheoDot(maDotDanhGia, maLop);
	}

	//kiet them
	public int getCountDanhGiaTheoLopTheoDot(int maDotDanhGia, int maLop) {
		return danhGiaDAO.getCountDanhGiaTheoLopTheoDot(maDotDanhGia, maLop);
	}
	
	//huy them
	public ArrayList<DanhGia> getAllDanhGiaXacNhan() {
		return danhGiaDAO.getAllDanhGiaXacNhan() ;
	}
	
	//huy them
	public int xacNhanDanhGia(String[] listIddanhgia) {
		return danhGiaDAO.xacNhanDanhGia(listIddanhgia) ;
	}
	
	//huy
	public ArrayList<DanhGia> getAllDanhGiaXacNhanByDotDanhGiaVsLop(int idDotDanhGia, int idLop) {
		return danhGiaDAO.getAllDanhGiaXacNhanByDotDanhGiaVsLop(idDotDanhGia,idLop); 
	}
	
	public int lastInserted() {
		return danhGiaDAO.lastInserted();
	}
}