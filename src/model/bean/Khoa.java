package model.bean;

public class Khoa {
	private int maKhoa;
	private String ten;
	private boolean active;
	
	public Khoa() {
		super();
	}

	public Khoa(int maKhoa, String ten) {
		super();
		this.maKhoa = maKhoa;
		this.ten = ten;
		this.active = true;
	}

	public Khoa(String ten) {
		super();
		this.ten = ten;
		this.active = true;
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Khoa [maKhoa=" + maKhoa + ", ten=" + ten + ", active=" + active + "]";
	}	
	
}
