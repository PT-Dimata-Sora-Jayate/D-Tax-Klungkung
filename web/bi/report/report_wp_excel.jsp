<%-- 
    Document   : report_wp_excel
    Created on : Oct 13, 2015, 10:20:03 PM
    Author     : Administrator
--%>

<%@page import="com.dimata.dtaxintegration.session.SessionData"%>
<%-- 
    Document   : uji_coba
    Created on : Oct 13, 2015, 5:23:03 PM
    Author     : dimata005
--%>

<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<%@page import="com.dimata.gui.jsp.ControlList"%>
<%@page import="java.util.Vector"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<%!
        
	public String drawList(Vector objectClass){

                Vector resultType = new Vector(1, 1);
                Vector resultHeader = new Vector(1, 1);
                Vector resultData = new Vector(1, 1);
                
                if((objectClass!=null)&&(objectClass.size()>0)){
                    for (int i = 0; i < objectClass.size(); i++) {
                        if(i==0){
                            resultHeader = (Vector) objectClass.get(0);
                        }else if(i==1){
                            resultType = (Vector) objectClass.get(1);
                        }else{
                             resultData = (Vector) objectClass.get(2);
                        }
                    }
                   
                }
            
            
		ControlList ctrlist = new ControlList();

		ctrlist.setAreaWidth("100%");

		ctrlist.setListStyle("listarea");

		ctrlist.setTitleStyle("listheader");

		ctrlist.setCellStyle("table_cell");

		ctrlist.setHeaderStyle("listheader");
                
                ctrlist.setBorder(1);
                
                if((resultHeader!=null)&&(resultHeader.size()>0)){
                    for (int i = 0; i < resultHeader.size(); i++) {
                        String header = (String) resultHeader.get(i);
                        ctrlist.addHeader(""+header,"10%");
                    }
                }
		
                
		ctrlist.setLinkRow(1);

		ctrlist.setLinkSufix("");

		Vector lstData = ctrlist.getData();

		Vector lstLinkData = ctrlist.getLinkData();

		ctrlist.setLinkPrefix("javascript:cmdEdit('");

		ctrlist.setLinkSufix("')");

		ctrlist.reset();

		for (int i = 0; i < resultData.size(); i++) {

			Vector rowx = new Vector();
                        Vector detailResultData = (Vector) resultData.get(i);
                        for (int k = 0; k < detailResultData.size(); k++) {
                            String header = (String) detailResultData.get(k);
                            rowx.add(""+header);
                        }
                        
			lstData.add(rowx);

			lstLinkData.add("");

		}

		return ctrlist.draw();

	}

%>

<%
    String loginID = FRMQueryString.requestString(request,"text_sql");
    SessionData sess = new SessionData();
    Vector list = new Vector();
    if(!loginID.equals("")){
        list = sess.getCustomeSql(""+loginID);
    }
    
%>
<%@ page contentType="application/x-msexcel" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script language="javascript"> 
            function cmdLogin()

            {	

              document.frmLogin.action = "uji_coba.jsp";
              document.frmLogin.submit();

            }

        </script>
    </head>
    <body>
        <form  name="frmLogin" method="get" onSubmit="javascript:cmdLogin()">
            <input type="text" name="text_sql" value="">
            <input type="submit" name="submit" value="login" class="input_text" onClick="javascript:cmdLogin()">
            <br>
            <%if((list!=null)&&(list.size()>0)){%>

            <%=drawList(list)%>

           <%}else{%>

              &nbsp;no employee match the search parameter  .... 

          <%}%>
        </form>
    </body>
</html>

