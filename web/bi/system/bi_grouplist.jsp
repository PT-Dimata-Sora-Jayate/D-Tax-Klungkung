<%@page import="com.dimata.gui.jsp.ControlList"%>
<%@page import="com.dimata.util.Formater"%>
<%@page import="java.util.Date"%>
<%@page import="com.dimata.dtaxintegration.entity.biadmin.AppGroup"%>
<%@page import="com.dimata.dtaxintegration.form.biadmin.FrmAppGroup"%>
<%@page import="com.dimata.util.Command"%>
<%@page import="com.dimata.gui.jsp.ControlLine"%>
<%@page import="java.util.Vector"%>
<%@page import="com.dimata.dtaxintegration.entity.biadmin.PstAppGroup"%>
<%@include file="../../main/javainit_bi.jsp" %>
<%@ include file = "../../main/checkuser_bi.jsp" %>
<%!
    
    public static final String listTextGlobal[][] = {
	    {"Managemen Pemakai","Kelompok","Daftar","Edit","Tidak Ada Kelompok"},
	    {"User Management","Group","List","Edit","No Group Available"}
    };

    public static final String listTextTableHeader[][] = {
	    {"No.","Nama","Deskripsi","Tanggal Pembuatan"},
	    {"No.","Name","Description","Creation Date"}
    };
    
    public static final String listTextGlobalEdit[][] = {
	{"Managemen Pemakai","Kelompok","Daftar","Edit"},
	{"User Management","Group","List","Edit"}
    };

    public static final String listTextTableHeaderEdit[][] = {
	{"No.","Nama","Deskripsi","Hak Akses","Tanggal Pembuatan"},
	{"No.","Name","Description","Privilege","Creation Date"}
    };
    
    
    public String drawListAppGroup(int language, int start, Vector objectClass, boolean privUpdate) {
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

	ctrlist.setLinkRow(1);
	ctrlist.setLinkSufix("");
	Vector lstData = ctrlist.getData();
	
	ctrlist.reset();
								
	for (int i = 0; i < objectClass.size(); i++) {
		 AppGroup appGroup = (AppGroup)objectClass.get(i);

		 Vector rowx = new Vector();
		 
		 rowx.add("<div align=\"center\">"+(start+i+1)+"</div>");
		 if(privUpdate == false){
		    rowx.add("<a href='javascript:' class='addeditgroup' data-group-oid='"+appGroup.getOID()+"'>"+appGroup.getGroupName()+"</a>");	
		 }else{ 
		    rowx.add(""+appGroup.getGroupName()+"");	
		 }	 
		 rowx.add(String.valueOf(appGroup.getDescription()));
		 try{
			 Date regdate = appGroup.getRegDate();
                         if(regdate==null){
                             regdate = new Date();
                         }
			 regdatestr = Formater.formatDate(regdate, "dd MMMM yyyy");
		 }catch(Exception e){
			 regdatestr = "";
		 }
		 
		 rowx.add(regdatestr);
		 		 
		 lstData.add(rowx);
	}						

	return ctrlist.drawBootstrapStrip();
    }

%>
<%
    String order = " " + PstAppGroup.fieldNames[PstAppGroup.FLD_GROUP_NAME];
    Vector listAppGroup = new Vector(1,1);
    ControlLine ctrLine = new ControlLine();
    listAppGroup = PstAppGroup.list(0,0, "" , order);
    String cmdTitle = listTextGlobal[SESS_LANGUAGE][1];
    
    Date currentDate = new Date();
    
    boolean privAdd = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_ADMIN, AppObjInfo.G2_ADMIN_USER, AppObjInfo.OBJ_ADMIN_USER_GROUP, AppObjInfo.COMMAND_ADD);
    boolean privUpdate = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_ADMIN, AppObjInfo.G2_ADMIN_USER, AppObjInfo.OBJ_ADMIN_USER_GROUP, AppObjInfo.COMMAND_UPDATE);
%>
<style type="text/css">
    .inputStyle{
	margin-right: 5px;
    }
</style>
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
	Group List
    </li>
</ol>
<div class="row">
    <div class="col-md-12">
	<div class="box box-primary">
	    <div class="box-header">
		<h3 class="box-title">Group List</h3>
	    </div>
	    <div class="box-body">
		<div id="listgroup">
		    <% 
			if ((listAppGroup!=null) && (listAppGroup.size()>0)){
		    %>
			    <%=drawListAppGroup(SESS_LANGUAGE, 0, listAppGroup, privUpdate)%>
		    <%
			}else{
		    %>
			    <%= listTextGlobal[SESS_LANGUAGE][4] %>
		    <%
			}
		    %>
		</div>
		<%
		    if(privAdd){
		    
		%>
		    <button type="button" class="btn btn-primary addeditgroup" data-group-oid="0">
			<i class="fa fontello-plus"></i> <%=ctrLine.getCommand(SESS_LANGUAGE,listTextGlobal[SESS_LANGUAGE][1],ctrLine.CMD_ADD,true)%>
		    </button>
		<%
		    }
		%>
	    </div>
	</div>
    </div>
</div>
<div id="groupedit" class="modal" style="overflow-y: auto;">
    <div class="modal-dialog">
      <div class="modal-content">
	<div class="modal-header">
	  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	  <b>Tambah / Ubah Kelompok</b>
	</div>
	  <form id="<%= FrmAppGroup.FRM_APP_GROUP %>">
	    <div class="modal-body">
		<input type="hidden" name="command" value="<%= Command.SAVE %>">
		<input type='hidden' name='FRM_FIELD_VIEW_TYPE' value="savegroup">
		<input type="hidden" name="group_oid" id="group_oid">
		<div class="row">
		    <div class="col-md-12">
			<div class="box box-primary">
			    <div class="box-body">
				<div class="form-group">
				    <label><%=listTextTableHeaderEdit[SESS_LANGUAGE][1]%></label>
				    <input type="text" class="form-control" name="<%= FrmAppGroup.fieldNames[FrmAppGroup.FRM_GROUP_NAME] %>" id="<%= FrmAppGroup.fieldNames[FrmAppGroup.FRM_GROUP_NAME] %>" required="required">
				</div>
				<div class="form-group">
				    <label><%=listTextTableHeaderEdit[SESS_LANGUAGE][2]%></label>
				    <textarea class="form-control" name="<%= FrmAppGroup.fieldNames[FrmAppGroup.FRM_DESCRIPTION] %>" id="<%= FrmAppGroup.fieldNames[FrmAppGroup.FRM_DESCRIPTION] %>" style="border:1px solid #ccc;"></textarea>
				</div>
				<div class="form-group">
				    <label><%=listTextTableHeaderEdit[SESS_LANGUAGE][3]%></label>
				    <div id="listprivchb">
					
				    </div>
				</div>
				<div class="form-group">
				    <label><%=listTextTableHeaderEdit[SESS_LANGUAGE][4]%></label>
				    <input type="text" class="form-control datePicker" name="<%= FrmAppGroup.fieldNames[FrmAppGroup.FRM_REG_DATE] %>" id="<%= FrmAppGroup.fieldNames[FrmAppGroup.FRM_REG_DATE] %>" required="required">
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
			<button class="btn btn-success" id="buttongroupedit">
			    <i class="fa fontello-ok"></i> <b><%= ctrLine.getCommand(SESS_LANGUAGE,cmdTitle,ctrLine.CMD_SAVE,true) %></b>
			</button>
		    <%
		    }
		%>
		
	    </div>
				
	</form>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->