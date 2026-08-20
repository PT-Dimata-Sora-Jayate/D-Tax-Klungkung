<%@page import="com.dimata.dslik.session.proses.ProsesTransferDataBank"%>
<%@page import="com.dimata.dslik.entity.masterdata.OutletConnection"%>
<%@page import="com.dimata.qdep.db.DBException"%>
<%@page import="com.dimata.qdep.db.DBHandler"%>
<%@page import="com.dimata.qdep.db.DBResultSet"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="org.apache.commons.io.output.*" %>

<%-- 
    Document   : validasi_ojk
    Created on : Sep 10, 2017, 10:38:40 PM
    Author     : dimata005
--%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Date"%>
<%@page import="com.dimata.util.Formater"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<%@page import="com.dimata.gui.jsp.ControlCombo"%>
<%@page import="java.util.Vector"%>
<%@page import="com.dimata.util.Command"%>
<!DOCTYPE html>
<%@include file="../../main/javainit_slik.jsp" %>
<%@include file="../../main/checkuser_slik.jsp" %>
<%
    boolean privView = true;//userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_PROSES_TRANSFER_DATA, AppObjInfo.G2_TRANSFER_PENGURUS_PEMILIK_DATA, AppObjInfo.OBJ_TRANSFER_PENGURUS_PEMILIK_DATA, AppObjInfo.COMMAND_VIEW);
    boolean privStart = true;//userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_PROSES_TRANSFER_DATA, AppObjInfo.G2_TRANSFER_PENGURUS_PEMILIK_DATA, AppObjInfo.OBJ_TRANSFER_PENGURUS_PEMILIK_DATA, AppObjInfo.COMMAND_START);
    boolean privStop = true;//userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_PROSES_TRANSFER_DATA, AppObjInfo.G2_TRANSFER_PENGURUS_PEMILIK_DATA, AppObjInfo.OBJ_TRANSFER_PENGURUS_PEMILIK_DATA, AppObjInfo.COMMAND_STOP);
