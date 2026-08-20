<%-- 
    Document   : privilagelist_new
    Created on : Jul 17, 2015, 11:49:39 AM
    Author     : dimata005
--%>

<%@page import="com.dimata.gui.jsp.ControlDate"%>
<%@page import="com.dimata.util.Command"%>
<%@page import="com.dimata.dslik.form.admin.FrmAppPriv"%>
<%@page import="com.dimata.dslik.form.admin.CtrlAppPriv"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<%@page import="com.dimata.gui.jsp.ControlLine"%>
<%@page import="com.dimata.dslik.entity.admin.PstAppPriv"%>
<%@page import="com.dimata.util.Formater"%>
<%@page import="java.util.Date"%>
<%@page import="com.dimata.dslik.entity.admin.AppPriv"%>
<%@page import="com.dimata.gui.jsp.ControlList"%>
<%@include file="../../main/javainit_slik.jsp" %>
<%@include file="../../main/checkuser_slik.jsp" %>


<%!
public static final String listTextGlobal[][] = {
	{"","Hak Akses","Daftar","Edit","Nama Hak Akses","Deskripsi","Hak Akses Modul",
	 "","Tidak Ada Hak Akses","Tambah","Ubah"},
	{"User Management","Privilege","List","Edit","Privilege Name","Description","Module Access",
	 "Module Access List","No Privilege Available","Add","Edit"}
};

public static final String listTextTableHeader[][] = {
	{"No.","Nama","Deskripsi","Tanggal Pembuatan"},
	{"No.","Name","Description","Creation Date"}
};

public String drawListAppPriv(int language, int start, Vector objectClass, long privId, boolean privUpdate) {
	String temp = "";
	String regdatestr = "";
	
	ControlList ctrlist = new ControlList();
	ctrlist.setAreaWidth("100%");
	ctrlist.setListStyle("listgen");
	ctrlist.setTitleStyle("listgentitle");
	ctrlist.setCellStyle("listgensell");
	ctrlist.setHeaderStyle("listgentitle");
	ctrlist.setCellSpacing("1");
	
	ctrlist.addHeader(listTextTableHeader[language][0],"5%");
	ctrlist.addHeader(listTextTableHeader[language][1],"20%");
	ctrlist.addHeader(listTextTableHeader[language][2],"60%");
	ctrlist.addHeader(listTextTableHeader[language][3],"10%");
	ctrlist.addHeader("Action","5%");

	ctrlist.setLinkRow(1);
	ctrlist.setLinkSufix("");
	Vector lstData = ctrlist.getData();
	Vector lstLinkData 	= ctrlist.getLinkData();						
	
	
	ctrlist.reset();
	int index = -1;							
	for (int i = 0; i < objectClass.size(); i++) {
		 AppPriv appPriv = (AppPriv)objectClass.get(i);

		 Vector rowx = new Vector();
		 
		 if(privId == appPriv.getOID())
		 	index = i;
			
		 rowx.add("<div align=\"center\">"+String.valueOf(i+1)+"</div>");
		 rowx.add(String.valueOf(appPriv.getPrivName()));		 
		 rowx.add(String.valueOf(appPriv.getDescr()));
		 try{
			 Date regdate = appPriv.getRegDate();
			 regdatestr = Formater.formatDate(regdate, "dd MMMM yyyy");
		 }catch(Exception e){
			 regdatestr = "";
		 }
		 rowx.add(regdatestr);
		 
		 if(privUpdate){
		     rowx.add("<button class='btn btn-warning' type='button' onclick='javascript:cmdEdit(\""+appPriv.getOID()+"\")'><i class='fa fa-pencil'></i> Edit</button>");
		 }else{
		     rowx.add("");
		 }
		 
		 
		 
		 		 
		 lstData.add(rowx);
	}						

	return ctrlist.drawBootstrapStrip();
}

%>
<%

/* VARIABLE DECLARATION */
int recordToGet = 10;
String order = " " + PstAppPriv.fieldNames[PstAppPriv.FLD_PRIV_NAME];

Vector listAppPriv = new Vector(1,1);
ControlLine ctrLine = new ControlLine();

/* GET REQUEST FROM HIDDEN TEXT */
int iCommand = FRMQueryString.requestCommand(request);
int start = FRMQueryString.requestInt(request, "start");
long appPrivOID = FRMQueryString.requestLong(request,"appriv_oid");
int prevCommand = FRMQueryString.requestInt(request, "prev_command");
int prevSaveCommand = FRMQueryString.requestInt(request, "prev_save_command");

