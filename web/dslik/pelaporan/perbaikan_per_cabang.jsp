<%-- 
    Document   : perbaikan_per_cabang
    Created on : Aug 2, 2017, 2:24:52 PM
    Author     : dimata005
--%>

<%@page import="com.dimata.dslik.entity.masterdata.PstPeriodeCabang"%>
<%@page import="com.dimata.dslik.entity.debitur.PstDebitur"%>
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

    
    public String drawReportPerSegmentxx(JspWriter outObj, int iCommand, Vector objectClass, int segment, long periodeId) {
        //drawReportPerSegmentList(outObj, iCommand, objectClass,segment, periodeId);
        return "";
    }

    public String drawReportPerSegment(JspWriter outObj, int iCommand, Vector objectClass, int segment, long periodeId, int kelengkapan) {

        
        ControlList ctrlist = new ControlList();

        ctrlist.setAreaWidth("100%");
        ctrlist.setListStyle("listgen");
        ctrlist.setTitleStyle("tableheader");
        ctrlist.setCellStyle("cellStyle");
        ctrlist.setHeaderStyle("tableheader");
        ctrlist.addHeader("NO", "200px");//1
        ctrlist.addHeader("KODE", "200px");//2
        ctrlist.addHeader("UNIT KERJA", "200px");//3
        ctrlist.addHeader("DEBITUR INDIVIDU", "200px");//4
        ctrlist.addHeader("BADAN USAHA", "200px");//5
        ctrlist.addHeader("BADAN USAHA TANPA PENGURUS", "200px");//6
        ctrlist.addHeader("AGUNAN", "200px");//7
        ctrlist.addHeader("PENJAMIN", "200px");//8
        ctrlist.addHeader("KREDIT", "200px");//9
        ctrlist.addHeader("BANK GARANSI", "200px");//7
        ctrlist.addHeader("PENGURUS/PEMILIK", "200px");//10

        ctrlist.addHeader("SURAT BERHARGA", "200px");//10
        ctrlist.addHeader("KREDIT JOIN", "200px");//10
        ctrlist.addHeader("IRREVOCABLE L/C", "200px");//10
        ctrlist.addHeader("FASILITAS LAINNYA", "200px");//10
        ctrlist.addHeader("LAP. KEUANGAN", "200px");//10
        ctrlist.addHeader("PANGSA KURANG / LEBIH 100%", "200px");//11

        if(kelengkapan==2){            
            ctrlist.addHeader("DEBITUR INDIVIDU NIK SAMA", "200px");//11
            ctrlist.addHeader("DEBITUR BDN USAHA NO IDENTITAS SAMA", "200px");//11
        }
        
        ctrlist.addHeader("TOTAL", "200px");//12
        ctrlist.addHeader("APPROVE DATA", "200px");//12
        if (iCommand != Command.EDIT) {
            ctrlist.setLinkRow(0);
        }
        
        ctrlist.setLinkSufix("");
        Vector lstData = ctrlist.getData();
        Vector lstLinkData = ctrlist.getLinkData();
        ctrlist.reset();

        int index = -1;
        int count=0;
        Vector rowx = new Vector(1, 1);
        double total=0.0;
        
        for (int i = 0; i < objectClass.size(); i++) {
            count=count+1;
            rowx = new Vector(1, 1);
            CabangBank cabangBank = (CabangBank) objectClass.get(i);
            rowx.add(""+count);//1
            rowx.add(""+cabangBank.getKodeCabang());//2
            rowx.add(""+cabangBank.getNamaCabang());//3
            System.out.print("lap "+count);

            String whereClauseDebitur = "debitur."+PstDebitur.fieldNames[PstDebitur.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB]+"='1'";
            whereClauseDebitur += " AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID]+"='"+periodeId+"'";
            int debiturIndividu = PstDebitur.getCountJoin(whereClauseDebitur,""+periodeId, cabangBank.getKodeCabang());
            rowx.add(""+debiturIndividu);//debitur individu
            
            String whereClauseDebiturBdnUsaha = "debitur."+PstDebitur.fieldNames[PstDebitur.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB]+"!='1'";
            whereClauseDebiturBdnUsaha += " AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID]+"='"+periodeId+"'";
            int debiturBdanUsaha = PstDebitur.getCountJoin(whereClauseDebiturBdnUsaha,""+periodeId, cabangBank.getKodeCabang());
            rowx.add(""+debiturBdanUsaha);//debitur badan usaha
            
            String whereClauseBdnUsahaTnpaPengurus = " debitur."+PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB]+"!='1' ";
            whereClauseBdnUsahaTnpaPengurus += " AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID]+"='"+periodeId+"'";
            int debituTanpaBdnUsaha = PstDebiturBdnUsaha.getCountTidakAdaPengurus(whereClauseBdnUsahaTnpaPengurus,""+periodeId, cabangBank.getKodeCabang());
            rowx.add(""+debituTanpaBdnUsaha);//6 debitur badan usaha tanpa pengurus

            String whereClauseAgunan = "agunan."+PstAgunan.fieldNames[PstAgunan.FLD_STATUS_DATA_PERUBAHAN]+" IS NULL";
            whereClauseAgunan += " AND agunan."+PstAgunan.fieldNames[PstAgunan.FLD_PERIODE_ID]+"='"+periodeId+"'";
            whereClauseAgunan += " AND agunan."+PstAgunan.fieldNames[PstAgunan.FLD_KODE_KANTOR_CABANG]+"='"+cabangBank.getKodeCabang()+"' ";
            int agunan = PstAgunan.getCountJoin(whereClauseAgunan);
            rowx.add(""+agunan);//7 agunan
            
            String whereClausePenjamin = "penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
            int penjamin = PstPenjamin.getCountJoin(whereClausePenjamin,periodeId,cabangBank.getKodeCabang());
            rowx.add(""+penjamin);//penjamin

            String whereClauseKredit = "kredit."+PstKredit.fieldNames[PstKredit.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
            whereClauseKredit += " AND kredit."+PstKredit.fieldNames[PstKredit.FLD_KODE_KANTOR_CABANG]+"='"+cabangBank.getKodeCabang()+"'  ";
            whereClauseKredit += " AND kredit."+PstKredit.fieldNames[PstKredit.FLD_PERIODE_ID]+"='"+periodeId+"'";
            int kredit = PstKredit.getCountJoin(whereClauseKredit);
            rowx.add(""+kredit);// kredit
            
            String whereClauseBankGaransi = "bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
            whereClauseBankGaransi += " AND bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_KODE_KANTOR_CABANG]+"='"+cabangBank.getKodeCabang()+"'  ";
            whereClauseBankGaransi += " AND bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_PERIODE_ID]+"='"+periodeId+"'";
            int bankGaransi = PstBankGaransi.getCountJoin(whereClauseBankGaransi);
            rowx.add(""+bankGaransi);// bank garansi
            
            String whereClausePengurus = "pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
            whereClausePengurus += " AND pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_PERIODE_ID]+"='"+periodeId+"'";
            int pengurus = PstPengurusAtauPemilik.getCountPengurusPemilik(whereClausePengurus, periodeId, cabangBank.getKodeCabang());
            rowx.add(""+pengurus);//pengurus
            
           
            
            String whereClauseSuratBerharha = "sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
            whereClauseSuratBerharha += " AND sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_KODE_KANTOR_CABANG]+"='"+cabangBank.getKodeCabang()+"'";
            int suratBerharga = PstSuratBerharga.getCountJoin(whereClauseSuratBerharha);
            rowx.add(""+suratBerharga);//11 ctrlist.addHeader("SURAT BERHARGA", "200px");//10

            String whereClauseKreditJoin = "kj."+PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
            whereClauseKreditJoin += " AND kj."+PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KODE_KANTOR_CABANG]+"='"+cabangBank.getKodeCabang()+"'";
            int intkreditjoin = PstKreditJoinAccount.getCountJoin(whereClauseKreditJoin);
            rowx.add(""+intkreditjoin);//11ctrlist.addHeader("KREDIT JOIN", "200px");//10
            
            String whereClauseIrrevocableLc = "ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_STATUS_DATA]+" IS NULL";
            whereClauseIrrevocableLc += " AND ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_KODE_KANTOR_CABANG]+"='"+cabangBank.getKodeCabang()+"'";
            int irrevocableLc = PstIrrevocableLc.getCountJoin(whereClauseIrrevocableLc);
            rowx.add(""+irrevocableLc);//11ctrlist.addHeader("IRREVOCABLE L/C", "200px");//10

            String whereClauseFasilitasLain = "fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
            whereClauseFasilitasLain += " AND fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_KODE_KANTOR_CABANG]+"='"+cabangBank.getKodeCabang()+"'";
            int intFasilitasLain = PstFasilitasLain.getCountJoin(whereClauseFasilitasLain);
            rowx.add(""+intFasilitasLain);//11ctrlist.addHeader("FASILITAS LAINNYA", "200px");//10
            
            String whereClauseLaporanKeungan = "laporan."+PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
            whereClauseLaporanKeungan += " AND laporan."+PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_KODE_KANTOR_CABANG]+"='"+cabangBank.getKodeCabang()+"'";
            int summarylapKeu = PstLaporanKeuanganDebitur.getCountJoin(whereClauseLaporanKeungan);
            rowx.add(""+summarylapKeu);//11ctrlist.addHeader("LAP. KEUANGAN", "200px");//10

            String whereClausePangsa = " pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_PERIODE_ID]+"='"+periodeId+"' ";
            int pangsa = PstPengurusAtauPemilik.getCountPangsaKurangLebihSeratus(whereClausePangsa, periodeId, cabangBank.getKodeCabang());
            rowx.add(""+pangsa);//11
            
            int indDISama=0;
            int indDBdnSama=0;

            if(kelengkapan==2){       
                Vector IndividulistSama = PstDebiturIndividu.listDoubleNik(0, 0, cabangBank.getKodeCabang(), periodeId);
                if(IndividulistSama.size()>0){
                    indDISama=IndividulistSama.size();
                }
                rowx.add(""+indDISama);//12
                Vector listSama = PstDebiturBdnUsaha.listDoubleNik(0, 0, cabangBank.getKodeCabang(), periodeId);
                if(listSama.size()>0){
                    indDBdnSama=listSama.size();
                }
                rowx.add(""+indDBdnSama);//12
            }
            
            int subtotal=debiturIndividu+debiturBdanUsaha+debituTanpaBdnUsaha+agunan+penjamin+kredit+pengurus+pangsa+indDISama+indDBdnSama+bankGaransi+suratBerharga+intkreditjoin+irrevocableLc+intFasilitasLain+summarylapKeu;
            rowx.add(""+subtotal);//12
            
            String cabangBelumApprove = PstPeriodeCabang.getStringCabangOpen("dpc."+PstPeriodeCabang.fieldNames[PstPeriodeCabang.FLD_STATUS]+"=0 "
                                                                          + " AND dpc."+PstPeriodeCabang.fieldNames[PstPeriodeCabang.FLD_PERIODE_ID]+"='"+periodeId+"'"
                                                                          + " AND dcb."+PstCabangBank.fieldNames[PstCabangBank.FLD_KODE_CABANG]+"='"+cabangBank.getKodeCabang()+"'");                               
            if(cabangBelumApprove.equals("")){
                rowx.add("APPROVE");//12
            }else{
                rowx.add("");//12
            }
            lstData.add(rowx);
        }

        return ctrlist.drawBootstrapStrip();
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
    int kelengkapan = FRMQueryString.requestInt(request, "kelengkapan");
    int summary = FRMQueryString.requestInt(request, "summary");
    
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
    
    Vector listCabang = PstCabangBank.list(0, 0, ""+whereCabang, ""+PstCabangBank.fieldNames[PstCabangBank.FLD_KODE_CABANG]+" ASC");
    if(listCabang.size() > 0){
        for(int i = 0; i < listCabang.size(); i++){
            CabangBank cabangBank = (CabangBank) listCabang.get(i);
            cabangKey.add(""+cabangBank.getKodeCabang());
            cabangVal.add("("+cabangBank.getKodeCabang()+") "+cabangBank.getNamaCabang());
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
    
    Vector lengkapKey = new Vector(1,1);
    Vector lengkapVal = new Vector(1,1);
    lengkapKey.add("2");
    lengkapVal.add("Detail");
    lengkapKey.add("1");
    lengkapVal.add("Per Data Segment");
    
    String whereCabangSelected="";
    if(!cabang.equals("")){
        whereCabangSelected=PstCabangBank.fieldNames[PstCabangBank.FLD_KODE_CABANG]+"='"+cabang+"'";
    }
    Vector objListCabang = PstCabangBank.list(0, 0, ""+whereCabangSelected, ""+PstCabangBank.fieldNames[PstCabangBank.FLD_KODE_CABANG]+" ASC");

%>

<html>
    <head>
        <meta charset="UTF-8">
        <title>SLIK | Laporan Per Detail Error</title>
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
                document.driver.action="perbaikan_per_cabang.jsp";
                document.driver.submit();
                lockScreen('Proses Reporting Sedang Berlangsung, Silahkan menunggu...');
            }
            
            function cmdSearchPrint(){
                document.driver.command.value="<%=Command.PRINT%>";
                document.driver.prev_command.value="<%=prevCommand%>";
                document.driver.action="perbaikan_per_cabang.jsp";
                document.driver.submit();
                lockScreen('Proses Reporting Sedang Berlangsung, Silahkan menunggu...');
            }
            
            function cmdSearchExcel(){
                document.driver.command.value="<%=Command.SEARCH%>";
                document.driver.prev_command.value="<%=prevCommand%>";
                document.driver.action="perbaikan_per_cabang_excel.jsp?cabangpersegment=<%=cabang%>&periodepersegment=<%=periodeId%>&segment=<%=getSegment%>&kelengkapan=<%=kelengkapan%>";
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
                        <small>Summary Data Segment Tidak Lengkap</small>
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
                                                    <%= ControlCombo.draw("segment","Semua", ""+getSegment, segmentKey, segmentVal, "", "form-control") %>
                                            </div>
                                            <div class="col-xs-3"><label>Kelengkapan</label>  
                                                <div class="input-group"> 
                                                    <%= ControlCombo.draw("kelengkapan", null, ""+kelengkapan, lengkapKey, lengkapVal, "", "form-control") %>
                                                 <span class="input-group-btn">
                                                     <button type='button' name='seach' id='search-btn-segment' class="btn btn-success pull-right" onclick="javascript:cmdSearch()" ><i class="fa fa-search"></i></button>
                                                 </span>
                                                 <span class="input-group-btn">
                                                     <button type='button' name='seach' id='search-btn-segment' class="btn btn-danger pull-right" onclick="javascript:cmdSearchExcel()" ><i class="fa fa-print"></i></button>
                                                 </span>
                                                 </div>
                                            </div>     
                                        </div>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <h3 class="box-title">Laporan Per Detail Error</h3>
                                            </div>
                                        </div>
                                        <%if(iCommand==Command.SEARCH){%>
                                            <div class="row">
                                                <div class="col-md-12" id="reportsummarypersegment">
                                                    <div class='row'>
                                                        <div class='col-md-12'>
                                                            <div class='form-group'>
                                                                <%=drawReportPerSegment(out,iCommand,objListCabang,0,periodeId, kelengkapan)%>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class='row'>
                                                        <div class='col-md-12'>
                                                            <div class='form-group'>
                                                                &nbsp;
                                                            </div>
                                                        </div>
                                                    </div>  
                                                </div>
                                            </div>
                                        <%}%>
                                    </div>
                                    <div class="row" style="margin-top:10px;">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <%
                                                    if(privPrint){
                                                        %>
                                                            &nbsp;&nbsp;&nbsp;<button class="btn btn-primary" onclick="javascript:cmdSearchExcel()"><i class="fa fa-save"></i> Export Excel</button>
                                                            <button class="btn btn-danger btnback hidden" type="button"><i class="fa fa-save"></i> Export Pdf</button>
                                                        <%
                                                    }
                                                %>
                                                
                                              </div>
                                        </div>
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
            <%@include file="../../template-component/plugins-component.jsp" %>
        </div><!-- ./wrapper -->
        <script language="JavaScript">
            function cmdSearchExcel2(cabang){
                    document.driver.command.value="<%=Command.SEARCH%>";
                    document.driver.prev_command.value="<%=prevCommand%>";
                    document.driver.action="perbaikan_per_cabang_excel.jsp?cabangpersegment="+cabang+"&periodepersegment=<%=periodeId%>&segment=<%=getSegment%>&kelengkapan=<%=kelengkapan%>";
                    document.driver.submit();
                }    
        <%
            if(iCommand==Command.PRINT){
                if(listCabang.size() > 0){
                    for(int i = 0; i < listCabang.size(); i++){
                        CabangBank cabangBank = (CabangBank) listCabang.get(i);
                        %>
                                cmdSearchExcel2('<%=cabangBank.getKodeCabang()%>');

                        <%
                    }
                }
            }
            %>
        </script>
    </body>
</html>
