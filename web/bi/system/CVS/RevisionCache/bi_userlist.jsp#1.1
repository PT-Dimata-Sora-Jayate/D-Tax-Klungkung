<%-- 
    Document   : newuserlist
    Created on : May 7, 2015, 2:51:09 PM
    Author     : dimata005


<%@page import="com.dimata.dtaxintegration.form.biadmin.CtrlAppUser"%>
<%@page import="com.dimata.util.Command"%>
<%@page import="com.dimata.gui.jsp.ControlLine"%>
<%@page import="com.dimata.dtaxintegration.entity.biadmin.PstAppUser"%>
<%@page import="com.dimata.gui.jsp.ControlList"%>
<%@page import="java.util.Vector"%>
<%@ page language="java" %>

<%@ include file = "../../main/javainit_bi.jsp" %>
<% int  appObjCode = AppObjInfo.composeObjCode(AppObjInfo.G1_ADMIN , AppObjInfo.G2_ADMIN_USER, AppObjInfo.OBJ_ADMIN_USER_USER); %>
<%@ include file = "../../main/checkuser_bi.jsp" %>

<!-- JSP Block -->
<%!
public static final String textListTitleHeader[][] =
{
        {"Sistem","Daftar Pemakai","ID Pemakai","Nama Lengkap","Status","Tambah Pemakai Baru"},
        {"System","User LIst","User ID","Full Name","Status","Add New User"}
};

public String drawListAppUser(int language,Vector objectClass)
{
        String temp = ""; 
        String regdatestr = "";
        
        ControlList ctrlist = new ControlList();
        ctrlist.setAreaWidth("90%");
        ctrlist.setListStyle("listgen");
        ctrlist.setTitleStyle("listgentitle");
        ctrlist.setCellStyle("listgensell");
        ctrlist.setHeaderStyle("listgentitle");
        ctrlist.addHeader(textListTitleHeader[language][2],"20%");
        ctrlist.addHeader(textListTitleHeader[language][3],"70%");
        ctrlist.addHeader(textListTitleHeader[language][4],"10%");

        ctrlist.setLinkRow(0);
        ctrlist.setLinkSufix("");

        Vector lstData = ctrlist.getData();

        Vector lstLinkData 	= ctrlist.getLinkData();

        ctrlist.setLinkPrefix("javascript:cmdEdit('");
        ctrlist.setLinkSufix("')");
        ctrlist.reset();

        for (int i = 0; i < objectClass.size(); i++) {
                 AppUser appUser = (AppUser)objectClass.get(i);

                 Vector rowx = new Vector();
                 
                 rowx.add(String.valueOf(appUser.getLoginId()));
                 rowx.add(String.valueOf(appUser.getFullName()));
                 rowx.add(String.valueOf(AppUser.getStatusTxt(appUser.getUserStatus())));		 
                                 
                 lstData.add(rowx);
                 lstLinkData.add(String.valueOf(appUser.getOID()));
        }						

        return ctrlist.drawBootstrapStrip();
}

%>
<%

/* VARIABLE DECLARATION */
int recordToGet = 300;

String order = " " + PstAppUser.fieldNames[PstAppUser.FLD_LOGIN_ID];

Vector listAppUser = new Vector(1,1);
ControlLine ctrLine = new ControlLine();

/* GET REQUEST FROM HIDDEN TEXT */
int iCommand = FRMQueryString.requestCommand(request);
int start = FRMQueryString.requestInt(request, "start"); 
long appUserOID = FRMQueryString.requestLong(request,"user_oid");
int listCommand = FRMQueryString.requestInt(request, "list_command");
if(listCommand==Command.NONE)
 listCommand = Command.LIST;

CtrlAppUser ctrlAppUser = new CtrlAppUser(request);
 
int vectSize = PstAppUser.getCount(""); 
start = ctrlAppUser.actionList(listCommand, start,vectSize,recordToGet);

listAppUser = PstAppUser.listPartObj(start,recordToGet, "" , order);

