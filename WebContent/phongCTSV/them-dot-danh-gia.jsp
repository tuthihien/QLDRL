<%@page import="java.util.Date"%>
<%@page import="utils.DateUtils"%>
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
					
				%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/Resources/bootstrap/css/table.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/Resources/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" />
<div class="container" >
	<%@include file="/templates/phongctsv/inc/header.jsp"%>
	<div id="pageBody">
		<div class="row">
			<%@include file="/templates/phongctsv/inc/left-bar.jsp"%>		
			<div class="col-sm-10" align="center">
				<!--  noi dung -->
				<div id="sinhVienDanhGia">
					<h3>Mở đợt đánh giá Điểm Rèn Luyện</h3><br>
				</div>
			
					<form class="form-horizontal" role="form" method="post" action="">
						  <div class="form-group">
						    <label class="control-label col-sm-4" for="email">Tên</label>
						    <div class="col-sm-6">
						      <input type="text" class="form-control" id="name" name="ten" value="">
						    </div>
						  </div>
						  <div class="form-group">
						    <label class="control-label col-sm-4" for="pwd">Ngày BĐ Sinh Viên</label>
						    <div class="col-sm-6">
						    	<div class="input-group date form_date" data-date="" data-date-format="dd/mm/yyyy hh:ii:ss" data-link-field="dtp_input2" data-link-format="dd/mm/yyyy hh:ii:ss">
			            			<input class="form-control" type="datetime"  value="<%=DateUtils.formatDatetime(new Date()) %>" readonly>
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
									<input type="hidden" id="dtp_input2" name="ngaybdsv" value="<%=DateUtils.formatDatetime(new Date()) %>" />
			            		</div>
						  	</div>
						  </div>
						  <div class="form-group">
						    <label class="control-label col-sm-4" for="pwd">Ngày KT Sinh Viên</label>
						    <div class="col-sm-6"> 
						     	<div class="input-group date form_date" data-date="" data-date-format="dd/mm/yyyy hh:ii" data-link-field="dtp_input2" data-link-format="dd/mm/yyyy hh:ii">
			            			<input class="form-control" type="datetime"  value="<%=DateUtils.formatDatetime(new Date()) %>" readonly>
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
									<input type="hidden" id="dtp_input2" name="ngayktsv" value="<%=DateUtils.formatDatetime(new Date()) %>" />
			            		</div>
						    </div>
						  </div>
						  <div class="form-group">
						    <label class="control-label col-sm-4" for="pwd">Ngày BĐ Tập thể lớp</label>
						    <div class="col-sm-6"> 
								<div class="input-group date form_date" data-date="" data-date-format="dd/mm/yyyy hh:ii:ss" data-link-field="dtp_input2" data-link-format="dd/mm/yyyy hh:ii:ss">
			            			<input class="form-control" type="datetime"  value="<%=DateUtils.formatDatetime(new Date()) %>" readonly>
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
									<input type="hidden" id="dtp_input2" name="ngaybdttl" value="<%=DateUtils.formatDatetime(new Date()) %>" />
			            		</div>						    
			            	</div>
						  </div>
						  <div class="form-group">
						    <label class="control-label col-sm-4" for="pwd">Ngày KT Tập thể lớp</label>
						    <div class="col-sm-6"> 
						      <div class="input-group date form_date" data-date="" data-date-format="dd/mm/yyyy hh:ii:ss" data-link-field="dtp_input2" data-link-format="dd/mm/yyyy hh:ii:ss">
			            			<input class="form-control" type="datetime"  value="<%=DateUtils.formatDatetime(new Date()) %>" readonly>
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
									<input type="hidden" id="dtp_input2" name="ngayktttl" value="<%=DateUtils.formatDatetime(new Date()) %>" />
			            		</div>
						    </div>
						  </div>
						  <div class="form-group">
						    <label class="control-label col-sm-4" for="pwd">Ngày BĐ GVCN</label>
						    <div class="col-sm-6"> 
						    <div class="input-group date form_date" data-date="" data-date-format="dd/mm/yyyy hh:ii:ss" data-link-field="dtp_input2" data-link-format="dd/mm/yyyy hh:ii:ss">
						     		 <input class="form-control" type="datetime"  value="<%=DateUtils.formatDatetime(new Date()) %>" readonly>
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
									<input type="hidden" id="dtp_input2" name="ngaybdgv" value="<%=DateUtils.formatDatetime(new Date()) %>" />
						    </div>
						  </div>
						  </div>
						  <div class="form-group">
						    <label class="control-label col-sm-4" for="pwd">Ngày KT GVCN</label>
						    <div class="col-sm-6">
						    <div class="input-group date form_date" data-date="" data-date-format="dd/mm/yyyy hh:ii:ss" data-link-field="dtp_input2" data-link-format="dd/mm/yyyy hh:ii:ss"> 
						      <input class="form-control" type="datetime"  value="<%=DateUtils.formatDatetime(new Date()) %>" readonly>
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
									<input type="hidden" id="dtp_input2" name="ngayktgv" value="<%=DateUtils.formatDatetime(new Date()) %>" />
						    </div>
						  </div>
						  </div>
						  <div class="form-group">
						    <label class="control-label col-sm-4" for="pwd">Ngày BĐ Trưởng Khoa</label>
						    <div class="col-sm-6"> 
						    <div class="input-group date form_date" data-date="" data-date-format="dd/mm/yyyy hh:ii:ss" data-link-field="dtp_input2" data-link-format="dd/mm/yyyy hh:ii:ss">
						      <input class="form-control" type="datetime"  value="<%=DateUtils.formatDatetime(new Date()) %>" readonly>
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
									<input type="hidden" id="dtp_input2" name="ngaybdtk" value="<%=DateUtils.formatDatetime(new Date()) %>" />
						    </div>
						  </div>
						  </div>
						  <div class="form-group">
						    <label class="control-label col-sm-4" for="pwd">Ngày KT Trưởng Khoa</label>
						    <div class="col-sm-6"> 
						    <div class="input-group date form_date" data-date="" data-date-format="dd/mm/yyyy hh:ii:ss" data-link-field="dtp_input2" data-link-format="dd/mm/yyyy hh:ii:ss">
						      <input class="form-control" type="datetime"  value="<%=DateUtils.formatDatetime(new Date()) %>" readonly>
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
									<input type="hidden" id="dtp_input2" name="ngaykttk" value="<%=DateUtils.formatDatetime(new Date()) %>" />
						    </div>
						  </div>	
						 </div>
						  <div class="form-group"> 
						    <div class="col-sm-offset-2 col-sm-10">
						      <button type="submit" class="btn btn-success" name="action" value="tao">Tạo</button>
						    </div>
						  </div>
					</form>
			</div>
		</div>
	</div>
	<%@include file="/templates/phongctsv/inc/footer.jsp"%>
</div>

</body>
<script src="<%=request.getContextPath()%>/Resources/bootstrap/jquery/jquery-1.11.3.min.js"></script>
	<script src="<%=request.getContextPath()%>/Resources/bootstrap/jquery/jquery.tablesorter.js"></script>
	<script src="<%=request.getContextPath()%>/Resources/bootstrap/jquery/jquery.tablesorter.widgets.js"></script>
	<script src="<%=request.getContextPath()%>/Resources/bootstrap/jquery/jquery.tablesorter.pager.js"></script>

    <script src="<%=request.getContextPath()%>/Resources/bootstrap/jquery/table.js"></script>
    
   <script type="text/javascript" src="<%=request.getContextPath() %>/Resources/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js" ></script>
	
		<script type="text/javascript" src="<%=request.getContextPath() %>/Resources/bootstrap-datetimepicker-master/js/locales/bootstrap-datetimepicker.vi.js" ></script>
		
		<script type="text/javascript">
			$('.form_date').datetimepicker({
				defaultDate: new Date(),
				format: 'dd/mm/yyyy hh:ii'
		    });
		</script>
</html>