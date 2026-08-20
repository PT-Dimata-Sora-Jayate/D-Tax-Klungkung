<%-- 
    Document   : hapus_data_segment
    Created on : Dec 5, 2017, 2:38:59 PM
    Author     : dimata005
--%>
<%@page import="com.dimata.gui.jsp.ControlList"%>
<%@page import="com.dimata.dslik.entity.debiturindividu.PstDebiturIndividu"%>
<%@page import="com.dimata.dslik.entity.suratberharga.PstSuratBerharga"%>
<%@page import="com.dimata.dslik.entity.penjamin.PstPenjamin"%>
<%@page import="com.dimata.dslik.entity.pengurusataupemilik.PstPengurusAtauPemilik"%>
<%@page import="com.dimata.dslik.entity.laporankeuangandebitur.PstLaporanKeuanganDebitur"%>
<%@page import="com.dimata.dslik.entity.kreditjoinaccount.PstKreditJoinAccount"%>
<%@page import="com.dimata.dslik.entity.kredit.PstKredit"%>
<%@page import="com.dimata.dslik.entity.irrevocablelc.PstIrrevocableLc"%>
<%@page import="com.dimata.dslik.entity.fasilitaslain.PstFasilitasLain"%>
<%@page import="com.dimata.dslik.entity.debiturbdnusaha.PstDebiturBdnUsaha"%>
<%@page import="com.dimata.dslik.entity.bankgaransi.PstBankGaransi"%>
<%@page import="com.dimata.dslik.entity.agunan.PstAgunan"%>
<%@page import="com.dimata.dslik.ajax.AjaxPelaporan"%>
<%@page import="com.dimata.dslik.entity.masterdata.CabangBank"%>
<%@page import="com.dimata.dslik.entity.masterdata.PstCabangBank"%>
<%@page import="com.dimata.dslik.session.proses.ManagerTransferData"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<%@page import="com.dimata.dslik.form.masterdata.FrmConnection"%>
<%@page import="com.dimata.gui.jsp.ControlCombo"%>
<%@page import="java.util.Vector"%>
<%@page import="com.dimata.util.Command"%>
<!DOCTYPE html>
<%!
    public final static int SEGMEN_DEBITUR_INDIVIDU = 0;
    public final static int SEGMEN_DEBITUR_BADAN_USAHA = 1;
    public final static int SEGMEN_PENGURUS_PEMILIK = 2;
    public final static int SEGMEN_KREDIT = 3;
    public final static int SEGMEN_BANK_GARANSI = 4;
    public final static int SEGMEN_AGUNAN = 5;
    public final static int SEGMEN_PENJAMIN = 6;
    public final static int SEGMEN_LAPORAN_KEUANGAN_DEBITUR = 7;
    public final static int SEGMEN_SURAT_BERHARGA = 8;
    public final static int SEGMEN_KREDIT_JOIN = 9;
    public final static int SEGMEN_IRREVOCABLE_LC = 10;
    public final static int SEGMEN_FASILITAS_LAINNYA = 11;
    
    public final static String[] segmenNames = {
        "Debitur Individu",
        "Debitur Badan Usaha",
        "Pengurus/Pemilik",
        "Kredit",
        "Bank Garansi",
        "Agunan",
        "Penjamin",
        "Laporan Keuangan Debitur",
        "Surat Berharga",
        "Kredit Join",
        "Irrevocable L/C",
        "Fasilitas Lainnya"
    };

    public String getPerSegmentReport(JspWriter outObj, long periode, String cabang, int segment, String cif){
        String returnData = "";
        String whereClause = "";
        Vector listObj = new Vector(1,1);
        switch(segment){
            case SEGMEN_AGUNAN :
                whereClause +=" AND "+PstAgunan.fieldNames[PstAgunan.FLD_CIF]+"='"+cif+"' ";
                listObj = PstAgunan.list(0,0,PstAgunan.fieldNames[PstAgunan.FLD_PERIODE_ID]+"='"+periode+"'"
                        + ""+whereClause, ""+PstAgunan.fieldNames[PstAgunan.FLD_KODE_REGISTER_AGUNAN]+" ASC");
            break;
                
            case SEGMEN_BANK_GARANSI :
                whereClause +=" AND "+PstBankGaransi.fieldNames[PstBankGaransi.FLD_CIF]+"='"+cif+"' ";
                listObj = PstBankGaransi.list(0,0, PstBankGaransi.fieldNames[PstBankGaransi.FLD_PERIODE_ID]+"='"+periode+"'"
                        + ""+whereClause,""+PstBankGaransi.fieldNames[PstBankGaransi.FLD_NO_REKENING]+" ASC");
            break;
                
            case SEGMEN_DEBITUR_BADAN_USAHA :
                whereClause +=" AND "+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_CIF]+"='"+cif+"' ";
                listObj = PstDebiturBdnUsaha.listJoinHapusData(0,0,"dslik_debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_PERIODE_ID]+"='"+periode+"' "
                        + "AND dslik_debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_KODE_JENIS_NSB]+"!='1'"
                        + ""+whereClause, "dslik_debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_CIF]+" ASC",periode,cabang);
            break;
                
            case SEGMEN_FASILITAS_LAINNYA :
                whereClause +=" AND "+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_CIF]+"='"+cif+"' ";
                listObj = PstFasilitasLain.list(0,0,""+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_PERIODE_ID]+"='"+periode+"'"
                        + ""+whereClause, PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_NO_REKENING]+" ASC");
            break;
                
            case SEGMEN_IRREVOCABLE_LC :
                whereClause +=" AND "+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_CIF]+"='"+cif+"' ";
                listObj = PstIrrevocableLc.list(0,0,""+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_PERIODE_ID]+"='"+periode+"'"
                        + ""+whereClause, PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_NO_REKENING]+" ASC");
            break;
                
            case SEGMEN_KREDIT :
                whereClause +=" AND "+PstKredit.fieldNames[PstKredit.FLD_CIF]+"='"+cif+"' ";
                listObj = PstKredit.list(0,0,""+PstKredit.fieldNames[PstKredit.FLD_PERIODE_ID]+"='"+periode+"'"
                        + ""+whereClause, PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]+" ASC");
            break;
                
            case SEGMEN_KREDIT_JOIN :
                whereClause +=" AND "+PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_CIF]+"='"+cif+"' ";
                listObj = PstKreditJoinAccount.list(0,0,""+PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_PERIODE_ID]+"='"+periode+"'"
                        + ""+whereClause, PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_NO_REKENING]+" ASC");
            break;
                
            case SEGMEN_LAPORAN_KEUANGAN_DEBITUR :
                whereClause +=" AND "+PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_CIF]+"='"+cif+"' ";
                listObj = PstLaporanKeuanganDebitur.list(0,0,""+PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_PERIODE_ID]+"='"+periode+"'"
                        + ""+whereClause, PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_CIF]+" ASC");
            break;
                
            case SEGMEN_PENGURUS_PEMILIK :
                whereClause +=" AND "+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_CIF]+"='"+cif+"' ";
                listObj = PstPengurusAtauPemilik.list(0,0,""+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_PERIODE_ID]+"='"+periode+"'"
                        + ""+whereClause, PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_NO_IDENTITAS]+" ASC");
            break;
                
            case SEGMEN_PENJAMIN : 
                whereClause +=" AND "+PstPenjamin.fieldNames[PstPenjamin.FLD_CIF]+"='"+cif+"' ";
                listObj = PstPenjamin.list(0,0,""+PstPenjamin.fieldNames[PstPenjamin.FLD_PERIODE_ID]+"='"+periode+"'"
                        + ""+whereClause, PstPenjamin.fieldNames[PstPenjamin.FLD_NO_ID_PENJAMIN]+" ASC");
            break;
                
            case SEGMEN_SURAT_BERHARGA :
                whereClause +=" AND "+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_CIF]+"='"+cif+"' ";
                listObj = PstSuratBerharga.list(0,0,""+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_PERIODE_ID]+"='"+periode+"'"
                        + ""+whereClause, PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_NO_REKENING]+" ASC");
            break;
            default:
                whereClause +=" AND "+PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_CIF]+"='"+cif+"' ";
                listObj = PstDebiturIndividu.listJoin(0,0,"dslik_debitur."+PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_PERIODE_ID]+"='"+periode+"' "
                        + "AND dslik_debitur."+PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_KODE_JENIS_NSB]+"='1'"
                        + ""+whereClause, "dslik_debitur."+PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_CIF]+" ASC",periode,cabang);
            break;
        }
        
        drawReportPerSegment( outObj, 0, listObj, segment, periode, cif);
        return"";
    }


    public void drawReportPerSegment(JspWriter outObj, int iCommand, Vector objectClass, int segment, long periode, String cif) {

        ControlList ctrlist = new ControlList();

        ctrlist.setAreaWidth("100%");
        ctrlist.setListStyle("listgen");
        ctrlist.setTitleStyle("tableheader");
        ctrlist.setCellStyle("cellStyle");
        ctrlist.setHeaderStyle("tableheader");
        ctrlist.addHeader("Action", "200px");//1
        ctrlist.addHeader("Cabang", "200px");//2
        ctrlist = AjaxPelaporan.draControlList(ctrlist, segment);
        ctrlist.setLinkSufix("");
        Vector lstData = ctrlist.getData();
        Vector lstLinkData = ctrlist.getLinkData();
        ctrlist.reset();

        int index = -1;
        int count=0;
        Vector rowx = new Vector(1, 1);
        double total=0.0;
        
        lstData = AjaxPelaporan.drawRowHapusData(objectClass,segment,lstData,0,periode,cif);

        ctrlist.drawBootstrapStrip(outObj);
    }
