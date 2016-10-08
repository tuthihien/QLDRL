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
						<div class="alert alert-success" role="alert">Thành công</div>
						<%}else{
							if(Integer.parseInt(errMsg)==0){
							%>
							<div class="alert alert-danger" role="alert">Thất bại</div>
						<%}else{
							%>
							<div class="alert alert-warning" role="alert">Chưa có sinh viên nào được chọn</div>
							<%
						}}} %>
					<div class="row">
						<form class="form-inline" role="form" action="<%=request.getContextPath()%>/phong-ctsv/danhsach-sinhvien" method="post">
							
							<div class="form-group" style="padding: 15px">
								<label for="faculty_list">Khoa</label> <select id="faculty_list"
									name="faculty" onchange="doKhoa()" class="form-control">
									<option value="0" disabled="disabled" selected="selected">===Chọn
										Khoa===</option>
									<% Khoa khoa =(Khoa)request.getAttribute("khoa");
										ArrayList<Khoa> danhSachKhoa = (ArrayList<Khoa>) request.getAttribute("listKhoa");
										if (danhSachKhoa != null) {
											for (Khoa khoa2 : danhSachKhoa) {
												if(khoa2.getMaKhoa()!=2){
									%>
									<option value="<%=khoa2.getMaKhoa()%>"<%if(khoa!=null){ if(khoa.getMaKhoa()==khoa2.getMaKhoa()){out.print("selected");} }%>><%=khoa2.getTen()%></option>
									<%
										}
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
					<p>
						<a class="btn btn-success" href="<%=request.getContextPath()%>/phong-ctsv/danhsach-sinhvien-them">Tạo<i class="fa fa-plus"></i></a>
					</p>
					<div class="row">
						<div class="container-fluid">
							<form action="<%=request.getContextPath()%>/phong-ctsv/danhsach-sinhvien-xoa" method="post" name="input">
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
										<th>Giới tính</th>
										<th>Số điện thoại</th>
										<th>Chức vụ</th>
										<th>Sửa</th>
										<th> <button style="width:60px" type="submit" class="btn btn-primary"  onclick="setKL()">Xóa</button></th>
										
									</tr>
								</thead>
								<tbody>
									<%	
										ArrayList<SinhVien> danhSachSinhVien = (ArrayList<SinhVien>) request.getAttribute("listSinhVienByLop");
										if (danhSachSinhVien != null) {
											for (SinhVien sv : danhSachSinhVien) {
									%>

						<tr>
							<td><%=sv.getMssv()%></td>
							<td><%=sv.getTen()%></td>
							<td><%=lop.getTen()%></td>
							<td><%=sv.getGioiTinh()%></td>
							<td><%=sv.getSdt()%></td>
							<td><%=sv.getChucVu()%></td>
							<td align="center" style="width:95px"><a class="btn btn-info"
											href="<%=request.getContextPath()%>/phong-ctsv/danhsach-sinhvien-capnhat?idSV=<%=sv.getMaSinhVien()%>&&idLop=<%=sv.getMaLop()%>">
								 			Sửa</a>
								 		</td>
								 			
								 		<td>
											<input style="width:10px" name="chk" value="<%=sv.getMaSinhVien()%>" type="checkbox" data-style="btn-group-sm">						
										</td>
							
						<%
							}
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
					url: "<%=request.getContextPath()%>/phong-ctsv/danhsach-sinhvien",
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
		 function setKL()
			{
				var idKhoa= $('#faculty_list').val();
				$('#idKhoa').val(idKhoa);
				var idLop= $('#class_list').val();
				$('#idLop').val(idLop);
			}
				</script>
			</div>
		</div>
	</div>
	<%@include file="/templates/phongctsv/inc/footer.jsp"%>
</div>
</body>
</html>