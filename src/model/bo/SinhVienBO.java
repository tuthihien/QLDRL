package model.bo;

import java.util.ArrayList;

import model.bean.DanhGia;
import model.bean.Lop;
import model.bean.SinhVien;
import model.dao.SinhVienDAO;

public class SinhVienBO {
	
	SinhVienDAO sinhVienDAO = new SinhVienDAO();
	
	public ArrayList<SinhVien> getAllSinhVien() {
		return sinhVienDAO.getAllSinhVien();
	}
	
	public SinhVien getSinhVienTheoMa(int maSinhVien) {
		return sinhVienDAO.getSinhVienTheoMa(maSinhVien);
	}
	
	public int insertSinhVien(SinhVien sinhVien) {
		return sinhVienDAO.insertSinhVien(sinhVien);
	}
	
	public int updateSinhVien(SinhVien sinhVien) {
		return sinhVienDAO.updateSinhVien(sinhVien);
	}
	
	public int deleteSinhVien(int maSinhVien) {
		return sinhVienDAO.deleteSinhVien(maSinhVien);
	}
	
	//kiet them
	public SinhVien getSinhVienDangNhap(String tenDangNhap, String matKhau) {
		return sinhVienDAO.getSinhVienDangNhap(tenDangNhap, matKhau);
	}
	
	//kiet them
	public ArrayList<SinhVien> getListSinhVienTuListDanhGia (ArrayList<DanhGia> listDanhGia) {
		return sinhVienDAO.getListSinhVienTuListDanhGia(listDanhGia);
	}
	
	// huy them
	public SinhVien getSinhVienById(int idSV) {
		return sinhVienDAO.getSinhVienById(idSV); 
	}
	//huy them
	public ArrayList<SinhVien> getAllSinhVienXacNhanByLop(int idLop) {
		return sinhVienDAO.getAllSinhVienXacNhanByLop(idLop) ;
	}
	// huy them
	public ArrayList<SinhVien> getAllSinhVienXacNhanByName(String name) {
		return sinhVienDAO.getAllSinhVienXacNhanByName(name); 
	}
	//huy them
	public ArrayList<SinhVien> getAllSinhVienXacNhanByLopVsTen(int idLop, String name) {
		return sinhVienDAO.getAllSinhVienXacNhanByLopVsTen(idLop, name) ;
	}
	// huy them
	public ArrayList<SinhVien> getAllSinhVienXacNhanByNameVsKhoa(ArrayList<SinhVien> listSinhVienXacNhanByName,
			ArrayList<Lop> listLopByKhoa) {
		for(SinhVien sv:listSinhVienXacNhanByName){
			int check=0;
			for(Lop lop :listLopByKhoa){
				if(lop.getMaLop()==sv.getMaLop()){
					check++;
				}
			}
			if(check==0){
				listSinhVienXacNhanByName.remove(sv);
			}
		}
		return listSinhVienXacNhanByName;
	}
//huy them
	public ArrayList<SinhVien> getAllSinhViebByLop(String idLop) {
		return sinhVienDAO.getAllSinhViebByLop(idLop);
	}
	
	//huy them
	public ArrayList<SinhVien> getAllSinhVienTheoLop(int idLop) {
		return sinhVienDAO.getAllSinhVienTheoLop(idLop);
	}
	
	public SinhVien getLopTruongByLop(int idLop) {
		return sinhVienDAO.getLopTruongByLop(idLop); 
	}

	public int deleteSinhVienByCheckBox(String[] listIdSinhVien) {
		return sinhVienDAO.deleteSinhVienByCheckBox(listIdSinhVien);
	}

}
