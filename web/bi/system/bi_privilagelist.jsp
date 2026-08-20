<%@page import="com.dimata.gui.jsp.ControlCombo"%>
<%@page import="com.dimata.dtaxintegration.form.biadmin.FrmAppPrivilegeObj"%>
<%@page import="com.dimata.dtaxintegration.form.biadmin.FrmAppPriv"%>
<%@page import="com.dimata.gui.jsp.ControlLine"%>
<%@page import="com.dimata.util.Formater"%>
<%@page import="java.util.Date"%>
<%@page import="com.dimata.dtaxintegration.entity.biadmin.AppPriv"%>
<%@page import="com.dimata.gui.jsp.ControlList"%>
<%@page import="java.util.Vector"%>
<%@page import="com.dimata.dtaxintegration.entity.biadmin.PstAppPriv"%>
<%@page import="com.dimata.util.Command"%>
<%@include file="../../main/javainit_bi.jsp" %>
<%@ include file = "../../main/checkuser_bi.jsp" %>

<%
    //INT DATA
    int recordToGet = 100;
    
    //STRING DATA
    String order = PstAppPriv.fieldNames[PstAppPriv.FLD_PRIV_NAME];
    
    //OBJECT DATA
    Vector listAppPriv = new Vector(1,1);
    ControlLine ctrLine = new ControlLine();
    
    listAppPriv = PstAppPriv.list(0,recordToGet, "" , order);
    
    boolean privAdd = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_ADMIN, AppObjInfo.G2_ADMIN_USER, AppObjInfo.OBJ_ADMIN_USER_PRIVILEGE, AppObjInfo.COMMAND_ADD);
    boolean privUpdate = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_ADMIN, AppObjInfo.G2_ADMIN_USER, AppObjInfo.OBJ_ADMIN_USER_PRIVILEGE, AppObjInfo.COMMAND_UPDATE);
    
    Date currentDate = new Date();
%>

<%!
    public static final String listTextTableHeader[][] = {
	{"No.","Nama","Deskripsi","Tanggal Pembuatan"},
	{"No.","Name","Description","Creation Date"}
    };
    
    
    public static final String listTextGlobal[][] = {
	{"","Hak Akses","Daftar","Edit","Nama Hak Akses","Deskripsi","Hak Akses Modul",
	 "","Tidak Ada Hak Akses","Tambah","Ubah"},
	{"User Management","Privilege","List","Edit","Privilege Name","Description","Module Access",
	 "Module Access List","No Privilege Available","Add","Edit"}
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
	ctrlist.addHeader(listTextTableHeader[language][3],"15%");
        ctrlist.addHeader(listTextGlobal[language][10]+" "+listTextGlobal[language][6],"15%");
        
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
		 
		 if(privUpdate){
		     rowx.add("<a href='javascript:' class='showprivedit' data-oid='"+appPriv.getOID()+"'>"+String.valueOf(appPriv.getPrivName())+"</a>");
		 }else{
		     rowx.add(""+String.valueOf(appPriv.getPrivName())+"");
		 }
		 		 
		 rowx.add(String.valueOf(appPriv.getDescr()));
		 try{
			 Date regdate = appPriv.getRegDate();
			 regdatestr = Formater.formatDate(regdate, "dd MMMM yyyy");
		 }catch(Exception e){
			 regdatestr = "";
		 }
		 
		 rowx.add(regdatestr);
		 if(privUpdate){
		    rowx.add("<a href='javascript:' class='showprivedit' data-oid='"+appPriv.getOID()+"'>Edit</a>");
		 }else{
		     rowx.add("");
		 }		 
		 lstData.add(rowx);
	}						

	return ctrlist.drawBootstrapStrip();
    }
%>
<input type="hidden" name="approot" id="approot" value="<%= approot %>">
<input type='hidden' name='command' id='command' value='<%= Command.NONE %>'>
<input type="hidden" name="FRM_FIELD_TANGGALBAYAR_DASHBOARD" id="FRM_FIELD_TANGGALBAYAR_DASHBOARD" value="01 January 1000">
<input type="hidden" name="FRM_FIELD_CURRENT_DATE" id="FRM_FIELD_CURRENT_DATE" value="<%= Formater.formatDate(currentDate, "dd MMMM yyyy") %>">
<ol class="breadcrumb newcrumb">
    <li>    
            <a href="#!/dashboard"><span><i class="fa fontello-home-outline"></i>
            </span>Dashboard</a>
    </li>
    <li class="active">
	Privilege List
    </li>
