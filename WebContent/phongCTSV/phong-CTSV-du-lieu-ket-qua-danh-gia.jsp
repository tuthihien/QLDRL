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
					<div class="row">
						<form class="form-inline" role="form"
							action="<%=request.getContextPath()%>/phong-ctsv/du-lieu"
							method="post">

							<div class="" style="padding:15px 5px  0px 5px;float:left">
								<label for="faculty_list">Khoa</label> <select id="faculty_list"
									name="faculty" onchange="submit()" class="form-control">
									<option value="0" disabled="disabled" selected="selected">===Chọn
										Khoa===</option>
									<%
										Khoa khoa = (Khoa) request.getAttribute("khoa");
										ArrayList<Khoa> danhSachKhoa = (ArrayList<Khoa>) request.getAttribute("listKhoa");
										if (danhSachKhoa != null) {
											for (Khoa khoa2 : danhSachKhoa) {
									%>
									<option value="<%=khoa2.getMaKhoa()%>"
										<%if (khoa != null) {
						if (khoa.getMaKhoa() == khoa2.getMaKhoa()) {
							out.print("selected");
						}
					}%>><%=khoa2.getTen()%></option>
									<%
										}
										}
									%>
								</select>
							</div>

							<div class="" style="padding:15px 5px  0px 5px;float: left" id="ajax-data1">
								<label for="class_list">Lớp</label> <select style="margin-left: 5px" id="class_list"
									name="aIdLop" onchange="submit()" class="form-control">
									<option value="0" disabled="disabled" selected="selected">===Chọn
										lớp===</option>
									<%
										Lop lop = (Lop) request.getAttribute("lop");
										ArrayList<Lop> danhSachLop = (ArrayList<Lop>) request.getAttribute("listLopByKhoa");
										if (danhSachLop != null) {
											for (Lop lop1 : danhSachLop) {
									%>
									<option value="<%=lop1.getMaLop()%>"
										<%if (lop != null) {
						if (lop.getMaLop() == lop1.getMaLop()) {
							out.print("selected");
						}
					}%>><%=lop1.getTen()%></option>
									<%
										}
										}
									%>
								</select>
							</div>
							<div class=""  style="padding:15px 5px  0px 5px;display: block"  id="ajax-data2">
								<label for="dotbaocao_list">Học kỳ</label> <select
									id="class_list" name="dotDanhGia" onchange="submit()"
									class="form-control">
									<option value="0" disabled="disabled" selected="selected">===Học kỳ===</option>
									<%
										DotDanhGia dotDanhGia = (DotDanhGia) request.getAttribute("dotDanhGia");
										ArrayList<DotDanhGia> listDotDanhGia = (ArrayList<DotDanhGia>) request.getAttribute("listDotDanhGia");
										if (listDotDanhGia != null) {
											for (DotDanhGia dot : listDotDanhGia) {
									%>
									<option value="<%=dot.getMaDotDanhGia()%>"
										<%if (dotDanhGia != null) {
						if (dot.getMaDotDanhGia() == dotDanhGia.getMaDotDanhGia()) {
							out.print("selected");
						}
					}%>><%=dot.getTen()%></option>
									<%
										}
										}
									%>
								</select>
							</div>
						</form>
					<div class="clr"></div>
					</div>
				<div class="button" >
					<a style="width:100px ; margin:0px 15px 15px 30px" class="btn btn-info"
					href="<%=request.getContextPath()%>/phong-ctsv/in-an?dot=${dotDanhGia.getMaDotDanhGia() }">
					In ấn</a>
				</div>
					<div class="row">
						<div class="container-fluid">
							<form
								action="<%=request.getContextPath()%>/phong-ctsv/xacnhan-danhgia"
								method="post" name="input">
								
								<table id="example"
									class="table table-striped table-bordered dt-responsive nowrap"
									cellspacing="0" width="100%">
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
										<%ArrayList<SinhVien>listSinhVienByLop=(ArrayList<SinhVien>)request.getAttribute("listSinhVienByLop"); 
										ArrayList<DanhGia>listDanhGia=(ArrayList<DanhGia>)request.getAttribute("listDanhGiaXacNhanByDotDanhGiaVsLop"); 					
											if(listSinhVienByLop!=null){
												for(SinhVien sv:listSinhVienByLop){
													
												%>
												<tr style="font-size: 12px;">
													<td><%=sv.getMssv()%></td>
													<td><%=sv.getTen()%></td>
													<td><%=sv.getNgaySinh()%></td>
													<td>
													<%if(listDanhGia!=null){
															for(DanhGia dg:listDanhGia){
																if(dg.getMaSinhVien()==sv.getMaSinhVien()){
																	out.print(dg.getTongDiem());
																}
															}
															
														}%>
													</td>
													<td>
													<%if(listDanhGia!=null){
															for(DanhGia dg:listDanhGia){
																if(dg.getMaSinhVien()==sv.getMaSinhVien()){
																	out.print(dg.getDiemTapTheLop());
																}
															}
															
														}%>
													</td>
													<td>
													<%
													String [] listXepLoai = (String[]) request.getAttribute("listXepLoai");
													if(listDanhGia!=null && listXepLoai != null){
															for(int i = 0 ; i < listDanhGia.size() ; i++){
																if(listDanhGia.get(i).getMaSinhVien()==sv.getMaSinhVien()){
																	out.print(listXepLoai[i]);
																}
															}
															
														}%>
													</td>
														<td align="center"><a class=""
														href="<%=request.getContextPath()%>/phong-ctsv/chi-tiet-danh-gia?id=<%
														if (listDanhGia != null) {
															for (DanhGia dg : listDanhGia) {
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
			$('#example').DataTable();
		});

	</script>
			<script>
	$(document).ready( function() {
		$(':checkbox').checkboxpicker();

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
									faculty : idKhoa,
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
		 function doDotDanhGia(){
				var idKhoa=$('#faculty_list').val();
				var idLop=$('#class_list').val();
				$.ajax({
					url: "<%=request.getContextPath()%>/phong-ctsv/du-lieu",
								type : 'POST',
								cache : false,
								data : {
									//Dữ liệu gửi cho server
									faculty : idKhoa,
									aIdLop:idLop,
								},
								success : function(data) {
									//kết quả về của  server trả về khi xử lý thành công

									$("#ajax-data2").html(data);
								},
								error : function() {
									// Xử lý nếu có lỗi
									alert("Có lỗi trong quá trình xử lý")
								}
							});

				}
				function xacNhan() {
					var idKhoa = $('#faculty_list').val();
					$('#idKhoa').val(idKhoa);
					var idLop = $('#class_list').val();
					$('#idLop').val(idLop);
				}
			</script>
		</div>
	</div>
	<%@include file="/templates/sinhvien/inc/footer.jsp"%>
</div>
</body>
</html>