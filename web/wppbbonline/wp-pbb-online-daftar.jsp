<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java"%>
<%@page import="com.dimata.wponline.form.wajibpajakonline.FrmWajibPajak"%>
<%@page import="com.dimata.util.Command"%>
<%!
    final static int MAX_SESSION_IDLE=100000;
    
%>

<!DOCTYPE html>
<html lang="en">
  
<head>
    <meta charset="utf-8">
    <title>Wajib Pajak Bumi dan Bagunan - Registrasi</title>
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
    <div class="navbar navbar-fixed-top">	
        <div class="navbar-inner">		
            <div class="container">		
                <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a>
			
                <a class="brand" href="#">
                    Wajib Pajak				
                </a>	
                <div class="nav-collapse">
                    <ul class="nav pull-right">
                        <li class="">						
                            <a href="<%= request.getContextPath() %>/wppbbonline/wp-pbb-online-login.jsp" class="">
                                <i class="icon-large icon-user akun_baru"> Login</i>
                            </a>
                        </li>
                        
                    </ul>
                </div>
            </div> 	
	</div> 
    </div> 
    
    <div class="account-container register">	
	<div class="content clearfix">	
            <form action="POST" name="<%= FrmWajibPajak.FRM_WAJIB_PAJAK%>" id="<%= FrmWajibPajak.FRM_WAJIB_PAJAK%>">
                <input type="hidden" name="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_WP_ID]%>" id="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_WP_ID]%>" value="0">
                <input type="hidden" name="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_STATUS]%>" id="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_STATUS]%>" value="0">
                <input type="hidden" name="CONTEXT" id="CONTEXT" value="<%= request.getContextPath()%>">
                <input type="hidden" name="Command" id="Command" value="<%= Command.SAVE%>">
                <input type="hidden" name="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_KODE_KONFIRMASI]%>" id="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_KODE_KONFIRMASI]%>" value="">
                <center>
                    <img src="../images/logodispenda.jpg" alt="Dispenda Gianyar"><br><br> 
                    <h1>ONLINE PAJAK</h1>
                    <h2>KABUPATEN GIANYAR</h2>	
                </center>					
                <div class="login-fields">
                    <br>
                    <p>Form pendaftaran :</p>
                    <div class="field">
                        <label for="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NAMA_USER]%>">Nama User:</label>
                        <input type="text" id="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NAMA_USER]%>" name="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NAMA_USER]%>" value="" placeholder="Nama User" class="login" />
                        <div class="alert alert-danger" style="display: none;margin-top:4px;" id="error_<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NAMA_USER] %>"></div>				
                    </div> 	
                    <div class="field">
                        <label for="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_PASSWORD]%>">Password</label>	
                        <input type="password" id="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_PASSWORD]%>" name="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_PASSWORD]%>" value="" placeholder="Password" class="login" />
                        <div class="alert alert-danger" style="display: none;margin-top:4px;" id="error_<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_PASSWORD] %>"></div>				                   
                    </div> 
                    <div class="field">
                        <label for="ULANG_PASSWORD">Password</label>	
                        <input type="password" id="ULANG_PASSWORD" name="ULANG_PASSWORD" value="" placeholder="Ulangi Password" class="login" />
                        <div class="alert alert-danger" style="display: none;margin-top:4px;" id="error_ULANG_PASSWORD"></div>				                   
                    
                    </div> 
                    <div class="field">
                        <label for="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NAMA_WAJIB_PAJAK]%>">Nama Wajib Pajak</label>
                        <input type="text" id="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NAMA_WAJIB_PAJAK]%>" name="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NAMA_WAJIB_PAJAK]%>" value="" placeholder="Nama Wajib Pajak" class="login"/>
                        <div class="alert alert-danger" style="display: none;margin-top:4px;" id="error_<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NAMA_WAJIB_PAJAK]%>"></div>				                   
                    
                    </div> 	
                    <div class="field">
                        <label for="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_ALAMAT]%>">Alamat</label>
                        <textarea class="login" id="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_ALAMAT]%>" placeholder="Alamat"  style="width: 96%" name="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_ALAMAT]%>"></textarea>
                    </div>
                    <div class="field">
                        <label for="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NOP]%>">NOP</label>
                        <input type="text" id="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NOP]%>" name="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NOP]%>" value="" placeholder="NOP" class="login"/>
                        <div class="alert alert-danger" style="display: none;margin-top:4px;" id="error_<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NOP]%>"></div>				                   
                    
                    </div> 
                    <div class="field">
                        <label for="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_TELP]%>">Telepon</label>
                        <input type="text" id="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_TELP]%>" name="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_TELP]%>" value="" placeholder="Telepon" class="login"/>
                        <div class="alert alert-danger" style="display: none;margin-top:4px;" id="error_<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_TELP]%>"></div>				                   
               
                    </div>
                    <div class="field">
                        <label for="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_FAX]%>">Fax</label>
                        <input type="text" id="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_FAX]%>" name="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_FAX]%>" value="" placeholder="Fax" class="login"/>
                    </div>
                    <div class="field">
                        <label for="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_EMAIL]%>">Email</label>
                        <input type="text" id="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_EMAIL]%>" name="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_EMAIL]%>" value="" placeholder="Email" class="login"/>
                        <div class="alert alert-danger" style="display: none;margin-top:4px;" id="error_<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_EMAIL]%>"></div>				                   
                    </div>		
                        <div class="field">
                            <label for="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_TELP] %>">Captcha / Kode Keamanan</label>
                            <div style="position:relative;">
                                <button type="button" class="btn btn-success" style="position:absolute;top:0;right:0;z-index: 1;" id="reloadImage"><i class="icon icon-refresh"></i></button>
                                <img src="<%= request.getContextPath() %>/Kaptcha.jpg" id="kaptchaImage" width="100%" style="border-radius:5px;margin-bottom:4px;"/> 
                            </div>
                                <input type="text" name="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_TELP] %>" id="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_TELP] %>" value="" placeholder="" class="login password-field"/>
                            <div class="alert alert-danger" style="display: none;margin-top:4px;" id="error_<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_TELP] %>"></div>
                        </div>   
                </div> 
			
                <div class="login-actions">

                    <button id="btnDaftar" type="button" class="button btn btn-primary btn-large">Daftar</button>

                </div> 		
            </form>	
	</div> 
    </div>
    <div class="login-extra">
            <div id="result" style="display:none;text-align: center;"></div>
    </div>
    <div id="reportSystem" class="modal fade" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="informasiModal">INFORMASI</h4>
                </div>
                <div class="modal-body">
                    <div id="reportMessage" style="text-align: center;"></div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-danger">Tutup</button>
                </div>
            </div>
        </div>
    </div>
    
                  