%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>AdminLTE | Data Tables</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <!-- bootstrap 3.0.2 -->
        <link href="../styles/bootstrap3.1/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- font Awesome -->
        <link href="../styles/bootstrap3.1/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons -->
        <link href="../styles/bootstrap3.1/css/ionicons.min.css" rel="stylesheet" type="text/css" />
        <!-- DATA TABLES -->
        <link href="../styles/bootstrap3.1/css/datatables/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />
        <!-- Theme style -->
        <link href="../styles/bootstrap3.1/css/AdminLTE.css" rel="stylesheet" type="text/css" />

         <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="../styles/bootstrap3.1/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="../styles/bootstrap3.1/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
        <script language="JavaScript">
            <% if (privAdd){%>
            function addNew(){
                    document.frmAppUser.user_oid.value="0";
                    document.frmAppUser.list_command.value="<%=listCommand%>";
                    document.frmAppUser.command.value="<%=Command.ADD%>";
                    document.frmAppUser.action="useredit_new.jsp";
                    document.frmAppUser.submit();
            }
            <%}%>

            function cmdEdit(oid){
                    document.frmAppUser.user_oid.value=oid;
                    document.frmAppUser.list_command.value="<%=listCommand%>";
                    document.frmAppUser.command.value="<%=Command.EDIT%>";
                    document.frmAppUser.action="useredit_new.jsp";
                    document.frmAppUser.submit();
            }

            function cmdListFirst(){
                    document.frmAppUser.command.value="<%=Command.FIRST%>";
                    document.frmAppUser.list_command.value="<%=Command.FIRST%>";
                    document.frmAppUser.action="userlist.jsp";
                    document.frmAppUser.submit();
            }

            function cmdListPrev(){
                    document.frmAppUser.command.value="<%=Command.PREV%>";
                    document.frmAppUser.list_command.value="<%=Command.PREV%>";
                    document.frmAppUser.action="userlist.jsp";
                    document.frmAppUser.submit();
            }

            function cmdListNext(){
                    document.frmAppUser.command.value="<%=Command.NEXT%>";
                    document.frmAppUser.list_command.value="<%=Command.NEXT%>";
                    document.frmAppUser.action="userlist.jsp";
                    document.frmAppUser.submit();
            }

            function cmdListLast(){
                    document.frmAppUser.command.value="<%=Command.LAST%>";
                    document.frmAppUser.list_command.value="<%=Command.LAST%>";
                    document.frmAppUser.action="userlist.jsp";
                    document.frmAppUser.submit();
            }
        </script>
    </head>
    <body class="skin-blue">
        <!-- header logo: style can be found in header.less -->
        <%@ include file = "../header_mobile.jsp" %> 
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <!-- Left side column. contains the logo and sidebar -->
             <%@ include file = "../menu_left_mobile.jsp" %> 
             
            <!-- Right side column. Contains the navbar and content of the page -->
            <aside class="right-side">                
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Tables
                        <small>User List</small>
                    </h1>
                </section>
                <!-- Main content -->
                <section class="content">
                     <div class="box">
                         <div class="box-body table-responsive">
                             <form name="frmAppUser" method="post" action="">
                                  <input type="hidden" name="command" value="">
                                  <input type="hidden" name="user_oid" value="<%=appUserOID%>">
                                  <input type="hidden" name="vectSize" value="<%=vectSize%>">
                                  <input type="hidden" name="start" value="<%=start%>">
                                  <input type="hidden" name="list_command" value="<%=listCommand%>">
                                  <table width="100%" cellspacing="0" cellpadding="0">
                                    <tr> 
                                      <td colspan="2" class="listtitle">
                                        <hr size="1">
                                      </td>
                                    </tr>
                                  </table>
                                  <% if ((listAppUser!=null)&&(listAppUser.size()>0)){ %>
                                  <%=drawListAppUser(SESS_LANGUAGE, listAppUser)%> 
                                  <%}%>
                                  <table width="100%">
                                    <tr> 
                                      <td colspan="2"> 
                                                      <span class="command"> 
                                                      <% 
                                                       int cmd = 0;
                                                       if ((iCommand == Command.FIRST || iCommand == Command.PREV )|| 
                                                            (iCommand == Command.NEXT || iCommand == Command.LAST))
                                                                    cmd =iCommand; 
                                                       else{
                                                              if(iCommand == Command.NONE)
                                                                    cmd = Command.FIRST;
                                                       } 
                                                       ctrLine.setLocationImg(approot+"/images");
                                                       ctrLine.initDefault();						   
                                                      %>
                                                      <%=ctrLine.drawImageListLimit(cmd,vectSize,start,recordToGet)%> 
                                                      </span>				  
                                                      </td>
                                    </tr>
                                    <% if (privAdd){%>
                                    <tr valign="middle"> 
                                      <td colspan="2" class="command"> 
                                        <table width="15%" border="0" cellspacing="2" cellpadding="2">
                                          <tr> 
                                            <td width="20%"><a href="javascript:addNew()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image10011','','<%=approot%>/images/BtnNewOn.jpg',1)"><img name="Image10011" border="0" src="<%=approot%>/images/BtnNew.jpg" width="24" height="24" alt="Tambah Baru User"></a></td>
                                            <td nowrap width="80%"><a href="javascript:addNew()" class="command">
                                              <%=textListTitleHeader[SESS_LANGUAGE][5]%></a></td>
                                          </tr>
                                        </table>
                                      </td>
                                    </tr>
                                    <%}%>
                                    <tr> 
                                      <td width="13%">&nbsp;</td>
                                      <td width="87%">&nbsp;</td>
                                    </tr>
                                  </table>
                                </form>
                         </div>
                     </div>
                </section><!-- /.content -->
            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->
        <!-- jQuery 2.0.2 -->
        <script src="../styles/jquery.min.js"></script>
        <!-- Bootstrap -->
        <script src="../styles/bootstrap3.1/js/bootstrap.min.js" type="text/javascript"></script>
        <!-- DATA TABES SCRIPT -->
        <script src="../styles/bootstrap3.1/js/plugins/datatables/jquery.dataTables.js" type="text/javascript"></script>
        <script src="../styles/bootstrap3.1/js/plugins/datatables/dataTables.bootstrap.js" type="text/javascript"></script>
        <!-- AdminLTE App -->
        <script src="../styles/bootstrap3.1/js/AdminLTE/app.js" type="text/javascript"></script>

        <!-- page script -->
        <script type="text/javascript">
            $(function() {
                $("#example1").dataTable();
                $('#example2').dataTable({
                    "bPaginate": true,
                    "bLengthChange": false,
                    "bFilter": false,
                    "bSort": true,
                    "bInfo": true,
                    "bAutoWidth": false
                });
            });
            $('#compose-modal').modal(
                {
                    backdrop:'static',
                    show:false
                }
            );
        </script>
    </body>