%>
<%
    int iCommand = FRMQueryString.requestCommand(request);
    
    ProsesTransferDataBank prosesTransferDataBank = new ProsesTransferDataBank();
    OutletConnection outletConnection = prosesTransferDataBank.getConfigurasiConnection();
    File file;
    int maxFileSize = 5000 * 1024;
    int maxMemSize = 5000 * 1024;
    String filePath = ""+outletConnection.getPathDelimitedText();
    String fileName="";
    String segment ="";
    boolean uploadSucsess=false;
    boolean uploadForUpdate=false;
    if(iCommand==Command.SUBMIT){
        fileName = FRMQueryString.requestString(request, "namefile");
        if(!fileName.equals("")){
             uploadSucsess=true;
             uploadForUpdate=true;
             segment = fileName.substring(17, 20);
        }
        iCommand=Command.UPLOADATA;
    }else if(iCommand==Command.NONE){
        String contentType = request.getContentType();
        if ((contentType.indexOf("multipart/form-data") >= 0)) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(maxMemSize);
            factory.setRepository(new File(""+outletConnection.getPathDelimitedText()));
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(maxFileSize);
            try {
                List fileItems = upload.parseRequest(request);
                Iterator i = fileItems.iterator();
                while (i.hasNext()) {
                    FileItem fi = (FileItem) i.next();
                    if (!fi.isFormField()) {
                        String fieldName = fi.getFieldName();
                        fileName = fi.getName();
                        segment = fileName.substring(17, 20);
                        boolean isInMemory = fi.isInMemory();
                        long sizeInBytes = fi.getSize();
                        file = new File(filePath + ""+fileName);
                        fi.write(file);
                        uploadSucsess=true;
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else {
        }
        iCommand=Command.UPLOADATA;
    }
%>
<html>
  <head>
    <meta charset="UTF-8">
    <title>SLIK | Validasi Transfer</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <%@include file="../../template-component/css-component.jsp" %>
    <script language="JavaScript">
            
        function cmdUpload(){
            document.driver.command.value="<%=Command.SUBMIT%>";
            document.driver.hidden_driver_id.value="0";
            document.driver.action="fileupload.jsp";
            document.driver.submit();
        }
    </script>
  </head>
  <body class="<%= skin %>">
      <input type="hidden" name="approot" id="approot" value="<%= approot %>">
    <div class="wrapper">
      
	<%@include file="../../template-component/header-component.jsp" %>
        <%@include file="../../template-component/sidebar-component.jsp" %>

      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            Transfer
            <small>Validasi Transfer File Dilimted Eror OJK</small>
          </h1>
        </section>
        <!-- Main content -->
        <%
            if(privView){
        %>
        <section class="content">
          <!-- Small boxes (Stat box) -->
          <form name="driver"  method ="post" action="" role="form">
            <input type="hidden" name="command" value="<%=iCommand%>">
            <input type="hidden" name="namefile" value="<%=fileName%>">
                <div class="row">
                  <div class="col-md-12">
                      <div class='box box-primary'>
                          <div class="box-header">
                        </div>
                        <div class="box-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <%if(uploadSucsess==true){%>
                                        Segment <%=segment%><br>
                                        <%
                                        BufferedReader in = null;
                                        try {
                                            in = new BufferedReader(new FileReader(filePath+fileName));
                                            String read = null;
                                            int count=0;
                                            if(segment.equals("A01")){
                                                while ((read = in.readLine()) != null) {
                                                    String[] splited = read.split("\n");
                                                    int countRecord=0;
                                                    for (String part : splited) {
                                                        if(count!=0){
                                                            if(!part.equals("")){
                                                                String[] rowData = part.split("\\|");
                                                                if(rowData[0].equals("RECORD")){
                                                                    countRecord=1;
                                                                    try{
                                                                        String kodeRegister = rowData[3];
                                                                        String noRek = rowData[4];
                                                                        String cif = rowData[5];
                                                                        %>
                                                                            update dslik_agunan set status_data=null where periode_id='<%=periode.getOID()%>' and kode_register_agunan='<%=kodeRegister%>' and no_rekening='<%=noRek%>' and cif='<%=cif%>';
                                                                        <%
                                                                        if(uploadForUpdate){
                                                                            //proses update
                                                                            int iResult = 0;
                                                                            DBResultSet dbrs = null;
                                                                            String sql = "update dslik_agunan set status_data=null where periode_id='"+periode.getOID()+"' and kode_register_agunan='"+kodeRegister+"' and no_rekening='"+noRek+"' and cif='"+cif+"'";
                                                                            try {
                                                                                iResult = DBHandler.execUpdate(sql);
                                                                                %>
                                                                                    ( berhasil ) <br>
                                                                                <%
                                                                            } catch (DBException e) {
                                                                                e.printStackTrace();
                                                                            } finally {
                                                                                DBResultSet.close(dbrs);
                                                                            }
                                                                        }else{
                                                                            %>
                                                                            <br>
                                                                            <%
                                                                        }
                                                                    }catch(Exception ex){
                                                                    }
                                                                }else{
                                                                   if(rowData[0].equals("ERROR")){
                                                                    %>
                                                                        <%=part%><br>
                                                                    <%
                                                                   }
                                                                }
                                                            }else{%>
                                                                <br>
                                                            <%}
                                                        }
                                                        count=count+1;    
                                                    }
                                                }
                                            }else if (segment.equals("D01")){
                                                 while ((read = in.readLine()) != null) {
                                                    String[] splited = read.split("\n");
                                                    for (String part : splited) {
                                                        if(count!=0){
                                                            if(!part.equals("")){
                                                                String[] rowData = part.split("\\|");
                                                                if(rowData[0].equals("RECORD")){
                                                                    try{
                                                                        String cif = rowData[3];
                                                                        %>
                                                                            update dslik_debitur set status_data=null where periode_id='<%=periode.getOID()%>' and cif='<%=cif%>';
                                                                        <%
                                                                        if(uploadForUpdate){
                                                                            //proses update
                                                                            int iResult = 0;
                                                                            DBResultSet dbrs = null;
                                                                            String sql = " update dslik_debitur set status_data=null where periode_id='"+periode.getOID()+"' and cif='"+cif+"'";
                                                                            try {
                                                                                iResult = DBHandler.execUpdate(sql);
                                                                                %>
                                                                                    ( berhasil ) <br>
                                                                                <%
                                                                            } catch (DBException e) {
                                                                                e.printStackTrace();
                                                                            } finally {
                                                                                DBResultSet.close(dbrs);
                                                                            }
                                                                        }else{
                                                                            %>
                                                                            <br>
                                                                            <%
                                                                        }
                                                                    }catch(Exception ex){
                                                                    }    
                                                                }else{
                                                                   if(rowData[0].equals("ERROR")){
                                                                    %>
                                                                            <%=part%><br>
                                                                    <%
                                                                   }
                                                                }
                                                            }else{%>
                                                                <br>
                                                            <%}
                                                        }
                                                        count=count+1;    
                                                    }
                                                }
                                            }else if (segment.equals("D02")){
                                                while ((read = in.readLine()) != null) {
                                                    String[] splited = read.split("\n");
                                                    for (String part : splited) {
                                                        if(count!=0){
                                                            if(!part.equals("")){
                                                                String[] rowData = part.split("\\|");
                                                                if(rowData[0].equals("RECORD")){
                                                                    try{
                                                                        String cif = rowData[3];
                                                                        %>
                                                                            update dslik_debitur set status_data=null where periode_id='<%=periode.getOID()%>' and cif='<%=cif%>';
                                                                        <%
                                                                        if(uploadForUpdate){
                                                                            //proses update
                                                                            int iResult = 0;
                                                                            DBResultSet dbrs = null;
                                                                            String sql = " update dslik_debitur set status_data=null where periode_id='"+periode.getOID()+"' and cif='"+cif+"'";
                                                                            try {
                                                                                iResult = DBHandler.execUpdate(sql);
                                                                                %>
                                                                                    ( berhasil ) <br>
                                                                                <%
                                                                            } catch (DBException e) {
                                                                                e.printStackTrace();
                                                                            } finally {
                                                                                DBResultSet.close(dbrs);
                                                                            }
                                                                        }else{
                                                                            %>
                                                                            <br>
                                                                            <%
                                                                        }
                                                                    }catch(Exception ex){
                                                                    }
                                                                }else{
                                                                   if(rowData[0].equals("ERROR")){
                                                                    %>
                                                                            <%=part%><br>
                                                                    <%
                                                                   }
                                                                }
                                                            }else{%>
                                                                <br>
                                                            <%}
                                                        }
                                                        count=count+1;    
                                                    }
                                                }
                                            }else if (segment.equals("F01")){
                                                int countRecord=0;
                                                while ((read = in.readLine()) != null) {
                                                    String[] splited = read.split("\n");
                                                    for (String part : splited) {
                                                        if(count!=0){
                                                            if(!part.equals("")){
                                                                String[] rowData = part.split("\\|");
                                                                if(rowData[0].equals("RECORD")){
                                                                    countRecord=1;
                                                                    try{
                                                                        String noRekening = rowData[3];
                                                                        String cif = rowData[4];
                                                                        %>
                                                                            update dslik_kredit set status_data=null where periode_id='<%=periode.getOID()%>' and no_rekening='<%=noRekening%>' and cif='<%=cif%>';
                                                                        <%
                                                                        if(uploadForUpdate){
                                                                            //proses update
                                                                            int iResult = 0;
                                                                            DBResultSet dbrs = null;
                                                                            String sql = "update dslik_kredit set status_data=null where periode_id='"+periode.getOID()+"' and no_rekening='"+noRekening+"' and cif='"+cif+"'";
                                                                            try {
                                                                                iResult = DBHandler.execUpdate(sql);
                                                                                %>
                                                                                    ( berhasil ) <br>
                                                                                <%
                                                                            } catch (DBException e) {
                                                                                e.printStackTrace();
                                                                            } finally {
                                                                                DBResultSet.close(dbrs);
                                                                            }
                                                                        }else{
                                                                            %>
                                                                            <br>
                                                                            <%
                                                                        }
                                                                    }catch(Exception ex){
                                                                    }    
                                                                }else{
                                                                   if(rowData[0].equals("ERROR")){
                                                                    %>
                                                                        <%=part%><br>
                                                                    <%
                                                                   }
                                                                }
                                                            }else{%>
                                                                <br>
                                                            <%}
                                                        }
                                                        count=count+1;    
                                                    }
                                                }
                                            }else if (segment.equals("F05")){
                                                while ((read = in.readLine()) != null) {
                                                    String[] splited = read.split("\n");
                                                    for (String part : splited) {
                                                        if(count!=0){
                                                            if(!part.equals("")){
                                                                String[] rowData = part.split("\\|");
                                                                if(rowData[0].equals("RECORD")){
                                                                    try{
                                                                        String noRekening = rowData[3];
                                                                        String cif = rowData[4];
                                                                        %>
                                                                            update dslik_bank_garansi set status_data=null where periode_id='<%=periode.getOID()%>' and no_rekening='<%=noRekening%>' and cif='<%=cif%>';
                                                                        <%
                                                                        if(uploadForUpdate){
                                                                            //proses update
                                                                            int iResult = 0;
                                                                            DBResultSet dbrs = null;
                                                                            String sql = "update dslik_bank_garansi set status_data=null where periode_id='"+periode.getOID()+"' and no_rekening='"+noRekening+"' and cif='"+cif+"'";
                                                                            try {
                                                                                iResult = DBHandler.execUpdate(sql);
                                                                                %>
                                                                                    ( berhasil ) <br>
                                                                                <%
                                                                            } catch (DBException e) {
                                                                                e.printStackTrace();
                                                                            } finally {
                                                                                DBResultSet.close(dbrs);
                                                                            }
                                                                        }else{
                                                                            %>
                                                                            <br>
                                                                            <%
                                                                        }
                                                                    }catch(Exception ex){
                                                                    }
                                                                }else{
                                                                   if(rowData[0].equals("ERROR")){
                                                                    %>
                                                                            <%=part%><br>
                                                                    <%
                                                                   }
                                                                }
                                                            }else{%>
                                                                <br>
                                                            <%}
                                                        }
                                                        count=count+1;    
                                                    }
                                                }
                                            }else if (segment.equals("M01")){
                                                while ((read = in.readLine()) != null) {
                                                    String[] splited = read.split("\n");
                                                    for (String part : splited) {
                                                        if(count!=0){
                                                            if(!part.equals("")){
                                                                String[] rowData = part.split("\\|");
                                                                if(rowData[0].equals("RECORD")){
                                                                    try{
                                                                        String noIdentitas = rowData[3];
                                                                        String cif = rowData[4];
                                                                        %>
                                                                            update dslik_pengurus_atau_pemilik set status_data=null where periode_id='<%=periode.getOID()%>' and no_identitas='<%=noIdentitas%>' and cif='<%=cif%>';
                                                                        <%

                                                                        if(uploadForUpdate){
                                                                            //proses update
                                                                            int iResult = 0;
                                                                            DBResultSet dbrs = null;
                                                                            String sql = "update dslik_pengurus_atau_pemilik set status_data=null where periode_id='"+periode.getOID()+"' and no_identitas='"+noIdentitas+"' and cif='"+cif+"'";
                                                                            try {
                                                                                iResult = DBHandler.execUpdate(sql);
                                                                                %>
                                                                                    ( berhasil ) <br>
                                                                                <%
                                                                            } catch (DBException e) {
                                                                                e.printStackTrace();
                                                                            } finally {
                                                                                DBResultSet.close(dbrs);
                                                                            }
                                                                        }else{
                                                                            %>
                                                                            <br>
                                                                            <%
                                                                        }
                                                                    }catch(Exception ex){
                                                                    }
                                                                }else{
                                                                   if(rowData[0].equals("ERROR")){
                                                                    %>
                                                                            <%=part%><br>
                                                                    <%
                                                                   }
                                                                }
                                                            }else{%>
                                                                <br>
                                                            <%}
                                                        }
                                                        count=count+1;    
                                                    }
                                                }
                                            }else if (segment.equals("P01")){
                                                 while ((read = in.readLine()) != null) {
                                                    String[] splited = read.split("\n");
                                                    for (String part : splited) {
                                                        if(count!=0){
                                                            if(!part.equals("")){
                                                                String[] rowData = part.split("\\|");
                                                                if(rowData[0].equals("RECORD")){
                                                                    try{
                                                                        String idPenjamin = rowData[3];
                                                                        String noRekening = rowData[4];
                                                                        String cif = rowData[5];
                                                                        %>
                                                                            update dslik_penjamin set status_data=null where periode_id='<%=periode.getOID()%>' and no_id_penjamin='<%=idPenjamin%>' and no_rekening='<%=noRekening%>' and cif='<%=cif%>';
                                                                        <%
                                                                        if(uploadForUpdate){
                                                                            //proses update
                                                                            int iResult = 0;
                                                                            DBResultSet dbrs = null;
                                                                            String sql = "update dslik_penjamin set status_data=null where periode_id='"+periode.getOID()+"' and no_id_penjamin='"+idPenjamin+"' and no_rekening='"+noRekening+"' and cif='"+cif+"'";
                                                                            try {
                                                                                iResult = DBHandler.execUpdate(sql);
                                                                                %>
                                                                                    ( berhasil ) <br>
                                                                                <%
                                                                            } catch (DBException e) {
                                                                                e.printStackTrace();
                                                                            } finally {
                                                                                DBResultSet.close(dbrs);
                                                                            }
                                                                        }else{
                                                                            %>
                                                                            <br>
                                                                            <%
                                                                        } 
                                                                    }catch(Exception ex){
                                                                    }
                                                                }else{
                                                                   if(rowData[0].equals("ERROR")){
                                                                    %>
                                                                            <%=part%><br>
                                                                    <%
                                                                   }
                                                                }
                                                            }else{%>
                                                                <br>
                                                            <%}
                                                        }
                                                        count=count+1;    
                                                    }
                                                }
                                            }
                                        } catch (IOException e) {
                                            System.out.println("There was a problem: " + e);
                                            e.printStackTrace();
                                        } finally {
                                            try {
                                                in.close();
                                            } catch (Exception e) {
                                            }
                                        }
                                        %>
                                    <%}else{%>
                                        Upload Data Gagal
                                    <%}%>
                                </div>
                            </div>
                        </div>
                        <div class='box-footer'>
                            <button class="btn btn-primary btnaddgeneral" onClick="javascript:cmdUpload()" data-oid="0" data-for="showform">
                                <i class="fa fa-star"></i> Upload Data Eror
                            </button>
                        </div>        
                      </div>
                  </div><!-- ./col -->
                </div><!-- /.row -->
          </form> 
        <%
            }
        %>
      </div><!-- /.content-wrapper -->
      <!-- Add the sidebar's background. This div must be placed
           immediately after the control sidebar -->
      <div class='control-sidebar-bg'></div>
      <%@include file="../../template-component/footer-component.jsp" %>
      <%@include file="../../template-component/plugins-component.jsp" %>
    </div><!-- ./wrapper -->
  </body>
</html>
