<%-- 
    Document   : dslik_logout
    Created on : Nov 25, 2016, 2:32:24 PM
    Author     : dimata005
--%>
<%@page import="com.dimata.dslik.session.admin.SessUserSession"%>
<%@ page language="java" %>
<%@ include file = "main/javainit_slik.jsp" %>
<% int appObjCode = 1;%>
<!--%@ include file = "main/checkuser.jsp" %-->
<%!
    public static final String textListTitleHeader[][] = {
        {"EXIT", "LOGIN"},
        {"EXIT", "LOGIN"}
    };
%>                                                   

<%
    try {
        if (userSession.isLoggedIn() == true) {
            userSession.printAppUser();
            userSession.doLogout();
            session.removeValue(SessUserSession.HTTP_SESSION_NAME);
            session.removeValue("APPLICATION_LANGUAGE");
        }
        if (userSession != null) {
            session.removeValue(SessUserSession.HTTP_SESSION_SALES);
        }
    } catch (Exception exc) {
        System.out.println(" ==> Exception during logout user");
    }

%>
<html>
    <head>
        <title>Dimata - SLIK</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <link rel="stylesheet" href="styles/default.css" type="text/css">
        <script language="JavaScript">
            function closeWindow() {
                window.close();
            }
        </script>
    </head>
    <body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" >
        <table width="100%" border="0" cellspacing="0" cellpadding="1" height="100%" align="center">
            <tr>       
                <td align="center">  
                    <table width="40%" cellspacing="2" cellpadding="2" align="center">
                        <tr> 
                            <td width="68%" align="center">&nbsp;</td> 
                        </tr>
                        <tr>
                            <td width="68%" height="20"> 
                                <div align="center">
                                    <p>&nbsp;</p>
                                    <p>
                                        <span class="biglogintitle"><font size="5"><b><font color="#0000CC" size="6">DIMATA</font></b></font></span> <br>
                                        <span class="smalllogintitle"><font color="#0000CC" size="2">- SLIK-</font></span>
                                    </p>
                                    <p>&nbsp;</p>
                                </div>
                            </td>
                        </tr>
                        <tr> 
                            <td width="68%" align="center">&nbsp;</td>
                        </tr>
                        <tr> 
                            <td width="68%" height="40" align="center"> <a href="javascript:closeWindow()">EXIT</a> | <a href="dslik/frontend/login.jsp">LOGIN </a></td>
                        </tr>
                    </table>
                </td> 
            </tr>
        </table>
    </body>
</html>