%>
<%@include file="../../main/javainit_slik.jsp" %>
<%@include file="../../main/checkuser_slik.jsp" %>
<%  
    boolean privView = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_MODUL_LAPORAN, AppObjInfo.G2_PER_SEGMENT_DETAIL, AppObjInfo.OBJ_PER_SEGMENT_DETAIL, AppObjInfo.COMMAND_VIEW);
    boolean privPrint = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_MODUL_LAPORAN, AppObjInfo.G2_PER_SEGMENT_DETAIL, AppObjInfo.OBJ_PER_SEGMENT_DETAIL, AppObjInfo.COMMAND_PRINT);
    boolean privViewCheck = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_PROSES_DELIMITED_TEXT, AppObjInfo.G2_BULANAN, AppObjInfo.OBJ_BULANAN, AppObjInfo.COMMAND_VIEW);
    int iCommand = FRMQueryString.requestCommand(request);
    String startDate = FRMQueryString.requestString(request, "tanggalStart");
    String oDate = "";
    String startEnd = FRMQueryString.requestString(request, "tanggalEnd");
    int start = FRMQueryString.requestInt(request, "start");
    int prevCommand = FRMQueryString.requestInt(request, "prev_command");
    String cabang = FRMQueryString.requestString(request, "cabangpersegment");
    long periodeId = FRMQueryString.requestLong(request, "periodepersegment");
    int getSegment = FRMQueryString.requestInt(request, "segment");
    String cif = FRMQueryString.requestString(request, "cif");
    int summary = FRMQueryString.requestInt(request, "summary");
    long oiddata = FRMQueryString.requestLong(request, "oiddata");
    int typesegment = FRMQueryString.requestInt(request, "typesegment");
    Vector cabangKey = new Vector(1,1);
    Vector cabangVal = new Vector(1,1);
    if(privViewCheck) {
        cabangKey.add("");
        cabangVal.add("Semua Cabang/Capem");
    }
    String whereCabang="";
    if(kd_bank!=null){
        if(!kd_bank.equals("") && privViewCheck==false){
            whereCabang=PstCabangBank.fieldNames[PstCabangBank.FLD_KODE_CABANG]+"='"+kd_bank+"'";
        }
    }
    
    Vector periodeKey = new Vector(1,1);
    Vector periodeVal = new Vector(1,1);
    Vector listPeriode = PstPeriode.list(0, 0, "", ""+PstPeriode.fieldNames[PstPeriode.FLD_TGL_AKHIR]+" DESC");
    if(listPeriode.size() > 0){
        for(int i = 0; i < listPeriode.size(); i++){
            Periode entPeriode = (Periode) listPeriode.get(i);
            periodeKey.add(""+entPeriode.getOID());
            periodeVal.add(""+entPeriode.getNama());
        }
    }
    
    Vector segmentKey = new Vector(1,1);
    Vector segmentVal = new Vector(1,1);
    for(int i = 0; i < AjaxPelaporan.segmenNames.length; i++){
        segmentKey.add(""+i);
        segmentVal.add(""+AjaxPelaporan.segmenNames[i]);
    }
    
    if(iCommand==Command.DELETE){
        if(oiddata!=0){
            long oid =0;
            switch (typesegment) {
                case SEGMEN_AGUNAN:
                    //System.out.print("xxxx");
                    oid = PstAgunan.deleteExc(oiddata);
                break;
                case SEGMEN_DEBITUR_BADAN_USAHA:
                    //System.out.print("xxxx");
                    oid = PstDebiturBdnUsaha.deleteExc(oiddata);
                break;
                case SEGMEN_KREDIT:
                    //System.out.print("xxxx");
                    oid = PstKredit.deleteExc(oiddata);
                break;
                case SEGMEN_PENGURUS_PEMILIK:
                    //System.out.print("xxxx");
                    oid = PstPengurusAtauPemilik.deleteExc(oiddata);
                break;
                case SEGMEN_BANK_GARANSI:
                    //System.out.print("xxxx");
                    oid = PstBankGaransi.deleteExc(oiddata);
                break;
                case SEGMEN_PENJAMIN:
                    //System.out.print("xxxx");
                    oid = PstPenjamin.deleteExc(oiddata);
                break;
                case SEGMEN_DEBITUR_INDIVIDU:
                    //System.out.print("xxxx");
                    oid = PstDebiturIndividu.deleteExc(oiddata);
                break;
                default:   
                   // System.out.print("xxxx");
            }
        }
        iCommand=Command.SEARCH;
    }    
