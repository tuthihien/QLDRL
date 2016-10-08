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
					SinhVien sinhVien = (SinhVien) request.getAttribute("sinhVien");
					DotDanhGia dotDanhGia = (DotDanhGia) request.getAttribute("dotDanhGia");
					@SuppressWarnings("unchecked")
					ArrayList<DanhGia> listDanhGia = (ArrayList<DanhGia>) request.getAttribute("listDanhGia");
					@SuppressWarnings("unchecked")
					ArrayList<DotDanhGia> listDotDanhGia = (ArrayList<DotDanhGia>) request.getAttribute("listDotDanhGia");
					@SuppressWarnings("unchecked")
					ArrayList<SinhVien> listSinhVien = (ArrayList<SinhVien>) request.getAttribute("listSinhVien");
					String [] listXepLoai = (String []) request.getAttribute("listXepLoai");
					int maDot = 0;
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
				
				<div class="row" style="margin-top: 10px; ">
					
					<form role="form" action="<%=request.getContextPath()%>/sinh-vien/tap-the-lop/du-lieu" method="post">
						<div class="col-sm-6" style="padding: 0px;">
							<select name="dot" class="form-control" id="sel1" onchange="this.form.submit()" style="width: 100%;">
								<%if (dotDanhGia!= null) for (int i = 0; i < listDotDanhGia.size(); i++) { %>
									<%if (dotDanhGia.getMaDotDanhGia() == listDotDanhGia.get(i).getMaDotDanhGia()) {%>
								<option value="<%=listDotDanhGia.get(i).getMaDotDanhGia() %>" selected="selected"><%=listDotDanhGia.get(i).getTen() %></option>
									<%maDot = dotDanhGia.getMaDotDanhGia(); } else { %>
								<option value="<%=listDotDanhGia.get(i).getMaDotDanhGia() %>" ><%=listDotDanhGia.get(i).getTen() %></option>
								<%}}%>
							</select>
						</div>
						
					<div class="col-sm-2">
						<a class="btn btn-success" href="<%=request.getContextPath()%>/sinh-vien/tap-the-lop/in-an?dot=<%=maDot %>&lop=<%=sinhVien.getMaLop() %>" style="margin-left: 10px;">In ấn</a>
					</div>
					
					<div class="col-sm-2">
						<p style="margin: 10px 10px 0px 10px;"><strong>Lớp: ${lop.getTen() }</strong></p>
					</div>
					</form>
				</div>
				<br>
				<table class="tablesorter-bootstrap">
					<thead>
						<tr>
							<th>MSSV</th>
							<th>Họ và tên</th>
							<th>Ngày sinh</th>
							<th>Điểm tự ĐG</th>
							<th>Điểm lớp ĐG</th>
							<th>Loại</th>
							<th>Xem</th>
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
									<a href="<%=request.getContextPath() %>/sinh-vien/ket-qua-danh-gia/chi-tiet?id=<%=listDanhGia.get(i).getMaDanhGia() %>">Xem</a>
								</td>
							</tr>
						<%}}%>
						
					
					</tbody>
				</table>
				
				<ul class="pagination">
					<c:if test="${currentPage != 1}">
	        			<li><a href="<%=request.getContextPath() %>/sinh-vien/tap-the-lop/du-lieu?dot=<%=dotDanhGia.getMaDotDanhGia() %>&page=${currentPage - 1}">Previous</a></li>
	    			</c:if>
 
            		<c:forEach begin="1" end="${noOfPages}" var="i">
						<c:choose>
                    		<c:when test="${currentPage eq i}">
                        		<li class="active"><a href="<%=request.getContextPath() %>/sinh-vien/tap-the-lop/du-lieu?dot=<%=dotDanhGia.getMaDotDanhGia() %>&page=${i}">${i}</a></li>
                   			 </c:when>
                    		<c:otherwise>
                        		<li><a href="<%=request.getContextPath() %>/sinh-vien/tap-the-lop/du-lieu?dot=<%=dotDanhGia.getMaDotDanhGia() %>&page=${i}">${i}</a></li>
                    		</c:otherwise>
                		</c:choose>
            		</c:forEach>
        			
					<c:if test="${currentPage lt noOfPages}">
        				<li><a href="<%=request.getContextPath() %>/sinh-vien/tap-the-lop/du-lieu?dot=<%=dotDanhGia.getMaDotDanhGia() %>&page=${currentPage + 1}">Next</a></li>
    				</c:if>
    			</ul>
				
				
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