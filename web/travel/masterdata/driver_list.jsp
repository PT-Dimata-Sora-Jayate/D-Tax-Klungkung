<!DOCTYPE html>

<%@page import="com.dimata.travel.entity.driver.Driver"%>
<%@page import="com.dimata.travel.form.driver.FrmDriver"%>
<%@page import="com.dimata.travel.form.driver.CtrlDriver"%>
<%@page import="com.dimata.travel.entity.driver.PstDriver"%>
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

    public String drawList(int iCommand, Vector objectClass, long gradeId, FrmDriver frmObject) {

        ControlList ctrlist = new ControlList();

        ctrlist.setAreaWidth("100%");
        ctrlist.setListStyle("listgen");
        ctrlist.setTitleStyle("tableheader");
        ctrlist.setCellStyle("cellStyle");
        ctrlist.setHeaderStyle("tableheader");
        ctrlist.addHeader("No", "200px");
        ctrlist.addHeader("Driver Code", "200px");
        ctrlist.addHeader("Driver Name", "200px");
        ctrlist.addHeader("Phone No", "200px");
        ctrlist.addHeader("Alamat", "200px");
        ctrlist.addHeader("Type", "200px");
        ctrlist.addHeader("%", "200px");
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
            Driver driver = (Driver) objectClass.get(i);
            count = count + 1;

            rowx = new Vector(1, 1);
            rowx.add("" + count);
            rowx.add("" + driver.getCode());
            rowx.add("" + driver.getNama());
            rowx.add("" + driver.getTelp());
            rowx.add("" + driver.getAlamat());
            rowx.add("" + driver.getType());
            rowx.add("" + driver.getProsentase());
            //oid,code,telp,cbtype,nama,gaji,prosentase,alamat,note
            rowx.add("<center><button type=\"button\" class=\"btn btn-success\" onclick=\"javascript:cmdEdit('" + driver.getOID() + "','" + driver.getCode() + "','" + driver.getTelp() + "','" + driver.getCbtype() + "','" + driver.getNama() + "','" + driver.getGaji() + "','" + driver.getProsentase() + "','" + driver.getAlamat() + "','" + driver.getNote() + "')\"></i> Edit </button></center>");
            lstData.add(rowx);
            //lstLinkData.add(String.valueOf(driver.getOID()));
        }

        /*rowx = new Vector();
        if (iCommand == Command.ADD) {
        rowx.add("<div align=\"center\">" + ControlCombo.draw(FrmDriver.fieldNames[FrmDriver.FRM_FIELD_SECTION_ID], null, sel_sectionid, sectionid_key, sectionid_value, "", "formElemen") + "</div>");
        rowx.add("<input type=\"text\" name=\"" + frmObject.fieldNames[FrmDriver.FRM_FIELD_GRADE_CODE] + "\" value=\"\" class=\"fromElement\" size=\"10\">");   
        rowx.add("<input type=\"text\" name=\"" + frmObject.fieldNames[FrmDriver.FRM_FIELD_GRADE_NAME] + "\" value=\"\" class=\"fromElement\" size=\"10\">");
        }
        lstData.add(rowx);*/
        return ctrlist.drawBootstrapStrip();
    }
%>

<% //proses yang tidak akan ditampilkan hanya diserver side

    int iCommand = FRMQueryString.requestCommand(request);
    int start = FRMQueryString.requestInt(request, "start");
    int prevCommand = FRMQueryString.requestInt(request, "prev_command");
    long oidDriver = FRMQueryString.requestLong(request, "hidden_driver_id");
    String gradeDelete = FRMQueryString.requestString(request, "gradeDelete");

    /*variable declaration*/

    int recordToGet = 15;
    String msgString = "";
    int iErrCode = FRMMessage.NONE;
    String whereClause = "";
    String orderClause = PstDriver.fieldNames[PstDriver.FLD_ID];
    CtrlDriver ctrlDriver = new CtrlDriver(request);
    ControlLine ctrLine = new ControlLine();
    Vector listDriver = new Vector(1, 1);

    /*switch statement */

    iErrCode = ctrlDriver.action(iCommand, oidDriver);

    FrmDriver frmDriver = ctrlDriver.getForm();

    //membuat command menjadi none kembali setelah menjalankan delete all
    if (iCommand == Command.DELETEALL && iErrCode == FRMMessage.NONE && iCommand == Command.SAVE && iCommand == Command.DELETE) {
        iCommand = Command.NONE;
    }

    /* end switch*/

    int vectSize = PstDriver.getCount(whereClause);

    /*switch list Student*/

    if (iCommand == Command.FIRST || iCommand == Command.NEXT || iCommand == Command.PREV || iCommand == Command.LAST) {
        start = 0;//ctrlDriver.action(iCommand, start, vectSize, recordToGet);
    }

    /* end switch list*/

    Driver driver = ctrlDriver.getDriver();
    msgString = ctrlDriver.getMessage();

    /* get record to display */

    listDriver = PstDriver.list(0, 0, "", PstDriver.fieldNames[PstDriver.FLD_ID]);
