package model.bo;

import java.util.ArrayList;

import model.bean.MucDanhGia;
import model.dao.MucDanhGiaDAO;

public class MucDanhGiaBO {
	MucDanhGiaDAO mucDanhGiaDAO = new MucDanhGiaDAO();
	
	public ArrayList<MucDanhGia> getAllMucDanhGia() {
		return mucDanhGiaDAO.getAllMucDanhGia();
	}
	
	public MucDanhGia getMucDanhGiaTheoMa(int maMucDanhGia) {
		return mucDanhGiaDAO.getMucDanhGiaTheoMa(maMucDanhGia);
	}
	
	public int insertMucDanhGia(MucDanhGia mucDanhGia) {
		return mucDanhGiaDAO.insertMucDanhGia(mucDanhGia);
	}
	
	public int updateMucDanhGia(MucDanhGia mucDanhGia) {
		return mucDanhGiaDAO.updateMucDanhGia(mucDanhGia);
	}
	
	public int deleteMucDanhGia(int maMucDanhGia) {
		return mucDanhGiaDAO.deleteMucDanhGia(maMucDanhGia);
	}
}
