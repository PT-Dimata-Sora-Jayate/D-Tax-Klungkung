<%-- 
    Document   : transport_service_order_edit
    Created on : Apr 29, 2015, 5:45:01 PM
    Author     : dimata005
--%>

<%@page import="com.dimata.travel.entity.vehicle.Vehicle"%>
<%@page import="com.dimata.travel.entity.vehicle.PstVehicle"%>
<%@page import="com.dimata.travel.entity.driver.Driver"%>
<%@page import="com.dimata.travel.entity.driver.PstDriver"%>
<%@page import="com.dimata.travel.entity.guide.Guide"%>
<%@page import="com.dimata.travel.entity.guide.PstGuide"%>
<%@page import="com.dimata.travel.form.tsoitem.FrmTransportServiceOrderItem"%>
<%@page import="com.dimata.travel.form.tsoitem.CtrlTransportServiceOrderItem"%>
<%@page import="com.dimata.travel.entity.tsoitem.TransportServiceOrderItem"%>
<%@page import="com.dimata.travel.entity.tsoitem.PstTransportServiceOrderItem"%>
<%-- 
    Document   : newjsp
    Created on : Apr 29, 2015, 4:09:46 PM
    Author     : dimata005
--%>

<%-- 
    Document   : transport_service_order_edit
    Created on : Apr 29, 2015, 3:47:55 PM
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

    public String drawList(int iCommand, Vector objectClass, long gradeId, FrmTransportServiceOrderItem frmObject, long oidTso, long ItemId) {

        ControlList ctrlist = new ControlList();

        ctrlist.setAreaWidth("100%");
        ctrlist.setListStyle("listgen");
        ctrlist.setTitleStyle("tableheader");
        ctrlist.setCellStyle("cellStyle");
        ctrlist.setHeaderStyle("tableheader");
        
        ctrlist.addHeader("No", "5px");
        ctrlist.addHeader("Date", "110px");
        ctrlist.addHeader("Order No", "100px");
        ctrlist.addHeader("Pax", "40px");
        ctrlist.addHeader("RQS", "50px");
        ctrlist.addHeader("Descriptions", "100px");
        ctrlist.addHeader("Police Number", "100px");
        ctrlist.addHeader("Driver Name", "100px");
        ctrlist.addHeader("Guide", "100px");
        ctrlist.addHeader("Start", "50px");
        ctrlist.addHeader("Curr", "50px");
        ctrlist.addHeader("Amount", "100px");
        ctrlist.addHeader("Note", "100px");
        if (iCommand != Command.EDIT) {
            ctrlist.setLinkRow(0);
        }
        ctrlist.setLinkSufix("");
        Vector lstData = ctrlist.getData();
        Vector lstLinkData = ctrlist.getLinkData();

        ctrlist.setLinkPrefix("javascript:cmdEdit('");

        ctrlist.setLinkSufix("')");

        ctrlist.reset();

        int index = -1;

        Vector carType_key = new Vector();
        Vector carType_value = new Vector();
        carType_key.add("2");
        carType_value.add("BS");
        carType_key.add("3");
        carType_value.add("CB");

        Vector carCurr_key = new Vector();
        Vector carCurr_value = new Vector();
        carCurr_key.add("USD");
        carCurr_value.add("USD");
        carCurr_key.add("RP");
        carCurr_value.add("RP");

        Vector carGuide_key = new Vector();
        Vector carGuide_value = new Vector();
        Vector listGuide = new Vector();
        listGuide = PstGuide.listAll();
        if (listGuide != null && listGuide.size() > 0) {
            for (int i = 0; i < listGuide.size(); i++) {
                Guide obj = (Guide) listGuide.get(i);
                carGuide_value.add(obj.getName());
                carGuide_key.add("" + obj.getOID());
            }
        }

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


        Vector car_key = new Vector();
        Vector car_value = new Vector();
        Vector listCar = new Vector();
        listCar = PstVehicle.listAll();
        if (listCar != null && listCar.size() > 0) {
            for (int i = 0; i < listCar.size(); i++) {
                Vehicle obj = (Vehicle) listCar.get(i);
                car_value.add(obj.getNopol());
                car_key.add("" + obj.getOID());
            }
        }
        Date newItem = new Date();
        Vector rowx = new Vector(1, 1);
        int count=0;
        for (int i = 0; i < objectClass.size(); i++) {
            TransportServiceOrderItem transportServiceOrderItem = (TransportServiceOrderItem) objectClass.get(i);
            rowx = new Vector(1, 1);
            count=count+1;
            if (iCommand == Command.EDIT && ItemId == transportServiceOrderItem.getOID()) {
                rowx.add("" +count);
                rowx.add("<input type=\"hidden\" name=\"" + frmObject.fieldNames[frmObject.FRM_FIELD_ID] + "\" value=\'" + transportServiceOrderItem.getId() + "'\" class=\"fromElement\" size=\"10\">"
                        + "<input type=\"text\" name=\"" + frmObject.fieldNames[frmObject.FRM_FIELD_DATE] + "\" value=\"\" class=\"fromElementtiga\" size=\"10\">");
                rowx.add("<input type=\"text\" name=\"" + frmObject.fieldNames[frmObject.FRM_FIELD_ORDERNO] + "\" value=\'" + transportServiceOrderItem.getOrderno() + "'\" class=\"fromElement\" size=\"10\">");
                rowx.add("<input type=\"text\" name=\"" + frmObject.fieldNames[frmObject.FRM_FIELD_PAX] + "\" value=\'" + transportServiceOrderItem.getPax() + "'\" class=\"fromElementdua\" size=\"10\">");
                rowx.add("<div align=\"center\">" + ControlCombo.drawBoostrap(frmObject.fieldNames[frmObject.FRM_FIELD_RQS], null, "" + transportServiceOrderItem.getRqs(), carType_key, carType_value, "formElemen") + "</div>");
                rowx.add("<input type=\"text\" name=\"" + frmObject.fieldNames[frmObject.FRM_FIELD_DESCRIPTIONS] + "\" value=\'" + transportServiceOrderItem.getDescriptions() + "'\" class=\"fromElement\" size=\"10\">");
                rowx.add("<div align=\"center\">" + ControlCombo.drawBoostrap(frmObject.fieldNames[frmObject.FRM_FIELD_CARNO], null, "" + transportServiceOrderItem.getCarno(), car_key, car_value, "formElemen") + "</div>");
                rowx.add("<div align=\"center\">" + ControlCombo.drawBoostrap(frmObject.fieldNames[frmObject.FRM_FIELD_DRIVER], null, "" + transportServiceOrderItem.getDriver(), carDriver_key, carDriver_value, "formElemen") + "</div>");
                rowx.add("<div align=\"center\">" + ControlCombo.drawBoostrap(frmObject.fieldNames[frmObject.FRM_FIELD_GUIDE], null, "" + transportServiceOrderItem.getGuide(), carGuide_key, carGuide_value, "formElemen") + "</div>");
                rowx.add("<input type=\"text\" name=\"" + frmObject.fieldNames[frmObject.FRM_FIELD_START] + "\" value=\'" + transportServiceOrderItem.getStart() + "'\" class=\"fromElementsatu\" size=\"10\">");
                rowx.add("<div align=\"center\">" + ControlCombo.drawBoostrap(frmObject.fieldNames[frmObject.FRM_FIELD_CURR], null, "" + transportServiceOrderItem.getCurr(), carCurr_key, carCurr_value, "formElemen") + "</div>");
                rowx.add("<input type=\"text\" name=\"" + frmObject.fieldNames[frmObject.FRM_FIELD_AMOUNT] + "\" value=\'" + transportServiceOrderItem.getAmount() + "'\" class=\"fromElement\" size=\"10\">");
                rowx.add("<input type=\"text\" name=\"" + frmObject.fieldNames[frmObject.FRM_FIELD_NOTE] + "\" value=\'" + transportServiceOrderItem.getNote() + "'\" class=\"fromElement\" size=\"10\">");

            } else {
                rowx.add("" +count);
                rowx.add("" + transportServiceOrderItem.getDate());
                rowx.add("" + transportServiceOrderItem.getOrderno());
                rowx.add("" + transportServiceOrderItem.getPax());
                rowx.add("" + transportServiceOrderItem.getCoderqs());
                rowx.add("" + transportServiceOrderItem.getDescriptions());
                rowx.add("" + transportServiceOrderItem.getCodecarno());
                rowx.add("" + transportServiceOrderItem.getCodedriver());
                rowx.add("" + transportServiceOrderItem.getCodeguide());
                rowx.add("" + transportServiceOrderItem.getStart());
                rowx.add("" + transportServiceOrderItem.getCurr());
                rowx.add("" + transportServiceOrderItem.getAmount());
                rowx.add("" + transportServiceOrderItem.getNote());

            }

            lstData.add(rowx);
            lstLinkData.add(String.valueOf(transportServiceOrderItem.getOID()+",'"+transportServiceOrderItem.getId()));
        }

        rowx = new Vector();
        if (iCommand == Command.ADD) {
            count=count+1;
            rowx.add("" +count);
            rowx.add("<input type=\"hidden\" name=\"" + frmObject.fieldNames[frmObject.FRM_FIELD_ID] + "\" value=\'" + oidTso + "'\" class=\"fromElement\">"
                    + "<input type=\"text\" name=\"" + frmObject.fieldNames[frmObject.FRM_FIELD_DATE] + "\" value=\'" + Formater.formatDate(newItem, "yyyy-MM-dd") + "'\" class=\"fromElementtiga\" size=\"10\" id=\"datepickerempat\">");
            rowx.add("<input type=\"text\" name=\"" + frmObject.fieldNames[frmObject.FRM_FIELD_ORDERNO] + "\" value=\"\" class=\"fromElement\">");
            rowx.add("<input type=\"text\" name=\"" + frmObject.fieldNames[frmObject.FRM_FIELD_PAX] + "\" value=\"\" class=\"fromElementdua\">");
            rowx.add("<div align=\"center\">" + ControlCombo.drawBoostrap(frmObject.fieldNames[frmObject.FRM_FIELD_RQS], null, "", carType_key, carType_value, "fromElement") + "</div>");
            rowx.add("<input type=\"text\" name=\"" + frmObject.fieldNames[frmObject.FRM_FIELD_DESCRIPTIONS] + "\" value=\"\" class=\"fromElement\"");
            rowx.add("<div align=\"center\">" + ControlCombo.drawBoostrap(frmObject.fieldNames[frmObject.FRM_FIELD_CARNO], null, "", car_key, car_value, "fromElement") + "</div>");
            rowx.add("<div align=\"center\">" + ControlCombo.drawBoostrap(frmObject.fieldNames[frmObject.FRM_FIELD_DRIVER], null, "", carDriver_key, carDriver_value, "fromElement") + "</div>");
            rowx.add("<div align=\"center\">" + ControlCombo.drawBoostrap(frmObject.fieldNames[frmObject.FRM_FIELD_GUIDE], null, "", carGuide_key, carGuide_value, "fromElement") + "</div>");
            rowx.add("<input type=\"text\" name=\"" + frmObject.fieldNames[frmObject.FRM_FIELD_START] + "\" value=\"\" class=\"fromElementsatu\"");
            rowx.add("<div align=\"center\">" + ControlCombo.drawBoostrap(frmObject.fieldNames[frmObject.FRM_FIELD_CURR], null, "", carCurr_key, carCurr_value, "fromElement") + "</div>");
            rowx.add("<input type=\"text\" name=\"" + frmObject.fieldNames[frmObject.FRM_FIELD_AMOUNT] + "\" value=\"\" class=\"fromElement\">");
            rowx.add("<input type=\"text\" name=\"" + frmObject.fieldNames[frmObject.FRM_FIELD_NOTE] + "\" value=\"\" class=\"fromElement\">");

        }
        lstData.add(rowx);


        return ctrlist.drawBootstrapStrip();
    }
