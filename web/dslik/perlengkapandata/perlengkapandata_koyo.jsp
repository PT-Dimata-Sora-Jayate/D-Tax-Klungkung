<%-- 
    Document   : home_marketing
    Created on : May 5, 2016, 11:23:02 AM
    Author     : dimata005
--%>
<%@page import="com.dimata.dslik.entity.debitur.Debitur"%>
<%@page import="java.util.Vector"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<%@page import="com.dimata.util.Command"%>
<!DOCTYPE html>
<%@include file="../../main/javainit_slik.jsp" %>
<%
    
    int iCommand = FRMQueryString.requestCommand(request);
    long oid = FRMQueryString.requestLong(request, "oid");
    int type = FRMQueryString.requestInt(request, "type");
    String contentHeader = ""
    + "<li>"
	+ "Perlengkapan Data"
    + "</li>";
    
    if(iCommand == Command.EDIT){
	contentHeader = ""
	+ "<li>"
	    + "<a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp'>Perlengkapan Data</a>"
	+ "</li>"
	+ "<li>"
	    + "Edit Perlengkapan Data"
	+ "</li>";
    }else if(iCommand == Command.VIEW){
	contentHeader = ""
	+ "<li>"
	    + "<a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp'>Perlengkapan Data</a>"
	+ "</li>"
	+ "<li>"
	    + "View Perlengkapan Data"
	+ "</li>";
    }
