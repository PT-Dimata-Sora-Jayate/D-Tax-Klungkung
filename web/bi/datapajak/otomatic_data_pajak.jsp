<%-- 
    Document   : otomatic_data_pajak
    Created on : Nov 6, 2015, 9:40:09 AM
    Author     : dimata005
--%>
<%@page import="com.dimata.util.Formater"%>
<%@page import="java.util.Date"%>
<%@page import="com.dimata.dtaxintegration.entity.biadmin.AppObjInfo"%>
<%@page import="com.dimata.dtaxintegration.entity.biadmin.AppPrivilegeObj"%>
<%@page import="com.dimata.dtaxintegration.form.bi.FrmSearchDataPajak"%>
<%@page import="com.dimata.dtaxintegration.form.bi.FrmDataPajak"%>
<%@page import="com.dimata.util.Command"%>
<%@include file="../../main/javainit_bi.jsp" %>
<%@include file="../../main/checkuser_bi.jsp" %>

<%
    Date currentDate = new Date();
%>
<input type="hidden" name="approot" id="approot" value="<%= approot %>">
<input type='hidden' name='command' id='command' value='<%= Command.NONE %>'>
<input type="hidden" name="FRM_FIELD_TANGGALBAYAR_DASHBOARD" id="FRM_FIELD_TANGGALBAYAR_DASHBOARD" value="01 January 1000">
<input type="hidden" name="FRM_FIELD_CURRENT_DATE" id="FRM_FIELD_CURRENT_DATE" value="<%= Formater.formatDate(currentDate, "dd MMMM yyyy") %>">
<ol class="breadcrumb newcrumb">
    <li>
        <a href="#!/dashboard">
            <span>
		<i class="fa fontello-home-outline"></i>
            </span>Dashboard
	</a>
    </li>
    <li class="active">Otomatis Proses</li>
</ol>

<form id="<%=FrmSearchDataPajak.FRM_NAME_SEARCHDATAPAJAK %>" name="<%=FrmSearchDataPajak.FRM_NAME_SEARCHDATAPAJAK%>">
<div class="row" >
    <div class="col-md-12">
	<input type="hidden" name="command" value="<%=Command.NONE%>">
	<input type="hidden" name="FRM_FIELD_VIEW_TYPE" value="savetarget">
        <input type="hidden" name="<%=FrmSearchDataPajak.fieldNames[FrmSearchDataPajak.FRM_FIELD_TYPE_INPUT_DATA]%>" value="1">
	<div class="box box-primary">
	    
	<!-- HEADER CONTENT -->
            <div class="box-header" style="border-bottom:1px solid #f1f1f1;">
		<div class="col-md-12">
		    <h2 class="box-title">Otomatis Proses Data Pajak</h2>
		</div>
            </div>
	<!--/ HEADER CONTENT -->
	<!-- BODY CONTENT -->
	    <div class="box-body">
                <!-- DATA DISPLAY SETTING CONTENT -->
		<div class="row">
		    <div class="col-md-12">
		    </div>
		</div>
		<div class="row">
		    <div class="col-md-12" id="VIEW_CONTENT_AUTOMATIC">
		    </div>
		</div>
		<div class="row">
		    <div class="col-md-12" id="RESULT_CONTENT">
		    </div>
		</div>
                <!--/ DATA DISPLAY -->
	    </div>
	    <div class="box-footer">
		<%
		    if(userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_DATA_PAJAK, AppObjInfo.G2_OTOMATIS_DATA_PAJAK, AppObjInfo.OBJ_OTOMATIS_DATA_PAJAK, AppObjInfo.COMMAND_START))
		    {
		    %>
			<button class="btn btn-primary" type="button" id="btnStart">
			    <i class="fa fa-check"></i> Start
			</button>
		    <%
		    }
		%>
		
		<%
		    if(userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_DATA_PAJAK, AppObjInfo.G2_OTOMATIS_DATA_PAJAK, AppObjInfo.OBJ_OTOMATIS_DATA_PAJAK, AppObjInfo.COMMAND_STOP))
		    {
		    %>
			<button class="btn btn-danger" type="button" id="btnStop">
			    <i class="fa fa-refresh"></i> Stop
			</button>
		    <%
		    }
		%>
		
	    </div>
	</div>
    </div>
</div>
</form>