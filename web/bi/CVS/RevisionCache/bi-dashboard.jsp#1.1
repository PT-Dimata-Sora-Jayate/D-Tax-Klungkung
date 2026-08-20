
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
<%@include file="../main/javainit_bi.jsp" %>
<%@include file="../main/checkuser_bi.jsp" %>
<%
    Date currentDate = new Date();
    double pembagi = 1000000000;
%>
<%!
    public String drawTabelPendapatanPajak(Date currenDate, double pembagi){
	
	Vector resultHeader = PstPajakType.listAll();
	

	ControlList ctrlist = new ControlList();

	ctrlist.setAreaWidth("100%");

	ctrlist.setListStyle("listarea");

	ctrlist.setTitleStyle("listheader");

	ctrlist.setCellStyle("table_cell");

	ctrlist.setHeaderStyle("listheader");

	ctrlist.setBorder(1);
	
	ctrlist.addHeader("","10%");
	
	if((resultHeader!=null)&&(resultHeader.size()!=0)){
	    for (int i = 0; i < resultHeader.size(); i++) {
		PajakType pajakType = (PajakType) resultHeader.get(i);
		ctrlist.addHeader(""+pajakType.getPajakTypeName(),"10%");
	    }
	}
	ctrlist.addHeader("Total","10%");


	ctrlist.setLinkRow(1);

	ctrlist.setLinkSufix("");

	Vector lstData = ctrlist.getData();

	Vector lstLinkData = ctrlist.getLinkData();

	ctrlist.setLinkPrefix("javascript:cmdEdit('");

	ctrlist.setLinkSufix("')");

	ctrlist.reset();

	Vector rowx = new Vector();
	rowx.add("Realisasi YTD");
	double realisasiTotal = 0;
	if(resultHeader.size() != 0){
	    for(int i = 0; i < resultHeader.size(); i++){
		PajakType pajakType = (PajakType) resultHeader.get(i);
		Vector listRealisasiPerJenisPajak = PstDataPajak.listPendapatanPerJenisPajak(0, 0,
			"EXTRACT(YEAR FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+Formater.formatDate(currenDate, "yyyy")+"' "
			+ "AND pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_TYPE_ID]+"='"+pajakType.getOID()+"'", 
			"", "pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_NAME]);
		DataPajak dataPajak;
		if(listRealisasiPerJenisPajak.size() != 0){
		    dataPajak = (DataPajak) listRealisasiPerJenisPajak.get(0);
		}else{
		    dataPajak = new DataPajak();
		}
		
		double realisasi = dataPajak.getJumlahPajak()/pembagi;
		rowx.add(""+Formater.formatNumber(realisasi,"#.##"));
		realisasiTotal+=realisasi;
	    }
	}
	rowx.add(Formater.formatNumber(realisasiTotal,"#.##"));
	lstData.add(rowx);
	
	rowx = new Vector();
	rowx.add("Target "+Formater.formatDate(currenDate, "yyyy"));
	double targetTotal = 0;
	if(resultHeader.size() != 0){
	    
	    for(int i = 0; i < resultHeader.size(); i++){
		PajakType pajakType = (PajakType) resultHeader.get(i);
		double target = PstDataTarget.getTotalTargetPerPajakType(0, 0, 
			"pajakDetail."+PstPajakTypeDetail.fieldNames[PstPajakTypeDetail.FLD_PAJAK_TYPE_ID]+"='"+pajakType.getOID()+"' "
			+ "AND target."+PstDataTarget.fieldNames[PstDataTarget.FLD_TAHUN_TARGET]+"='"+Formater.formatDate(currenDate,"yyyy")+"'", "");
		target = target/pembagi;
		rowx.add(""+Formater.formatNumber(target, "#.##"));
		targetTotal +=  target;
	    }
	    
	    
	}
	rowx.add(Formater.formatNumber(targetTotal, "#.##"));
	lstData.add(rowx);
	
	rowx = new Vector();
	rowx.add("Diviasi");
	
	if(resultHeader.size() != 0){
	    
	    for(int i = 0; i < resultHeader.size(); i++){
		PajakType pajakType = (PajakType) resultHeader.get(i);
		double target = PstDataTarget.getTotalTargetPerPajakType(0, 0, 
			"pajakDetail."+PstPajakTypeDetail.fieldNames[PstPajakTypeDetail.FLD_PAJAK_TYPE_ID]+"='"+pajakType.getOID()+"' "
			+ "AND target."+PstDataTarget.fieldNames[PstDataTarget.FLD_TAHUN_TARGET]+"='"+Formater.formatDate(currenDate,"yyyy")+"'", "");
		target = target/pembagi;
		
		Vector listRealisasiPerJenisPajak = PstDataPajak.listPendapatanPerJenisPajak(0, 0,
			"EXTRACT(YEAR FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+Formater.formatDate(currenDate, "yyyy")+"' "
			+ "AND pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_TYPE_ID]+"='"+pajakType.getOID()+"'", 
			"", "pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_NAME]);
		DataPajak dataPajak;
		if(listRealisasiPerJenisPajak.size() != 0){
		    dataPajak = (DataPajak) listRealisasiPerJenisPajak.get(0);
		}else{
		    dataPajak = new DataPajak();
		}
		double realisasi = dataPajak.getJumlahPajak()/pembagi;
		rowx.add(""+Formater.formatNumber(realisasi-target, "#.##"));
	    }
	    
	    
	}
	rowx.add(Formater.formatNumber(realisasiTotal-targetTotal, "#.##"));
	lstData.add(rowx);
	
	rowx = new Vector();
	rowx.add("% Realisasi");
	
	if(resultHeader.size() != 0){
	    
	    for(int i = 0; i < resultHeader.size(); i++){
		PajakType pajakType = (PajakType) resultHeader.get(i);
		double target = PstDataTarget.getTotalTargetPerPajakType(0, 0, 
			"pajakDetail."+PstPajakTypeDetail.fieldNames[PstPajakTypeDetail.FLD_PAJAK_TYPE_ID]+"='"+pajakType.getOID()+"' "
			+ "AND target."+PstDataTarget.fieldNames[PstDataTarget.FLD_TAHUN_TARGET]+"='"+Formater.formatDate(currenDate,"yyyy")+"'", "");
		target = target/pembagi;
		
		Vector listRealisasiPerJenisPajak = PstDataPajak.listPendapatanPerJenisPajak(0, 0,
			"EXTRACT(YEAR FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+Formater.formatDate(currenDate, "yyyy")+"' "
			+ "AND pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_TYPE_ID]+"='"+pajakType.getOID()+"'", 
			"", "pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_NAME]);
		DataPajak dataPajak;
		if(listRealisasiPerJenisPajak.size() != 0){
		    dataPajak = (DataPajak) listRealisasiPerJenisPajak.get(0);
		}else{
		    dataPajak = new DataPajak();
		}
		double realisasi = dataPajak.getJumlahPajak()/pembagi;
		rowx.add(""+Formater.formatNumber((realisasi/target)*100, "#.##")+"%");
	    }
	    
	    
	}
	rowx.add(Formater.formatNumber((realisasiTotal/targetTotal)*100,"#.##")+"%");
	lstData.add(rowx);
	
	rowx = new Vector();
	rowx.add("% Sisa");
	if(resultHeader.size() != 0){
	    
	    for(int i = 0; i < resultHeader.size(); i++){
		PajakType pajakType = (PajakType) resultHeader.get(i);
		double target = PstDataTarget.getTotalTargetPerPajakType(0, 0, 
			"pajakDetail."+PstPajakTypeDetail.fieldNames[PstPajakTypeDetail.FLD_PAJAK_TYPE_ID]+"='"+pajakType.getOID()+"' "
			+ "AND target."+PstDataTarget.fieldNames[PstDataTarget.FLD_TAHUN_TARGET]+"='"+Formater.formatDate(currenDate,"yyyy")+"'", "");
		target = target/pembagi;
		
		Vector listRealisasiPerJenisPajak = PstDataPajak.listPendapatanPerJenisPajak(0, 0,
			"EXTRACT(YEAR FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+Formater.formatDate(currenDate, "yyyy")+"' "
			+ "AND pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_TYPE_ID]+"='"+pajakType.getOID()+"'", 
			"", "pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_NAME]);
		DataPajak dataPajak;
		if(listRealisasiPerJenisPajak.size() != 0){
		    dataPajak = (DataPajak) listRealisasiPerJenisPajak.get(0);
		}else{
		    dataPajak = new DataPajak();
		}
		double realisasi = dataPajak.getJumlahPajak()/pembagi;
		rowx.add(""+Formater.formatNumber(100-((realisasi/target)*100), "#.##")+"%");
	    }
	    
	    
	}
	rowx.add(Formater.formatNumber(100-((realisasiTotal/targetTotal)*100),"#.##")+"%");
	lstData.add(rowx);
	
	rowx = new Vector();
	rowx.add("% Bobot");
	if(resultHeader.size() != 0){
	    for(int i = 0; i < resultHeader.size(); i++){
		PajakType pajakType = (PajakType) resultHeader.get(i);
		Vector listRealisasiPerJenisPajak = PstDataPajak.listPendapatanPerJenisPajak(0, 0,
			"EXTRACT(YEAR FROM dataPajak."+PstDataPajak.fieldNames[PstDataPajak.FLD_TANGGALBAYAR]+")='"+Formater.formatDate(currenDate, "yyyy")+"' "
			+ "AND pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_TYPE_ID]+"='"+pajakType.getOID()+"'", 
			"", "pajakType."+PstPajakType.fieldNames[PstPajakType.FLD_PAJAK_NAME]);
		DataPajak dataPajak;
		if(listRealisasiPerJenisPajak.size() != 0){
		    dataPajak = (DataPajak) listRealisasiPerJenisPajak.get(0);
		}else{
		    dataPajak = new DataPajak();
		}
		
		double realisasi = dataPajak.getJumlahPajak()/pembagi;
		rowx.add(""+Formater.formatNumber((realisasi/realisasiTotal)*100,"#.##")+"%");
	    }
	}
	rowx.add("");
	lstData.add(rowx);
	return ctrlist.drawBootstrapStrip();

}
%>

<input type="hidden" name="approot" id="approot" value="<%= approot %>">
<input type='hidden' name='command' id='command' value='<%= Command.NONE %>'>
<ol class="breadcrumb newcrumb">
    <li>
            <span><i class="fa fontello-home-outline"></i>
            </span>Dashboard
    </li>
</ol>


<div class="row">
    <div class="col-md-12">
	<div class="box box-primary">
	    <div class="box-body">
		<div class="row">
		    <div class="col-md-1">
			<h4 class="box-title">Periode</h4>
		    </div>
		<div class="col-md-3">
		    <div class="input-group">
			<input type="text" class="datePickerDashboard form-control" name="FRM_FIELD_TANGGALBAYAR" id="FRM_FIELD_TANGGALBAYAR_DASHBOARD" value="<%= Formater.formatDate(currentDate, "dd MMMM yyyy") %>">
			<input type="hidden" id="FRM_FIELD_CURRENT_DATE" value="<%= Formater.formatDate(currentDate, "dd MMMM yyyy") %>">
			<div class="input-group-addon">
			    <i class="fa fa-calendar"></i>
			</div>
		    </div>
		</div>
		
		<div class="col-md-3" id="displayTime">
		    
		</div>
		    
		</div>
	    </div>
	</div>
	<div class="box box-primary">
	    <div class="box-header">
		<h3 class="box-title">Pendapatan Daerah</h3>
	    </div>
	    <div class="box-body">
		<div style="height: 20px;display: none;" id="reportperhari-progress">
		    <i class='fa fa-check'></i> Berhasil Memperbaharui Data
		</div>
		<div id="reportperhari">
	
		</div>
	    </div>
	</div>
	<div class="box box-primary">
	    <div class="box-header">
		<h3 class="box-title">Pendapatan Daerah</h3>
	    </div>
	    <div class="box-body">
		<div style="height: 20px;display: none;" id="reportperbulan-progress">
		    <i class='fa fa-check'></i> Berhasil Memperbaharui Data
		</div>
		<div id="reportperbulan">
	
		</div>
	    </div>
	</div>
    </div>
    <div class="col-md-6">
        <!-- AREA CHART -->
        <div class="box box-primary">
            <div class="box-header">
                <h3 class="box-title">Total Target vs Realisasi <%= Formater.formatDate(currentDate, "yyyy") %></h3>
            </div>
	    <div id="runchart" style="display:none;"></div>
            <div class="box-body chart-responsive">
		<div id="targetvsrealisasi" style="height: 300px;"></div>
            </div>
            <!-- /.box-body -->
        </div>
	<div class="box box-primary">
	    <div class="box-header">
		<h3 class="box-title">Pendapatan per Jenis Pajak</h3>
	    </div>
	    <div class="box-body chart-responsive">
		<div id="pendapatanperjenispajak" style="height: 300px;"></div>
	    </div>
	</div>
        <!-- /.box -->

    </div>
    <div class="col-md-6">
	<div class="box box-primary">
	    <div class="box-header">
		<h3 class="box-title">Total Pajak</h3>
	    </div>
	    <div class="box-body chart-responsive">
		<div id="totalpajak" style="height: 300px"></div>
	    </div>
	</div>
	<div class="box box-primary">
	    <div class="box-header">
		<h3 class="box-title">Persentase Pendapatan per Jenis Pajak</h3>
	    </div>
	    <div class="box-body chart-responsive">
		<div id="persentasependapatanperjenispajak" style="height: 300px;"></div>
	    </div>
	</div>
    </div>
    <div class="col-md-12">
	<div class="box box-primary">
	    <div class="box-header">
		<h3 class="box-title">Tabel Pendapatan Pajak</h3>
	    </div>
	    <div class="box-body">
		<%= drawTabelPendapatanPajak(currentDate,pembagi) %>
	    </div>
	</div>
    </div>
    
</div>
<!-- /.row -->
