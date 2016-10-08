package model.bean;

public class NoiDungDanhGia {
	private int maNoiDungDanhGia;
	private int maMucDanhGia;
	private String noiDung;
	private int diemToiDa;
	private boolean active;
	
	public NoiDungDanhGia() {
		super();
	}

	public NoiDungDanhGia(int maNoiDungDanhGia, int maMucDanhGia,
			String noiDung, int diemToiDa) {
		super();
		this.maNoiDungDanhGia = maNoiDungDanhGia;
		this.maMucDanhGia = maMucDanhGia;
		this.noiDung = noiDung;
		this.diemToiDa = diemToiDa;
		this.active = true;
	}

	public NoiDungDanhGia(int maMucDanhGia, String noiDung, int diemToiDa) {
		super();
		this.maMucDanhGia = maMucDanhGia;
		this.noiDung = noiDung;
		this.diemToiDa = diemToiDa;
		this.active = true;
	}

	public int getMaNoiDungDanhGia() {
		return maNoiDungDanhGia;
	}

	public void setMaNoiDungDanhGia(int maNoiDungDanhGia) {
		this.maNoiDungDanhGia = maNoiDungDanhGia;
	}
	
	public int getMaMucDanhGia() {
		return maMucDanhGia;
	}

	public void setMaMucDanhGia(int maMucDanhGia) {
		this.maMucDanhGia = maMucDanhGia;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public int getDiemToiDa() {
		return diemToiDa;
	}

	public void setDiemToiDa(int diemToiDa) {
		this.diemToiDa = diemToiDa;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "NoiDungDanhGia [maNoiDungDanhGia=" + maNoiDungDanhGia
				+ ", maMucDanhGia=" + maMucDanhGia + ", noiDung=" + noiDung
				+ ", diemToiDa=" + diemToiDa + ", active=" + active + "]";
	}
	
	
}
