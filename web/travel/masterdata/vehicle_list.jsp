<%@page import="com.dimata.travel.entity.vehicletype.VehicleType"%>
<%@page import="com.dimata.travel.entity.vehicletype.PstVehicleType"%>
<%@page import="com.dimata.travel.entity.driver.Driver"%>
<%@page import="com.dimata.travel.entity.driver.PstDriver"%>
<!DOCTYPE html>

<%@page import="com.dimata.travel.entity.vehicle.Vehicle"%>
<%@page import="com.dimata.travel.form.vehicle.FrmVehicle"%>
<%@page import="com.dimata.travel.form.vehicle.CtrlVehicle"%>
<%@page import="com.dimata.travel.entity.vehicle.PstVehicle"%>
<%-- 
    Document   : driver_list
    Created on : Apr 27, 2015, 2:27:27 PM
    Author     : dimata005
--%>
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

    public String drawList(int iCommand, Vector objectClass, long gradeId, FrmVehicle frmObject) {

        ControlList ctrlist = new ControlList();

        ctrlist.setAreaWidth("100%");
        ctrlist.setListStyle("listgen");
        ctrlist.setTitleStyle("tableheader");
        ctrlist.setCellStyle("cellStyle");
        ctrlist.setHeaderStyle("tableheader");
        ctrlist.addHeader("No", "200px");
        ctrlist.addHeader("No Polisi", "200px");
        ctrlist.addHeader("Driver", "200px");
        ctrlist.addHeader("Type", "200px");
        ctrlist.addHeader("Seat", "200px");
        ctrlist.addHeader("LKO", "200px");
        ctrlist.addHeader("Year", "200px");
        ctrlist.addHeader("Merk", "200px");
        ctrlist.addHeader("Kir", "200px");
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

        ctrlist.setLinkSufix("')");

        ctrlist.reset();

        int index = -1;

        Vector rowx = new Vector(1, 1);
        int count = 0;
        for (int i = 0; i < objectClass.size(); i++) {
            Vehicle vehicle = (Vehicle) objectClass.get(i);
            count = count + 1;
            rowx = new Vector(1, 1);
            rowx.add("" + count);
            rowx.add("" + vehicle.getNopol());
            rowx.add("" + vehicle.getCodedriver());
            rowx.add("" + vehicle.getCodetype());
            rowx.add("" + vehicle.getSeat());
            rowx.add("" + vehicle.getLko());
            rowx.add("" + vehicle.getTahun());
            rowx.add("" + vehicle.getMerek());
            rowx.add("" + vehicle.getKir());
            //oid,nopol,jenis,seat,merk,lko,kir,samsat,bb,tahun
            rowx.add("<center><button type=\"button\" class=\"btn btn-success\" onclick=\"javascript:cmdEdit('" + vehicle.getOID() + "','" + vehicle.getNopol() + "','" + vehicle.getJenis() + "','" + vehicle.getSeat() + "','" + vehicle.getMerek() + "','" + vehicle.getLko() + "','" + vehicle.getKir() + "','" + vehicle.getSamsat() + "','" + vehicle.getBb() + "','" + vehicle.getTahun() + "','" + vehicle.getDriver() + "')\"></i> Edit </button></center>");

            lstData.add(rowx);
        }
        return ctrlist.drawBootstrapStrip();
    }
%>

