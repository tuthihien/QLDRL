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
				
				<form class="form-horizontal" action="" method="post" role="form">
					
					<div class="form-group">
						<label class="control-label col-sm-2" for="ten">Tên giảng
							viên</label>
						<div class="col-sm-10">
							<input name="ten" type="text" value=""
								class="form-control" id="ten" placeholder="Tên giảng viên" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="ngaySinh">Ngày
							sinh</label>
						<div class="col-sm-10">
							<input readonly="readonly" name="ngaySinh" type="text" id="datepicker"
								value=""
								class="form-control" id="ngaySinh" />
						</div>
					</div>
						<div class="form-group" >
								<label for="faculty_list" class="control-label col-sm-2">Khoa</label> 
								<div class="col-sm-10">
								<select id="faculty_list"
									name="faculty"  class="form-control" >
									<option value="0" disabled="disabled" selected="selected">===Chọn
										Khoa===</option>
									<% 
										ArrayList<Khoa> danhSachKhoa = (ArrayList<Khoa>) request.getAttribute("listKhoa");
										if (danhSachKhoa != null) {
											for (Khoa khoa2 : danhSachKhoa) {
									%>
									<option value="<%=khoa2.getMaKhoa()%>"><%=khoa2.getTen()%></option>
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
								class="form-control" id="soDienThoai" />
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
								<select id="faculty_list"
									name="chucVu"  class="form-control" >
									<option value="0" disabled="disabled" selected="selected">===Chức vụ===</option>
									<option value="truongkhoa" >Trưởng khoa</option>
									<option value="gvcn" >Giáo viên chủ nhiệm</option>
									<option value="truongphongCTSV" >Trưởng phòng CTSV</option>
								</select>
					</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="Active">Active</label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								<input type="checkbox" name="active" value="1"
									data-style="btn-group-justified">
							</div>
						</div>
					</div>
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