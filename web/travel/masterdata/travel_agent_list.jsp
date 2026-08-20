<!DOCTYPE html>


<%@page import="com.dimata.travel.entity.travelagent.TravelAgent"%>
<%@page import="com.dimata.travel.form.travelagent.FrmTravelAgent"%>
<%@page import="com.dimata.travel.form.travelagent.CtrlTravelAgent"%>
<%@page import="com.dimata.travel.entity.travelagent.PstTravelAgent"%>
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

    public String drawList(int iCommand, Vector objectClass, long gradeId, FrmTravelAgent frmObject) {

        ControlList ctrlist = new ControlList();

        ctrlist.setAreaWidth("100%");
        ctrlist.setListStyle("listgen");
        ctrlist.setTitleStyle("tableheader");
        ctrlist.setCellStyle("cellStyle");
        ctrlist.setHeaderStyle("tableheader");
        ctrlist.addHeader("No", "200px");
        ctrlist.addHeader("Travel Agent Name", "200px");
        ctrlist.addHeader("Disc", "200px");
        ctrlist.addHeader("Rate", "200px");
        ctrlist.addHeader("Phone No.", "200px");
        ctrlist.addHeader("Fax No.", "200px");
        ctrlist.addHeader("Action", "200px");
        //ctrlist.addHeader("", "200px"); 

        if (iCommand != Command.EDIT) {
            //membuat link dirow 0
            ctrlist.setLinkRow(0);
        }
        ctrlist.setLinkSufix("");
        Vector lstData = ctrlist.getData();
        Vector lstLinkData = ctrlist.getLinkData();

        ctrlist.setLinkSufix("')");

        ctrlist.reset();

        int index = -1;

        Vector rowx = new Vector(1, 1);
        int count = 0;
        for (int i = 0; i < objectClass.size(); i++) {
            TravelAgent travelAgent = (TravelAgent) objectClass.get(i);
            count=count+1;
            rowx = new Vector(1, 1);
            rowx.add("" + count);
            rowx.add("" + travelAgent.getName());
            rowx.add("" + travelAgent.getDisc());
            rowx.add("" + travelAgent.getKurts());
            rowx.add("" + travelAgent.getContact());
            rowx.add("" + travelAgent.getFax());
            //oid,name,alamat,telp,fax,contact,hp,disc,kurts,curr
            rowx.add("<center><button type=\"button\" class=\"btn btn-success\" onclick=\"javascript:cmdEdit('" + travelAgent.getOID() + "','"+travelAgent.getName()+"','"+travelAgent.getAlamat()+"','"+travelAgent.getTelp()+"','"+travelAgent.getFax()+"','"+travelAgent.getContact()+"','"+travelAgent.getHpcontact()+"','"+travelAgent.getDisc()+"','"+travelAgent.getKurts()+"','"+travelAgent.getCurrency()+"')\"></i> Edit </button></center>");

            lstData.add(rowx);
        }
        return ctrlist.drawBootstrapStrip();
    }
%>

<% //proses yang tidak akan ditampilkan hanya diserver side

    int iCommand = FRMQueryString.requestCommand(request);
    int start = FRMQueryString.requestInt(request, "start");
    int prevCommand = FRMQueryString.requestInt(request, "prev_command");
    long oidtravel = FRMQueryString.requestLong(request, "hidden_travel_id");
    String gradeDelete = FRMQueryString.requestString(request, "gradeDelete");

    /*variable declaration*/

    int recordToGet = 15;
    String msgString = "";
    int iErrCode = FRMMessage.NONE;
    String whereClause = "";
    String orderClause = PstTravelAgent.fieldNames[PstTravelAgent.FLD_ID];
    CtrlTravelAgent ctrlTravelAgent = new CtrlTravelAgent(request);
    ControlLine ctrLine = new ControlLine();
    Vector listTravel = new Vector(1, 1);

    /*switch statement */

    iErrCode = ctrlTravelAgent.action(iCommand, oidtravel);

    FrmTravelAgent frmTravelAgent = ctrlTravelAgent.getForm();

    //membuat command menjadi none kembali setelah menjalankan delete all
    if (iCommand == Command.DELETEALL && iErrCode == FRMMessage.NONE) {
        iCommand = Command.NONE;
    }

    /* end switch*/

    int vectSize = PstTravelAgent.getCount(whereClause);

    /*switch list Student*/

    if (iCommand == Command.FIRST || iCommand == Command.NEXT || iCommand == Command.PREV || iCommand == Command.LAST) {
        start = 0;//ctrlTravelAgent.action(iCommand, start, vectSize, recordToGet);
    }

    /* end switch list*/

    TravelAgent travelAgent = ctrlTravelAgent.getTravelAgent();
    msgString = ctrlTravelAgent.getMessage();

    /* get record to display */

    listTravel = PstTravelAgent.listAll();
