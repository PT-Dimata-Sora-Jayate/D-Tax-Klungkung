<%-- 
    Document   : form_siswa
    Created on : Oct 10, 2014, 11:39:24 AM
    Author     : khirayinNura
--%>

<%@page import="com.dimata.gui.jsp.ControlDate"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <%
            String [] bulan = {
                "Januari","Februari","Maret","April","Mei","Juni","Juli","Agustus","September","Oktober","November","Desember"
            };
        %>
    </head>
    <body>
        <form name="input" action="" method="post">
            <table>
                <tr>
                    <td>
                        Nama Lengkap
                    </td>           
                    <td>
                        <input type="text" name="name" />
                    </td>
                </tr>
                <tr>
                    <td>
                        Tempat & Tanggal Lahir
                    </td>           
                    <td>
                        <input type="text" name="ttl" />
                    </td>
                    <td>
                       <select name="bln">
                           <%for(int i = 0; i <= bulan.length; i++) {%>
                            <option><%= bulan[i]%></option>
                           <%}%>
                        </select>
                    </td>
                    <td>
                       <select name="tgl">
                           <%for(int i = 1; i <= 31; i++) {%>
                            <option><%=i%></option>
                           <%}%>
                        </select>
                    </td>
                    <td>
                       <select name="thn">
                           <%for(int i = 1990; i <= 2014; i++) {%>
                            <option><%=i%></option>
                           <%}%>
                        </select>
                    </td>
                </tr>
            </table>
        </form>
        
        
    </body>
</html>
