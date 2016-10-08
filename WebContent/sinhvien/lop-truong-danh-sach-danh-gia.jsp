<%@page import="utils.DateUtils"%>
<%@page import="model.bean.DanhGia"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.DotDanhGia"%>
<%@page import="model.bean.SinhVien"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<link rel="stylesheet" href="<%=request.getContextPath()%>/Resources/bootstrap/css/table.css">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<div class="container">
				<%
					SinhVien sinhVien = (SinhVien) request.getAttribute("sinhVien");
					DotDanhGia dotDanhGia = (DotDanhGia) request.getAttribute("dotDanhGia");
					@SuppressWarnings("unchecked")
					ArrayList<DanhGia> listDanhGia = (ArrayList<DanhGia>) request.getAttribute("listDanhGia");
					@SuppressWarnings("unchecked")
					ArrayList<SinhVien> listSinhVien = (ArrayList<SinhVien>) request.getAttribute("listSinhVien");
					String [] listXepLoai = (String []) request.getAttribute("listXepLoai");
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
			<div id="ketQuaDanhGia">
				<!--  noi dung -->
				
				<%if (dotDanhGia == null) {%>
				
				<h4>Hiện tại không phải thời gian thực hiện chức năng này!</h4>
				<p>Click vào <a href="#"><strong>đây</strong></a> để xem lịch đánh giá điểm rèn luyện.</p>
				
				<%} else { %>
				
				<h4> Đánh giá điểm rèn luyện <%=dotDanhGia.getTen()%></h4>
				<div class="row">
					<div class="col-sm-2" style="padding: 0px;"><p>Lớp: 12T3</p></div>
					<div class="col-sm-10"><p>GVCN: Mai Văn Hà</p></div>
				</div>
				
				<table class="tablesorter-bootstrap">
					<thead>
						<tr>
							<th>MSSV</th>
							<th>Họ và tên</th>
							<th>Ngày sinh</th>
							<th>Điểm tự ĐG</th>
							<th>Điểm lớp ĐG</th>
							<th>Loại</th>
							<th>Tình trạng</th>
							
							
						</tr>
					</thead>
					<tbody>
						<%if (listDanhGia != null && listSinhVien != null && listXepLoai != null) { %>
							<%for (int i = 0; i < listDanhGia.size(); i++) {%>
							<tr>
								<td width="10%"><%=listSinhVien.get(i).getMssv() %></td>
								<td><%=listSinhVien.get(i).getTen() %></td>
								<td width="15%"><%=DateUtils.formatDate(listSinhVien.get(i).getNgaySinh()) %></td>
								<td width="10%"><%=listDanhGia.get(i).getTongDiem() %></td>
								<td width="12%"><%=listDanhGia.get(i).getDiemTapTheLop() %></td>
								<td width="10%"><%=listXepLoai[i] %></td>
								<td width="15%">
									<%if (listDanhGia.get(i).getTinhTrang().equals("tapthelopdaduyet")) {%>
										<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
										<a href="<%=request.getContextPath() %>/sinh-vien/tap-the-lop/danh-gia?page=${currentPage}&id=<%=listDanhGia.get(i).getMaDanhGia() %>">Cập nhật</a>
									<%} else if (listDanhGia.get(i).getTinhTrang().equals("tapthelopchuaduyet")) { %>
										<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
										<a href="<%=request.getContextPath() %>/sinh-vien/tap-the-lop/danh-gia?page=${currentPage}&id=<%=listDanhGia.get(i).getMaDanhGia() %>">Đánh giá</a>
									<%} %>
								</td>
							</tr>
						<%}}%>
					</tbody>
				</table>
				
				<ul class="pagination">
					<c:if test="${currentPage != 1}">
	        			<li><a href="<%=request.getContextPath() %>/sinh-vien/tap-the-lop/danh-sach?page=${currentPage - 1}">Previous</a></li>
	    			</c:if>
 
            		<c:forEach begin="1" end="${noOfPages}" var="i">
						<c:choose>
                    		<c:when test="${currentPage eq i}">
                        		<li class="active"><a href="<%=request.getContextPath() %>/sinh-vien/tap-the-lop/danh-sach?page=${i}">${i}</a></li>
                   			 </c:when>
                    		<c:otherwise>
                        		<li><a href="<%=request.getContextPath() %>/sinh-vien/tap-the-lop/danh-sach?page=${i}">${i}</a></li>
                    		</c:otherwise>
                		</c:choose>
            		</c:forEach>
        			
					<c:if test="${currentPage lt noOfPages}">
        				<li><a href="<%=request.getContextPath() %>/sinh-vien/tap-the-lop/danh-sach?page=${currentPage + 1}">Next</a></li>
    				</c:if>
    			</ul>
				
				<%} %>
				
			</div>
		</div>
		
	</div>
	
</div>
<%@include file="/templates/sinhvien/inc/footer.jsp"%>
<%} %>
</div>
</body>
	<script src="<%=request.getContextPath()%>/Resources/bootstrap/jquery/jquery-1.11.3.min.js"></script>
	<script src="<%=request.getContextPath()%>/Resources/bootstrap/jquery/jquery.tablesorter.js"></script>
	<script src="<%=request.getContextPath()%>/Resources/bootstrap/jquery/jquery.tablesorter.widgets.js"></script>
	<script src="<%=request.getContextPath()%>/Resources/bootstrap/jquery/jquery.tablesorter.pager.js"></script>

    <script src="<%=request.getContextPath()%>/Resources/bootstrap/jquery/table.js"></script>
</html>