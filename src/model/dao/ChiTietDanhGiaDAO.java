package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.ChiTietDanhGia;

public class ChiTietDanhGiaDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	ArrayList<ChiTietDanhGia> listChiTietDanhGia = null;
	ChiTietDanhGia chiTietDanhGia = null;
	
	public ArrayList<ChiTietDanhGia> getAllChiTietDanhGia() {
		String sql = "SELECT id_chitietdanhgia, id_noidungdanhgia, id_danhgia, id_sinhvien, diemdanhgia, diemtapthelop, active FROM chitietdanhgia_tbl WHERE active = 1";		
		listChiTietDanhGia = new ArrayList<ChiTietDanhGia>();
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				chiTietDanhGia = new ChiTietDanhGia();
				chiTietDanhGia.setMaChiTietDanhGia(rs.getInt(1));
				chiTietDanhGia.setMaNoiDungDanhGia(rs.getInt(2));
				chiTietDanhGia.setMaDanhGia(rs.getInt(3));
				chiTietDanhGia.setMaSinhVien(rs.getInt(4));
				chiTietDanhGia.setDiemDanhGia(rs.getInt(5));
				chiTietDanhGia.setDiemTapTheLop(rs.getInt(6));
				chiTietDanhGia.setActive(rs.getBoolean(7));
				
				listChiTietDanhGia.add(chiTietDanhGia);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return listChiTietDanhGia;
	}

	public ChiTietDanhGia getChiTietDanhGiaTheoMa(int maChiTietDanhGia) {
		String sql = "SELECT id_chitietdanhgia, id_noidungdanhgia, id_danhgia, id_sinhvien, diemdanhgia, diemtapthelop, active FROM chitietdanhgia_tbl WHERE active = 1 AND id_chitietdanhgia = ?";	
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, maChiTietDanhGia);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				chiTietDanhGia = new ChiTietDanhGia();
				chiTietDanhGia.setMaChiTietDanhGia(rs.getInt(1));
				chiTietDanhGia.setMaNoiDungDanhGia(rs.getInt(2));
				chiTietDanhGia.setMaDanhGia(rs.getInt(3));
				chiTietDanhGia.setMaSinhVien(rs.getInt(4));
				chiTietDanhGia.setDiemDanhGia(rs.getInt(5));
				chiTietDanhGia.setDiemTapTheLop(rs.getInt(6));
				chiTietDanhGia.setActive(rs.getBoolean(7));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return chiTietDanhGia;
	}

	public int insertChiTietDanhGia(ChiTietDanhGia chiTietDanhGia) {
		String sql = "INSERT INTO chitietdanhgia_tbl(id_noidungdanhgia, id_danhgia, id_sinhvien, diemdanhgia, diemtapthelop, active) VALUES (?,?,?,?,?,?)";		
		int result = 0;
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			
			
			pstmt.setInt(1, chiTietDanhGia.getMaNoiDungDanhGia());
			pstmt.setInt(2, chiTietDanhGia.getMaDanhGia());
			pstmt.setInt(3, chiTietDanhGia.getMaSinhVien());
			pstmt.setInt(4, chiTietDanhGia.getDiemDanhGia());
			pstmt.setInt(5, chiTietDanhGia.getDiemTapTheLop());
			pstmt.setBoolean(6, chiTietDanhGia.isActive());
			
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
	
	
	public int insertListChiTietDanhGia(ArrayList<ChiTietDanhGia> listChiTietDanhGia) {
		String sql = "INSERT INTO chitietdanhgia_tbl(id_noidungdanhgia, id_danhgia, id_sinhvien, diemdanhgia, diemtapthelop, active) VALUES (?,?,?,?,?,?)";		
		int result = 0;
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			
			for (int i = 0; i < listChiTietDanhGia.size(); i++) {
				pstmt.setInt(1, listChiTietDanhGia.get(i).getMaNoiDungDanhGia());
				pstmt.setInt(2, listChiTietDanhGia.get(i).getMaDanhGia());
				pstmt.setInt(3, listChiTietDanhGia.get(i).getMaSinhVien());
				pstmt.setInt(4, listChiTietDanhGia.get(i).getDiemDanhGia());
				pstmt.setInt(5, listChiTietDanhGia.get(i).getDiemTapTheLop());
				pstmt.setBoolean(6, listChiTietDanhGia.get(i).isActive());
			
				result += pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return result;
	}

	public int updateChiTietDanhGia(ChiTietDanhGia chiTietDanhGia) {
		String sql = "UPDATE chitietdanhgia_tbl SET id_noidungdanhgia = ?, id_danhgia = ?, id_sinhvien = ?, diemdanhgia = ?, diemtapthelop = ?, active = ? WHERE id_chitietdanhgia";		
		int result = 0;
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			
			
			pstmt.setInt(1, chiTietDanhGia.getMaNoiDungDanhGia());
			pstmt.setInt(2, chiTietDanhGia.getMaDanhGia());
			pstmt.setInt(3, chiTietDanhGia.getMaSinhVien());
			pstmt.setInt(4, chiTietDanhGia.getDiemDanhGia());
			pstmt.setInt(5, chiTietDanhGia.getDiemTapTheLop());
			pstmt.setBoolean(6, chiTietDanhGia.isActive());
			pstmt.setInt(7, chiTietDanhGia.getMaChiTietDanhGia());
			
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

	public int deleteChiTietDanhGia(int maChiTietDanhGia) {
		String sql = "UPDATE chitietdanhgia_tbl SET active = 0 WHERE id_chitietdanhgia = ?";		
		int result = 0;
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, maChiTietDanhGia);
			
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

	public int deleteChiTietDanhGiaTheoMaDanhGia(int maDanhGia) {
		String sql = "DELETE FROM chitietdanhgia_tbl WHERE id_danhgia = ?";		
		int result = 0;
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, maDanhGia);
			
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

	public ArrayList<ChiTietDanhGia> getAllChiTietDanhGiaTheoMucCapNhat(
			int maDanhGia, int maMucDanhGia) {
		String sql = "SELECT chitietdanhgia_tbl.id_chitietdanhgia, chitietdanhgia_tbl.id_noidungdanhgia, chitietdanhgia_tbl.id_danhgia, chitietdanhgia_tbl.id_sinhvien, "
				+ "chitietdanhgia_tbl.diemdanhgia, chitietdanhgia_tbl.diemtapthelop, chitietdanhgia_tbl.active "
				+ "FROM chitietdanhgia_tbl INNER JOIN noidungdanhgia_tbl ON chitietdanhgia_tbl.id_noidungdanhgia = noidungdanhgia_tbl.id_noidungdanhgia "
				+ "WHERE noidungdanhgia_tbl.id_mucdanhgia = ? AND chitietdanhgia_tbl.id_danhgia = ? AND chitietdanhgia_tbl.active = 1";		
		listChiTietDanhGia = new ArrayList<ChiTietDanhGia>();
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, maMucDanhGia);
			pstmt.setInt(2, maDanhGia);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				chiTietDanhGia = new ChiTietDanhGia();
				chiTietDanhGia.setMaChiTietDanhGia(rs.getInt(1));
				chiTietDanhGia.setMaNoiDungDanhGia(rs.getInt(2));
				chiTietDanhGia.setMaDanhGia(rs.getInt(3));
				chiTietDanhGia.setMaSinhVien(rs.getInt(4));
				chiTietDanhGia.setDiemDanhGia(rs.getInt(5));
				chiTietDanhGia.setDiemTapTheLop(rs.getInt(6));
				chiTietDanhGia.setActive(rs.getBoolean(7));
				
				listChiTietDanhGia.add(chiTietDanhGia);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return listChiTietDanhGia;
	}
}
