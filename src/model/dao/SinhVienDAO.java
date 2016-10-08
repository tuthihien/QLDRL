package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.DanhGia;
import model.bean.SinhVien;

public class SinhVienDAO {
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	ArrayList<SinhVien> listSinhVien = null;
	SinhVien sinhVien = null;
	
	public ArrayList<SinhVien> getAllSinhVien() {
		String sql = "SELECT id_sinhvien, mssv, ten, ngaysinh, id_lop, tendangnhap, matkhau, email, sdt, gioitinh, chucvu, diemhocbong, active FROM sinhvien_tbl WHERE active = 1";		
		listSinhVien = new ArrayList<SinhVien>();
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sinhVien = new SinhVien();
				sinhVien.setMaSinhVien(rs.getInt(1));
				sinhVien.setMssv(rs.getString(2));
				sinhVien.setTen(rs.getString(3));
				sinhVien.setNgaySinh(rs.getDate(4));
				sinhVien.setMaLop(rs.getInt(5));
				sinhVien.setTenDangNhap(rs.getString(6));
				sinhVien.setMatKhau(rs.getString(7));
				sinhVien.setEmail(rs.getString(8));
				sinhVien.setSdt(rs.getString(9));
				sinhVien.setGioiTinh(rs.getString(10));
				sinhVien.setChucVu(rs.getString(11));
				sinhVien.setDiemHocBong(rs.getInt(12));
				sinhVien.setActive(rs.getBoolean(13));
				
				listSinhVien.add(sinhVien);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return listSinhVien;
	}