<% //proses yang tidak akan ditampilkan hanya diserver side

    int iCommand = FRMQueryString.requestCommand(request);
    int start = FRMQueryString.requestInt(request, "start");
    int prevCommand = FRMQueryString.requestInt(request, "prev_command");
    long oidVehicle = FRMQueryString.requestLong(request, "hidden_vehicle_id");
    String gradeDelete = FRMQueryString.requestString(request, "gradeDelete");

    /*variable declaration*/

    int recordToGet = 15;
    String msgString = "";
    int iErrCode = FRMMessage.NONE;
    String whereClause = "";
    String orderClause = PstVehicle.fieldNames[PstVehicle.FLD_ID];
    CtrlVehicle ctrlVehicle = new CtrlVehicle(request);
    ControlLine ctrLine = new ControlLine();
    Vector listVehicle = new Vector(1, 1);

    /*switch statement */

    iErrCode = ctrlVehicle.action(iCommand, oidVehicle, request);

    FrmVehicle frmVehicle = ctrlVehicle.getForm();

    //membuat command menjadi none kembali setelah menjalankan delete all
    if (iCommand == Command.DELETE && iErrCode == FRMMessage.NONE) {
        iCommand = Command.NONE;
        oidVehicle = 0;
    }

    if (iCommand == Command.FIRST || iCommand == Command.NEXT || iCommand == Command.PREV || iCommand == Command.LAST) {
        start = 0;
    }

    /* end switch list*/
    Date newDay = new Date();
    String startDate = "";
    if (startDate.equals("")) {
        startDate = Formater.formatDate(newDay, "yyyy-MM-dd");
    }

    Vehicle vehicle = ctrlVehicle.getVehicle();
    msgString = ctrlVehicle.getMessage();

    listVehicle = PstVehicle.listJoin(0, 0, "", "");
%>

