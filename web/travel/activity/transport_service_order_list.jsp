<%-- 
    Document   : tso_list
    Created on : Apr 28, 2015, 6:40:09 PM
    Author     : dimata005
--%>

<%@page import="com.dimata.travel.entity.hotel.Hotel"%>
<%@page import="com.dimata.travel.entity.hotel.PstHotel"%>
<%@page import="com.dimata.travel.entity.travelagent.TravelAgent"%>
<%@page import="com.dimata.travel.entity.travelagent.PstTravelAgent"%>
<%@page import="com.dimata.gui.jsp.ControlDate"%>
<%@page import="com.dimata.travel.entity.tso.TransportServiceOrder"%>
<%@page import="com.dimata.travel.form.tso.CtrlTransportServiceOrder"%>
<%@page import="com.dimata.travel.entity.tso.PstTransportServiceOrder"%>
<%@page import="com.dimata.travel.form.tso.FrmTransportServiceOrder"%>
<!DOCTYPE html>
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

    public String drawList(int iCommand, Vector objectClass, long gradeId, FrmTransportServiceOrder frmObject) {

        ControlList ctrlist = new ControlList();

        ctrlist.setAreaWidth("100%");
        ctrlist.setListStyle("listgen");
        ctrlist.setTitleStyle("tableheader");
        ctrlist.setCellStyle("cellStyle");
        ctrlist.setHeaderStyle("tableheader");
        
        ctrlist.addHeader("No", "200px");//0
        ctrlist.addHeader("Tour No", "200px");//1
        ctrlist.addHeader("Order No", "200px");//2
        ctrlist.addHeader("Date", "200px");//3
        ctrlist.addHeader("Agent Name", "200px");//4
        ctrlist.addHeader("Guest/Group Name", "200px");//5
        ctrlist.addHeader("Hotel", "200px");//6
        ctrlist.addHeader("Print", "200px");//7
        ctrlist.addHeader("Action", "200px");//8
        if (iCommand != Command.EDIT) {
            //membuat link dirow 0
            ctrlist.setLinkRow(0);
        }
        ctrlist.setLinkSufix("");
        Vector lstData = ctrlist.getData();
        Vector lstLinkData = ctrlist.getLinkData();
        //ctrlist.setLinkPrefix("javascript:cmdEdit('");
        //ctrlist.setLinkSufix("')");

        ctrlist.reset();
        int index = -1;
        Vector rowx = new Vector(1, 1);
        int count=0;
        for (int i = 0; i < objectClass.size(); i++) {
            TransportServiceOrder transportServiceOrder = (TransportServiceOrder) objectClass.get(i);
            count=count+1;
            rowx = new Vector(1, 1);
            rowx.add(""+count);//0
            rowx.add(""+transportServiceOrder.getTourno());//1
            rowx.add(""+transportServiceOrder.getRefno());//2
            rowx.add(""+transportServiceOrder.getDate());//3
            rowx.add(""+transportServiceOrder.getAgentName());//4
            rowx.add(""+transportServiceOrder.getClient());//5
            rowx.add(""+transportServiceOrder.getHotelName());//6
            rowx.add(""+"<input type=\"checkbox\" name=\"check\" value=\"1\" id=\"myCheck\" >");//7
            rowx.add("<center><button type=\"button\" class=\"btn btn-success\" onclick=\"javascript:cmdEdit('"+transportServiceOrder.getOID()+"')\"></i> Edit </button></center>");
            
            lstData.add(rowx);
        }
        return ctrlist.drawBootstrapStrip();
    }
%>

