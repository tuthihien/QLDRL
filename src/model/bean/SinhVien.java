package model.bean;

import java.sql.Date;

public class SinhVien {
	private int maSinhVien;
	private String mssv;
	private String ten;
	private Date ngaySinh;
	private int maLop;
	private String tenDangNhap;
	private String matKhau;
	private String email;
	private String sdt;
	private String gioiTinh;
	private String chucVu;
	private float diemHocBong;
	private boolean active;
	
	public SinhVien(int maSinhVien, String mssv, String ten, Date ngaySinh, int maLop,
			String tenDangNhap, String matKhau, String email, String sdt, String gioiTinh,
			String chucVu) {
		super();
		this.maSinhVien = maSinhVien;
		this.mssv = mssv;
		this.ten = ten;
		this.ngaySinh = ngaySinh;
		this.maLop = maLop;
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
		this.email = email;
		this.sdt = sdt;
		this.gioiTinh = gioiTinh;
		this.chucVu = chucVu;
		this.diemHocBong = 0;
		this.active = true;
	}

	public SinhVien(String mssv, String ten, Date ngaySinh, int maLop, String tenDangNhap,
			String matKhau, String email, String sdt, String gioiTinh, String chucVu,
			Integer diemHocBong) {
		super();
		this.mssv = mssv;
		this.ten = ten;
		this.ngaySinh = ngaySinh;
		this.maLop = maLop;
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
		this.email = email;
		this.sdt = sdt;
		this.gioiTinh = gioiTinh;
		this.chucVu = chucVu;
		this.diemHocBong = 0;
		this.active = true;
	}
	
	public SinhVien() {
		
	}

	public int getMaSinhVien() {
		return maSinhVien;
	}

	public void setMaSinhVien(int maSinhVien) {
		this.maSinhVien = maSinhVien;
	}
	
	public String getMssv() {
		return mssv;
	}

	public void setMssv(String mssv) {
		this.mssv = mssv;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}
	
	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public int getMaLop() {
		return maLop;
	}

	public void setMaLop(int maLop) {
		this.maLop = maLop;
	}

	public String getTenDangNhap() {
		return tenDangNhap;
	}

	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getChucVu() {
		return chucVu;
	}

	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}

	public float getDiemHocBong() {
		return diemHocBong;
	}

	public void setDiemHocBong(float diemHocBong) {
		this.diemHocBong = diemHocBong;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "SinhVien [maSinhVien=" + maSinhVien + ", mssv=" + mssv
				+ ", ten=" + ten + ", ngaySinh=" + ngaySinh + ", maLop="
				+ maLop + ", tenDangNhap=" + tenDangNhap + ", matKhau="
				+ matKhau + ", email=" + email + ", sdt=" + sdt + ", chucVu="
				+ chucVu + ", diemHocBong=" + diemHocBong + ", active="
				+ active + "]";
	}
	
}
