<%-- 
    Document   : report_bbm_summary
    Created on : May 1, 2015, 10:06:28 AM
    Author     : dimata005
--%>
<%@page import="com.dimata.travel.entity.report.BbmReport"%>
<%@page import="com.dimata.travel.session.SessBbmReport"%>
<%@page import="com.dimata.travel.entity.report.SrcReport"%>
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

    public String drawList(int iCommand, Vector objectClass, long gradeId) {

        ControlList ctrlist = new ControlList();

        ctrlist.setAreaWidth("100%");
        ctrlist.setListStyle("listgen");
        ctrlist.setTitleStyle("tableheader");
        ctrlist.setCellStyle("cellStyle");
        ctrlist.setHeaderStyle("tableheader");
        
        ctrlist.addHeader("No", "200px");
        ctrlist.addHeader("Police Number", "200px");
        ctrlist.addHeader("Liter", "200px");
        ctrlist.addHeader("Total", "200px");
         
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
        int count=0;
        Vector rowx = new Vector(1, 1);
        double total=0.0;
        
        for (int i = 0; i < objectClass.size(); i++) {
            BbmReport reportDetail = (BbmReport) objectClass.get(i);
            count=count+1;
            total=total+reportDetail.getTotal();
            rowx = new Vector(1, 1);
            rowx.add(""+count);
            rowx.add(""+reportDetail.getNoPol());
            rowx.add(""+reportDetail.getLiter());
            rowx.add(""+FRMHandler.userFormatStringDecimal(reportDetail.getTotal()));
            
            lstData.add(rowx);
            //lstLinkData.add("");
        }
        
        rowx = new Vector(1, 1);
        rowx.add("");
        rowx.add("");
        rowx.add("<b>Total<b>");
        rowx.add(""+FRMHandler.userFormatStringDecimal(total));

        lstData.add(rowx);

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
    double exchangeRate = FRMQueryString.requestDouble(request, "exchangeRate");
    double pemotongan = FRMQueryString.requestDouble(request, "pemotongan");
    
    /*variable declaration*/

    int recordToGet = 15;
    String msgString = "";
    int iErrCode = FRMMessage.NONE;
    String whereClause = "";
    String orderClause = "";//PstTransportServiceOrder.fieldNames[PstTransportServiceOrder.FLD_ID];
    ControlLine ctrLine = new ControlLine();
    Vector listReportDetail = new Vector(1, 1);

    //membuat command menjadi none kembali setelah menjalankan delete all
    if (iCommand == Command.DELETEALL && iErrCode == FRMMessage.NONE) {
        iCommand = Command.NONE;
    }

    /* end switch*/

    //int vectSize = PstTransportServiceOrder.getCount(whereClause);

    /*switch list Student*/

    if (iCommand == Command.FIRST || iCommand == Command.NEXT || iCommand == Command.PREV || iCommand == Command.LAST) {
        start = 0;//ctrlTransportServiceOrder.action(iCommand, start, vectSize, recordToGet);
    }

    /* get record to display */
    Date newDay=new Date();

    if(endDate.equals("")){
        exchangeRate=8500;
        pemotongan=0.05;
        endDate=Formater.formatDate(newDay,"yyyy-MM-dd");
    }
    
    if(startDate.equals("")){
        newDay.setDate(1);
        startDate=Formater.formatDate(newDay,"yyyy-MM-dd");
    }
    
    SrcReport srcReportDetailSummary = new SrcReport();
    srcReportDetailSummary.setStartDate(startDate);
    srcReportDetailSummary.setEndDate(endDate);
    srcReportDetailSummary.setExchangeRate(exchangeRate);
    srcReportDetailSummary.setPemotonganKomisi(pemotongan);
    listReportDetail = SessBbmReport.getListBBMSummary(srcReportDetailSummary,0,0);
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
            
            function cmdPrint(){
                var myWindow = window.open("report_bbm_summary_excel.jsp?tanggalStart=<%=startDate%>&tanggalEnd=<%=endDate%>&exchangeRate=<%=exchangeRate%>&pemotongan=<%=pemotongan%>", "MsgWindow", "toolbar=no, scrollbars=yes, resizable=yes, top=500, left=500, width=1500, height=800");
                if (window.focus) 
                 {
                   myWindow.focus();
                 }
            }
            
            function cmdSearch(){
                document.driver.command.value="<%=Command.SEARCH%>";
                document.driver.hidden_driver_id.value="0";
                document.driver.prev_command.value="<%=prevCommand%>";
                document.driver.action="report_bbm_summary.jsp";
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
                        Report
                        <small>BBM Summary</small>
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
                                                    <button type='button' name='seach' id='search-btn' class="btn btn-success pull-right"  onclick="javascript:cmdSearch()"><i class="fa fa-search"></i></button>
                                                </span>
                                            </div>
                                       </div>
                                       <div class="col-xs-2"><label>&nbsp;</label>  
                                            
                                       </div>
                                       <div class="col-xs-2"><label>&nbsp;</label>  
                                       </div>
                                       <div class="col-xs-4"><label>&nbsp;</label>
                                           <button  onclick="javascript:cmdPrint()" type="button" class="btn btn-primary pull-right">Print Excel</button>
                                       </div>
                                    </div>
                                    <%=drawList(iCommand, listReportDetail, oidDriver)%>
                                </div><!-- /.box -->
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="box-footer">
                                   <button  onclick="javascript:cmdPrint()" type="button" class="btn btn-primary">Print Excel</button>
                                </div>
                            </div>
                        </div>       
                    </form>
                </section><!-- /.content -->
            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->


        <!-- jQuery 2.0.2 -->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
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
