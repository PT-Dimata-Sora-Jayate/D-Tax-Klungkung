<%-- 
    Document   : payment_phr
    Created on : May 19, 2015, 7:00:57 PM
    Author     : dimata005
--%>

<%@page import="com.dimata.dtaxintegrationsisda.entity.piutang.RefSettingan"%>
<%@page import="java.text.Format"%>
<%@page import="com.dimata.dtaxintegrationsisda.entity.piutang.TaKartuPajakHotel"%>
<%@page import="com.dimata.dtaxintegrationsisda.entity.piutang.TaKartuPajakPungut"%>
<%@page import="com.dimata.dtaxintegrationsisda.entity.piutang.PstTaKartuPajakPungut"%>
<%@page import="com.dimata.dtaxintegrationsisda.entity.piutang.PstTaKartuPajakHotel"%>
<%@page import="com.dimata.dtaxintegrationsisda.session.SessSimpatdaSimda"%>
<%@page import="com.dimata.dtaxintegrationsisda.entity.piutang.ViewSptpdSimda"%>
<%@page import="com.dimata.dtaxintegrationsisda.entity.piutang.PstViewSptpdSimda"%>
<%@page import="com.dimata.webclient.AppSetting"%>
<%@page import="com.dimata.webclient.EchoTagihanDeleteById"%>
<%@page import="com.dimata.dtaxintegration.entity.tagihan.TagihanDelete"%>
<%@page import="com.dimata.webclient.EchoTagihanDeleteByRecordId"%>
<%@page import="com.dimata.dtaxintegration.entity.tagihan.Tagihan"%>
<%@page import="com.dimata.dtaxintegration.entity.inquery.InqueryProses"%>
<%@page import="com.dimata.webclient.Inquery"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.dimata.dtaxintegration.session.DTaxIntegrationMonitor"%>
<%@page import="com.dimata.dtaxintegration.entity.payment.PaymentPhr"%>
<%@page import="com.dimata.dtaxintegration.entity.payment.PstPaymentPhr"%>
<%@page import="com.dimata.dtaxintegration.entity.inquery.Payment"%>
<%@page import="com.dimata.webclient.EchoLaporanPaymentDetail"%>
<%@page import="com.dimata.dtaxintegration.entity.laporan.LaporanPayment"%>
<%-- 
    Document   : report_bbm_summary
    Created on : May 1, 2015, 10:06:28 AM
    Author     : dimata005
--%>
<%@page import="com.dimata.util.Formater"%>
<%@page import="java.util.Date"%>
<%@page import="com.dimata.dtaxintegration.entity.inquery.Simpatda"%>
<%@page import="com.dimata.dtaxintegration.session.SessSimpatda"%>
<%@page import="com.dimata.dtaxintegration.session.SrcReport"%>
<%@page import="com.dimata.qdep.form.FRMHandler"%>
<%@page import="com.dimata.gui.jsp.ControlDate"%>
<%@page import="com.dimata.gui.jsp.ControlCombo"%>
<%@page import="com.dimata.util.Command"%>
<%@page import="com.dimata.gui.jsp.ControlLine"%>
<%@page import="com.dimata.qdep.form.FRMMessage"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<%@page import="com.dimata.gui.jsp.ControlList"%>
<%@page import="java.util.Vector"%>
<!DOCTYPE html>
<%@ include file = "../../main/javainit.jsp" %>
<% //int  appObjCode = AppObjInfo.composeObjCode(AppObjInfo.G1_LOGIN, AppObjInfo.G2_LOGIN, AppObjInfo.OBJ_LOGIN_LOGIN); %>
<%@ include file = "../../main/checkuser.jsp" %>