</html>
--%>

<%@page import="com.dimata.util.Formater"%>
<%@page import="java.util.Date"%>
<%@page import="com.dimata.dtaxintegration.form.biadmin.FrmAppUser"%>
<%@page import="com.dimata.dtaxintegration.form.biadmin.CtrlAppUser"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<%@page import="com.dimata.gui.jsp.ControlLine"%>
<%@page import="com.dimata.gui.jsp.ControlList"%>
<%@page import="com.dimata.dtaxintegration.entity.biadmin.PstAppUser"%>
<%-- 
    Document   : manual_data_pajak
    Created on : Nov 6, 2015, 9:32:50 AM
    Author     : dimata005
--%>

<%@page import="com.dimata.dtaxintegration.form.bi.FrmSearchDataPajak"%>
<%@page import="com.dimata.gui.jsp.ControlCombo"%>
<%@page import="com.dimata.dtaxintegration.entity.bi.PajakTypeDetail"%>
<%@page import="com.dimata.dtaxintegration.entity.bi.PstPajakTypeDetail"%>
<%@page import="java.util.Vector"%>
<%@page import="com.dimata.dtaxintegration.form.bi.FrmDataPajak"%>
<%@page import="com.dimata.util.Command"%>
<%@ include file = "../../main/javainit_bi.jsp" %>
<% int appObjCode = AppObjInfo.composeObjCode(AppObjInfo.G1_ADMIN, AppObjInfo.G2_ADMIN_USER, AppObjInfo.OBJ_ADMIN_USER_USER);%>
<%@ include file = "../../main/checkuser_bi.jsp" %>

