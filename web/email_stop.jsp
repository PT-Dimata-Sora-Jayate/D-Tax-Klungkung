<%@page import="java.util.Date"%>
<%@page import="com.dimata.util.Formater"%>
<%@page import="com.dimata.common.session.email.SessEmail"%>
<%

SessEmail email = new SessEmail() ;
Date newDate = new Date();
email.sendEamilNotif("dianiopiari89@gmail.com,bkpad.kabbangli@gmail.com", "Aplikasi Pajak BANGLI STOP "+Formater.formatDate(newDate, "dd-MM-yyyy"), "Dear Team, <br> Aplikasi akan di stop 5 menit lagi <br> Terimakasih ");
%>