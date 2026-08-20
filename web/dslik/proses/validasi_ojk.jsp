<%-- 
    Document   : validasi_ojk
    Created on : Sep 10, 2017, 10:38:40 PM
    Author     : dimata005
--%>
<%@page import="com.dimata.dslik.session.proses.ManagerValidasiTransferData"%>
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
    boolean privView = true;//userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_PROSES_TRANSFER_DATA, AppObjInfo.G2_TRANSFER_PENGURUS_PEMILIK_DATA, AppObjInfo.OBJ_TRANSFER_PENGURUS_PEMILIK_DATA, AppObjInfo.COMMAND_VIEW);
    boolean privStart = true;//userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_PROSES_TRANSFER_DATA, AppObjInfo.G2_TRANSFER_PENGURUS_PEMILIK_DATA, AppObjInfo.OBJ_TRANSFER_PENGURUS_PEMILIK_DATA, AppObjInfo.COMMAND_START);
    boolean privStop = true;//userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_PROSES_TRANSFER_DATA, AppObjInfo.G2_TRANSFER_PENGURUS_PEMILIK_DATA, AppObjInfo.OBJ_TRANSFER_PENGURUS_PEMILIK_DATA, AppObjInfo.COMMAND_STOP);
%>
<%
    int iCommand = FRMQueryString.requestCommand(request);
%>
<html>
  <head>
    <meta charset="UTF-8">
    <title>SLIK | Validasi Transfer</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <%@include file="../../template-component/css-component.jsp" %>
    <script language="JavaScript">
            
        function cmdStart(){
            document.driver.command.value="<%=Command.START%>";
            document.driver.hidden_driver_id.value="0";
            document.driver.action="validasi_transfer.jsp";
            document.driver.submit();
        }

        function cmdStop(){

            document.driver.command.value="<%=Command.STOP%>";
            document.driver.start.value="0";
            document.driver.action="validasi_transfer.jsp";
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
            <small>Validasi Transfer File Dilimted Eror OJK</small>
          </h1>
        </section>
        <!-- Main content -->
        <%
            if(privView){
        %>
        <section class="content">
          <!-- Small boxes (Stat box) -->
          <%--
          <form name="driver"  method ="post" action="" role="form">
            <input type="hidden" name="command" value="<%=iCommand%>">
                <div class="row">
                  <div class="col-md-12">
                      <div class='box box-primary'>
                          <div class="box-header">
                        </div>
                        <div class="box-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <b>Validasi Transfer</b><br>
                                </div>
                            </div>
                        </div>        
                      </div>
                  </div><!-- ./col -->
                </div><!-- /.row -->
          </form>    
          --%>
          Select a file to upload: <br />
            <form action="fileupload.jsp" method="post" enctype="multipart/form-data">
                <div class="row">
                    <div class="col-md-12">
                       <div class='box box-primary'>
                          <div class="box-header">
                        </div>
                        <div class="box-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <input type="hidden" name="command" value="<%=iCommand%>">
                                    <input type="file" name="file" size="50" />
                                    <br />
                                    <input type="submit" value="Upload File" />
                                </div>
                            </div>
                        </div>        
                      </div> 
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
  </body>
</html>
