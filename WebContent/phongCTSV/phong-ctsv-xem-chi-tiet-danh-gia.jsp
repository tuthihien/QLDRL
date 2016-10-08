<%@page import="model.bean.GiangVien"%>
<%@page import="model.bean.SinhVien"%>
<%@page import="model.bean.DanhGia"%>
<%@page import="model.bean.ChiTietDanhGia"%>
<%@page import="model.bean.DotDanhGia"%>
<%@page import="model.bean.MucDanhGia"%>
<%@page import="model.bean.NoiDungDanhGia"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
				<%	
				@SuppressWarnings("unchecked")
				ArrayList<MucDanhGia> listMucDanhGia = (ArrayList<MucDanhGia>) request.getAttribute("listMucDanhGia");
				DanhGia danhGia = (DanhGia) request.getAttribute("danhGia");
				GiangVien giangVien = (GiangVien) request.getAttribute("giangVien");
				if (giangVien!= null && listMucDanhGia != null) {
				%>
<div class="container">
	<%@include file="/templates/phongctsv/inc/header.jsp"%>
	<div id="pageBody">
		<div class="row">
			<%@include file="/templates/phongctsv/inc/left-bar.jsp"%>
			
			
			<div class="col-sm-10">
				<!--  noi dung -->
			
			<div id="sinhVienDanhGia">
			<div class="row" style="color: #3C763D">
				<h4 style="text-align: left">Đợt đánh giá: ${dotDanhGia.getTen() }</h4>
				<p><strong>Sinh viên: ${sinhVien.getTen() }, Lớp: ${lop.getTen() } </strong></p>
			</div>
				<table class="table table-condensd">
					<thead>
						<tr>
							<th class="text-center"><span style="font-size: 18px">NỘI
									DUNG ĐÁNH GIÁ</span></th>
							<th class="text-center">Điểm tự ĐG</th>
							<th class="text-center">Điểm TTL ĐG</th>
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
							<table class="table" style="margin: 0px;">
								<tr>
									<td><strong><%=i + 1 %>.<%=j + 1 %>/ </strong><%=listNoiDungDanhGia.get(j).getNoiDung() %></td>
									<td class="text-right" style="width: 70px"><%=listNoiDungDanhGia.get(j).getDiemToiDa() %> điểm</td>
								</tr>
							</table>
						</td>

						<td style="width: 10%"><select name="diemTuDanhGia<%=i %>.<%=j %>" id="input"
							class="form-control" required="required" disabled="disabled">
					<%
						for (int m = 0; m <= listNoiDungDanhGia.get(j).getDiemToiDa(); m++) {
							if (m == listChiTietDanhGia.get(j).getDiemDanhGia()) {
					%>
								<option value="<%=m %>" selected="selected"><%=m %></option>
					<%
						} else {
					%>
						<option value="<%=m %>"><%=m %></option>
					<%
							}
						}
					%>

						</select></td>
						<td style="width: 10%"><select name="diem" id="inputDiem"
							class="form-control" required="required" disabled="disabled">
					<%
						for (int n = 0; n <= listNoiDungDanhGia.get(j).getDiemToiDa(); n++) {
							if (n == listChiTietDanhGia.get(j).getDiemTapTheLop()) {
					%>
								<option value="<%=n %>" selected="selected"><%=n %></option>
					<%
						} else {
					%>
						<option value="<%=n %>"><%=n %></option>
					<%
						}
							}
					%>
						</select></td>

					</tr>

			<%
					}
				}
			%>
					
				
				</table>
			</div>
		
		<%
			}
		%>
		
			</div>
		</div>
	</div>
	<%@include file="/templates/phongctsv/inc/footer.jsp"%>
</div>
</body>
</html>