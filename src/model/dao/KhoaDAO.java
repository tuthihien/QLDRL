package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.Khoa;

public class KhoaDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	ArrayList<Khoa> listKhoa = null;
	Khoa khoa = null;
	
	public ArrayList<Khoa> getAllKhoa() {
		String sql = "SELECT id_khoa, ten, active FROM khoa_tbl WHERE active = 1 AND id_khoa <> 2";		
		listKhoa = new ArrayList<Khoa>();
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				khoa = new Khoa();
				khoa.setMaKhoa(rs.getInt(1));
				khoa.setTen(rs.getString(2));
				khoa.setActive(rs.getBoolean(3));
				
				listKhoa.add(khoa);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return listKhoa;
	}

	public Khoa getKhoaTheoMa(int maKhoa) {
		String sql = "SELECT id_khoa, ten, active FROM khoa_tbl WHERE active = 1 AND id_khoa = ?";		
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, maKhoa);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				khoa = new Khoa();
				khoa.setMaKhoa(rs.getInt(1));
				khoa.setTen(rs.getString(2));	
				khoa.setActive(rs.getBoolean(3));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return khoa;
	}

	public int insertKhoa(Khoa khoa) {
		String sql = "INSERT INTO khoa_tbl(ten, active) VALUES (?,?)";		
		int result = 0;
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, khoa.getTen());
			pstmt.setBoolean(2, khoa.isActive());
			
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

	public int updateKhoa(Khoa khoa) {
		String sql = "UPDATE khoa_tbl SET ten = ?, active = ? WHERE id_khoa = ?";		
		int result = 0;
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, khoa.getTen());
			pstmt.setBoolean(2, khoa.isActive());
			pstmt.setInt(3, khoa.getMaKhoa());
			
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

	public int deleteKhoa(int maKhoa) {
		String sql = "UPDATE khoa_tbl SET active = 0 WHERE id_khoa = ?";		
		int result = 0;
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, maKhoa);
			
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

}
