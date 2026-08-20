<%-- 
    Document   : view
    Created on : Apr 30, 2015, 3:44:03 PM
    Author     : dimata005
--%>

<%@ page language="java" import="java.sql.*" %>

<HTML>
    <HEAD> <TITLE> The JDBCQuery JSP  </TITLE> </HEAD>
    <BODY BGCOLOR="white">

        <% String searchCondition = request.getParameter("cond");%>
        <H3> Search results for  <I> <%= searchCondition%> </I> </H3>
        <B> <%= runQuery(searchCondition)%> </B> <HR><BR>

    </BODY>
</HTML>

<%-- Declare and define the runQuery() method. --%>
<%!
    private String runQuery(String cond) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.12.16:1521/pbbgianyar", "bankbpd", "bankbpd");
            stmt = conn.createStatement();
            rset = stmt.executeQuery("SELECT * FROM VIEW_SIMPATDA ");
            return (formatResult(rset));
        } catch (SQLException e) {
            return ("<P> SQL error: <PRE> " + e + " </PRE> </P>\n");
        } finally {
            if (rset != null) {
                rset.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    private String formatResult(ResultSet rset) throws SQLException {
        StringBuffer sb = new StringBuffer();
        if (!rset.next()) {
            sb.append("<P> No matching rows.<P>\n");
        } else {
            sb.append("<UL>");
            do {
                sb.append("<LI> ID " + rset.getString(1)
                        + " BULAN " + rset.getString(2) + ".</LI>\n"
                        + " TAHUN " + rset.getString(3) + ".</LI>\n"
                        +" NAMA " + rset.getString(4) + ".</LI>\n"
                        +" JUMLAH PAJAK " + rset.getDouble(5) + ".</LI>\n"
                        +" INSTANSI " + rset.getString(6) + ".</LI>\n"
                        +" NO SSPD " + rset.getString(7) + ".</LI>\n"
                        +" NPWD " + rset.getString(8) + ".</LI>\n");
            } while (rset.next());
            sb.append("</UL>");
        }
        return sb.toString();
    }
%>

