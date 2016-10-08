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
					<h4>Thêm Lớp</h4><br>
				</div>
			<form class="form-horizontal" role="form" method="post" action="<%=request.getContextPath()%>/phong-ctsv/them-lop">
						  <div class="form-group">
						    <label class="control-label col-sm-4" for="name">Tên Lớp</label>
							    <div class="col-sm-6">
							      <input type="text" class="form-control" id="name" name="tenLop" value="">
							    </div>
						    </div>
						    
						    <div class="form-group">
						    	<label class="control-label col-sm-4" for="name">Khoa</label>
							    <div class="col-sm-6">
							      	<select name="khoa" class="form-control" style="width: 50%;"
							      		id="faculty_list" onchange="doKhoa()">
										<option value="0" disabled="disabled" selected="selected">==Chọn Khoa==</option>
										<%if (listKhoa!= null) for (int i = 0; i < listKhoa.size(); i++) { %>
										<option value="<%=listKhoa.get(i).getMaKhoa() %>"><%=listKhoa.get(i).getTen() %></option>
										<%} %>
									</select>
							    </div>
						    </div>
						    
						    <div class="form-group" id="ajax-data">
						    	<label class="control-label col-sm-4" for="name">GVCN</label>
							    <div class="col-sm-6">
							      	<select name="gvcn" class="form-control" id="sel1" style="width: 50%">
										<option value="0" disabled="disabled">==Chọn GVCN==</option>
									</select>
							    </div>
						    </div>
						    
						    <div class="form-group">
						   		<label class="control-label col-sm-4"></label>
						   		<div class="col-sm-6">
						      		<button type="submit" class="btn btn-success" name="action" value="them">Tạo</button>
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
    
    <script>
				
		 function doKhoa(){
				var idKhoa=$('#faculty_list').val();
				$.ajax({
					url: "<%=request.getContextPath()%>/phong-ctsv/them-lop",
					type : 'POST',
					cache : false,
					data : {
						//Dữ liệu gửi cho server
						khoa :idKhoa,
					},
					success : function(data) {
						//kết quả về của  server trả về khi xử lý thành công
						
					$("#ajax-data").html(data);
					},
					error : function() {
						// Xử lý nếu có lỗi
						alert("Có lỗi trong quá trình xử lý")
					}
				});
				
			}
		
	</script>
</html>