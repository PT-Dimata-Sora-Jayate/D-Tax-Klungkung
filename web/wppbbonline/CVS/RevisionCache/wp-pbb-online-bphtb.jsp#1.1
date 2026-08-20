<%@page import="com.dimata.util.Command"%>
<%@page import="com.dimata.wponline.entity.wajibpajakonline.WajibPajak"%>
<%@page import="com.dimata.wponline.entity.wajibpajakonline.PstWajibPajak"%>
<%@page import="com.dimata.wponline.form.wajibpajakonline.FrmWajibPajak"%>
<%@page import="com.dimata.wponline.form.wajibpajakonline.CtrlWajibPajak"%>

<%@page import="java.util.Vector"%>
<%@page import="com.dimata.wponline.session.SessUserWPOnlineSession"%>

<%
    String activeHome="";
    String activeDaftarPBB = "";
    String activeDaftarBPHTB = "active";
    
 
    Vector listWajibPajak = new Vector(1,1);  
    WajibPajak wajibPajak = new WajibPajak();
    
    listWajibPajak = PstWajibPajak.list(0, 0, ""+PstWajibPajak.fieldNames[PstWajibPajak.FLD_NAMA_USER]+"='"+session.getValue(SessUserWPOnlineSession.HTTP_SESSION_WPNAME)+"'", "");
    
    
    if(listWajibPajak.size() != 0){
	wajibPajak = (WajibPajak) listWajibPajak.get(0);
    }
    
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
    
    <script src="../styles/wpupload/js/jquery-1.7.2.min.js"></script>
    <script src="../styles/wpupload/js/bootstrap.js"></script>
    <script src="../styles/wpupload/js/base.js"></script>
    <!--Data Table -->
    <link href="../styles/wpupload/datatables-plugins/integration/bootstrap/2/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="../styles/wpupload/datatables-responsive/css/dataTables.responsive.css" rel="stylesheet">
    <script src="../styles/wpupload/datatables/media/js/jquery.dataTables.min.js"></script>
    <script src="../styles/wpupload/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {  
            var noTib = $('#tib').val(); 
            function loadTable1(noTib){
                $('#tabledata1').dataTable({
                    "destroy": true,
                    "bJQueryUI" : true,
                    "sPaginationType" : "full_numbers",
                    "iDisplayLength": 10,
                    "bProcessing" : true,
                    "bServerSide" : true,
                    "sAjaxSource" : "<%= request.getContextPath() %>/AjaxBphtb?Command=<%= Command.LIST%>&nop=<%= wajibPajak.getNop()%>&tib="+noTib+"",
                    "aoColumns" :   [{"bSearchable" : true, "bVisible" : true, "asSorting" : [ "asc" ] },
                                    {"sWidth" : "10%","bSortable" : true },
                                    {"sWidth" : "30%","bSortable" : true },
                                    {"sWidth" : "10%","bSortable" : true },
                                    {"sWidth" : "10%","bSortable" : true },
                                    {"sWidth" : "10%","bSortable" : true },
                                    {"sWidth" : "10%","bSortable" : true },
                                    {"sWidth" : "10%","bSortable" : true }]
                });


            }
            
            function loadTable2(noTib){
                $('#tabledata2').dataTable({
                    "destroy": true,
                    "bJQueryUI" : true,
                    "sPaginationType" : "full_numbers",
                    "iDisplayLength": 10,
                    "bProcessing" : true,
                    "bServerSide" : true,
                    "sAjaxSource" : "<%= request.getContextPath() %>/AjaxPaymentBphtb?Command=<%= Command.LIST%>&nop=<%= wajibPajak.getNop()%>&tib="+noTib+"",
                    "aoColumns" :   [{"bSearchable" : true, "bVisible" : true, "asSorting" : [ "asc" ] },
                                    {"sWidth" : "10%","bSortable" : true },
                                    {"sWidth" : "10%","bSortable" : true },
                                    {"sWidth" : "10%","bSortable" : true },
                                    {"sWidth" : "10%","bSortable" : true },
                                    {"sWidth" : "10%","bSortable" : true },          
                                    {"sWidth" : "10%","bSortable" : true },
                                    {"sWidth" : "10%","bSortable" : true },
                                    {"sWidth" : "10%","bSortable" : true },
                                    {"sWidth" : "10%","bSortable" : true }]
                });


            }
            
             
            loadTable1(noTib);
            loadTable2(noTib);
            
            $('#btnSearch').click(function(){
                noTib = $('#tib').val(); 
                loadTable1(noTib);
                loadTable2(noTib);
            });
            
            
        });
        
        
    </script>
    
    <!-- css optional untuk bootstrap, khusus untuk template ini -->
    <link href="../styles/wpupload/css/pages/optional.css" rel="stylesheet">