%>

<html>
    <head>
        <meta charset="UTF-8">
        <title>SLIK | Laporan Per Segmen Detail</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <%@include file="../../template-component/css-component.jsp" %>
        <script language="JavaScript">

            function cmdStart() {
                document.driver.command.value = "<%=Command.START%>";
                document.driver.hidden_driver_id.value = "0";
                document.driver.action = "pbb.jsp";
                document.driver.submit();
            }

            function cmdStop() {

                document.driver.command.value = "<%=Command.STOP%>";
                document.driver.start.value = "0";
                document.driver.action = "pbb.jsp";
                document.driver.submit();

            }

            function lockScreen(str)
            {
                var lock = document.getElementById('theLockPane');
                if (lock)
                    lock.className = 'LockOn';

                lock.innerHTML = str;
            }
            
            function cmdSearch(){
                document.driver.command.value="<%=Command.SEARCH%>";
                document.driver.prev_command.value="<%=prevCommand%>";
                document.driver.action="hapus_data_segment.jsp";
                document.driver.submit();
                lockScreen('Proses Reporting Sedang Berlangsung, Silahkan menunggu...');
            }
            
            function cmdSearchPrint(){
                document.driver.command.value="<%=Command.PRINT%>";
                document.driver.prev_command.value="<%=prevCommand%>";
                document.driver.action="hapus_data_segment.jsp";
                document.driver.submit();
            }
            
            function cmdSearchExcel(){
                document.driver.command.value="<%=Command.SEARCH%>";
                document.driver.prev_command.value="<%=prevCommand%>";
                document.driver.action="hapus_data_segment_excel.jsp?cabangpersegment=<%=cabang%>&periodepersegment=<%=periodeId%>&segment=<%=getSegment%>&cif=<%=cif%>";
                document.driver.submit();
            }
            
            
            function cmdSearchPdf(){
                window.open("<%=approot%>/hapus_data_segment?cabangpersegment=<%=cabang%>&periodepersegment=<%=periodeId%>&segment=<%=getSegment%>&cif=<%=cif%>","",'scrollbars=yes,status=yes,width=750,height=500,resizable=yes');
            }
            
            function cmdChangeJenisDebitur(oid, type){
                alert("oid : "+oid+" tipe "+type);
                document.driver.command.value="<%=Command.DELETE%>";
                document.driver.prev_command.value="<%=prevCommand%>";
                document.driver.oiddata.value=oid;
                document.driver.typesegment.value=type;
                document.driver.action="hapus_data_segment.jsp";
                document.driver.submit();
            }
            
        </script>
    </head>
    <body class="<%= skin%>">
        <div id="theLockPane" class="LockOff"></div>
        <input type="hidden" name="command" id="command" value="<%= Command.NONE%>">
        <input type="hidden" name="approot" id="approot" value="<%= approot%>">
        <div class="wrapper">

            <%@include file="../../template-component/header-component.jsp" %>
            <%@include file="../../template-component/sidebar-component.jsp" %>

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Laporan 
                        <small>Per Segmen Detail</small>
                    </h1>
                </section>
                <!-- Main content -->
                <%
                    if(privView){
                %>
               <section class="content">
                    <form name="driver"  method ="post" action="" role="form">
                        <input type="hidden" name="command" value="<%=iCommand%>">
                        <input type="hidden" name="start" value="<%=start%>">
                        <input type="hidden" name="prev_command" value="<%=prevCommand%>">
                        <input type="hidden" name="menu" value="16">
                        <input type="hidden" name="tree" value="5">
                        <input type="hidden" name="oiddata" value="<%=oiddata%>">
                        <input type="hidden" name="typesegment" value="<%=typesegment%>">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="box">
                                    <div class="box-body">
                                        <div class="row">
                                            <div class="col-xs-3"><label>Cabang/Capem</label>
                                                <%=ControlCombo.drawBootsratap("cabangpersegment", null, ""+cabang, cabangKey, cabangVal, "", "form-control") %>
                                            </div>
                                            <div class="col-xs-2"><label>Periode</label>
                                                <%= ControlCombo.draw("periodepersegment", null, ""+periodeId, periodeKey, periodeVal, "", "form-control") %>
                                            </div>
                                            <div class="col-xs-2"><label>Segment</label>  
                                                    <%= ControlCombo.draw("segment", "Semua Segment", ""+getSegment, segmentKey, segmentVal, "", "form-control") %>
                                            </div>
                                            <div class="col-xs-3"><label>CIF</label>  
                                                <div class="input-group"> 
                                                    <input name="cif" type="text" class="form-control" placeholder="CIF" value="<%=cif%>" size="25">
                                                    <span class="input-group-btn">
                                                        <button type='button' name='seach' id='search-btn-segment' class="btn btn-success pull-right" onclick="javascript:cmdSearch()" ><i class="fa fa-search"></i></button>
                                                    </span>
                                                 </div>
                                            </div>     
                                        </div>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <h3 class="box-title">Laporan Per Segmen Detail</h3>
                                            </div>
                                        </div>
                                        <%if(iCommand==Command.SEARCH){%>
                                            <div class="row">
                                                <div class="col-md-12" id="reportsummarypersegment">
                                                    <div class='row'>
                                                        <div class='col-md-12'>
                                                            <div class='form-group'>
                                                                <%=getPerSegmentReport(out, periodeId, cabang, getSegment,cif)%>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class='row'>
                                                        <div class='col-md-12'>
                                                            <div class='form-group'><i class='fa fa-remove'></i>
                                                                &nbsp;
                                                            </div>
                                                        </div>
                                                    </div>  
                                                </div>
                                            </div>
                                        <%}%>
                                    </div>       
                                </div><!-- /.box -->
                            </div>
                        </div>         
                    </form>
                </section><!-- /.content -->
                <%
                    }
                %>
            </div><!-- /.content-wrapper -->
            <!-- Add the sidebar's background. This div must be placed
                 immediately after the control sidebar -->
            <div class='control-sidebar-bg'></div>
            <%@include file="../../template-component/footer-component.jsp" %>
            <%@include file="../../template-component/plugins-component.jsp" %>
        </div><!-- ./wrapper -->
        <%
        if(iCommand==Command.PRINT){
            %>
            <script language="JavaScript">
            cmdSearchExcel();
            </script>
        <%
        }
        %>
        
    </body>
</html>
                