	public SinhVien getSinhVienTheoMa(int maSinhVien) {
		String sql = "SELECT id_sinhvien, mssv, ten, ngaysinh, id_lop, tendangnhap, matkhau, email, sdt, gioitinh, chucvu, diemhocbong, active FROM sinhvien_tbl WHERE active = 1 AND id_sinhvien = ?";		
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, maSinhVien);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sinhVien = new SinhVien();
				sinhVien.setMaSinhVien(rs.getInt(1));
				sinhVien.setMssv(rs.getString(2));
				sinhVien.setTen(rs.getString(3));
				sinhVien.setNgaySinh(rs.getDate(4));
				sinhVien.setMaLop(rs.getInt(5));
				sinhVien.setTenDangNhap(rs.getString(6));
				sinhVien.setMatKhau(rs.getString(7));
				sinhVien.setEmail(rs.getString(8));
				sinhVien.setSdt(rs.getString(9));
				sinhVien.setGioiTinh(rs.getString(10));
				sinhVien.setChucVu(rs.getString(11));
				sinhVien.setDiemHocBong(rs.getInt(12));
				sinhVien.setActive(rs.getBoolean(13));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return sinhVien;
	}

	public int insertSinhVien(SinhVien sinhVien) {
		String sql = "INSERT INTO sinhvien_tbl(mssv, ten, ngaysinh, id_lop, tendangnhap, matkhau, email, sdt, gioitinh, chucvu, diemhocbong, active) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";		
		int result = 0;
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, sinhVien.getMssv());
			pstmt.setString(2, sinhVien.getTen());
			pstmt.setDate(3, sinhVien.getNgaySinh());
			pstmt.setInt(4, sinhVien.getMaLop());
			pstmt.setString(5, sinhVien.getTenDangNhap());
			pstmt.setString(6, sinhVien.getMatKhau());
			pstmt.setString(7, sinhVien.getEmail());
			pstmt.setString(8, sinhVien.getSdt());
			pstmt.setString(9, sinhVien.getGioiTinh());
			pstmt.setString(10, sinhVien.getChucVu());
			pstmt.setFloat(11, sinhVien.getDiemHocBong());
			pstmt.setBoolean(12, sinhVien.isActive());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return result;
	}

	public int updateSinhVien(SinhVien sinhVien) {
		String sql = "UPDATE sinhvien_tbl SET mssv = ?, ten = ?, ngaysinh = ?, id_lop = ?, tendangnhap = ?, matkhau = ?, email = ?, sdt = ?, gioitinh = ?, chucvu = ?, active = ? WHERE id_sinhvien = ?";		
		int result = 0;
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, sinhVien.getMssv());
			pstmt.setString(2, sinhVien.getTen());
			pstmt.setDate(3, sinhVien.getNgaySinh());
			pstmt.setInt(4, sinhVien.getMaLop());
			pstmt.setString(5, sinhVien.getTenDangNhap());
			pstmt.setString(6, sinhVien.getMatKhau());
			pstmt.setString(7, sinhVien.getEmail());
			pstmt.setString(8, sinhVien.getSdt());
			pstmt.setString(9, sinhVien.getGioiTinh());
			pstmt.setString(10, sinhVien.getChucVu());
			pstmt.setBoolean(11, sinhVien.isActive());
			pstmt.setInt(12, sinhVien.getMaSinhVien());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return result;
	}

	public int deleteSinhVien(int maSinhVien) {
		String sql = "UPDATE sinhvien_tbl SET active = 0 WHERE id_sinhvien = ?";		
		int result = 0;
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, maSinhVien);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return result;
	}


	public SinhVien getSinhVienDangNhap(String tendangnhap, String matKhau) {
		String sql = "SELECT id_sinhvien, mssv, ten, ngaysinh, id_lop, tendangnhap, matkhau, email, sdt, gioitinh, chucvu, diemhocbong, active FROM sinhvien_tbl WHERE active = 1 AND mssv = ? AND matkhau = ?";		
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, tendangnhap);
			pstmt.setString(2, matKhau);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sinhVien = new SinhVien();
				sinhVien.setMaSinhVien(rs.getInt(1));
				sinhVien.setMssv(rs.getString(2));
				sinhVien.setTen(rs.getString(3));
				sinhVien.setNgaySinh(rs.getDate(4));
				sinhVien.setMaLop(rs.getInt(5));
				sinhVien.setTenDangNhap(rs.getString(6));
				sinhVien.setMatKhau(rs.getString(7));
				sinhVien.setEmail(rs.getString(8));
				sinhVien.setSdt(rs.getString(9));
				sinhVien.setGioiTinh(rs.getString(10));
				sinhVien.setChucVu(rs.getString(11));
				sinhVien.setDiemHocBong(rs.getInt(12));
				sinhVien.setActive(rs.getBoolean(13));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return sinhVien;
		
	}
	

	public ArrayList<SinhVien> getListSinhVienTuListDanhGia (ArrayList<DanhGia> listDanhGia) {
		String sql;
		listSinhVien = new ArrayList<SinhVien>();
		
		try {
			con = SQLConnection.getConnection();
			sql = "SELECT id_sinhvien, mssv, ten, ngaysinh, id_lop, tendangnhap, matkhau, email, sdt, gioitinh, chucvu, diemhocbong, active FROM sinhvien_tbl WHERE active = 1 AND id_sinhvien = ?";		
			pstmt = con.prepareStatement(sql);
			for (DanhGia danhGia : listDanhGia) {
				pstmt.setInt(1, danhGia.getMaSinhVien());
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					sinhVien = new SinhVien();
					sinhVien.setMaSinhVien(rs.getInt(1));
					sinhVien.setMssv(rs.getString(2));
					sinhVien.setTen(rs.getString(3));
					sinhVien.setNgaySinh(rs.getDate(4));
					sinhVien.setMaLop(rs.getInt(5));
					sinhVien.setTenDangNhap(rs.getString(6));
					sinhVien.setMatKhau(rs.getString(7));
					sinhVien.setEmail(rs.getString(8));
					sinhVien.setSdt(rs.getString(9));
					sinhVien.setGioiTinh(rs.getString(10));
					sinhVien.setChucVu(rs.getString(11));
					sinhVien.setDiemHocBong(rs.getInt(12));
					sinhVien.setActive(rs.getBoolean(13));
					
					listSinhVien.add(sinhVien);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return listSinhVien;

	}

	public SinhVien getSinhVienById(int idSV) {
		String sql = "SELECT id_sinhvien, mssv, ten, ngaysinh, id_lop, tendangnhap, matkhau, email, sdt, gioitinh, chucvu, diemhocbong, active FROM sinhvien_tbl WHERE active = 1 AND id_sinhvien = ?";		
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idSV);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sinhVien = new SinhVien();
				sinhVien.setMaSinhVien(rs.getInt(1));
				sinhVien.setMssv(rs.getString(2));
				sinhVien.setTen(rs.getString(3));
				sinhVien.setNgaySinh(rs.getDate(4));
				sinhVien.setMaLop(rs.getInt(5));
				sinhVien.setTenDangNhap(rs.getString(6));
				sinhVien.setMatKhau(rs.getString(7));
				sinhVien.setEmail(rs.getString(8));
				sinhVien.setSdt(rs.getString(9));
				sinhVien.setGioiTinh(rs.getString(10));
				sinhVien.setChucVu(rs.getString(11));
				sinhVien.setDiemHocBong(rs.getInt(12));
				sinhVien.setActive(rs.getBoolean(13));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return sinhVien;
	
	}
//(sinh vien xac nhan by lop)
	public ArrayList<SinhVien> getAllSinhVienXacNhanByLop(int idLop) {
		String sql = "SELECT sinhvien_tbl.id_sinhvien, sinhvien_tbl.mssv, sinhvien_tbl.ten, sinhvien_tbl.ngaysinh, sinhvien_tbl.id_lop, sinhvien_tbl.tendangnhap, sinhvien_tbl.matkhau, sinhvien_tbl.email, sinhvien_tbl.sdt,sinhvien_tbl.gioitinh, sinhvien_tbl.chucvu, sinhvien_tbl.diemhocbong, sinhvien_tbl.active FROM sinhvien_tbl INNER JOIN danhgia_tbl ON sinhvien_tbl.id_sinhvien = danhgia_tbl.id_sinhvien WHERE danhgia_tbl.active = 1 and sinhvien_tbl.active=1 and sinhvien_tbl.id_lop="+idLop+" ORDER BY mssv ASC ";		
		listSinhVien = new ArrayList<SinhVien>();
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sinhVien = new SinhVien();
				sinhVien.setMaSinhVien(rs.getInt(1));
				sinhVien.setMssv(rs.getString(2));
				sinhVien.setTen(rs.getString(3));
				sinhVien.setNgaySinh(rs.getDate(4));
				sinhVien.setMaLop(rs.getInt(5));
				sinhVien.setTenDangNhap(rs.getString(6));
				sinhVien.setMatKhau(rs.getString(7));
				sinhVien.setEmail(rs.getString(8));
				sinhVien.setSdt(rs.getString(9));
				sinhVien.setGioiTinh(rs.getString(10));
				sinhVien.setChucVu(rs.getString(11));
				sinhVien.setDiemHocBong(rs.getInt(12));
				sinhVien.setActive(rs.getBoolean(13));
				
				listSinhVien.add(sinhVien);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return listSinhVien;
	}
	
	
	// (SinhVienXacNhanByLopVsTen)
	public ArrayList<SinhVien> getAllSinhVienXacNhanByLopVsTen(int idLop, String name) {
		String sql = "SELECT sinhvien_tbl.id_sinhvien, sinhvien_tbl.mssv, sinhvien_tbl.ten, sinhvien_tbl.ngaysinh, sinhvien_tbl.id_lop, sinhvien_tbl.tendangnhap, sinhvien_tbl.matkhau, sinhvien_tbl.email, sinhvien_tbl.sdt,sinhvien_tbl.gioitinh, sinhvien_tbl.chucvu, sinhvien_tbl.diemhocbong, sinhvien_tbl.active FROM sinhvien_tbl INNER JOIN danhgia_tbl ON sinhvien_tbl.id_sinhvien = danhgia_tbl.id_sinhvien WHERE danhgia_tbl.active = 1 and sinhvien_tbl.active=1 and sinhvien_tbl.id_lop="+idLop+" and sinhvien_tbl.ten LIKE '%"+name+"%' ORDER BY mssv ASC ";		
		listSinhVien = new ArrayList<SinhVien>();
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sinhVien = new SinhVien();
				sinhVien.setMaSinhVien(rs.getInt(1));
				sinhVien.setMssv(rs.getString(2));
				sinhVien.setTen(rs.getString(3));
				sinhVien.setNgaySinh(rs.getDate(4));
				sinhVien.setMaLop(rs.getInt(5));
				sinhVien.setTenDangNhap(rs.getString(6));
				sinhVien.setMatKhau(rs.getString(7));
				sinhVien.setEmail(rs.getString(8));
				sinhVien.setSdt(rs.getString(9));
				sinhVien.setGioiTinh(rs.getString(10));
				sinhVien.setChucVu(rs.getString(11));
				sinhVien.setDiemHocBong(rs.getInt(12));
				sinhVien.setActive(rs.getBoolean(13));
				
				listSinhVien.add(sinhVien);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return listSinhVien;
	}
	
	
	public ArrayList<SinhVien> getAllSinhVienXacNhanByName(String name) {
		String sql = "SELECT sinhvien_tbl.id_sinhvien, sinhvien_tbl.mssv, sinhvien_tbl.ten, sinhvien_tbl.ngaysinh, sinhvien_tbl.id_lop, sinhvien_tbl.tendangnhap, sinhvien_tbl.matkhau, sinhvien_tbl.email, sinhvien_tbl.sdt,sinhvien_tbl.gioitinh, sinhvien_tbl.chucvu, sinhvien_tbl.diemhocbong, sinhvien_tbl.active FROM sinhvien_tbl INNER JOIN danhgia_tbl ON sinhvien_tbl.id_sinhvien = danhgia_tbl.id_sinhvien WHERE danhgia_tbl.active = 1 and sinhvien_tbl.active=1 and sinhvien_tbl.ten LIKE '%"+name+"%' ORDER BY mssv ASC ";		
		listSinhVien = new ArrayList<SinhVien>();
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sinhVien = new SinhVien();
				sinhVien.setMaSinhVien(rs.getInt(1));
				sinhVien.setMssv(rs.getString(2));
				sinhVien.setTen(rs.getString(3));
				sinhVien.setNgaySinh(rs.getDate(4));
				sinhVien.setMaLop(rs.getInt(5));
				sinhVien.setTenDangNhap(rs.getString(6));
				sinhVien.setMatKhau(rs.getString(7));
				sinhVien.setEmail(rs.getString(8));
				sinhVien.setSdt(rs.getString(9));
				sinhVien.setGioiTinh(rs.getString(10));
				sinhVien.setChucVu(rs.getString(11));
				sinhVien.setDiemHocBong(rs.getInt(12));
				sinhVien.setActive(rs.getBoolean(13));
				
				listSinhVien.add(sinhVien);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return listSinhVien;
	}
	
	
	public ArrayList<SinhVien> getAllSinhViebByLop(String idLop) {
		String sql = "SELECT id_sinhvien, mssv, ten, ngaysinh, id_lop, tendangnhap, matkhau, email, sdt, gioitinh, chucvu, diemhocbong, active FROM sinhvien_tbl WHERE active = 1 and id_lop="+idLop;		
		listSinhVien = new ArrayList<SinhVien>();
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sinhVien = new SinhVien();
				sinhVien.setMaSinhVien(rs.getInt(1));
				sinhVien.setMssv(rs.getString(2));
				sinhVien.setTen(rs.getString(3));
				sinhVien.setNgaySinh(rs.getDate(4));
				sinhVien.setMaLop(rs.getInt(5));
				sinhVien.setTenDangNhap(rs.getString(6));
				sinhVien.setMatKhau(rs.getString(7));
				sinhVien.setEmail(rs.getString(8));
				sinhVien.setSdt(rs.getString(9));
				sinhVien.setGioiTinh(rs.getString(10));
				sinhVien.setChucVu(rs.getString(11));
				sinhVien.setDiemHocBong(rs.getInt(12));
				sinhVien.setActive(rs.getBoolean(13));
				
				listSinhVien.add(sinhVien);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return listSinhVien;
	}
// 
	public SinhVien getLopTruongByLop(int idLop) {
String sql = "SELECT id_sinhvien, mssv, ten, ngaysinh, id_lop, tendangnhap, matkhau, email, sdt, gioitinh, chucvu, diemhocbong, active FROM sinhvien_tbl WHERE active = 1 AND id_lop = ?";		
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idLop);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sinhVien = new SinhVien();
				sinhVien.setMaSinhVien(rs.getInt(1));
				sinhVien.setMssv(rs.getString(2));
				sinhVien.setTen(rs.getString(3));
				sinhVien.setNgaySinh(rs.getDate(4));
				sinhVien.setMaLop(rs.getInt(5));
				sinhVien.setTenDangNhap(rs.getString(6));
				sinhVien.setMatKhau(rs.getString(7));
				sinhVien.setEmail(rs.getString(8));
				sinhVien.setSdt(rs.getString(9));
				sinhVien.setGioiTinh(rs.getString(10));
				sinhVien.setChucVu(rs.getString(11));
				sinhVien.setDiemHocBong(rs.getInt(12));
				sinhVien.setActive(rs.getBoolean(13));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return sinhVien;
	}

	public int deleteSinhVienByCheckBox(String[] listIdSinhVien) {
		int result = 0;
		int check=0;
		for(int i=0;i<listIdSinhVien.length;i++){
			String sql = "UPDATE sinhvien_tbl SET active = 0 WHERE id_sinhvien = ?";		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,Integer.parseInt(listIdSinhVien[i]));
			
			result = pstmt.executeUpdate();
			if(result==0){
				check++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		}
		if(check>0){
			result=0;
		}
		if(check==0){
			result=1;
		}
		return result;
	}
	//
	public ArrayList<SinhVien> getAllSinhVienTheoLop(int idLop) {
		String sql = "SELECT sinhvien_tbl.id_sinhvien, sinhvien_tbl.mssv, sinhvien_tbl.ten, sinhvien_tbl.ngaysinh, sinhvien_tbl.id_lop, sinhvien_tbl.tendangnhap, sinhvien_tbl.matkhau, sinhvien_tbl.email, sinhvien_tbl.sdt,sinhvien_tbl.gioitinh, sinhvien_tbl.chucvu, sinhvien_tbl.diemhocbong, sinhvien_tbl.active FROM sinhvien_tbl INNER JOIN danhgia_tbl ON sinhvien_tbl.id_sinhvien = danhgia_tbl.id_sinhvien WHERE danhgia_tbl.tinhtrang='hoanthanh' and sinhvien_tbl.id_lop="+idLop+" ORDER BY mssv ASC ";		
		listSinhVien = new ArrayList<SinhVien>();
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sinhVien = new SinhVien();
				sinhVien.setMaSinhVien(rs.getInt(1));
				sinhVien.setMssv(rs.getString(2));
				sinhVien.setTen(rs.getString(3));
				sinhVien.setNgaySinh(rs.getDate(4));
				sinhVien.setMaLop(rs.getInt(5));
				sinhVien.setTenDangNhap(rs.getString(6));
				sinhVien.setMatKhau(rs.getString(7));
				sinhVien.setEmail(rs.getString(8));
				sinhVien.setSdt(rs.getString(9));
				sinhVien.setGioiTinh(rs.getString(10));
				sinhVien.setChucVu(rs.getString(11));
				sinhVien.setDiemHocBong(rs.getInt(12));
				sinhVien.setActive(rs.getBoolean(13));
				
				listSinhVien.add(sinhVien);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return listSinhVien;
	}
}