<%!//proses yang akan ditampilkan

    public String drawListDataPaymentPhrSimda(int iCommand, Vector objectClass, long gradeId) {

        ControlList ctrlist = new ControlList();

        ctrlist.setAreaWidth("100%");
        ctrlist.setListStyle("listgen");
        ctrlist.setTitleStyle("tableheader");
        ctrlist.setCellStyle("cellStyle");
        ctrlist.setHeaderStyle("tableheader");
        
        ctrlist.addHeader("No", "200px");//1
        ctrlist.addHeader("ACT", "200px");//11
        ctrlist.addHeader("TAHUN", "200px");//2
        ctrlist.addHeader("NO SPT", "200px");//2
        ctrlist.addHeader("NO WP", "200px");//3
        ctrlist.addHeader("MASA", "200px");//3
        ctrlist.addHeader("DASAR", "200px");//5
        ctrlist.addHeader("TARIF", "200px");//6
        ctrlist.addHeader("PAJAK ", "200px");//7
       
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
        Format formatter = new SimpleDateFormat("MM"); 
        for (int i = 0; i < objectClass.size(); i++) {
            
            //Simpatda simpatda = (Simpatda) objectClass.get(i);
            Vector vDataPajak = (Vector) objectClass.get(i);
            
            TaKartuPajakPungut entTaKartuPajakPungut = (TaKartuPajakPungut) vDataPajak.get(0);
            TaKartuPajakHotel entKartuPajakHotel = (TaKartuPajakHotel) vDataPajak.get(1);
            
            count=count+1;
            rowx = new Vector(1, 1);
            
            rowx.add(""+count);//1
            rowx.add("<center><input type=\"checkbox\" name=\"delete_status_"+i+"\" value=\"'"+entTaKartuPajakPungut.getNoSpt()+"'\" class=\"form-control\" size=\"10\"></center>");
            
            rowx.add(""+entTaKartuPajakPungut.getTahun());//2
            rowx.add(""+entTaKartuPajakPungut.getNoSpt());//2
            rowx.add(""+entTaKartuPajakPungut.getNoPokokWp());//3
            rowx.add(""+formatter.format(entTaKartuPajakPungut.getMasa1()));//3
            rowx.add(""+FRMHandler.userFormatStringDecimal(entKartuPajakHotel.getDasarPengenaan()));//3
            rowx.add(""+FRMHandler.userFormatStringDecimal(entKartuPajakHotel.getTarifPajak()));//3
            rowx.add(""+FRMHandler.userFormatStringDecimal(entKartuPajakHotel.getPajakTerhutang()));//3
            
            
            total=total+entKartuPajakHotel.getPajakTerhutang();
            
            lstData.add(rowx);
        }
        
        rowx = new Vector(1, 1);
        
        rowx.add("");//1
        rowx.add("");//1
        rowx.add("");//2
        rowx.add("");//3
        rowx.add("");//4
        rowx.add("");//5
        rowx.add("<b>Total<b>");//9
        rowx.add(""+FRMHandler.userFormatStringDecimal(total));//10
        lstData.add(rowx);

        return ctrlist.drawBootstrapStrip();
    }

    public String drawListDataPaymentPhrDispenda(int iCommand, Vector objectClass, long gradeId) {

        ControlList ctrlist = new ControlList();

        ctrlist.setAreaWidth("100%");
        ctrlist.setListStyle("listgen");
        ctrlist.setTitleStyle("tableheader");
        ctrlist.setCellStyle("cellStyle");
        ctrlist.setHeaderStyle("tableheader");
        
        ctrlist.addHeader("No", "200px");//1
        ctrlist.addHeader("ACT", "200px");//6 
        ctrlist.addHeader("SPTPD", "200px");//2
        ctrlist.addHeader("SSPD", "200px");//3
        ctrlist.addHeader("NPWPD", "200px");//3
        ctrlist.addHeader("MASA", "200px");//4
        ctrlist.addHeader("TAHUN", "200px");//5
        ctrlist.addHeader("KODE", "200px");//5
        ctrlist.addHeader("RKN", "200px");//5
        ctrlist.addHeader("SUBRKN", "200px");//6
        ctrlist.addHeader("TARIF", "200px");//6 
        ctrlist.addHeader("OMZET", "200px");//6 
        ctrlist.addHeader("PAJAK", "200px");//6 
        ctrlist.addHeader("S&T", "200px");//6 
        ctrlist.addHeader("DENDA", "200px");//6 
        ctrlist.addHeader("PENGURANGAN", "200px");//6 
        ctrlist.addHeader("HARUS BAYAR", "200px");//6 
        //ctrlist.addHeader("TGL", "200px");//6 
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
            
            //Simpatda simpatda = (Simpatda) objectClass.get(i);
            ViewSptpdSimda viewSptpdSimda = (ViewSptpdSimda) objectClass.get(i);
            count=count+1;
            rowx = new Vector(1, 1);
            
            rowx.add(""+count);//1
            rowx.add("<center><input type=\"checkbox\" name=\"status_"+i+"\" value=\"'"+viewSptpdSimda.getNoSptpd()+"';'"+viewSptpdSimda.getNoSspd()+"';'"+viewSptpdSimda.getNpwdp()+"';'"+viewSptpdSimda.getMasaPajak()+"';'"+viewSptpdSimda.getTahunPajak()+"';'"+viewSptpdSimda.getKodePajak()+"';'"+viewSptpdSimda.getNoRekening()+"';'"+viewSptpdSimda.getNoSubrekening()+"';'"+viewSptpdSimda.getTarif()+"';'"+viewSptpdSimda.getOmzet()+"';'"+viewSptpdSimda.getPajak()+"';'"+viewSptpdSimda.getServiceTax()+"';'"+viewSptpdSimda.getDenda()+"';'"+viewSptpdSimda.getPengurangan()+"';'"+viewSptpdSimda.getHarusDibayar()+";'1';'"+viewSptpdSimda.getTglRekam()+"'\" class=\"form-control\" size=\"10\"></center>");
            rowx.add(""+viewSptpdSimda.getNoSptpd());//2
            rowx.add(""+viewSptpdSimda.getNoSspd());//3
            rowx.add(""+viewSptpdSimda.getNpwdp());//3
            rowx.add(""+viewSptpdSimda.getMasaPajak());//4
            rowx.add(""+viewSptpdSimda.getTahunPajak());//4
            rowx.add(""+viewSptpdSimda.getKodePajak());//5
            rowx.add(""+viewSptpdSimda.getNoRekening());//5
            rowx.add(""+viewSptpdSimda.getNoSubrekening());//5
            rowx.add(""+viewSptpdSimda.getTarif());//6
            
            rowx.add(""+FRMHandler.userFormatStringDecimal(viewSptpdSimda.getOmzet()));//6
            rowx.add(""+FRMHandler.userFormatStringDecimal(viewSptpdSimda.getPajak()));//6
            rowx.add(""+FRMHandler.userFormatStringDecimal(viewSptpdSimda.getServiceTax()));//6
            rowx.add(""+FRMHandler.userFormatStringDecimal(viewSptpdSimda.getDenda()));//6
            rowx.add(""+FRMHandler.userFormatStringDecimal(viewSptpdSimda.getPengurangan()));//6
            rowx.add(""+FRMHandler.userFormatStringDecimal(viewSptpdSimda.getHarusDibayar()));//6
            total = total+viewSptpdSimda.getHarusDibayar();
            lstData.add(rowx);
        }
        
            rowx = new Vector(1, 1);
        
            rowx.add("");
            rowx.add("");
            rowx.add("");
            rowx.add("");
            rowx.add("");
            rowx.add("");
            rowx.add("");
            rowx.add("");
            rowx.add("");
            rowx.add("");
            rowx.add("");
            rowx.add("");
            rowx.add("");
            rowx.add("");
            rowx.add("");//6
            rowx.add("<b>Total<b>");//4
            rowx.add(""+FRMHandler.userFormatStringDecimal(total));//5
        
        lstData.add(rowx);

        return ctrlist.drawBootstrapStrip();
    }
