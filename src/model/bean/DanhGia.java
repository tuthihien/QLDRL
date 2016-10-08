package model.bean;

import java.sql.Timestamp;

import utils.DateUtils;

public class DanhGia {
	private int maDanhGia;
	private int maSinhVien;
	private int maDotDanhGia;
	private Timestamp ngayDanhGia;
	private Integer tongDiem;
	private Integer diemTapTheLop;
	private String tinhTrang;
	private String ghiChu;
	private Timestamp ngayXacNhanLT;
	private Timestamp ngayXacNhanGV;
	private Timestamp ngayXacNhanTK;
	private Timestamp ngayXacNhanCTSV;
	private boolean active;
	
	public DanhGia() {
		super();
	}

	public DanhGia(int maDanhGia, int maSinhVien, int maDotDanhGia,
			Timestamp ngayDanhGia, Integer tongDiem, Integer diemTapTheLop, String tinhTrang,
			String ghiChu, Timestamp ngayXacNhanLT, Timestamp ngayXacNhanGV, Timestamp ngayXacNhanTK, Timestamp ngayXacNhanCTSV) {
		super();
		this.maDanhGia = maDanhGia;
		this.maSinhVien = maSinhVien;
		this.maDotDanhGia = maDotDanhGia;
		this.ngayDanhGia = ngayDanhGia;
		this.tongDiem = tongDiem;
		this.diemTapTheLop = diemTapTheLop;
		this.tinhTrang = tinhTrang;
		this.ghiChu = ghiChu;
		this.ngayXacNhanLT = ngayXacNhanLT;
		this.ngayXacNhanGV = ngayXacNhanGV;
		this.ngayXacNhanTK = ngayXacNhanTK;
		this.ngayXacNhanCTSV = ngayXacNhanCTSV;
		this.active = true;
	}

	public DanhGia(int maSinhVien, int maDotDanhGia, Timestamp ngayDanhGia,
			Integer tongDiem, Integer diemTapTheLop, String tinhTrang, String ghiChu,
			Timestamp ngayXacNhanLT, Timestamp ngayXacNhanGV, Timestamp ngayXacNhanTK, Timestamp ngayXacNhanCTSV) {
		super();
		this.maSinhVien = maSinhVien;
		this.maDotDanhGia = maDotDanhGia;
		this.ngayDanhGia = ngayDanhGia;
		this.tongDiem = tongDiem;
		this.diemTapTheLop = diemTapTheLop;
		this.tinhTrang = tinhTrang;
		this.ghiChu = ghiChu;
		this.ngayXacNhanLT = ngayXacNhanLT;
		this.ngayXacNhanGV = ngayXacNhanGV;
		this.ngayXacNhanTK = ngayXacNhanTK;
		this.ngayXacNhanCTSV = ngayXacNhanCTSV;
		this.active = true;
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

	public int getMaDotDanhGia() {
		return maDotDanhGia;
	}

	public void setMaDotDanhGia(int maDotDanhGia) {
		this.maDotDanhGia = maDotDanhGia;
	}

	public Timestamp getNgayDanhGia() {
		return ngayDanhGia;
	}

	public void setNgayDanhGia(Timestamp ngayDanhGia) {
		this.ngayDanhGia = ngayDanhGia;
	}

	public Integer getTongDiem() {
		return tongDiem;
	}

	public void setTongDiem(Integer tongDiem) {
		this.tongDiem = tongDiem;
	}

	public Integer getDiemTapTheLop() {
		return diemTapTheLop;
	}

	public void setDiemTapTheLop(Integer diemTapTheLop) {
		this.diemTapTheLop = diemTapTheLop;
	}

	public String getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public Timestamp getNgayXacNhanLT() {
		return ngayXacNhanLT;
	}

	public void setNgayXacNhanLT(Timestamp ngayXacNhanLT) {
		this.ngayXacNhanLT = ngayXacNhanLT;
	}

	public Timestamp getNgayXacNhanGV() {
		return ngayXacNhanGV;
	}

	public void setNgayXacNhanGV(Timestamp ngayXacNhanGV) {
		this.ngayXacNhanGV = ngayXacNhanGV;
	}

	public Timestamp getNgayXacNhanTK() {
		return ngayXacNhanTK;
	}

	public void setNgayXacNhanTK(Timestamp ngayXacNhanTK) {
		this.ngayXacNhanTK = ngayXacNhanTK;
	}

	public Timestamp getNgayXacNhanCTSV() {
		return ngayXacNhanCTSV;
	}

	public void setNgayXacNhanCTSV(Timestamp ngayXacNhanCTSV) {
		this.ngayXacNhanCTSV = ngayXacNhanCTSV;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	
	
	@Override
	public String toString() {
		return "DanhGia [maDanhGia=" + maDanhGia + ", maSinhVien=" + maSinhVien
				+ ", maDotDanhGia=" + maDotDanhGia + ", ngayDanhGia="
				+ ngayDanhGia + ", tongDiem=" + tongDiem + ", diemTapTheLop="
				+ diemTapTheLop + ", tinhTrang=" + tinhTrang + ", ghiChu="
				+ ghiChu + ", ngayXacNhanLT=" + ngayXacNhanLT
				+ ", ngayXacNhanGV=" + ngayXacNhanGV + ", ngayXacNhanTK="
				+ ngayXacNhanTK + ", ngayXacNhanCTSV=" + ngayXacNhanCTSV
				+ ", active=" + active + "]";
	}

	// kiet them
	public String ngayDanhGiaToString() {
		return DateUtils.formatDatetime(ngayDanhGia);
	}

}
