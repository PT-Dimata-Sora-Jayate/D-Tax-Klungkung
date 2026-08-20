<%-- 
    Document   : komisi
    Created on : Apr 28, 2015, 6:29:52 PM
    Author     : dimata005
--%>

<%@page import="com.dimata.qdep.form.FRMHandler"%>
<%@page import="com.dimata.travel.entity.driver.PstDriver"%>
<%@page import="com.dimata.travel.entity.driver.Driver"%>
<%@page import="com.dimata.travel.entity.artshop.ArtShop"%>
<%@page import="com.dimata.travel.entity.artshop.PstArtShop"%>
<%@page import="com.dimata.travel.form.komisi.CtrlKomisi"%>
<%@page import="com.dimata.travel.entity.komisi.PstKomisi"%>
<%@page import="com.dimata.travel.entity.komisi.Komisi"%>
<%@page import="com.dimata.travel.form.komisi.FrmKomisi"%>
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
<%@ include file = "../../main/checkuser.jsp" %>
<%!//proses yang akan ditampilkan

    public String drawList(int iCommand, Vector objectClass, long gradeId, FrmKomisi frmObject) {

        ControlList ctrlist = new ControlList();

        ctrlist.setAreaWidth("100%");
        ctrlist.setListStyle("listgen");
        ctrlist.setTitleStyle("tableheader");
        ctrlist.setCellStyle("cellStyle");
        ctrlist.setHeaderStyle("tableheader");
         ctrlist.addHeader("No", "200px");
        ctrlist.addHeader("Date", "200px");
        ctrlist.addHeader("Art Shop Name", "200px");
        ctrlist.addHeader("Driver Name", "200px");
        ctrlist.addHeader("Amount", "200px");
         ctrlist.addHeader("Action", "200px");
        //ctrlist.addHeader("", "200px"); 

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
        for (int i = 0; i < objectClass.size(); i++) {
            Komisi komisi = (Komisi) objectClass.get(i);
            rowx = new Vector(1, 1);
            count=count+1;
            rowx.add(""+count);
            rowx.add(""+komisi.getTgl());
            rowx.add(""+komisi.getArtcode());
            rowx.add(""+komisi.getDrvcode());
            rowx.add(""+FRMHandler.userFormatStringDecimal(komisi.getTot()));
            rowx.add("<center><button type=\"button\" class=\"btn btn-success\" onclick=\"javascript:cmdEdit('" + komisi.getOID() + "','" + komisi.getTgl()+ "','" + komisi.getArt()+ "','"+komisi.getDrv()+"','"+komisi.getTot()+"')\"></i> Edit </button></center>");

            lstData.add(rowx);
            //lstLinkData.add(String.valueOf(komisi.getOID()));
        }
        return ctrlist.drawBootstrapStrip();
    }
%>

<% //proses yang tidak akan ditampilkan hanya diserver side

    int iCommand = FRMQueryString.requestCommand(request);
    int start = FRMQueryString.requestInt(request, "start");
    int prevCommand = FRMQueryString.requestInt(request, "prev_command");
    long oidKomisi = FRMQueryString.requestLong(request, "hidden_komisi_id");
    String gradeDelete = FRMQueryString.requestString(request, "gradeDelete");
    String startDate = FRMQueryString.requestString(request, "tanggalStart");
    String endDate = FRMQueryString.requestString(request, "tanggalEnd");
    /*variable declaration*/

    int recordToGet = 15;
    String msgString = "";
    int iErrCode = FRMMessage.NONE;
    String whereClause = "";
    String orderClause = PstKomisi.fieldNames[PstKomisi.FLD_ID];
    CtrlKomisi ctrlKomisi = new CtrlKomisi(request);
    ControlLine ctrLine = new ControlLine();
    Vector listKomisi = new Vector(1, 1);

    /*switch statement */

    iErrCode = ctrlKomisi.action(iCommand, oidKomisi,request);

    FrmKomisi frmKomisi = ctrlKomisi.getForm();

    //membuat command menjadi none kembali setelah menjalankan delete all
    if ((iCommand == Command.DELETEALL || iCommand == Command.DELETE ) && iErrCode == FRMMessage.NONE) {
        iCommand = Command.NONE;
    }
    
    /* end switch*/

    //int vectSize = PstKomisi.getCount(whereClause);

    /*switch list Student*/

    if (iCommand == Command.FIRST || iCommand == Command.NEXT || iCommand == Command.PREV || iCommand == Command.LAST) {
        start = 0;//ctrlKomisi.action(iCommand, start, vectSize, recordToGet);
    }

    /* end switch list*/

    Komisi komisi = ctrlKomisi.getKomisi();
    msgString = ctrlKomisi.getMessage();

    /* get record to display */
    Date newDay = new Date();

    if (endDate.equals("")) {
        endDate = Formater.formatDate(newDay, "yyyy-MM-dd");
    }

    if (startDate.equals("")) {
        newDay.setDate(1);
        startDate = Formater.formatDate(newDay, "yyyy-MM-dd");
    }
    String where = " km.tgl BETWEEN '" + startDate + "' AND '" + endDate + "' ORDER BY km.id ASC ";
    listKomisi = PstKomisi.listJoin(0,0,where,"");