%>

<html>
    <head>
        <meta charset="UTF-8">
        <title>AdminLTE | Data Tables</title>
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
                document.travelList.command.value="<%=Command.ADD%>";
                document.travelList.hidden_travel_id.value="0";
                document.travelList.<%=frmTravelAgent.fieldNames[frmTravelAgent.FRM_FIELD_NAME]%>.value="";
                document.travelList.<%=frmTravelAgent.fieldNames[frmTravelAgent.FRM_FIELD_ALAMAT]%>.value="";
                document.travelList.<%=frmTravelAgent.fieldNames[frmTravelAgent.FRM_FIELD_TELP]%>.value="";
                document.travelList.<%=frmTravelAgent.fieldNames[frmTravelAgent.FRM_FIELD_FAX]%>.value="";
                document.travelList.<%=frmTravelAgent.fieldNames[frmTravelAgent.FRM_FIELD_CONTACT]%>.value="";
                document.travelList.<%=frmTravelAgent.fieldNames[frmTravelAgent.FRM_FIELD_HPCONTACT]%>.value="";
                document.travelList.<%=frmTravelAgent.fieldNames[frmTravelAgent.FRM_FIELD_DISC]%>.value="";
                document.travelList.<%=frmTravelAgent.fieldNames[frmTravelAgent.FRM_FIELD_KURTS]%>.value="";
                document.travelList.<%=frmTravelAgent.fieldNames[frmTravelAgent.FRM_FIELD_CURRENCY]%>.value="";
                document.getElementById('togglee').style.visibility = 'hidden';
                $(document).ready(function(){
                    $('#compose-modal').modal('show');
                });
            }
            
            function cmdEdit(oid,name,alamat,telp,fax,contact,hp,disc,kurts,curr){
                document.travelList.command.value="<%=Command.EDIT%>";
                document.travelList.hidden_travel_id.value=oid;
                document.travelList.<%=frmTravelAgent.fieldNames[frmTravelAgent.FRM_FIELD_NAME]%>.value=name;
                document.travelList.<%=frmTravelAgent.fieldNames[frmTravelAgent.FRM_FIELD_ALAMAT]%>.value=alamat;
                document.travelList.<%=frmTravelAgent.fieldNames[frmTravelAgent.FRM_FIELD_TELP]%>.value=telp;
                document.travelList.<%=frmTravelAgent.fieldNames[frmTravelAgent.FRM_FIELD_FAX]%>.value=fax;
                document.travelList.<%=frmTravelAgent.fieldNames[frmTravelAgent.FRM_FIELD_CONTACT]%>.value=contact;
                document.travelList.<%=frmTravelAgent.fieldNames[frmTravelAgent.FRM_FIELD_HPCONTACT]%>.value=hp;
                document.travelList.<%=frmTravelAgent.fieldNames[frmTravelAgent.FRM_FIELD_DISC]%>.value=disc;
                document.travelList.<%=frmTravelAgent.fieldNames[frmTravelAgent.FRM_FIELD_KURTS]%>.value=kurts;
                document.travelList.<%=frmTravelAgent.fieldNames[frmTravelAgent.FRM_FIELD_CURRENCY]%>.value=curr;
                document.getElementById('togglee').style.visibility = 'visible';
                $(document).ready(function(){
                    $('#compose-modal').modal('show');
                });
            }
            
            function cmdDelete()
            {
                if (confirm('Yakin Menghapus Data Agent ? ')) {
                    document.travelList.command.value="<%=Command.DELETE%>";
                    document.travelList.action="travel_agent_list.jsp";
                    document.travelList.submit();
                }
               
            }
            function cmdSave(){
                var oidDriver = document.travelList.hidden_travel_id.value;
                if(oidDriver!=0){
                    document.travelList.command.value="<%=Command.SAVE%>";
                    //document.travelList.action="travel_agent_list.jsp";
                    //document.travelList.submit();
                }else{
                    document.travelList.command.value="<%=Command.SAVE%>";
                    document.travelList.hidden_travel_id.value="0";
                    //document.travelList.action="travel_agent_list.jsp";
                    //document.travelList.submit();
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
                        <small>Travel Agent List</small>
                    </h1>
                    <!--<ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li><a href="#">Tables</a></li>
                        <li class="active">Data tables</li>
                    </ol> -->
                </section>
                <!-- Main content -->
                <section class="content">
                    <form name="travelList"  method ="post" action="" role="form">
                        <input type="hidden" name="command" value="<%=iCommand%>">
                        <input type="hidden" name="hidden_travel_id" value="<%=oidtravel%>"> 
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
                                    <%=drawList(iCommand, listTravel, oidtravel, frmTravelAgent)%>
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
                                    <%} else if (iCommand == Command.SAVE && iErrCode != 0) {%>  
                                    <div class="alert alert-success alert-dismissable">
                                        <i class="fa fa-check"></i>
                                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                        <b><%=msgString%></b>
                                    </div>
                                    <button  onclick="javascript:cmdAdd()" type="button" class="btn btn-primary">Add New</button>
                                    <%} else if (iCommand == Command.SAVE && iErrCode == 0) {%>
                                    <div class="alert alert-success alert-dismissable">
                                        <i class="fa fa-check"></i>
                                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                        <b><%=msgString%></b>
                                    </div>
                                    <button  onclick="javascript:cmdAdd()" type="submit" class="btn btn-primary">Add New</button>
                                    <%} else if (iCommand == Command.DELETE) {%>
                                    <div class="alert alert-danger alert-dismissable">
                                        <i class="fa fa-ban"></i>
                                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                        <b><%=msgString%></b>
                                    </div>
                                    <button  onclick="javascript:cmdAdd()" type="submit" class="btn btn-primary">Add New</button>
                                    <%}%>
                                </div>
                            </div>
                        </div>
                        <div class="modal fade" id="compose-modal" tabindex="-1" role="dialog" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        <h4 class="modal-title"></i>Input Travel Agent</h4>
                                    </div>
                                    <div class="modal-body">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <span class="input-group-addon">Agent Name</span>
                                                    <input name="<%=frmTravelAgent.fieldNames[frmTravelAgent.FRM_FIELD_NAME]%>" type="text" class="form-control" placeholder="Agent Name" required>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <span class="input-group-addon">Address</span>
                                                    <textarea id="editor1" name="<%=frmTravelAgent.fieldNames[frmTravelAgent.FRM_FIELD_ALAMAT]%>" rows="2" cols="53" placeholder="Address"></textarea>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <span class="input-group-addon">Phone No.</span>
                                                    <input name="<%=frmTravelAgent.fieldNames[frmTravelAgent.FRM_FIELD_TELP]%>" type="text" class="form-control" placeholder="Phone Number" required>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <span class="input-group-addon">Fax No.</span>
                                                    <input name="<%=frmTravelAgent.fieldNames[frmTravelAgent.FRM_FIELD_FAX]%>" type="text" class="form-control" placeholder="Fax Number" required>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <span class="input-group-addon">Contact Name</span>
                                                    <input name="<%=frmTravelAgent.fieldNames[frmTravelAgent.FRM_FIELD_CONTACT]%>" type="text" class="form-control" placeholder="Contact Name" required>
                                                </div>
                                            </div> 
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <span class="input-group-addon">Mobile No</span>
                                                    <input name="<%=frmTravelAgent.fieldNames[frmTravelAgent.FRM_FIELD_HPCONTACT]%>" type="text" class="form-control" placeholder="Mobile No" required>
                                                </div>
                                            </div> 
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <span class="input-group-addon">Discount</span>
                                                    <input name="<%=frmTravelAgent.fieldNames[frmTravelAgent.FRM_FIELD_DISC]%>" type="text" class="form-control" placeholder="Discount" required>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <span class="input-group-addon">Currency Rate</span>
                                                    <input name="<%=frmTravelAgent.fieldNames[frmTravelAgent.FRM_FIELD_KURTS]%>" type="text" class="form-control" placeholder="0.00" required>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <span class="input-group-addon">Currency</span>
                                                    <%
                                                        Vector carType_key = new Vector();
                                                        Vector carType_value = new Vector();
                                                        carType_value.add("RP");
                                                        carType_key.add("RP");
                                                        carType_value.add("USD");
                                                        carType_key.add("USD");
                                                    %>
                                                    <%=ControlCombo.drawBoostrap(frmTravelAgent.fieldNames[frmTravelAgent.FRM_FIELD_CURRENCY], null, "", carType_key, carType_value, "form-control")%>
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
        <script src="../../styles/jquery.min.js"></script>
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