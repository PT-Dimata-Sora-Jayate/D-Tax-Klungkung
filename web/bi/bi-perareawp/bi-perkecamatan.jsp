<%@page import="com.dimata.dtaxintegration.form.bi.FrmPajakTypeDetail"%>
<%@page import="com.dimata.dtaxintegration.entity.bi.PajakTypeDetail"%>
<%@page import="com.dimata.dtaxintegration.form.bi.FrmDataTarget"%>
<%@page import="com.dimata.gui.jsp.ControlLine"%>
<%@page import="com.dimata.gui.jsp.ControlCombo"%>
<%@page import="com.dimata.dtaxintegration.form.bi.FrmDataPajak"%>
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
    
    Vector tahun = new Vector(1,1);
    int thisYear = Integer.parseInt(Formater.formatDate(currentDate,"yyyy"));
    int lastYear = thisYear - 1;
    int nextYear = thisYear + 1;
    for(int i = thisYear-3; i <= thisYear+3; i++){
	tahun.add(""+i);
    }
    
    Vector pajakType_key = new Vector(1,1);
    Vector pajakType_val = new Vector(1,1);
    
    Vector listPajakDetail = PstPajakTypeDetail.list(0,0,"","");
    
    if(listPajakDetail.size() != 0){
	for(int i = 0; i < listPajakDetail.size(); i++){
	    PajakTypeDetail pajakTypeDetail = (PajakTypeDetail) listPajakDetail.get(i);
	    pajakType_key.add(""+pajakTypeDetail.getOID());
	    pajakType_val.add(""+pajakTypeDetail.getPajakDetailName());
	}
    }
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
	Per Kecamatan
    </li>
</ol>


<div class="row">
    <div class="col-md-12">
	<div class="box bix-primary">
	    <div class="box-header">
		<div class="box-title">Pendapatan Pajak Tahun</div>
		<div class="col-md-2" style="margin-top:12px;">
		    <%= ControlCombo.drawBootsratap(FrmDataPajak.fieldNames[FrmDataPajak.FRM_FIELD_TAHUN]+"_KECAMATAN", null, ""+thisYear, tahun, tahun, "", "form-control WP_FILTER") %> 
		</div>
	    </div>
	</div>
    </div>
    <div class='col-md-12'>
	<div class="box bix-primary">
	    <div class="box-header">
		<div class="box-title">
		    Total Pajak Per Kecamatan Pada <span class="kecamatan"></span>
		</div>
	    </div>
	    <div class="box-body">
		<div id="persenperkecamatan" style="height: 300px;"></div>
	    </div>
	</div>
    </div>
    <div class='col-md-12'>
	<div class="box bix-primary">
	    <div class="box-header">
		<div class="box-title">
		    Tabel Total Pajak Per Kecamatan Pada <span class="kecamatan"></span>
		</div>
	    </div>
	    <div class="box-body">
		<div id="tabelperkecamatan"></div>
	    </div>
	</div>
    </div>
    <div class='col-md-12'>
	<div class="box bix-primary">
	    <div class="box-header">
		<div class="box-title">
		    Perbandingan Pajak
		</div>
	    </div>
	    <div class="box-body">
		<div id="perbandingankecamatan"></div>
	    </div>
	</div>
    </div>
</div>
<!-- /.row -->
