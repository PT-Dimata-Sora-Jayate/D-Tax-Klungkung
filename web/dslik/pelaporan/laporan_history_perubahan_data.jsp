<%-- 
    Document   : pelaporan_per_segment
    Created on : Oct 2, 2016, 6:41:31 PM
    Author     : dimata005
--%>
<%@page import="com.dimata.common.entity.logger.LogSysHistory"%>
<%@page import="com.dimata.common.entity.logger.PstLogSysHistory"%>
<%@page import="com.dimata.dslik.entity.admin.PstAppUser"%>
<%@page import="com.dimata.dslik.entity.agunan.Agunan"%>
<%@page import="com.dimata.dslik.entity.kredit.Kredit"%>
<%@page import="java.util.Date"%>
<%@page import="com.dimata.util.Formater"%>
<%@page import="com.dimata.gui.jsp.ControlList"%>
<%@page import="com.dimata.dslik.entity.debiturindividu.PstDebiturIndividu"%>
<%@page import="com.dimata.dslik.entity.suratberharga.PstSuratBerharga"%>
<%@page import="com.dimata.dslik.entity.penjamin.PstPenjamin"%>
<%@page import="com.dimata.dslik.entity.pengurusataupemilik.PstPengurusAtauPemilik"%>
<%@page import="com.dimata.dslik.entity.laporankeuangandebitur.PstLaporanKeuanganDebitur"%>
<%@page import="com.dimata.dslik.entity.kreditjoinaccount.PstKreditJoinAccount"%>
<%@page import="com.dimata.dslik.entity.kredit.PstKredit"%>
<%@page import="com.dimata.dslik.entity.irrevocablelc.PstIrrevocableLc"%>
<%@page import="com.dimata.dslik.entity.fasilitaslain.PstFasilitasLain"%>
<%@page import="com.dimata.dslik.entity.debiturbdnusaha.PstDebiturBdnUsaha"%>
<%@page import="com.dimata.dslik.entity.bankgaransi.PstBankGaransi"%>
<%@page import="com.dimata.dslik.entity.agunan.PstAgunan"%>
<%@page import="com.dimata.dslik.ajax.AjaxPelaporan"%>
<%@page import="com.dimata.dslik.entity.masterdata.CabangBank"%>
<%@page import="com.dimata.dslik.entity.masterdata.PstCabangBank"%>
<%@page import="com.dimata.dslik.session.proses.ManagerTransferData"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<%@page import="com.dimata.dslik.form.masterdata.FrmConnection"%>
<%@page import="com.dimata.gui.jsp.ControlCombo"%>
<%@page import="java.util.Vector"%>
<%@page import="com.dimata.util.Command"%>
<!DOCTYPE html>
<%!
    public final static int SEGMEN_DEBITUR_INDIVIDU = 0;
    public final static int SEGMEN_DEBITUR_BADAN_USAHA = 1;
    public final static int SEGMEN_PENGURUS_PEMILIK = 2;
    public final static int SEGMEN_KREDIT = 3;
    public final static int SEGMEN_BANK_GARANSI = 4;
    public final static int SEGMEN_AGUNAN = 5;
    public final static int SEGMEN_PENJAMIN = 6;
    public final static int SEGMEN_LAPORAN_KEUANGAN_DEBITUR = 7;
    public final static int SEGMEN_SURAT_BERHARGA = 8;
    public final static int SEGMEN_KREDIT_JOIN = 9;
    public final static int SEGMEN_IRREVOCABLE_LC = 10;
    public final static int SEGMEN_FASILITAS_LAINNYA = 11;
    
    public final static String[] segmenNames = {
        "Debitur Individu",
        "Debitur Badan Usaha",
        "Pengurus/Pemilik",
        "Kredit",
        "Bank Garansi",
        "Agunan",
        "Penjamin",
        "Laporan Keuangan Debitur",
        "Surat Berharga",
        "Kredit Join",
        "Irrevocable L/C",
        "Fasilitas Lainnya"
    };