<html>
    <head>
        <meta charset="UTF-8">
        <title>AdminLTE | Data Tables</title>
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
                document.vehicleList.command.value="<%=Command.ADD%>";
                document.vehicleList.hidden_vehicle_id.value="0";
                document.vehicleList.<%=frmVehicle.fieldNames[frmVehicle.FRM_FIELD_NOPOL]%>.value="";
                document.vehicleList.<%=frmVehicle.fieldNames[frmVehicle.FRM_FIELD_DRIVER]%>.value="";
                document.vehicleList.<%=frmVehicle.fieldNames[frmVehicle.FRM_FIELD_JENIS]%>.value="";
                document.vehicleList.<%=frmVehicle.fieldNames[frmVehicle.FRM_FIELD_SEAT]%>.value="";
                document.vehicleList.<%=frmVehicle.fieldNames[frmVehicle.FRM_FIELD_MEREK]%>.value="";
                document.vehicleList.<%=frmVehicle.fieldNames[frmVehicle.FRM_FIELD_LKO]%>.checked="";
                document.vehicleList.<%=frmVehicle.fieldNames[frmVehicle.FRM_FIELD_KIR]%>.value="";
                document.vehicleList.<%=frmVehicle.fieldNames[frmVehicle.FRM_FIELD_SAMSAT]%>.value="";
                document.vehicleList.<%=frmVehicle.fieldNames[frmVehicle.FRM_FIELD_BB]%>.value="";
                document.vehicleList.<%=frmVehicle.fieldNames[frmVehicle.FRM_FIELD_TAHUN]%>.value="";
                
                document.getElementById('togglee').style.visibility = 'hidden';
                $(document).ready(function(){
                    $('#compose-modal').modal('show');
                });
            }
            function cmdSave(){
                var oidDriver = document.vehicleList.hidden_vehicle_id.value;
                if(oidDriver!=0){
                    document.vehicleList.command.value="<%=Command.SAVE%>";
                    //document.vehicleList.action="vehicle_list.jsp";
                    //document.vehicleList.submit();
                }else{
                    document.vehicleList.command.value="<%=Command.SAVE%>";
                    document.vehicleList.hidden_vehicle_id.value="0";
                    //document.vehicleList.action="vehicle_list.jsp";
                    //document.vehicleList.submit();
                }
            }
            
            function cmdEdit(oid,nopol,jenis,seat,merk,lko,kir,samsat,bb,tahun,driver){
                document.vehicleList.command.value="<%=Command.EDIT%>";
                document.vehicleList.hidden_vehicle_id.value=oid;
                document.vehicleList.<%=frmVehicle.fieldNames[frmVehicle.FRM_FIELD_NOPOL]%>.value=nopol;
                document.vehicleList.<%=frmVehicle.fieldNames[frmVehicle.FRM_FIELD_DRIVER]%>.value=driver;
                document.vehicleList.<%=frmVehicle.fieldNames[frmVehicle.FRM_FIELD_JENIS]%>.value=jenis;
                document.vehicleList.<%=frmVehicle.fieldNames[frmVehicle.FRM_FIELD_SEAT]%>.value=seat;
                document.vehicleList.<%=frmVehicle.fieldNames[frmVehicle.FRM_FIELD_MEREK]%>.value=merk;
                document.vehicleList.<%=frmVehicle.fieldNames[frmVehicle.FRM_FIELD_LKO]%>.checked=lko;
                document.vehicleList.<%=frmVehicle.fieldNames[frmVehicle.FRM_FIELD_KIR]%>.value=kir;
                document.vehicleList.<%=frmVehicle.fieldNames[frmVehicle.FRM_FIELD_SAMSAT]%>.value=samsat;
                document.vehicleList.<%=frmVehicle.fieldNames[frmVehicle.FRM_FIELD_BB]%>.value=bb;
                document.vehicleList.<%=frmVehicle.fieldNames[frmVehicle.FRM_FIELD_TAHUN]%>.value=tahun;
                document.getElementById('togglee').style.visibility = 'visible';
                $(document).ready(function(){
                    $('#compose-modal').modal('show');
                });
            }
            
             function cmdDelete()
            {
                if (confirm('Yakin Menghapus Data Vehicle ? ')) {
                    document.vehicleList.command.value="<%=Command.DELETE%>";
                    document.vehicleList.action="vehicle_list.jsp";
                    document.vehicleList.submit();
                }
               
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
                        <small>Vehicle List</small>
                    </h1>
                </section>
                <!-- Main content -->
                <section class="content">
                    <form name="vehicleList"  method ="post" action="" role="form">
                        <input type="hidden" name="command" value="<%=iCommand%>">
                        <input type="hidden" name="hidden_vehicle_id" value="<%=oidVehicle%>"> 
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="box">
                                    <div class="box-header">
                                        &nbsp;
                                    </div>
                                    <div class="box-header">
                                        <div class="col-xs-2">
                                        </div>
                                        <div class="col-xs-2">
                                        </div>
                                        <div class="col-xs-8">
                                            <button  onclick="javascript:cmdAdd()" type="button" class="btn btn-primary pull-right">Add New</button>
                                        </div>
                                    </div>
                                    <%=drawList(iCommand, listVehicle, oidVehicle, frmVehicle)%>
                                </div><!-- /.box -->
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="box-footer">
                                    <%if (iCommand == Command.NONE) {%>
                                        <button  onclick="javascript:cmdAdd()" type="button" class="btn btn-primary">Add New</button>
                                    <%} else if (iCommand == Command.ADD || iCommand == Command.EDIT) {%>
                                        <button  onclick="javascript:cmdAdd()" type="button" class="btn btn-primary">Add New</button>
                                    <%} else if (iCommand == Command.CANCEL && iCommand == Command.NONE) {%>
                                        <button  onclick="javascript:cmdAdd()" type="button" class="btn btn-primary">Add New</button>
                                    <%} else if (iCommand == Command.SAVE && iErrCode != 0) {%>
                                        <div class="alert alert-success alert-dismissable">
                                            <i class="fa fa-check"></i>
                                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                            <b><%=msgString%></b>
                                        </div>
                                        <button  onclick="javascript:cmdAdd()" type="button" class="btn btn-primary">Add New</button>
                                    <%} else if (iCommand == Command.SAVE && iErrCode == 0) {%>
                                        <button  onclick="javascript:cmdAdd()" type="button" class="btn btn-primary">Add New</button>
                                    <%} else if (iCommand == Command.DELETE) {%>
                                        <div class="alert alert-danger alert-dismissable">
                                            <i class="fa fa-ban"></i>
                                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                            <b><%=msgString%></b>
                                        </div>
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
                                        <h4 class="modal-title"></i>Input Vehicle</h4>
                                    </div>
                                    <div class="modal-body">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <span class="input-group-addon">Polive No</span>
                                                    <input name="<%=frmVehicle.fieldNames[frmVehicle.FRM_FIELD_NOPOL]%>" type="text" class="form-control" placeholder="Police Number" required>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <span class="input-group-addon">Driver Name</span>
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
                                                    <%=ControlCombo.drawBoostrap(frmVehicle.fieldNames[frmVehicle.FRM_FIELD_DRIVER], null, "", carDriver_key, carDriver_value, "form-control")%>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <span class="input-group-addon">Car Type</span>
                                                    <%
                                                        Vector carType_key = new Vector();
                                                        Vector carType_value = new Vector();
                                                        Vector listCarType = new Vector();
                                                        listCarType = PstVehicleType.listAll();
                                                        if (listCarType != null && listCarType.size() > 0) {
                                                            for (int i = 0; i < listCarType.size(); i++) {
                                                                VehicleType obj = (VehicleType) listCarType.get(i);
                                                                carType_value.add(obj.getName());
                                                                carType_key.add("" + obj.getOID());
                                                            }
                                                        }
                                                    %>
                                                    <%=ControlCombo.drawBoostrap(frmVehicle.fieldNames[frmVehicle.FRM_FIELD_JENIS], null, "", carType_key, carType_value, "form-control")%>
                                                </div>
                                            </div>     
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <span class="input-group-addon">No Of Seat</span>
                                                    <input name="<%=frmVehicle.fieldNames[frmVehicle.FRM_FIELD_SEAT]%>" type="text" class="form-control" placeholder="No Of Seat" required>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <span class="input-group-addon">Brand/Merk</span>
                                                    <input name="<%=frmVehicle.fieldNames[frmVehicle.FRM_FIELD_MEREK]%>" type="text" class="form-control" placeholder="Brand/Merk" required>
                                                </div>
                                            </div>

                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <div class="checkbox">
                                                    <label>
                                                        <input type="checkbox" name="<%=frmVehicle.fieldNames[frmVehicle.FRM_FIELD_LKO]%>" value="1" >  LKO
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <span class="input-group-addon">Date Of KIR</span>
                                                    <input name="<%=frmVehicle.fieldNames[frmVehicle.FRM_FIELD_KIR]%>" type="text" class="form-control" placeholder="yyyy-MM-dd" id="datepicker" value="<%=startDate%>" required> 
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <span class="input-group-addon">Date Of Samsat</span>
                                                    <input name="<%=frmVehicle.fieldNames[frmVehicle.FRM_FIELD_SAMSAT]%>" type="text" class="form-control" placeholder="yyyy-MM-dd" id="datepickerdua" value="<%=startDate%>" required>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <span class="input-group-addon">Bensin/Solar</span>
                                                    <input name="<%=frmVehicle.fieldNames[frmVehicle.FRM_FIELD_BB]%>" type="text" class="form-control" placeholder="B/S" maxlength="1" required>
                                                </div>
                                            </div> 
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <span class="input-group-addon">Year</span>
                                                    <input name="<%=frmVehicle.fieldNames[frmVehicle.FRM_FIELD_TAHUN]%>" type="text" class="form-control" placeholder="Year" required>
                                                </div>
                                            </div>      
                                        </div>  
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="modal-footer clearfix">
                                                <%--<button type="button" class="btn btn-primary pull-left" onclick="javascript:cmdSave()"></i> Save </button>--%>
                                                <input type="submit" value="Save" class="btn btn-primary pull-left" onclick="javascript:cmdSave()" >
                                                <button type="button" class="btn btn-danger pull-left" onclick="javascript:cmdDelete()" id="togglee"></i> Delete</button>
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
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
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