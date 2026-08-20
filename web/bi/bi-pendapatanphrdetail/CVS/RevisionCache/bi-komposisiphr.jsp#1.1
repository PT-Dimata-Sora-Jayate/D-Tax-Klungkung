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
    for(int i = thisYear-3; i <= thisYear+3; i++){
	tahun.add(""+i);
    }
    
    Vector listPajakType = PstPajakType.listAll();
    String pajakTypes = "";
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
	Komposisi PHR
    </li>
</ol>


<div class="row">
    <div class="col-md-12">
	<div class="box bix-primary">
	    <div class="box-header">
		<div class="box-title">Komposisi PHR Pendapatan Pada Tahun</div>
		<div class="col-md-2" style="margin-top:12px;">
		    <%= ControlCombo.drawBootsratap(FrmDataPajak.fieldNames[FrmDataPajak.FRM_FIELD_BULAN]+"_KOMPOSISI_PHR", null, ""+thisYear, tahun, tahun, "", "form-control") %> 
		</div>
		
	    </div>
	</div>
	
    </div>
    <div class="col-md-6">
	<div class="box box-primary">
	    <div class="box-header">
		<h3 class="box-title">Pencapaian PHR vs Target s/d Hari Ini</h3>
	    </div>
	    <div class="box-body chart-responsive">
		<div id="komposisiphr" style="height: 300px;"></div>
	    </div>
	</div>
    </div>
    <div class="col-md-6">
	<div class="box box-primary">
	    <div class="box-header">
		<h3 class="box-title">Persentase Pencapaian PHR vs Target s/d Hari Ini</h3>
	    </div>
	    <div class="box-body chart-responsive">
		<div id="persenkomposisiphr" style="height: 300px;overflow-x: auto"></div>
	    </div>
	</div>
    </div>
    <div class="col-md-12">
	<div class="box box-primary">
	    <div class="box-header">
		<h3 class="box-title">Tabel Pendapatan PHR</h3>
	    </div>
	    <div class="box-body chart-responsive">
		<div id="tabelkomposisiphr" style="height: 300px;overflow-x: auto"></div>
	    </div>
	</div>
    </div>
    <div class="col-md-6">
	<div class="box box-primary">
	    <div class="box-header">
		<h3 class="box-title">PHR Pencapaian <span class="forYear">2015</span></h3>
	    </div>
	    <div class="box-body chart-responsive">
		<div id="pencapaianphrline" style="height: 300px;overflow-x: auto"></div>
	    </div>
	</div>
    </div>
    <div class="col-md-6">
	<div class="box box-primary">
	    <div class="box-header">
		<h3 class="box-title">PHR Pencapaian <span class="forYear">2015</span></h3>
	    </div>
	    <div class="box-body chart-responsive">
		<div id="pencapaianphrstackline" style="height: 300px;overflow-x: auto"></div>
	    </div>
	</div>
    </div>
    <div class="col-md-12">
	<div class="box box-primary">
	    <div class="box-header">
		<h3 class="box-title">Tabel PHR Pencapaian <span class="forYear">2015</span> Per Bulan</h3>
	    </div>
	    <div class="box-body chart-responsive">
		<div id="tabelpencapaianphrperbulan" style="height: 300px;overflow-x: auto"></div>
	    </div>
	</div>
    </div>
</div>
<!-- /.row -->
