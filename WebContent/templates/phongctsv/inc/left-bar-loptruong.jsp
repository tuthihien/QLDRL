<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="col-sm-2">
	<div class="panel panel-primary">
		<div class="panel-heading">Thao tác</div>
		<div class="panel-body">
			<div class="list-group">
				<a href="<%=request.getContextPath()%>/sinh-vien/thong-tin-ca-nhan" class="list-group-item"> <span
					class="glyphicon glyphicon-wrench"></span> Thông tin cá nhân
				</a> 
				<a href="<%=request.getContextPath()%>/sinh-vien/lop-truong-danh-gia" class="list-group-item"> <span
					class="glyphicon glyphicon-wrench"></span> Lớp trưởng đánh giá
				</a> 
				<a href="<%=request.getContextPath()%>/sinh-vien/logout" class="list-group-item"> <span
					class="glyphicon glyphicon-wrench"></span> Logout
				</a>
			</div>
		</div>
	</div>
	
</div>