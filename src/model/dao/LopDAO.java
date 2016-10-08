package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.Lop;

public class LopDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	ArrayList<Lop> listLop = null;
	Lop lop = null;
	
	public ArrayList<Lop> getAllLop() {
		String sql = "SELECT id_lop, id_khoa, ten, id_giangvien, active FROM lop_tbl WHERE active = 1";		
		listLop = new ArrayList<Lop>();
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lop = new Lop();
				lop.setMaLop(rs.getInt(1));
				lop.setMaKhoa(rs.getInt(2));
				lop.setTen(rs.getString(3));
				lop.setMaGiangVien(rs.getInt(4));
				lop.setActive(rs.getBoolean(5));
				
				listLop.add(lop);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return listLop;
	}

	public Lop getLopTheoMa(int maLop) {
		String sql = "SELECT id_lop, id_khoa, ten, id_giangvien, active FROM lop_tbl WHERE active = 1 AND id_lop = ?";		
		listLop = new ArrayList<Lop>();
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, maLop);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lop = new Lop();
				lop.setMaLop(rs.getInt(1));
				lop.setMaKhoa(rs.getInt(2));
				lop.setTen(rs.getString(3));
				lop.setMaGiangVien(rs.getInt(4));
				lop.setActive(rs.getBoolean(5));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return lop;
	}

	public int insertLop(Lop lop) {
		String sql = "INSERT INTO lop_tbl(id_khoa, ten, id_giangvien, active) VALUES (?,?,?,?)";		
		int result = 0;
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, lop.getMaKhoa());
			pstmt.setString(2, lop.getTen());
			pstmt.setInt(3, lop.getMaGiangVien());
			pstmt.setBoolean(4, lop.isActive());
			
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

	public int updateLop(Lop lop) {
		String sql = "UPDATE lop_tbl SET id_khoa = ?, ten = ?, id_giangvien = ?, active = ? WHERE id_lop = ?";		
		int result = 0;
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, lop.getMaKhoa());
			pstmt.setString(2, lop.getTen());
			pstmt.setInt(3, lop.getMaGiangVien());
			pstmt.setBoolean(4, lop.isActive());
			pstmt.setInt(5, lop.getMaLop());
			
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

	public int deleteLop(int maLop) {
		String sql = "UPDATE lop_tbl SET active = 0 WHERE id_lop = ?";		
		int result = 0;
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, maLop);
			
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


	public ArrayList<Lop> getAllLopGVCN(int maGiangVien) {
		String sql = "SELECT id_lop, id_khoa, ten, id_giangvien, active FROM lop_tbl WHERE active = 1 AND id_giangvien = ?";		
		listLop = new ArrayList<Lop>();
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, maGiangVien);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lop = new Lop();
				lop.setMaLop(rs.getInt(1));
				lop.setMaKhoa(rs.getInt(2));
				lop.setTen(rs.getString(3));
				lop.setMaGiangVien(rs.getInt(4));
				lop.setActive(rs.getBoolean(5));
				
				listLop.add(lop);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return listLop;
	}
	

	public ArrayList<Lop> getAllLopByKhoa(int idKhoa) {
		String sql = "SELECT id_lop, id_khoa, ten, id_giangvien, active FROM lop_tbl WHERE active = 1 and id_khoa="+idKhoa;		
		listLop = new ArrayList<Lop>();
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lop = new Lop();
				lop.setMaLop(rs.getInt(1));
				lop.setMaKhoa(rs.getInt(2));
				lop.setTen(rs.getString(3));
				lop.setMaGiangVien(rs.getInt(4));
				lop.setActive(rs.getBoolean(5));
				
				listLop.add(lop);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return listLop;
	}
}
