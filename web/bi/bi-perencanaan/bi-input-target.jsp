<%@page import="com.dimata.dtaxintegration.entity.bi.PajakTypeDetail"%>
<%@page import="com.dimata.dtaxintegration.entity.bi.PstPajakTypeDetail"%>
<%@page import="com.dimata.gui.jsp.ControlCombo"%>
<%@page import="com.dimata.util.Formater"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Vector"%>
<%@page import="com.dimata.dtaxintegration.entity.biadmin.AppObjInfo"%>
<%@page import="com.dimata.util.Command"%>
<%@page import="com.dimata.dtaxintegration.form.bi.FrmDataTarget"%>
<%@include file="../../main/javainit_bi.jsp" %>
<%@include file="../../main/checkuser_bi.jsp" %>

<%
    //PIVILEGE
    Date currentDate = new Date();
    boolean privAdd = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_PERENCANAAN, AppObjInfo.G2_INPUT_TARGET, AppObjInfo.OBJ_INPUT_TARGET, AppObjInfo.COMMAND_ADD);
    boolean privUpdate = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_PERENCANAAN, AppObjInfo.G2_INPUT_TARGET, AppObjInfo.OBJ_INPUT_TARGET, AppObjInfo.COMMAND_UPDATE);
%>

<input type="hidden" name="approot" id="approot" value="<%= approot %>">
<input type="hidden" name="command" id="command" value="<%= Command.NONE %>"
<input type="hidden" name="privadd" id="privadd" value="<%= privAdd %>">
<input type="hidden" name="privupdate" id="privupdate" value="<%= privUpdate %>">
<input type="hidden" name="FRM_FIELD_TANGGALBAYAR_DASHBOARD" id="FRM_FIELD_TANGGALBAYAR_DASHBOARD" value="01 January 1000">
<input type="hidden" name="FRM_FIELD_CURRENT_DATE" id="FRM_FIELD_CURRENT_DATE" value="<%= Formater.formatDate(currentDate, "dd MMMM yyyy") %>">
<ol class="breadcrumb newcrumb">
    <li>
        <a href="#">
            <span>
		<i class="fa fontello-home-outline"></i>
            </span> Perencanaan
	</a>
    </li>
    <li class="active">Input Target</li>
</ol>

<form id="<%= FrmDataTarget.FRM_NAME_DATATARGET %>" name="<%= FrmDataTarget.FRM_NAME_DATATARGET %>">
<div class="row" >
    <div class="col-md-12">
	
	<input type="hidden" name="command" value="<%= Command.SAVE %>">
	<input type="hidden" name="FRM_FIELD_VIEW_TYPE" value="savetarget">
	<div class="box box-primary">
	    
	<!-- HEADER CONTENT -->
            <div class="box-header" style="border-bottom:1px solid #f1f1f1;">
		
	    <!-- HEADER TITLE CONTENT -->
		<div class="col-md-2">
		    <h2 class="box-title">INPUT TARGET</h2>
		</div>
	    <!--/ HEADER TITLE CONTENT -->
	    
	    <!-- DATA JENIS PAJAK DISPLAY SETTING -->
                <div class="col-md-6">
		    
		    <div class="box-title">
			Tahun 
		    </div>
		    <div class="col-md-3"  style="padding-top:12px;">
			<%
			    Vector listYear = new Vector(1,1);
			    int currenYear = Integer.parseInt(Formater.formatDate(new Date(),"yyyy"));
			    
			    for(int i = currenYear-3; i <= currenYear+3; i++){
				listYear.add(""+i);
			    }
			%>
			<%= ControlCombo.drawBootsratap(FrmDataTarget.fieldNames[FrmDataTarget.FRM_FIELD_TAHUN_TARGET], null, ""+currenYear, listYear, listYear, "required='required'", "form-control") %>
		    </div>
		    <div class="box-title">
			Jenis Pajak 
		    </div>
		    <div class="col-md-4" style="padding-top:12px;">
			<%
			    Vector jenisPajak_key = new Vector(1,1);
			    Vector jenisPajak_value = new Vector(1,1);
			    
			    //Vector listJenisPajak = PstPajakTypeDetail.listAll();
			    Vector listJenisPajak = PstPajakTypeDetail.list(0,0,"","");
			    if(listJenisPajak.size() != 0){
				
				for(int i = 0; i<listJenisPajak.size(); i++){
				    PajakTypeDetail pajakTypeDetail = (PajakTypeDetail) listJenisPajak.get(i);

				    jenisPajak_key.add(pajakTypeDetail.getOID()+"");
				    jenisPajak_value.add(""+pajakTypeDetail.getPajakDetailName());
				}
			    }
			%>
			<%= ControlCombo.drawBootsratap(FrmDataTarget.fieldNames[FrmDataTarget.FRM_FIELD_PAJAK_DETAIL_ID], null, "0", jenisPajak_key, jenisPajak_value, "required='required'", "form-control showDataTarget") %>
		    </div>
		</div>
	    <!--/ DATA JENIS PAJAK DISPLAY SETTING -->
            </div>
	<!--/ HEADER CONTENT -->
	
	<!-- BODY CONTENT -->
	    <div class="box-body">
		
	    <!-- DATA DISPLAY SETTING CONTENT -->
		<div class="row">
		    <div class="col-md-6">
			
		    <!-- SUMBER FILTER -->
			<div class="col-md-6">
			    <div class="form-group">
				<label for="targetSumber">Sumber</label>
				<%
				    Vector sumber_key = new Vector(1,1);
				    Vector sumber_value = new Vector(1,1);
				    
				    String[] sumbers = {
					"Target",
					"Realisasi"
				    };
				    
				    for(int i = 0; i < sumbers.length; i++){
					sumber_key.add(""+i);
					sumber_value.add(""+sumbers[i]);
				    }
				%>
				<%= ControlCombo.drawBootsratap("FRM_FIELD_SUMBER_TARGET", null, "", sumber_key, sumber_value, "required='required'", "form-control showDataTarget") %>
			    </div>
			</div>
		    <!--/ SUMBER FILTER -->
		    
		    <!-- TAHUN FILTER -->
			<div class="col-md-6">
			    <div class="form-group">
				<label for="targetTahun">Tahun</label>
				<%
				    Vector tahun_key = new Vector(1,1);
				    
				    int currentYear = Integer.parseInt(Formater.formatDate(new Date(),"yyyy"))-1;
				    
				    
				    for(int i = currentYear-3; i <= currentYear; i++){
					tahun_key.add(""+i);
				    }
				%>
				<%= ControlCombo.drawBootsratap(FrmDataTarget.fieldNames[FrmDataTarget.FRM_FIELD_TAHUN_SUMBER], null, ""+currentYear, tahun_key, tahun_key, "required='required'", "form-control showDataTarget") %>
			    </div>
			</div>
		    <!--/ TAHUN FILTER -->
		    </div>
		</div>
	    <!--/ DATA DISPLAY SETTING CONTENT -->
	    
	    <!-- DATA DISPLAY -->
		<div class="row">
		    <div class="col-md-12" id="VIEW_CONTENT">
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
		    if(privAdd && privUpdate){
		    %>
			<button class="btn btn-primary" type="submit" id="btnSave">
			    <i class="fa fa-check"></i> Simpan
			</button>
			<button class="btn btn-danger" type="reset">
			    <i class="fa fa-refresh"></i> Reset
			</button>
		    <%
		    }
		%>
		
		
	    </div>
	</div>
    </div>
</div>
</form>