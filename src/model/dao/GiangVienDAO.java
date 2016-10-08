package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.GiangVien;

public class GiangVienDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	ArrayList<GiangVien> listGiangVien = null;
	GiangVien giangVien = null;
	
	public ArrayList<GiangVien> getAllGiangVien() {
		String sql = "SELECT id_giangvien, id_khoa, ten, ngaysinh, email, sdt, tendangnhap, matkhau, chucvu, active FROM giangvien_tbl WHERE active = 1";		
		listGiangVien = new ArrayList<GiangVien>();
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				giangVien = new GiangVien();
				giangVien.setMaGiangVien(rs.getInt(1));
				giangVien.setMaKhoa(rs.getInt(2));
				giangVien.setTen(rs.getString(3));
				giangVien.setNgaySinh(rs.getDate(4));
				giangVien.setEmail(rs.getString(5));
				giangVien.setSdt(rs.getString(6));
				giangVien.setTenDangNhap(rs.getString(7));
				giangVien.setMatKhau(rs.getString(8));
				giangVien.setChucVu(rs.getString(9));
				giangVien.setActive(rs.getBoolean(10));
				
				listGiangVien.add(giangVien);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return listGiangVien;

	}

	public GiangVien getGiangVienTheoMa(int maGiangVien) {
		String sql = "SELECT id_giangvien, id_khoa, ten, ngaysinh, email, sdt, tendangnhap, matkhau, chucvu, active FROM giangvien_tbl WHERE active = 1 AND id_giangvien = ?";
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, maGiangVien);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				giangVien = new GiangVien();
				giangVien.setMaGiangVien(rs.getInt(1));
				giangVien.setMaKhoa(rs.getInt(2));
				giangVien.setTen(rs.getString(3));
				giangVien.setNgaySinh(rs.getDate(4));
				giangVien.setEmail(rs.getString(5));
				giangVien.setSdt(rs.getString(6));
				giangVien.setTenDangNhap(rs.getString(7));
				giangVien.setMatKhau(rs.getString(8));
				giangVien.setChucVu(rs.getString(9));
				giangVien.setActive(rs.getBoolean(10));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return giangVien;
	}

	public int insertGiangVien(GiangVien giangVien) {
		String sql = "INSERT INTO giangvien_tbl(id_khoa, ten, ngaysinh, email, sdt, tendangnhap, matkhau, chucvu, active) VALUES (?,?,?,?,?,?,?,?,?)";		
		int result = 0;
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, giangVien.getMaKhoa());
			pstmt.setString(2, giangVien.getTen());
			pstmt.setDate(3, giangVien.getNgaySinh());
			pstmt.setString(4, giangVien.getEmail());
			pstmt.setString(5, giangVien.getSdt());
			pstmt.setString(6, giangVien.getTenDangNhap());
			pstmt.setString(7, giangVien.getMatKhau());
			pstmt.setString(8, giangVien.getChucVu());
			pstmt.setBoolean(9, giangVien.isActive());
			
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

	public int updateGiangVien(GiangVien giangVien) {
		String sql = "UPDATE giangvien_tbl SET id_khoa = ?, ten = ?, ngaysinh = ?, email = ?, sdt = ?, tendangnhap = ?, matkhau = ?, chucvu = ?, active =? WHERE id_giangvien = ?";		
		int result = 0;
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, giangVien.getMaKhoa());
			pstmt.setString(2, giangVien.getTen());
			pstmt.setDate(3, giangVien.getNgaySinh());
			pstmt.setString(4, giangVien.getEmail());
			pstmt.setString(5, giangVien.getSdt());
			pstmt.setString(6, giangVien.getTenDangNhap());
			pstmt.setString(7, giangVien.getMatKhau());
			pstmt.setString(8, giangVien.getChucVu());
			pstmt.setBoolean(9, giangVien.isActive());
			pstmt.setInt(10, giangVien.getMaGiangVien());
			
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

	public int deleteGiangVien(int maGiangVien) {
		String sql = "UPDATE giangvien_tbl SET active = 0 WHERE id_giangvien = ?";		
		int result = 0;
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, maGiangVien);
			
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

	public GiangVien getGiangVienDangNhap(String tenDangNhap, String matKhau) {
		String sql = "SELECT id_giangvien, id_khoa, ten, ngaysinh, email, sdt, tendangnhap, matkhau, chucvu, active FROM giangvien_tbl WHERE active = 1 AND tendangnhap = ? AND matkhau = ?";
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, tenDangNhap);
			pstmt.setString(2, matKhau);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				giangVien = new GiangVien();
				giangVien.setMaGiangVien(rs.getInt(1));
				giangVien.setMaKhoa(rs.getInt(2));
				giangVien.setTen(rs.getString(3));
				giangVien.setNgaySinh(rs.getDate(4));
				giangVien.setEmail(rs.getString(5));
				giangVien.setSdt(rs.getString(6));
				giangVien.setTenDangNhap(rs.getString(7));
				giangVien.setMatKhau(rs.getString(8));
				giangVien.setChucVu(rs.getString(9));
				giangVien.setActive(rs.getBoolean(10));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return giangVien;
	}


	public ArrayList<GiangVien> getGiangVienByKhoa(int idKhoa) {
		String sql = "SELECT id_giangvien, id_khoa, ten, ngaysinh, email, sdt, tendangnhap, matkhau, chucvu, active FROM giangvien_tbl WHERE active = 1 and id_khoa="+idKhoa;		
		listGiangVien = new ArrayList<GiangVien>();
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				giangVien = new GiangVien();
				giangVien.setMaGiangVien(rs.getInt(1));
				giangVien.setMaKhoa(rs.getInt(2));
				giangVien.setTen(rs.getString(3));
				giangVien.setNgaySinh(rs.getDate(4));
				giangVien.setEmail(rs.getString(5));
				giangVien.setSdt(rs.getString(6));
				giangVien.setTenDangNhap(rs.getString(7));
				giangVien.setMatKhau(rs.getString(8));
				giangVien.setChucVu(rs.getString(9));
				giangVien.setActive(rs.getBoolean(10));
				
				listGiangVien.add(giangVien);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return listGiangVien;

	}
	
	public ArrayList<GiangVien> getAllGiangVienByName(String name) {
		String sql = "SELECT id_giangvien, id_khoa, ten, ngaysinh, email, sdt, tendangnhap, matkhau, chucvu, active FROM giangvien_tbl WHERE active = 1 and ten LIKE '%"+name+"%'";		
		listGiangVien = new ArrayList<GiangVien>();
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				giangVien = new GiangVien();
				giangVien.setMaGiangVien(rs.getInt(1));
				giangVien.setMaKhoa(rs.getInt(2));
				giangVien.setTen(rs.getString(3));
				giangVien.setNgaySinh(rs.getDate(4));
				giangVien.setEmail(rs.getString(5));
				giangVien.setSdt(rs.getString(6));
				giangVien.setTenDangNhap(rs.getString(7));
				giangVien.setMatKhau(rs.getString(8));
				giangVien.setChucVu(rs.getString(9));
				giangVien.setActive(rs.getBoolean(10));
				
				listGiangVien.add(giangVien);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return listGiangVien;
	}


	public ArrayList<GiangVien> getGiangVienByKhoaVsTen(int idKhoa, String name) {
		String sql = "SELECT id_giangvien, id_khoa, ten, ngaysinh, email, sdt, tendangnhap, matkhau, chucvu, active FROM giangvien_tbl WHERE active = 1 and id_khoa="+idKhoa+"&&ten LIKE '%"+name+"%'";		
		listGiangVien = new ArrayList<GiangVien>();
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				giangVien = new GiangVien();
				giangVien.setMaGiangVien(rs.getInt(1));
				giangVien.setMaKhoa(rs.getInt(2));
				giangVien.setTen(rs.getString(3));
				giangVien.setNgaySinh(rs.getDate(4));
				giangVien.setEmail(rs.getString(5));
				giangVien.setSdt(rs.getString(6));
				giangVien.setTenDangNhap(rs.getString(7));
				giangVien.setMatKhau(rs.getString(8));
				giangVien.setChucVu(rs.getString(9));
				giangVien.setActive(rs.getBoolean(10));
				
				listGiangVien.add(giangVien);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return listGiangVien;
	}

	public int deleteGiangVienByCheckBox(String[] listIdGiangVien) {
		int result = 0;
		int check=0;
		for(int i=0;i<listIdGiangVien.length;i++){
			String sql = "UPDATE giangvien_tbl SET active = 0 WHERE id_giangvien = ?";		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,Integer.parseInt(listIdGiangVien[i]));
			
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

	public GiangVien getTruongKhoaByChucVuVsKhoa(int idKhoa) {
		String sql = "SELECT id_giangvien, id_khoa, ten, ngaysinh, email, sdt, tendangnhap, matkhau, chucvu, active FROM giangvien_tbl WHERE active = 1 AND id_khoa = ?";
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idKhoa);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				giangVien = new GiangVien();
				giangVien.setMaGiangVien(rs.getInt(1));
				giangVien.setMaKhoa(rs.getInt(2));
				giangVien.setTen(rs.getString(3));
				giangVien.setNgaySinh(rs.getDate(4));
				giangVien.setEmail(rs.getString(5));
				giangVien.setSdt(rs.getString(6));
				giangVien.setTenDangNhap(rs.getString(7));
				giangVien.setMatKhau(rs.getString(8));
				giangVien.setChucVu(rs.getString(9));
				giangVien.setActive(rs.getBoolean(10));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return giangVien;

	}

	public GiangVien getTruongPhongCTSV() {
String sql = "SELECT id_giangvien, id_khoa, ten, ngaysinh, email, sdt, tendangnhap, matkhau, chucvu, active FROM giangvien_tbl WHERE active = 1 AND chucvu= ?";
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,"truongphongCTSV");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				giangVien = new GiangVien();
				giangVien.setMaGiangVien(rs.getInt(1));
				giangVien.setMaKhoa(rs.getInt(2));
				giangVien.setTen(rs.getString(3));
				giangVien.setNgaySinh(rs.getDate(4));
				giangVien.setEmail(rs.getString(5));
				giangVien.setSdt(rs.getString(6));
				giangVien.setTenDangNhap(rs.getString(7));
				giangVien.setMatKhau(rs.getString(8));
				giangVien.setChucVu(rs.getString(9));
				giangVien.setActive(rs.getBoolean(10));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return giangVien;
	}
}
