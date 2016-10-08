<%@page import="utils.DateUtils"%>
<%@page import="model.bean.DanhGia"%>
<%@page import="model.bean.DotDanhGia"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/Resources/bootstrap/css/bootstrap.min.css">
<link
	href="<%=request.getContextPath()%>/templates/sinhvien/css/style.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=request.getContextPath()%>/Resources/bootstrap/css/bootstrapValidator.css"
	rel="stylesheet" />

<script
	src="<%=request.getContextPath()%>/Resources/bootstrap/jquery/jquery-1.11.3.min.js"></script>
<script
	src="<%=request.getContextPath()%>/Resources/bootstrap/js/bootstrap.min.js"></script>
<script
	src="<%=request.getContextPath()%>/Resources/bootstrap/jquery/bootstrapValidator.min.js"></script>

<title>Hệ thống quản lý điểm rèn luyện</title>
</head>
<body>
	<div class="container">
		<% DotDanhGia dotDanhGia = (DotDanhGia) request.getAttribute("dotDanhGia"); %>
	
		<!-- header -->
		<div id="header">
			<div class="row">
				<div class="col-sm-2">
					<img alt=""
						src="<%=request.getContextPath()%>/Resources/imgs/bk.png"
						width="110px">
				</div>
				<div class="col-sm-10">
					<h3>HỆ THỐNG ĐÁNH GIÁ ĐIỂM RÈN LUYỆN</h3>
					<h4>Trường Đại Học Bách Khoa Đà Nẵng</h4>
				</div>
			</div>
		</div>

		<nav class="navbar navbar-inverse ">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand"
					href="<%=request.getContextPath()%>/sinh-vien/thong-tin-ca-nhan">Trang
					chủ</a>
			</div>
			<div>
				<ul class="nav navbar-nav">
					<li><a href="<%=request.getContextPath()%>/sinh-vien">Lịch đánh giá</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="<%=request.getContextPath()%>/sinh-vien"><span class="glyphicon glyphicon-log-in"></span>
							Login</a></li>
				</ul>
			</div>
		</div>
		</nav>
		<!-- ket thuc header -->

		<div id="pageBody">
		
			<div class="row">
			<div id="login">
				<div class="col-md-8">
					<span class="label label-primary">Thông báo</span>
					<% if (dotDanhGia != null) { %>
					<h4>Lịch đánh giá điểm rèn luyện <%=dotDanhGia.getTen() %></h4>
					<ul>
						<li>Sinh viên tự đánh giá điểm rèn luyện từ <strong><%=DateUtils.formatDatetime(dotDanhGia.getNgayBatDauSV()) %> -> <%=DateUtils.formatDatetime(dotDanhGia.getNgayKetThucSV()) %></strong></li>
						<li>Tập thể lớp đánh giá điểm rèn luyện cho từng sinh viên từ <strong><%=DateUtils.formatDatetime(dotDanhGia.getNgayBatDauLT()) %> -> <%=DateUtils.formatDatetime(dotDanhGia.getNgayKetThucLT()) %></strong></li>
						<li>GVCN xác nhận điểm rèn luyện của sinh viên từ <strong><%=DateUtils.formatDatetime(dotDanhGia.getNgayBatDauGV()) %> -> <%=DateUtils.formatDatetime(dotDanhGia.getNgayKetThucGV()) %></strong></li>
						<li>Khoa xác nhận điểm rèn luyện của sinh viên từng lớp từ <strong><%=DateUtils.formatDatetime(dotDanhGia.getNgayBatDauTK()) %> -> <%=DateUtils.formatDatetime(dotDanhGia.getNgayKetThucTK()) %></strong></li>
					</ul>
					<%} %>
				</div>
				<div class="col-md-4">
					<div class="form-group" style="text-align: center; margin-bottom: 30px;">
						<h4>Đăng nhập:</h4>
					</div>
					<form id="registration-form" method="POST" class="form-horizontal"
						action="<%=request.getContextPath() %>/sinh-vien">

						<div class="form-group">
							<label class="col-md-5 control-label" for="username">MSSV</label>
							<div class="col-md-7">
								<input type="text" class="form-control" id="username"
									name="username" placeholder="Mã số sinh viên" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-5 control-label" for="password">Mật khẩu</label>
							<div class="col-md-7">
								<input type="password" class="form-control" id="password"
									name="password" placeholder="Mật khẩu" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-5"></div>
							<div class="col-md-7">
								<button name="action" type="submit" class="btn btn-success" value="login">Đăng nhập</button>
							</div>
						</div>
					</form>
					<div id="confirmation" class="alert alert-success hidden">
					<span class="glyphicon glyphicon-star"></span> Thank you for registering
				</div>
				</div>
			</div>
			</div>
		</div>
		<%@include file="/templates/sinhvien/inc/footer.jsp"%>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		var validator = $("#registration-form").bootstrapValidator({
			feedbackIcons : {
				valid : "glyphicon glyphicon-ok",
				invalid : "glyphicon glyphicon-remove",
				validating : "glyphicon glyphicon-refresh"
			},
			fields : {
				username : {
					validators : {
						notEmpty : {
							message : "Nhập mã sinh viên"
						},

						integer : {
							message : 'Mã sinh viên không hợp lệ'
						},
					}
				},
				password : {
					validators : {
						notEmpty : {
							message : "Nhập mật khẩu"
						},
						stringLength : {
							min : 6,
							message : "Mật khẩu tối thiểu 6 ký tự"
						},

					}
				},
			}
		});
	});
</script>
</html>