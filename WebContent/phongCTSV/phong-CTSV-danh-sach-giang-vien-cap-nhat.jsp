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
				<%
					GiangVien gv = (GiangVien) request.getAttribute("gv");
					ArrayList<Khoa> listKhoa = (ArrayList<Khoa>) request.getAttribute("listKhoa");
					if (gv != null) {
				%>
				<form class="form-horizontal" action="<%=request.getContextPath()%>/phong-ctsv/danhsach-giangvien-capnhat" method="post" role="form">
					<div class="form-group">
						<div class="col-sm-10">
							<input type="hidden" value="<%=gv.getMaGiangVien()%>"
								name="maGiangVien" class="form-control" id="maGiangVien" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="ten">Tên giảng
							viên</label>
						<div class="col-sm-10">
							<input name="ten" type="text" value="<%=gv.getTen()%>"
								class="form-control" id="ten" placeholder="Tên giảng viên" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="ngaySinh">Ngày
							sinh</label>
						<div class="col-sm-10">
							<input readonly="readonly" name="ngaySinh" type="text" id="datepicker"
								value="<%=DateUtils.formatDate(gv.getNgaySinh())%>"
								class="form-control" id="ngaySinh" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="khoa">Khoa</label>
						<div class="col-sm-10">
							<input type="text"
								value="<%if (listKhoa != null) {
					for (Khoa khoa : listKhoa) {
						if (khoa.getMaKhoa() == gv.getMaKhoa()) {
							out.println(khoa.getTen());
						}
					}

				}%>"
								class="form-control" id="khoa" placeholder="Khoa"
								readonly="readonly" /> <input type="hidden"
								value="<%=gv.getMaKhoa()%>" name="khoa" class="form-control"
								id="khoa" />
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-2" for="tenDangNhap">Tên
							đăng nhập</label>
						<div class="col-sm-10">
							<input readonly="readonly" name="tenDangNhap" type="text" placeholder="Tên đăng nhập"
								value="<%=gv.getTenDangNhap()%>" class="form-control"
								id="tenDangNhap" />
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-2" for="matKhau">Mật
							khẩu</label>
						<div class="col-sm-10">
							<input readonly="readonly" name="matKhau" type="text" placeholder="Mật khẩu"
								value="<%=gv.getMatKhau()%>" class="form-control" id="matKhau" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="soDienThoai">Số
							điện thoại</label>
						<div class="col-sm-10">
							<input name="soDienThoai" type="tel" value="<%=gv.getSdt()%>"
								class="form-control" id="soDienThoai" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">Email</label>
						<div class="col-sm-10">
							<input name="email" type="email" placeholder="email"
								value="<%=gv.getEmail()%>" class="form-control" id="email" />
						</div>
					</div>
					<div class="form-group">
						<label for="faculty_list" class="control-label col-sm-2">Chức vụ</label> 
								<div class="col-sm-10">
								<select id="faculty_list"
									name="chucVu"  class="form-control" >
									<option value="0" disabled="disabled" selected="selected">===Chức vụ===</option>
									<option value="truongkhoa" <%if("truongkhoa".equals(gv.getChucVu())){out.print("selected");} %> >Trưởng khoa</option>
									<option value="gvcn" <%if("gvcn".equals(gv.getChucVu())){out.print("selected");} %> >Giáo viên chủ nhiệm</option>
									<option value="ctsv" <%if("ctsv".equals(gv.getChucVu())){out.print("selected");} %> >Trưởng phòng CTSV</option>
								</select>
					</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="Active">Active</label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								<input type="checkbox" name="active" value="1"
									<%if (gv.isActive() == true) {
					out.println("checked");
				}%>
									data-style="btn-group-justified">
							</div>
						</div>
					</div>

					<div class="container">
						<div class="form-group">
							<div class="col-sm-offset-9 col-sm-10" style='margin-left: 50%'>
								<button type="submit" name="submit" class="btn btn-default">
									<span class="glyphicon glyphicon-ok"></span> Cập nhật
								</button>
								<button type="reset" class="btn btn-default">
									<span class="glyphicon glyphicon-repeat"></span> Nhập Lại
								</button>
								
							</div>
						</div>
					</div>
				</form>
				<%
					}
				%>
				<script>
					$(document).ready(function() {
						$(':checkbox').checkboxpicker();

					});
				</script>
				<!-- Datetime picker js  -->
		<script type="text/javascript" src="<%=request.getContextPath() %>/Resources/bootstrap-datepicker/js/bootstrap-datepicker.js" ></script>
		
		<script type="text/javascript">
			$('#datepicker').datepicker({
				format: 'dd/mm/yyyy',
			    minViewMode: "days"
		    });
		</script>
			</div>
		</div>
	</div>
	<%@include file="/templates/sinhvien/inc/footer.jsp"%>
</div>
</body>
</html>