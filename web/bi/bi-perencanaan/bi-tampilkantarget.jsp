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
	Tampilkan Target 
    </li>
</ol>


<div class="row">
    <div class="col-md-12">
	<div class="box bix-primary">
	    <div class="box-header">
		<div class="box-title">Perencanaan Tahun</div>
		<div class="col-md-2" style="margin-top:12px;">
		    <%= ControlCombo.drawBootsratap(FrmDataTarget.fieldNames[FrmDataTarget.FRM_FIELD_TAHUN_TARGET]+"_TAMPIL", null, ""+thisYear, tahun, tahun, "", "form-control") %> 
		</div>
		
	    </div>
	</div>
	
    </div>
    <div class="col-md-6">
	<div class="box box-primary">
	    <div class="box-header">
		<h3 class="box-title">Target Pajak</h3>
	    </div>
	    <div class="box-body chart-responsive">
		<div id="targetpajak" style="height: 300px;"></div>
	    </div>
	</div>
    </div>
    <div class="col-md-6">
	<div class="box box-primary">
	    <div class="box-header">
		<h3 class="box-title">Target Pajak Non PHR</h3>
	    </div>
	    <div class="box-body chart-responsive">
		<div id="targetpajaknonphr" style="height: 300px;overflow-x: auto"></div>
	    </div>
	</div>
    </div>
    <div class="col-md-12">
	<div class="box box-primary">
	    <div class="box-header">
		<h3 class="box-title">Target Pajak</h3>
	    </div>
	    <div class="box-body chart-responsive">
		<div id="persentasetargetpajak" style="height: 300px;overflow-x: auto"></div>
	    </div>
	</div>
    </div>
    <div class="col-md-12">
	<div class="box box-primary">
	    <div class="box-header">
		<h3 class="box-title">Table Target Pajak</h3>
	    </div>
	    <div class="box-body chart-responsive">
		<div id="tabeltargetpajak" style="overflow-x: auto"></div>
	    </div>
	</div>
    </div>
    <div class="col-md-6">
	<div class="box box-primary">
	    <div class="box-header">
		<h3 class="box-title">Target PHR <span class="targetYear"></span></h3>
	    </div>
	    <div class="box-body chart-responsive">
		<div id="targetpajakphrhotel" style="height: 300px;overflow-x: auto"></div>
	    </div>
	</div>
    </div>
    <div class="col-md-6">
	<div class="box box-primary">
	    <div class="box-header">
		<h3 class="box-title">Target PHR Non Hotel <span class="targetYear"></span></h3>
	    </div>
	    <div class="box-body chart-responsive">
		<div id="targetpajakphrnonhotel" style="height: 300px;overflow-x: auto"></div>
	    </div>
	</div>
    </div>
</div>
<!-- /.row -->