</ol>


<div class="row">
    <div class="col-md-12">
	<div class="box box-primary">
	    <div class="box-body">
		<div id="listpriv">
		    <%=drawListAppPriv(SESS_LANGUAGE, 0, listAppPriv, 0, privUpdate)%>
		</div>
		<%
		    if(privAdd){
		    %>
			<button class="btn btn-primary showprivedit" data-oid="0">
			    <%=ctrLine.getCommand(SESS_LANGUAGE,listTextGlobal[SESS_LANGUAGE][1],ctrLine.CMD_ADD,true)%>
			</button>
		    <%
		    }
		%>
	    </div>
	</div>
    </div>
</div>

<input type="hidden" value='<%= listTextGlobal[SESS_LANGUAGE][9]+" "+listTextGlobal[SESS_LANGUAGE][1] %>' id="titlenew">
<input type="hidden" value='<%= listTextGlobal[SESS_LANGUAGE][10]+" "+listTextGlobal[SESS_LANGUAGE][1] %>' id="titleedit">
<div id="privedit" class="modal" style="overflow-y: auto;">
    <div class="modal-dialog">
      <div class="modal-content">
	<div class="modal-header">
	  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	  <b id='titlemodal'></b>
	</div>
	  <form id="<%= FrmAppPriv.FRM_APP_PRIVILEGE %>">
	    <div class="modal-body">
		<input type="hidden" name="command" value="<%= Command.SAVE %>">
		<input type="hidden" name="user_oid" id="USER_OID">
		<input type='hidden' name='FRM_FIELD_VIEW_TYPE' value="saveprivilege">
		<input type="hidden" name="priv_oid" id="priv_oid">
		<div class="row">
		    <div class="col-md-12">
			<div class="box box-primary">
			    <div class="box-body">
				<div class="form-group">
				    <label><%=listTextTableHeader[SESS_LANGUAGE][1]%></label>
				    <input type="text" class="form-control" name="<%= FrmAppPriv.fieldNames[FrmAppPriv.FRM_PRIV_NAME] %>" id="<%= FrmAppPriv.fieldNames[FrmAppPriv.FRM_PRIV_NAME] %>" required="required">
				</div>
				<div class="form-group">
				    <label><%=listTextTableHeader[SESS_LANGUAGE][2]%></label>
				    <input type="text" class="form-control" name="<%= FrmAppPriv.fieldNames[FrmAppPriv.FRM_DESCRIPTION] %>" id="<%= FrmAppPriv.fieldNames[FrmAppPriv.FRM_DESCRIPTION] %>" required="required">
				</div>
				<div class="form-group">
				    <label><%=listTextTableHeader[SESS_LANGUAGE][3]%></label>
				    <input type="text" class="form-control datePicker" name="<%= FrmAppPriv.fieldNames[FrmAppPriv.FRM_REG_DATE] %>" id="<%= FrmAppPriv.fieldNames[FrmAppPriv.FRM_REG_DATE] %>" required="required">
				</div>
			    </div>
			</div>
		    </div>
		</div>
	    </div>
	    <div class="modal-footer">
		<button type="button" class="btn btn-danger pull-left" data-dismiss="modal">
		    <i class="fa fontello-cancel"></i> <b>Batal</b>
		</button>
		<%
		    if(privAdd && privUpdate){
		    %>
			
			<button type="button" class="btn btn-primary" id="btnhakakses" style="display: none;">
			    <i class="fa fontello-ok"></i> <b><%=listTextGlobal[SESS_LANGUAGE][10]+" "+listTextGlobal[SESS_LANGUAGE][6]%></b>
			</button>
			<button class="btn btn-success" id="buttonprivedt">
			    <i class="fa fontello-ok"></i> <b><%= ctrLine.getCommand(SESS_LANGUAGE,listTextGlobal[SESS_LANGUAGE][1],ctrLine.CMD_SAVE,true) %></b>
			</button>
		    <%
		    }
		%>
		
	    </div>
				
	</form>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div id="moduledit" class="modal" style="overflow-y: auto;">
    <div class="modal-dialog">
      <div class="modal-content">
	<div class="modal-header">
	  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	  <b>Privilege / Hak Akses</b>
	</div>
	    <div class="modal-body">
		<input type="hidden" name="command" value="<%= Command.SAVE %>">
		<input type="hidden" name="user_oid" id="USER_OID">
		<div class="row">
		    <div class="col-md-12">
			<div class="box box-primary">
			    <div class="box-body">
				<div class="col-md-12">
				    <div class="col-md-4"><%= listTextGlobal[SESS_LANGUAGE][4] %></div>
				    <div class="col-md-8" id="privname"></div>
				</div>
				<div class="col-md-12">
				    <div class="col-md-4"><%= listTextGlobal[SESS_LANGUAGE][5] %></div>
				    <div class="col-md-8" id="privdesc"></div>
				</div>
				<div class="col-md-12" id="listprivobj">
				    
				</div>
			    </div>
			</div>
		    </div>
		</div>
	    </div>
	    <div class="modal-footer">
		<button class="btn btn-danger pull-left" data-dismiss="modal">
		    <i class="fa fontello-cancel"></i> <b>Batal</b>
		</button>
		<%
		    if(privAdd && privUpdate){
		    %>
			<button class="btn btn-success addeditmodul" data-priv-oid="" data-priv-obj-oid="0" data-command="<%= Command.ADD %>" id="addprivobj">
			    <i class="fa fontello-plus"></i> <b><%=ctrLine.getCommand(SESS_LANGUAGE,listTextGlobal[SESS_LANGUAGE][6],ctrLine.CMD_ADD,true)%></b>
			</button>
			<button class="btn btn-primary" style="display: none;">
			    <i class="fa fontello-ok"></i> <b><%=listTextGlobal[SESS_LANGUAGE][10]+" "+listTextGlobal[SESS_LANGUAGE][6]%></b>
			</button>
		    <%
		    }
		%>
		
	    </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<div id="modulobj" class="modal" style="overflow-y: auto;">
    <div class="modal-dialog">
      <div class="modal-content">
	<div class="modal-header">
	  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	  <b>Privilege / Hak Akses</b>
	</div>
	 <form id="<%= FrmAppPrivilegeObj.FRM_APP_PRIVILEGE_OBJ %>">
	     <input type="hidden" name="command" value="<%= Command.SAVE %>">
	     <input type="hidden" name="FRM_FIELD_VIEW_TYPE" value="savemodulobj">
	     <input type="hidden" name="<%= FrmAppPrivilegeObj.fieldNames[FrmAppPrivilegeObj.FRM_PRIV_ID] %>" id="<%= FrmAppPrivilegeObj.fieldNames[FrmAppPrivilegeObj.FRM_PRIV_ID] %>">
	     <input type="hidden" name="priv_obj_oid" value="" id="priv_obj_oid">
	    <div class="modal-body">
		<div class="row">
		    <div class="col-md-12">
			<div class="box box-primary" id="contentmodulobj">
			    
			</div>
		    </div>
		</div>
	    </div>
	    <div class="modal-footer">
		<button class="btn btn-danger pull-left" data-dismiss="modal">
		    <i class="fa fontello-cancel"></i> <b>Batal</b>
		</button>
		<%
		    if(privAdd && privUpdate){
		    %>
			<button class="btn btn-success" id="buttonsaveobj">
			    <i class="fa fontello-ok"></i> <b><%= ctrLine.getCommand(SESS_LANGUAGE,listTextGlobal[SESS_LANGUAGE][1],ctrLine.CMD_SAVE,true) %></b>
			</button>
			<button class="btn btn-primary" style="display: none;">
			    <i class="fa fontello-ok"></i> <b><%=listTextGlobal[SESS_LANGUAGE][10]+" "+listTextGlobal[SESS_LANGUAGE][6]%></b>
			</button>
		    <%
		    }
		%>
	    </div>
	 </form>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->