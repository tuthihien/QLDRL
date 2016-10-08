package model.bo;

import java.util.ArrayList;

import model.bean.Lop;
import model.dao.LopDAO;

public class LopBO {
	LopDAO lopDAO = new LopDAO();
	
	public ArrayList<Lop> getAllLop() {
		return lopDAO.getAllLop();
	}
	
	public Lop getLopTheoMa(int maLop) {
		return lopDAO.getLopTheoMa(maLop);
	}
	
	public int insertLop(Lop lop) {
		return lopDAO.insertLop(lop);
	}
	
	public int updateLop(Lop lop) {
		return lopDAO.updateLop(lop);
	}
	
	public int deleteLop(int maLop) {
		return lopDAO.deleteLop(maLop);
	}
	
	//kiet them
	public ArrayList<Lop> getAllLopGVCN(int maGiangVien) {
		return lopDAO.getAllLopGVCN(maGiangVien);
	}
	
	// huy them
	public ArrayList<Lop> getAllLopByKhoa(int idKhoa) {
		return lopDAO.getAllLopByKhoa(idKhoa) ;
	}
	
}
