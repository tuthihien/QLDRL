<%@page import="model.bean.Khoa"%>
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
					ArrayList<Khoa> listKhoa = (ArrayList<Khoa>) request.getAttribute("listKhoa");
					if (giangVien != null) {
				%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/Resources/bootstrap/css/table.css">
<div class="container">
	<%@include file="/templates/phongctsv/inc/header.jsp"%>
	<div id="pageBody">
		<div class="row">
			<%@include file="/templates/phongctsv/inc/left-bar.jsp"%>		
			<div class="col-sm-10">
				<!--  noi dung -->
				<div id="sinhVienDanhGia">
					<h4 style="text-align: left;">Danh sách khoa</h4>
				</div>
				<a class="btn btn-success" href="<%=request.getContextPath()%>/phong-ctsv/them-khoa">Thêm</a>
				<br><br>
				
				<table class="tablesorter-bootstrap">
					<thead>
						<tr >
							<th >Mã Khoa</th>
							<th >Tên Khoa</th>
							<th >Thao tác</th>
						</tr>
					</thead>
					<tbody>
					<%
					if (listKhoa != null)
						for (Khoa khoa : listKhoa) { 
					%>
						<tr >
							<td><%=khoa.getMaKhoa() %></td>
							<td><%=khoa.getTen() %></td>
							<td align="center" style="font-size: 18px;">
								<a href="<%=request.getContextPath()%>/phong-ctsv/sua-khoa?id=<%=khoa.getMaKhoa() %>"><span class="glyphicon glyphicon-pencil" style="width: 25px"></span></a>
								<a href="<%=request.getContextPath()%>/phong-ctsv/ds-khoa?action=delete&id=<%=khoa.getMaKhoa() %>"><span class="glyphicon glyphicon-remove" style="width: 25px;"></span></a>
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