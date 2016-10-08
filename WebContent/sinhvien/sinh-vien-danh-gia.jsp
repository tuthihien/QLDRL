<%@page import="model.bean.SinhVien"%>
<%@page import="utils.DateUtils"%>
<%@page import="model.bean.DanhGia"%>
<%@page import="model.bean.DotDanhGia"%>
<%@page import="model.bean.NoiDungDanhGia"%>
<%@page import="model.bean.MucDanhGia"%>
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
			<div id="sinhVienDanhGia">
			<%if(dotDanhGia == null) {%>
					<h4 style="text-align: left;">Hiện tại không phải thời gian thực hiện chức năng này!</h4>
					<p>Vui lòng xem lại lịch đánh giá điểm rèn luyện.</p>
					<%
				} else if (danhGia != null) { 
			%>
				<h4> Đánh giá điểm rèn luyện <%=dotDanhGia.getTen()%></h4>
				<div class="thongTinDanhGia">
					<p>Bạn đã đánh giá điểm rèn luyện vào lúc <%=DateUtils.formatDatetime(danhGia.getNgayDanhGia()) %></p>
					<p>Nhấn vào <a href="<%=request.getContextPath() %>/sinh-vien/ket-qua-danh-gia?id=<%=danhGia.getMaDotDanhGia() %>"><strong>đây</strong></a> để xem kết quả đánh giá</p>
				</div>
			<%
				} else if (listMucDanhGia != null) {
			%>
			<h4> Đánh giá điểm rèn luyện <%=dotDanhGia.getTen()%></h4>
			<form action="<%=request.getContextPath() %>/sinh-vien/danh-gia" method="post">	
			
			
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
						for (int j = 0; j < listNoiDungDanhGia.size(); j++) {
					%>
					
					<tr>
						<td>
							<table class="table">
								<tr>
									<td><p><strong><%=i + 1 %>.<%=j + 1 %>/ </strong><%=listNoiDungDanhGia.get(j).getNoiDung() %></p></td>
									<td class="text-right" style="width: 10%"><p><%=listNoiDungDanhGia.get(j).getDiemToiDa() %> điểm</p></td>
								</tr>
							</table>
						</td>

						<td style="width: 10%"><select name="diemTuDanhGia<%=i %>.<%=j %>" id="input"
							class="form-control" required="required">
					<%
						for (int m = 0; m <= listNoiDungDanhGia.get(j).getDiemToiDa(); m++) {
					%>
								<option value="<%=m %>"><%=m %></option>
					<%
						}
					%>

						</select></td>
						<td style="width: 10%"><select name="diem" id="inputDiem"
							class="form-control" required="required" disabled="disabled">
					<%
						for (int n = 0; n <= listNoiDungDanhGia.get(j).getDiemToiDa(); n++) {
					%>
								<option value="<%=n %>"><%=n %></option>
					<%
						}
					%>
						</select></td>

					</tr>

			<%
					}
				}
			%>
					
				
				</table>
			
			<input hidden="true" name="tongDiem" value="<%=tongDiem %>"/>
			<button type="submit" class="btn btn-default center-block" name="action" value="submit">Xác nhận</button>
		</form>		
		<%
			} }
		%>
		</div>
			</div>
		</div>
	</div>
<%@include file="/templates/sinhvien/inc/footer.jsp"%>
</div>
</body>
</html>