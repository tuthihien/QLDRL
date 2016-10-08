package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.MucDanhGia;


public class MucDanhGiaDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	ArrayList<MucDanhGia> listMucDanhGia = null;
	MucDanhGia mucDanhGia = null;

	public ArrayList<MucDanhGia> getAllMucDanhGia() {
		String sql = "SELECT id_mucdanhgia, ten, active FROM mucdanhgia_tbl WHERE active = 1";		
		listMucDanhGia = new ArrayList<MucDanhGia>();
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				mucDanhGia = new MucDanhGia();
				mucDanhGia.setMaMucDanhGia(rs.getInt(1));
				mucDanhGia.setTen(rs.getString(2));
				mucDanhGia.setActive(rs.getBoolean(3));
				
				listMucDanhGia.add(mucDanhGia);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return listMucDanhGia;
	}

	public MucDanhGia getMucDanhGiaTheoMa(int maMucDanhGia) {
		String sql = "SELECT id_mucdanhgia, ten, active FROM mucdanhgia_tbl WHERE active = 1 AND id_mucdanhgia = ?";		
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, maMucDanhGia);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				mucDanhGia = new MucDanhGia();
				mucDanhGia.setMaMucDanhGia(rs.getInt(1));
				mucDanhGia.setTen(rs.getString(2));
				mucDanhGia.setActive(rs.getBoolean(3));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return mucDanhGia;
	}

	public int insertMucDanhGia(MucDanhGia mucDanhGia) {
		String sql = "INSERT INTO mucdanhgia_tbl(ten, active) VALUES (?,?)";		
		int result = 0;
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			
		
			pstmt.setString(1, mucDanhGia.getTen());
			pstmt.setBoolean(2, mucDanhGia.isActive());
			
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

	public int updateMucDanhGia(MucDanhGia mucDanhGia) {
		String sql = "UPDATE mucdanhgia_tbl SET ten = ?, active = ? WHERE id_mucdanhgia = ?";		
		int result = 0;
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			
		
			pstmt.setString(1, mucDanhGia.getTen());
			pstmt.setBoolean(2, mucDanhGia.isActive());
			pstmt.setInt(3, mucDanhGia.getMaMucDanhGia());
			
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

	public int deleteMucDanhGia(int maMucDanhGia) {
		String sql = "UPDATE mucdanhgia_tbl SET active = 0 WHERE id_mucdanhgia = ?";		
		int result = 0;
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, maMucDanhGia);
			
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
