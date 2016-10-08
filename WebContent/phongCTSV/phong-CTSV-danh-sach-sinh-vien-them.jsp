<%@page import="model.bean.GiangVien"%>
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
				<%String errMsg=(String)request.getAttribute("errMsg1");
					if(errMsg!=null){
					%>
						<div class="alert alert-danger" role="alert">Mã số sinh viên bị trùng</div>
					<%
					}
				%>
				<form id="sinhvien-form" class="form-horizontal" action="<%=request.getContextPath()%>/phong-ctsv/danhsach-sinhvien-them" method="post" role="form">
				<%	
					SinhVien sv = (SinhVien) request.getAttribute("sv");
					ArrayList<Khoa> listKhoa = (ArrayList<Khoa>) request.getAttribute("listKhoa");
					if (sv != null) {
				%>
					<div class="form-group">
						<label class="control-label col-sm-2" for="MSSV">Mã số sinh viên</label>
						<div class="col-sm-10">
							<input name="mssv" type="text" value="" id="loi"
								class="form-control"  placeholder="Mã số sinh viên" onkeypress="doError()"/>
						</div>
					<div  id="ajax-data2">
						
					</div>	
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="ten">Tên sinh
							viên</label>
						<div class="col-sm-10">
							<input name="ten" type="text" value="<%=sv.getTen()%>"
								class="form-control" id="ten" placeholder="Tên sinh viên" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="ngaySinh">Ngày
							sinh</label>
						<div class="col-sm-10">
							<input readonly="readonly" name="ngaySinh" id="datepicker" type="text" placeholder="Ngày sinh"
								value="<%=DateUtils.formatDate(sv.getNgaySinh())%>"
								class="form-control" id="ngaySinh" />
						</div>
					</div>
					<div class="form-group">
						<label for="faculty_list" class="control-label col-sm-2">Giới tính</label> 
								<div class="col-sm-10">
								<select id=""
									name="gioiTinh"  class="form-control" >
									<option value="0" disabled="disabled" selected="selected">===Giới tính===</option>
									<option value="nam" <%if("nam".equals(sv.getGioiTinh())){out.print("selected");} %> >Nam</option>
									<option value="nu" <%if("nu".equals(sv.getGioiTinh())){out.print("selected");} %> >Nữ</option>
								</select>
					</div>
					</div>
					<div class="form-group" >
								<label for="faculty_list" class="control-label col-sm-2">Khoa</label>  
						<div class="col-sm-10">			
								<select id="faculty_list"
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
					</div>	
					<div class="form-group"  id="ajax-data1">
								<label for="class_list" class="control-label col-sm-2">Lớp</label>  
						<div class="col-sm-10">			
								<select id="class_list"
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
					</div>
					<div class="form-group">
						<label  class="control-label col-sm-2" for="tenDangNhap">Tên
							đăng nhập</label>
						<div class="col-sm-10">
							<input readonly="readonly" name="tenDangNhap" type="text" placeholder="Tên đăng nhập"
								value="<%=sv.getTenDangNhap()%>" class="form-control"
								id="tenDangNhap" />
						</div>
					</div>

					<div class="form-group">
						<label  class="control-label col-sm-2" for="matKhau">Mật
							khẩu</label>
						<div class="col-sm-10">
							<input readonly="readonly" name="matKhau" type="text" placeholder="Mật khẩu"
								value="<%=sv.getMatKhau()%>" class="form-control" id="matKhau" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="soDienThoai">Số
							điện thoại</label>
						<div class="col-sm-10">
							<input name="soDienThoai" type="tel" value="<%=sv.getSdt()%>"
								class="form-control" id="soDienThoai" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">Email</label>
						<div class="col-sm-10">
							<input name="email" type="email" placeholder="email"
								value="<%=sv.getEmail()%>" class="form-control" id="email" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="">Điểm Học Bổng</label>
						<div class="col-sm-10">
							<input name="diemHocBong" type="text" placeholder="Điểm học bổng"
								value="<%=sv.getDiemHocBong()%>" class="form-control" id="diemHocBong" />
						</div>
					</div>
					<div class="form-group">
						<label for="faculty_list" class="control-label col-sm-2">Chức vụ</label> 
								<div class="col-sm-10">
								<select id=""
									name="chucVu"  class="form-control" >
									<option value="0" disabled="disabled" selected="selected">===Chức vụ===</option>
									<option value="loptruong" <%if("loptruong".equals(sv.getChucVu())){out.print("selected");} %> >Lớp trưởng</option>
									<option value="thanhvien" <%if("thanhvien".equals(sv.getChucVu())){out.print("selected");} %> >Thành viên</option>
								</select>
					</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="Active">Active</label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								<input type="checkbox" name="active" value="1"
									<%if (sv.isActive() == true) {
					out.println("checked");
				}%>
									data-style="btn-group-justified">
							</div>
						</div>
					</div>
				<%
					}else{
				%>
				
					<div class="form-group">
						<label class="control-label col-sm-2" for="MSSV">Mã số sinh viên</label>
						<div class="col-sm-10">
							<input name="mssv" type="text" value=""
								class="form-control" id="loiMaSV" placeholder="Mã số sinh viên" onkeypress="doError()" />
						</div>
					</div>
					<div  id="ajax-data2">
						
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="ten">Tên sinh
							viên</label>
						<div class="col-sm-10">
							<input name="ten" type="text" value=""
								class="form-control" id="ten" placeholder="Tên sinh viên" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="ngaySinh">Ngày
							sinh</label>
						<div class="col-sm-10">
							<input readonly="readonly" name="ngaySinh" id="datepicker" type="text" placeholder="Ngày sinh"
								value=""
								class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="faculty_list" class="control-label col-sm-2">Giới tính</label> 
								<div class="col-sm-10">
								<select id=""
									name="gioiTinh"  class="form-control" >
									<option value="0" disabled="disabled" selected="selected" >===Giới tính===</option>
									<option value="Nam" >Nam</option>
									<option value="Nu" >Nữ</option>
								</select>
					</div>
					</div>
					<div class="form-group" >
								<label for="faculty_list" class="control-label col-sm-2">Khoa</label>  
						<div class="col-sm-10">			
								<select id="faculty_list"
									name="faculty" onchange="doKhoa()" class="form-control">
									<option value="0" disabled="disabled" selected="selected" >===Chọn
										Khoa===</option>
									<% Khoa khoa =(Khoa)request.getAttribute("khoa");
										ArrayList<Khoa> danhSachKhoa = (ArrayList<Khoa>) request.getAttribute("listKhoa");
										if (danhSachKhoa != null) {
											for (Khoa khoa2 : danhSachKhoa) {
												if(khoa2.getMaKhoa()!=2){
									%>
									<option value="<%=khoa2.getMaKhoa()%>" <%if(khoa!=null){ if(khoa.getMaKhoa()==khoa2.getMaKhoa()){out.print("selected");} }%>><%=khoa2.getTen()%></option>
									<%
										}
										}
										}
									%>
								</select>
							</div>
					</div>	
					<div class="form-group"  id="ajax-data1">
								<label for="class_list" class="control-label col-sm-2" selected="selected">Lớp</label>  
						<div class="col-sm-10">			
								<select id="class_list"
									name="class" onchange="submit()" class="form-control">
									<option value="0" disabled="disabled" selected="selected">===Chọn lớp===</option>
									<%	Lop lop =(Lop)request.getAttribute("lop");
										ArrayList<Lop> danhSachLop = (ArrayList<Lop>) request.getAttribute("listLopByKhoa");
										if (danhSachLop != null) {
											for (Lop lop1 : danhSachLop) {
									%>
									<option value="<%=lop1.getMaLop()%>" ><%=lop1.getTen()%></option>
									<%
										}
										}
									%>
								</select>
							</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="soDienThoai">Số
							điện thoại</label>
						<div class="col-sm-10">
							<input name="soDienThoai" type="tel" value=""
								class="form-control" placeholder="Số điện thoại" id="soDienThoai" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">Email</label>
						<div class="col-sm-10">
							<input name="email" type="email" placeholder="email"
								value="" class="form-control" id="email" />
						</div>
					</div>
					
					<div class="form-group">
						<label for="faculty_list" class="control-label col-sm-2">Chức vụ</label> 
								<div class="col-sm-10">
								<select id=""
									name="chucVu"  class="form-control" >
									<option value="0" disabled="disabled" >===Chức vụ===</option>
									<option value="loptruong" >Lớp trưởng</option>
									<option value="thanhvien" >Thành viên</option>
								</select>
					</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="Active">Active</label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								<input type="checkbox" name="active" value="1"
									checked="checked" data-style="btn-group-justified">
							</div>
						</div>
					</div>
					<%} %>			
					<div class="container">
						<div class="form-group">
							<div class="col-sm-offset-9 col-sm-10" style='margin-left: 50%'>
								<button type="submit" name="submit" class="btn btn-default">
									<span class="glyphicon glyphicon-ok"></span> Thêm
								</button>
								<button type="reset" class="btn btn-default">
									<span class="glyphicon glyphicon-repeat"></span> Nhập Lại
								</button>
								
							</div>
						</div>
					</div>
				</form>
				
					<script type="text/javascript">
	$(document).ready( function() {
		$(':checkbox').checkboxpicker();

	});
	</script>
				<script>
				 function doKhoa(){
						var idKhoa=$('#faculty_list').val();
						$.ajax({
							url: "<%=request.getContextPath()%>/phong-ctsv/danhsach-sinhvien-them",
							type : 'POST',
							cache : false,
							data : {
								//Dữ liệu gửi cho server
								khoa :idKhoa,
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
				 function doError1(){
						var Mssv=$('#loiMaSV').val();
						$.ajax({
							url: "<%=request.getContextPath()%>/phong-ctsv/danhsach-sinhvien-them",
							type : 'POST',
							cache : false,
							data : {
								//Dữ liệu gửi cho server
								aMssv:Mssv,
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
				 function doError2(){
					 var Mssv=$('#loiMaSV').val();
					 alert(Mssv);
				 }
				</script>
					<script type="text/javascript" src="<%=request.getContextPath() %>/Resources/bootstrap-datepicker/js/bootstrap-datepicker.js" ></script>
		
		<script type="text/javascript">
			$('#datepicker').datepicker({
				format: 'dd/mm/yyyy',
			    minViewMode: "days"
		    });
		</script>
		<script type="text/javascript">
	$(document).ready(function() {
		var validator = $("#sinhvien-form").bootstrapValidator({
			feedbackIcons : {
				valid : "glyphicon glyphicon-ok",
				invalid : "glyphicon glyphicon-remove",
				validating : "glyphicon glyphicon-refresh"
			},
			fields : {
				mssv : {
					validators : {
						notEmpty : {
							message : "Nhập vào mã số sinh viên"
						},
					}
				},
				ten : {
					validators : {
						notEmpty : {
							message : "Nhập vào tên sinh viên"
						},
					}
				},
				
				faculty : {
					validators : {
						notEmpty : {
							message : "Vui lòng chọn khoa"
						},
					}
				},
				'class' : {
					validators : {
						notEmpty : {
							message : "Vui lòng chọn lop"
						},
					}
				},
				gioiTinh : {
					validators : {
						notEmpty : {
							message : "Vui lòng chọn giới tính"
						},
					}
				},
				soDienThoai : {
					validators : {
						 notEmpty: {
	                            message: 'Vui lòng nhập vào số điện thoại'
	                        },
	                        digits: {
	                            message: 'Nhập vào đúng định dạng số điện thoại'
	                        }
					},
				},
				diemHocBong : {
					validators : {
						notEmpty : {
							message : "Vui lòng nhập vào điểm học bổng"
						},

						numeric : {
							message : 'Điểm học bổng k hợp lệ'
						},
					},
				},
				email : {
					validators : {
						notEmpty : {
							message : "Vui lòng nhập vào email"
						},

						email : {
							message : 'Vui lòng nhập đúng định dạng email'
						},
					}
				},
				chucVu : {
					validators : {
						notEmpty : {
							message : "Vui lòng chọn chức vụ "
						},
					}
				},
			}
		});
	});
</script>
			</div>
		</div>
	</div>
	<%@include file="/templates/phongctsv/inc/footer.jsp"%>
</div>
</body>
</html>