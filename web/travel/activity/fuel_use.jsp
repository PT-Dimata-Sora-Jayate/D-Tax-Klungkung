<%-- 
    Document   : fuel_use
    Created on : Apr 28, 2015, 11:55:33 AM
    Author     : dimata005
--%>

<%@page import="com.dimata.travel.entity.driver.Driver"%>
<%@page import="com.dimata.travel.entity.driver.PstDriver"%>
<%@page import="com.dimata.travel.entity.spbu.Spbu"%>
<%@page import="com.dimata.travel.entity.spbu.PstSpbu"%>
<%@page import="com.dimata.travel.entity.vehicle.PstVehicle"%>
<%@page import="com.dimata.travel.entity.vehicle.Vehicle"%>
<%@page import="com.dimata.qdep.form.FRMHandler"%>
<%@page import="com.dimata.gui.jsp.ControlDate"%>
<%@page import="com.dimata.travel.entity.bbm.FuelUse"%>
<%@page import="com.dimata.travel.form.bbm.CtrlFuelUse"%>
<%@page import="com.dimata.travel.entity.bbm.PstFuelUse"%>
<%@page import="com.dimata.travel.form.bbm.FrmFuelUse"%>
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

    public String drawList(int iCommand, Vector objectClass, long gradeId, FrmFuelUse frmObject) {

        ControlList ctrlist = new ControlList();

        ctrlist.setAreaWidth("100%");
        ctrlist.setListStyle("listgen");
        ctrlist.setTitleStyle("tableheader");
        ctrlist.setCellStyle("cellStyle");
        ctrlist.setHeaderStyle("tableheader");
        
        ctrlist.addHeader("No", "200px");
        ctrlist.addHeader("Date", "200px");
        ctrlist.addHeader("Police No", "200px");
        ctrlist.addHeader("KM", "200px");
        ctrlist.addHeader("Liter", "200px");
        ctrlist.addHeader("Driver", "200px");
        ctrlist.addHeader("SPBU", "200px");
        ctrlist.addHeader("STL", "200px");
        ctrlist.addHeader("Amount", "200px");
        ctrlist.addHeader("Action", "200px");
        if (iCommand != Command.EDIT) {
            ctrlist.setLinkRow(0);
        }
        ctrlist.setLinkSufix("");
        Vector lstData = ctrlist.getData();
        Vector lstLinkData = ctrlist.getLinkData();

       // ctrlist.setLinkPrefix("javascript:cmdEdit('");

        //ctrlist.setLinkSufix("')");

        ctrlist.reset();

        int index = -1;
        int count=0;
        Vector rowx = new Vector(1, 1);
        for (int i = 0; i < objectClass.size(); i++) {
            FuelUse fuelUse = (FuelUse) objectClass.get(i);
            count=count+1;
            rowx = new Vector(1, 1);
            rowx.add("" + count);
            rowx.add("" + fuelUse.getTgl());
            rowx.add("" + fuelUse.getNpolcode());
            rowx.add("" + fuelUse.getKm());
            rowx.add("" + fuelUse.getLitter());
            rowx.add("" + fuelUse.getDrvcode());
            rowx.add("" + fuelUse.getSpbucode());
            rowx.add("" + fuelUse.getStl());
            rowx.add("" + FRMHandler.userFormatStringDecimal(fuelUse.getHarga()));
            int full=0;
            if(fuelUse.getStl().equals("true")){
                full=1;
            }
            rowx.add("<center><button type=\"button\" class=\"btn btn-success\" onclick=\"javascript:cmdEdit('"+fuelUse.getOID()+"','"+fuelUse.getTgl()+"','"+fuelUse.getLitter()+"','"+fuelUse.getKm()+"','"+full+"','"+fuelUse.getNpol()+"','"+fuelUse.getSpbu()+"','"+fuelUse.getDrv()+"','"+fuelUse.getHarga()+"')\"></i> Edit </button></center>");

            lstData.add(rowx);
            //lstLinkData.add(String.valueOf(fuelUse.getOID())+"','"+fuelUse.getTgl()+"','"+fuelUse.getLitter()+"','"+fuelUse.getKm()+"','"+full+"','"+fuelUse.getNpol()+"','"+fuelUse.getSpbu()+"','"+fuelUse.getDrv()+"','"+fuelUse.getHarga()+"'");
        }
        return ctrlist.drawBootstrapStrip();
    }
