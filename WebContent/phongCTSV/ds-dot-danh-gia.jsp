<%@page import="utils.DateUtils"%>
<%@page import="model.bean.GiangVien"%>
<%@page import="model.bean.SinhVien"%>
<%@page import="model.bean.DanhGia"%>
<%@page import="model.bean.ChiTietDanhGia"%>
<%@page import="model.bean.DotDanhGia"%>
<%@page import="model.bean.MucDanhGia"%>
<%@page import="model.bean.NoiDungDanhGia"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
				<%	
					GiangVien giangVien = (GiangVien) request.getAttribute("giangVien");
					@SuppressWarnings("unchecked")
					ArrayList<DotDanhGia> listDotDanhGia = (ArrayList<DotDanhGia>) request.getAttribute("listDotDanhGia");
					if (giangVien != null) {
				%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/Resources/bootstrap/css/table.css">
<div class="container" style="max-width: 1200px;">
	<%@include file="/templates/phongctsv/inc/header.jsp"%>
	<div id="pageBody">
		<div class="row">
			<%@include file="/templates/phongctsv/inc/left-bar.jsp"%>		
			<div class="col-sm-10">
				<!--  noi dung -->
				<div id="sinhVienDanhGia">
					<h4 style="text-align: left;">Danh sách các đợt đánh giá</h4>
				</div>
				<a class="btn btn-success" href="<%=request.getContextPath()%>/phong-ctsv/them-dot-danh-gia">Mở đợt</a>
				<br><br>
				
				<table class="tablesorter-bootstrap">
					<thead>
						<tr >
							<th style="font-size: 10px;">Mã đợt</th>
							<th style="font-size: 10px;">Tên đợt</th>
							<th style="font-size: 10px;">Ngày BĐ Sinh Viên</th>
							<th style="font-size: 10px;">Ngày KT Sinh Viên</th>
							<th style="font-size: 10px;">Ngày BĐ Tập thể lớp</th>
							<th style="font-size: 10px;">Ngày KT Tập thể lớp</th>
							<th style="font-size: 10px;">Ngày BĐ GVCN</th>
							<th style="font-size: 10px;">Ngày KT GVCN</th>
							<th style="font-size: 10px;">Ngày BĐ Trưởng Khoa</th>
							<th style="font-size: 10px;">Ngày KT Trưởng khoa</th>
							<th style="font-size: 10px;">Thao tác</th>
						</tr>
					</thead>
					<tbody>
					<%
					if (listDotDanhGia != null)
						for (DotDanhGia dotDanhGia : listDotDanhGia) { 
					%>
						<tr style="font-size: 12px;">
							<td><%=dotDanhGia.getMaDotDanhGia() %></td>
							<td><%=dotDanhGia.getTen() %></td>
							<td><%=DateUtils.formatDatetime(dotDanhGia.getNgayBatDauSV()) %></td>
							<td><%=DateUtils.formatDatetime(dotDanhGia.getNgayKetThucSV()) %></td>
							<td><%=DateUtils.formatDatetime(dotDanhGia.getNgayBatDauLT()) %></td>
							<td><%=DateUtils.formatDatetime(dotDanhGia.getNgayKetThucLT()) %></td>
							<td><%=DateUtils.formatDatetime(dotDanhGia.getNgayBatDauGV()) %></td>
							<td><%=DateUtils.formatDatetime(dotDanhGia.getNgayKetThucGV()) %></td>
							<td><%=DateUtils.formatDatetime(dotDanhGia.getNgayBatDauTK()) %></td>
							<td><%=DateUtils.formatDatetime(dotDanhGia.getNgayKetThucTK()) %></td>
							<td align="center" style="font-size: 18px;">
								<a href="<%=request.getContextPath()%>/phong-ctsv/sua-dot-danh-gia?id=<%=dotDanhGia.getMaDotDanhGia() %>"><span class="glyphicon glyphicon-pencil" style="width: 25px"></span></a>
								<a href="<%=request.getContextPath()%>/phong-ctsv/ds-dot-danh-gia?action=delete&id=<%=dotDanhGia.getMaDotDanhGia() %>"><span class="glyphicon glyphicon-remove" style="width: 25px;"></span></a>
							</td>
						</tr>
					<%} %>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<%@include file="/templates/phongctsv/inc/footer.jsp"%>
</div>
<%} %>
</body>
<script src="<%=request.getContextPath()%>/Resources/bootstrap/jquery/jquery-1.11.3.min.js"></script>
	<script src="<%=request.getContextPath()%>/Resources/bootstrap/jquery/jquery.tablesorter.js"></script>
	<script src="<%=request.getContextPath()%>/Resources/bootstrap/jquery/jquery.tablesorter.widgets.js"></script>
	<script src="<%=request.getContextPath()%>/Resources/bootstrap/jquery/jquery.tablesorter.pager.js"></script>

    <script src="<%=request.getContextPath()%>/Resources/bootstrap/jquery/table.js"></script>
</html>