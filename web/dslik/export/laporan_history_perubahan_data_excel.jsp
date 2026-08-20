<%@page import="com.dimata.common.entity.logger.LogSysHistory"%>
<%@page import="com.dimata.common.entity.logger.PstLogSysHistory"%>
<%@page import="com.dimata.dslik.entity.agunan.Agunan"%>
<%@page import="com.dimata.dslik.entity.agunan.PstAgunan"%>
<%@page import="com.dimata.dslik.entity.kredit.Kredit"%>
<%@page import="com.dimata.dslik.entity.kredit.PstKredit"%>
<%@page import="com.dimata.dslik.entity.masterdata.CabangBank"%>
<%@page import="com.dimata.util.Formater"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Vector"%>
<%@page import="com.dimata.dslik.entity.masterdata.PstCabangBank"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../main/javainit_slik.jsp" %>
<!DOCTYPE html>
<%
    String file = "Laporan-History-Perubahan-Data";
    
    response.setContentType("application/x-msexcel"); 
    response.setHeader("Content-Disposition","attachment; filename="+file+".xls" ); 
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            long userid = FRMQueryString.requestLong(request, "userid");
            String doctype = FRMQueryString.requestString(request, "doctype");
            String dari = FRMQueryString.requestString(request, "daritanggal");
            String sampai = FRMQueryString.requestString(request, "sampaitanggal");
        %>
        <table width="100%" border="1" cellpadding="0" cellspacing="0">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Tanggal</th>
                    <th>Aksi</th>
                    <th>Log Detail</th>
                </tr>
            </thead>
            <tbody>
                <%
                    String whereClause = "";
                    if(userid != 0){
                        if(whereClause.length() > 0){
                            whereClause +=" AND "+PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_USER_ID]+"='"+userid+"'";
                        }else{
                            whereClause +=" "+PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_USER_ID]+"='"+userid+"'";
                        }
                    }

                    if(doctype.length() > 0){
                        if(whereClause.length() > 0){
                            whereClause +=" AND "+PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_DOCUMENT_TYPE]+"='"+doctype+"'";
                        }else{
                            whereClause +=" "+PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_DOCUMENT_TYPE]+"='"+doctype+"'";
                        }
                    }

                    if(dari.length() > 0 && sampai.length() > 0){
                        if(whereClause.length() > 0){
                            whereClause +=" AND ("+PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_DOCUMENT_TYPE]+" BETWEEN '"+dari+"' AND '"+sampai+"')";
                        }else{
                            whereClause +=" ("+PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_DOCUMENT_TYPE]+" BETWEEN '"+dari+"' AND '"+sampai+"')";
                        }
                    }

                    Vector listLog = PstLogSysHistory.listPurchaseOrder(0, 0, whereClause, ""+PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_UPDATE_DATE]+" DESC");
                    if(listLog.size() > 0){
                        for(int i = 0; i < listLog.size(); i++){
                            LogSysHistory logSysHistory = (LogSysHistory) listLog.get(i);
                            %>
                            <tr>
                                <td><%= i+1 %></td>
                                <td><%= (logSysHistory.getLogUpdateDate() == null ? "" : Formater.formatDate(logSysHistory.getLogUpdateDate(), "yyyy-MM-dd")) %></td>
                                <td><%= logSysHistory.getLogUserAction() %></td>
                                <td><%= logSysHistory.getLogDetail() %></td>
                            </tr>
                            <%
                        }
                    }
                %>
            </tbody>
        </table>
    </body>
</html>
