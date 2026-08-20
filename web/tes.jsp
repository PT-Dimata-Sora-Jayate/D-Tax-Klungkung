<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<%@page import="com.dimata.gui.jsp.ControlCombo"%>
<%@page import="java.util.Calendar"%>
<%@page import="com.dimata.util.Command"%>
<%@page import="com.dimata.util.Formater"%>
<%@page import="java.util.Date"%>
<%@page import="com.dimata.wpupload.form.esptpd.FrmESPTPD"%>
<%@page import="com.dimata.wpupload.form.wpnamabadan.FrmNamaBadan"%>
<%@page import="com.dimata.wpupload.entity.esptpd.PstESPTPD"%>
<%@page import="com.dimata.wpupload.entity.wpuser.PstAppUserWP"%>
<%@page import="com.dimata.wpupload.entity.wpnamabadan.PstNamaBadan"%>
<%@page import="java.io.IOException"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.nio.file.*"%>
<%@include file="../main/javainit-wp.jsp" %>
<!DOCTYPE html>
<html lang="en">
<%
	String message="";
    NumberFormat nf = NumberFormat.getNumberInstance();
	long mb = 1024L * 1024L;
	for (Path root : FileSystems.getDefault().getRootDirectories()) {

		try {
			FileStore store = Files.getFileStore(root);
			message+=root+" : Space Sisa : "+ nf.format(store.getUsableSpace() / mb) + " MB, "
					+ " Space Total : " + nf.format(store.getTotalSpace() / mb) + " MB <br>";
		} catch (IOException e) {
		}
	}
%>
  
<head>
    <meta charset="utf-8">
  </head>

<body>
	<%=message%>
  </body>
</html>
