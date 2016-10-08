package model.bean;

public class Lop {
	private int maLop;
	private int maKhoa;
	private String ten;
	private int maGiangVien;
	private boolean active;
	
	public Lop() {
		super();
	}

	public Lop(int maLop, int maKhoa, String ten, int maGiangVien) {
		super();
		this.maLop = maLop;
		this.maKhoa = maKhoa;
		this.ten = ten;
		this.maGiangVien = maGiangVien;
		this.active = true;
	}

	public Lop(int maKhoa, String ten, int maGiangVien) {
		super();
		this.maKhoa = maKhoa;
		this.ten = ten;
		this.maGiangVien = maGiangVien;
		this.active = true;
	}

	public int getMaLop() {
		return maLop;
	}
	
	public void setMaLop(int maLop) {
		this.maLop = maLop;
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

	public int getMaGiangVien() {
		return maGiangVien;
	}

	public void setMaGiangVien(int maGiangVien) {
		this.maGiangVien = maGiangVien;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Lop [maLop=" + maLop + ", maKhoa=" + maKhoa + ", ten=" + ten
				+ ", maGiangVien=" + maGiangVien + ", active=" + active + "]";
	}
	
	
}
