<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/Resources/bootstrap/css/bootstrap.min.css">
<link href="<%=request.getContextPath()%>/templates/sinhvien/css/style.css" rel="stylesheet" type="text/css" />
<script src="<%=request.getContextPath()%>/Resources/bootstrap/jquery/jquery-1.11.3.min.js"></script>
<script src="<%=request.getContextPath()%>/Resources/bootstrap/js/bootstrap.min.js"></script>
<title>Hệ thống quản lý điểm rèn luyện</title>
</head>
<body>
	
	<!-- header -->
		<div id="header">
			<div class="row">
	  			<div class="col-sm-2">
	  				<img alt="" src="<%=request.getContextPath()%>/Resources/imgs/bk.png" width="110px">
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
      				<a class="navbar-brand" href="<%=request.getContextPath()%>/sinh-vien/thong-tin-ca-nhan">Trang chủ</a>
    			</div>
	   			<div>
		      		<ul class="nav navbar-nav">
		       			<li><a href="<%=request.getContextPath()%>/sinh-vien/danh-gia">Đánh giá</a></li>
		       			<li><a href="<%=request.getContextPath()%>/sinh-vien/ket-qua-danh-gia">Kết quả đánh giá</a></li>
		      		</ul>
		      		<ul class="nav navbar-nav navbar-right">
		        		<li><a href="<%=request.getContextPath()%>/sinh-vien/thong-tin-ca-nhan"><span class="glyphicon glyphicon-user"></span>${sinhVien.getTen() }</a></li>
		        		<li><a href="<%=request.getContextPath()%>/sinh-vien/logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
		      		</ul>
	    		</div>
	    	</div>
		</nav>
	<!-- ket thuc header -->