%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Transport</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
         <link rel="stylesheet" href="../../styles/datepicker/css/jquery.ui.all.css">
        <!-- bootstrap 3.0.2 -->
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
                document.komisi.command.value="2";
                document.komisi.hidden_komisi_id.value="0";
                document.komisi.<%=frmKomisi.fieldNames[frmKomisi.FRM_FIELD_TGL]%>.value="";
                document.komisi.<%=frmKomisi.fieldNames[frmKomisi.FRM_FIELD_ART]%>.value="";
                document.komisi.<%=frmKomisi.fieldNames[frmKomisi.FRM_FIELD_DRV]%>.value="";
                document.komisi.<%=frmKomisi.fieldNames[frmKomisi.FRM_FIELD_TOT]%>.value="";
                 document.getElementById('togglee').style.visibility = 'hidden';
                $(document).ready(function(){
                    $('#compose-modal').modal('show');
                });
            }
            
            function cmdEdit(oid,tgl,art,drv,tot){
                document.komisi.command.value="<%=Command.EDIT%>";
                document.komisi.hidden_komisi_id.value=oid;
                document.komisi.<%=frmKomisi.fieldNames[frmKomisi.FRM_FIELD_TGL]%>.value=tgl;
                document.komisi.<%=frmKomisi.fieldNames[frmKomisi.FRM_FIELD_ART]%>.value=art;
                document.komisi.<%=frmKomisi.fieldNames[frmKomisi.FRM_FIELD_DRV]%>.value=drv;
                document.komisi.<%=frmKomisi.fieldNames[frmKomisi.FRM_FIELD_TOT]%>.value=tot;
                document.getElementById('togglee').style.visibility = 'visible';
                $(document).ready(function(){
                    $('#compose-modal').modal('show');
                });
            }
            
            function cmdSave(){
                var oidDriver = document.komisi.hidden_komisi_id.value;
                //alert(""+oidDriver);
                if(oidDriver!=0){
                    document.komisi.command.value="<%=Command.SAVE%>";
                    //document.komisi.action="komisi.jsp";
                    //document.komisi.submit();
                }else{
                    document.komisi.command.value="<%=Command.SAVE%>";
                    document.komisi.hidden_komisi_id.value="0";
                    //document.komisi.action="komisi.jsp";
                    //document.komisi.submit();
                }
            }
            
            function cmdDelete()
            {
                if (confirm('Yakin Menghapus Data Komisi ? ')) {
                    document.komisi.command.value="<%=Command.DELETE%>";
                    document.komisi.action="komisi.jsp";
                    document.komisi.submit();
                }
               
            }
            function cmdSearch(){
                document.komisi.command.value="<%=Command.SEARCH%>";
                document.komisi.hidden_komisi_id.value="0";
                document.komisi.prev_command.value="<%=prevCommand%>";
                document.komisi.action="komisi.jsp";
                document.komisi.submit();
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
                        <small>Komisi List</small>
                    </h1>
                    <!--<ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li><a href="#">Tables</a></li>
                        <li class="active">Data tables</li>
                    </ol> -->
                </section>
                <!-- Main content -->
                <section class="content">
                    <form name="komisi"  method ="post" action="" role="form">
                        <input type="hidden" name="command" value="<%=iCommand%>">
                        <input type="hidden" name="start" value="<%=start%>">
                        <input type="hidden" name="prev_command" value="<%=prevCommand%>">
                        <input type="hidden" name="hidden_komisi_id" value="<%=oidKomisi%>">    
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
                                                    <button type='button' name='seach' id='search-btn'  class="btn btn-success pull-right" onclick="javascript:cmdSearch()"><i class="fa fa-search"></i></button>
                                                </span>
                                            </div>
                                        </div>
                                        <div class="col-xs-8"><label>&nbsp;</label>
                                            <button  onclick="javascript:cmdAdd()" type="button" class="btn btn-primary pull-right">Add New</button>
                                        </div>
                                    </div>
                                    <%=drawList(iCommand, listKomisi, oidKomisi, frmKomisi)%>
                                </div><!-- /.box -->
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="box-footer">
                                   <%
                                                //jika command == none -> munculkan button add dan delete
                                                // jika command == add atau command == edit -> munculkan button save dan cancel
                                                //jika command == cancel maka munculkan button add dan delete
                                                // jika command == save dan eror ==0 -> maka munculkan command add dan hide command save
                                                // jika command == delete -> maka munculkan command save
                                    if(iCommand==Command.NONE){ %>
                                        <button  onclick="javascript:cmdAdd()" type="button" class="btn btn-primary">Add</button>
                                    <%} else if(iCommand==Command.ADD || iCommand==Command.EDIT){%>
                                        <button  onclick="javascript:cmdAdd()" type="button" class="btn btn-primary">Add</button>
                                    <%} else if(iCommand==Command.CANCEL && iCommand==Command.NONE) {%>
                                        <button  onclick="javascript:cmdAdd()" type="button" class="btn btn-primary">Add</button>
                                    <%} else if(iCommand==Command.SAVE && iErrCode!=0) {%>
                                         <div class="alert alert-success alert-dismissable">
                                            <i class="fa fa-check"></i>
                                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                            <b><%=msgString%></b>
                                        </div>
                                        <button  onclick="javascript:cmdAdd()" type="button" class="btn btn-primary">Add</button>
                                    <%} else if(iCommand==Command.SAVE && iErrCode==0) {%>
                                        <div class="alert alert-success alert-dismissable">
                                            <i class="fa fa-check"></i>
                                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                            <b><%=msgString%></b>
                                        </div>
                                        <button  onclick="javascript:cmdAdd()" type="button" class="btn btn-primary">Add</button>
                                    <%} else if(iCommand==Command.DELETE) {%>
                                    <div class="alert alert-danger alert-dismissable">
                                        <i class="fa fa-ban"></i>
                                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                        <b><%=msgString%></b>
                                        <button  onclick="javascript:cmdAdd()" type="button" class="btn btn-primary">Add</button>
                                    </div>
                                    <%}else{%>
                                    <div class="alert alert-danger alert-dismissable">
                                        <i class="fa fa-ban"></i>
                                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                        <b><%=msgString%></b>
                                        <button  onclick="javascript:cmdAdd()" type="button" class="btn btn-primary">Add</button>
                                    </div>
                                    <%}%>
                                </div>
                            </div>
                        </div>
                        <div class="modal fade" id="compose-modal" tabindex="-1" role="dialog" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        <h4 class="modal-title"></i>Input Komisi</h4>
                                    </div>
                                    <div class="modal-body">
                                        <div class="form-group">
                                            <div class="input-group">
                                                <span class="input-group-addon">Date</span>
                                                <input name="<%=frmKomisi.fieldNames[frmKomisi.FRM_FIELD_TGL]%>" id="datepickertiga" type="text" class="form-control" placeholder="yyyy-MM-dd" value="<%=endDate%>" required>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <span class="input-group-addon">Art Shop Name</span>
                                                 <%
                                                        Vector artshop_key = new Vector();
                                                        Vector artshop_value = new Vector();
                                                        Vector listArtshop = new Vector();
                                                        listArtshop = PstArtShop.listAll();
                                                        if (listArtshop != null && listArtshop.size() > 0) {
                                                            for (int i = 0; i < listArtshop.size(); i++) {
                                                                ArtShop obj = (ArtShop) listArtshop.get(i);
                                                                artshop_value.add(obj.getName());
                                                                artshop_key.add("" + obj.getOID());
                                                            }
                                                        }
                                                    %>
                                                <%=ControlCombo.drawBoostrap(frmKomisi.fieldNames[frmKomisi.FRM_FIELD_ART], null, "", artshop_key, artshop_value, "form-control")%>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <span class="input-group-addon">Driver Name</span>
                                                 <%
                                                        Vector driver_key = new Vector();
                                                        Vector driver_value = new Vector();
                                                        Vector listDriver = new Vector();
                                                        listDriver = PstDriver.listAll();
                                                        if (listDriver != null && listDriver.size() > 0) {
                                                            for (int i = 0; i < listDriver.size(); i++) {
                                                                Driver obj = (Driver) listDriver.get(i);
                                                                driver_value.add(obj.getNama());
                                                                driver_key.add("" + obj.getOID());
                                                            }
                                                        }
                                                    %>
                                                <%=ControlCombo.drawBoostrap(frmKomisi.fieldNames[frmKomisi.FRM_FIELD_DRV], null, "", driver_key, driver_value, "form-control")%>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <span class="input-group-addon">Amount</span>
                                                <input name="<%=frmKomisi.fieldNames[frmKomisi.FRM_FIELD_TOT]%>" type="text" class="form-control" placeholder="0.00" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="modal-footer clearfix">
                                        <input type="submit" value="Save" class="btn btn-primary pull-left" onclick="javascript:cmdSave()" >
                                        <button type="button" class="btn btn-danger pull-left" onclick="javascript:cmdDelete()" id="togglee"></i> Delete</button>
                                    </div>

                                </div><!-- /.modal-content -->
                            </div><!-- /.modal-dialog -->
                        </div><!-- /.modal -->    
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
         <!-- calender -->
        <script src="../../styles/datepicker/js/jquery.ui.core.js"></script>
        <script src="../../styles/datepicker/js/jquery.ui.widget.js"></script>
        <script src="../../styles/datepicker/js/jquery.ui.datepicker.js"></script>

        <!-- page script -->
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
