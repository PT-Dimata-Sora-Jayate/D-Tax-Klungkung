<%@page import="com.dimata.common.session.upload.SessUpload"%>
<%@page import="com.dimata.util.blob.ImageLoader"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<%@page import="com.dimata.balismartisland.form.FrmAreaUsage"%>
<%@page import="com.dimata.balismartisland.form.FrmAreaType"%>
<%@page import="com.dimata.util.Command"%>
<!DOCTYPE html>
<%@include file="../../main/javainit.jsp" %>

<%
    int iCommand = FRMQueryString.requestInt(request,"command");
    long oid = FRMQueryString.requestLong(request, "FRM_FIELD_OID");
    
    if (iCommand==0){
        try {
            ImageLoader uploader = new ImageLoader();
            int numFiles = uploader.uploadImage(config, request, response);

            //System.out.println("oid di proses upload image : "+oidmaterial);
            String fieldFormName = "FRM_ICON";
            Object obj = uploader.getImage(fieldFormName);

            String patchPhoto = getServletContext().getRealPath("document_upload");
            String textName = ""+String.valueOf(oid)+".png";

            byte[] byteOfObj = (byte[]) obj;
            int intByteOfObjLength = byteOfObj.length;
            SessUpload sessUploadCatalogPhoto = new SessUpload();
            if (intByteOfObjLength > 0) {
                String pathFileName = sessUploadCatalogPhoto.getAbsoluteFileName(textName, patchPhoto);
                java.io.ByteArrayInputStream byteIns = new java.io.ByteArrayInputStream(byteOfObj);
                uploader.writeCache(byteIns, pathFileName, true);
                try {
                    //PROSES UPDATE

                } catch (Exception eY) {
                    System.out.print("");
                }

            }
            
        } catch(Exception ex){
        
        }
    }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Uploading</title>
    </head>
    <body>
        <script>
            window.location="<%=approot%>/page/masterdata/areausage.jsp";
        </script>
    </body>
</html>
