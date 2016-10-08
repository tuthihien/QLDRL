<%@page import="utils.DateUtils"%>
<%@page import="model.bean.SinhVien"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--Datepicker css  -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/Resources/bootstrap-datepicker/css/datepicker.css" />

<div class="container">
<!-- Object -->
			<%
				SinhVien sinhVien = (SinhVien) request.getAttribute("sinhVien");
				String tt = (String) request.getParameter("tt"); 
				if (sinhVien != null) {
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
			<!--  noi dung -->
			
			<div id="profile">
				<h3>Thông tin cá nhân</h3>
				<%
					if (tt != null) {
						if(tt.equals("ok")) {
				%>
				<div class="alert alert-success">
  					Cập nhật thông tin cá nhân <strong>Thành công!</strong>
				</div>
				<%} else { %>
				<div class="alert alert-warning">
  					Cập nhật thông tin cá nhân <strong>Thất bại!</strong>
				</div>				
				<%}}%>
				<form class="form" role="form">
					
					<div class="row">
						<div class="form-group col-sm-6">
							<label for="name_input">Tên</label> <input type="text" disabled
								class="form-control" id="name_input" name="ten" value="<%=sinhVien.getTen() %>">
						</div>
						<div class="form-group col-sm-3">
							<label for="email_input">Ngày Sinh</label> <input id="datepicker" 
								readonly class="form-control" type="text" name="ngaySinh" value="<%=DateUtils.formatDate(sinhVien.getNgaySinh())%>" />
						</div>
						<div class="form-group col-sm-3">
							<label for="phone_input">Giới tính</label>
							<select class="form-control input-medium" name="gioiTinh">
								<option value="Nam" <%if ("Nam".equals(sinhVien.getGioiTinh())) {%> selected <%}%>>Nam</option>
								<option value="Nu" <%if ("Nu".equals(sinhVien.getGioiTinh())) {%> selected <%}%>>Nữ</option>
								<option value="Khac" <%if ("Khac".equals(sinhVien.getGioiTinh())) {%> selected <%}%>>Khác</option>
							</select>
						</div>
					</div>
					
					<div class="row">
						<div class="form-group col-sm-3">
							<label for="class_input">MSSV</label> <input type="text" disabled
								class="form-control" id="class_input" value="<%=sinhVien.getMssv()%>">
						</div>
						<div class="form-group col-sm-3">
							<label for="id_input">Lớp</label> <input type="text" disabled
								class="form-control" id="id_input"  value="${lop.getTen()}">
						</div>
						<div class="form-group col-sm-6">
							<label for="dob_input">Khoa</label> <input type="text"
								disabled class="form-control" id="dob_input" value="${khoa.getTen()}">
						</div>
					</div>
					
					<div class="row">
						<div class="form-group col-sm-6">
							<label for="email_input">Email</label> <input type="email"
								class="form-control" id="email_input" name="email" value="<%=sinhVien.getEmail()%>">
						</div>
						<div class="form-group col-sm-6">
							<label for="phone_input">Số điện thoại</label> <input type="text"
								class="form-control" id="phone_input" name="sdt" value="<%=sinhVien.getSdt()%>">
						</div>
					</div>
					
					<div class="row">
						<div class="form-group col-sm-3">
							<label for="old_pwd">Mật khẩu cũ</label> <input type="password"
								class="form-control" id="old_pwd" name="matKhauCu" value="">
						</div>
	
						<div class="form-group col-sm-3">
							<label for="new_pwd">Mật khẩu mới</label> <input type="password"
								class="form-control" id="new_pwd" name="matKhauMoi" value="">
						</div>
	
						<div class="form-group col-sm-3">
							<label for="confirm_pwd">Xác nhận mật khẩu</label> <input
								type="password" class="form-control" id="confirm_pwd" name="xnMatKhauMoi" value="">
						</div>
						
						<div class="form-group col-sm-3">
							<label for="save_btn"> </label><br> <input type="submit"
								class="btn btn-success btn-block" name="action" value="Cập nhật" id="save_btn">
						</div>
					</div>
				</form>
			</div>
			<%
				}
			%>
		</div>

	</div>
	</div>
	<!-- Datetime picker js  -->
		<script type="text/javascript" src="<%=request.getContextPath() %>/Resources/bootstrap-datepicker/js/bootstrap-datepicker.js" ></script>
		
		<script type="text/javascript">
			$('#datepicker').datepicker({
				format: 'dd/mm/yyyy',
			    minViewMode: "days"
		    });
		</script>
	<%@include file="/templates/sinhvien/inc/footer.jsp"%>
</div>
</body>
</html>