%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Transport</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
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
                document.driver.command.value="<%=Command.EDIT%>";
                document.driver.hidden_driver_id.value="0";
                document.driver.<%=frmDriver.fieldNames[frmDriver.FRM_FIELD_CODE]%>.value="";
                document.driver.<%=frmDriver.fieldNames[frmDriver.FRM_FIELD_TELP]%>.value="";
                document.driver.<%=frmDriver.fieldNames[frmDriver.FRM_FIELD_CBTYPE]%>.value="";
                document.driver.<%=frmDriver.fieldNames[frmDriver.FRM_FIELD_NAMA]%>.value="";
                document.driver.<%=frmDriver.fieldNames[frmDriver.FRM_FIELD_GAJI]%>.value="";
                document.driver.<%=frmDriver.fieldNames[frmDriver.FRM_FIELD_ALAMAT]%>.value="";
                document.driver.<%=frmDriver.fieldNames[frmDriver.FRM_FIELD_NOTE]%>.value="";
                document.getElementById('togglee').style.visibility = 'hidden';
                
                $(document).ready(function(){
                    $('#compose-modal').modal('show');
                });
            }
            
            function cmdEdit(oid,code,telp,cbtype,nama,gaji,prosentase,alamat,note){
                document.driver.command.value="<%=Command.EDIT%>";
                document.driver.hidden_driver_id.value=oid;
                document.driver.<%=frmDriver.fieldNames[frmDriver.FRM_FIELD_CODE]%>.value=code;
                document.driver.<%=frmDriver.fieldNames[frmDriver.FRM_FIELD_TELP]%>.value=telp;
                document.driver.<%=frmDriver.fieldNames[frmDriver.FRM_FIELD_CBTYPE]%>.value=cbtype;
                document.driver.<%=frmDriver.fieldNames[frmDriver.FRM_FIELD_NAMA]%>.value=nama;
                document.driver.<%=frmDriver.fieldNames[frmDriver.FRM_FIELD_GAJI]%>.value=gaji;
                if(prosentase=="True"){
                    document.getElementById("myCheck").checked = true;
                }else{
                    document.getElementById("myCheck").checked = false;
                }
                document.driver.<%=frmDriver.fieldNames[frmDriver.FRM_FIELD_ALAMAT]%>.value=alamat;
                document.driver.<%=frmDriver.fieldNames[frmDriver.FRM_FIELD_NOTE]%>.value=note;
                document.getElementById('togglee').style.visibility = 'visible';
                $(document).ready(function(){
                    $('#compose-modal').modal('show');
                });
            }
            
            function cmdSave(){
                var oidDriver = document.driver.hidden_driver_id.value;
                if(oidDriver!=0){
                    document.driver.command.value="<%=Command.SAVE%>";
                    document.driver.prev_command.value="<%=prevCommand%>";
                    //document.driver.action="driver_list.jsp";
                    //document.driver.submit();
                }else{
                    document.driver.command.value="<%=Command.SAVE%>";
                    document.driver.hidden_driver_id.value="0";
                    document.driver.prev_command.value="<%=prevCommand%>";
                    //document.driver.action="driver_list.jsp";
                    //document.driver.submit();
                }
            }
            
            function cmdDelete()
            {
                if (confirm('Yakin Menghapus Data Driver ? ')) {
                    document.driver.command.value="<%=Command.DELETE%>";
                    document.driver.prev_command.value="<%=prevCommand%>";
                    document.driver.action="driver_list.jsp";
                    document.driver.submit();
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
                        <small>Driver List</small>
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
                                        <div class="col-xs-2">
                                        </div>
                                        <div class="col-xs-2">
                                        </div>
                                        <div class="col-xs-8">
                                            <button  onclick="javascript:cmdAdd()" type="button" class="btn btn-primary pull-right">Add New</button>
                                        </div>
                                    </div>
                                    <%=drawList(iCommand, listDriver, oidDriver, frmDriver)%>
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
                                    <div class="alert alert-danger alert-dismissable">
                                        <i class="fa fa-ban"></i>
                                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                        <b><%=msgString%></b>
                                        <button  onclick="javascript:cmdAdd()" type="button" class="btn btn-primary">Add New</button>
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
                                        <h4 class="modal-title"></i>Input Driver</h4>
                                    </div>
                                    <div class="modal-body">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <span class="input-group-addon">Code:</span>
                                                    <input name="<%=frmDriver.fieldNames[frmDriver.FRM_FIELD_CODE]%>" type="text" class="form-control" placeholder="Name Driver" required>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <span class="input-group-addon">Phone No :</span>
                                                    <input name="<%=frmDriver.fieldNames[frmDriver.FRM_FIELD_TELP]%>" type="text" class="form-control" placeholder="Phone Number" required>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <span class="input-group-addon">Bus/CombiType</span>
                                                    <input name="<%=frmDriver.fieldNames[frmDriver.FRM_FIELD_CBTYPE]%>" type="text" class="form-control" placeholder="B/C" maxlength="1" required>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <span class="input-group-addon">Name:</span>
                                                    <input name="<%=frmDriver.fieldNames[frmDriver.FRM_FIELD_NAMA]%>" type="text" class="form-control" placeholder="Name Driver" required>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <span class="input-group-addon">Salary / Gaji:</span>
                                                    <input name="<%=frmDriver.fieldNames[frmDriver.FRM_FIELD_GAJI]%>" type="text" class="form-control" placeholder="0.00" required >
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="checkbox">
                                                    <label>
                                                        <input type="checkbox" name="<%=frmDriver.fieldNames[frmDriver.FRM_FIELD_PROSENTASE]%>" value="1" id="myCheck" >  Prosentase
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <span >Address</span>
                                                <div class="input-group">
                                                    <textarea id="editor1" name="<%=frmDriver.fieldNames[frmDriver.FRM_FIELD_ALAMAT]%>" rows="1" cols="100"></textarea>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <span >Note</span>
                                                <div class="input-group">
                                                    <textarea id="editor1" name="<%=frmDriver.fieldNames[frmDriver.FRM_FIELD_NOTE]%>" rows="2" cols="100"></textarea>
                                                </div>
                                            </div>
                                        </div>    
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="modal-footer clearfix">
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

    </body>
</html>