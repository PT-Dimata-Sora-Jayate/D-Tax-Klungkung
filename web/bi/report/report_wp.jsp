<%@page import="com.dimata.dtaxintegration.session.SessionData"%>
<%@page import="com.dimata.webclient.AppSetting"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.dimata.dtaxintegration.session.DTaxIntegrationMonitor"%>
<%@page import="com.dimata.dtaxintegration.entity.payment.PaymentPhr"%>
<%@page import="com.dimata.dtaxintegration.entity.payment.PstPaymentPhr"%>
<%@page import="com.dimata.dtaxintegration.entity.inquery.Payment"%>
<%@page import="com.dimata.webclient.EchoLaporanPaymentDetail"%>
<%@page import="com.dimata.dtaxintegration.entity.laporan.LaporanPayment"%>
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

    public String drawList(Vector objectClass){

                Vector resultType = new Vector(1, 1);
                Vector resultHeader = new Vector(1, 1);
                Vector resultData = new Vector(1, 1);
                
                if((objectClass!=null)&&(objectClass.size()>0)){
                    for (int i = 0; i < objectClass.size(); i++) {
                        if(i==0){
                            resultHeader = (Vector) objectClass.get(0);
                        }else if(i==1){
                            resultType = (Vector) objectClass.get(1);
                        }else{
                             resultData = (Vector) objectClass.get(2);
                        }
                    }
                   
                }
            
            
		ControlList ctrlist = new ControlList();

		ctrlist.setAreaWidth("100%");

		ctrlist.setListStyle("listarea");

		ctrlist.setTitleStyle("listheader");

		ctrlist.setCellStyle("table_cell");

		ctrlist.setHeaderStyle("listheader");
                
                ctrlist.setBorder(1);
                
                if((resultHeader!=null)&&(resultHeader.size()>0)){
                    for (int i = 0; i < resultHeader.size(); i++) {
                        String header = (String) resultHeader.get(i);
                        ctrlist.addHeader(""+header,"10%");
                    }
                }
		
                
		ctrlist.setLinkRow(1);

		ctrlist.setLinkSufix("");

		Vector lstData = ctrlist.getData();

		Vector lstLinkData = ctrlist.getLinkData();

		ctrlist.setLinkPrefix("javascript:cmdEdit('");

		ctrlist.setLinkSufix("')");

		ctrlist.reset();

		for (int i = 0; i < resultData.size(); i++) {

			Vector rowx = new Vector();
                        Vector detailResultData = (Vector) resultData.get(i);
                        for (int k = 0; k < detailResultData.size(); k++) {
                            String header = (String) detailResultData.get(k);
                            rowx.add(""+header);
                        }
                        
			lstData.add(rowx);

			lstLinkData.add("");

		}

		return ctrlist.draw();

	}
%>

