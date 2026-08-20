<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<%@page import="com.dimata.posbo.session.admin.SessUserSession"%>
<%@page import="com.dimata.wponline.form.wajibpajakonline.FrmWajibPajak"%>
<%@page import="com.dimata.util.Command"%>


<!DOCTYPE html>
<html lang="en">
  
<head>
    <meta charset="utf-8">
    <title>Wajib Pajak Bumi dan Bagunan - Konfirmasi Email</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">   
    <link href="../styles/wpupload/img/favicon.ico" rel="shortcut icon" />
    <link href="../styles/wpupload/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="../styles/wpupload/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css" />
    <link href="../styles/wpupload/css/font-awesome.css" rel="stylesheet">
    <link href="../styles/wpupload/css/font-google.css" rel="stylesheet">    
    <link href="../styles/wpupload/css/style.css" rel="stylesheet" type="text/css">
    <link href="../styles/wpupload/css/pages/signin.css" rel="stylesheet" type="text/css">
</head>

<body>
    <form action="POST" name="<%= FrmWajibPajak.FRM_WAJIB_PAJAK%>" id="<%= FrmWajibPajak.FRM_WAJIB_PAJAK%>">
        <input type="hidden" name="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_WP_ID]%>" id="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_WP_ID]%>" value="0">
        <input type="hidden" name="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_STATUS]%>" id="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_STATUS]%>" value="0">
        <input type="hidden" name="CONTEXT" id="CONTEXT" value="<%= request.getContextPath()%>">
        <input type="hidden" name="Command" id="Command" value="<%= Command.EDIT%>">
        <input type="hidden" name="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_KODE_KONFIRMASI]%>" id="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_KODE_KONFIRMASI]%>" value="">    
        <input type="hidden" id="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NAMA_USER]%>" name="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NAMA_USER]%>" value="" class="login" />
        <input type="hidden" id="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_PASSWORD]%>" name="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_PASSWORD]%>" value=""  class="login" />              
        <input type="hidden" id="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NAMA_WAJIB_PAJAK]%>" name="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NAMA_WAJIB_PAJAK]%>" class="login"/>       	
        <input type="hidden" class="login" id="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_ALAMAT]%>" name="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_ALAMAT]%>">>
        <input type="hidden" id="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NOP]%>" name="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NOP]%>" value="" class="login"/>                       
        <input type="hidden" id="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_TELP]%>" name="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_TELP]%>" value=""  class="login"/>
        <input type="hidden" id="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_FAX]%>" name="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_FAX]%>" value=""  class="login"/>
        <input type="hidden" id="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_EMAIL]%>" name="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_EMAIL]%>" value="" class="login"/>                    	
    </form>	
	
    <div id="reportSystem" class="modal fade" tabindex="-1">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button id="btnTutup2" type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="informasiModal">INFORMASI</h4>
                </div>
                <div class="modal-body">
                    <div id="reportMessage" style="text-align: center;">
                        <p>Mohon tunggu proses konfirmasi...</p>
                        <div class="progress progress-striped active">
                            <div class="bar" style="width: 100%;"></div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button id="btnTutup" type="button" data-dismiss="modal" class="btn btn-danger">Tutup</button>
                </div>
            </div>
        </div>
    </div>
    
<%
    String email= FRMQueryString.requestString(request, "email");
    String kodeKonfirmasi = FRMQueryString.requestString(request, "kode");
%>                

<script src="../styles/wpupload/js/jquery-1.7.2.min.js"></script>
<script>
    $(document).ready(function(){
          
        var errReturn = true; 
        var oId = "";
          
        $('#reportSystem').modal({
            backdrop: 'static',
            keyboard: false
          })
        $("#btnTutup").attr({"disabled":true});
        $("#btnTutup2").attr({"disabled":true});
        
        $('#reportSystem').on('hidden.bs.modal', function (e) {
            window.location ="<%= request.getContextPath()%>/wppbbonline/wp-pbb-online-daftar.jsp";
        })
        
        $.ajax({
            type: "GET",
            url	: "<%= request.getContextPath()%>/AjaxWajibPajak",
            data: "Command=<%=Command.GET%>&<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_EMAIL] %>=<%= email%>&<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_KODE_KONFIRMASI] %>=<%= kodeKonfirmasi%>",
            cache: false,
            success: function(data){
               if (data>0){
                   errReturn= false;
                   oId = data;
               }else{
                   errReturn = true;
                    $("#informasiModal").html('KESALAHAN');
                    $("#reportMessage").html('Konfirmasi gagal dilakukan,<br>Link yang anda gunakan sudah tidak berlaku...');
                    $("#btnTutup").removeAttr("disabled");
                    $("#btnTutup2").removeAttr("disabled");
                  
               }
            },
            error : function(data){
                $("#informasiModal").html('KESALAHAN KONEKSI');
                $("#reportMessage").html('Konfirmasi gagal dilakukan,<br>Silahkan periksa koneksi internet anda');
                $("#btnTutup").removeAttr("disabled");
                $("#btnTutup2").removeAttr("disabled");
            }
        }).done(function() {
            if (errReturn==false){
                //apabila user ada, maka dilakukan fetch sesuai oId, hasil fetch dimasukkan ke form
                $.ajax({
                    type:"GET",
                    data:"Command=<%=Command.VIEW%>&oId=" + oId,
                    url:"<%= request.getContextPath()%>/AjaxWajibPajak",     
                    success: function(data){   
                        var tamp = data.split(";");
                        $("#<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_WP_ID]%>").val(oId);
                        $("#<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NAMA_USER]%>").val(tamp[0]);
                        $("#<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_PASSWORD]%>").val(tamp[1]);
                        $("#<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NAMA_WAJIB_PAJAK]%>").val(tamp[2]);
                        $("#<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_ALAMAT]%>").val(tamp[3]);
                        $("#<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NOP]%>").val(tamp[4]);
                        $("#<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_TELP]%>").val(tamp[5]);
                        $("#<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_FAX]%>").val(tamp[6]);
                        $("#<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_STATUS]%>").val("1");
                        $("#<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_EMAIL]%>").val(tamp[8]);  
                        $("#<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_KODE_KONFIRMASI]%>").val('0');
                    }
                }).done(function() {
                    //setelah selesai di fetch dan masuk form, data tersebut di submit untuk update wajib pajak
                    $.ajax({
                        type	: "POST",
                        url	: "<%= request.getContextPath() %>/AjaxWajibPajak",
                        data	: $('#<%= FrmWajibPajak.FRM_WAJIB_PAJAK%>').serialize(),
                        cache	: false,
                        success	: function(data){
                            $("#informasiModal").html('INFORMASI');
                            $("#reportMessage").html('Konfirmasi berhasil dilakukan,<br>Sistem sudah mengirim hasil konfirmasi pendaftaran ke email anda.<br> Silahkan cek kotak masuk email anda. Terimakasih...');
                            $("#btnTutup").removeAttr("disabled");
                            $("#btnTutup2").removeAttr("disabled");
                        },
                        error : function(data){
                            $("#informasiModal").html('KESALAHAN KONEKSI');
                            $("#reportMessage").html('Konfirmasi gagal dilakukan,<br>Silahkan periksa koneksi internet anda...');
                            $("#btnTutup").removeAttr("disabled");
                            $("#btnTutup2").removeAttr("disabled");
                        }
                    });
                });
                
            }
        });
      
    });
    
</script>
<script src="../styles/wpupload/js/bootstrap.js"></script>
<script src="../styles/wpupload/js/signin.js"></script>

</body>

</html>
