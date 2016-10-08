package model.bean;

public class MucDanhGia {
	private int maMucDanhGia;
	private String ten;
	private boolean active;
	
	public MucDanhGia() {
		super();
	}
	public MucDanhGia(int maMucDanhGia, String ten) {
		super();
		this.maMucDanhGia = maMucDanhGia;
		this.ten = ten;
		this.active = true;
	}
	public MucDanhGia(String ten) {
		super();
		this.ten = ten;
		this.active = true;
	}
	public int getMaMucDanhGia() {
		return maMucDanhGia;
	}
	
	public void setMaMucDanhGia(int maMucDanhGia) {
		this.maMucDanhGia = maMucDanhGia;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "MucDanhGia [maMucDanhGia=" + maMucDanhGia + ", ten=" + ten
				+ ", active=" + active + "]";
	}
	
	
}
