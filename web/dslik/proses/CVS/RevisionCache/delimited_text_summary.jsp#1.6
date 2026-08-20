<%-- 
    Document   : delimited_text_summary
    Created on : Dec 21, 2016, 4:42:56 PM
    Author     : dimata005
--%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.dimata.dslik.session.proses.ManagerDilimitedTextSummary"%>
<%-- 
    Document   : delimited_text
    Created on : Sep 27, 2016, 11:53:48 PM
    Author     : dimata005
--%>
<%@page import="com.dimata.dslik.entity.masterdata.OutletConnection"%>
<%@page import="com.dimata.dslik.session.proses.ProsesTransferDataBank"%>
<%@page import="java.io.File"%>
<%@page import="java.io.IOException"%>
<%@page import="com.dimata.util.Formater"%>
<%@page import="com.dimata.dslik.session.proses.FileSent"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<%@page import="com.dimata.dslik.form.masterdata.FrmConnection"%>
<%@page import="com.dimata.gui.jsp.ControlCombo"%>
<%@page import="java.util.Vector"%>
<%@page import="com.dimata.util.Command"%>
<!DOCTYPE html>
<%@include file="../../main/javainit_slik.jsp" %>
<%@include file="../../main/checkuser_slik.jsp" %>
<%
    boolean privStart = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_PROSES_DELIMITED_TEXT, AppObjInfo.G2_INITIAL, AppObjInfo.OBJ_INITIAL, AppObjInfo.COMMAND_START);
    boolean privStop = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_PROSES_DELIMITED_TEXT, AppObjInfo.G2_INITIAL, AppObjInfo.OBJ_INITIAL, AppObjInfo.COMMAND_STOP);
    boolean privView = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_PROSES_DELIMITED_TEXT, AppObjInfo.G2_INITIAL, AppObjInfo.OBJ_INITIAL, AppObjInfo.COMMAND_VIEW);

    int iCommand = FRMQueryString.requestCommand(request);
    
    String startDate = FRMQueryString.requestString(request, "tanggalStart");
    String oDate = "";//FRMQueryString.requestString(request, "tanggalEnd");
    String startEnd = FRMQueryString.requestString(request, "tanggalEnd");
    String oEnd = "";//FRMQueryString.requestString(request, "tanggalEnd");
    FileSent fileSent = new FileSent();
    Date newDay=new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    if(startDate.equals("") || startEnd.equals("")){
        startDate=Formater.formatDate(newDay,"yyyy-MM-dd");
        startEnd=Formater.formatDate(newDay,"yyyy-MM-dd");
    }else{
        String dateStringTransaksi = startDate;
        String dateEndStringTransaksi = startEnd;
        try {
                Date transaksiDate = formatter.parse(dateStringTransaksi);
                Date transaksiEndDate = formatter.parse(dateEndStringTransaksi);
                transaksiEndDate.setMonth(transaksiEndDate.getMonth()+1);
                fileSent.setStartDateSummary(transaksiDate);
                fileSent.setEndDateSummary(transaksiEndDate);
        } catch (Exception e) {
                e.printStackTrace();
        }
    }
    
    
    
    ManagerDilimitedTextSummary managerDilimitedTextSummary = new ManagerDilimitedTextSummary();
    ProsesTransferDataBank prosesTransferDataBank = new ProsesTransferDataBank();
    OutletConnection outletConnection = prosesTransferDataBank.getConfigurasiConnection();
    String locFile="";
    if(outletConnection.equals("")){
        locFile=""+approot;
    }else{
        locFile=outletConnection.getPathDelimitedTextSummary();
    }
    fileSent.setFileName("");
    fileSent.setTahun(""+Formater.formatDate(periode.getTglAwal(), "yyyy"));
    fileSent.setBulan(""+Formater.formatDate(periode.getTglAwal(), "MM"));
    fileSent.setOperasiData("U");
    fileSent.setPeriodeId(periode.getOID());
    fileSent.setEndPeriodeDate(periode.getTglAkhir());
    boolean checkFolderIsExist = fileSent.checkDirectori(locFile, Formater.formatDate(periode.getTglAwal(), "MM"), Formater.formatDate(periode.getTglAwal(), "yyyy"));
    String locationDocumentzip="";
    if(checkFolderIsExist){
        locationDocumentzip=approot+"/dslik/dilimited_text_slik_summary/"+fileSent.getBulan()+"_"+fileSent.getTahun()+".zip";
    }
    switch (iCommand) {
        case Command.START:
            //create file
            fileSent.create(locFile, fileSent.getBulan(), fileSent.getTahun());
            String locationDocument=locFile+""+fileSent.getBulan()+"_"+fileSent.getTahun();
            fileSent.setLocation(locationDocument);
            fileSent.setTypeDilimitedSummary(0);
            managerDilimitedTextSummary.startDilimitedText(fileSent,outletConnection);
            break;
        case Command.REPOSTING:
            //create file
            fileSent.create(locFile, fileSent.getBulan(), fileSent.getTahun());
            String locationDocumentx=locFile+""+fileSent.getBulan()+"_"+fileSent.getTahun();
            fileSent.setLocation(locationDocumentx);
            fileSent.setTypeDilimitedSummary(1);
            managerDilimitedTextSummary.startDilimitedText(fileSent,outletConnection);
            break;    
        case Command.STOP:

            managerDilimitedTextSummary.stopMonitor();

            break;

    }
   boolean running = managerDilimitedTextSummary.getStatus();
