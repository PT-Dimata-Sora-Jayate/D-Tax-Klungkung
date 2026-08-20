<%-- 
    Document   : query
    Created on : Feb 3, 2018, 3:00:57 PM
    Author     : dimata005
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="com.dimata.dslik.session.proses.ProsesTransferDataBank"%>
<%@page import="com.dimata.dslik.entity.masterdata.OutletConnection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table>
            <tr>
                
            </tr>
        </table>
        <%
        ProsesTransferDataBank prosesTransferDataBank = new ProsesTransferDataBank();    
        OutletConnection outletConnection = prosesTransferDataBank.getConfigurasiConnection();    
        Connection conn = null;    
        String dbMasterBackup="DBLMON";
        PreparedStatement stxxz = null;
        ResultSet rsxxz = null;
        
        Class.forName(outletConnection.getDbdriver()).newInstance();
        conn = DriverManager.getConnection(outletConnection.getDburl(), outletConnection.getDbuser(), outletConnection.getDbpasswd());
        try {
            String sql = "SELECT "+dbMasterBackup+".LNRESTRK.* from "+dbMasterBackup+".LNRESTRK, "
                    + "(select COUNT(*) as TOTRESTRUK "
                    + " FROM "+dbMasterBackup+".LNRESTRK WHERE "+dbMasterBackup+".LNRESTRK.FLGAPPRV=1 AND CONCAT("+dbMasterBackup+".LNRESTRK.BRANCHID, "+dbMasterBackup+".LNRESTRK.ACCNBR)='0390626041284') AS countrstk  " +
                      " WHERE "+dbMasterBackup+".LNRESTRK.FLGAPPRV=1 AND CONCAT("+dbMasterBackup+".LNRESTRK.BRANCHID, "+dbMasterBackup+".LNRESTRK.ACCNBR)='0390626041284' "
                    + "ORDER BY "+dbMasterBackup+".LNRESTRK.RESTDATE DESC";
            stxxz = conn.prepareStatement(sql);
            rsxxz = stxxz.executeQuery();
            while (rsxxz.next()) {
                %>
                    <%=rsxxz.getString("ACCNBR")%> | 
                    <%=rsxxz.getDate("RESTDATE")%> <br>
                <%
                //entKredit.setTanggalRestrukturisasiAkhir(rsxxz.getDate("RESTDATE")); ACCNBR RESTDATE
                //entKredit.setTglMulai(rsxxz.getDate("RESTDATE"));
            }
        }catch (Exception e) {
            System.out.println("com.dimata.dslik.session.proses.ProsesTransferDataBank.requestEntityObjectKreditRestrukMoreThan() "+e.getMessage());
        } finally {
            if (rsxxz != null) {
                try {
                    rsxxz.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
            if (stxxz != null) {
                try {
                    stxxz.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
        }
        
        %>
    </body>
</html>
