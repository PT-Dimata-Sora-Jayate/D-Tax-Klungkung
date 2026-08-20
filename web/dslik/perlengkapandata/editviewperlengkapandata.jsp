<%-- 
    Document   : home_marketing
    Created on : May 5, 2016, 11:23:02 AM
    Author     : dimata005
--%>
<%@page import="com.dimata.util.Command"%>
<!DOCTYPE html>
<%@include file="../../main/javainit_slik.jsp" %>
<html>
  <head>
    <meta charset="UTF-8">
    <title>SLIK | HOME</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <%@include file="../../template-component/css-component.jsp" %>
  </head>
  <body class="<%=skin%>">
    <div class="wrapper">
     
	<%@include file="../../template-component/header-component.jsp" %>
        <%@include file="../../template-component/sidebar-component.jsp" %>
      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            Perlengkapan Data
          </h1>
          <ol class="breadcrumb">
            <li><a href="<%= approot %>/home_slik.jsp"><i class="fa fa-home"></i> Home</a></li>
	    <li>Modul Perlengkapan Data</li>
	    <li>Perlengkapan Data</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
          <!-- Small boxes (Stat box) -->
          <div class="row">
            <div class="col-md-12">
		<div class='box box-primary'>
		    <div class="box-body">
			<div class="row">
			    <div class="col-md-12" id="perlengkapanDataElement">
				<table class="table table-bordered table-striped">
				    <thead>
					<tr>
					    <td>No.</td>
					    <td>NIK</td>
					    <td>Nama Identitas</td>
					    <td>Jenis Kelamin</td>
					    <td>NPWP</td>
					    <td>Alamat</td>
					    <td>Telp / Hp</td>
					    <td>Email</td>
					    <td>Aksi</td>
					</tr>
				    </thead>
				</table>
			    </div>
			</div>
		    </div>
		</div>
            </div><!-- ./col -->
          </div><!-- /.row -->

        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->
      <!-- Add the sidebar's background. This div must be placed
           immediately after the control sidebar -->
      <div class='control-sidebar-bg'></div>
      <%@include file="../../template-component/footer-component.jsp" %>
      
      <%@include file="../../template-component/plugins-component.jsp" %>
      <script type="text/javascript">
	$(document).ready(function(){
	    //SET ACTIVE MENU
	    var activeMenu = function(parentId, childId){
		$(parentId).addClass("active").find(".treeview-menu").slideDown();
		$(childId).addClass("active");
	    }
	    
	    
	    
	    function dataTablesOptions(elementIdParent, elementId, servletName, dataFor, callBackDataTables){
		var datafilter = $("#datafilter").val();
		var privUpdate = $("#privUpdate").val();
		$(elementIdParent).find('table').addClass('table-bordered table-striped table-hover').attr({'id':elementId});
		$("#"+elementId).dataTable({"bDestroy": true,
		    "iDisplayLength": 10,
		    "bProcessing" : true,
		    "oLanguage" : {
			"sProcessing" : "<div class='progress progress-striped active'><div class='progress-bar progress-bar-primary' style='width: 100%'><b>Please Wait...</b></div></div>"
		    },
		    "bServerSide" : true,
		    "sAjaxSource" : "<%= approot %>/"+servletName+"?command=<%= Command.LIST%>&FRM_FIELD_DATA_FILTER="+datafilter+"&FRM_FIELD_DATA_FOR="+dataFor+"&privUpdate="+privUpdate,
		    aoColumnDefs: [
			{
			   bSortable: false,
			   aTargets: [ -1 ]
			}
		      ],
		    "initComplete": function(settings, json) {
			if(callBackDataTables != null){
			    callBackDataTables();
			}
		    },
		    "fnDrawCallback": function( oSettings ) {
			if(callBackDataTables != null){
			    callBackDataTables();
			}
		    },
		    "fnPageChange" : function(oSettings){

		    }
		});

		$(elementIdParent).find("#"+elementId+"_filter").find("input").addClass("form-control");
		$(elementIdParent).find("#"+elementId+"_length").find("select").addClass("form-control");
		$("#"+elementId).css("width","100%");
	    }
	    
	    function runDataTable(){
		dataTablesOptions("#perlengkapanDataElement", "tablePerlengkapanDataElement", "AjaxPerlengkapanData", "listperlengkapandata", null);
	    }
	    runDataTable();
	    
	    activeMenu("#modperlengkapandata", "#perlengkapandata");
	});
      </script>
      <script type='text/javascript'>
      </script>
  
    </div><!-- ./wrapper -->
  </body>
</html>