%>

<% //proses yang tidak akan ditampilkan hanya diserver side

    int iCommand = FRMQueryString.requestCommand(request);
    int start = FRMQueryString.requestInt(request, "start");
    int prevCommand = FRMQueryString.requestInt(request, "prev_command");
    long oidTso = FRMQueryString.requestLong(request, "hidden_tso_id");
    String gradeDelete = FRMQueryString.requestString(request, "gradeDelete");

    String startDate = FRMQueryString.requestString(request, "tanggalStart");
    String endDate = FRMQueryString.requestString(request, "tanggalEnd");

    long oidTransportServiceOrderItem = FRMQueryString.requestLong(request, "hidden_tso_item_id");
    /*variable declaration*/

    int recordToGet = 15;
    String msgString = "";
    String msgStringItem = "";
    int iErrCode = FRMMessage.NONE;
    int iErrCodeDetail = FRMMessage.NONE;
    String whereClause = "";
    String orderClause = PstTransportServiceOrder.fieldNames[PstTransportServiceOrder.FLD_ID];
    CtrlTransportServiceOrder ctrlTransportServiceOrder = new CtrlTransportServiceOrder(request);
    CtrlTransportServiceOrderItem ctrlTransportServiceOrderItem = new CtrlTransportServiceOrderItem(request);
    ControlLine ctrLine = new ControlLine();
    Vector listDriver = new Vector(1, 1);

    /*switch statement */
    if (iCommand == Command.SAVE || iCommand == Command.EDIT) {
        iErrCode = ctrlTransportServiceOrder.action(iCommand, oidTso, request);
        iCommand = Command.ADD;
    }

    if (iCommand == Command.SAVE2) {
        iErrCode = ctrlTransportServiceOrder.action(4, oidTso, request);
        iErrCodeDetail = ctrlTransportServiceOrderItem.action(iCommand, oidTransportServiceOrderItem,request);
        iCommand = Command.ADD;
    }

    FrmTransportServiceOrder frmTransportServiceOrder = ctrlTransportServiceOrder.getForm();
    FrmTransportServiceOrderItem frmTransportServiceOrderItem = ctrlTransportServiceOrderItem.getForm();

    if (iCommand == Command.DELETEALL && iErrCode == FRMMessage.NONE) {
        iCommand = Command.NONE;
    }
    if (iCommand == Command.FIRST || iCommand == Command.NEXT || iCommand == Command.PREV || iCommand == Command.LAST) {
        start = 0;//ctrlTransportServiceOrder.action(iCommand, start, vectSize, recordToGet);
    }

    TransportServiceOrder transportServiceOrder = ctrlTransportServiceOrder.getTransportServiceOrder();
    TransportServiceOrderItem transportServiceOrderItem = ctrlTransportServiceOrderItem.getTransportServiceOrderItem();
    msgString = ctrlTransportServiceOrder.getMessage();
    msgStringItem = ctrlTransportServiceOrderItem.getMessage();

    /* get record to display */
    Date newDay = new Date();
    if (startDate.equals("")) {
        newDay.setDate(1);
        startDate = Formater.formatDate(newDay, "yyyy-MM-dd");
    }

    if (endDate.equals("")) {
        endDate = Formater.formatDate(newDay, "yyyy-MM-dd");
    }

    String where = "";
    if (oidTso != 0) {
        where = " tsi.id='" + oidTso + "'";
    } else {
        oidTso=transportServiceOrder.getOID();
        where = " tsi.id='" + transportServiceOrder.getOID() + "'";
    }

    listDriver = PstTransportServiceOrderItem.listJoin(0, 500, where, "tsi.detail_id ASC");
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
                document.myForm.command.value="2";
                document.myForm.hidden_driver_id.value="0";
                $(document).ready(function(){
                    $('#compose-modal').modal('show');
                });
            }
            
            function cmdEdit(oidItem,oid){
                document.myForm.command.value="<%=Command.EDIT%>";
                document.myForm.hidden_tso_id.value=oid;
                document.myForm.hidden_tso_item_id.value=oidItem;
                document.myForm.prev_command.value="<%=prevCommand%>";
                document.myForm.action="transport_service_order_edit.jsp";
                document.myForm.submit()
            }
            
            function cmdSave(){
                document.myForm.command.value="<%=Command.SAVE%>";
                document.myForm.hidden_tso_id.value="0";
                document.myForm.hidden_tso_item_id.value="0";
                document.myForm.prev_command.value="<%=prevCommand%>";
                document.myForm.action="transport_service_order_edit.jsp";
                document.myForm.submit();
            }
            
            function cmdSaveDetail(){
                document.myForm.command.value="<%=Command.SAVE2%>";
                document.myForm.prev_command.value="<%=prevCommand%>";
                document.myForm.action="transport_service_order_edit.jsp";
                document.myForm.submit();
            }
            function cmdSearch(){
                document.myForm.command.value="<%=Command.SEARCH%>";
                document.myForm.hidden_driver_id.value="0";
                document.myForm.prev_command.value="<%=prevCommand%>";
                document.myForm.action="transport_service_order_edit.jsp";
                document.myForm.submit();
            }
            
            function cmdPrint(){
                 window.open("<%=printrootx%>.session.InvoiceTransport?hidden_tso_id=<%=oidTso%>","",'scrollbars=yes,status=yes,width=750,height=500,resizable=yes');
            }
            
        </script>
    </head>
    <body class="skin-blue">
        <!-- header logo: style can be found in header.less -->
        <%@ include file = "../../header_mobile.jsp" %> 
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <!-- Left side column. contains the logo and sidebar -->
            <!-- Right side column. Contains the navbar and content of the page -->
              
                <!-- Content Header (Page header) -->
                <section>
                    <h1>
                        &nbsp;&nbsp;Transport Service Order
                    </h1>
                </section>
                <!-- Main content -->
                <section >
                    <form name="myForm"  method ="post" action="" role="form">
                        <input type="hidden" name="command" value="<%=iCommand%>">
                        <input type="hidden" name="vectSize" value="0">
                        <input type="hidden" name="start" value="<%=start%>">
                        <input type="hidden" name="prev_command" value="<%=prevCommand%>">
                        <input type="hidden" name="hidden_tso_id" value="<%=oidTso%>">
                        <input type="hidden" name="hidden_tso_item_id" value="<%=oidTransportServiceOrderItem%>">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="box">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        <h4 class="modal-title"></i>Input Transport Service Order</h4>
                                    </div>
                                    <div class="modal-body">
                                        <div class="row">
                                            <div class="col-md-5">
                                                <div class="form-group">
                                                    <div class="input-group">
                                                        <span class="input-group-addon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Date:</span>
                                                        <input name="<%=frmTransportServiceOrder.fieldNames[frmTransportServiceOrder.FRM_FIELD_DATE]%>" id="datepickertiga" type="text" class="form-control" placeholder="yyyy-MM-dd" value="<%=Formater.formatDate(transportServiceOrder.getDate(), "yyyy-MM-dd")%>">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="input-group">
                                                        <span class="input-group-addon">Tour No:</span>
                                                        <input name="<%=frmTransportServiceOrder.fieldNames[frmTransportServiceOrder.FRM_FIELD_TOURNO]%>" id="datepickertiga" type="text" class="form-control" placeholder="Tour No" value="<%=transportServiceOrder.getTourno()%>">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="input-group">
                                                        <span class="input-group-addon">&nbsp;Ref No:</span>
                                                        <input name="<%=frmTransportServiceOrder.fieldNames[frmTransportServiceOrder.FRM_FIELD_REFNO]%>" id="datepickertiga" type="text" class="form-control" placeholder="Referensi No" value="<%=transportServiceOrder.getRefno()%>">
                                                    </div>
                                                </div>    
                                            </div>
                                            <div class="col-md-1"></div>        
                                            <div class="col-md-5">
                                                <div class="form-group">
                                                    <div class="input-group">
                                                        <span class="input-group-addon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Agent Name:</span>
                                                        <%
                                                            Vector agent_key = new Vector();
                                                            Vector agent_value = new Vector();
                                                            Vector listAgent = new Vector();
                                                            listAgent = PstTravelAgent.list(0, 500, " name != '(NULL)' ", "");
                                                            if (listAgent != null && listAgent.size() > 0) {
                                                                for (int i = 0; i < listAgent.size(); i++) {
                                                                    TravelAgent obj = (TravelAgent) listAgent.get(i);
                                                                    agent_value.add(obj.getName());
                                                                    agent_key.add("" + obj.getOID());
                                                                }
                                                            }
                                                        %>
                                                        <%=ControlCombo.drawBoostrap(frmTransportServiceOrder.fieldNames[frmTransportServiceOrder.FRM_FIELD_AGENT], null, "" + transportServiceOrder.getAgent(), agent_key, agent_value, "form-control")%>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="input-group">
                                                        <span class="input-group-addon">Guest/Group Name:</span>
                                                        <input name="<%=frmTransportServiceOrder.fieldNames[frmTransportServiceOrder.FRM_FIELD_CLIENT]%>" type="email" class="form-control" placeholder="Guest/Group Name" value="<%=transportServiceOrder.getClient()%>" >
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <%
                                                        Vector hotel_key = new Vector();
                                                        Vector hotel_value = new Vector();
                                                        Vector listHotel = new Vector();
                                                        listHotel = PstHotel.list(0, 500, " name != '(NULL)' ", "");
                                                        if (listHotel != null && listHotel.size() > 0) {
                                                            for (int i = 0; i < listHotel.size(); i++) {
                                                                Hotel obj = (Hotel) listHotel.get(i);
                                                                hotel_value.add(obj.getName());
                                                                hotel_key.add("" + obj.getOID());
                                                            }
                                                        }
                                                    %>
                                                    <div class="input-group">
                                                        <span class="input-group-addon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hotel Name:</span>
                                                        <%=ControlCombo.drawBoostrap(frmTransportServiceOrder.fieldNames[frmTransportServiceOrder.FRM_FIELD_HOTEL], null, "" + transportServiceOrder.getHotel(), hotel_key, hotel_value, "form-control")%>
                                                    </div>
                                                </div>     
                                            </div>
                                            <div class="col-md-1"></div>
                                        </div>
                                        <%if (transportServiceOrder.getOID() != 0 || oidTso != 0) {%>
                                        <div class="row">
                                                <%=drawList(iCommand, listDriver, oidTransportServiceOrderItem, frmTransportServiceOrderItem, oidTso, oidTransportServiceOrderItem)%>
                                            
                                        </div>
                                        <%}%>          
                                    </div>
                                    <%if (transportServiceOrder.getOID() != 0 || oidTso != 0) {%>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="modal-footer clearfix">
                                                <button type="button" class="btn btn-primary pull-left" onclick="javascript:cmdSaveDetail()"></i> Save </button>
                                                <button type="button" class="btn btn-danger pull-left" onclick="javascript:cmdDelete()"></i> Delete</button>
                                                <button type="button" class="btn btn-danger pull-right" onclick="javascript:cmdPrint()"></i> Print Invoice</button>
                                            </div>
                                        </div>   
                                    </div>
                                    <%} else {%>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="modal-footer clearfix">
                                                <button type="button" class="btn btn-primary pull-left" onclick="javascript:cmdSave()"></i> Save </button>
                                                <button type="button" class="btn btn-danger pull-left" onclick="javascript:cmdDelete()"></i> Delete</button>
                                                <button type="button" class="btn btn-danger pull-right" onclick="javascript:cmdPrint()"></i> Print Invoice</button>
                                            </div>
                                        </div>   
                                    </div>
                                    <%}%>  

                                </div><!-- /.box -->
                            </div>
                        </div>
                    </form>
                </section><!-- /.content -->
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
        <script>
            $(function() {
                $( "#datepickerempat" ).datepicker({ dateFormat: "yy-mm-dd" });
            });
        </script>
    </body>
</html>