<!-- daterange picker -->
<%!
    public static final String textListTitleHeader[][]
            = {
                {"Sistem", "Daftar Pemakai", "ID Pemakai", "Nama Lengkap", "Status", "Tambah Pemakai Baru"},
                {"System", "User LIst", "User ID", "Full Name", "Status", "Add New User"}
            };
    public static final String textListTitleHeaderForm[][]=
    {
	//0,                    1,          2,          3,                  4,              5,      6,          7,              8,              9,              10,                 11,                     12                               13                          14                                15              16                 17                                        18
        {"Manajemen Pemakai","ID Pemakai","Pasword","Konfirmasi Pasword","Nama Lengkap","Email","Keterangan","Status Pemakai","Grup Pemakai","Simpan Pemakai","Hapus Pemakai","Kembali ke Daftar Pemakai","Proses OK..kembali ke list","Assign Final Document Transaction Location","Assign Data Exchange Location"," Assign Day Login","Assign Time","Assign View Sales/Stock Report Location","Nama Perusahaan","Assign Create Location Document"},
	{"User Management","User ID","Password","Confirm Password","Full Name","Email","Description","User Status","User Group","Save User","Delete User","Back to User List","Processing OK..back to list","Assign Final Document Transaction Location","Assign Data Exchange Location","Assign Day Login","Assign Time","Assign View Sales/Stock Report Location","Company","Assign Create Location Document"}
    };
    public String drawListAppUser(int language, Vector objectClass, boolean privUpdate) {
        String temp = "";
        String regdatestr = "";

        ControlList ctrlist = new ControlList();
        ctrlist.setAreaWidth("90%");
        ctrlist.setListStyle("listgen");
        ctrlist.setTitleStyle("listgentitle");
        ctrlist.setCellStyle("listgensell");
        ctrlist.setHeaderStyle("listgentitle");
        ctrlist.addHeader(textListTitleHeader[language][2], "20%");
        ctrlist.addHeader(textListTitleHeader[language][3], "70%");
        ctrlist.addHeader(textListTitleHeader[language][4], "10%");

        ctrlist.setLinkRow(0);
        ctrlist.setLinkSufix("");

        Vector lstData = ctrlist.getData();
        ctrlist.reset();

        for (int i = 0; i < objectClass.size(); i++) {
            AppUser appUser = (AppUser) objectClass.get(i);

            Vector rowx = new Vector();
	    if(privUpdate){
		rowx.add("<a href='javascript:' class='showuseredit' data-oid='"+appUser.getOID()+"'>"+String.valueOf(appUser.getLoginId())+"</a>");
	    }else{
		rowx.add(""+String.valueOf(appUser.getLoginId())+"");
	    }
            
            rowx.add(String.valueOf(appUser.getFullName()));
            rowx.add(String.valueOf(AppUser.getStatusTxt(appUser.getUserStatus())));

            lstData.add(rowx);
        }

        return ctrlist.drawBootstrapStrip();
    }

%>
<%    /* VARIABLE DECLARATION */
    int recordToGet = 0;

    String order = " " + PstAppUser.fieldNames[PstAppUser.FLD_LOGIN_ID];

    Vector listAppUser = new Vector(1, 1);
    ControlLine ctrLine = new ControlLine();

    /* GET REQUEST FROM HIDDEN TEXT */
    int iCommand = FRMQueryString.requestCommand(request);
    int start = FRMQueryString.requestInt(request, "start");
    long appUserOID = FRMQueryString.requestLong(request, "user_oid");
    int listCommand = FRMQueryString.requestInt(request, "list_command");
    if (listCommand == Command.NONE) {
        listCommand = Command.LIST;
    }

    CtrlAppUser ctrlAppUser = new CtrlAppUser(request);

    int vectSize = PstAppUser.getCount("");
    start = ctrlAppUser.actionList(listCommand, start, vectSize, recordToGet);

    listAppUser = PstAppUser.listPartObj(start, recordToGet, "", order);
    
    boolean privAdd = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_ADMIN, AppObjInfo.G2_ADMIN_USER, AppObjInfo.OBJ_ADMIN_USER_USER, AppObjInfo.COMMAND_ADD);
    boolean privUpdate = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_ADMIN, AppObjInfo.G2_ADMIN_USER, AppObjInfo.OBJ_ADMIN_USER_USER, AppObjInfo.COMMAND_UPDATE);
    
    Date currentDate =  new Date();
%>
<input type="hidden" name="approot" id="approot" value="<%=approot%>">
<input type='hidden' name='command' id='command' value='<%=Command.NONE%>'>
<input type="hidden" name="usersession" id="usersession" value="<%= userSession %>">
<input type="hidden" name="isloggedin" id="isloggedin" value="<%= isLoggedIn %>">
<input type="hidden" name="FRM_FIELD_TANGGALBAYAR_DASHBOARD" id="FRM_FIELD_TANGGALBAYAR_DASHBOARD" value="01 January 1000">
<input type="hidden" name="FRM_FIELD_CURRENT_DATE" id="FRM_FIELD_CURRENT_DATE" value="<%= Formater.formatDate(currentDate, "dd MMMM yyyy") %>">
<ol class="breadcrumb newcrumb">
    <li>
        <a href="#">
            <span>
                <i class="fa fontello-home-outline"></i>
            </span>Admin Sistem
        </a>
    </li>
    <li class="active">List User</li>
