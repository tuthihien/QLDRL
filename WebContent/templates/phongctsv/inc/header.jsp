<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/Resources/bootstrap/css/bootstrap.min.css"/>
<link href="<%=request.getContextPath()%>/templates/phongctsv/css/style.css" rel="stylesheet" type="text/css" />
<script src="<%=request.getContextPath()%>/Resources/bootstrap/jquery/jquery-1.11.3.min.js"></script>
 <script src="<%=request.getContextPath()%>/Resources/bootstrap/js/bootstrap.min.js"></script>
<!-- jQuery -->
	<script src="<%=request.getContextPath()%>/Resources/jquery/jquery.min.js"></script>
<!-- Custom Fonts -->
<link href="<%=request.getContextPath()%>/Resources/fonts/font-awesome/css/font-awesome.min.css"
rel="stylesheet"/>
<link href="<%=request.getContextPath()%>/Resources/datatable-bootstrap/css/dataTables.bootstrap.css"
rel="stylesheet"/>
<!-- DataTables Responsive CSS -->
		<link
			href="<%=request.getContextPath()%>/Resources/datatable-responsive/css/dataTables.responsive.css"
			rel="stylesheet" />
<!-- Select CSS -->
<link href="<%=request.getContextPath()%>/Resources/bootstrap-select/css/bootstrap-select.css" rel="stylesheet"/>
<!-- Custom CSS -->
<link href="<%=request.getContextPath()%>/Resources/sb-admin-2/css/sb-admin-2.css" rel="stylesheet">
<!-- DataTables JavaScript -->
		<script src="<%=request.getContextPath()%>/Resources/data/js/jquery.dataTables.min.js"></script>

		<script src="<%=request.getContextPath()%>/Resources/datatable-bootstrap/js/dataTables.bootstrap.min.js"></script>	

<!--JavaScript checkbox -->
<script src="<%=request.getContextPath()%>/Resources/bootstrap-checkbox/js/bootstrap-checkbox.min.js"></script>
<!--JavaScript select -->
<script src="<%=request.getContextPath()%>/Resources/bootstrap-select/js/bootstrap-select.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/Resources/bootstrap-datepicker/css/datepicker.css" />

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
      				<a class="navbar-brand" href="<%=request.getContextPath()%>/giang-vien/thong-tin-ca-nhan">Trang chủ</a>
    			</div>
	   			<div>
		      		<ul class="nav navbar-nav">
		       			<li><a href="<%=request.getContextPath()%>/phong-ctsv/xacnhan-danhgia">Xác nhận ĐRL</a></li>
		       			<li><a href="<%=request.getContextPath()%>/phong-ctsv/du-lieu">Dữ liệu</a></li>
		      		</ul>
		      		<ul class="nav navbar-nav navbar-right">
		        		<li><a href="<%=request.getContextPath()%>/giang-vien/thong-tin-ca-nhan"><span class="glyphicon glyphicon-user"></span>${giangVien.getTen() }</a></li>
		        		<li><a href="<%=request.getContextPath()%>/giang-vien/logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
		      		</ul>
	    		</div>
	    	</div>
		</nav>
	<!-- ket thuc header -->
