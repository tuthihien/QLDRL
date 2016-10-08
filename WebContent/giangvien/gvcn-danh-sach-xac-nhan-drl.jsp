<%@page import="model.bean.GiangVien"%>
<%@page import="model.bean.Lop"%>
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
				GiangVien giangVien = (GiangVien) request.getAttribute("giangVien");
				DotDanhGia dotDanhGia = (DotDanhGia) request.getAttribute("dotDanhGia");
				Integer maLopHienTai = (Integer) request.getAttribute("maLopHienTai");
				@SuppressWarnings("unchecked")
				ArrayList<DanhGia> listDanhGia = (ArrayList<DanhGia>) request.getAttribute("listDanhGia");
				@SuppressWarnings("unchecked")
				ArrayList<SinhVien> listSinhVien = (ArrayList<SinhVien>) request.getAttribute("listSinhVien");
				if (giangVien != null) {
				%>
<%if(giangVien.getChucVu().equals("truongkhoa")) { %>
<%@include file="/templates/giangvien/inc/header-truongkhoa.jsp"%>
<%} else { %>
<%@include file="/templates/giangvien/inc/header.jsp"%>
<%} %>

<div id="pageBody">
	<div class="row">
		
		<%@include file="/templates/giangvien/inc/left-bar.jsp"%>
		
		<div class="col-sm-10">
			<div id="ketQuaDanhGia">
				<!--  noi dung -->
				
			<%if (dotDanhGia == null) {%>
				<h4>Hiện tại không phải thời gian thực hiện chức năng này!</h4>
				<p>Vui lòng xem lại lịch đánh giá điểm rèn luyện.</p>
			<%} else { %>
				
				<h4 style="text-align: center;"> Đánh giá điểm rèn luyện <%=dotDanhGia.getTen()%></h4>
				
				<p><strong>Khoa: ${khoa.getTen() }</strong></p>
				<form action="<%=request.getContextPath() %>/giang-vien/gvcn/danh-sach-xac-nhan" method="post">
				<div class="row">
					<div class="col-sm-9" style="padding: 0px;">
						<select name="lop" class="form-control" id="sel1" onchange="this.form.submit()" style="width: 20%;">
							<%
								@SuppressWarnings("unchecked")
								ArrayList<Lop> listLop = (ArrayList<Lop>) request.getAttribute("listLop");
								if(listLop != null)
								for (Lop lop : listLop) {
							%>
							<%if(maLopHienTai != null && maLopHienTai == lop.getMaLop()) { %>
								<option value="<%=lop.getMaLop() %>" selected="selected"><%=lop.getTen() %></option>
							<%} else { %>
								<option value="<%=lop.getMaLop() %>" ><%=lop.getTen() %></option>
							<%}} %>
						</select>
					</div>
					<!-- <div class="col-sm-3"><p style="text-align: right;"><strong>Xác nhận 12/12</strong></p></div> -->
				</div>
				

				<table class="tablesorter-bootstrap">
					<thead>
						<tr>
							<th>Chọn</th>
							<th>MSSV</th>
							<th>Họ và tên</th>
							<th>Ngày sinh</th>
							<th>Điểm tự ĐG</th>
							<th>Điểm lớp ĐG</th>
							<th>Tình trạng</th>
							
							
						</tr>
					</thead>
					<tbody>
						<%if (listDanhGia != null && listSinhVien != null) { %>
							<%for (int i = 0; i < listDanhGia.size(); i++) {%>
						<tr>
							<td width="5%" style="text-align: center;"><input type="checkbox" name="chon" value="<%=listDanhGia.get(i).getMaDanhGia() %>"/></td>
							<td width="12%"><%=listSinhVien.get(i).getMssv() %></td>
								<td><%=listSinhVien.get(i).getTen() %></td>
								<td width="13%"><%=DateUtils.formatDate(listSinhVien.get(i).getNgaySinh()) %></td>
								<td width="14%"><%=listDanhGia.get(i).getTongDiem() %></td>
								<td width="15%"><%=listDanhGia.get(i).getDiemTapTheLop() %></td>
								<td width="15%">
									<%if (listDanhGia.get(i).getTinhTrang().equals("gvcndaduyet")) {%>
										<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
										<a href="<%=request.getContextPath() %>/giang-vien/xem-chi-tiet-danh-gia?id=<%=listDanhGia.get(i).getMaDanhGia() %>">Xem</a>
									<%} else if (listDanhGia.get(i).getTinhTrang().equals("tapthelopdaduyet")) { %>
										<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
										<a href="<%=request.getContextPath() %>/giang-vien/xem-chi-tiet-danh-gia?id=<%=listDanhGia.get(i).getMaDanhGia() %>">Xem</a>
									<%} %>
								</td>
						</tr>
						<%}}%>
					</tbody>
				</table>
				
				<div class="row">
					<div class="col-sm-4" style="padding: 0px;">
						<div class="input-group center-block" >
							<input type="text" name="page" value="${currentPage}" hidden="true">
							<button type="submit" class="btn btn-default" name="action" value="submit" >Xác nhận</button>
						</div>
					</div>
				
					<div class="col-sm-8" style="pad: 0px;">
						<ul class="pagination" style="margin: 0px;">
							<c:if test="${currentPage != 1}">
			        			<li><a href="<%=request.getContextPath() %>/giang-vien/gvcn/danh-sach-xac-nhan?lop=${maLopHienTai}&page=${currentPage - 1}">Previous</a></li>
			    			</c:if>
		 
		            		<c:forEach begin="1" end="${noOfPages}" var="i">
								<c:choose>
		                    		<c:when test="${currentPage eq i}">
		                        		<li class="active"><a href="<%=request.getContextPath() %>/giang-vien/gvcn/danh-sach-xac-nhan?lop=${maLopHienTai}&page=${i}">${i}</a></li>
		                   			 </c:when>
		                    		<c:otherwise>
		                        		<li><a href="<%=request.getContextPath() %>/giang-vien/gvcn/danh-sach-xac-nhan?lop=${maLopHienTai}&page=${i}">${i}</a></li>
		                    		</c:otherwise>
		                		</c:choose>
		            		</c:forEach>
		        			
							<c:if test="${currentPage lt noOfPages}">
		        				<li><a href="<%=request.getContextPath() %>/giang-vien/gvcn/danh-sach-xac-nhan?lop=${maLopHienTai}&page=${currentPage + 1}">Next</a></li>
		    				</c:if>
		    			</ul>
					</div>
				</div>
				</form>
				<%} %>
			</div>
		</div>
		
	</div>
	</div>
<%@include file="/templates/giangvien/inc/footer.jsp"%>
<%} %>
</div>
</body>
	<script src="<%=request.getContextPath()%>/Resources/bootstrap/jquery/jquery-1.11.3.min.js"></script>
	<script src="<%=request.getContextPath()%>/Resources/bootstrap/jquery/jquery.tablesorter.js"></script>
	<script src="<%=request.getContextPath()%>/Resources/bootstrap/jquery/jquery.tablesorter.widgets.js"></script>
	<script src="<%=request.getContextPath()%>/Resources/bootstrap/jquery/jquery.tablesorter.pager.js"></script>

    <script src="<%=request.getContextPath()%>/Resources/bootstrap/jquery/table.js"></script>
</html>