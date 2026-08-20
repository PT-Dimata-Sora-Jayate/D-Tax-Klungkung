<%-- 
    Document   : perubahan_data_segment
    Created on : Oct 2, 2016, 6:42:32 PM
    Author     : dimata005
--%>
<%@page import="com.dimata.dslik.session.proses.ManagerTransferData"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<%@page import="com.dimata.dslik.form.masterdata.FrmConnection"%>
<%@page import="com.dimata.gui.jsp.ControlCombo"%>
<%@page import="java.util.Vector"%>
<%@page import="com.dimata.util.Command"%>
<!DOCTYPE html>
<%@include file="../../main/javainit_slik.jsp" %>
<%
int iCommand = FRMQueryString.requestCommand(request);
 
    ManagerTransferData managerTransferData = new ManagerTransferData();

    switch (iCommand) {

        case Command.START:
            managerTransferData.startTransfer();

            break;

        case Command.STOP:

            managerTransferData.stopMonitor();

            break;

    }
     boolean running = managerTransferData.getStatus();
%>
<html>
  <head>
    <meta charset="UTF-8">
    <title>SLIK | Configurasi Databases</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <%@include file="../../template-component/css-component.jsp" %>
    <script language="JavaScript">
            
        function cmdStart(){
            document.driver.command.value="<%=Command.START%>";
            document.driver.hidden_driver_id.value="0";
            document.driver.action="pbb.jsp";
            document.driver.submit();
        }

        function cmdStop(){

            document.driver.command.value="<%=Command.STOP%>";
            document.driver.start.value="0";
            document.driver.action="pbb.jsp";
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
            Delimited Text
            <small>Data</small>
          </h1>
        </section>
        <!-- Main content -->
        <section class="content">
          <!-- Small boxes (Stat box) -->
          <form name="driver"  method ="post" action="" role="form">
            <input type="hidden" name="command" value="<%=iCommand%>">
                <div class="row">
                  <div class="col-md-12">
                      <div class='box box-primary'>
                          <div class='box-header'>
                              Delimited Text
                          </div>
                          <div class="box-body">
                              <div id="countryElement">
                              </div>
                          </div>
                      </div>
                  </div><!-- ./col -->
                </div><!-- /.row -->
          </form>        
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->
      <!-- Add the sidebar's background. This div must be placed
           immediately after the control sidebar -->
      <div class='control-sidebar-bg'></div>
      <%@include file="../../template-component/footer-component.jsp" %>
      <%@include file="../../template-component/plugins-component.jsp" %>
    </div><!-- ./wrapper -->
  </body>
</html>