<% //proses yang tidak akan ditampilkan hanya diserver side

    int iCommand = FRMQueryString.requestCommand(request);
    int start = FRMQueryString.requestInt(request, "start");
    int prevCommand = FRMQueryString.requestInt(request, "prev_command");
    long oidDriver = FRMQueryString.requestLong(request, "hidden_driver_id");
    String gradeDelete = FRMQueryString.requestString(request, "gradeDelete");
    String startDate = FRMQueryString.requestString(request, "tanggalStart");
    String endDate = FRMQueryString.requestString(request, "tanggalEnd");

    /*variable declaration*/
    int recordToGet = 15;
    String msgString = "";
    int iErrCode = FRMMessage.NONE;
    String whereClause = "";
    String orderClause = PstTransportServiceOrder.fieldNames[PstTransportServiceOrder.FLD_ID];
    CtrlTransportServiceOrder ctrlTransportServiceOrder = new CtrlTransportServiceOrder(request);
    ControlLine ctrLine = new ControlLine();
    Vector listDriver = new Vector(1, 1);

    /*switch statement */

    iErrCode = ctrlTransportServiceOrder.action(iCommand, oidDriver,request);

    FrmTransportServiceOrder frmTransportServiceOrder = ctrlTransportServiceOrder.getForm();

    //membuat command menjadi none kembali setelah menjalankan delete all
    if (iCommand == Command.DELETEALL && iErrCode == FRMMessage.NONE) {
        iCommand = Command.NONE;
    }

    /* end switch*/

    int vectSize = PstTransportServiceOrder.getCount(whereClause);

    /*switch list Student*/

    if (iCommand == Command.FIRST || iCommand == Command.NEXT || iCommand == Command.PREV || iCommand == Command.LAST) {
        start = 0;//ctrlTransportServiceOrder.action(iCommand, start, vectSize, recordToGet);
    }

    /* end switch list*/

    TransportServiceOrder transportServiceOrder = ctrlTransportServiceOrder.getTransportServiceOrder();
    msgString = ctrlTransportServiceOrder.getMessage();

    /* get record to display */
    Date newDay=new Date();

    if(endDate.equals("")){
        endDate=Formater.formatDate(newDay,"yyyy-MM-dd");
    }
    
    if(startDate.equals("")){
        newDay.setDate(1);
        startDate=Formater.formatDate(newDay,"yyyy-MM-dd");
    }
    
    
    String where = " ts.date BETWEEN '"+startDate+"' AND '"+endDate+"'";
    
    listDriver = PstTransportServiceOrder.listJoin(0,500,where,"");