%>

<% //proses yang tidak akan ditampilkan hanya diserver side

    int iCommand = FRMQueryString.requestCommand(request);
    int start = FRMQueryString.requestInt(request, "start");
    int prevCommand = FRMQueryString.requestInt(request, "prev_command");
    long oidFullUse = FRMQueryString.requestLong(request, "hidden_full_use_id");
    String gradeDelete = FRMQueryString.requestString(request, "gradeDelete");
    String startDate = FRMQueryString.requestString(request, "tanggalStart");
    String endDate = FRMQueryString.requestString(request, "tanggalEnd");

    /*variable declaration*/

    int recordToGet = 15;
    String msgString = "";
    int iErrCode = FRMMessage.NONE;
    String whereClause = "";
    String orderClause = PstFuelUse.fieldNames[PstFuelUse.FLD_ID];
    CtrlFuelUse ctrlFuelUse = new CtrlFuelUse(request);
    ControlLine ctrLine = new ControlLine();
    Vector listFullUse = new Vector(1, 1);

    iErrCode = ctrlFuelUse.action(iCommand, oidFullUse, request);

    FrmFuelUse frmFuelUse = ctrlFuelUse.getForm();

    FuelUse fuelUse = ctrlFuelUse.getFuelUse();
    
    if ((iCommand == Command.DELETEALL ||iCommand == Command.DELETE) && iErrCode == FRMMessage.NONE) {
        iCommand = Command.NONE;
    }
    msgString = ctrlFuelUse.getMessage();

    Date newDay = new Date();

    if (endDate.equals("")) {
        endDate = Formater.formatDate(newDay, "yyyy-MM-dd");
    }

    if (startDate.equals("")) {
        newDay.setDate(1);
        startDate = Formater.formatDate(newDay, "yyyy-MM-dd");
    }

    String where = " bb.tgl BETWEEN '" + startDate + "' AND '" + endDate + "' ORDER BY bb.id ASC ";
    listFullUse = PstFuelUse.listJoin(0, 0, where, "");

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
                document.driver.command.value="2";
                document.driver.hidden_full_use_id.value="0";
                document.driver.<%=frmFuelUse.fieldNames[frmFuelUse.FRM_FIELD_TGL]%>.value="";
                document.driver.<%=frmFuelUse.fieldNames[frmFuelUse.FRM_FIELD_LITTER]%>.value="";
                document.driver.<%=frmFuelUse.fieldNames[frmFuelUse.FRM_FIELD_KM]%>.value="";
                //document.driver.<%=frmFuelUse.fieldNames[frmFuelUse.FRM_FIELD_STL]%>.value="";
                document.driver.<%=frmFuelUse.fieldNames[frmFuelUse.FRM_FIELD_NPOL]%>.value="";
                document.driver.<%=frmFuelUse.fieldNames[frmFuelUse.FRM_FIELD_SPBU]%>.value="";
                document.driver.<%=frmFuelUse.fieldNames[frmFuelUse.FRM_FIELD_DRV]%>.value="";
                document.driver.<%=frmFuelUse.fieldNames[frmFuelUse.FRM_FIELD_HARGA]%>.value="";
                document.getElementById('togglee').style.visibility = 'hidden';
                $(document).ready(function(){
                    $('#compose-modal').modal('show');
                });
            }
            
            function cmdEdit(oid,tgl,liter,km,stl,npol,spbu,drv,harga){
                document.driver.command.value="<%=Command.EDIT%>";
                document.driver.hidden_full_use_id.value=oid;
                document.driver.<%=frmFuelUse.fieldNames[frmFuelUse.FRM_FIELD_TGL]%>.value=tgl;
                document.driver.<%=frmFuelUse.fieldNames[frmFuelUse.FRM_FIELD_LITTER]%>.value=liter;
                document.driver.<%=frmFuelUse.fieldNames[frmFuelUse.FRM_FIELD_KM]%>.value=km;
                //document.driver.<%=frmFuelUse.fieldNames[frmFuelUse.FRM_FIELD_STL]%>.value=stl;
                document.driver.<%=frmFuelUse.fieldNames[frmFuelUse.FRM_FIELD_NPOL]%>.value=npol;
                document.driver.<%=frmFuelUse.fieldNames[frmFuelUse.FRM_FIELD_SPBU]%>.value=spbu;
                document.driver.<%=frmFuelUse.fieldNames[frmFuelUse.FRM_FIELD_DRV]%>.value=drv;
                document.driver.<%=frmFuelUse.fieldNames[frmFuelUse.FRM_FIELD_HARGA]%>.value=harga;
                document.getElementById('togglee').style.visibility = 'visible';
                $(document).ready(function(){
                    $('#compose-modal').modal('show');
                });
            }
            
            function cmdSave(){
                var oidSave = document.driver.hidden_full_use_id.value;
                if(oidSave==0){
                    document.driver.command.value="<%=Command.SAVE%>";
                    document.driver.hidden_full_use_id.value="0";
                    document.driver.prev_command.value="<%=prevCommand%>";
                    //document.driver.action="fuel_use.jsp";
                    //document.driver.submit();
                }else{

                    document.driver.command.value="<%=Command.SAVE%>";
                    document.driver.prev_command.value="<%=prevCommand%>";
                    //document.driver.action="fuel_use.jsp";
                    //document.driver.submit();
                }
                
            }
            function cmdDelete()
            {
                if (confirm('Yakin Menghapus Data Fuel Use ? ')) {
                    document.driver.command.value="<%=Command.DELETE%>";
                    document.driver.action="fuel_use.jsp";
                    document.driver.submit();
                }
               
            }
            function cmdSearch(){
                document.driver.command.value="<%=Command.SEARCH%>";
                document.driver.hidden_full_use_id.value="0";
                document.driver.prev_command.value="<%=prevCommand%>";
                document.driver.action="fuel_use.jsp";
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
                        <small>Fuel Use List</small>
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
                                    <%=drawList(iCommand, listFullUse, oidFullUse, frmFuelUse)%>
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
                                    if (iCommand == Command.NONE) {%>
                                    <button  onclick="javascript:cmdAdd()" type="button" class="btn btn-primary">Add New</button>
                                    <%} else if (iCommand == Command.ADD || iCommand == Command.EDIT) {%>
                                    <button  onclick="javascript:cmdAdd()" type="button" class="btn btn-primary">Add New</button>
                                    <%} else if (iCommand == Command.CANCEL && iCommand == Command.NONE) {%>
                                    <button  onclick="javascript:cmdAdd()" type="button" class="btn btn-primary">Add New</button>
                                    <%} else if (iCommand == Command.SAVE && iErrCode == 0) {%>
                                    <div class="alert alert-success alert-dismissable">
                                        <i class="fa fa-check"></i>
                                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                        <b><%=msgString%></b>
                                    </div>
                                    <button  onclick="javascript:cmdAdd()" type="button" class="btn btn-primary">Add New</button>
                                    <%} else if (iCommand == Command.DELETE) {%>
                                    <div class="alert alert-danger alert-dismissable">
                                        <i class="fa fa-ban"></i>
                                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                        <b><%=msgString%></b>
                                        <button  onclick="javascript:cmdAdd()" type="button" class="btn btn-primary">Add New</button>
                                    </div>
                                    <%} else {%>
                                    <button  onclick="javascript:cmdAdd()" type="button" class="btn btn-primary">Add New</button>
                                    <%}%>
                                </div>
                            </div>
                        </div>
                        <div class="modal fade" id="compose-modal" tabindex="-1" role="dialog" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        <h4 class="modal-title"></i>Input Fuel Use</h4>
                                    </div>
                                    <div class="modal-body">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <span class="input-group-addon">Date</span>
                                                    <input type="hidden" name="hidden_full_use_id" value="<%=oidFullUse%>">
                                                    <input name="<%=frmFuelUse.fieldNames[frmFuelUse.FRM_FIELD_TGL]%>" id="datepickertiga" type="text" class="form-control" placeholder="yyyy-MM-dd" value="<%=startDate%>" required>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <span class="input-group-addon">Liter</span>
                                                    <input name="<%=frmFuelUse.fieldNames[frmFuelUse.FRM_FIELD_LITTER]%>" type="text" class="form-control" placeholder="Liter" required>
                                                </div>
                                            </div>    
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <span class="input-group-addon">KM</span>
                                                    <input name="<%=frmFuelUse.fieldNames[frmFuelUse.FRM_FIELD_KM]%>" type="text" class="form-control" placeholder="Km" required>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <div class="checkbox">
                                                    <label>
                                                        <input type="checkbox" name="stl" value="1" > STL
                                                    </label>
                                                </div>
                                            </div>    
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <span class="input-group-addon">Police No:</span>
                                                    <%
                                                        Vector car_key = new Vector();
                                                        Vector car_value = new Vector();
                                                        Vector listVehicle = new Vector();
                                                        listVehicle = PstVehicle.listAll();
                                                        if (listVehicle != null && listVehicle.size() > 0) {
                                                            for (int i = 0; i < listVehicle.size(); i++) {
                                                                Vehicle obj = (Vehicle) listVehicle.get(i);
                                                                car_value.add(obj.getNopol());
                                                                car_key.add("" + obj.getOID());
                                                            }
                                                        }
                                                    %>
                                                    <%=ControlCombo.drawBoostrap(frmFuelUse.fieldNames[frmFuelUse.FRM_FIELD_NPOL], null, "", car_key, car_value, "form-control")%>
                                                </div>
                                            </div> 
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <span class="input-group-addon">SPBU </span>
                                                    <%
                                                        Vector spbu_key = new Vector();
                                                        Vector spbu_value = new Vector();
                                                        Vector listSpbu = new Vector();
                                                        listSpbu = PstSpbu.listAll();
                                                        if (listSpbu != null && listSpbu.size() > 0) {
                                                            for (int i = 0; i < listSpbu.size(); i++) {
                                                                Spbu obj = (Spbu) listSpbu.get(i);
                                                                spbu_value.add(obj.getName());
                                                                spbu_key.add("" + obj.getOID());
                                                            }
                                                        }
                                                    %>
                                                    <%=ControlCombo.drawBoostrap(frmFuelUse.fieldNames[frmFuelUse.FRM_FIELD_SPBU], null, "", spbu_key, spbu_value, "form-control")%>
                                                </div>
                                            </div> 
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <span class="input-group-addon">Driver</span>
                                                    <%
                                                            Vector carDriver_key = new Vector();
                                                            Vector carDriver_value = new Vector();
                                                            Vector listDriver = new Vector();
                                                            listDriver = PstDriver.listAll();
                                                            if (listDriver != null && listDriver.size() > 0) {
                                                                for (int i = 0; i < listDriver.size(); i++) {
                                                                    Driver obj = (Driver) listDriver.get(i);
                                                                    carDriver_value.add(obj.getNama());
                                                                    carDriver_key.add("" + obj.getOID());
                                                                }
                                                            }
                                                    %>
                                                    <%=ControlCombo.drawBoostrap(frmFuelUse.fieldNames[frmFuelUse.FRM_FIELD_DRV], null, "", carDriver_key, carDriver_value, "form-control")%>
                                                </div>
                                            </div>  
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <span class="input-group-addon">Amount (Rp) </span>
                                                    <input name="<%=frmFuelUse.fieldNames[frmFuelUse.FRM_FIELD_HARGA]%>" type="text" class="form-control" placeholder="0.00" required>
                                                </div>
                                            </div> 
                                        </div>           
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="modal-footer clearfix">
                                                 <input type="submit" value="Save" class="btn btn-primary pull-left" onclick="javascript:cmdSave()" >
                                                <button type="button" class="btn btn-danger pull-left" onclick="javascript:cmdDelete()" id="togglee" ></i> Delete</button>
                                            </div>
                                        </div>   
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