%>

<% 
    //proses yang tidak akan ditampilkan hanya diserver side
    int iCommand = FRMQueryString.requestCommand(request);
    int start = FRMQueryString.requestInt(request, "start");
    int prevCommand = FRMQueryString.requestInt(request, "prev_command");
    long oidDriver = FRMQueryString.requestLong(request, "hidden_driver_id");
    String gradeDelete = FRMQueryString.requestString(request, "gradeDelete");
    
    String startDate = FRMQueryString.requestString(request, "tanggalStart");
    String oDate = "";
    String sNoId = FRMQueryString.requestString(request, "sNoId");
    String idbank = FRMQueryString.requestString(request, "idbank");
    
    String idtagihan = FRMQueryString.requestString(request, "idtagihan");
    int tahun = FRMQueryString.requestInt(request, "tahun");
    String bulan = FRMQueryString.requestString(request, "bulan");
    
    /*variable declaration*/
    String whereClause = "";
    Vector listReportDetail = new Vector(1, 1);
    
    //proses posting
    SessSimpatdaSimda sessSimpatdaSimda = new SessSimpatdaSimda();
    if(iCommand==Command.POSTING){
        if(!idtagihan.equals("")){
            sessSimpatdaSimda.prosesInsertSimda(idtagihan);
        }
        idtagihan="";
    }
    
    /*cek data di sql server*/
    /*Inquery inquery = new Inquery();
    InqueryProses inqueryProses = new InqueryProses();
    inqueryProses.setsUser(AppSetting.USERNAME_PHR);
    inqueryProses.setsPassword(AppSetting.PWD_PHR);
    inqueryProses.setsInstansi(AppSetting.INSTANSI_PHR);
    inqueryProses.setsNoId(sNoId);*/
    String convertNPWPD="";
    String nameComp="";
    if(sNoId!=""){
        convertNPWPD=sNoId.substring(2,6);
        String whereClauseSqlServer = " tp.No_Pokok_WP like '%000"+convertNPWPD+"%' and tp.Tahun='"+tahun+"'";
        listReportDetail = PstTaKartuPajakPungut.listJoinHotel(0,0,whereClauseSqlServer, ""); //inquery.InqueryPHR(inqueryProses);
        nameComp = sessSimpatdaSimda.getCompNoWpSimda(sNoId);
    }
   
    /*data di view_sptpd_simda*/
    Vector listTagihanPhr = new Vector();
    whereClause = "NPWPD='"+sNoId+"' AND  SUBSTR(NPWPD,1,1) = '1' AND TAHUN_PAJAK='"+tahun+"' ";//PstPaymentPhr.fieldNames[PstPaymentPhr.FLD_TANGGAL]+" = TO_DATE ('"+oDate+"','YYYY-MM-DD HH24:MI:SS')";
    listTagihanPhr = PstViewSptpdSimda.list(0, 0, whereClause, "");
    