</ol>
<form id="frmAppUser" name="frmAppUser">
    <div class="row" >
        <div class="col-md-12">
            <div class="box box-primary">
                <div class="box-body">
                    <input type="hidden" name="command" value="">
                    <input type="hidden" name="user_oid" value="<%=appUserOID%>">
                    <input type="hidden" name="vectSize" value="<%=vectSize%>">
                    <input type="hidden" name="start" value="<%=start%>">
                    <input type="hidden" name="list_command" value="<%=listCommand%>">
                    <table width="100%" cellspacing="0" cellpadding="0">
                        <tr> 
                            <td colspan="2" class="listtitle">
                                <hr size="1">
                            </td>
                        </tr>
                    </table>
                    <% if ((listAppUser != null) && (listAppUser.size() > 0)) {%>
		    <div id="listuser">
                    <%=drawListAppUser(SESS_LANGUAGE, listAppUser, privUpdate)%>
		    </div>
                    <%}%>
                    <table width="100%">
                        <tr> 
                            <td colspan="2" style="display:none;"> 
                                <span class="command"> 
                                    <%
                                        int cmd = 0;
                                        if ((iCommand == Command.FIRST || iCommand == Command.PREV)
                                                || (iCommand == Command.NEXT || iCommand == Command.LAST)) {
                                            cmd = iCommand;
                                        } else {
                                            if (iCommand == Command.NONE) {
                                                cmd = Command.FIRST;
                                            }
                                        }
                                        ctrLine.setLocationImg(approot + "/images");
                                        ctrLine.initDefault();
                                    %>
                                    <%=ctrLine.drawImageListLimit(cmd, vectSize, start, recordToGet)%> 
                                </span>				  
                            </td>
                        </tr>
                        <% if (privAdd) {%>
                        <tr valign="middle"> 
                            <td colspan="2" class="command"> 
                                <table width="15%" border="0" cellspacing="2" cellpadding="2">
                                    <tr> 
                                        <td width="20%">
					    <br><br>
					    <button class="btn btn-primary showuseredit" data-oid="0">
						<i class="fa fontello-user-add"></i> <%= textListTitleHeader[SESS_LANGUAGE][5] %>
					    </button>
					</td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <%}%>
                        <tr> 
                            <td width="13%">&nbsp;</td>
                            <td width="87%">&nbsp;</td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>    

</form>
<div id="useredit" class="modal" >
    <div class="modal-dialog">
      <div class="modal-content">
	<div class="modal-header">
	  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	  <b>TAMBAH / EDIT PEMAKAI</b>
	</div>
	<form id="<%= FrmAppUser.FRM_APP_USER %>">
	    <div class="modal-body">
		<input type="hidden" name="command" value="<%= Command.SAVE %>">
		<input type="hidden" name="user_oid" id="USER_OID">
		<input type="hidden" name="FRM_FIELD_VIEW_TYPE" value="saveuserlist">
		<div class="row">
		    <div class="col-md-12">
			<div class="box box-primary">
			    <div class="box-body">
				<div class="form-group">
				    <label><%=textListTitleHeaderForm[SESS_LANGUAGE][1]%></label>
				    <input type="text" class="form-control" name="<%= FrmAppUser.fieldNames[FrmAppUser.FRM_LOGIN_ID] %>" id="<%= FrmAppUser.fieldNames[FrmAppUser.FRM_LOGIN_ID] %>" required="required">
				</div>
				<div class="form-group">
				    <label><%=textListTitleHeaderForm[SESS_LANGUAGE][2]%></label>
				    <input type="password" class="form-control" name="<%= FrmAppUser.fieldNames[FrmAppUser.FRM_PASSWORD] %>" id="<%= FrmAppUser.fieldNames[FrmAppUser.FRM_PASSWORD] %>" required="required">
				</div>
				<div class="form-group">
				    <label><%=textListTitleHeaderForm[SESS_LANGUAGE][3]%></label>
				    <input type="password" class="form-control" name="<%= FrmAppUser.fieldNames[FrmAppUser.FRM_CFRM_PASSWORD] %>" id="<%= FrmAppUser.fieldNames[FrmAppUser.FRM_CFRM_PASSWORD] %>" required="required">
				</div>
				<div class="form-group">
				    <label><%=textListTitleHeaderForm[SESS_LANGUAGE][4]%></label>
				    <input type="text" class="form-control" name="<%= FrmAppUser.fieldNames[FrmAppUser.FRM_FULL_NAME] %>" id="<%= FrmAppUser.fieldNames[FrmAppUser.FRM_FULL_NAME] %>" required="required">
				</div>
				<div class="form-group">
				    <label><%=textListTitleHeaderForm[SESS_LANGUAGE][5]%></label>
				    <input type="text" class="form-control" name="<%= FrmAppUser.fieldNames[FrmAppUser.FRM_EMAIL] %>" id="<%= FrmAppUser.fieldNames[FrmAppUser.FRM_EMAIL] %>">
				</div>
				<div class="form-group">
				    <label><%=textListTitleHeaderForm[SESS_LANGUAGE][6]%></label>
				    <input type="text" class="form-control" name="<%= FrmAppUser.fieldNames[FrmAppUser.FRM_DESCRIPTION] %>" id="<%= FrmAppUser.fieldNames[FrmAppUser.FRM_DESCRIPTION] %>">
				</div>
				<div class="form-group">
				    <label><%=textListTitleHeaderForm[SESS_LANGUAGE][7]%></label>
				    <%
					ControlCombo cmbox = new ControlCombo();
					Vector sts = AppUser.getStatusTxts();
					Vector stsVals = AppUser.getStatusVals();
				    %>
				    <%=cmbox.draw(FrmAppUser.fieldNames[FrmAppUser.FRM_USER_STATUS] ,"form-control",null, "", stsVals, sts)%>
				</div>
				<div class="form-group">
				    <label><%=textListTitleHeaderForm[SESS_LANGUAGE][8]%></label>
				    <%
					ControlCombo cmbox1 = new ControlCombo();
					Vector sts1 = PstAppUser.listUserGroupNewTypeKey();
					Vector stsVals1 = PstAppUser.listUserGroupTypeValue();
				    %>
				    <%=cmbox1.draw(FrmAppUser.fieldNames[FrmAppUser.FRM_USER_GROUP_NEW] ,"form-control",null, "", stsVals1, sts1)%>
				</div>
				<div class="form-group">
				    <label>Group Assigned</label>
				    <div id="groupassigned"></div>
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
		<button class="btn btn-success" id="btnsimpanuser">
		    <i class="fa fontello-ok"></i> <b>Simpan</b>
		</button>
	    </div>
				
	</form>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<%--
