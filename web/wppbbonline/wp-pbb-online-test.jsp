<%@page import="com.dimata.util.Command"%>
<%@page import="com.dimata.wponline.entity.wajibpajakonline.WajibPajak"%>
<%@page import="com.dimata.wponline.entity.wajibpajakonline.PstWajibPajak"%>
<%@page import="com.dimata.wponline.form.wajibpajakonline.FrmWajibPajak"%>
<%@page import="com.dimata.wponline.form.wajibpajakonline.CtrlWajibPajak"%>

<%@page import="java.util.Vector"%>
<%@page import="com.dimata.wponline.session.SessUserWPOnlineSession"%>

<%
    String activeHome="";
    String activeDaftarPBB = "active";
    String activeDaftarBPHTB = "";
    
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
    <script src="../styles/wpupload/datatables-plugins/type-detection/formatted-num.js"></script>
    <script src="../styles/wpupload/datatables-plugins/type-detection/currency.js"></script>
    <script src="../styles/wpupload/datatables-plugins/type-detection/num-html.js"></script>
    <script src="../styles/wpupload/datatables-plugins/type-detection/numeric-comma.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {  
            var tableData1 = $('#tabledata1').dataTable({
                "bJQueryUI" : true,
                "sPaginationType" : "full_numbers",
                "iDisplayLength": 10,
                "bProcessing" : true,
                "bServerSide" : true,
               
                "sAjaxSource" : "<%= request.getContextPath() %>/AjaxPaymentPbb?Command=<%= Command.LIST%>&nop=<%= wajibPajak.getNop()%>",
                "aoColumns" :   [{"bSearchable" : true, "bVisible" : true, "asSorting" : [ "asc" ] },
                                {"sWidth" : "10%","bSortable" : true },
                                {"sWidth" : "10%","bSortable" : true },
                                {"sWidth" : "10%","bSortable" : true },
                                {"sWidth" : "10%","bSortable" : true },
                                {"sWidth" : "10%","bSortable" : true },
                                {"sWidth" : "10%","bSortable" : true },
                                {"sWidth" : "10%","bSortable" : true },
                                {"sWidth" : "10%","bSortable" : true },
                                {"sWidth" : "10%","bSortable" : true },
                                {"sWidth" : "10%","bSortable" : true },
                                {"sWidth" : "15%","bSortable" : true },
                                {"sWidth" : "10%","bSortable" : true },
                                {"sWidth" : "15%","bSortable" : true },
                                {"sWidth" : "15%","bSortable" : true },
                                {"sWidth" : "15%","bSortable" : true },
                                {"sWidth" : "15%","bSortable" : true },
                                {"sWidth" : "15%","bSortable" : true }]
            });
            
        });
    </script>
    
    <!-- css optional untuk bootstrap, khusu untuk template ini -->
    <link href="../styles/wpupload/css/pages/optional.css" rel="stylesheet">
</head>
<body>
        <%@include file ="wp-pbb-online-top.jsp" %>
        <%@include file="wp-pbb-menu.jsp" %>
        <div class="main">
            <div class="main-inner">
                <div class="container">
                    <!-- View Pembayaran PBB -->
                    
                    <div class="row">
                      <div class="span12">      		
                          <div class="widget ">
                              <div class="widget-header">
                                  <i class="icon-list"></i>
                                  <h3>DAFTAR PEMBAYARAN PBB</h3>
                              </div> 
                              <div class="widget-content">
                                    <div class="">                                   
                                        <table class=" table table-striped table-bordered table-hover">
                                            <thead>
                                                <tr>
                                                    <th>NOP</th>
                                                    <th>Kode Propinsi</th>
                                                    <th>Kode Dati 2</th>
                                                    <th>Kode Kecamatan</th>
                                                    <th>Kode Kelurahan</th>
                                                    <th>Kode Blok</th>
                                                    <th>No Urut</th>
                                                    <th>Kode Jenis OP</th>
                                                    <th>Tahun Pajak SPPT</th>
                                                    <th>Pembayaran SPPT</th>
                                                    <th>Kode Kanwil Bank</th>
                                                    <th>Kode KPPBB Bank</th>
                                                    <th>Kode Bank Tunggal</th>
                                                    <th>Kode Bank Persepsi</th>
                                                    <th>Kode Tp</th>
                                                    <th>Denda</th>
                                                    <th>Jumlah Dibayar</th>
                                                    <th>Tgl Pembayaran</th>
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
            <br>
            
            
            
        </div>
        
        
        
        
       
        <%@include file="wp-pbb-online-footer.jsp" %>
        
        
    </body>