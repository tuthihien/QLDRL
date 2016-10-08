<%@page import="model.bo.LopBO"%>
<%@page import="java.sql.Array"%>
<%@page import="utils.DateUtils"%>
<%@page import="model.bean.Lop"%>
<%@page import="model.bean.Khoa"%>
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
	<%@include file="/templates/phongctsv/inc/header.jsp"%>
	<div id="pageBody">
		<div class="row">

			<%@include file="/templates/phongctsv/inc/left-bar.jsp"%>

			<div class="col-sm-10">
				<!--  noi dung -->
				<div class="container-fluid">
					<% String errMsg=(String)request.getAttribute("errMsg");
							if(errMsg!=null){
								if(Integer.parseInt(errMsg)==1){
						%>
						<div class="alert alert-success" role="alert">Xác nhận thành công</div>
						<%}else{
							if(Integer.parseInt(errMsg)==0){
							%>
							<div class="alert alert-danger" role="alert">Xác nhận thất bại</div>
						<%}else{
							%>
							<div class="alert alert-warning" role="alert">Chưa có sinh viên nào được chọn</div>
							<%
						}}} %>
					<div class="row">
						<form class="form-inline" role="form" action="<%=request.getContextPath()%>/phong-ctsv/danhsach-ketqua" method="post">
							
							<div class="form-group" style="padding: 15px">
								<label for="faculty_list">Khoa</label> <select id="faculty_list"
									name="faculty" onchange="doKhoa()" class="form-control">
									<option value="0" disabled="disabled" selected="selected">===Chọn
										Khoa===</option>
									<% Khoa khoa =(Khoa)request.getAttribute("khoa");
										ArrayList<Khoa> danhSachKhoa = (ArrayList<Khoa>) request.getAttribute("listKhoa");
										if (danhSachKhoa != null) {
											for (Khoa khoa2 : danhSachKhoa) {
									%>
									<option value="<%=khoa2.getMaKhoa()%>"<%if(khoa!=null){ if(khoa.getMaKhoa()==khoa2.getMaKhoa()){out.print("selected");} }%>><%=khoa2.getTen()%></option>
									<%
										}
										}
									%>
								</select>
							</div>

							<div class="form-group" style="padding: 15px" id="ajax-data1">
								<label for="class_list">Lớp</label> <select id="class_list"
									name="class" onchange="submit()" class="form-control">
									<option value="0" disabled="disabled" selected="selected" >===Chọn lớp===</option>
									<%	Lop lop =(Lop)request.getAttribute("lop");
										ArrayList<Lop> danhSachLop = (ArrayList<Lop>) request.getAttribute("listLopByKhoa");
										if (danhSachLop != null) {
											for (Lop lop1 : danhSachLop) {
									%>
									<option value="<%=lop1.getMaLop()%>" <%if(lop!=null){if(lop.getMaLop()==lop1.getMaLop()){out.print("selected");}} %>><%=lop1.getTen()%></option>
									<%
										}
										}
									%>
								</select>
							</div>
						</form>
					</div>

					<div class="row">
						<div class="container-fluid">
							<form action="<%=request.getContextPath()%>/phong-ctsv/xacnhan-danhgia" method="post" name="input">
							<input type="hidden" value="" name="xacnhan" id="xacnhan"> 
							<input type="hidden" value="" name="idKhoa" id="idKhoa"> 
							<input type="hidden" value="" name="idLop" id="idLop"> 
							<table id="example"
								class="table table-striped table-bordered dt-responsive nowrap"
								cellspacing="0" width="100%">
								<thead>
									<tr>
										<th>MSSV</th>
										<th>Họ Và Tên</th>
										<th>Lớp</th>
										<th>Ngày báo cáo</th>
										<th>Điểm</th>
										<th>Trạng thái </br>
											 <button type="submit" class="btn btn-primary"  onclick="xacNhan()">Xác nhận</button>
										</th>
										<th>Xem</th>
										
									</tr>
								</thead>
								<tbody>
									<%	
										ArrayList<SinhVien> danhSachSinhVien = (ArrayList<SinhVien>) request.getAttribute("listSinhVienXacNhanByLop");
										ArrayList<DanhGia> danhSachDanhGia = (ArrayList<DanhGia>) request.getAttribute("listDanhGiaXacNhan");
										if (danhSachSinhVien != null) {
											for (SinhVien sv : danhSachSinhVien) {
									%>

						<tr>
							<td><%=sv.getMssv()%></td>
							<td><%=sv.getTen()%></td>
							<td>
								<%=lop.getTen()
								%>
							</td>
							<td>
								<%
									if (danhSachDanhGia != null) {
												for (DanhGia dg : danhSachDanhGia) {
													if (dg.getMaSinhVien() == sv.getMaSinhVien()) {
														out.println(DateUtils.formatDatetime(dg.getNgayDanhGia()));
													}
												}
											}
								%>
							</td>
							<td>
								<%
									if (danhSachDanhGia != null) {
												for (DanhGia dg : danhSachDanhGia) {
													if (dg.getMaSinhVien() == sv.getMaSinhVien()) {
														out.println(dg.getTongDiem());
													}
												}
											}
								%>
							</td>
							<td align="center">
								<%
									if (danhSachDanhGia != null) {
												for (DanhGia dg : danhSachDanhGia) {
													if (dg.getMaSinhVien() == sv.getMaSinhVien()) {
														if (!"hoanthanh".equals(dg.getTinhTrang())) {
								%> 
									<input type="checkbox"  name="chk" value="<%=dg.getMaDanhGia()%>">
								<%
 	} else {
 %> <input type="checkbox"  value="<%=dg.getMaDanhGia()%>"  disabled checked> <%
 	}
 					}
 				}
 			}
 %>
							</td>
							<td align="center"><a class=""
								href="<%=request.getContextPath()%>/phong-ctsv/chi-tiet-danh-gia?id=<%
										if (danhSachDanhGia != null) {
											for (DanhGia dg : danhSachDanhGia) {
												if (dg.getMaSinhVien() == sv.getMaSinhVien()) {
													out.print(dg.getMaDanhGia());	
												}
												}}
								%>">
								 <button type="button" class="btn btn-info">Xem</button>
								</a></td>
						</tr>
						<%
							}
						%><%					
							} 
						%>
								</tbody>
							</table>
								
    
    </div>
   
			</form>
						
						</div>
					</div>
				</div>
	<script type="text/javascript">
	$(document).ready( function() {
		$(':checkbox').checkboxpicker();

	});
	</script>
	<script type="text/javascript">
		$(document).ready( function() {
			$('#example').DataTable();
		});

	</script>
	
	
	
				<script>
				
		 function doKhoa(){
				var idKhoa=$('#faculty_list').val();
				$.ajax({
					url: "<%=request.getContextPath()%>/phong-ctsv/danhsach-ketqua",
					type : 'POST',
					cache : false,
					data : {
						//Dữ liệu gửi cho server
						faculty :idKhoa,
					},
					success : function(data) {
						//kết quả về của  server trả về khi xử lý thành công
						
					$("#ajax-data1").html(data);
					},
					error : function() {
						// Xử lý nếu có lỗi
						alert("Có lỗi trong quá trình xử lý")
					}
				});
				
			}
		 function xacNhan()
			{
				var idKhoa= $('#faculty_list').val();
				$('#idKhoa').val(idKhoa);
				var idLop= $('#class_list').val();
				$('#idLop').val(idLop);
			}
				</script>
			</div>
		</div>
	<%@include file="/templates/sinhvien/inc/footer.jsp"%>
</div>
</body>
</html>