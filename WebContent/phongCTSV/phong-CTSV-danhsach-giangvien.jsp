<%@page import="model.bean.GiangVien"%>
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

<div class="container" style="max-width: 1026px">
	<%@include file="/templates/phongctsv/inc/header.jsp"%>
	<div id="pageBody">
		<div class="row">
<link href="<%=request.getContextPath()%>/Resources/bootstrap-checkbox-montre/bootstrap-ck.css" rel="stylesheet"/>
			<%@include file="/templates/phongctsv/inc/left-bar.jsp"%>
			<div class="col-sm-10">
				<!--  noi dung -->
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
							<div class="alert alert-warning" role="alert">Chưa có giảng viên nào được chọn</div>
							<%
						}}} %>
					<div class="row">
						<form class="form-inline" role="form" action="<%=request.getContextPath()%>/phong-ctsv/danhsach-giangvien" method="post">
							
							<div class="form-group" style="padding:15px 15px 5px 15px">
								<label for="faculty_list">Khoa</label> <select class="form-control"  id="faculty_list"
									name="faculty" onchange="submit()">
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
						</form>
					</div>
					<p>
						<a style="margin-left: 20px" class="btn btn-success" href="<%=request.getContextPath()%>/phong-ctsv/danhsach-giangvien-them">Tạo<i class="fa fa-plus"></i></a>
					</p>
				
						
							<form action="<%=request.getContextPath()%>/phong-ctsv/danhsach-giangvien-xoa" method="post" name="input">
							<input type="hidden" value="" name="idKhoa" id="idKhoa"> 
							<table id="example"
								class="table table-striped table-bordered dt-responsive nowrap"
								cellspacing="0" width="100%">
								<thead>
									<tr style="font-size: 13px;">
										<th>Mã Giảng Viên</th>
										<th>Tên</th>
										<th>Khoa</th>
										<th>Email</th>
										<th >Chức vụ</th>
										<th >Sửa</th>
										<th><button style="width:70px" type="submit" class="btn btn-primary"  onclick="setKhoa()">Xóa</button></th>
										
									</tr>
								</thead>
								<tbody>
								<% ArrayList<GiangVien> listGiangVienByKhoa=(ArrayList<GiangVien>)request.getAttribute("listGiangVienByKhoa");
									if(listGiangVienByKhoa!=null){
										for(GiangVien gv :listGiangVienByKhoa){
								%>
									<tr style="font-size: 13px;">	
										<td width="40px" align="center"><%=gv.getMaGiangVien()%></td>
										<td align="center"><%=gv.getTen() %></td>
										<td align="center"><%=khoa.getTen() %></td>
										<td style="width:30px" align="center"><%=gv.getEmail() %></td>
										<td align="center"><%=gv.getChucVu()%></td>
										<td align="center" style="width:20%"><a class="btn btn-info"
											href="<%=request.getContextPath()%>/phong-ctsv/danhsach-giangvien-capnhat?idGV=<%=gv.getMaGiangVien()%>">
								 			Sửa</a>
								 		</td>
								 			
								 		<td align="center">
											<input  style="width:10px" name="chk" value="<%=gv.getMaGiangVien()%>" type="checkbox" data-style="btn-group-sm">						
										</td>
												
									</tr>
								<%}} %>
								</tbody>
							</table>
   						
   
							</form>
						
					
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
		 function setKhoa()
			{
				var idKhoa= $('#faculty_list').val();
				$('#idKhoa').val(idKhoa);
			}
				</script>
			</div>
		</div>
	<%@include file="/templates/phongctsv/inc/footer.jsp"%>
</div>
</body>
</html>