</head>
    <body>
        <%@include file ="wp-pbb-online-top.jsp" %>
        <%@include file="wp-pbb-menu.jsp" %>
        <div class="main">
            <div class="main-inner">
                <div class="container">
                  <div class="row">
                    <div class="span12">      		
                        <div class="widget ">
                            <div class="widget-header">
                                <i class="icon-play-circle"></i>
                                <h3>NO TIB</h3>
                                
                            </div> 
                            <div class="widget-content">
                                <div class="control-group">											
                                   
                                    <div class="controls">
                                        <input type="text" class="span4" id="tib" name="tib" value="" placeholder="No TIB.."> 
                                        <button id="btnSearch" type="button" class="btn btn-primary" style="margin-top: -8px;">
                                            <i class=" icon-search "></i>
                                            Search
                                        </button>
                                        <p class="help-block">Masukkan no TIB untuk menampilkan Daftar BPHTB dan Daftar Pembayaran BPHTB</p>
                                    </div> 			
                                </div>
                            </div> 
                        </div> 

                    </div> 
                  </div>
                  <div class="row">
                    <div class="span12">      		
                        <div class="widget ">
                            <div class="widget-header">
                                <i class="icon-list"></i>
                                <h3>DAFTAR BPHTB</h3>
                                
                            </div> 
                            <div class="widget-content">
                                <div class="tabbable"> 
                                    <div class="table-responsive" style="overflow-x: auto;">
                                        <table style="width: 100%"  class="example table table-striped table-bordered table-hover display" id="tabledata1">
                                        <thead>
                                            <tr>
                                                <th>Masa Pajak</th>
                                                <th>Tahun Pajak</th>
                                                <th>Nama</th>
                                                <th>Pokok</th>
                                                <th>Denda</th>
                                                <th>Jumlah</th>
                                                <th>Instansi</th>
                                                <th>NOP</th>

                                            </tr>
                                        </thead>
                                    </table>
                                    </div>
                                </div>

                            </div> 
                        </div> 

                    </div> 
                  </div> 
                  <div class="row">
                    <div class="span12">      		
                        <div class="widget ">
                            <div class="widget-header">
                                <i class="icon-list"></i>
                                <h3>DAFTAR PEMBAYARAN BPHTB</h3>
                                
                            </div> 
                            <div class="widget-content">
                                <div class="tabbable"> 
                                    <div class="table-responsive" style="overflow-x: auto;">
                                    <table style="width: 100%"  class="example table table-striped table-bordered table-hover display" id="tabledata2">
                                        <thead>
                                            <tr>
                                                <th>TIB</th>
                                                <th>Register</th>
                                                <th>Jumlah Pajak</th>
                                                <th>Jumlah Bayar</th>
                                                <th>Tanggal Bayar</th>
                                                <th>Dibuat Oleh</th>
                                                <th>Dibuat Tanggal</th>                                            
                                                <th>Jumlah Bayar Validasi</th>
                                                <th>Nilai TKP</th>
                                                <th>Validasi Bayar PPAT</th>
                                            </tr>
                                        </thead>
                                    </table>
                                    </div>
                                </div>

                            </div> 
                        </div> 

                    </div> 
                  </div> 
                </div> 

            </div> 
            <br>
        
        </div>
        
        <!-- Table View Pembayaran-->
        
        
        
       
        <%@include file="wp-pbb-online-footer.jsp" %>
        
        
    </body>
</html>


