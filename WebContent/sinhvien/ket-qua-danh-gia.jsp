<%@page import="model.bean.SinhVien"%>
<%@page import="model.bean.DanhGia"%>
<%@page import="model.bean.DotDanhGia"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="container">
				<%
					@SuppressWarnings("unchecked")
					ArrayList<DotDanhGia> listDotDanhGia = (ArrayList<DotDanhGia>) request.getAttribute("listDotDanhGia");
					DanhGia danhGia = (DanhGia) request.getAttribute("danhGia");
					DotDanhGia dotDanhGia = (DotDanhGia) request.getAttribute("dotDanhGia");
					SinhVien sinhVien = (SinhVien) request.getAttribute("sinhVien");
					if (listDotDanhGia != null && sinhVien != null) {
				%>
<%@include file="/templates/sinhvien/inc/header.jsp"%>
<div id="pageBody">
	<div class="row">
		<%if (sinhVien.getChucVu().equals("lớp trưởng")) { %>
		<%@include file="/templates/sinhvien/inc/left-bar-loptruong.jsp"%>
		<%} else { %>
		<%@include file="/templates/sinhvien/inc/left-bar.jsp"%>
		<%} %>
		
		<div class="col-sm-10">
			<div id="ketQuaDanhGia">
				<!--  noi dung -->
				
				
				
				<form role="form" action="<%=request.getContextPath()%>/sinh-vien/ket-qua-danh-gia" method="post">
					<div class="row">
						<div class="input-group">
							<span class="input-group-addon"> <label for="sel1">Kì</label>
							</span>
							
							<select name="id" class="form-control" id="sel1" onchange="this.form.submit()">
								<%
									for (int i = 0; i < listDotDanhGia.size(); i++) {
										if (dotDanhGia != null && dotDanhGia.getMaDotDanhGia() != listDotDanhGia.get(i).getMaDotDanhGia()) {
								%>
								<option value="<%=listDotDanhGia.get(i).getMaDotDanhGia() %>" ><%=listDotDanhGia.get(i).getTen() %></option>
								<%} else {%>
								<option value="<%=listDotDanhGia.get(i).getMaDotDanhGia() %>" selected="selected"><%=listDotDanhGia.get(i).getTen() %></option>
								<%}} %>
							</select>
						</div>
					</div>
				
				<br />

				<div class="table-responsive">
					<table class="table table-bordered">
						<thead>
							<tr style="font-size: 13px">
								<th>Ngày đánh giá</th>
								<th>Điểm tự đánh giá</th>
								<th>Điểm tập thể lớp đánh giá</th>
								<th>Xếp loại</th>
								<th>Tình trạng</th>
							</tr>
						</thead>
						<tbody>
							<tr style="font-size: 13px">
								<td>${danhGia.ngayDanhGiaToString()}</td>
								<td>${danhGia.getTongDiem()}</td>
								<td>${danhGia.getDiemTapTheLop()}</td>
								<td>${xepLoai }</td>
								<%if (danhGia != null) {
									if (danhGia.getTinhTrang().equals("tapthelopchuaduyet")) {%>
								<td>Tập thể lớp chưa duyệt</td>
								<%} else if (danhGia.getTinhTrang().equals("tapthelopdaduyet")) {%>
								<td>Tập thể lớp đã duyệt</td>
								<%} else if (danhGia.getTinhTrang().equals("gvcndaduyet")) {%>
								<td>GVCN đã duyệt</td>
								<%} else if (danhGia.getTinhTrang().equals("truongkhoadaduyet")) {%>
								<td>Khoa đã duyệt</td>
								<%} else if (danhGia.getTinhTrang().equals("hoanthanh")) {%>
								<td>Hoàn thành</td>
								<%}} %>
							</tr>
						</tbody>
					</table>
					<% if (danhGia != null) { %>
					
					<div style="float: right;">
						<button type="submit" class="btn btn-default" name="action" value="xem" >
							<span class="glyphicon glyphicon-eye-open"></span>Xem</button>
							<%
								String choSua = (String) request.getAttribute("choSua");
								if (choSua != null) { 
							%>
						<button type="submit" class="btn btn-default" name="action" value="sua" >
							<span class="glyphicon glyphicon-eye-open"></span>Sửa</button>
							<%} %>
					</div>
					<%} %>
				</div>
				</form>
				<%} %>
			</div>
		</div>
		
	</div>
	
</div>
<%@include file="/templates/sinhvien/inc/footer.jsp"%>
</div>
</body>
</html>