CtrlAppPriv ctrlAppPriv = new CtrlAppPriv(request);
FrmAppPriv frmAppPriv = ctrlAppPriv.getForm();
 
int vectSize = PstAppPriv.getCount("");

int excCode = ctrlAppPriv.action(iCommand,appPrivOID,start,vectSize,recordToGet);
vectSize = PstAppPriv.getCount(""); 
String msgString = ctrlAppPriv.getMessage(); 
AppPriv appPriv = ctrlAppPriv.getAppPriv();

if ((iCommand == Command.FIRST || iCommand == Command.PREV )||
	(iCommand == Command.NEXT || iCommand == Command.LAST))
		start = ctrlAppPriv.getStart();

if((iCommand == Command.SAVE)&&(frmAppPriv.errorSize()<1))
	start = PstAppPriv.findLimitStart(appPriv.getOID(),recordToGet,"", order);
		
order= PstAppPriv.fieldNames[PstAppPriv.FLD_PRIV_NAME] ;		
listAppPriv = PstAppPriv.list(start,recordToGet, "" , order);

/* TO HANDLE CONDITION AFTER DELETE LAST, IF START LIMIT IS BIGGER THAN VECT SIZE, GET LIST FIRST */
if(((listAppPriv==null)||(listAppPriv.size()<1))){		
	start=0;
	listAppPriv = PstAppPriv.list(start,recordToGet, "" , order);
}

    boolean privAdd = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_ADMIN, AppObjInfo.G2_ADMIN_USER, AppObjInfo.OBJ_ADMIN_USER_PRIVILEGE, AppObjInfo.COMMAND_ADD);
    boolean privUpdate = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_ADMIN, AppObjInfo.G2_ADMIN_USER, AppObjInfo.OBJ_ADMIN_USER_PRIVILEGE, AppObjInfo.COMMAND_UPDATE);
    boolean privDelete = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_ADMIN, AppObjInfo.G2_ADMIN_USER, AppObjInfo.OBJ_ADMIN_USER_PRIVILEGE, AppObjInfo.COMMAND_DELETE);
    boolean privView = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_ADMIN, AppObjInfo.G2_ADMIN_USER, AppObjInfo.OBJ_ADMIN_USER_PRIVILEGE, AppObjInfo.COMMAND_VIEW);

