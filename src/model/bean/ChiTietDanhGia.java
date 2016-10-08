package model.bean;

public class ChiTietDanhGia {
	private int maChiTietDanhGia;
	private int maNoiDungDanhGia;
	private int maDanhGia;
	private int maSinhVien;
	private int diemDanhGia;
	private int diemTapTheLop;
	private boolean active;
	
	public ChiTietDanhGia() {
		super();
	}
	
	public ChiTietDanhGia(int maChiTietDanhGia, int maNoiDungDanhGia,
			int maDanhGia, int maSinhVien, int diemDanhGia, int diemTapTheLop) {
		super();
		this.maChiTietDanhGia = maChiTietDanhGia;
		this.maNoiDungDanhGia = maNoiDungDanhGia;
		this.maDanhGia = maDanhGia;
		this.maSinhVien = maSinhVien;
		this.diemDanhGia = diemDanhGia;
		this.diemTapTheLop = diemTapTheLop;
		this.active = true;
	}
	
	public ChiTietDanhGia(int maNoiDungDanhGia, int maDanhGia, int maSinhVien,
			int diemDanhGia, int diemTapTheLop) {
		super();
		this.maNoiDungDanhGia = maNoiDungDanhGia;
		this.maDanhGia = maDanhGia;
		this.maSinhVien = maSinhVien;
		this.diemDanhGia = diemDanhGia;
		this.diemTapTheLop = diemTapTheLop;
		this.active = true;
	}
	
	public int getMaChiTietDanhGia() {
		return maChiTietDanhGia;
	}
	
	public void setMaChiTietDanhGia(int maChiTietDanhGia) {
		this.maChiTietDanhGia = maChiTietDanhGia;
	}
	
	public int getMaNoiDungDanhGia() {
		return maNoiDungDanhGia;
	}
	
	public void setMaNoiDungDanhGia(int maNoiDungDanhGia) {
		this.maNoiDungDanhGia = maNoiDungDanhGia;
	}
	
	public int getMaDanhGia() {
		return maDanhGia;
	}
	
	public void setMaDanhGia(int maDanhGia) {
		this.maDanhGia = maDanhGia;
	}
	
	public int getMaSinhVien() {
		return maSinhVien;
	}
	
	public void setMaSinhVien(int maSinhVien) {
		this.maSinhVien = maSinhVien;
	}
	
	public int getDiemDanhGia() {
		return diemDanhGia;
	}
	
	public void setDiemDanhGia(int diemDanhGia) {
		this.diemDanhGia = diemDanhGia;
	}
	
	public int getDiemTapTheLop() {
		return diemTapTheLop;
	}

	public void setDiemTapTheLop(int diemTapTheLop) {
		this.diemTapTheLop = diemTapTheLop;
	}

	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "ChiTietDanhGia [maChiTietDanhGia=" + maChiTietDanhGia
				+ ", maNoiDungDanhGia=" + maNoiDungDanhGia + ", maDanhGia="
				+ maDanhGia + ", maSinhVien=" + maSinhVien + ", diemDanhGia="
				+ diemDanhGia + ", diemTapTheLop=" + diemTapTheLop
				+ ", active=" + active + "]";
	}
	
	
	
}
