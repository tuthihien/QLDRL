package model.bean;

import java.sql.Timestamp;

public class DotDanhGia {
	private int maDotDanhGia;
	private String ten;
	private Timestamp ngayBatDauSV;
	private Timestamp ngayKetThucSV;
	private Timestamp ngayBatDauLT;
	private Timestamp ngayKetThucLT;
	private Timestamp ngayBatDauGV;
	private Timestamp ngayKetThucGV;
	private Timestamp ngayBatDauTK;
	private Timestamp ngayKetThucTK;
	private boolean active;
	
	public DotDanhGia() {
		super();
	}

	public DotDanhGia(int maDotDanhGia, String ten, Timestamp ngayBatDauSV,
			Timestamp ngayKetThucSV, Timestamp ngayBatDauLT,
			Timestamp ngayKetThucLT, Timestamp ngayBatDauGV,
			Timestamp ngayKetThucGV, Timestamp ngayBatDauTK, Timestamp ngayKetThucTK) {
		super();
		this.maDotDanhGia = maDotDanhGia;
		this.ten = ten;
		this.ngayBatDauSV = ngayBatDauSV;
		this.ngayKetThucSV = ngayKetThucSV;
		this.ngayBatDauLT = ngayBatDauLT;
		this.ngayKetThucLT = ngayKetThucLT;
		this.ngayBatDauGV = ngayBatDauGV;
		this.ngayKetThucGV = ngayKetThucGV;
		this.ngayBatDauTK = ngayBatDauTK;
		this.ngayKetThucTK = ngayKetThucTK;
		this.active = true;
	}

	public DotDanhGia(String ten, Timestamp ngayBatDauSV,
			Timestamp ngayKetThucSV, Timestamp ngayBatDauLT,
			Timestamp ngayKetThucLT, Timestamp ngayBatDauGV,
			Timestamp ngayKetThucGV, Timestamp ngayBatDauTK, Timestamp ngayKetThucTK) {
		super();
		this.ten = ten;
		this.ngayBatDauSV = ngayBatDauSV;
		this.ngayKetThucSV = ngayKetThucSV;
		this.ngayBatDauLT = ngayBatDauLT;
		this.ngayKetThucLT = ngayKetThucLT;
		this.ngayBatDauGV = ngayBatDauGV;
		this.ngayKetThucGV = ngayKetThucGV;
		this.ngayBatDauTK = ngayBatDauTK;
		this.ngayKetThucTK = ngayKetThucTK;
		this.active = true;
	}

	public int getMaDotDanhGia() {
		return maDotDanhGia;
	}
	
	public void setMaDotDanhGia(int maDotDanhGia) {
		this.maDotDanhGia = maDotDanhGia;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public Timestamp getNgayBatDauSV() {
		return ngayBatDauSV;
	}

	public void setNgayBatDauSV(Timestamp ngayBatDauSV) {
		this.ngayBatDauSV = ngayBatDauSV;
	}

	public Timestamp getNgayKetThucSV() {
		return ngayKetThucSV;
	}

	public void setNgayKetThucSV(Timestamp ngayKetThucSV) {
		this.ngayKetThucSV = ngayKetThucSV;
	}

	public Timestamp getNgayBatDauLT() {
		return ngayBatDauLT;
	}

	public void setNgayBatDauLT(Timestamp ngayBatDauLT) {
		this.ngayBatDauLT = ngayBatDauLT;
	}

	public Timestamp getNgayKetThucLT() {
		return ngayKetThucLT;
	}

	public void setNgayKetThucLT(Timestamp ngayKetThucLT) {
		this.ngayKetThucLT = ngayKetThucLT;
	}

	public Timestamp getNgayBatDauGV() {
		return ngayBatDauGV;
	}

	public void setNgayBatDauGV(Timestamp ngayBatDauGV) {
		this.ngayBatDauGV = ngayBatDauGV;
	}

	public Timestamp getNgayKetThucGV() {
		return ngayKetThucGV;
	}

	public void setNgayKetThucGV(Timestamp ngayKetThucGV) {
		this.ngayKetThucGV = ngayKetThucGV;
	}
	
	public Timestamp getNgayBatDauTK() {
		return ngayBatDauTK;
	}

	public void setNgayBatDauTK(Timestamp ngayBatDauTK) {
		this.ngayBatDauTK = ngayBatDauTK;
	}

	public Timestamp getNgayKetThucTK() {
		return ngayKetThucTK;
	}

	public void setNgayKetThucTK(Timestamp ngayKetThucTK) {
		this.ngayKetThucTK = ngayKetThucTK;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "DotDanhGia [maDotDanhGia=" + maDotDanhGia + ", ten=" + ten
				+ ", ngayBatDauSV=" + ngayBatDauSV + ", ngayKetThucSV="
				+ ngayKetThucSV + ", ngayBatDauLT=" + ngayBatDauLT
				+ ", ngayKetThucLT=" + ngayKetThucLT + ", ngayBatDauGV="
				+ ngayBatDauGV + ", ngayKetThucGV=" + ngayKetThucGV
				+ ", ngayBatDauTK=" + ngayBatDauTK + ", ngayKetThucTK="
				+ ngayKetThucTK + ", active=" + active + "]";
	}
	
}
