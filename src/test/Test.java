package test;

import model.bo.KhoaBO;
import model.bo.LopBO;
import model.bo.SinhVienBO;

public class Test {

	public static void main(String[] args) {
		SinhVienBO svBO = new SinhVienBO();
		LopBO lopBO = new LopBO();
		KhoaBO khoaBO = new KhoaBO();
		System.out.println(svBO.getSinhVienById(1));
		System.out.println(lopBO.getLopTheoMa(1));
		System.out.println(khoaBO.getKhoaTheoMa(1));

	}

}
