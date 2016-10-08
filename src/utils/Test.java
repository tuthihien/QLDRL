package utils;

import java.util.ArrayList;

import model.bean.Khoa;
import model.bean.Lop;
import model.bean.NoiDungDanhGia;
import model.bean.SinhVien;
import model.bo.KhoaBO;
import model.bo.LopBO;
import model.bo.NoiDungDanhGiaBO;
import model.bo.SinhVienBO;
import model.dao.SinhVienDAO;

public class Test {
	
	public static void main(String[] args) {
		LopBO lopBO = new LopBO();
		ArrayList<Lop> listLop = lopBO.getAllLopByKhoa(1);
		
		for (Lop lop : listLop) {
			System.out.println(lop.toString());
		}
	}
}


