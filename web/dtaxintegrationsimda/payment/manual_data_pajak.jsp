<%-- 
    Document   : manual_data_pajak
    Created on : Nov 6, 2015, 9:32:50 AM
    Author     : dimata005
--%>

<%@page import="com.dimata.util.Formater"%>
<%@page import="java.util.Date"%>
<%@page import="com.dimata.saras.entity.admin.AppGroup"%>
<%@page import="com.dimata.dtaxintegration.entity.biadmin.AppObjInfo"%>
<%@page import="com.dimata.dtaxintegration.form.bi.FrmSearchDataPajak"%>
<%@page import="com.dimata.gui.jsp.ControlCombo"%>
<%@page import="com.dimata.dtaxintegration.entity.bi.PajakTypeDetail"%>
<%@page import="com.dimata.dtaxintegration.entity.bi.PstPajakTypeDetail"%>
<%@page import="java.util.Vector"%>
<%@page import="com.dimata.dtaxintegration.form.bi.FrmDataPajak"%>
<%@page import="com.dimata.util.Command"%>
<%@include file="../../main/javainit_bi.jsp" %>
<%@include file="../../main/checkuser_bi.jsp" %>

<!-- daterange picker -->

<%
    Date currentDate = new Date();
%>
<input type="hidden" name="approot" id="approot" value="<%=approot%>">
<input type='hidden' name='command' id='command' value='<%=Command.NONE%>'>
<input type="hidden" name="FRM_FIELD_TANGGALBAYAR_DASHBOARD" id="FRM_FIELD_TANGGALBAYAR_DASHBOARD" value="01 January 1000">
<input type="hidden" name="FRM_FIELD_CURRENT_DATE" id="FRM_FIELD_CURRENT_DATE" value="<%= Formater.formatDate(currentDate, "dd MMMM yyyy") %>">
<ol class="breadcrumb newcrumb">
    <li>
        <a href="#!/dashboard">
            <span>
                <i class="fa fontello-home-outline"></i>
            </span>Dashboard
        </a>
    </li>
    <li class="active">Manual Proses </li>
</ol>

<form id="<%=FrmSearchDataPajak.FRM_NAME_SEARCHDATAPAJAK %>" name="<%=FrmSearchDataPajak.FRM_NAME_SEARCHDATAPAJAK%>">
    <div class="row" >
        <div class="col-md-12">
            <input type="hidden" name="command" value="">
            <input type="hidden" name="FRM_FIELD_VIEW_TYPE" value="savetarget">
            <input type="hidden" name="<%=FrmSearchDataPajak.fieldNames[FrmSearchDataPajak.FRM_FIELD_TYPE_INPUT_DATA]%>" value="0">
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
                        <div class="col-md-12">
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label>Jenis Pajak:</label>
                                    <%
                                        Vector tipePajak = new Vector(1,1);
                                        tipePajak = PstPajakTypeDetail.list(0,0, "","");
                                        Vector valueTipePajak =  new Vector();
                                        Vector keyTipePajak = new  Vector();
                                        if(tipePajak.size() != 0){
                                             for(int i = 0; i<tipePajak.size(); i++){
                                                 PajakTypeDetail entPajakTypeDetail = (PajakTypeDetail) tipePajak.get(i);
                                                 valueTipePajak.add(""+entPajakTypeDetail.getOID());
                                                 keyTipePajak.add(""+entPajakTypeDetail.getPajakDetailName());
                                             }
                                         }
                                    %>
                                    <%=ControlCombo.drawBootsratap(FrmSearchDataPajak.fieldNames[FrmSearchDataPajak.FRM_FIELD_PAJAKDETAILID], null, "", valueTipePajak, keyTipePajak, "required='required'", "form-control") %>
                                </div>
                            </div>
                            <div class="col-md-6">
                                 <div class="form-group">
                                    <label>Date range:</label>
                                    <div class="input-group">
                                        <div class="input-group-addon">
                                            <i class="fontello-calendar"></i>
                                        </div>
                                        <input name="<%=FrmSearchDataPajak.fieldNames[FrmSearchDataPajak.FRM_FIELD_RANGEDATE]%>" type="text" class="form-control pull-right" id="<%=FrmSearchDataPajak.fieldNames[FrmSearchDataPajak.FRM_FIELD_RANGEDATE]%>" readonly />
                                    </div>
                                    <!-- /.input group -->
                                </div>
                            </div>
                            <div class="col-md-3">
                                 <div class="form-group">
                                    <label>&nbsp;</label>
                                    <button class="form-control btn btn-google-plus button test pull-left" type="button" id="btnSearch">
                                    <span class="fontello-loop"></span>&nbsp;&nbsp;Refresh</button>
                                    <!-- /.input group -->
                                </div>
                            </div>    
                                  
                        </div>
                        <!-- DATA DISPLAY -->
                        <div class="row">
                            <div class="col-md-12" id="VIEW_CONTENT">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12" id="RESULT_CONTENT">
                            </div>
                        </div>

                    </div>
                    <div class="box-footer">
			<% 
			    if(userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_DATA_PAJAK, AppObjInfo.G2_MANUAL_DATA_PAJAK, AppObjInfo.OBJ_MANUAL_DATA_PAJAK, AppObjInfo.COMMAND_DOWNLOAD)){
			    %>
				<button class="btn btn-primary" type="button" id="btnDownload">
				    <i class="fa fa-check"></i> Download
				</button>
			    <%	
			    } 
			%>
                        
                    </div>
                </div>
            </div>
        </div>
</form>
<!-- daterangepicker -->
<script>
    $('#<%=FrmSearchDataPajak.fieldNames[FrmSearchDataPajak.FRM_FIELD_RANGEDATE]%>').daterangepicker();
</script>