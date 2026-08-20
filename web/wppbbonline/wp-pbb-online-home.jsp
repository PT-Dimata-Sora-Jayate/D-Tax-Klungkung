<%@page import="com.dimata.util.Command"%>
<%@page import="com.dimata.wponline.entity.wajibpajakonline.WajibPajak"%>
<%@page import="com.dimata.wponline.entity.wajibpajakonline.PstWajibPajak"%>
<%@page import="com.dimata.wponline.form.wajibpajakonline.FrmWajibPajak"%>
<%@page import="com.dimata.wponline.form.wajibpajakonline.CtrlWajibPajak"%>

<%@page import="java.util.Vector"%>
<%@page import="com.dimata.wponline.session.SessUserWPOnlineSession"%>

<%
    Vector listWajibPajak = new Vector(1,1);  
    WajibPajak wajibPajak = new WajibPajak();
    
    listWajibPajak = PstWajibPajak.list(0, 0, ""+PstWajibPajak.fieldNames[PstWajibPajak.FLD_NAMA_USER]+"='"+session.getValue(SessUserWPOnlineSession.HTTP_SESSION_WPNAME)+"'", "");
    
    
    if(listWajibPajak.size() != 0){
	wajibPajak = (WajibPajak) listWajibPajak.get(0);
    }
    
%>
<%
    String activeHome="active";
    String activeDaftarPBB = "";
    String activeDaftarBPHTB = "";
    
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="utf-8">
    <title>Wajib Pajak Bumi dan Bagunan - Home </title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">  
    <link href="../styles/wpupload/img/favicon.ico" rel="shortcut icon" />
    <link href="../styles/wpupload/css/bootstrap.min.css" rel="stylesheet">
    <link href="../styles/wpupload/css/bootstrap-responsive.min.css" rel="stylesheet">
    <link href="../styles/wpupload/css/font-awesome.css" rel="stylesheet">
    <link href="../styles/wpupload/css/font-google.css" rel="stylesheet">    
    <link href="../styles/wpupload/css/style.css" rel="stylesheet">
    <link href="../styles/wpupload/css/pages/dashboard.css" rel="stylesheet">