<script src="../styles/wpupload/js/jquery-1.7.2.min.js"></script>
<script>
    $(document).ready(function(){
          
        function reloadCaptcha(){
            $("#kaptchaImage").hide()
	      .attr('src', '<%= request.getContextPath() %>/Kaptcha.jpg?' + Math.floor(Math.random()*100) )
	      .fadeIn();      
        }  
          
        //validate email
        function isValidEmailAddress(emailAddress) {
            var pattern = /^([a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+(\.[a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+)*|"((([ \t]*\r\n)?[ \t]+)?([\x01-\x08\x0b\x0c\x0e-\x1f\x7f\x21\x23-\x5b\x5d-\x7e\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|\\[\x01-\x09\x0b\x0c\x0d-\x7f\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))*(([ \t]*\r\n)?[ \t]+)?")@(([a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.)+([a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.?$/i;
            return pattern.test(emailAddress);
        };
        
        //clear form
        function clearForm(){
            $("#<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NAMA_USER]%>").val("");
	    $("#<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_PASSWORD]%>").val("");
            $("#ULANG_PASSWORD").val("");
            $("#<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NAMA_WAJIB_PAJAK]%>").val("");
            $("#<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_ALAMAT]%>").val("");
            $("#<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NOP]%>").val("");
            $("#<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_TELP]%>").val("");
            $("#<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_FAX]%>").val("");
            $("#<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_EMAIL]%>").val("");  
            $("#<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_KODE_KONFIRMASI]%>").val(Math.floor(Math.random() * 100000000) + 1);
            //alert(Math.floor(Math.random() * 6000000) + 1);
        }
        
        
        
        //validasi form 
        function validate(){
            var errorValidate = false;
	    var namaUser = $("#<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NAMA_USER]%>").val();
	    var password = $("#<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_PASSWORD]%>").val();
            var ulangPassword = $("#ULANG_PASSWORD").val();
            var namaWP = $("#<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NAMA_WAJIB_PAJAK]%>").val();
            var nop = $("#<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NOP]%>").val();
            var telp = $("#<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_TELP]%>").val();
            var email = $("#<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_EMAIL]%>").val();
            var captcha =$("#<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_TELP]%>").val();
            
            $("#result").css('display','none');
            if (namaUser.length == 0){
		errorValidate = true;
		$("#error_<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NAMA_USER] %>").html("<b><i class='icon icon-warning-sign'></i> Kesalahan : </b>Masukkan Nama User...").fadeIn("medium");
	    }else{
		$("#error_<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NAMA_USER] %>").fadeOut();
	    }
            
            if (password.length == 0){
		errorValidate = true;
		$("#error_<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_PASSWORD] %>").html("<b><i class='icon icon-warning-sign'></i> Kesalahan : </b>Masukkan Password...").fadeIn("medium");
	    }else{
		$("#error_<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_PASSWORD] %>").fadeOut();
	    }
            
            if (ulangPassword.length == 0){
		errorValidate = true;
           
		$("#error_ULANG_PASSWORD").html("<b><i class='icon icon-warning-sign'></i> Kesalahan : </b>Ulangi Password...").fadeIn("medium");
	    }else{
		if (ulangPassword!=password){
                    errorValidate = true;
                   
                    $("#error_ULANG_PASSWORD").html("<b><i class='icon icon-warning-sign'></i> Kesalahan : </b>Ulang Password Tidak Sama Dengan Password").fadeIn("medium");
                }else{
                    $("#error_ULANG_PASSWORD").fadeOut();
                    
                }
	    }
            
            if (namaWP.length == 0){
		errorValidate = true;
		$("#error_<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NAMA_WAJIB_PAJAK]%>").html("<b><i class='icon icon-warning-sign'></i> Kesalahan : </b>Masukkan Nama Wajib Pajak...").fadeIn("medium");
	    }else{
		$("#error_<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NAMA_WAJIB_PAJAK] %>").fadeOut();
	    }
            
            if (nop.length == 0){
		errorValidate = true;
		$("#error_<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NOP]%>").html("<b><i class='icon icon-warning-sign'></i> Kesalahan : </b>Masukkan NOP...").fadeIn("medium");
	    }else{
		$("#error_<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NOP] %>").fadeOut();
	    }
            
            if (telp.length == 0){
		errorValidate = true;
		$("#error_<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_TELP]%>").html("<b><i class='icon icon-warning-sign'></i> Kesalahan : </b>Masukkan Telepon...").fadeIn("medium");
	    }else{
		$("#error_<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_TELP] %>").fadeOut();
	    }
            
            if (email.length == 0){
		errorValidate = true;
		$("#error_<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_EMAIL]%>").html("<b><i class='icon icon-warning-sign'></i> Kesalahan : </b>Masukkan Email...").fadeIn("medium");
	    }else{
		if(!isValidEmailAddress(email)) { 
                    errorValidate = true;
                    $("#error_<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_EMAIL]%>").html("<b><i class='icon icon-warning-sign'></i> Kesalahan : </b>Masukkan Email Yang Benar...").fadeIn("medium");
                }else{
                     $("#error_<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_EMAIL]%>").fadeOut();               
                }
	    }
            
            if (captcha.length == 0){
		errorValidate = true;
		$("#error_<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_TELP] %>").html("<b><i class='icon icon-warning-sign'></i> Kesalahan : </b>Masukkan Captcha...").fadeIn("medium");
	    }else{
		$("#error_<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_TELP] %>").fadeOut();
	    }
            
            return errorValidate;
            
        }
        clearForm();
        
        $('#reloadImage').click(function () {
			$(this).attr({"disabled":true});
			reloadCaptcha();
			$(this).removeAttr("disabled");
		});
        
        //saveUser
        function saveUser(){
            var userExist = false;
            var emailExist = false;
            var namaUser = $("#<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NAMA_USER]%>").val();
            var email = $("#<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_EMAIL]%>").val();
            //mengecek user sudah ada atau belum
            $.ajax({
                type:"GET",
                data:"Command=<%=Command.ASK%>&user=" + namaUser,
                url:"<%= request.getContextPath()%>/AjaxWajibPajak",     
                success: function(data){   
                   if(data!=0){
                       userExist = true;      
                   }else{
                       userExist = false;                
                   }
                }
            }).done(function() {
                if (userExist==false){                  
                    //cek email exist
                    $.ajax({
                        type:"GET",
                        data:"Command=<%=Command.START%>&email=" + email,
                        url:"<%= request.getContextPath()%>/AjaxWajibPajak",     
                        success: function(data){   
                           if(data!=0){
                               emailExist = true;      
                           }else{
                               emailExist = false;                
                           }
                        }
                    }).done(function() {
                        if (emailExist==false){
                            $("#error_<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NAMA_USER] %>").fadeOut();
                            $("#btnDaftar").html("Mohon Tunggu...").attr({"disabled":true});
                            //lanjutkan dengan penyimpanan user
                            $.ajax({
                               type	: "POST",
                               url	: "<%= request.getContextPath() %>/AjaxWajibPajak",
                               data	: $('#<%= FrmWajibPajak.FRM_WAJIB_PAJAK%>').serialize(),
                               cache	: false,
                               success	: function(data){
                                   clearForm();
                                   $("#btnDaftar").removeAttr("disabled").html("Daftar");
                                   $("#informasiModal").html('INFORMASI');
                                   $("#reportMessage").html('Pendaftaran berhasil dilakukan..<br>Sistem sudah mengirim link untuk konfirmasi pendaftaran ke email anda.<br> Silahkan cek kotak masuk email anda. Terimakasih...')
                                   $("#reportSystem").modal("show");
                               },
                               error : function(data){
                                   $("#btnDaftar").removeAttr("disabled").html("Daftar");
                                   $("#informasiModal").html('KESALAHAN');
                                   $("#reportMessage").html('Pendaftaran gagal dilakukan..<br>Silahkan cek koneksi internet anda...');
                                   $("#reportSystem").modal("show");
                               }
                           });
                            
                        }else{
                            $("#error_<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_EMAIL] %>").html("<b><i class='icon icon-warning-sign'></i> Kesalahan : </b>Email Sudah Terdaftar, Silahkan Ganti Email").fadeIn("medium"); 
                        }
                    });
                }else{
                    $("#error_<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NAMA_USER] %>").html("<b><i class='icon icon-warning-sign'></i> Kesalahan : </b>Nama User Sudah Terdaftar, Silahkan Ganti Nama User").fadeIn("medium"); 
                }
            });
            
        
        }
        
        $('.login').keyup(function(){
            var id = $(this).attr('id');
            var iderror = "error_" + id;
            $('#'+iderror+'').fadeOut();
        });
        
        $('#btnDaftar').click(function(){
            var errValidate = validate();
            if(errValidate==false){
                saveUser(); 
            }
  
            
        });
        
        $( ".login" ).keypress(function( event ) {
            if ( event.which == 13 ) {
              $('#btnDaftar').trigger('click')
            }
        });
        
      
    });
    
</script>
<script src="../styles/wpupload/js/bootstrap.js"></script>
<script src="../styles/wpupload/js/signin.js"></script>

</body>

</html>