%>
<%@include file="../../main/javainit_slik.jsp" %>
<%@include file="../../main/checkuser_slik.jsp" %>
<%
    boolean privView = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_MODUL_PEMELIHARAAN, AppObjInfo.G2_LAPORAN_HISTORY_PERUBAHAN_DATA, AppObjInfo.OBJ_LAPORAN_HISTORY_PERUBAHAN_DATA, AppObjInfo.COMMAND_VIEW);
    boolean privPrint = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_MODUL_PEMELIHARAAN, AppObjInfo.G2_LAPORAN_HISTORY_PERUBAHAN_DATA, AppObjInfo.OBJ_LAPORAN_HISTORY_PERUBAHAN_DATA, AppObjInfo.COMMAND_PRINT);
    int iCommand = FRMQueryString.requestCommand(request);
    String startDate = FRMQueryString.requestString(request, "tanggalStart");
    String oDate = "";
    String startEnd = FRMQueryString.requestString(request, "tanggalEnd");
    int start = FRMQueryString.requestInt(request, "start");
    int prevCommand = FRMQueryString.requestInt(request, "prev_command");
    long userid = FRMQueryString.requestLong(request, "userid");
    String doctype = FRMQueryString.requestString(request, "doctype");
    String dari = FRMQueryString.requestString(request, "daritanggal");
    String sampai = FRMQueryString.requestString(request, "sampaitanggal");
    
    Vector listUser = PstAppUser.listFullObj(0, 0, "", ""+PstAppUser.fieldNames[PstAppUser.FLD_FULL_NAME]+" ASC");
    Vector userKey = new Vector(1,1);
    Vector userVal = new Vector(1,1);
    if(listUser.size() > 0){
        for(int i = 0; i < listUser.size(); i++){
            AppUser appUser = (AppUser) listUser.get(i);
            userKey.add(""+appUser.getOID());
            userVal.add(""+appUser.getFullName());
        }
    }
    
    Vector listDocType = PstLogSysHistory.listDocType(0, 0, "", "");
    Vector docTypeKey = new Vector(1,1);
    if(listDocType.size() > 0){
        for(int i = 0; i < listDocType.size(); i++){
            LogSysHistory logSysHistory = (LogSysHistory) listDocType.get(i);
            docTypeKey.add(""+logSysHistory.getLogDocumentType());
        }
    }
