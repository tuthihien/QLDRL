package model.bo;

import java.util.ArrayList;

import model.bean.ChiTietDanhGia;
import model.dao.ChiTietDanhGiaDAO;

public class ChiTietDanhGiaBO {
	ChiTietDanhGiaDAO chiTietDanhGiaDAO = new ChiTietDanhGiaDAO();
	
	public ArrayList<ChiTietDanhGia> getAllChiTietDanhGia() {
		return chiTietDanhGiaDAO.getAllChiTietDanhGia();
	}
	
	public ChiTietDanhGia getChiTietDanhGiaTheoMa(int maChiTietDanhGia) {
		return chiTietDanhGiaDAO.getChiTietDanhGiaTheoMa(maChiTietDanhGia);
	}
	
	public int insertChiTietDanhGia(ChiTietDanhGia chiTietDanhGia) {
		return chiTietDanhGiaDAO.insertChiTietDanhGia(chiTietDanhGia);
	}
	
	public int updateChiTietDanhGia(ChiTietDanhGia chiTietDanhGia) {
		return chiTietDanhGiaDAO.updateChiTietDanhGia(chiTietDanhGia);
	}
	
	public int deleteChiTietDanhGia(int maChiTietDanhGia) {
		return chiTietDanhGiaDAO.deleteChiTietDanhGia(maChiTietDanhGia);
	}
	
	// kiet them
	public int insertListChiTietDanhGia(ArrayList<ChiTietDanhGia> listChiTietDanhGia) {
		return chiTietDanhGiaDAO.insertListChiTietDanhGia(listChiTietDanhGia);
	}
	
	// kiet them
	public int deleteChiTietDanhGiaTheoMaDanhGia(int maDanhGia) {
		return chiTietDanhGiaDAO.deleteChiTietDanhGiaTheoMaDanhGia(maDanhGia);
	}

	// kiet them , dung trong SinhVienCapNhatDanhGiaServlet
	public ArrayList<ChiTietDanhGia> getAllChiTietDanGiaTheoMucCapNhat(int maDanhGia, int maMucDanhGia) {
		return chiTietDanhGiaDAO.getAllChiTietDanhGiaTheoMucCapNhat(maDanhGia, maMucDanhGia);
	}
		
}
