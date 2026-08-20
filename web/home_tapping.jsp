<%-- 
    Document   : home_tapping
    Created on : Nov 17, 2017, 1:52:43 PM
    Author     : dimata005
--%>
<%@page import="java.util.Date"%>
<%@page import="com.dimata.util.Formater"%>
<%@page import="java.util.Vector"%>
<%@page import="com.dimata.util.Command"%>
<!DOCTYPE html>
<%@include file="main/javainit_tapping.jsp" %>
<%@include file="main/checkuser_tapping.jsp" %>
<%
    boolean privView = true;//
    boolean privDelete = true;//userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_HOME, AppObjInfo.G2_HOME, AppObjInfo.OBJ_HOME, AppObjInfo.COMMAND_DELETE);
    boolean privUpdate = true;//userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_HOME, AppObjInfo.G2_HOME, AppObjInfo.OBJ_HOME, AppObjInfo.COMMAND_UPDATE);
    boolean privViewMaster = true;//userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_PROFILE_BANK, AppObjInfo.OBJ_BANK, AppObjInfo.COMMAND_VIEW);
%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>TAPPING | HOME</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <%@include file="dtappingsystem/template-component/css-component.jsp" %>
    </head>
    <body class="<%=skin%>">
        <div class="wrapper">
            <input type="hidden" name="privupdate" id="privupdate" value="<%= privUpdate %>">
            <input type="hidden" name="privview" id="privview" value="<%= privView %>">
            <%@include file="dtappingsystem/template-component/header-component.jsp" %>
            <%@include file="dtappingsystem/template-component/sidebar-component.jsp" %>
            <!-- Content Wrapper. Contains page content -->
            <script language="JavaScript">
                 function cmdChangeCabang(){
                    document.driver.command.value="<%=Command.UPDATE%>";
                    document.driver.action="home_slik.jsp";
                    document.driver.submit();
                }    
            </script>
            <%
                if(privView){
            %>
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Dashboard
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-home"></i> Dashboard</a></li>
                    </ol>
                </section>

                <!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="col-md-12">
                            <div class='box box-primary'>
                            </div>
                        </div><!-- ./col -->
                    </div><!-- /.row -->
                    <!-- Small boxes (Stat box) -->
                    <%-- finish final --%>
                    <div class="row">
                    </div><!-- /.row -->

                </section><!-- /.content -->
            </div><!-- /.content-wrapper -->
            <%
                }
            %>
            <div class='control-sidebar-bg'></div>
            <%@include file="dtappingsystem/template-component/footer-component.jsp" %>
            <%@include file="dtappingsystem/template-component/plugins-component.jsp" %>
        </div><!-- ./wrapper -->?
    </body>
</html>
