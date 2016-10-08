package model.bo;

import java.util.ArrayList;

import model.bean.NoiDungDanhGia;
import model.dao.NoiDungDanhGiaDAO;

public class NoiDungDanhGiaBO {
	NoiDungDanhGiaDAO noiDungDanhGiaDAO = new NoiDungDanhGiaDAO();
	
	public ArrayList<NoiDungDanhGia> getAllNoiDungDanhGia() {
		return noiDungDanhGiaDAO.getAllNoiDungDanhGia();
	}
	
	//kiet them
	public ArrayList<NoiDungDanhGia> getAllNoiDungDanhGiaTheoMuc(int maMucDanhGia) {
		return noiDungDanhGiaDAO.getAllNoiDungDanhGiaTheoMuc(maMucDanhGia);
	}
	
	//kiet them - dung trong SinhVienCapNhatDanhGiaServlet
	public ArrayList<NoiDungDanhGia> getAllNoiDungDanhGiaTheoMucCapNhat(int maMucDanhGia, int maDanhGia) {
		return noiDungDanhGiaDAO.getAllNoiDungDanhGiaTheoMucCapNhat(maMucDanhGia, maDanhGia);
	}
	
	
	public NoiDungDanhGia getNoiDungDanhGiaTheoMa(int maNoiDungDanhGia) {
		return noiDungDanhGiaDAO.getNoiDungDanhGiaTheoMa(maNoiDungDanhGia);
	}
	
	public int insertNoiDungDanhGia(NoiDungDanhGia noiDungDanhGia) {
		return noiDungDanhGiaDAO.insertNoiDungDanhGia(noiDungDanhGia);
	}
	
	public int updateNoiDungDanhGia(NoiDungDanhGia noiDungDanhGia) {
		return noiDungDanhGiaDAO.updateNoiDungDanhGia(noiDungDanhGia);
	}
	
	public int deleteNoiDungDanhGia(int maNoiDungDanhGia) {
		return noiDungDanhGiaDAO.deleteNoiDungDanhGia(maNoiDungDanhGia);
	}
	
	
}
