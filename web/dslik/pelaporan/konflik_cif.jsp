<%-- 
    Document   : konflik_cif
    Created on : Aug 31, 2017, 11:20:59 AM
    Author     : dimata005
--%>

<%@page import="com.dimata.dslik.session.proses.SessFixedCifKredit"%>
<%@page import="com.dimata.util.Formater"%>
<%@page import="com.dimata.dslik.entity.bankgaransi.BankGaransi"%>
<%@page import="com.dimata.dslik.session.report.SessKredit"%>
<%@page import="com.dimata.dslik.entity.kredit.Kredit"%>
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

    public String drawReportPerSegment(JspWriter outObj, int iCommand, Vector objectClass, int segment, long periodeId) {

      
        ControlList ctrlist = new ControlList();
        Periode periode = new Periode();
        Periode periodePrev = new Periode();
        try{
            periode = PstPeriode.fetchExc(periodeId);
            try{
                Vector listPeriode = PstPeriode.list(0, 1, ""+PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID]+"='"+periodeId+"'", "");
                if(listPeriode != null){
                    periodePrev = (Periode) listPeriode.get(0);
                    periodePrev.getTglAwal().setMonth(periodePrev.getTglAwal().getMonth()-1);
                    Vector listPeriodex = PstPeriode.list(0, 1, ""+PstPeriode.fieldNames[PstPeriode.FLD_TGL_AWAL]+" BETWEEN '"+Formater.formatDate(periodePrev.getTglAwal(),"yyyy-MM-dd") +"' AND '"+Formater.formatDate(periodePrev.getTglAwal(),"yyyy-MM-dd")+"'", "");
                    if(listPeriodex != null){
                        if(listPeriodex.size()>0){
                            periodePrev = (Periode) listPeriodex.get(0);
                        }
                     }
                }
            }catch(Exception ex){
            }
        }catch(Exception ex){
        }
        ctrlist.setAreaWidth("100%");
        ctrlist.setListStyle("listgen");
        ctrlist.setTitleStyle("tableheader");
        ctrlist.setCellStyle("cellStyle");
        ctrlist.setHeaderStyle("tableheader");
        ctrlist.addHeader("NO", "200px");//1
        ctrlist.addHeader("NO REKENING ", "200px");//1
        ctrlist.addHeader("CIF BULAN " + periodePrev.getNama(), "200px");//1
        ctrlist.addHeader("CIF BULAN "+periode.getNama(), "200px");//1
        ctrlist.addHeader("NO REKENING", "200px");//1
        ctrlist.addHeader("ACTION");//1

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
            Kredit kredit = (Kredit) objectClass.get(i);
            rowx.add(""+count);//
            rowx.add(""+kredit.getNoRekening());//
            rowx.add(""+kredit.getCif());//
            //cek di periode, cif yang dipergunakan berapa
            String cif = SessKredit.checkCifKredir(periodeId, kredit.getNoRekening());
            if(cif.equals("")){
                rowx.add("rekening tdk ditemukan (pindah)");//
                rowx.add("");
                rowx.add("<button type='button' name='seach' id='search-btn-segment' class='btn btn-danger' onclick=\"javascript:cmdNotFound('"+kredit.getNoRekening()+"','"+periodeId+"','"+kredit.getCif()+"')\"><i class='fa fa-check'></i></button>");//
            }else{
                rowx.add(""+cif);//
                double countRek = SessKredit.checkCountCifKredir(periodeId, cif);
                String listRek = SessKredit.checkNorekeningKredit(periodeId, cif);
                if(countRek > 1){
                    //insert cif
                    rowx.add(""+listRek);
                    //rowx.add("<button type='button' name='seach' id='search-btn-segment' class='btn btn-danger' onclick=\"javascript:cmdNotFoundx('"+kredit.getNoRekening()+"','"+periodeId+"','"+kredit.getCif()+"')\"><i class='fa fa-check'></i></button>");//
                    rowx.add("<button type='button' name='seach' id='search-btn-segment' class='btn btn-warning' onclick=\"javascript:cmdReplaceCifInsert('"+kredit.getNoRekening()+"','"+periodeId+"','"+kredit.getCif()+"','"+cif+"')\"><i class='fa fa-check'></i></button>");//
                }else{
                    //replace
                    rowx.add(""+listRek);
                    rowx.add("<button type='button' name='seach' id='search-btn-segment' class='btn btn-info' onclick=\"javascript:cmdReplaceCif('"+kredit.getNoRekening()+"','"+periodeId+"','"+kredit.getCif()+"','"+cif+"')\"><i class='fa fa-check'></i></button>");//
                }
            }
            lstData.add(rowx);
        }

        return ctrlist.drawBootstrap();
    }


    public String drawReportPerSegmentBankGaransi(JspWriter outObj, int iCommand, Vector objectClass, int segment, long periodeId) {

        ControlList ctrlist = new ControlList();
        Periode periode = new Periode();
        Periode periodePrev = new Periode();
        try{
            periode = PstPeriode.fetchExc(periodeId);
            try{
                Vector listPeriode = PstPeriode.list(0, 1, ""+PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID]+"='"+periodeId+"'", "");
                if(listPeriode != null){
                    periodePrev = (Periode) listPeriode.get(0);
                    periodePrev.getTglAwal().setMonth(periodePrev.getTglAwal().getMonth()-1);
                    Vector listPeriodex = PstPeriode.list(0, 1, ""+PstPeriode.fieldNames[PstPeriode.FLD_TGL_AWAL]+" BETWEEN '"+Formater.formatDate(periodePrev.getTglAwal(),"yyyy-MM-dd") +"' AND '"+Formater.formatDate(periodePrev.getTglAwal(),"yyyy-MM-dd")+"'", "");
                    if(listPeriodex != null){
                        if(listPeriodex.size()>0){
                            periodePrev = (Periode) listPeriodex.get(0);
                        }
                     }
                }
            }catch(Exception ex){
            }
        }catch(Exception ex){
        }
        ctrlist.setAreaWidth("100%");
        ctrlist.setListStyle("listgen");
        ctrlist.setTitleStyle("tableheader");
        ctrlist.setCellStyle("cellStyle");
        ctrlist.setHeaderStyle("tableheader");
        ctrlist.addHeader("NO", "200px");//1
        ctrlist.addHeader("NO REKENING", "200px");//1
        ctrlist.addHeader("CIF BULAN" + periodePrev.getNama(), "200px");//1
        ctrlist.addHeader("CIF BULAN "+periode.getNama(), "200px");//1
        ctrlist.addHeader("NO REKENING", "200px");//1
        ctrlist.addHeader("ACTION");//1
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
            BankGaransi bankGaransi = (BankGaransi) objectClass.get(i);
            rowx.add(""+count);//
            rowx.add(""+bankGaransi.getNoRekening());//
            rowx.add(""+bankGaransi.getCif());//
            
            //cek di periode, cif yang dipergunakan berapa
            String cif = SessKredit.checkCifBankGaransi(periodeId, bankGaransi.getNoRekening());
            if(cif.equals("")){
                rowx.add("rekening tdk ditemukan (pindah)");//
            }else{
                rowx.add(""+cif);//
                double countRek = SessKredit.checkCountCifBankGaransi(periodeId, cif);
                String listRek = SessKredit.checkNorekeningBankGaransi(periodeId, cif);
                if(countRek > 1){
                    //insert cif
                    rowx.add(""+listRek);
                    //rowx.add("<button type='button' name='seach' id='search-btn-segment' class='btn btn-danger' onclick=\"javascript:cmdNotFoundx('"+kredit.getNoRekening()+"','"+periodeId+"','"+kredit.getCif()+"')\"><i class='fa fa-check'></i></button>");//
                    rowx.add("<button type='button' name='seach' id='search-btn-segment' class='btn btn-warning' onclick=\"javascript:cmdReplaceCifInsertBankGaransi('"+bankGaransi.getNoRekening()+"','"+periodeId+"','"+bankGaransi.getCif()+"','"+cif+"')\"><i class='fa fa-check'></i></button>");//
                }else{
                    //replace
                    rowx.add(""+listRek);
                    rowx.add("<button type='button' name='seach' id='search-btn-segment' class='btn btn-info' onclick=\"javascript:cmdReplaceCifBankGaransi('"+bankGaransi.getNoRekening()+"','"+periodeId+"','"+bankGaransi.getCif()+"','"+cif+"')\"><i class='fa fa-check'></i></button>");//
                }
            }
            lstData.add(rowx);
        }

        return ctrlist.drawBootstrap();
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
    String norekening = FRMQueryString.requestString(request, "norekening");
    String cif = FRMQueryString.requestString(request, "cif");
    String prevcif = FRMQueryString.requestString(request, "prevcif");
    
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
    lengkapVal.add("Tidak Lengkap");
    
    String whereCabangSelected="";
    if(!cabang.equals("")){
        whereCabangSelected=PstKredit.fieldNames[PstKredit.FLD_KODE_KANTOR_CABANG]+"='"+cabang+"'";
    }
    Vector objListKredit= new Vector();
    Vector objListBankGaransi= new Vector();
    
    if(iCommand==Command.SEARCH){
        objListKredit= SessKredit.listJoinKredit(0, 0, ""+whereCabangSelected, "", periodeId);
        objListBankGaransi= SessKredit.listJoinBankGaransi(0, 0, ""+whereCabangSelected, "", periodeId);
    }
    
    if(iCommand==Command.UPDATE){
        if(!norekening.equals("")){
            long moveDataKreditPindah = SessFixedCifKredit.moveDataKreditPindah(norekening, periodeId,cif);
        }
        iCommand=Command.SEARCH;
        objListKredit= SessKredit.listJoinKredit(0, 0, ""+whereCabangSelected, "", periodeId);
        objListBankGaransi= SessKredit.listJoinBankGaransi(0, 0, ""+whereCabangSelected, "", periodeId);
    }
    
    if(iCommand==Command.UPDATE2){
        if(!norekening.equals("")){
            long moveDataKreditPindah = SessFixedCifKredit.replaceCifNoRekening(norekening, periodeId,cif,prevcif);
        }
        iCommand=Command.SEARCH;
        objListKredit= SessKredit.listJoinKredit(0, 0, ""+whereCabangSelected, "", periodeId);
        objListBankGaransi= SessKredit.listJoinBankGaransi(0, 0, ""+whereCabangSelected, "", periodeId);
    }
    
    if(iCommand==Command.SAVE2){
        if(!norekening.equals("")){
            long moveDataKreditPindah = SessFixedCifKredit.replaceInsertCifNoRekening(norekening, periodeId,cif,prevcif);
        }
        iCommand=Command.SEARCH;
        objListKredit= SessKredit.listJoinKredit(0, 0, ""+whereCabangSelected, "", periodeId);
        objListBankGaransi= SessKredit.listJoinBankGaransi(0, 0, ""+whereCabangSelected, "", periodeId);
    }
    
    if(iCommand==Command.SUBMIT){
       if(!norekening.equals("")){
            long moveDataKreditPindah = SessFixedCifKredit.replaceCifNoRekeningBankGaransi(norekening, periodeId,cif,prevcif);
        }
        iCommand=Command.SEARCH;
        objListKredit= SessKredit.listJoinKredit(0, 0, ""+whereCabangSelected, "", periodeId);
        objListBankGaransi= SessKredit.listJoinBankGaransi(0, 0, ""+whereCabangSelected, "", periodeId);
    }
    
    if(iCommand==Command.CONFIRM){
        if(!norekening.equals("")){
            long moveDataKreditPindah = SessFixedCifKredit.replaceInsertCifNoRekeningBankGaransi(norekening, periodeId,cif,prevcif);
        }
        iCommand=Command.SEARCH;
        objListKredit= SessKredit.listJoinKredit(0, 0, ""+whereCabangSelected, "", periodeId);
        objListBankGaransi= SessKredit.listJoinBankGaransi(0, 0, ""+whereCabangSelected, "", periodeId);
    }
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
                document.driver.action="konflik_cif.jsp";
                document.driver.submit();
                lockScreen('Proses Reporting Sedang Berlangsung, Silahkan menunggu...');
            }
            
            function cmdNotFound(noRekening, periode,cif){
                document.driver.command.value="<%=Command.UPDATE%>";
                document.driver.norekening.value=noRekening;
                document.driver.cif.value=cif;
                document.driver.periodepersegment.value=periode;
                document.driver.prev_command.value="<%=prevCommand%>";
                document.driver.action="konflik_cif.jsp";
                document.driver.submit();
                lockScreen('Proses Sedang Berlangsung, Silahkan menunggu...');
            }
            
            function cmdReplaceCif(noRekening, periode,prevcif, currencif){
                document.driver.command.value="<%=Command.UPDATE2%>";
                document.driver.norekening.value=noRekening;
                document.driver.cif.value=currencif;
                document.driver.prevcif.value=prevcif;
                document.driver.periodepersegment.value=periode;
                document.driver.prev_command.value="<%=prevCommand%>";
                document.driver.action="konflik_cif.jsp";
                document.driver.submit();
                lockScreen('Proses Sedang Berlangsung, Silahkan menunggu...');
            }
            
            function cmdReplaceCifInsert(noRekening, periode,prevcif, currencif){
                document.driver.command.value="<%=Command.SAVE2%>";
                document.driver.norekening.value=noRekening;
                document.driver.cif.value=currencif;
                document.driver.prevcif.value=prevcif;
                document.driver.periodepersegment.value=periode;
                document.driver.prev_command.value="<%=prevCommand%>";
                document.driver.action="konflik_cif.jsp";
                document.driver.submit();
                lockScreen('Proses Sedang Berlangsung, Silahkan menunggu...');
            }
            
            
             function cmdReplaceCifBankGaransi(noRekening, periode,prevcif, currencif){
                document.driver.command.value="<%=Command.SUBMIT%>";
                document.driver.norekening.value=noRekening;
                document.driver.cif.value=currencif;
                document.driver.prevcif.value=prevcif;
                document.driver.periodepersegment.value=periode;
                document.driver.prev_command.value="<%=prevCommand%>";
                document.driver.action="konflik_cif.jsp";
                document.driver.submit();
                lockScreen('Proses Sedang Berlangsung, Silahkan menunggu...');
            }
            
            function cmdReplaceCifInsertBankGaransi(noRekening, periode,prevcif, currencif){
                document.driver.command.value="<%=Command.CONFIRM%>";
                document.driver.norekening.value=noRekening;
                document.driver.cif.value=currencif;
                document.driver.prevcif.value=prevcif;
                document.driver.periodepersegment.value=periode;
                document.driver.prev_command.value="<%=prevCommand%>";
                document.driver.action="konflik_cif.jsp";
                document.driver.submit();
                lockScreen('Proses Sedang Berlangsung, Silahkan menunggu...');
            }
            
            
            function cmdSearchPrint(){
                document.driver.command.value="<%=Command.PRINT%>";
                document.driver.prev_command.value="<%=prevCommand%>";
                document.driver.action="konflik_cif.jsp";
                document.driver.submit();
                lockScreen('Proses Reporting Sedang Berlangsung, Silahkan menunggu...');
            }
            
            function cmdSearchExcel(){
                document.driver.command.value="<%=Command.SEARCH%>";
                document.driver.prev_command.value="<%=prevCommand%>";
                document.driver.action="konflik_cif_excel.jsp?cabangpersegment=<%=cabang%>&periodepersegment=<%=periodeId%>&segment=<%=getSegment%>&kelengkapan=<%=kelengkapan%>";
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
                        <small>Summary Data Rekening Pindah CIF</small>
                    </h1>
                </section>
                <!-- Main content -->
                <%
                    if(privView){
                %>
               <section class="content">
                    <form name="driver"  method ="post" action="" role="form">
                        <input type="hidden" name="command" value="<%=iCommand%>">
                        <input type="hidden" name="norekening" value="<%=norekening%>">
                        <input type="hidden" name="cif" value="<%=cif%>">
                        <input type="hidden" name="prevcif" value="<%=prevcif%>">
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
                                                <h3 class="box-title">Laporan Rekening Pindah CIF</h3>
                                            </div>
                                        </div>
                                        <%if(iCommand==Command.SEARCH){%>
                                            <div class="row">
                                                <div class="col-md-12" id="reportsummarypersegment">
                                                     <div class='row'>
                                                        <div class='col-md-12'>
                                                                <b>KREDIT</b>
                                                        </div>
                                                    </div>
                                                    <div class='row'>
                                                        <div class='col-md-12'>
                                                            <div class='form-group'>
                                                                <%=drawReportPerSegment(out,iCommand,objListKredit,0,periodeId)%>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class='row'>
                                                        <div class='col-md-12'>
                                                                <b>BANK GARANSI</b>
                                                        </div>
                                                    </div>        
                                                    <div class='row'>
                                                        <div class='col-md-12'>
                                                            <div class='form-group'>
                                                                <%=drawReportPerSegmentBankGaransi(out,iCommand,objListBankGaransi,0,periodeId)%>
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
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <button type='button' name='seach' id='search-btn-segment' class='btn btn-warning'><i class='fa fa-check'></i></button> : Insert Data + Update Cif di fasilitas
                                                <button type='button' name='seach' id='search-btn-segment' class='btn btn-info'><i class='fa fa-check'></i></button> : Update CIF di fasilitas
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
