package model.bo;

import java.sql.Timestamp;
import java.util.ArrayList;

import model.bean.DotDanhGia;
import model.dao.DotDanhGiaDAO;

public class DotDanhGiaBO {
	DotDanhGiaDAO dotDanhGiaDAO = new DotDanhGiaDAO();
	
	public ArrayList<DotDanhGia> getAllDotDanhGia() {
		return dotDanhGiaDAO.getAllDotDanhGia();
	}
	
	public DotDanhGia getDotDanhGiaTheoMa(int maDotDanhGia) {
		return dotDanhGiaDAO.getDotDanhGiaTheoMa(maDotDanhGia);
	}
	
	public int insertDotDanhGia(DotDanhGia dotDanhGia) {
		return dotDanhGiaDAO.insertDotDanhGia(dotDanhGia);
	}
	
	public int updateDotDanhGia(DotDanhGia dotDanhGia) {
		return dotDanhGiaDAO.updateDotDanhGia(dotDanhGia);
	}
	
	public int deleteDotDanhGia(int maDotDanhGia) {
		return dotDanhGiaDAO.deleteDotDanhGia(maDotDanhGia);
	}
	
	//kiet them
	public DotDanhGia getDotDanhGiaMoiNhat() {
		return dotDanhGiaDAO.getDotDanhGiaMoiNhat();
	}
	
	// Kiet them
	public DotDanhGia getDotDanhGiaHienTaiSV(Timestamp thoiGianHienTai) {
		return dotDanhGiaDAO.getDotDanhGiaHienTaiSV(thoiGianHienTai);
	}
	
	// Kiet them
	public DotDanhGia getDotDanhGiaHienTaiLT(Timestamp thoiGianHienTai) {
		return dotDanhGiaDAO.getDotDanhGiaHienTaiLT(thoiGianHienTai);
	}
	
	// Kiet them
	public DotDanhGia getDotDanhGiaHienTaiGVCN(Timestamp thoiGianHienTai) {
		return dotDanhGiaDAO.getDotDanhGiaHienTaiGVCN(thoiGianHienTai);
	}
	
	// Kiet them
	public DotDanhGia getDotDanhGiaHienTaiTK(Timestamp thoiGianHienTai) {
		return dotDanhGiaDAO.getDotDanhGiaHienTaiTK(thoiGianHienTai);
	}
	
}
