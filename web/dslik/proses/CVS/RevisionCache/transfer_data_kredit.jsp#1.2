<%-- 
    Document   : transfer_data_kredit
    Created on : Nov 10, 2016, 10:09:54 AM
    Author     : dimata005
--%>
<%@page import="com.dimata.dslik.session.proses.ManagerTransferDataKredit"%>
<%-- 
    Document   : transfer_data_debitur
    Created on : Nov 10, 2016, 10:09:46 AM
    Author     : dimata005
--%>

<%-- 
    Document   : transfer_data
    Created on : Sep 21, 2016, 6:51:24 PM
    Author     : dimata005
--%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Date"%>
<%@page import="com.dimata.dslik.session.proses.ProsesTransferDataBank"%>
<%@page import="com.dimata.util.Formater"%>
<%@page import="com.dimata.dslik.session.proses.ManagerTransferData"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<%@page import="com.dimata.dslik.form.masterdata.FrmConnection"%>
<%@page import="com.dimata.gui.jsp.ControlCombo"%>
<%@page import="java.util.Vector"%>
<%@page import="com.dimata.util.Command"%>
<!DOCTYPE html>
<%@include file="../../main/javainit_slik.jsp" %>
<%@include file="../../main/checkuser_slik.jsp" %>
<%
    boolean privView = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_PROSES_TRANSFER_DATA, AppObjInfo.G2_TRANSFER_KREDIT_DATA, AppObjInfo.OBJ_TRANSFER_KREDIT_DATA, AppObjInfo.COMMAND_VIEW);
    boolean privStart = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_PROSES_TRANSFER_DATA, AppObjInfo.G2_TRANSFER_KREDIT_DATA, AppObjInfo.OBJ_TRANSFER_KREDIT_DATA, AppObjInfo.COMMAND_START);
    boolean privStop = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_PROSES_TRANSFER_DATA, AppObjInfo.G2_TRANSFER_KREDIT_DATA, AppObjInfo.OBJ_TRANSFER_KREDIT_DATA, AppObjInfo.COMMAND_STOP);
%>
<%
    int iCommand = FRMQueryString.requestCommand(request);
 
    ManagerTransferDataKredit managerTransferData = new ManagerTransferDataKredit();

    switch (iCommand) {

        case Command.START:
            managerTransferData.startTransfer();

            break;

        case Command.STOP:

            managerTransferData.stopMonitor();

            break;

    }
    boolean running = managerTransferData.getStatus();
    
    ProsesTransferDataBank prosesTransferDataBank = new ProsesTransferDataBank();
    Date periodeHost = prosesTransferDataBank.getHostPeriodeDate();
    
    String dateHost = Formater.formatDate(periodeHost, "yyyy-MM");
    String datePeriodeSlik = Formater.formatDate(periode.getTglAwal(), "yyyy-MM");
    if(dateHost.equals(datePeriodeSlik)){
        System.out.println("xxx");
    }

%>
<html>
  <head>
    <meta charset="UTF-8">
    <title>SLIK | Transfer Kredit Data</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <%@include file="../../template-component/css-component.jsp" %>
    <script language="JavaScript">
            
        function cmdStart(){
            document.driver.command.value="<%=Command.START%>";
            document.driver.hidden_driver_id.value="0";
            document.driver.action="transfer_data_kredit.jsp";
            document.driver.submit();
        }

        function cmdStop(){

            document.driver.command.value="<%=Command.STOP%>";
            document.driver.start.value="0";
            document.driver.action="transfer_data_kredit.jsp";
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
    <div class="wrapper">
      
	<%@include file="../../template-component/header-component.jsp" %>
        <%@include file="../../template-component/sidebar-component.jsp" %>

      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            Transfer
            <small>Kredit Data</small>
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
                          <div class="box-header">
                            <h3 class="box-title">
                                Periode Data SLIK yang aktif : <%= (periode.getTglAwal() == null ? "" : Formater.formatDate(periode.getTglAwal(), "MMMM yyyy"))%>
                            </h3><br>
                            <h3 class="box-title">
                                Periode Host CoreBanking : <%=Formater.formatDate(periodeHost, "MMMM yyyy")%>
                            </h3>
                            <%if(!dateHost.equals(datePeriodeSlik)){%>
                              <h3 class="box-title">
                                PERIODE TIDAK SESUAI - SETTING PERIODE DATA SLIK TERLEBIH DAHULU
                            </h3>
                            <%}%>
                        </div>
                        <%if(dateHost.equals(datePeriodeSlik)){%>
                          <div class="box-body">
                              <div class="row">
                                    <div class="col-md-12">
                                        <b>Transfer Data Kredit</b><br>
                                    </div>
                                </div>
                              <div id="countryElement">
                              </div>
                          </div>
                          <%  String stopSts = "";
                              String startSts = "";
                              if (running) {
                                  startSts = "disabled=\"true\"";
                                  stopSts = "";
                              } else {
                                  startSts = "";
                                  stopSts = "disabled=\"true\"";
                              }
                          %>
                            <div class='box-footer'>
                                <%
                                    if(privStart){
                                        %>
                                            <button class="btn btn-primary btnaddgeneral" onClick="javascript:cmdStart()" data-oid="0" data-for="showform" <%=startSts%>>
                                                <i class="fa fa-star"></i> Start
                                            </button>
                                        <%
                                    }
                                    if(privStop){
                                        %>
                                            <button class="btn btn-danger btndeleteareatype" onClick="javascript:cmdStop()" data-for="country"  <%=stopSts%>>
                                                <i class="fa fa-stop"></i> Stop
                                            </button>
                                        <%
                                    }
                                %>
                            </div>
                            <div id="dynamicOrder">
                            </div>    
                          <%}%>          
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
            var data="datatype=2";
            ajaxProsesTransfer("<%=approot%>/AjaxProsesTransfer",data,"GET","#dynamicOrder","loadData","");
        }
        
        //load data pertama kalinya
        loadData();
        
        //untuk membuat load data
        setInterval(function() {
            loadData();
           
        },1000);
      </script>
    </div><!-- ./wrapper -->
  </body>
</html>
