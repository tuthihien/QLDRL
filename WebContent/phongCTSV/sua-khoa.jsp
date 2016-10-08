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
					<h4>Cập nhật khoa</h4><br>
				</div>
			<form class="form-horizontal" role="form" method="post" action="<%=request.getContextPath()%>/phong-ctsv/sua-khoa">
						  <div class="form-group">
						    <label class="control-label col-sm-4" for="name">Tên Khoa</label>
						    <div class="col-sm-6">
						      <input type="text" class="form-control" id="name" name="tenKhoa" value="${khoa.getTen() }">
						    </div>
						    <input type="text" name="id" value="${khoa.getMaKhoa() }" hidden="true">
						    <div class="col-sm-offset-2 col-sm-">
						      <button type="submit" class="btn btn-success" name="action" value="them">Cập nhật</button>
						    </div>
							</div>
						 
					</form>
				
				
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