%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Transport</title>
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
            
            function cmdAdd(){
                document.driver.command.value="2";
                document.driver.hidden_driver_id.value="0";
                var myWindow = window.open("transport_service_order_edit.jsp", "MsgWindow", "toolbar=no, scrollbars=yes, resizable=yes, top=500, left=500, width=1500, height=800");
                if (window.focus) 
                 {
                   myWindow.focus();
                 }
            }
            
            function cmdEdit(oid){
                var myWindow = window.open("transport_service_order_edit.jsp?hidden_tso_id="+oid+"&command=3", "MsgWindow", "toolbar=no, scrollbars=yes, resizable=no, top=500, left=500, width=1500, height=800");
                if (window.focus) 
                 {
                   myWindow.focus();
                 }
            }
            
            function cmdSave(){
                document.driver.command.value="<%=Command.SAVE%>";
                document.driver.hidden_driver_id.value="0";
                document.driver.prev_command.value="<%=prevCommand%>";
                document.driver.action="driver_list.jsp";
                document.driver.submit();
            }
            
            function cmdSearch(){
                document.driver.command.value="<%=Command.SEARCH%>";
                document.driver.hidden_driver_id.value="0";
                document.driver.prev_command.value="<%=prevCommand%>";
                document.driver.action="transport_service_order_list.jsp";
                document.driver.submit();
            }
            
        </script>
        
        
    </head>
    <body class="skin-blue">
        <!-- header logo: style can be found in header.less -->
        <%@ include file = "../../header_mobile.jsp" %> 
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <!-- Left side column. contains the logo and sidebar -->
            <%@ include file = "../../menu_left_mobile.jsp" %> 

            <!-- Right side column. Contains the navbar and content of the page -->
            <aside class="right-side">                
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Tables
                        <small>Transport Service List</small>
                    </h1>
                    <!--<ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li><a href="#">Tables</a></li>
                        <li class="active">Data tables</li>
                    </ol> -->
                </section>
                <!-- Main content -->
                <section class="content">
                    <form name="driver"  method ="post" action="" role="form">
                        <input type="hidden" name="command" value="<%=iCommand%>">
                        <input type="hidden" name="vectSize" value="<%=vectSize%>">
                        <input type="hidden" name="start" value="<%=start%>">
                        <input type="hidden" name="prev_command" value="<%=prevCommand%>">
                        <input type="hidden" name="hidden_driver_id" value="<%=oidDriver%>">
                        <div class="row">
                            <div class="col-xs-12">
                               <div class="box">
                                   <div class="box-header">
                                       &nbsp;
                                   </div>    
                                   <div class="box-header">
                                       <div class="col-xs-2"><label>Show From Date</label>
                                           <input name="tanggalStart" id="datepicker" type="text" class="form-control" placeholder="yyyy-MM-dd" value="<%=startDate%>">
                                       </div>
                                       <div class="col-xs-2"><label>To Date Date</label>
                                        <div class="input-group">   
                                            <input name="tanggalEnd" id="datepickerdua" type="text" class="form-control" placeholder="yyyy-MM-dd" value="<%=endDate%>">
                                            <span class="input-group-btn">
                                                <button type='button' name='seach' id='search-btn' class="btn btn-success pull-right" onclick="javascript:cmdSearch()"><i class="fa fa-search"></i></button>
                                            </span>
                                        </div>
                                       </div>
                                       <div class="col-xs-7">
                                           <button  onclick="javascript:cmdAdd()" type="button" class="btn btn-primary pull-right">Add New</button>
                                       </div>
                                       <div class="col-xs-1">
                                           <%--<button  onclick="javascript:cmdPrint()" type="button" class="btn btn-primary pull-right">Print</button>--%>
                                       </div>     
                                    </div>
                                    <%=drawList(iCommand, listDriver, oidDriver, frmTransportServiceOrder)%>
                                </div><!-- /.box -->
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="box-footer">
                                   <%
                                    if(iCommand==Command.NONE){ %>
                                        <button  onclick="javascript:cmdAdd()" type="button" class="btn btn-primary">Add New</button>
                                        <%--<button  onclick="javascript:cmdPrint()" type="button" class="btn btn-primary">Print</button>--%>
                                    <%} else if(iCommand==Command.ADD || iCommand==Command.EDIT){%>
                                        <button  onclick="javascript:cmdAdd()" type="button" class="btn btn-primary">Add New</button>
                                        <%--<button  onclick="javascript:cmdPrint()" type="button" class="btn btn-primary">Print</button>--%>
                                    <%} else if(iCommand==Command.CANCEL && iCommand==Command.NONE) {%>
                                        <button  onclick="javascript:cmdAdd()" type="button" class="btn btn-primary">Add New</button>
                                         <%--<button  onclick="javascript:cmdPrint()" type="button" class="btn btn-primary">Print</button>--%>
                                    <%} else if(iCommand==Command.SAVE && iErrCode!=0) {%>
                                        <div class="alert alert-success alert-dismissable">
                                            <i class="fa fa-check"></i>
                                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                            <b><%=msgString%></b>
                                        </div>
                                        <button  onclick="javascript:cmdAdd()" type="button" class="btn btn-primary">Add New</button>
                                         <%--<button  onclick="javascript:cmdPrint()" type="button" class="btn btn-primary">Print</button>--%>
                                    <%} else if(iCommand==Command.SAVE && iErrCode==0) {%>
                                        <div class="alert alert-success alert-dismissable">
                                            <i class="fa fa-check"></i>
                                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                            <b><%=msgString%></b>
                                        </div>
                                        <button  onclick="javascript:cmdAdd()" type="button" class="btn btn-primary">Add New</button>
                                         <%--<button  onclick="javascript:cmdPrint()" type="button" class="btn btn-primary">Print</button>--%>
                                    <%} else if(iCommand==Command.DELETE) {%>
                                        <div class="alert alert-danger alert-dismissable">
                                            <i class="fa fa-ban"></i>
                                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                            <b><%=msgString%></b>
                                            <button  onclick="javascript:cmdAdd()" type="button" class="btn btn-primary">Add New</button>
                                             <%--<button  onclick="javascript:cmdPrint()" type="button" class="btn btn-primary">Print</button>--%>
                                        </div>
                                    <%} else if(iCommand==Command.SEARCH) {%>
                                         <div class="alert alert-success alert-dismissable">
                                            <i class="fa fa-check"></i>
                                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                            <b><%=msgString%></b>
                                        </div>
                                        <button  onclick="javascript:cmdAdd()" type="button" class="btn btn-primary">Add</button>
                                         <%--<button  onclick="javascript:cmdPrint()" type="button" class="btn btn-primary">Print</button>--%>
                                    <%}%>
                                </div>
                            </div>
                        </div>       
                    </form>
                </section><!-- /.content -->
            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->


        <!-- jQuery 2.0.2 -->
        <script src="../../styles/jquery.min.js"></script>
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
                $("#example1").dataTable();
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
