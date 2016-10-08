package model.bo;

import java.util.ArrayList;

import model.bean.Khoa;
import model.dao.KhoaDAO;

public class KhoaBO {
	KhoaDAO khoaDAO = new KhoaDAO();
	
	public ArrayList<Khoa> getAllKhoa() {
		return khoaDAO.getAllKhoa();
	}
	
	public Khoa getKhoaTheoMa(int maKhoa) {
		return khoaDAO.getKhoaTheoMa(maKhoa);
	}
	
	public int insertKhoa(Khoa khoa) {
		return khoaDAO.insertKhoa(khoa);
	}
	
	public int updateKhoa(Khoa khoa) {
		return khoaDAO.updateKhoa(khoa);
	}
	
	public int deleteKhoa(int maKhoa) {
		return khoaDAO.deleteKhoa(maKhoa);
	}
}
