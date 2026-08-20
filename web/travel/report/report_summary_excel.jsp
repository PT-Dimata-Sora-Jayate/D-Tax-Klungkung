<%-- 
    Document   : report_summary_excel
    Created on : May 1, 2015, 2:56:48 PM
    Author     : dimata005
--%>


<%@page import="com.dimata.travel.entity.report.SrcReport"%>
<%@page import="com.dimata.travel.entity.report.ReportSummary"%>
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
        ctrlist.addHeader("Pendapatan", "200px");
        ctrlist.addHeader("Persentase", "200px");
        ctrlist.addHeader("Gaji", "200px");
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
        double totPendapatan=0.0;
        double totPersentase=0.0;
        double totgaji=0.0;
        double totSemua=0.0;
        Vector rowx = new Vector(1, 1);
        for (int i = 0; i < objectClass.size(); i++) {
            ReportSummary reportDetail = (ReportSummary) objectClass.get(i);
            count=count+1;
            totPendapatan=totPendapatan+reportDetail.getPendapatan();
            totPersentase=totPersentase+reportDetail.getPersentase();
            totgaji=totgaji+reportDetail.getGaji();
            totSemua=totSemua+reportDetail.getTotal();
            
            rowx = new Vector(1, 1);
            rowx.add(""+count);
            rowx.add(""+reportDetail.getDriverName());
            rowx.add(""+FRMHandler.userFormatStringDecimal(reportDetail.getPendapatan()));
            rowx.add(""+FRMHandler.userFormatStringDecimal(reportDetail.getPersentase()));
            rowx.add(""+FRMHandler.userFormatStringDecimal(reportDetail.getGaji()));
            rowx.add(""+FRMHandler.userFormatStringDecimal(reportDetail.getTotal()));
            
            lstData.add(rowx);
        }
        
        rowx = new Vector(1, 1);
        rowx.add("");
        rowx.add("Total");
        rowx.add("<b>"+FRMHandler.userFormatStringDecimal(totPendapatan)+"</b>");
        rowx.add("<b>"+FRMHandler.userFormatStringDecimal(totPersentase)+"</b>");
        rowx.add("<b>"+FRMHandler.userFormatStringDecimal(totgaji)+"</b>");
        rowx.add("<b>"+FRMHandler.userFormatStringDecimal(totSemua)+"</b>");

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
    listReportDetail = SessReportDetailSummary.getListSummary(srcReportDetailSummary,0,0);
%>
<%@ page contentType="application/x-msexcel" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Report Summary</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    </head>
    <body class="skin-blue">
        <table width="100%" cellspacing="0" cellpadding="3">
            <tr align="left" valign="top">
                  <td height="14" valign="middle" colspan="3" class="command">
                   <b><strong>
                        REPORT SUMMARY <%=startDate%> S/D <%=endDate%>
                    </strong></b>
                    </td>
                </tr>
        </table>
         <%=drawList(iCommand, listReportDetail, oidDriver)%>
    </body>
</html>