%>
<html>
  <head>
    <meta charset="UTF-8">
    <title>SLIK | Delimited Text Initial</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <%@include file="../../template-component/css-component.jsp" %>
    <script language="JavaScript">
            
        function cmdStart(){
            document.driver.command.value="<%=Command.START%>";
            document.driver.hidden_driver_id.value="0";
            document.driver.action="delimited_text_summary.jsp";
            document.driver.submit();
        }

        function cmdStop(){

            document.driver.command.value="<%=Command.STOP%>";
            document.driver.start.value="0";
            document.driver.action="delimited_text_summary.jsp";
            document.driver.submit();

        }
        
        function cmdLoad(){
            document.driver.command.value="<%=Command.REPOSTING%>";
            document.driver.start.value="0";
            document.driver.action="delimited_text_summary.jsp";
            document.driver.submit();

        }

        function lockScreen(str)
        {
            var lock = document.getElementById('theLockPane');
            if (lock)
                lock.className = 'LockOn';

            lock.innerHTML = str;
        }
    </script>
  </head>
  <body class="<%= skin %>">
      <input type="hidden" name="command" id="command" value="<%= Command.NONE %>">
      <input type="hidden" name="approot" id="approot" value="<%= approot %>">
      <input type="hidden" name="privstart" id="privstart" value="<%= privStart %>">
      <input type="hidden" name="privstop" id="privstop" value="<%= privStop %>">
    <div class="wrapper">
      
	<%@include file="../../template-component/header-component.jsp" %>
        <%@include file="../../template-component/sidebar-component.jsp" %>

      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            Delimited Text
            <small>Data Initial</small>
          </h1>
        </section>
        <!-- Main content -->
        <%
            if(privView){
        %>
        <section class="content">
          <!-- Small boxes (Stat box) -->
          <form name="driver"  method ="post" action="" role="form">
            <input type="hidden" name="command" value="<%=iCommand%>">
                <div class="row">
                  <div class="col-md-12">
                      <div class='box box-primary'>
                          <div class='box-header'>
                              Delimited Summary Fasilitas
                          </div>
                          <div class="box-body">
                              <div id="countryElement">
                                  <%if(checkFolderIsExist){%>
                                        Data Delimited Text Sudah berhasil Download file di <a href="<%=locationDocumentzip%>" target="_blank">Sini</a>
                                  <%}%>
                              </div>
                                <div class="col-xs-2"><label>Dari Tanggal</label>
                                    <input name="tanggalStart" data-provide='datepicker' data-date-format='yyyy-mm-dd' type="text" class="form-control" placeholder="yyyy-MM-dd" value="<%=startDate%>">
                                </div>
                                <div class="col-xs-2"><label>Sampai Tanggal</label>
                                    <div class="input-group">  
                                    <input name="tanggalEnd" data-provide='datepicker' data-date-format='yyyy-mm-dd' type="text" class="form-control" placeholder="yyyy-MM-dd" value="<%=startEnd%>">
                                   
                                    </div>
                                </div>
                              
                          </div>
                           <div id="dynamicOrderButton">
                           </div>       
                           <div id="dynamicOrder">
                           </div>        
                      </div>
                  </div><!-- ./col -->
                </div><!-- /.row -->
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
      <script language="JavaScript">
        function ajaxProsesTransfer(url,data,type,appendTo,another,optional){
            $.ajax({
                url : ""+url+"",
                data: ""+data+"",
                type : ""+type+"",
                async : false,
                cache: false,
                success : function(data) {
                    if (appendTo!=""){
                        $(''+appendTo+'').html(data);
                    }
                }
            }).done(function(){
                
            });
        }
        
        function loadData(){
            var privStart = document.getElementById("privstart").value;
            var privStop = document.getElementById("privstop").value;
            var data="datatype=6&privstart="+privStart+"&privstop="+privStop;
            ajaxProsesTransfer("<%=approot%>/AjaxProsesTransfer",data,"GET","#dynamicOrder","loadData","");
        }
        
        function loadButton(){
            var privStart = document.getElementById("privstart").value;
            var privStop = document.getElementById("privstop").value;
            var data="datatype=6&dataview=1&privstart="+privStart+"&privstop="+privStop;
            ajaxProsesTransfer("<%=approot%>/AjaxProsesTransfer",data,"GET","#dynamicOrderButton","loadButton","");
        }
        
        //load data pertama kalinya
        loadData();
        loadButton();
        
        //untuk membuat load data
        <%if(running){%>
            setInterval(function() {
                loadData();
                loadButton();
            },1000);
        <%}%>
      </script>
    </div><!-- ./wrapper -->
  </body>
</html>
