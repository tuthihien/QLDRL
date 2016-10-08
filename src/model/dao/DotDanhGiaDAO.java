package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import model.bean.DotDanhGia;

public class DotDanhGiaDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	ArrayList<DotDanhGia> listDotDanhGia = null;
	DotDanhGia dotDanhGia = null;

	public ArrayList<DotDanhGia> getAllDotDanhGia() {
		String sql = "SELECT id_dotdanhgia, ten, ngaybatdau_sv, ngayketthuc_sv, ngaybatdau_lt, ngayketthuc_lt, ngaybatdau_gv, ngayketthuc_gv, ngaybatdau_tk, ngayketthuc_tk, active FROM dotDanhGia_tbl WHERE active = 1 ORDER BY ngaybatdau_sv DESC";		
		listDotDanhGia = new ArrayList<DotDanhGia>();
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dotDanhGia = new DotDanhGia();
				dotDanhGia.setMaDotDanhGia(rs.getInt(1));
				dotDanhGia.setTen(rs.getString(2));
				dotDanhGia.setNgayBatDauSV(rs.getTimestamp(3));
				dotDanhGia.setNgayKetThucSV(rs.getTimestamp(4));
				dotDanhGia.setNgayBatDauLT(rs.getTimestamp(5));
				dotDanhGia.setNgayKetThucLT(rs.getTimestamp(6));
				dotDanhGia.setNgayBatDauGV(rs.getTimestamp(7));
				dotDanhGia.setNgayKetThucGV(rs.getTimestamp(8));
				dotDanhGia.setNgayBatDauTK(rs.getTimestamp(9));
				dotDanhGia.setNgayKetThucTK(rs.getTimestamp(10));
				dotDanhGia.setActive(rs.getBoolean(11));
				
				listDotDanhGia.add(dotDanhGia);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return listDotDanhGia;
	}

	public DotDanhGia getDotDanhGiaTheoMa(int maDotDanhGia) {
		String sql = "SELECT id_dotdanhgia, ten, ngaybatdau_sv, ngayketthuc_sv, ngaybatdau_lt, ngayketthuc_lt, ngaybatdau_gv, ngayketthuc_gv, ngaybatdau_tk, ngayketthuc_tk, active FROM dotDanhGia_tbl WHERE active = 1 AND id_dotdanhgia = ?";		
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, maDotDanhGia);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dotDanhGia = new DotDanhGia();
				dotDanhGia.setMaDotDanhGia(rs.getInt(1));
				dotDanhGia.setTen(rs.getString(2));
				dotDanhGia.setNgayBatDauSV(rs.getTimestamp(3));
				dotDanhGia.setNgayKetThucSV(rs.getTimestamp(4));
				dotDanhGia.setNgayBatDauLT(rs.getTimestamp(5));
				dotDanhGia.setNgayKetThucLT(rs.getTimestamp(6));
				dotDanhGia.setNgayBatDauGV(rs.getTimestamp(7));
				dotDanhGia.setNgayKetThucGV(rs.getTimestamp(8));
				dotDanhGia.setNgayBatDauTK(rs.getTimestamp(9));
				dotDanhGia.setNgayKetThucTK(rs.getTimestamp(10));
				dotDanhGia.setActive(rs.getBoolean(11));
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return dotDanhGia;
	}

	public int insertDotDanhGia(DotDanhGia dotDanhGia) {
		String sql = "INSERT INTO dotdanhgia_tbl(ten, ngaybatdau_sv, ngayketthuc_sv, ngaybatdau_lt, ngayketthuc_lt, ngaybatdau_gv, ngayketthuc_gv, ngaybatdau_tk, ngayketthuc_tk, active) VALUES (?,?,?,?,?,?,?,?,?,?)";		
		int result = 0;
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dotDanhGia.getTen());
			pstmt.setTimestamp(2, dotDanhGia.getNgayBatDauSV());
			pstmt.setTimestamp(3, dotDanhGia.getNgayKetThucSV());
			pstmt.setTimestamp(4, dotDanhGia.getNgayBatDauLT());
			pstmt.setTimestamp(5, dotDanhGia.getNgayKetThucLT());
			pstmt.setTimestamp(6, dotDanhGia.getNgayBatDauGV());
			pstmt.setTimestamp(7, dotDanhGia.getNgayKetThucGV());
			pstmt.setTimestamp(8, dotDanhGia.getNgayBatDauTK());
			pstmt.setTimestamp(9, dotDanhGia.getNgayKetThucTK());
			pstmt.setBoolean(10, dotDanhGia.isActive());
			
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

	public int updateDotDanhGia(DotDanhGia dotDanhGia) {
		String sql = "UPDATE dotdanhgia_tbl SET ten = ?, ngaybatdau_sv = ?, ngayketthuc_sv = ?, ngaybatdau_lt = ?, ngayketthuc_lt = ?, ngaybatdau_gv = ?, ngayketthuc_gv = ?, ngaybatdau_tk = ?, ngayketthuc_tk = ?, active = ? WHERE id_dotdanhgia = ?";		
		int result = 0;
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dotDanhGia.getTen());
			pstmt.setTimestamp(2, dotDanhGia.getNgayBatDauSV());
			pstmt.setTimestamp(3, dotDanhGia.getNgayKetThucSV());
			pstmt.setTimestamp(4, dotDanhGia.getNgayBatDauLT());
			pstmt.setTimestamp(5, dotDanhGia.getNgayKetThucLT());
			pstmt.setTimestamp(6, dotDanhGia.getNgayBatDauGV());
			pstmt.setTimestamp(7, dotDanhGia.getNgayKetThucGV());
			pstmt.setTimestamp(8, dotDanhGia.getNgayBatDauTK());
			pstmt.setTimestamp(9, dotDanhGia.getNgayKetThucTK());
			pstmt.setBoolean(10, dotDanhGia.isActive());
			pstmt.setInt(11, dotDanhGia.getMaDotDanhGia());
			
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

	public int deleteDotDanhGia(int maDotDanhGia) {
		String sql = "UPDATE dotdanhgia_tbl SET active = 0 WHERE id_dotdanhgia = ?";		
		int result = 0;
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, maDotDanhGia);
			
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

	
	public DotDanhGia getDotDanhGiaMoiNhat() {
		String sql = "SELECT id_dotdanhgia, ten, ngaybatdau_sv, ngayketthuc_sv, ngaybatdau_lt, ngayketthuc_lt, ngaybatdau_gv, ngayketthuc_gv, ngaybatdau_tk, ngayketthuc_tk, active FROM dotDanhGia_tbl WHERE active = 1 ORDER BY ngaybatdau_sv DESC";
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dotDanhGia = new DotDanhGia();
				dotDanhGia.setMaDotDanhGia(rs.getInt(1));
				dotDanhGia.setTen(rs.getString(2));
				dotDanhGia.setNgayBatDauSV(rs.getTimestamp(3));
				dotDanhGia.setNgayKetThucSV(rs.getTimestamp(4));
				dotDanhGia.setNgayBatDauLT(rs.getTimestamp(5));
				dotDanhGia.setNgayKetThucLT(rs.getTimestamp(6));
				dotDanhGia.setNgayBatDauGV(rs.getTimestamp(7));
				dotDanhGia.setNgayKetThucGV(rs.getTimestamp(8));
				dotDanhGia.setNgayBatDauTK(rs.getTimestamp(9));
				dotDanhGia.setNgayKetThucTK(rs.getTimestamp(10));
				dotDanhGia.setActive(rs.getBoolean(11));
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return dotDanhGia;
	}
	
	
	public DotDanhGia getDotDanhGiaHienTaiSV(Timestamp thoiGianHienTai) {
		String sql = "SELECT id_dotdanhgia, ten, ngaybatdau_sv, ngayketthuc_sv, ngaybatdau_lt, ngayketthuc_lt, ngaybatdau_gv, ngayketthuc_gv, ngaybatdau_tk, ngayketthuc_tk, active FROM dotDanhGia_tbl "
		+ "WHERE active = 1 AND ngaybatdau_sv <= ? AND ngayketthuc_sv > ?";		
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setTimestamp(1, thoiGianHienTai);
			pstmt.setTimestamp(2, thoiGianHienTai);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dotDanhGia = new DotDanhGia();
				dotDanhGia.setMaDotDanhGia(rs.getInt(1));
				dotDanhGia.setTen(rs.getString(2));
				dotDanhGia.setNgayBatDauSV(rs.getTimestamp(3));
				dotDanhGia.setNgayKetThucSV(rs.getTimestamp(4));
				dotDanhGia.setNgayBatDauLT(rs.getTimestamp(5));
				dotDanhGia.setNgayKetThucLT(rs.getTimestamp(6));
				dotDanhGia.setNgayBatDauGV(rs.getTimestamp(7));
				dotDanhGia.setNgayKetThucGV(rs.getTimestamp(8));
				dotDanhGia.setNgayBatDauTK(rs.getTimestamp(9));
				dotDanhGia.setNgayKetThucTK(rs.getTimestamp(10));
				dotDanhGia.setActive(rs.getBoolean(11));
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return dotDanhGia;
	}

	
		public DotDanhGia getDotDanhGiaHienTaiLT(Timestamp thoiGianHienTai) {
			String sql = "SELECT id_dotdanhgia, ten, ngaybatdau_sv, ngayketthuc_sv, ngaybatdau_lt, ngayketthuc_lt, ngaybatdau_gv, ngayketthuc_gv, ngaybatdau_tk, ngayketthuc_tk, active FROM dotDanhGia_tbl "
			+ "WHERE active = 1 AND ngaybatdau_lt <= ? AND ngayketthuc_lt > ?";		
			
			try {
				con = SQLConnection.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setTimestamp(1, thoiGianHienTai);
				pstmt.setTimestamp(2, thoiGianHienTai);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					dotDanhGia = new DotDanhGia();
					dotDanhGia.setMaDotDanhGia(rs.getInt(1));
					dotDanhGia.setTen(rs.getString(2));
					dotDanhGia.setNgayBatDauSV(rs.getTimestamp(3));
					dotDanhGia.setNgayKetThucSV(rs.getTimestamp(4));
					dotDanhGia.setNgayBatDauLT(rs.getTimestamp(5));
					dotDanhGia.setNgayKetThucLT(rs.getTimestamp(6));
					dotDanhGia.setNgayBatDauGV(rs.getTimestamp(7));
					dotDanhGia.setNgayKetThucGV(rs.getTimestamp(8));
					dotDanhGia.setNgayBatDauTK(rs.getTimestamp(9));
					dotDanhGia.setNgayKetThucTK(rs.getTimestamp(10));
					dotDanhGia.setActive(rs.getBoolean(11));
					
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				SQLConnection.closeConnection(this.con);
				SQLConnection.closePrepareStatement(pstmt);
				SQLConnection.closeResultSet(rs);
			}
			return dotDanhGia;
		}
		

		public DotDanhGia getDotDanhGiaHienTaiGVCN(Timestamp thoiGianHienTai) {
			String sql = "SELECT id_dotdanhgia, ten, ngaybatdau_sv, ngayketthuc_sv, ngaybatdau_lt, ngayketthuc_lt, ngaybatdau_gv, ngayketthuc_gv, ngaybatdau_tk, ngayketthuc_tk, active FROM dotDanhGia_tbl "
				+ "WHERE active = 1 AND ngaybatdau_gv <= ? AND ngayketthuc_gv > ?";		
					
				try {
					con = SQLConnection.getConnection();
					pstmt = con.prepareStatement(sql);
					pstmt.setTimestamp(1, thoiGianHienTai);
					pstmt.setTimestamp(2, thoiGianHienTai);						
					rs = pstmt.executeQuery();
					
					while (rs.next()) {
						dotDanhGia = new DotDanhGia();
						dotDanhGia.setMaDotDanhGia(rs.getInt(1));
						dotDanhGia.setTen(rs.getString(2));
						dotDanhGia.setNgayBatDauSV(rs.getTimestamp(3));
						dotDanhGia.setNgayKetThucSV(rs.getTimestamp(4));
						dotDanhGia.setNgayBatDauLT(rs.getTimestamp(5));
						dotDanhGia.setNgayKetThucLT(rs.getTimestamp(6));
						dotDanhGia.setNgayBatDauGV(rs.getTimestamp(7));
						dotDanhGia.setNgayKetThucGV(rs.getTimestamp(8));
						dotDanhGia.setNgayBatDauTK(rs.getTimestamp(9));
						dotDanhGia.setNgayKetThucTK(rs.getTimestamp(10));
						dotDanhGia.setActive(rs.getBoolean(11));
							
					}

				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					SQLConnection.closeConnection(this.con);
					SQLConnection.closePrepareStatement(pstmt);
					SQLConnection.closeResultSet(rs);
				}
				return dotDanhGia;
			}

		public DotDanhGia getDotDanhGiaHienTaiTK(Timestamp thoiGianHienTai) {
			String sql = "SELECT id_dotdanhgia, ten, ngaybatdau_sv, ngayketthuc_sv, ngaybatdau_lt, ngayketthuc_lt, ngaybatdau_gv, ngayketthuc_gv, ngaybatdau_tk, ngayketthuc_tk, active FROM dotDanhGia_tbl "
				+ "WHERE active = 1 AND ngaybatdau_tk <= ? AND ngayketthuc_tk > ?";		
					
				try {
					con = SQLConnection.getConnection();
					pstmt = con.prepareStatement(sql);
					pstmt.setTimestamp(1, thoiGianHienTai);
					pstmt.setTimestamp(2, thoiGianHienTai);						
					rs = pstmt.executeQuery();
					
					while (rs.next()) {
						dotDanhGia = new DotDanhGia();
						dotDanhGia.setMaDotDanhGia(rs.getInt(1));
						dotDanhGia.setTen(rs.getString(2));
						dotDanhGia.setNgayBatDauSV(rs.getTimestamp(3));
						dotDanhGia.setNgayKetThucSV(rs.getTimestamp(4));
						dotDanhGia.setNgayBatDauLT(rs.getTimestamp(5));
						dotDanhGia.setNgayKetThucLT(rs.getTimestamp(6));
						dotDanhGia.setNgayBatDauGV(rs.getTimestamp(7));
						dotDanhGia.setNgayKetThucGV(rs.getTimestamp(8));
						dotDanhGia.setNgayBatDauTK(rs.getTimestamp(9));
						dotDanhGia.setNgayKetThucTK(rs.getTimestamp(10));
						dotDanhGia.setActive(rs.getBoolean(11));
							
					}

				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					SQLConnection.closeConnection(this.con);
					SQLConnection.closePrepareStatement(pstmt);
					SQLConnection.closeResultSet(rs);
				}
				return dotDanhGia;
			}


	
}
