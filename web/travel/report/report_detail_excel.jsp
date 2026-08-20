<%-- 
    Document   : report_detail_excel
    Created on : May 1, 2015, 2:54:25 PM
    Author     : dimata005
--%>

<%@page import="com.dimata.travel.entity.report.SrcReport"%>
<%@page import="com.dimata.qdep.form.FRMHandler"%>
<%@page import="com.dimata.travel.entity.report.ReportDetail"%>
<%@page import="com.dimata.travel.session.SessReportDetailSummary"%>
<%@page import="com.dimata.gui.jsp.ControlDate"%>
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
<%//@ include file = "main/checkuser.jsp" %>

<%
    boolean privAdd = true;
    boolean privSave = true;
    boolean privDelete = true;
    boolean privUpdate = true;
    boolean privView = true;
%>


<%!//proses yang akan ditampilkan

    public String drawList(int iCommand, Vector objectClass, long gradeId) {

        ControlList ctrlist = new ControlList();

        ctrlist.setAreaWidth("100%");
        ctrlist.setListStyle("listgen");
        ctrlist.setTitleStyle("tableheader");
        ctrlist.setCellStyle("cellStyle");
        ctrlist.setHeaderStyle("tableheader");
        ctrlist.setBorder(1);
        ctrlist.addHeader("No", "200px");
        ctrlist.addHeader("Driver Name", "200px");
        ctrlist.addHeader("Date Report", "200px");
        ctrlist.addHeader("Police Number", "200px");
        ctrlist.addHeader("Agent Name", "200px");
        ctrlist.addHeader("Tour No", "200px");
        ctrlist.addHeader("Description", "200px");
        ctrlist.addHeader("Pax", "200px");
        ctrlist.addHeader("Amount", "200px");
         
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
            ReportDetail reportDetail = (ReportDetail) objectClass.get(i);
            count=count+1;
            total=total+reportDetail.getAmount();
            rowx = new Vector(1, 1);
            rowx.add(""+count);
            rowx.add(""+reportDetail.getDriverName());
            rowx.add(""+reportDetail.getDateReport());
            rowx.add(""+reportDetail.getPoliceNumber());
            rowx.add(""+reportDetail.getAgentName());
            rowx.add(""+reportDetail.getTourNo());
            rowx.add(""+reportDetail.getDescription());
            rowx.add(""+reportDetail.getPax());
            rowx.add(""+FRMHandler.userFormatStringDecimal(reportDetail.getAmount()));
            
            lstData.add(rowx);
            //lstLinkData.add("");
        }
        
        rowx = new Vector(1, 1);
        rowx.add("");
        rowx.add("");
        rowx.add("");
        rowx.add("");
        rowx.add("");
        rowx.add("");
        rowx.add("");
        rowx.add("<b>Total<b>");
        rowx.add(""+FRMHandler.userFormatStringDecimal(total));

        lstData.add(rowx);

        return ctrlist.draw();
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
    listReportDetail = SessReportDetailSummary.getListDetail(srcReportDetailSummary,0,0);
%>
<%@ page contentType="application/x-msexcel" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Report Detail</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    </head>
    <body class="skin-blue">
        <table width="100%" cellspacing="0" cellpadding="3">
            <tr align="left" valign="top">
                  <td height="14" valign="middle" colspan="3" class="command">
                   <b><strong>
                        REPORT DETAIL <%=startDate%> S/D <%=endDate%>
                    </strong></b>
                    </td>
                </tr>
        </table>
         <%=drawList(iCommand, listReportDetail, oidDriver)%>
    </body>
</html>