%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>AdminLTE | Data Tables</title>
        <%@include  file="../../template-component/css-component.jsp" %>
	<script type="text/javascript">
	    function cmdPrint(){
		window.print();
	    }
	</script>
        <script language="JavaScript">
            function addNew(){
                    document.frmAppPriv.appriv_oid.value="0";
                    document.frmAppPriv.prev_command.value="<%=prevCommand%>";
                    document.frmAppPriv.command.value="<%=Command.ADD%>";
                    document.frmAppPriv.action="privilagelist_new.jsp";
                    document.frmAppPriv.submit();
            }

            function cmdEdit(oid){
                    document.frmAppPriv.appriv_oid.value=oid;
                    document.frmAppPriv.prev_command.value="<%=prevCommand%>";
                    document.frmAppPriv.command.value="<%=Command.EDIT%>";
                    document.frmAppPriv.action="privilagelist_new.jsp";
                    document.frmAppPriv.submit();
            }


            function cmdSave(){
                    document.frmAppPriv.command.value="<%=Command.SAVE%>";
                    document.frmAppPriv.prev_command.value="<%=prevCommand%>";
                    document.frmAppPriv.action="privilagelist_new.jsp";
                    document.frmAppPriv.submit();
            }

            function cmdEditObj(oid){
                    document.frmAppPriv.appriv_oid.value=oid;
                    document.frmAppPriv.prev_command.value="<%=prevCommand%>";
                    document.frmAppPriv.command.value="<%=Command.LIST%>";
                    document.frmAppPriv.action="privilegeedit_new.jsp";
                    document.frmAppPriv.submit();
            }

            function cmdAsk(oid){
                    document.frmAppPriv.appriv_oid.value=oid;
                    document.frmAppPriv.prev_command.value="<%=prevCommand%>";
                    document.frmAppPriv.command.value="<%=Command.ASK%>";
                    document.frmAppPriv.action="privilagelist_new.jsp";
                    document.frmAppPriv.submit();
            }
            function cmdDelete(oid){
                    document.frmAppPriv.appriv_oid.value=oid;
                    document.frmAppPriv.prev_command.value="<%=prevCommand%>";
                    document.frmAppPriv.command.value="<%=Command.DELETE%>";
                    document.frmAppPriv.action="privilagelist_new.jsp";
                    document.frmAppPriv.submit();
            }

            function cmdCancel(){
                    document.frmAppPriv.prev_command.value="<%=prevCommand%>";
                    document.frmAppPriv.command.value="<%=Command.EDIT%>";
                    document.frmAppPriv.action="privilagelist_new.jsp";
                    document.frmAppPriv.submit();
            }


            function cmdListFirst(){
                    document.frmAppPriv.command.value="<%=Command.FIRST%>";
                    document.frmAppPriv.prev_command.value="<%=Command.FIRST%>";
                    document.frmAppPriv.action="privilagelist_new.jsp";
                    document.frmAppPriv.submit();
            }
            function cmdListPrev(){
                    document.frmAppPriv.command.value="<%=Command.PREV%>";
                    document.frmAppPriv.prev_command.value="<%=Command.PREV%>";
                    document.frmAppPriv.action="privilagelist_new.jsp";
                    document.frmAppPriv.submit();
            }

            function cmdListNext(){
                    document.frmAppPriv.command.value="<%=Command.NEXT%>";
                    document.frmAppPriv.prev_command.value="<%=Command.NEXT%>";
                    document.frmAppPriv.action="privilagelist_new.jsp";
                    document.frmAppPriv.submit();
            }
            function cmdListLast(){
                    document.frmAppPriv.command.value="<%=Command.LAST%>";
                    document.frmAppPriv.prev_command.value="<%=Command.LAST%>";
                    document.frmAppPriv.action="privilagelist_new.jsp";
                    document.frmAppPriv.submit();
            }

            function cmdBack(){
                    document.frmAppPriv.command.value="<%=Command.BACK%>";
                    document.frmAppPriv.action="privilagelist_new.jsp";
                    document.frmAppPriv.submit();
            }

            function hideObjectForEmployee(){
            } 

            function hideObjectForLockers(){ 
            }

            function hideObjectForCanteen(){
            }

            function hideObjectForClinic(){
            }

            function hideObjectForMasterdata(){
            }

            function showObjectForMenu(){
            }

            //<!--
            function MM_swapImgRestore() { //v3.0
              var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
            }

            function MM_preloadImages() { //v3.0
              var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
                var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
                if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
            }

            function MM_findObj(n, d) { //v4.0
              var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
                d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
              if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
              for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
              if(!x && document.getElementById) x=document.getElementById(n); return x;
            }

            function MM_swapImage() { //v3.0
              var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
               if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
            }
            //-->
       </script>
    </head>
    <body class="<%=skin%>">
        
        <div class="wrapper">
            <input type="hidden" name="usergroup" id="usergroup" value="<%= getUserGroup %>">
            <input type="hidden" name="kodecabang" id="kodecabang" value="<%= kd_bank %>">
            <input type="hidden" name="periode" id="periode" value="<%= periode.getOID() %>">
            <%@include  file="../../template-component/header-component.jsp" %>
            <%@include  file="../../template-component/sidebar-component.jsp" %>
        <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Privelege
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-home"></i> Privelege</a></li>
                    </ol>
                </section>
                <%
		    if(privView){
		%>
                <!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="col-md-12">
                            <div class='box box-primary'>
                                <form name="frmAppPriv" method="post" action="">
                                    <input type="hidden" name="command" value="">
                                    <input type="hidden" name="appriv_oid" value="<%=appPrivOID%>">
                                    <input type="hidden" name="vectSize" value="<%=vectSize%>">
                                    <input type="hidden" name="start" value="<%=start%>">
                                    <input type="hidden" name="prev_command" value="<%=prevCommand%>">
                                    <input type="hidden" name="prev_save_command" value="<%=prevSaveCommand%>">
                                    <input type="hidden" name="menu" value="22">
                                    <input type="hidden" name="tree" value="6">
                                    <table width="100%" cellspacing="0" cellpadding="0">
                                        <tr> 
                                            <td colspan="3" class="bigtitleflash">&nbsp; </td>
                                        </tr>
                                    </table>
                                    <% 
                                        if ((listAppPriv!=null)&&(listAppPriv.size()>0)){ %>
                                        <%=drawListAppPriv(SESS_LANGUAGE, start, listAppPriv, appPrivOID, privUpdate)%> 
                                    <%}%>
                                    <div class="box-body table-responsive">
                                    <table width="100%">
                                      <tr> 
                                        <td colspan="3"> <span class="command"> 
                                                              <% 
                                                              int cmd = 0;					  
                                                              if ((iCommand == Command.FIRST || iCommand == Command.PREV )|| (iCommand == Command.NEXT || iCommand == Command.LAST))
                                                                      cmd =iCommand;								   
                                                              else {
                                                                      if(iCommand == Command.NONE || prevCommand == Command.NONE)						  
                                                                              cmd=Command.FIRST;
                                                                      else {
                                                                              if((prevSaveCommand==Command.ADD)&&(iCommand==Command.SAVE)&&(frmAppPriv.errorSize()<1)) {
                                                                                      cmd = Command.LAST;
                                                                              }						
                                                                              else {
                                                                                      if((iCommand == Command.SAVE) && (frmAppPriv.errorSize()<1))
                                                                                              cmd = PstAppPriv.findLimitCommand(start,recordToGet,vectSize); 
                                                                                      else
                                                                                              cmd = prevCommand;
                                                                              }

                                                                      }
                                                              }
                                                              ctrLine.setLocationImg(approot+"/images");
                                                              ctrLine.initDefault();						   					   
                                                              %>
                                          <%=ctrLine.drawImageListLimit(cmd,vectSize,start,recordToGet)%> </span> </td>
                                      </tr>
                                      <%if(privAdd  && (iCommand!=Command.ADD)&&(iCommand!=Command.ASK)&&(iCommand!=Command.EDIT)&&(frmAppPriv.errorSize()<1)){%>
                                      <tr> 
                                        <td colspan="3" class="command"> 
                                          <table width="15%" border="0" cellspacing="2" cellpadding="3">
                                            <tr> 
                                              <td width="20%">
                                                  <!--<a href="javascript:addNew()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image100111','','<%=approot%>/images/BtnNewOn.jpg',1)">
                                                      <img name="Image100111" border="0" src="<%=approot%>/images/BtnNew.jpg" width="24" height="24" alt="<%=ctrLine.getCommand(SESS_LANGUAGE,listTextGlobal[SESS_LANGUAGE][1],ctrLine.CMD_ADD,true)%>">
                                                  </a>-->
                                              </td>
                                              <td nowrap width="80%">
                                                  <%
                                                      if(privAdd){
                                                          %>
                                                              <button type="button" onclick="javascript:addNew()" class="command btn btn-primary"> <i class="fa fa-plus"></i> <%=ctrLine.getCommand(SESS_LANGUAGE,listTextGlobal[SESS_LANGUAGE][1],ctrLine.CMD_ADD,true)%></button></td>
                                                          <%
                                                      }
                                                  %>

                                            </tr>
                                          </table>
                                        </td>
                                      </tr>
                                      <%}%>
                                      <tr>
                                        <td colspan="3">&nbsp;</td>
                                      </tr>
                                      <%if(((iCommand==Command.SAVE)&&(frmAppPriv.errorSize()>0))||(iCommand==Command.ADD)||(iCommand==Command.EDIT)||(iCommand==Command.ASK)){%>
                                      <tr> 
                                        <td colspan="3" class="listtitle">
                                                         <%= appPrivOID == 0 ? listTextGlobal[SESS_LANGUAGE][9]+" "+listTextGlobal[SESS_LANGUAGE][1] : listTextGlobal[SESS_LANGUAGE][10]+" "+listTextGlobal[SESS_LANGUAGE][1]%>
                                                        </td>
                                      </tr>
                                      <tr> 
                                        <td colspan="3">&nbsp;</td>
                                      </tr>
                                      <tr> 
                                        <td colspan="3" width="100%" valign="top">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                      <div class="col-md-3">
                                                          <h6><strong><%=listTextTableHeader[SESS_LANGUAGE][1]%> *</strong></h6>
                                                      </div>
                                                      <div class="col-md-6">
                                                          <input type="text" name="<%=frmAppPriv.fieldNames[frmAppPriv.FRM_PRIV_NAME] %>" value="<%=appPriv.getPrivName()%>" class="formElemen form-control" size="30">
                                                             &nbsp;<%= frmAppPriv.getErrorMsg(frmAppPriv.FRM_PRIV_NAME) %>
                                                      </div>
                                                    </div>
                                                </div>
                                            </div>
                                              <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                      <div class="col-md-3">
                                                          <h6><strong><%=listTextTableHeader[SESS_LANGUAGE][2]%></strong></h6>
                                                      </div>
                                                      <div class="col-md-6">
                                                          <textarea name="<%=frmAppPriv.fieldNames[frmAppPriv.FRM_DESCRIPTION] %>" cols="45" rows="3" class="formElemen form-control"><%=appPriv.getDescr()%></textarea>
                                                      </div>
                                                    </div>
                                                </div>
                                            </div>
                                              <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                      <div class="col-md-3">
                                                          <h6><strong><%=listTextTableHeader[SESS_LANGUAGE][3]%></strong></h6>
                                                      </div>
                                                      <div class="col-md-6">
                                                          <div class='form-group'>
                                                              <br>
                                                              <%=ControlDate.drawDate(frmAppPriv.fieldNames[FrmAppPriv.FRM_REG_DATE], appPriv.getRegDate(), 0, -30)%>
                                                          </div>
                                                      </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                      </tr>

                                      <tr> 
                                        <td colspan="3" class="command">&nbsp;</td>
                                      </tr>
                                      <tr> 
                                        <td colspan="3" class="command" height="22"> 
                                          <%
                                                                      String cmdTitle = listTextGlobal[SESS_LANGUAGE][1];

                                                                      ctrLine.setSaveImageAlt(ctrLine.getCommand(SESS_LANGUAGE,cmdTitle,ctrLine.CMD_SAVE,true));
                                                                      ctrLine.setDeleteImageAlt(ctrLine.getCommand(SESS_LANGUAGE,cmdTitle,ctrLine.CMD_ASK,true));
                                                                      ctrLine.setBackImageAlt(ctrLine.getCommand(SESS_LANGUAGE,cmdTitle,ctrLine.CMD_BACK,true));							
                                                                      ctrLine.setEditImageAlt(ctrLine.getCommand(SESS_LANGUAGE,cmdTitle,ctrLine.CMD_CANCEL,false));

                                                                      ctrLine.setLocationImg(approot+"/images");
                                                                      ctrLine.initDefault();
                                                                      ctrLine.setTableWidth("80%");
                                                                      ctrLine.setCommandStyle("command");
                                                                      ctrLine.setColCommStyle("command");
                                                                      String scomDel = "javascript:cmdAsk('"+appPrivOID+"')";
                                                                      String sconDelCom = "javascript:cmdDelete('"+appPrivOID+"')";
                                                                      String scancel = "javascript:cmdCancel('"+appPrivOID+"')";

                                                                      ctrLine.setBackCaption(ctrLine.getCommand(SESS_LANGUAGE,cmdTitle,ctrLine.CMD_BACK,true));
                                                                      ctrLine.setSaveCaption(ctrLine.getCommand(SESS_LANGUAGE,cmdTitle,ctrLine.CMD_SAVE,true));
                                                                      ctrLine.setDeleteCaption(ctrLine.getCommand(SESS_LANGUAGE,cmdTitle,ctrLine.CMD_ASK,true));
                                                                      ctrLine.setConfirmDelCaption(ctrLine.getCommand(SESS_LANGUAGE,cmdTitle,ctrLine.CMD_DELETE,true));
                                                                      ctrLine.setAddCaption(ctrLine.getCommand(SESS_LANGUAGE,cmdTitle,ctrLine.CMD_ADD,true));
                                                                      ctrLine.setCancelCaption(ctrLine.getCommand(SESS_LANGUAGE,cmdTitle,ctrLine.CMD_CANCEL,false));

                                                                      if (privDelete){
                                                                              ctrLine.setConfirmDelCommand(sconDelCom);
                                                                              ctrLine.setDeleteCommand(scomDel);
                                                                              ctrLine.setEditCommand(scancel);
                                                                      }else{ 
                                                                              ctrLine.setConfirmDelCaption("");
                                                                              ctrLine.setDeleteCaption("");
                                                                              ctrLine.setEditCaption("");
                                                                      }

                                                                      if(privAdd == false  && privUpdate == false){
                                                                              ctrLine.setSaveCaption("");
                                                                      }

                                                                      if (privAdd == false){
                                                                              ctrLine.setAddCaption("");
                                                                      }
                                                              %>
                                          <!--%= ctrLine.drawImage(iCommand, excCode, msgString)%-->
                                                        </td>
                                      </tr>
                                      <% if((privAdd && privUpdate) && (iCommand != Command.ASK || iCommand == Command.DELETE) && (appPrivOID != 0)){%>
                                      <tr> 
                                        <td colspan="3" class="command"> 
                                          <table width="15%" border="0" cellspacing="2" cellpadding="2">
                                            <tr> 
                                              <td width="20%">
                                                  <!--<a href="javascript:cmdEditObj('<%=appPrivOID%>')" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image10011','','<%=approot%>/images/BtnEditOn.jpg',1)">
                                                      <img name="Image10011" border="0" src="<%=approot%>/images/BtnEdit.jpg" width="24" height="24" alt="<%=listTextGlobal[SESS_LANGUAGE][10]+" "+listTextGlobal[SESS_LANGUAGE][6]%>">
                                                  </a>-->
                                              </td>
                                              <td nowrap width="80%">
                                                  <%
                                                      if(privAdd || privUpdate){
                                                          %>
                                                              <button type="button" onclick="javascript:cmdSave()" class="btn btn-success"><i class="fa fa-check"></i> Simpan <%= listTextGlobal[SESS_LANGUAGE][1] %></button>
                                                          <%
                                                      }

                                                      if(privUpdate){
                                                          %>
                                                              <button type="button" onclick="javascript:cmdEditObj('<%=appPrivOID%>')" class="command btn btn-warning"><i class='fa fa-pencil'></i> <%=listTextGlobal[SESS_LANGUAGE][10]+" "+listTextGlobal[SESS_LANGUAGE][6]%></button>
                                                          <%
                                                      }

                                                      if(privDelete){
                                                          %>
                                                              <button type="button" onclick="javascript:cmdDelete('<%= appPrivOID %>')" class="btn btn-danger"><i class="fa fa-trash"></i> Hapus <%= listTextGlobal[SESS_LANGUAGE][1] %></button>
                                                          <%
                                                      }
                                                  %>



                                                  <button type="button" onclick="javascript:cmdBack()" class="btn btn-primary"><i class="fa fa-arrow-left"></i> Kembali ke Daftar <%= listTextGlobal[SESS_LANGUAGE][1] %></button>
                                              </td>
                                            </tr>
                                          </table>
                                        </td>
                                      </tr>
                                      <%}else{
                                          %>
                                          <tr> 
                                          <td colspan="3" class="command"> 
                                            <table width="15%" border="0" cellspacing="2" cellpadding="2">
                                              <tr> 
                                                  <td width="20%">
                                                      <!--<a href="javascript:cmdEditObj('<%=appPrivOID%>')" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image10011','','<%=approot%>/images/BtnEditOn.jpg',1)">
                                                          <img name="Image10011" border="0" src="<%=approot%>/images/BtnEdit.jpg" width="24" height="24" alt="<%=listTextGlobal[SESS_LANGUAGE][10]+" "+listTextGlobal[SESS_LANGUAGE][6]%>">
                                                      </a>-->
                                                  </td>
                                                  <td nowrap width="80%">
                                                      <%
                                                          if(privAdd || privUpdate){
                                                              %>
                                                                  <button type="button" onclick="javascript:cmdSave()" class="btn btn-success"><i class="fa fa-check"></i> Simpan <%= listTextGlobal[SESS_LANGUAGE][1] %></button>
                                                              <%
                                                          }
                                                      %>

                                                      <button type="button" onclick="javascript:cmdBack()" class="btn btn-primary"><i class="fa fa-arrow-left"></i> Kembali ke Daftar <%= listTextGlobal[SESS_LANGUAGE][1] %></button>
                                                  </td>
                                              </tr>
                                            </table>
                                          </td>
                                          </tr>
                                          <%
                                      }%>
                                      <%}%>
                                    </table>
                                    </div>
                                  </form>
                                </div>
                            </div>
                        </div>
                    </section>
		<%
		    }
		%>
                <div class='control-sidebar-bg'></div>
        </div><!-- ./wrapper -->
        <!-- jQuery 2.0.2 -->
       
        <%@include file="../../template-component/plugins-component.jsp" %>
	<script type="text/javascript">
	    $(document).ready(function(){
		//SET ACTIVE MENU
		var activeMenu = function(parentId, childId){
		    $(parentId).addClass("active").find(".treeview-menu").slideDown();
		    $(childId).addClass("active");
		}

		activeMenu("#system", "#masterprivilege");
	    })
	</script>
    </body>
</html>