<% //proses yang tidak akan ditampilkan hanya diserver side

    String loginID = FRMQueryString.requestString(request,"text_sql");
    SessionData sess = new SessionData();
    Vector list = new Vector();
    if(!loginID.equals("")){
        list = sess.getCustomeSql(""+loginID);
    }
    
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
            
            function cmdLogin()

            {	

              document.driver.action = "report_wp.jsp";
              document.driver.submit();

            }
             function cmdPrintExcel(){
                var query =  document.driver.text_sql.value;
                var myWindow = window.open("report_wp_excel.jsp?text_sql="+query, "MsgWindow", "toolbar=no, scrollbars=yes, resizable=yes, top=500, left=500, width=1500, height=800");
                if (window.focus) 
                 {
                   myWindow.focus();
                 }
            }
        </script>
        <style type="text/css">
            .page {
                width: 29.7cm;
                min-height: 21cm;
                padding:1cm;
                margin: 1cm auto;
                border: 1px #D3D3D3 solid;
                border-radius: 5px;
                background: white;
                box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
            }
            .subpage {
                min-height: 21cm;
            }

            .printable{
                display:none;
            }

            .modal-lg-print{
                max-width: 1366px;
                width: 96.5%;
            }

            @page{
                size: portrait;
                margin: 0;
            }

            @media print{
                .printable{
                        display: block;
                        visibility: visible;
                }
                .nonprint{
                        display: none;
                        visibility: hidden;
                }
                .page {
                    margin:0;
                    width: 7.5 cm;
                    min-height: 10cm;
                    background: white;
                    border:none;
                }
                .box-default{
                    border:1px solid #ccc;
                    border-top:3px solid #ccc;
                }

                td,th{
                    font-size: 8px;
                }
            }
            
        </style> 
    </head>
    <body class="skin-blue">
        <!-- header logo: style can be found in header.less -->
        <%@ include file = "../../header_mobile.jsp" %> 
        <div class="wrapper row-offcanvas row-offcanvas-left nonprint">
            <!-- Left side column. contains the logo and sidebar -->
            <%@ include file = "../../menu_left_mobile_bi.jsp" %> 

            <!-- Right side column. Contains the navbar and content of the page -->
            <aside class="right-side">                
                <!-- Content Header (Page header) -->
                <section class="content-header " >
                    <h1>
                        <small>Report Custome</small>
                    </h1>
                </section>
                <!-- Main content -->
                <section class="content" >
                    <form name="driver"  method ="post" action="" role="form">
                        <input type="hidden" name="menu" value="13">
                        <input type="hidden" name="tree" value="5">
                        <div class="row">
                            <div class="col-xs-12">
                               <div class="box">
                                   <div class="box-header">
                                       &nbsp;
                                   </div>    
                                   <div class="box-header">>
                                       <div class="col-xs-4"><label>Query</label>
                                           <div class="input-group">  
                                           <input type="text" name="text_sql" value="<%=loginID%>">
                                            <span class="input-group-btn">
                                                <button type='button' name='seach' id='search-btn' class="btn btn-success pull-right"  onclick="javascript:cmdLogin()"><i class="fa fa-search"></i></button>
                                            </span>
                                            </div>
                                       </div>
                                       <div class="col-xs-2"><label>&nbsp;</label>  
                                       </div>
                                       <% if(userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_REPORT,AppObjInfo.G2_REPORT_PHR, AppObjInfo.OBJ_REPORT_DETAIL_PHR, AppObjInfo.COMMAND_PRINT)){ %>
                                           <div class="col-xs-2"><label>&nbsp;</label>  
                                               <button  onclick="javascript:cmdPrintExcel()" type="button" class="btn btn-danger pull-right">Export Excel</button>
                                           </div>
                                           <div class="col-xs-2"><label>&nbsp;</label>  
                                               <button  onclick="javascript:cmdPrintPdfx()" type="button" class="btn btn-info pull-right">Export Pdf</button>
                                           </div>
                                       <%}%>     
                                    </div>
                                    <%if((list!=null)&&(list.size()>0)){%>

                                    <%=drawList(list)%>

                                   <%}else{%>

                                      &nbsp;no employee match the search parameter  .... 

                                  <%}%>
                                </div><!-- /.box -->
                            </div>
                        </div>     
                    </form>
                </section><!-- /.content -->
            </aside><!-- /.right-side -->
            <div id="myModalView" class="modal fade" tabindex="-1">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="modal-title"></h4>
                        </div>
                        <div class="modal-body" id="modal-body">
                        </div>
                        <div class="modal-footer"><br><br>
                            <button type="button" class="btn btn-primary" onclick="javascript:cmdPrint()">Print</button>
                            <button type="button" data-dismiss="modal" class="btn btn-danger">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div><!-- ./wrapper -->
       
        <!-- jQuery 2.0.2 -->
        <script type="text/javascript" src="../../styles/jquery.min.js"></script>
        <!-- jQuery UI 1.10.3 -->
        <script src="../../styles/bootstrap3.1/js/jquery-ui-1.10.3.min.js" type="text/javascript"></script>
        <!-- Bootstrap -->
        <script src="../../styles/bootstrap3.1/js/bootstrap.min.js" type="text/javascript"></script>
        <script language="JavaScript">
            function cmdPrintPdfx(start, end) {
                 ajaxScriptParentPage("ajaxDataReportSource.jsp","Search Data PHR",1,"#myModalView",start,end, "#modal-title", "#modal-body");
            }
            
            function ajaxScriptParentPage(pageTarget, titlePage, pageShow, modalTemplate,start, end, titleId, bodyId){
                $(titleId).html(titlePage);
                $(modalTemplate).modal("show");
                $(bodyId).html("Harap tunggu ...");
                $.ajax({
                    type	: "POST",
                    url	: pageTarget,
                    data	: {"searchType":"parent", 
                                "pageShow":pageShow,
                                "tanggalStart":start,
                                "tanggalEnd":end
                            },
                    cache	: false,
                    success	: function(data){
                        $(bodyId).html(data);
                        $(".print-body").html(data).fadeIn("medium");

                    },
                    error : function(){
                        $(bodyId).html("Data not found");
                    }
                });
            }

            //agar modal tidak close saat are di luar form di klik
            $("#myModalView").modal({
                            backdrop:"static",
                            keyboard:false,
                            show:false
            });

            function ajaxFunctionChildPage(ajaxFunction){
                return ajaxFunction;
            }
        </script>
        <div class="printable">
            <span class="print-body"></span>
        </div>
        <!-- DATA TABES SCRIPT -->
        <script src="../../styles/bootstrap3.1/js/plugins/datatables/jquery.dataTables.js" type="text/javascript"></script>
        <script src="../../styles/bootstrap3.1/js/plugins/datatables/dataTables.bootstrap.js" type="text/javascript"></script>
        <!-- AdminLTE App -->
        <script src="../../styles/bootstrap3.1/js/AdminLTE/app.js" type="text/javascript"></script>
        <!-- page script -->
        <script src="../../styles/datepicker/js/jquery.ui.datepicker.js"></script>

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
    </body>
</html>

