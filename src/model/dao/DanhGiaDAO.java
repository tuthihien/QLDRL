package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import utils.DateUtils;
import model.bean.DanhGia;

public class DanhGiaDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	ArrayList<DanhGia> listDanhGia = null;
	DanhGia danhGia = null;
	
	public ArrayList<DanhGia> getAllDanhGia() {
		String sql = "SELECT id_danhgia, id_sinhvien, id_dotdanhgia, ngaydanhgia, tongdiem, diemtapthelop, tinhtrang, ghichu, ngayltxacnhan, ngaygvxacnhan, ngaytkxacnhan, ngayctsvxacnhan, active FROM danhgia_tbl WHERE active = 1";		
		listDanhGia = new ArrayList<DanhGia>();
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				danhGia = new DanhGia();
				danhGia.setMaDanhGia(rs.getInt(1));
				danhGia.setMaSinhVien(rs.getInt(2));
				danhGia.setMaDotDanhGia(rs.getInt(3));
				danhGia.setNgayDanhGia(rs.getTimestamp(4));
				danhGia.setTongDiem(rs.getInt(5));
				danhGia.setDiemTapTheLop(rs.getInt(6));
				danhGia.setTinhTrang(rs.getString(7));
				danhGia.setGhiChu(rs.getString(8));
				danhGia.setNgayXacNhanLT(rs.getTimestamp(9));
				danhGia.setNgayXacNhanGV(rs.getTimestamp(10));
				danhGia.setNgayXacNhanTK(rs.getTimestamp(11));
				danhGia.setNgayXacNhanCTSV(rs.getTimestamp(12));
				danhGia.setActive(rs.getBoolean(13));
				
				listDanhGia.add(danhGia);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return listDanhGia;
	}

	public DanhGia getDanhGiaTheoMa(int maDanhGia) {
		String sql = "SELECT id_danhgia, id_sinhvien, id_dotdanhgia, ngaydanhgia, tongdiem, diemtapthelop, tinhtrang, ghichu, ngayltxacnhan, ngaygvxacnhan, ngaytkxacnhan, ngayctsvxacnhan, active FROM danhgia_tbl WHERE active = 1 AND id_danhgia = ?";		
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, maDanhGia);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				danhGia = new DanhGia();
				danhGia.setMaDanhGia(rs.getInt(1));
				danhGia.setMaSinhVien(rs.getInt(2));
				danhGia.setMaDotDanhGia(rs.getInt(3));
				danhGia.setNgayDanhGia(rs.getTimestamp(4));
				danhGia.setTongDiem(rs.getInt(5));
				danhGia.setDiemTapTheLop(rs.getInt(6));
				danhGia.setTinhTrang(rs.getString(7));
				danhGia.setGhiChu(rs.getString(8));
				danhGia.setNgayXacNhanLT(rs.getTimestamp(9));
				danhGia.setNgayXacNhanGV(rs.getTimestamp(10));
				danhGia.setNgayXacNhanTK(rs.getTimestamp(11));
				danhGia.setNgayXacNhanCTSV(rs.getTimestamp(12));
				danhGia.setActive(rs.getBoolean(13));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return danhGia;
	}
	
	
	public DanhGia getDanhGiaTheoMaSinhVien(int maSinhVien) {
		String sql = "SELECT id_danhgia, id_sinhvien, id_dotdanhgia, ngaydanhgia, tongdiem, diemtapthelop, tinhtrang, ghichu, ngayltxacnhan, ngaygvxacnhan, ngaytkxacnhan, ngayctsvxacnhan, active FROM danhgia_tbl WHERE active = 1 AND id_sinhvien = ?";		
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, maSinhVien);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				danhGia = new DanhGia();
				danhGia.setMaDanhGia(rs.getInt(1));
				danhGia.setMaSinhVien(rs.getInt(2));
				danhGia.setMaDotDanhGia(rs.getInt(3));
				danhGia.setNgayDanhGia(rs.getTimestamp(4));
				danhGia.setTongDiem(rs.getInt(5));
				danhGia.setDiemTapTheLop(rs.getInt(6));
				danhGia.setTinhTrang(rs.getString(7));
				danhGia.setGhiChu(rs.getString(8));
				danhGia.setNgayXacNhanLT(rs.getTimestamp(9));
				danhGia.setNgayXacNhanGV(rs.getTimestamp(10));
				danhGia.setNgayXacNhanTK(rs.getTimestamp(11));
				danhGia.setNgayXacNhanCTSV(rs.getTimestamp(12));
				danhGia.setActive(rs.getBoolean(13));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return danhGia;
	}

	public int insertDanhGia(DanhGia danhGia) {
		String sql = "INSERT INTO danhgia_tbl(id_sinhvien, id_dotdanhgia, ngaydanhgia, tongdiem, diemtapthelop, tinhtrang, ghichu, ngayltxacnhan, ngaygvxacnhan, ngaytkxacnhan, ngayctsvxacnhan, active) VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ";		
		int idDanhGia = 0;
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			
		
			pstmt.setInt(1, danhGia.getMaSinhVien());
			pstmt.setInt(2, danhGia.getMaDotDanhGia());
			pstmt.setTimestamp(3, danhGia.getNgayDanhGia());
			pstmt.setInt(4, danhGia.getTongDiem());
			pstmt.setInt(5, danhGia.getDiemTapTheLop());
			pstmt.setString(6, danhGia.getTinhTrang());
			pstmt.setString(7, danhGia.getGhiChu());
			pstmt.setTimestamp(8, danhGia.getNgayXacNhanLT());
			pstmt.setTimestamp(9, danhGia.getNgayXacNhanGV());
			pstmt.setTimestamp(10, danhGia.getNgayXacNhanTK());
			pstmt.setTimestamp(11, danhGia.getNgayXacNhanCTSV());
			pstmt.setBoolean(12, danhGia.isActive());
			
			idDanhGia = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return idDanhGia;
	}

	public int updateDanhGia(DanhGia danhGia) {
		String sql = "UPDATE danhgia_tbl SET id_sinhvien = ?, id_dotdanhgia = ?, ngaydanhgia = ?, tongdiem = ?, diemtapthelop = ?, tinhtrang = ?, ghichu = ?, ngayltxacnhan = ?, ngaygvxacnhan = ?, ngaytkxacnhan = ?, ngayctsvxacnhan = ?, active = ? WHERE id_danhgia = ?";		
		int result = 0;
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			
		
			pstmt.setInt(1, danhGia.getMaSinhVien());
			pstmt.setInt(2, danhGia.getMaDotDanhGia());
			pstmt.setTimestamp(3, danhGia.getNgayDanhGia());
			pstmt.setInt(4, danhGia.getTongDiem());
			pstmt.setInt(5, danhGia.getDiemTapTheLop());
			pstmt.setString(6, danhGia.getTinhTrang());
			pstmt.setString(7, danhGia.getGhiChu());
			pstmt.setTimestamp(8, danhGia.getNgayXacNhanLT());
			pstmt.setTimestamp(9, danhGia.getNgayXacNhanGV());
			pstmt.setTimestamp(10, danhGia.getNgayXacNhanTK());
			pstmt.setTimestamp(11, danhGia.getNgayXacNhanCTSV());
			pstmt.setBoolean(12, danhGia.isActive());
			pstmt.setInt(13, danhGia.getMaDanhGia());
			
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

	public int deleteDanhGia(int maDanhGia) {
		String sql = "UPDATE danhgia_tbl SET active = 0 WHERE id_danhgia = ?";		
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

	
	//Kiet them
	public DanhGia getDanhGiaTheoMaSinhVien(int maSinhVien, int maDotDanhGia) {
		String sql = "SELECT id_danhgia, id_sinhvien, id_dotdanhgia, ngaydanhgia, tongdiem, diemtapthelop, tinhtrang, ghichu, ngayltxacnhan, ngaygvxacnhan, ngaytkxacnhan, ngayctsvxacnhan, active FROM danhgia_tbl WHERE active = 1 AND id_sinhvien = ? AND id_dotdanhgia = ?";		
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, maSinhVien);
			pstmt.setInt(2, maDotDanhGia);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				danhGia = new DanhGia();
				danhGia.setMaDanhGia(rs.getInt(1));
				danhGia.setMaSinhVien(rs.getInt(2));
				danhGia.setMaDotDanhGia(rs.getInt(3));
				danhGia.setNgayDanhGia(rs.getTimestamp(4));
				danhGia.setTongDiem(rs.getInt(5));
				danhGia.setDiemTapTheLop(rs.getInt(6));
				danhGia.setTinhTrang(rs.getString(7));
				danhGia.setGhiChu(rs.getString(8));
				danhGia.setNgayXacNhanLT(rs.getTimestamp(9));
				danhGia.setNgayXacNhanGV(rs.getTimestamp(10));
				danhGia.setNgayXacNhanTK(rs.getTimestamp(11));
				danhGia.setNgayXacNhanCTSV(rs.getTimestamp(12));
				danhGia.setActive(rs.getBoolean(13));
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return danhGia;
	}
	
	//kiet them (lop truong danhg gia)
	public ArrayList<DanhGia> getAllDanhGiaTheoLopTT(int maDotDanhGia, int maLop, String tinhTrang1, String tinhTrang2, int offset, int noOfRecords) {
		String sql = "SELECT danhgia_tbl.id_danhgia, danhgia_tbl.id_sinhvien, danhgia_tbl.id_dotdanhgia, danhgia_tbl.ngaydanhgia, "
				+ "danhgia_tbl.tongdiem, danhgia_tbl.diemtapthelop, danhgia_tbl.tinhtrang, danhgia_tbl.ghichu, "
				+ "danhgia_tbl.ngayltxacnhan, danhgia_tbl.ngaygvxacnhan, danhgia_tbl.ngaytkxacnhan, danhgia_tbl.ngayctsvxacnhan, danhgia_tbl.active "
				+ "FROM danhgia_tbl INNER JOIN sinhvien_tbl ON danhgia_tbl.id_sinhvien = sinhvien_tbl.id_sinhvien "
				+ "WHERE danhgia_tbl.id_dotdanhgia = ? AND sinhvien_tbl.id_lop = ?  "
				+ "AND (danhgia_tbl.tinhtrang = ? OR danhgia_tbl.tinhtrang = ?) ORDER BY danhgia_tbl.id_danhgia OFFSET  ? ROWS FETCH NEXT ? ROWS ONLY";		
		listDanhGia = new ArrayList<DanhGia>();
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, maDotDanhGia);
			pstmt.setInt(2, maLop);
			pstmt.setString(3, tinhTrang1);
			pstmt.setString(4, tinhTrang2);
			pstmt.setInt(5, offset);
			pstmt.setInt(6, noOfRecords);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				danhGia = new DanhGia();
				danhGia.setMaDanhGia(rs.getInt(1));
				danhGia.setMaSinhVien(rs.getInt(2));
				danhGia.setMaDotDanhGia(rs.getInt(3));
				danhGia.setNgayDanhGia(rs.getTimestamp(4));
				danhGia.setTongDiem(rs.getInt(5));
				danhGia.setDiemTapTheLop(rs.getInt(6));
				danhGia.setTinhTrang(rs.getString(7));
				danhGia.setGhiChu(rs.getString(8));
				danhGia.setNgayXacNhanLT(rs.getTimestamp(9));
				danhGia.setNgayXacNhanGV(rs.getTimestamp(10));
				danhGia.setNgayXacNhanTK(rs.getTimestamp(11));
				danhGia.setNgayXacNhanCTSV(rs.getTimestamp(12));
				danhGia.setActive(rs.getBoolean(13));
				
				listDanhGia.add(danhGia);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return listDanhGia;
	}
	
	//kiet them
	public int getCountDanhGiaTheoLopTT(int maDotDanhGia, int maLop, String tinhTrang1, String tinhTrang2) {
		String sql = "SELECT COUNT(*) FROM danhgia_tbl INNER JOIN sinhvien_tbl ON danhgia_tbl.id_sinhvien = sinhvien_tbl.id_sinhvien "
				+ "WHERE danhgia_tbl.id_dotdanhgia = ? AND sinhvien_tbl.id_lop = ? AND (danhgia_tbl.tinhtrang = ? OR danhgia_tbl.tinhtrang = ?)";
		try {
			
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, maDotDanhGia);
			pstmt.setInt(2, maLop);
			pstmt.setString(3, tinhTrang1);
			pstmt.setString(4, tinhTrang2);
			
			rs = pstmt.executeQuery();
			if(rs.next())
				return rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return 0;
	}

	//kiet them
	public int updateDanhGia(ArrayList<DanhGia> listDanhGia) {
		String sql = "UPDATE danhgia_tbl SET id_sinhvien = ?, id_dotdanhgia = ?, ngaydanhgia = ?, tongdiem = ?, diemtapthelop = ?, tinhtrang = ?, ghichu = ?, ngayltxacnhan = ?, ngaygvxacnhan = ?, ngaytkxacnhan = ?, ngayctsvxacnhan = ?, active = ? WHERE id_danhgia = ?";		
		int result = 0;
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			
			for (int i = 0; i < listDanhGia.size(); i++) {
				pstmt.setInt(1, listDanhGia.get(i).getMaSinhVien());
				pstmt.setInt(2, listDanhGia.get(i).getMaDotDanhGia());
				pstmt.setTimestamp(3, listDanhGia.get(i).getNgayDanhGia());
				pstmt.setInt(4, listDanhGia.get(i).getTongDiem());
				pstmt.setInt(5, listDanhGia.get(i).getDiemTapTheLop());
				pstmt.setString(6, listDanhGia.get(i).getTinhTrang());
				pstmt.setString(7, listDanhGia.get(i).getGhiChu());
				pstmt.setTimestamp(8, listDanhGia.get(i).getNgayXacNhanLT());
				pstmt.setTimestamp(9, listDanhGia.get(i).getNgayXacNhanGV());
				pstmt.setTimestamp(10, listDanhGia.get(i).getNgayXacNhanTK());
				pstmt.setTimestamp(11, listDanhGia.get(i).getNgayXacNhanCTSV());
				pstmt.setBoolean(12, listDanhGia.get(i).isActive());
				pstmt.setInt(13, listDanhGia.get(i).getMaDanhGia());
				
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
	
	//kiet them
	public ArrayList<DanhGia> getAllDanhGiaTheoId(String[] id) {
		String sql = "SELECT id_danhgia, id_sinhvien, id_dotdanhgia, ngaydanhgia, tongdiem, diemtapthelop, tinhtrang, ghichu, ngayltxacnhan, ngaygvxacnhan, ngaytkxacnhan, ngayctsvxacnhan, active FROM danhgia_tbl WHERE active = 1 AND id_danhgia = ?";		
		listDanhGia = new ArrayList<DanhGia>();
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			
			if (id != null)
			for (int i = 0; i < id.length ; i++) {
				pstmt.setInt(1, Integer.parseInt(id[i]));
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					danhGia = new DanhGia();
					danhGia.setMaDanhGia(rs.getInt(1));
					danhGia.setMaSinhVien(rs.getInt(2));
					danhGia.setMaDotDanhGia(rs.getInt(3));
					danhGia.setNgayDanhGia(rs.getTimestamp(4));
					danhGia.setTongDiem(rs.getInt(5));
					danhGia.setDiemTapTheLop(rs.getInt(6));
					danhGia.setTinhTrang(rs.getString(7));
					danhGia.setGhiChu(rs.getString(8));
					danhGia.setNgayXacNhanLT(rs.getTimestamp(9));
					danhGia.setNgayXacNhanGV(rs.getTimestamp(10));
					danhGia.setNgayXacNhanTK(rs.getTimestamp(11));
					danhGia.setNgayXacNhanCTSV(rs.getTimestamp(12));
					danhGia.setActive(rs.getBoolean(13));
					
					listDanhGia.add(danhGia);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return listDanhGia;
	}
	

	// kiet them (co phan trang)
	public ArrayList<DanhGia> getAllDanhGiaTheoLopTheoDotPhanTrang(int maDotDanhGia, int maLop, int offset, int noOfRecords) {
		String sql = "SELECT id_danhgia, danhgia_tbl.id_sinhvien, id_dotdanhgia, ngaydanhgia, tongdiem, diemtapthelop, "
				+ "tinhtrang, ghichu, ngayltxacnhan, ngaygvxacnhan, ngaytkxacnhan, ngayctsvxacnhan, danhgia_tbl.active "
				+ "FROM danhgia_tbl INNER JOIN sinhvien_tbl ON danhgia_tbl.id_sinhvien = sinhvien_tbl.id_sinhvien "
				+ "WHERE danhgia_tbl.active = 1 AND sinhvien_tbl.id_lop = ? AND danhgia_tbl.id_dotdanhgia = ? "
				+ "ORDER BY sinhvien_tbl.ten ASC";		
		listDanhGia = new ArrayList<DanhGia>();
			
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, maLop);
			pstmt.setInt(2, maDotDanhGia);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				danhGia = new DanhGia();
				danhGia.setMaDanhGia(rs.getInt(1));
				danhGia.setMaSinhVien(rs.getInt(2));
				danhGia.setMaDotDanhGia(rs.getInt(3));
				danhGia.setNgayDanhGia(rs.getTimestamp(4));
				danhGia.setTongDiem(rs.getInt(5));
				danhGia.setDiemTapTheLop(rs.getInt(6));
				danhGia.setTinhTrang(rs.getString(7));
				danhGia.setGhiChu(rs.getString(8));
				danhGia.setNgayXacNhanLT(rs.getTimestamp(9));
				danhGia.setNgayXacNhanGV(rs.getTimestamp(10));
				danhGia.setNgayXacNhanTK(rs.getTimestamp(11));
				danhGia.setNgayXacNhanCTSV(rs.getTimestamp(12));
				danhGia.setActive(rs.getBoolean(13));
					
				listDanhGia.add(danhGia);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return listDanhGia;
	}
	
	public ArrayList<DanhGia> getAllDanhGiaTheoLopTheoDot(int maDotDanhGia, int maLop) {
		String sql = "SELECT id_danhgia, danhgia_tbl.id_sinhvien, id_dotdanhgia, ngaydanhgia, tongdiem, diemtapthelop, "
				+ "tinhtrang, ghichu, ngayltxacnhan, ngaygvxacnhan, ngaytkxacnhan, ngayctsvxacnhan, danhgia_tbl.active "
				+ "FROM danhgia_tbl INNER JOIN sinhvien_tbl ON danhgia_tbl.id_sinhvien = sinhvien_tbl.id_sinhvien "
				+ "WHERE danhgia_tbl.active = 1 AND sinhvien_tbl.id_lop = ? AND danhgia_tbl.id_dotdanhgia = ? "
				+ "ORDER BY sinhvien_tbl.ten ASC";		
		listDanhGia = new ArrayList<DanhGia>();
			
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, maLop);
			pstmt.setInt(2, maDotDanhGia);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				danhGia = new DanhGia();
				danhGia.setMaDanhGia(rs.getInt(1));
				danhGia.setMaSinhVien(rs.getInt(2));
				danhGia.setMaDotDanhGia(rs.getInt(3));
				danhGia.setNgayDanhGia(rs.getTimestamp(4));
				danhGia.setTongDiem(rs.getInt(5));
				danhGia.setDiemTapTheLop(rs.getInt(6));
				danhGia.setTinhTrang(rs.getString(7));
				danhGia.setGhiChu(rs.getString(8));
				danhGia.setNgayXacNhanLT(rs.getTimestamp(9));
				danhGia.setNgayXacNhanGV(rs.getTimestamp(10));
				danhGia.setNgayXacNhanTK(rs.getTimestamp(11));
				danhGia.setNgayXacNhanCTSV(rs.getTimestamp(12));
				danhGia.setActive(rs.getBoolean(13));
					
				listDanhGia.add(danhGia);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return listDanhGia;
	}

	//kiet them
	public int getCountDanhGiaTheoLopTheoDot(int maDotDanhGia, int maLop) {
		String sql = "SELECT COUNT(*) FROM danhgia_tbl INNER JOIN sinhvien_tbl ON danhgia_tbl.id_sinhvien = sinhvien_tbl.id_sinhvien "
				+ "WHERE danhgia_tbl.active = 1 AND sinhvien_tbl.id_lop = ? AND danhgia_tbl.id_dotdanhgia = ? ";
		try {
			
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, maLop);
			pstmt.setInt(2, maDotDanhGia);
			
			rs = pstmt.executeQuery();
			if(rs.next())
				return rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return 0;
	}
	
	// huy them
	public ArrayList<DanhGia> getAllDanhGiaXacNhan() {
		String sql = "SELECT id_danhgia, id_sinhvien, id_dotdanhgia, ngaydanhgia, tongdiem, diemtapthelop, tinhtrang, ghichu, ngayltxacnhan, ngaygvxacnhan, ngaytkxacnhan, ngayctsvxacnhan, active FROM danhgia_tbl WHERE active = 1";
		listDanhGia = new ArrayList<DanhGia>();

		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				danhGia = new DanhGia();
				danhGia.setMaDanhGia(rs.getInt(1));
				danhGia.setMaSinhVien(rs.getInt(2));
				danhGia.setMaDotDanhGia(rs.getInt(3));
				danhGia.setNgayDanhGia(rs.getTimestamp(4));
				danhGia.setTongDiem(rs.getInt(5));
				danhGia.setDiemTapTheLop(rs.getInt(6));
				danhGia.setTinhTrang(rs.getString(7));
				danhGia.setGhiChu(rs.getString(8));
				danhGia.setNgayXacNhanLT(rs.getTimestamp(9));
				danhGia.setNgayXacNhanGV(rs.getTimestamp(10));
				danhGia.setNgayXacNhanTK(rs.getTimestamp(11));
				danhGia.setNgayXacNhanCTSV(rs.getTimestamp(12));
				danhGia.setActive(rs.getBoolean(13));

				listDanhGia.add(danhGia);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return listDanhGia;
	}

	// huy them
	public int xacNhanDanhGia(String[] listIddanhgia) {
		int result = 0;
		int check=0;
		Date date = new Date();
		for (int i = 0; i < listIddanhgia.length; i++) {
			String sql = "UPDATE danhgia_tbl SET  tinhtrang = ?, ngayctsvxacnhan= ?  WHERE id_danhgia = ?";
			try {

				con = SQLConnection.getConnection();
				pstmt = con.prepareStatement(sql);

				pstmt.setString(1, "hoanthanh");
				pstmt.setTimestamp(2, DateUtils.convertToTimestamp(date));
				pstmt.setInt(3, Integer.parseInt(listIddanhgia[i]));
				result= pstmt.executeUpdate();
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
	
	//huy them
	public ArrayList<DanhGia> getAllDanhGiaXacNhanByDotDanhGiaVsLop(int idDotDanhGia, int idLop) {
		String sql = "SELECT id_danhgia, danhgia_tbl.id_sinhvien, id_dotdanhgia, ngaydanhgia, tongdiem, diemtapthelop, "
				+ "tinhtrang, ghichu, ngayltxacnhan, ngaygvxacnhan, ngaytkxacnhan, ngayctsvxacnhan, danhgia_tbl.active "
				+ "FROM danhgia_tbl INNER JOIN sinhvien_tbl ON danhgia_tbl.id_sinhvien = sinhvien_tbl.id_sinhvien "
				+ "WHERE danhgia_tbl.active = 1 AND sinhvien_tbl.id_lop = ? AND danhgia_tbl.id_dotdanhgia = ? ";
				
		listDanhGia = new ArrayList<DanhGia>();
			
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idLop);
			pstmt.setInt(2, idDotDanhGia);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				danhGia = new DanhGia();
				danhGia.setMaDanhGia(rs.getInt(1));
				danhGia.setMaSinhVien(rs.getInt(2));
				danhGia.setMaDotDanhGia(rs.getInt(3));
				danhGia.setNgayDanhGia(rs.getTimestamp(4));
				danhGia.setTongDiem(rs.getInt(5));
				danhGia.setDiemTapTheLop(rs.getInt(6));
				danhGia.setTinhTrang(rs.getString(7));
				danhGia.setGhiChu(rs.getString(8));
				danhGia.setNgayXacNhanLT(rs.getTimestamp(9));
				danhGia.setNgayXacNhanGV(rs.getTimestamp(10));
				danhGia.setNgayXacNhanTK(rs.getTimestamp(11));
				danhGia.setNgayXacNhanCTSV(rs.getTimestamp(12));
				danhGia.setActive(rs.getBoolean(13));
					
				listDanhGia.add(danhGia);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.closeConnection(this.con);
			SQLConnection.closePrepareStatement(pstmt);
			SQLConnection.closeResultSet(rs);
		}
		return listDanhGia;
	}
	
	public int lastInserted() {
		String sql = "SELECT id_danhgia FROM danhgia_tbl ORDER BY id_danhgia DESC";
		int result = 0;
		
		try {
			con = SQLConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
				System.out.println("danhgiaodao: " + result);
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
	
}