<form id="<%=FrmSearchDataPajak.FRM_NAME_SEARCHDATAPAJAK %>" name="<%=FrmSearchDataPajak.FRM_NAME_SEARCHDATAPAJAK%>">
    <div class="row" >
        <div class="col-md-12">
            <input type="hidden" name="command" value="">
            <input type="hidden" name="user_oid" value="<%=appUserOID%>">
            <input type="hidden" name="vectSize" value="<%=vectSize%>">
            <input type="hidden" name="start" value="<%=start%>">
            <input type="hidden" name="list_command" value="<%=listCommand%>">
            <div class="box box-primary">
                <!-- HEADER CONTENT -->
                <div class="box-header" style="border-bottom:1px solid #f1f1f1;">
                    <!-- HEADER TITLE CONTENT -->
                    <div class="col-md-2">
                        <h2 class="box-title"></h2>
                    </div>
                    <!--/ HEADER TITLE CONTENT -->
                    <!-- DATA JENIS PAJAK DISPLAY SETTING -->
                    <div class="col-md-6">
                        <div class="box-title"> 
                        </div>
                        <div class="col-md-3"  style="padding-top:12px;">
                        </div>
                        <div class="box-title">
                        </div>
                        <div class="col-md-4" style="padding-top:12px;">
                        </div>
                    </div>
                    <!--/ DATA JENIS PAJAK DISPLAY SETTING -->
                </div>
                <!--/ HEADER CONTENT -->

                <!-- BODY CONTENT -->
                <div class="box-body">
                    <!--/ Search parameter -->
                    <div class="row">
                        <!-- DATA DISPLAY -->
                        <div class="row">
                            <div class="col-md-12" id="VIEW_CONTENT">
                                <% if ((listAppUser!=null)&&(listAppUser.size()>0)){ %>
                                  <%=drawListAppUser(SESS_LANGUAGE, listAppUser)%> 
                                  <%}%>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12" id="RESULT_CONTENT">
                            </div>
                        </div>

                    </div>
                    <div class="box-footer">
                        <button class="btn btn-primary" type="button" id="btnDownload">
                            <i class="fa fa-check"></i> Download
                        </button>
                    </div>
                </div>
            </div>
        </div>
</form>
<!-- daterangepicker -->
<script>
    $('#<%=FrmSearchDataPajak.fieldNames[FrmSearchDataPajak.FRM_FIELD_RANGEDATE]%>').daterangepicker();
</script>--%>