%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Pajak dan Retribusi Daerah Terintegrasi Pembayaran Online</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <!-- bootstrap 3.0.2 -->
        <link rel="stylesheet" href="../../styles/datepicker/css/jquery.ui.all.css">
        <link href="../../styles/bootstrap3.1/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- font Awesome -->
        <link href="../../styles/bootstrap3.1/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons -->
        <link href="../../styles/bootstrap3.1/css/ionicons.min.css" rel="stylesheet" type="text/css" />
        <!-- DATA TABLES -->
        <link href="../../styles/bootstrap3.1/css/datatables/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />
        <!-- Theme style -->
        <link href="../../styles/bootstrap3.1/css/AdminLTE.css" rel="stylesheet" type="text/css" />

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="../../styles/bootstrap3.1/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="../../styles/bootstrap3.1/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
        <script language="JavaScript">
            
            function cmdPrint(){
                document.driver.command.value="<%=Command.POSTING%>";
                document.driver.action="payment_phr.jsp";
                document.driver.submit();
            }
            
            function cmdSearch(){
                document.driver.command.value="<%=Command.SEARCH%>";
                document.driver.hidden_driver_id.value="0";
                document.driver.prev_command.value="<%=prevCommand%>";
                document.driver.action="manual_phr_hotel.jsp";
                document.driver.submit();
            }
            
            function cmdPostingDelete(idBank){
                document.driver.idbank.value=idBank;
                document.driver.command.value="<%=Command.DELETE%>";
                document.driver.action="manual_phr_hotel.jsp";
                document.driver.submit();
            }
            
            function cmdPosting(id, tahun, bulan){
                document.driver.idtagihan.value=id;
                document.driver.tahun.value=tahun;
                document.driver.bulan.value=bulan;
                document.driver.command.value="<%=Command.POSTING%>";
                document.driver.action="manual_phr_hotel.jsp";
                document.driver.submit();
            }
            
            function cmdAction(){
               if (confirm('Yakin Proses ? ')) {  
                    var val = '';
                    <%
                    for (int a=0; a<listTagihanPhr.size();a++){
                    %>
                        if(eval("driver.status_"+<%=a%>+".checked")==true){
                            val = val + eval("driver.status_"+<%=a%>+".value") + ',';
                        }
                    <%
                    }
                    %>
                    document.driver.idtagihan.value=val;
                    document.driver.command.value="<%=Command.POSTING%>";
                    document.driver.action="manual_phr_hotel.jsp";
                    document.driver.submit(); 
                    lockScreen('Proses Posting tagihan PHR ke Bank BPD, Silahkan menunggu...');
                }
            }
            
            function cmdActionDelete(){
                if (confirm('Yakin Proses ? ')) {  
                    var val = '';
                    <%
                    for (int a=0; a<listReportDetail.size();a++){
                    %>
                        if(eval("driver.delete_status_"+<%=a%>+".checked")==true){
                            val = val + eval("driver.delete_status_"+<%=a%>+".value") + ',';
                        }
                    <%
                    }
                    %>
                    document.driver.idtagihan.value=val;
                    document.driver.command.value="<%=Command.DELETE%>";
                    document.driver.action="manual_phr_hotel.jsp";
                    document.driver.submit(); 
                    lockScreen('Proses Delete Tagihan PHR di BANK BPD, Silahkan menunggu...');
                }
            }
            
            
             function cmdActionDeleteAll(){
                if (confirm('Yakin Proses ? ')) {  
                    document.driver.command.value="<%=Command.DELETE2%>";
                    document.driver.action="manual_phr_hotel.jsp";
                    document.driver.submit(); 
                    lockScreen('Proses Delete Tagihan PHR di BANK BPD, Silahkan menunggu...');
                }
            }
            
            
             function lockScreen(str)
                {
                    var lock = document.getElementById('theLockPane');
                    if (lock)
                        lock.className = 'LockOn';

                    lock.innerHTML = str;
                }
        </script>
        <style type="text/css">

            .LockOff {
                display: none;
                visibility: hidden;
            }

            .LockOn {
                display: block;
                visibility: visible;
                position: absolute;
                z-index: 999;
                top: 0px;
                left: 0px;
                width: 105%;
                height: 105%;
                background-color: #ccc;
                text-align: center;
                padding-top: 20%;
                filter: alpha(opacity=75);
                opacity: 0.75;
                font-size: 250%;
            }
        </style>
    </head>
    <body class="skin-blue">
         <div id="theLockPane" class="LockOff"></div>
        <!-- header logo: style can be found in header.less -->
        <%@ include file = "../../header_mobile.jsp" %> 
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <!-- Left side column. contains the logo and sidebar -->
            <%@ include file = "../menu_left_mobile_simda.jsp" %> 

            <!-- Right side column. Contains the navbar and content of the page -->
            <aside class="right-side">                
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                       Sistem Integrasi Simpatda - Simda
                    </h1>
                </section>
                <!-- Main content -->
                <section class="content">
                    <form name="driver"  method ="post" action="" role="form">
                        <input type="hidden" name="command" value="<%=iCommand%>">
                        <input type="hidden" name="start" value="<%=start%>">
                        <input type="hidden" name="prev_command" value="<%=prevCommand%>">
                        <input type="hidden" name="hidden_driver_id" value="<%=oidDriver%>">
                         <input type="hidden" name="idbank" value="<%=idbank%>">
                         <input type="hidden" name="idtagihan" value="<%=idtagihan%>">
                         <input type="hidden" name="bulan" value="<%=bulan%>">
                         <input type="hidden" name="menu" value="1">
                        <input type="hidden" name="tree" value="1">
                        <div class="row">
                            <div class="col-xs-12">
                               <div class="box">
                                   <div class="box-header">
                                       &nbsp;
                                   </div>    
                                   <div class="box-header">
                                       <div class="col-xs-12"><label>DATA SIMDA</label></div>  
                                         <div class="col-xs-2"><label>TAHUN </label>  
                                            <div class="input-group">   
                                                 <%
                                                    Vector tipePajak = new Vector(1,1);
                                                    tipePajak = sessSimpatdaSimda.getTahunPerhitungan();
                                                    Vector valueTahun =  new Vector();
                                                    Vector keyTahun = new  Vector();
                                                    if(tipePajak.size() != 0){
                                                         for(int i = 0; i<tipePajak.size(); i++){
                                                             RefSettingan refSettingan = (RefSettingan) tipePajak.get(i);
                                                             valueTahun.add(""+refSettingan.getTahun());
                                                             keyTahun.add(""+refSettingan.getTahun());
                                                         }
                                                     }
                                                %>
                                                <%=ControlCombo.drawBootsratap("tahun", null,""+tahun, valueTahun, keyTahun, "required='required'", "form-control") %>
                                            </div>
                                       </div>
                                       <div class="col-xs-4"><label> No NPWPD </label>  
                                            <div class="input-group">   
                                                <input name="sNoId" type="text" class="form-control" placeholder="Inputkan No NPWPD" value="<%=sNoId%>" size="50">
                                                <span class="input-group-btn">
                                                    <button type='button' name='seach' id='search-btn' class="btn btn-success pull-right" onclick="javascript:cmdSearch()"><i class="fa fa-search"></i></button>
                                                </span>
                                            </div>
                                       </div>
                                       <div class="col-xs-4"><label>.</label>  
                                            <div class="input-group">   <%=nameComp%>   
                                            </div>
                                       </div>   
                                            
                                    </div>
                                    <%=drawListDataPaymentPhrSimda(iCommand, listReportDetail, oidDriver)%>
                                </div><!-- /.box -->
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                               <div class="box">
                                   <div class="box-header">
                                       &nbsp;
                                   </div>
                                   <div class="box-header">
                                        <div class="col-xs-12"><label>DATA SIMPATDA</label></div>  
                                        <% if(userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_PROSES_INTEGRASI,AppObjInfo.G2_PHR,AppObjInfo.OBJ_UPLOAD_MANUAL_PHR, AppObjInfo.COMMAND_UPLOAD)){ %>
                                            <div class="col-xs-12">
                                                 <button  onclick="javascript:cmdAction()" type="button" class="btn btn-primary pull-right">UPLOAD</button>
                                            </div>
                                        <%}%>
                                   </div>  
                                   <%=drawListDataPaymentPhrDispenda(iCommand, listTagihanPhr, oidDriver)%>
                                </div><!-- /.box -->
                            </div>
                        </div>      
                    </form>
                </section><!-- /.content -->
            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->


        <!-- jQuery 2.0.2 -->
        <script src="../../styles/bootstrap3.1/js/jquery.min.js"></script>
        <!-- Bootstrap -->
        <script src="../../styles/bootstrap3.1/js/bootstrap.min.js" type="text/javascript"></script>
        <!-- DATA TABES SCRIPT -->
        <script src="../../styles/bootstrap3.1/js/plugins/datatables/jquery.dataTables.js" type="text/javascript"></script>
        <script src="../../styles/bootstrap3.1/js/plugins/datatables/dataTables.bootstrap.js" type="text/javascript"></script>
        <!-- AdminLTE App -->
        <script src="../../styles/bootstrap3.1/js/AdminLTE/app.js" type="text/javascript"></script>

        <!-- page script -->
        <script src="../../styles/datepicker/js/jquery.ui.core.js"></script>
        <script src="../../styles/datepicker/js/jquery.ui.widget.js"></script>
        <script src="../../styles/datepicker/js/jquery.ui.datepicker.js"></script>

        <script type="text/javascript">
            $(function() {
                //$("#example1").dataTable();
                $('#example2').dataTable({
                    "bPaginate": true,
                    "bLengthChange": false,
                    "bFilter": false,
                    "bSort": true,
                    "bInfo": true,
                    "bAutoWidth": false
                });
            });
        </script>
        <script>
            $(function() {
                    $( "#datepicker" ).datepicker({ dateFormat: "yy-mm-dd" });
            });
	</script>
        <script>
            $(function() {
                    $( "#datepickerdua" ).datepicker({ dateFormat: "yy-mm-dd" });
            });
	</script>
         <script>
            $(function() {
                    $( "#datepickertiga" ).datepicker({ dateFormat: "yy-mm-dd" });
            });
	</script>
    </body>
</html>