%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>SLIK | Laporan Nominatif Agunan</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <%@include file="../../template-component/css-component.jsp" %>
        <script language="JavaScript">

            function cmdStart() {
                document.driver.command.value = "<%=Command.START%>";
                document.driver.hidden_driver_id.value = "0";
                document.driver.action = "pbb.jsp";
                document.driver.submit();
            }

            function cmdStop() {

                document.driver.command.value = "<%=Command.STOP%>";
                document.driver.start.value = "0";
                document.driver.action = "pbb.jsp";
                document.driver.submit();

            }

            function lockScreen(str)
            {
                var lock = document.getElementById('theLockPane');
                if (lock)
                    lock.className = 'LockOn';

                lock.innerHTML = str;
            }
            
            function cmdSearch(){
                document.driver.command.value="<%=Command.SEARCH%>";
                document.driver.prev_command.value="<%=prevCommand%>";
                document.driver.action="laporan_history_perubahan_data.jsp";
                document.driver.submit();
            }
            
            function cmdSearchExcel(){
                document.driver.command.value="<%=Command.SEARCH%>";
                document.driver.action="<%=approot%>/dslik/export/laporan_history_perubahan_data_excel.jsp";
                document.driver.submit();
            }
            
        </script>
    </head>
    <body class="<%= skin%>">
        <input type="hidden" name="command" id="command" value="<%= Command.NONE%>">
        <input type="hidden" name="approot" id="approot" value="<%= approot%>">
        <div class="wrapper">

            <%@include file="../../template-component/header-component.jsp" %>
            <%@include file="../../template-component/sidebar-component.jsp" %>

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Laporan 
                        <small>Nominatif Agunan</small>
                    </h1>
                </section>
                <!-- Main content -->
                <%
                    if(privView){
                %>
               <section class="content">
                    <form name="driver"  method ="post" action="" role="form">
                        <input type="hidden" name="command" value="<%=iCommand%>">
                        <input type="hidden" name="start" value="<%=start%>">
                        <input type="hidden" name="prev_command" value="<%=prevCommand%>">
                        <input type="hidden" name="menu" value="16">
                        <input type="hidden" name="tree" value="5">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="box">
                                    <div class="box-body">
                                        <div class="row">
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label>User</label>
                                                    <%=ControlCombo.draw("userid", "Semua User", ""+userid, userKey, userVal, "", "form-control") %>
                                            
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label>Tipe Dokument</label>
                                                    <%= ControlCombo.draw("doctype", "Semua Tipe Dokumen", ""+doctype, docTypeKey, docTypeKey, "", "form-control") %>
                                                    
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label>Dari Tanggal</label>
                                                    <div class="input-group">
                                                        <input type="text" name="daritanggal" class="form-control datepicker" data-provide="datepicker" data-date-format="yyyy-mm-dd">
                                                        <div class="input-group-addon">
                                                            <i class="fa fa-calendar"></i>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label>Sampai Tanggal</label>
                                                    <div class="input-group">
                                                        <input type="text" name="sampaitanggal" class="form-control datepicker" data-provide="datepicker" data-date-format="yyyy-mm-dd">
                                                        <div class="input-group-addon">
                                                            <i class="fa fa-calendar"></i>
                                                        </div>
                                                        <div class="input-group-btn">
                                                            <button type="button" class="btn btn-success" onclick="cmdSearch()"><i class="fa fa-search"></i></button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <h3 class="box-title">Detail per Segment</h3>
                                            </div>
                                        </div>
                                        <%if(iCommand==Command.SEARCH){%>
                                            <div class="row">
                                                <div class="col-md-12" id="reportsummarypersegment">
                                                    <div class='row'>
                                                        <div class='col-md-12'>
                                                            <div class='form-group' id="exportelement">
                                                                <table class="table table-bordered table-striped">
                                                                    <thead>
                                                                        <tr>
                                                                            <th>No</th>
                                                                            <th>Tanggal</th>
                                                                            <th>Aksi</th>
                                                                            <th>Log Detail</th>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                        <%
                                                                            String whereClause = "";
                                                                            if(userid != 0){
                                                                                if(whereClause.length() > 0){
                                                                                    whereClause +=" AND "+PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_USER_ID]+"='"+userid+"'";
                                                                                }else{
                                                                                    whereClause +=" "+PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_USER_ID]+"='"+userid+"'";
                                                                                }
                                                                            }
                                                                            
                                                                            if(doctype.length() > 0){
                                                                                if(whereClause.length() > 0){
                                                                                    whereClause +=" AND "+PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_DOCUMENT_TYPE]+"='"+doctype+"'";
                                                                                }else{
                                                                                    whereClause +=" "+PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_DOCUMENT_TYPE]+"='"+doctype+"'";
                                                                                }
                                                                            }
                                                                            
                                                                            if(dari.length() > 0 && sampai.length() > 0){
                                                                                if(whereClause.length() > 0){
                                                                                    whereClause +=" AND ("+PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_DOCUMENT_TYPE]+" BETWEEN '"+dari+"' AND '"+sampai+"')";
                                                                                }else{
                                                                                    whereClause +=" ("+PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_DOCUMENT_TYPE]+" BETWEEN '"+dari+"' AND '"+sampai+"')";
                                                                                }
                                                                            }
                                                                            
                                                                            Vector listLog = PstLogSysHistory.listPurchaseOrder(0, 0, whereClause, ""+PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_UPDATE_DATE]+" DESC");
                                                                            if(listLog.size() > 0){
                                                                                for(int i = 0; i < listLog.size(); i++){
                                                                                    LogSysHistory logSysHistory = (LogSysHistory) listLog.get(i);
                                                                                    %>
                                                                                    <tr>
                                                                                        <td><%= i+1 %></td>
                                                                                        <td><%= (logSysHistory.getLogUpdateDate() == null ? "" : Formater.formatDate(logSysHistory.getLogUpdateDate(), "yyyy-MM-dd")) %></td>
                                                                                        <td><%= logSysHistory.getLogUserAction() %></td>
                                                                                        <td><%= logSysHistory.getLogDetail() %></td>
                                                                                    </tr>
                                                                                    <%
                                                                                }
                                                                            }
                                                                        %>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class='row'>
                                                        <div class='col-md-12'>
                                                            <div class="form-group">
                                                                <%
                                                                    if(privPrint){
                                                                        %>
                                                                            <button type="button" class="btn btn-primary" onclick="javascript:cmdSearchExcel()"><i class="fa fa-save"></i> Export Excel</button>
                                                                            <button class="btn btn-danger btnback hidden" type="button"><i class="fa fa-save"></i> Export Pdf</button>
                                                                        <%
                                                                    }
                                                                %>
                                                                
                                                            </div>
                                                        </div>
                                                    </div>  
                                                </div>
                                            </div>
                                        <%
                                            }
                                        %>
                                    </div>        
                                </div><!-- /.box -->
                            </div>
                        </div>         
                    </form>
                </section><!-- /.content -->
                <%
                    }
                %>
            </div><!-- /.content-wrapper -->
            <!-- Add the sidebar's background. This div must be placed
                 immediately after the control sidebar -->
            <div class='control-sidebar-bg'></div>
            <%@include file="../../template-component/footer-component.jsp" %>
            <%@include file="../../template-component/plugins-component.jsp" %>
            <%--
            <script type="text/javascript">
                $(document).ready(function(){

                    var approot = "<%= approot %>";

                    //SET ACTIVE MENU
                    var activeMenu = function(parentId, childId){
                        $(parentId).addClass("active").find(".treeview-menu").slideDown();
                        $(childId).addClass("active");
                    };

                    var getDataFunction = function(onDone, onSuccess, approot, command, dataSend, servletName, dataAppendTo, notification){
                        /*
                         * getDataFor	: # FOR PROCCESS FILTER
                         * onDone	: # ON DONE FUNCTION,
                         * onSuccess	: # ON ON SUCCESS FUNCTION,
                         * approot	: # APPLICATION ROOT,
                         * dataSend	: # DATA TO SEND TO THE SERVLET,
                         * servletName  : # SERVLET'S NAME
                         */
                        $(this).getData({
                           onDone	: function(data){
                               onDone(data);
                           },
                           onSuccess	: function(data){
                                onSuccess(data);
                           },
                           approot	: approot,
                           dataSend	: dataSend,
                           servletName	: servletName,
                           dataAppendTo	: dataAppendTo,
                           notification : notification
                        });
                    }

                    //DATE PICKER FUNCTION
                    var datePicker = function(contentId, formatDate){
                        $(contentId).datepicker({
                            format : formatDate
                        });
                        $(contentId).on('changeDate', function(ev){
                            $(this).datepicker('hide');
                        });
                    };

                    //MODAL SETTING
                    var modalSetting = function(elementId, backdrop, keyboard, show){
                        $(elementId).modal({
                            backdrop	: backdrop,
                            keyboard	: keyboard,
                            show	: show
                        });
                    };

                    function iCheckBox(){
                        $("input[type='checkbox'], input[type='radio']").iCheck({
                            checkboxClass: 'icheckbox_minimal',
                            radioClass: 'iradio_minimal'
                        });

                        $(".tickdelete").click(function(){
                           var checked = $(this).find(":checkbox:checked").length; 
                           if(checked == 0){
                               $(this).find(":checkbox").iCheck('check');
                           }else{
                               $(this).find(":checkbox").iCheck('uncheck');
                           }
                        });
                    }
                    datePicker(".datepicker", "yyyy-mm-dd");
                    $("#search-btn").click(function(){
                       var command = <%= Command.SEARCH %>;
                       var datafor = "searchsummerysegmen";
                       var periode = $("#periode").val();
                       var cabang = $("#cabang").val();
                       var dataSend = {
                           "FRM_FIELD_DATA_FOR" : datafor,
                           "command" : command,
                           "periode" : periode,
                           "cabang" : cabang
                       };
                       var onDone = function(data){
                       };
                       
                       var onSuccess = function(data){
                       };
                       getDataFunction(onDone, onSuccess, approot, command, dataSend, "AjaxPelaporan", "#reportsummary", false);
                    });
                    
                    $("#search-btn-segment").click(function(){
                       var command = <%= Command.SEARCH %>;
                       var datafor = "searchpersegment";
                       var periode = $("#periodepersegment").val();
                       var cabang = $("#cabangpersegment").val();
                       var segment = $("#segment").val();
                       var dataSend = {
                           "FRM_FIELD_DATA_FOR" : datafor,
                           "command" : command,
                           "periode" : periode,
                           "cabang" : cabang,
                           "segment" : segment
                       };
                       var onDone = function(data){
                       };
                       
                       var onSuccess = function(data){
                       };
                       getDataFunction(onDone, onSuccess, approot, command, dataSend, "AjaxPelaporan", "#reportsummarypersegment", false);
                    });
                });
              </script>
            --%>
        </div><!-- ./wrapper -->
        
    </body>
</html>