%>
<html>
  <head>
    <meta charset="UTF-8">
    <title>SLIK | PERLENGKAPAN DATA</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <%@include file="../../template-component/css-component.jsp" %>
    <style type="text/css">
	.active{
	    color : #444;
	}
	.ac
    </style>
     <script language="JavaScript">

          function cmdIndividu(){
              document.formperlengkapandatax.command.value="<%=Command.VIEW%>";
              document.formperlengkapandatax.action="perlengkapandata.jsp?command=8&oid=1&type=1";
              document.formperlengkapandatax.submit();
          }

          function cmdBadanUsaha(){
              document.formperlengkapandatax.command.value="<%=Command.VIEW%>";
              document.formperlengkapandatax.action="perlengkapandata.jsp?command=8&oid=1&type=2";
              document.formperlengkapandatax.submit();

          }
      </script>  
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
	    <%= contentHeader %>
          </ol>
        </section>

        <!-- Main content -->
	
        <form id="formperlengkapandata">
	    <input type="hidden" name="command" id="command" value="<%= Command.SAVE %>">
            <input type="hidden" name="oid" id="oid" value="<%= oid %>">
            <input type="hidden" name="type" id="type" value="<%= type %>">
            <input type="hidden" id="servletdestination">
            <input type="hidden" name="FRM_FIELD_DATA_FOR" id="dataforperlengkapan">
	    <section class="content">
	      <!-- Small boxes (Stat box) -->
	      <div class="row">
		<div class="col-md-12">
		    
		    <%
			if(iCommand == Command.NONE){
			    %>
				<div class='box box-primary'>
				    <div class="box-body">
					<div class="row">
					    <div class="col-md-12">
						<div id="perlengkapanDataElement">
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
				</div>
			    <%
			}else{
			    //TYPE 1 = INDIVIDU
			    //TYPE 2 = BADAN USAHA
			    Vector listTabName = new Vector();
			    Vector listTabTarget = new Vector();
			    Vector listColor = new Vector();
			    
			    //TAB COLOR 
			    listColor.add("background-color:#02b8fa;");
			    listColor.add("background-color:#1a439b;");
			    listColor.add("background-color:#88c426;");
			    listColor.add("background-color:#bcd63f;");
			    listColor.add("background-color:#d00405;");
			    listColor.add("background-color:#ff6711;");
			    listColor.add("background-color:#febf02;");
			    listColor.add("background-color:#a737fb;");
			    listColor.add("background-color:#f82b4c;");
			    listColor.add("background-color:#b25c11;");
			    listColor.add("background-color:#555555;");
			    
			    //TAB NAME
			    if(type == 2){
				listTabName.add("Debitur Badan Usaha");
				listTabTarget.add("AjaxDebiturBadanUsaha");
			    }else{
				listTabName.add("Debitur Individu");
				listTabTarget.add("AjaxDebiturIndividu");
			    }

                            if(type == 2){
				listTabName.add("Pengurus/Pemilik");
				listTabTarget.add("AjaxPengurusPemilik");
			    }

                            listTabName.add("Kredit");
                            listTabTarget.add("AjaxKredit");

                            listTabName.add("Bank Garansi");
                            listTabTarget.add("AjaxBankGaransi");

                            listTabName.add("Agunan");
                            listTabTarget.add("AjaxAgunan");

			    listTabName.add("Penjamin");
                            listTabTarget.add("AjaxPenjamin");

                            if(type == 2){
				//TAB NAME
				listTabName.add("Laporan Keuangan Debitur");
				//TAB TARGET
				listTabTarget.add("AjaxLaporanKeuanganDebitur");
			    }    
                            listTabName.add("Surat Berharga");
                            listTabTarget.add("AjaxSuratBerharga");

			    listTabName.add("Kredit Join");
			    listTabTarget.add("AjaxKreditJoin");

			    listTabName.add("Irrevocable L/C");
                            listTabTarget.add("AjaxIrrevocable");

			    listTabName.add("Fasilitas Lainnya");
			    listTabTarget.add("AjaxFasilitasLainnya");

			    //TAB TARGET
			    
			    %>
				<!-- Custom Tabs -->
				
				<div class="row">
				    <div class="col-md-12">
					<button type="button" class="btn btn-primary pull-right" id="searchdebitur" data-command="<%= Command.NONE %>" data-target="AjaxPerlengkapanData" data-for="searchform"><i class="fa fa-search"></i> Pencarian Data Debitur</button><br><br>
				    </div>
				</div>
				<div class="row">
				    <div class="col-md-12">
					<div class="nav-tabs-custom">
					    <ul class="nav nav-tabs" id="tabmenu">
					      <%
						  String tabs = "";
						  String tabsOther = "";
						  if(listTabName != null){
						      for(int i = 0; i < listTabName.size();i++){
							  String data = (String) listTabName.get(i);
							  String target = (String) listTabTarget.get(i);
							  String color = (String) listColor.get(i);
							  String active = "";
							  String aStyle = "style='color:white;'";
							  if(i == 0){
							      active = "active";
							      aStyle ="";
							  }



							  /*if(i > 7){
							      tabsOther+=""
							      + "<li role='presentation'>"
								  + "<a role='menuitem' tabindex='-1' href='#' data-target='"+target+"' class='tabmenu'>"+data+"</a>"
							      + "</li>";
							  }else{*/
							      tabs +=""
							      + "<li class='"+active+" menutab' style='cursor:pointer;margin-right:0px;"+color+"' data-target='"+target+"' data-for='showform' data-oid='"+i+" data-command='"+Command.NONE+"' data-for-save=''>"
								  + "<a hre='#' data-toggle='tab' class='tabmenu' "+aStyle+">"+data+"</a>"
							      + "</li>";
							  /*}*/
						      }
						      if(tabsOther.length() > 0){
							  tabsOther=""
							  + "<li class='dropdown'>"
							      + "<a class='dropdown-toggle' data-toggle='dropdown' href='#'>"
								  + "Lainnya <span class='caret'></span>"
							      + "</a>"
							      + "<ul class='dropdown-menu'>"
								  + tabsOther
							      + "</ul>"
							  + "</li>";
						      }
						  }
					      %>
					      <%= tabs+""+tabsOther %>
					    </ul>
					    <div class="tab-content">
					      <div class="tab-pane active" id="tabscontent" style="background-color:#f1f1f1;">
					      </div><!-- /.tab-pane -->
						<div class="row" style="margin-top:10px;">
						    <div class="col-md-12">
							<div class="form-group">
                                                            <button type="submit" class="btn btn-primary" id="btnSave"><i class="fa fa-save"></i> Simpan</button>
							    <button class="btn btn-danger btnback" type="button"><i class="fa fa-arrow-left"></i> Kembali</button>
                                                            <%--<button class="btn btn-info pull-right" id="openhistory" ><i class="fa fa-history"></i>&nbsp; History Perubahan Data</button>--%>
                                                            <button type="button" class="btn btn-info pull-right" id="openhistory" data-command="<%= Command.LIST %>" data-target="AjaxPerlengkapanData" data-for="searchhistory"><i class="fa fa-history"></i> History Perubahan Data</button>
							</div>
						    </div>
						</div>
					    </div><!-- /.tab-content -->
					  </div><!-- nav-tabs-custom -->
				    </div>
				</div>
				
			    <%
			}
		    %>
		</div><!-- ./col -->
	      </div><!-- /.row -->

	    </section><!-- /.content -->
	</form>
      </div><!-- /.content-wrapper -->
      <!-- Add the sidebar's background. This div must be placed
           immediately after the control sidebar -->
      <div class='control-sidebar-bg'></div>
      <div id="searchmodal" class="modal fade" tabindex="-1">
	<div class="modal-dialog modal-lg">
	    <div class="modal-content">
		<div class="modal-header">
		    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		    <h4 class="modal-title"></h4>
		</div>
		<form id="generalform">
		    <input type="hidden" name="FRM_FIELD_DATA_FOR" id="datafor">
		    <input type="hidden" name="command" value="<%= Command.SAVE %>">
		    <input type="hidden" name="FRM_FIELD_OID" id="oid">
		    <div class="modal-body ">
			<div class="row">
			    <div class="col-md-3">
				<div class="form-group">
				    <label>NIK</label>
				    <input type="text" class="form-control">
				</div>
			    </div>
			    <div class="col-md-3">
				<div class="form-group">
				    <label>Nama Identitas</label>
				    <input type="text" class="form-control">
				</div>
			    </div>
			    <div class="col-md-3">
				<div class="form-group">
				    <label>NPWP</label>
				    <input type="text" class="form-control">
				</div>
			    </div>
			    <div class="col-md-3">
				<div class="form-group">
				    <label>Email</label>
				    <input type="text" class="form-control">
				</div>
			    </div>
			</div>
			<div class="row">
			    <div class="col-md-12">
				<div class="box-body addeditgeneral-body">
				    <table class="table table-striped table-bordered">
					<thead>
					    <tr>
						<td>No.</td>
                                                <td>Type Debitur</td>
						<td>NIK</td>
						<td>Nama Identitas</td>
						<td>NPWP</td>
						<td>Email</td>
						<td>Aksi</td>
					    </tr>
					</thead>
					<tbody>
					    <tr>
						<td>1.</td>
                                                <td>Individu</td>
						<td>123456</td>
						<td>Ktanjana</td>
						<td>123456</td>
						<td>identitas1@gmail.com</td>
						<td><button class="btn btn-primary" id="btngeneralform" data-dismiss="modal" onClick="javascript:cmdIndividu()">View</button></td>
					    </tr>
					    <tr>
						<td>2.</td>
                                                <td>Badan Usaha</td>
						<td>123456</td>
						<td>Yashoda</td>
						<td>123456</td>
						<td>identitas2@gmail.com</td>
						<td><button class="btn btn-primary" id="btngeneralform" data-dismiss="modal" onClick="javascript:cmdBadanUsaha()">View</button></td>
					    </tr>
					</tbody>
				    </table>
				</div>
			    </div>
			</div>
		    </div>
		    <div class="modal-footer">
			<button type="button" class="btn btn-primary" id="btngeneralform" data-dismiss="modal"><i class="fa fa-check"></i> Select </button>
			<button type="button" data-dismiss="modal" class="btn btn-danger"><i class="fa fa-ban"></i> Tutup</button>
		    </div>
		</form>
	    </div>
	</div>
    </div>
                    
     <div id="openhistorydata" class="modal fade" tabindex="-1">
	<div class="modal-dialog modal-lg">
	    <div class="modal-content">
		<div class="modal-header">
		    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		    <h4 class="modal-title"></h4>
		</div>
		<form id="generalhistoryform">
		    <input type="hidden" name="FRM_FIELD_DATA_HISTORY" id="datafor">
		    <input type="hidden" name="command" value="<%= Command.SAVE %>">
                    
		    <input type="hidden" name="FRM_FIELD_OID" id="oid">
		    <div class="modal-body ">
			<div class="row">
			    <div class="col-md-12">
				<div class="box-body addeditgeneral-body">
                                    <div id="historyTableElement">
                                        <table class="table table-striped table-bordered">
                                            <thead>
                                                <tr>
                                                    <td>No.</td>
                                                    <td>Tanggal</td>
                                                    <td>User</td>
                                                    <td>Tipe Perubahan</td>
                                                    <td>Detail Perubahan</td>
                                                    <td>Aksi</td>
                                                </tr>
                                            </thead>                                           
                                        </table>
                                    </div>
				</div>
			    </div>
			</div>
		    </div>
		</form>
	    </div>
	</div>
    </div>               
    
      <%@include file="../../template-component/footer-component.jsp" %>
      
      <%@include file="../../template-component/plugins-component.jsp" %>
      <script type="text/javascript">
	$(document).ready(function(){
	    
	    var approot = "<%= approot %>";
	    
	    //SET ACTIVE MENU
	    var activeMenu = function(parentId, childId){
		$(parentId).addClass("active").find(".treeview-menu").slideDown();
		$(childId).addClass("active");
	    }
	    
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
	    
	    function dataTablesOptions(elementIdParent, elementId, servletName, dataFor, callBackDataTables){
		var datafilter = $("#datafilter").val();
		var privUpdate = $("#privUpdate").val();
                var oid = $("#formperlengkapandata #oid").val();
		$(elementIdParent).find('table').addClass('table-bordered table-striped table-hover').attr({'id':elementId});
		$("#"+elementId).dataTable({"bDestroy": true,
		    "iDisplayLength": 10,
		    "bProcessing" : true,
		    "oLanguage" : {
			"sProcessing" : "<div class='progress progress-striped active'><div class='progress-bar progress-bar-primary' style='width: 100%'><b>Please Wait...</b></div></div>"
		    },
		    "bServerSide" : true,
		    "sAjaxSource" : "<%= approot %>/"+servletName+"?command=<%= Command.LIST%>&FRM_FIELD_DATA_FILTER="+datafilter+"&FRM_FIELD_DATA_FOR="+dataFor+"&privUpdate="+privUpdate+"&FRM_FIELD_OID="+oid+"",
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
            
            
	    
	    function vieweditdata(tableElementId, elementId){
		$(tableElementId).on("click", elementId, function(){
		    var oid = $(this).data("oid");
		    var command = $(this).data("command");
		    var type = $(this).data("type");
		    
		    $("#oid").val(oid);
		    $("#command").val(command);
		    $("#type").val(type);
		});
	    }
	    
	    function tabMenu(selector){
		$(selector).click(function(){
		    var target = $(this).data("target");
		    var oid = $("#oid").val();
		    var datafor = $(this).data("for");
		    var command = $(this).data("command");
		    $(".menutab").find("a").attr("style", "color:white;");
                    $("#servletdestination").val(target);
                    $("#dataforperlengkapan").val(datafor);
		    $(this).find("a").removeAttr("style");
		    var dataSend = {
			"FRM_FIELD_OID" : oid,
			"FRM_FIELD_DATA_FOR" : datafor,
			"command" : command
		    };
		    
		    var onDone = function(data){
			datePicker(".datepicker","yyyy-mm-dd");
		    };
		    
		    var onSuccess = function(data){
		    };
		    getDataFunction(onDone, onSuccess, approot, command, dataSend, target, "#tabscontent", false);
		});
	    }
	    
	    function loadActive(selector){
		var element = $(selector).find(".active");
		if(element.length > 0){
		    var target = element.data("target");
		    var oid = $("#oid").val();
		    var datafor = element.data("for");
		    var command = element.data("command");
                    $("#servletdestination").val(target);
                    $("#dataforperlengkapan").val(datafor);
		    var dataSend = {
			"FRM_FIELD_OID" : oid,
			"FRM_FIELD_DATA_FOR" : datafor,
			"command" : command
		    };

		    var onDone = function(data){
			datePicker(".datepicker","yyyy-mm-dd");
		    };

		    var onSuccess = function(data){
		    };
		    getDataFunction(onDone, onSuccess, approot, command, dataSend, target, "#tabscontent", false);
		}
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
	    
	    function showForm(selector, modal){
		$(selector).click(function(){
		    $(modal).modal("show");
		    var datafor = $(this).data("for");
		    var oid = $("#oid").val();
		    var command = $(this).data("command");
		    var target = $(this).data("target");
		    var modaltitle = "";
		    var datasend = {};
		    var onDone = function(data){
		    };
		    var onSuccess = function(data){
		    };
		    if(datafor == "searchform"){
			modaltitle = "Pencarian Data Debitur";
			datasend = {
			    "FRM_FIELD_OID" : oid,
			    "FRM_FIELD_DATA_FOR" : datafor,
			    "command" : command
			};
			getDataFunction(onDone, onSuccess, approot, command, datasend, target, "#formcontent", false);
		    }else if(datafor == "searchhistory"){
                        modaltitle = "Histori Perubahan Data";
			
                        dataTablesOptions("#historyTableElement", "tableHistoryDataElement", ""+target+"", ""+datafor+"", null);
                       
                    }
		    $(modal).find(".modal-title").html(modaltitle);
                    //alert(JSON.stringify(datasend));
		    
		    
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
	    
	    function btnback(selector, url){
		$(selector).click(function(){
		    window.location = url;
		});
	    }
	    
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
            
            $("#formperlengkapandata").submit(function(){
		var currentBtnHtml = $("#btnSave").html();
		$("#btnSave").html("Saving...").attr({"disabled":"true"});
                var servletTarget = $("#servletdestination").val();
                var command = "<%= Command.SAVE%>";
                onSuccess = function(data){
		};
                
                onDone = function(data){
                    if (servletTarget==="AjaxDebiturIndividu"){
                        $("#formperlengkapandata #oid").val(data.FRM_FIELD_RETURN_OID);     
                    }
                    $("#btnSave").removeAttr("disabled").html(currentBtnHtml);
                };
                
                var dataSend = $(this).serialize();
                getDataFunction(onDone, onSuccess, approot, command, dataSend, ""+servletTarget+"", null, true, "json");
		return false;
	    });
	    	    
	    btnback(".btnback","<%= approot %>/dslik/perlengkapandata/perlengkapandata.jsp");
	    modalSetting("#searchmodal","static",false,false);
	    showForm("#searchdebitur","#searchmodal");
            showForm("#openhistory","#openhistorydata");
            
	    runDataTable();
	    vieweditdata("#tablePerlengkapanDataElement", ".addedit");
	    tabMenu(".menutab")
	    activeMenu("#modperlengkapandata", "#perlengkapandata");
	    loadActive("#tabmenu");
	});
      </script>
      <script type='text/javascript'>
      </script>
  
    </div><!-- ./wrapper -->
  </body>
</html>
