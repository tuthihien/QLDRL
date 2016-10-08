package model.bean;

import java.sql.Date;

public class GiangVien {
	private int maGiangVien;
	private int maKhoa;
	private String ten;
	private Date ngaySinh;
	private String email;
	private String sdt;
	private String tenDangNhap;
	private String matKhau;
	private String chucVu;
	private boolean active;
	
	public GiangVien() {
		super();
	}

	public GiangVien(int maGiangVien, int maKhoa, String ten, Date ngaySinh, String email, String sdt,
			String tenDangNhap, String matKhau, String chucVu) {
		super();
		this.maGiangVien = maGiangVien;
		this.maKhoa = maKhoa;
		this.ten = ten;
		this.ngaySinh = ngaySinh;
		this.email = email;
		this.sdt = sdt;
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
		this.chucVu = chucVu;
		this.active = true;
	}

	public GiangVien(int maKhoa, String ten, Date ngaySinh, String email, String sdt, String tenDangNhap,
			String matKhau, String chucVu) {
		super();
		this.maKhoa = maKhoa;
		this.ten = ten;
		this.ngaySinh = ngaySinh;
		this.email = email;
		this.sdt = sdt;
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
		this.chucVu = chucVu;
		this.active = true;
	}

	public int getMaGiangVien() {
		return maGiangVien;
	}
	
	public void setMaGiangVien(int maGiangVien) {
		this.maGiangVien = maGiangVien;
	}
	

	public int getMaKhoa() {
		return maKhoa;
	}

	public void setMaKhoa(int maKhoa) {
		this.maKhoa = maKhoa;
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

	public String getChucVu() {
		return chucVu;
	}

	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "GiangVien [maGiangVien=" + maGiangVien + ", maKhoa=" + maKhoa
				+ ", ten=" + ten + ", ngaySinh=" + ngaySinh + ", email="
				+ email + ", sdt=" + sdt + ", tenDangNhap=" + tenDangNhap
				+ ", matKhau=" + matKhau + ", chucVu=" + chucVu + ", active="
				+ active + "]";
	}

	
	
}
