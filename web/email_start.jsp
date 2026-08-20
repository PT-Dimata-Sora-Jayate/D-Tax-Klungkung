<%@page import="java.util.Date"%>
<%@page import="com.dimata.util.Formater"%>
<%@page import="com.dimata.common.session.email.SessEmail"%>
<%

SessEmail email = new SessEmail() ;
Date newDate = new Date();
email.sendEamilNotif("gunadiwirawan94@gmail.com", "Aplikasi_Pajak_BANGLI_START_"+Formater.formatDate(newDate, "ddMMyyyy"), "Dear Team, <br> Aplikasi sudah aktive <br> Terimakasih ");
%>