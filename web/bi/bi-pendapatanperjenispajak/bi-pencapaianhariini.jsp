<%@page import="com.dimata.dtaxintegration.form.bi.FrmDataPajak"%>
<%@page import="com.dimata.gui.jsp.ControlCombo"%>
<%@page import="java.util.TimeZone"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.DateFormatSymbols"%>
<%@page import="com.dimata.dtaxintegration.entity.bi.DataPajak"%>
<%@page import="com.dimata.dtaxintegration.entity.bi.PstDataPajak"%>
<%@page import="com.dimata.dtaxintegration.entity.bi.PstPajakTypeDetail"%>
<%@page import="com.dimata.dtaxintegration.entity.bi.PstDataTarget"%>
<%@page import="com.dimata.dtaxintegration.entity.bi.PajakType"%>
<%@page import="com.dimata.dtaxintegration.entity.bi.PstPajakType"%>
<%@page import="com.dimata.gui.jsp.ControlList"%>
<%@page import="java.util.Vector"%>
<%@page import="com.dimata.util.Command"%>
<%@page import="java.util.Date"%>
<%@page import="com.dimata.util.Formater"%>
<%@include file="../../main/javainit_bi.jsp" %>
<%@include file="../../main/checkuser_bi.jsp" %>
<%
    Date currentDate = new Date();
    double pembagi = 1000000000;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    df.setTimeZone(TimeZone.getTimeZone("UTC"));
   
    
    
%>

<input type="hidden" name="approot" id="approot" value="<%= approot %>">
<input type='hidden' name='command' id='command' value='<%= Command.NONE %>'>
<input type="hidden" name="FRM_FIELD_TANGGALBAYAR_DASHBOARD" id="FRM_FIELD_TANGGALBAYAR_DASHBOARD" value="01 January 1000">
<input type="hidden" name="FRM_FIELD_CURRENT_DATE" id="FRM_FIELD_CURRENT_DATE" value="<%= Formater.formatDate(currentDate, "dd MMMM yyyy") %>">
<ol class="breadcrumb newcrumb">
    <li>    
            <a href="#!/dashboard"><span><i class="fa fontello-home-outline"></i>
            </span>Dashboard</a>
    </li>
    <li class="active">
	Pencapaian s/d Hari Ini
    </li>
</ol>


<div class="row">
    <div class="col-md-12">
	<div class="box box-primary">
	    <div class="box-header">
		<div class="box-title">
		    Pendapatan setahun sampai tanggal
		</div>
		<div class="col-md-2" style="margin-top:12px;">
		    <div class="input-group">
			<input type="text" class="form-control datePicker" name="<%= FrmDataPajak.fieldNames[FrmDataPajak.FRM_FIELD_TANGGALBAYAR] %>" id="<%= FrmDataPajak.fieldNames[FrmDataPajak.FRM_FIELD_TANGGALBAYAR] %>" readonly="readonly" value="<%= Formater.formatDate(currentDate, "yyyy-MM-dd") %>">
			<div class="input-group-addon">
			    <i class="fa fa-calendar"></i>
			</div>
		    </div>
		    
		</div>
	    </div>
	</div>
    </div>
    <div class="col-md-6">
	<div class="box box-primary">
	    <div class="box-header">
		<h3 class="box-title">Pendapatan per Jenis Pajak</h3>
	    </div>
	    <div class="box-body chart-responsive">
		<div id="pendapatanperjenispajakhariini" style="height: 300px;"></div>
	    </div>
	</div>
        <!-- /.box -->
    </div>
    <div class="col-md-6">
	<div class="box box-primary">
	    <div class="box-header">
		<h3 class="box-title">Persentase Pendapatan per Jenis Pajak</h3>
	    </div>
	    <div class="box-body chart-responsive">
		<div id="persentasependapatanperjenispajakhariini" style="height: 300px;"></div>
	    </div>
	</div>

    </div>
    <div id="CONTENT">
    <%
	String pajakTypes = "";
	Vector listPajakType = PstPajakType.listAll();
	if(listPajakType.size() != 0){
	    for(int i = 0; i < listPajakType.size(); i++){
		
		PajakType pajakType = (PajakType) listPajakType.get(i);
		
		if(pajakTypes.length() == 0){
		    pajakTypes += ""+pajakType.getOID();
		}else{
		    pajakTypes += ","+pajakType.getOID();
		}
		%>
		<div class="col-md-12">
		    <div class="box box-primary">
			<div class="box-header">
			    <h3 class="box-title">Pencapaian  <%= pajakType.getPajakTypeName()+" "+Formater.formatDate(currentDate, "yyyy") %></h3>
			</div>
			<div class="box-body">
			    <div style="overflow: auto" id="pencapaiantable<%= pajakType.getOID() %>"></div>
			    <div id="pencapaian<%= pajakType.getOID() %>" style="height: 300px;"></div>
			</div>
		    </div>
		</div>
		<%
	    }
	}
    %>
    </div>
    <input type="hidden" name="pajakType" id="pajakType" value="<%= pajakTypes %>">
</div>
<!-- /.row -->
