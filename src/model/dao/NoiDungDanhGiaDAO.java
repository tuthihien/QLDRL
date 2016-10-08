package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.bean.NoiDungDanhGia;

public class NoiDungDanhGiaDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	ArrayList<NoiDungDanhGia> listNoiDungDanhGia = null;
	NoiDungDanhGia noiDungDanhGia = null;

	public ArrayList<NoiDungDanhGia> getAllNoiDungDanhGia() {
		String sql = "SELECT id_noidungdanhgia, id_mucdanhgia, noidung, diemtoida, active FROM noidungdanhgia_tbl WHERE active = 1";		
		listNoiDungDanhGia = new ArrayList<NoiDungDanhGia>();
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				noiDungDanhGia = new NoiDungDanhGia();
				noiDungDanhGia.setMaNoiDungDanhGia(rs.getInt(1));
				noiDungDanhGia.setMaMucDanhGia(rs.getInt(2));
				noiDungDanhGia.setNoiDung(rs.getString(3));
				noiDungDanhGia.setDiemToiDa(rs.getInt(4));
				noiDungDanhGia.setActive(rs.getBoolean(3));
				
				listNoiDungDanhGia.add(noiDungDanhGia);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return listNoiDungDanhGia;
	}
	

	public ArrayList<NoiDungDanhGia> getAllNoiDungDanhGiaTheoMuc(int maMucDanhGia) {
		String sql = "SELECT id_noidungdanhgia, id_mucdanhgia, noidung, diemtoida, active FROM noidungdanhgia_tbl WHERE active = 1 AND id_mucdanhgia = ?";		
		listNoiDungDanhGia = new ArrayList<NoiDungDanhGia>();
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, maMucDanhGia);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				noiDungDanhGia = new NoiDungDanhGia();
				noiDungDanhGia.setMaNoiDungDanhGia(rs.getInt(1));
				noiDungDanhGia.setMaMucDanhGia(rs.getInt(2));
				noiDungDanhGia.setNoiDung(rs.getString(3));
				noiDungDanhGia.setDiemToiDa(rs.getInt(4));
				noiDungDanhGia.setActive(rs.getBoolean(3));
				
				listNoiDungDanhGia.add(noiDungDanhGia);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return listNoiDungDanhGia;
	}

	public NoiDungDanhGia getNoiDungDanhGiaTheoMa(int maNoiDungDanhGia) {
		String sql = "SELECT id_noidungdanhgia, id_mucdanhgia, noidung, diemtoida, active FROM mucdanhgia_tbl WHERE active = 1 AND id_noidungdanhgia = ?";		
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, maNoiDungDanhGia);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				noiDungDanhGia = new NoiDungDanhGia();
				noiDungDanhGia.setMaNoiDungDanhGia(rs.getInt(1));
				noiDungDanhGia.setMaMucDanhGia(rs.getInt(2));
				noiDungDanhGia.setNoiDung(rs.getString(3));
				noiDungDanhGia.setDiemToiDa(rs.getInt(4));
				noiDungDanhGia.setActive(rs.getBoolean(5));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return noiDungDanhGia;
	}

	public int insertNoiDungDanhGia(NoiDungDanhGia noiDungDanhGia) {
		String sql = "INSERT INTO noidungdanhgia_tbl(id_mucdanhgia, noidung, diemtoida, active) VALUES (?,?,?,?)";		
		int result = 0;
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			
		
			pstmt.setInt(1, noiDungDanhGia.getMaMucDanhGia());
			pstmt.setString(2, noiDungDanhGia.getNoiDung());
			pstmt.setInt(3, noiDungDanhGia.getDiemToiDa());
			pstmt.setBoolean(4, noiDungDanhGia.isActive());
			
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

	public int updateNoiDungDanhGia(NoiDungDanhGia noiDungDanhGia) {
		String sql = "UPDATE noidungdanhgia_tbl SET id_mucdanhgia = ?, noidung = ?, diemtoida = ?, active = ? WHERE id_noidungdanhgia = ?";		
		int result = 0;
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			
		
			pstmt.setInt(1, noiDungDanhGia.getMaMucDanhGia());
			pstmt.setString(2, noiDungDanhGia.getNoiDung());
			pstmt.setInt(3, noiDungDanhGia.getDiemToiDa());
			pstmt.setBoolean(4, noiDungDanhGia.isActive());
			pstmt.setInt(5, noiDungDanhGia.getMaNoiDungDanhGia());
			
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

	public int deleteNoiDungDanhGia(int maNoiDungDanhGia) {
		String sql = "UPDATE noidungdanhgia_tbl SET active = 0 WHERE id_noidungdanhgia = ?";		
		int result = 0;
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, maNoiDungDanhGia);
			
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

	//dung trong SinhVienCapNhatDanhGiaServlet
	public ArrayList<NoiDungDanhGia> getAllNoiDungDanhGiaTheoMucCapNhat(
			int maMucDanhGia, int maDanhGia) {
		String sql = "SELECT noidungdanhgia_tbl.id_noidungdanhgia, noidungdanhgia_tbl.id_mucdanhgia, noidungdanhgia_tbl.noidung, noidungdanhgia_tbl.diemtoida, noidungdanhgia_tbl.active "
				+ " FROM noidungdanhgia_tbl INNER JOIN chitietdanhgia_tbl ON noidungdanhgia_tbl.id_noidungdanhgia = chitietdanhgia_tbl.id_noidungdanhgia"
				+ " INNER JOIN mucdanhgia_tbl ON noidungdanhgia_tbl.id_mucdanhgia = mucdanhgia_tbl.id_mucdanhgia"
				+ " WHERE chitietdanhgia_tbl.id_danhgia = ? AND mucdanhgia_tbl.id_mucdanhgia = ?";		
		listNoiDungDanhGia = new ArrayList<NoiDungDanhGia>();
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, maDanhGia);
			pstmt.setInt(2, maMucDanhGia);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				noiDungDanhGia = new NoiDungDanhGia();
				noiDungDanhGia.setMaNoiDungDanhGia(rs.getInt(1));
				noiDungDanhGia.setMaMucDanhGia(rs.getInt(2));
				noiDungDanhGia.setNoiDung(rs.getString(3));
				noiDungDanhGia.setDiemToiDa(rs.getInt(4));
				noiDungDanhGia.setActive(rs.getBoolean(3));
				
				listNoiDungDanhGia.add(noiDungDanhGia);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return listNoiDungDanhGia;
	}

}