</head>
    <body>
        <%@include file ="wp-pbb-online-top.jsp" %>
        <%@include file="wp-pbb-menu.jsp" %>
        <div class="main" style="border-bottom: none;">
            <div class="main-inner">
                <div class="container">
                    <div class="row">
                        <div class="span12">
                            <div class="widget widget-nopad">
                                <div class="widget-header"> 
                                    <i class="icon-home"></i>
                                    <h3> SELAMAT DATANG</h3>
                                </div>
                                <div class="widget-content" style="min-height: 150px;">
                                    <div class="widget big-stats-container">
                                        <div class="widget-content">
                                            <h6 class="bigstats">
                                                Selamat datang <b style="color:black;"><%= wajibPajak.getNamaUser()%></b> di <b style="color:black;">Sistem Pelaporan Wajib Pajak.</b><br>
                                                Untuk menggunakan sistem ini silahkan gunakan menu yang ada diatas.
                                            </h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="widget widget-nopad">
                                <div class="widget-header"> 
                                    <i class="icon-info-sign"></i>
                                    <h3> INFORMASI WAJIB PAJAK</h3>
                                </div>
                                <div class="widget-content">
                                    <div class="widget big-stats-container">
                                        <div class="widget-content" style="min-height: 150px;">
                                            <div class="row">
                                                <br>
                                                <div class="span12">
                                                    <div class="span2">
                                                        <b>Nama Wajib Pajak</b>
                                                    </div>
                                                    <div class="span3">
                                                        : <%= wajibPajak.getNamaWajibPajak()%>
                                                    </div>
                                                    <div class="span2">
                                                        <b>Fax</b>
                                                    </div>
                                                    <div class="span3">
                                                        : <%= wajibPajak.getFax()%>
                                                    </div>
                                                </div> 
                                            </div>
                                            <div class="row">
                                                <div class="span12">
                                                    <div class="span2">
                                                        <b>Telepon</b>
                                                    </div>
                                                    <div class="span3">
                                                        : <%= wajibPajak.getTelp()%>
                                                    </div>
                                                    <div class="span2">
                                                        <b>Tgl Daftar</b>
                                                    </div>
                                                    <div class="span3">
                                                        : <%= wajibPajak.getTglPendaftaran()%>
                                                    </div>
                                                </div> 
                                            </div>
                                            <div class="row">
                                                <div class="span12">
                                                    <div class="span2">
                                                        <b>NOP</b>
                                                    </div>
                                                    <div class="span3">
                                                        : <%= wajibPajak.getNop()%>
                                                    </div>
                                                    <div class="span2">
                                                        <b>Email</b>
                                                    </div>
                                                    <div class="span3">
                                                        : <%= wajibPajak.getEmail()%>
                                                    </div>
                                                </div> 
                                            </div>
                                            <div class="row">
                                                <div class="span12">
                                                    <div class="span2">
                                                        <b>Alamat</b>
                                                    </div>
                                                    <div class="span3">
                                                        : <%= wajibPajak.getAlamat()%>
                                                    </div>

                                                    <div class="span2">
                                                        <b>Password</b>
                                                    </div>
                                                    <div class="span3">
                                                        : [ <a style="cursor: pointer" id="ubahPassword">Ubah</a> ]
                                                    </div>
                                                </div> 
                                            </div>
                                                
                                               
                                        </div> 
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
        </div>
        
        <div id="modalChangePassword" class="modal fade" tabindex="-1">
            <div class="modal-dialog modal-lg">
                <div class="modal-content" id="ajaxContent">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="modal-title">Pengaturan</h4>
                    </div>
                    <div class="modal-body" id="modal-body">
                        <div id="dynamicContent">

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <br/>
        <br/>
        <br/>
        
        <%@include file="wp-pbb-online-footer.jsp" %>
        <script src="../styles/wpupload/js/jquery-1.7.2.min.js"></script> 
        <script src="../styles/wpupload/js/excanvas.min.js"></script> 
        <script src="../styles/wpupload/js/chart.min.js" type="text/javascript"></script> 
        <script src="../styles/wpupload/js/bootstrap.js"></script>
        <script language="javascript" type="text/javascript" src="../styles/wpupload/js/full-calendar/fullcalendar.min.js"></script>
        <script src="../styles/wpupload/js/base.js"></script> 
        <script type="text/javascript">
            $(document).ready(function(){
                $('#ubahPassword').click(function(){
                    //alert('test');
                    var email ="<%= wajibPajak.getEmail()%>" ;
                    var curentPass ="";
                    var errCekPassword = false;
                   
                    $.ajax({
                        type: "GET",
                        url	: "<%= request.getContextPath() %>/AjaxWajibPajak",
                        data: "Command=<%= Command.RESET%>&email="+email+"",
                        cache: false,
                        success: function(data){ 
                           $('#dynamicContent').html(data);
                           
                        },
                        error : function(data){
                            alert('error');
                        }
                    }).done(function() {
                        function clearPopUpForm(){ 
                            $('#<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_PASSWORD]%>').val('');
                            $('#newPassword2').val('');
                            $('#newPassword2').attr({"disabled":true});
                            $('#<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_PASSWORD]%>').attr({"disabled":true});
                            $('#simpan').attr({"disabled":true});
                        }
                        function removeDisableForm(){
                            $('#<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_PASSWORD]%>').val('');
                            $('#newPassword2').val('');
                            $('#newPassword2').removeAttr("disabled");
                            $('#<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_PASSWORD]%>').removeAttr("disabled");
                            $('#simpan').removeAttr("disabled");
                        }
                        $('#modalChangePassword').modal('show');
                        $('#curentPassword').keyup(function(){
                            curentPass = $('#curentPassword').val();
                            $.ajax({
                                type: "GET",
                                url	: "<%= request.getContextPath() %>/AjaxWajibPajak",
                                data: "Command=<%= Command.DETAIL%>&email="+email+"&password="+curentPass+"",
                                cache: false,
                                success: function(data){
                                    
                                    if(data>0){
                                        errCekPassword = false;
                                    }else{
                                        errCekPassword = true;
                                    }
                                },
                                error : function(data){
                                    alert('error');
                                }
                            }).done(function() {
                                if (errCekPassword==true){
                                    //nggak cocok
                                    clearPopUpForm();
                                    $('#error_curentPassword').fadeIn('slow');
                                    $('#error_<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_PASSWORD]%>').fadeOut('slow');
                                }else{
                                    //cocok
                                    removeDisableForm();
                                    $('#error_curentPassword').fadeOut();
                                    $('#<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_PASSWORD]%>').change(function(){
                                        $('#error_<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_PASSWORD]%>').fadeOut();
                                    })
                                    $('#newPassword2').change(function(){
                                        $('#error_newPassword2').fadeOut(); 
                                    })
                                    $('#simpan').click(function(){
                                        var errReturn = false;
                                        var newPass1 = $('#<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_PASSWORD]%>').val();
                                        var newPass2 = $('#newPassword2').val();
                                        if (newPass1.length == 0){
                                            errReturn= true
                                            $('#error_<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_PASSWORD]%>').fadeIn('slow');
                                        }else{
                                            $('#error_<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_PASSWORD]%>').fadeOut();
                                        
                                        }
                                        
                                        if (newPass2.length == 0){
                                            errReturn= true
                                            $('#error_newPassword2 .span3').html('&nbsp;&nbsp; Ulang Password tidak boleh kosong');
                                            $('#error_newPassword2').fadeIn('slow');
                                        }else{
                                            if (newPass1!=newPass2){
                                                errReturn= true
                                                $('#error_newPassword2 .span3').html('&nbsp;&nbsp; Ulang Password harus sama');
                                                $('#error_newPassword2').fadeIn('slow');
                                            }else{
                                                $('#error_newPassword2').fadeOut();  
                                            }
                                            
                   
                                        } 
                                        
                                        if (errReturn!=true){
    
                                            $.ajax({
                                                type: "POST",
                                                url	: "<%= request.getContextPath() %>/AjaxWajibPajak",
                                                data: $('#<%= FrmWajibPajak.FRM_WAJIB_PAJAK%>').serialize(),
                                                cache: false,
                                                success: function(data){ 
                                                   $('#dynamicContent').html(data);

                                                },
                                                error : function(data){

                                                }
                                            });
                                       
                                        }
                                        
                                        
                                    })
                                    
                                }
                            });
                        });
                        
                    });
                    
                })
            });
        </script>
        
    </body>
</html>
