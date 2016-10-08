<%@page import="model.bean.SinhVien"%>
<%@page import="model.bean.DanhGia"%>
<%@page import="model.bean.ChiTietDanhGia"%>
<%@page import="model.bean.DotDanhGia"%>
<%@page import="model.bean.MucDanhGia"%>
<%@page import="model.bean.NoiDungDanhGia"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="container">
				<%	
				@SuppressWarnings("unchecked")
				ArrayList<MucDanhGia> listMucDanhGia = (ArrayList<MucDanhGia>) request.getAttribute("listMucDanhGia");
				DotDanhGia dotDanhGia = (DotDanhGia) request.getAttribute("dotDanhGia");
				DanhGia danhGia = (DanhGia) request.getAttribute("danhGia");
				SinhVien sinhVien = (SinhVien) request.getAttribute("sinhVien");
				int tongDiem = 0;
				if (listMucDanhGia != null && sinhVien != null) {
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
				<% if(dotDanhGia == null) {%>
					<h4>Hiện tại không phải thời gian thực hiện chức năng này!</h4>
					<p>Vui lòng xem lại lịch đánh giá điểm rèn luyện.</p>
				<%} else {%>
				
			<form action="<%=request.getContextPath() %>/sinh-vien/tap-the-lop/danh-gia?id=<%=danhGia.getMaDanhGia() %>" method="post">	
			
			<div id="sinhVienDanhGia">
				<table class="table table-condensd">
					<thead>
						<tr>
							<th class="text-center"><span style="font-size: 20px">NỘI
									DUNG ĐÁNH GIÁ</span></th>
							<th class="text-center">Điểm tự đánh giá</th>
							<th class="text-center">Điểm tập thể lớp đánh giá</th>
						</tr>
					</thead>

					<!-- Muc danh gia -->
			<%
				for (int i = 0; i < listMucDanhGia.size(); i++) {
			%>
					<tr>
						<td colspan="3"><strong><%=listMucDanhGia.get(i).getTen() %></strong></td>
					</tr>

					<!-- Noi dung danh gia -->
					
					<%
						@SuppressWarnings("unchecked")
						ArrayList<NoiDungDanhGia> listNoiDungDanhGia = (ArrayList<NoiDungDanhGia>) request.getAttribute("listNoiDungDanhGia" + i);
						@SuppressWarnings("unchecked")
						ArrayList<ChiTietDanhGia> listChiTietDanhGia = (ArrayList<ChiTietDanhGia>) request.getAttribute("listChiTietDanhGia" + i);
						
					for (int j = 0; j < listNoiDungDanhGia.size(); j++) {
					%>
					
					<tr>
						<td>
							<table class="table" style="font-size: 14px">
								<tr>
			
									<td><strong><%=i + 1 %>.<%=j + 1 %>/ </strong><%=listNoiDungDanhGia.get(j).getNoiDung() %></td>
									<td class="text-right" style="width: 70px"><%=listNoiDungDanhGia.get(j).getDiemToiDa() %> điểm</td>
								</tr>
							</table>
						</td>

						<td style="width: 10%">
							<select name="diemTuDanhGia<%=i %>.<%=j %>" id="input" class="form-control" required="required">
									<option value="<%=listChiTietDanhGia.get(j).getDiemDanhGia() %>" selected="selected"><%=listChiTietDanhGia.get(j).getDiemDanhGia() %></option>
							</select >
						</td>
				
						<td style="width: 10%">
							<select name="diemTTLDanhGia<%=i %>.<%=j %>" id="inputDiem" class="form-control" required="required">
								<%for (int n = 0; n <= listNoiDungDanhGia.get(j).getDiemToiDa(); n++) {
										if (n == listChiTietDanhGia.get(j).getDiemDanhGia()) {%>
									<option value="<%=n %>" selected="selected"><%=n %></option>
								<%} else {%>
									<option value="<%=n %>"><%=n %></option>
								<%}}%>
							</select>
						</td>

					</tr>

			<%
					}
				}
			%>
					
				
				</table>
			</div>
			<div class="row">
				<div class="col-md-5 col-centered">
					<div class="input-group center-block" >
						<input type="text" name="page" value="${currentPage}" hidden="true">
						<button type="submit" class="btn btn-default" name="action" value="cancel" >Hủy</button>
						<button type="submit" class="btn btn-default" name="action" value="submit" >Xác nhận</button>
					</div>
				</div>
			</div>
		</form>		
		<%
				}}
		%>
		
			</div>
		</div>
	</div>
	<%@include file="/templates/sinhvien/inc/footer.jsp"%>
</div>
</body>
</html>