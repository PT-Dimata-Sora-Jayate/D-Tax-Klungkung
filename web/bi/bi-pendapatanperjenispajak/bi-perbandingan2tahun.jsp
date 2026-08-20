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
	Perbandingan 2 Tahun
    </li>
</ol>


<div class="row">
    <div class="col-md-12">
	<div class="box bix-primary">
	    <div class="box-header">
		<div class="box-title">Pendapatan Pajak tahun</div>
		<div class="col-md-2" style="margin-top:12px;">
		    <%= ControlCombo.drawBootsratap(FrmDataPajak.fieldNames[FrmDataPajak.FRM_FIELD_TAHUN], null, ""+lastYear, tahun, tahun, "", "form-control "+FrmDataPajak.fieldNames[FrmDataPajak.FRM_FIELD_TAHUN]) %> 
		</div>
		<div class="box-title">
		    -
		</div>
		<div class="col-md-2" style="margin-top:12px;">
		    <%= ControlCombo.drawBootsratap(FrmDataPajak.fieldNames[FrmDataPajak.FRM_FIELD_TAHUN], null, Formater.formatDate(currentDate,"yyyy"), tahun, tahun, "", "form-control "+FrmDataPajak.fieldNames[FrmDataPajak.FRM_FIELD_TAHUN]) %> 
		</div>
	    </div>
	</div>
	
    </div>
    <div class="col-md-6">
	<div class="box box-primary">
	    <div class="box-header">
		<h3 class="box-title">Perbandingan <span class="startYear"><%= ""+lastYear %></span> vs <span class="endYear"><%= ""+thisYear %></span></h3>
	    </div>
	    <div class="box-body chart-responsive">
		<div id="perbandingan2tahun" style="height: 300px;"></div>
	    </div>
	</div>
	<div class="box box-primary">
	    <div class="box-header">
		<h3 class="box-title">Perbandingan <span class="startYear"><%= ""+lastYear %></span> vs <span class="endYear"><%= ""+thisYear %></span></h3>
	    </div>
	    <div class="box-body chart-responsive">
		<div id="perbandinganperjenispajak" style="height: 300px;"></div>
	    </div>
	</div>
	<div class="box box-primary">
	    <div class="box-header">
		<h3 class="box-title">Perbandingan <span class="startYear"><%= ""+lastYear %></span> vs <span class="endYear"><%= ""+thisYear %></span> vs Target</h3>
	    </div>
	    <div class="box-body chart-responsive">
		<div id="perbandinganperbulan" style="height: 300px;"></div>
	    </div>
	</div>
        <!-- /.box -->
    </div>
    <div class="col-md-6">
	<div class="box box-primary">
	    <div class="box-header">
		<h3 class="box-title">Tabel Perbandingan <span class="startYear"><%= ""+lastYear %></span> vs <span class="endYear"><%= ""+thisYear %></span></h3>
	    </div>
	    <div class="box-body chart-responsive">
		<div id="tabelperbandingan2tahun" style="height: 300px;overflow-x: auto"></div>
	    </div>
	</div>
	<div class="box box-primary">
	    <div class="box-header">
		<h3 class="box-title">Perbandingan <span class="startYear"><%= ""+lastYear %></span> vs <span class="endYear"><%= ""+thisYear %></span></h3>
	    </div>
	    <div class="box-body chart-responsive">
		<div id="perbandinganperjenispajaknonphr" style="height: 300px;"></div>
	    </div>
	</div>
	<div class="box box-primary">
	    <div class="box-header">
		<h3 class="box-title">Tabel Perbandingan <span class="startYear"><%= ""+lastYear %></span> vs <span class="endYear"><%= ""+thisYear %></span> vs Target </h3>
	    </div>
	    <div class="box-body chart-responsive">
		<div id="tabelperbandinganperbulan" style="height: 300px;overflow: auto;"></div>
	    </div>
	</div>
    </div>
</div>
<!-- /.row -->
