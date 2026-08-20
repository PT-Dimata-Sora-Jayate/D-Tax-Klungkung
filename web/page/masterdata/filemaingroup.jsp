<%@page import="com.dimata.system.form.dataupload.FrmDataUploadGroup"%>
<%@page import="com.dimata.common.session.upload.SessUpload"%>
<%@page import="com.dimata.util.blob.ImageLoader"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<%@page import="com.dimata.util.Command"%>
<!DOCTYPE html>
<%@include file="../../main/javainit.jsp" %>


<html>
  <head>
    <meta charset="UTF-8">
    <title>DIAMAS | FILE MAIN GROUP</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <%@include file="../../template-component/css-component.jsp" %>
  </head>
  <body class="<%= skin %>">
      <input type="hidden" name="command" id="command" value="<%= Command.NONE %>">
      <input type="hidden" name="approot" id="approot" value="<%= approot %>">
    <div class="wrapper">
      
	<%@include file="../../template-component/header-component.jsp" %>
	<!-- Left side column. contains the logo and sidebar -->
	<%@include file="../../template-component/sidebar-component.jsp" %>

      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            Master Data
            <small>File Main Group</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Master Data</a></li>
            <li class="active">File Main Group</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
          <!-- Small boxes (Stat box) -->
          <div class="row">
            <div class="col-md-12">
		<div class='box box-primary'>
		    <div class='box-header'>
			File Main Group
		    </div>
		    <div class="box-body">
			<div id="fileMainGroup">
			    <table class="table table-bordered table-striped">
				<thead>
				    <tr>
					<th style="width:5%">No.</th>
                                        <th style="width:15">System Name</th>
					<th style="width:15%">Modul</th>
					<th style="width:20 ">Title</th>
                                        <th style="width:25 ">Description</th>
                                        <th style="width:20%">Action</th>
				    </tr>
				</thead>
			    </table>
			</div>
		    </div>
		    <div class='box-footer'>
			<button class="btn btn-primary btnaddgeneral" data-oid="0" data-for="showfilemaingroupform">
			    <i class="fa fa-plus"></i> Add File Main Group
			</button>
                        <button class="btn btn-danger btndeleteareausage" data-for="filemaingroup">
			    <i class="fa fa-trash"></i> Delete
			</button>
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

	    activeMenu("#masterdata", "#filemaingroup");
	    
	    
	    var approot = $("#approot").val();
	    var command = $("#command").val();
	    var dataSend = null;
	    
	    var oid = null;
	    var dataFor = null;

	    //FUNCTION VARIABLE
	    var onDone = null;
	    var onSuccess = null;
	    var callBackDataTables = null;
	    var iCheckBox = null;
	    var addeditgeneral = null;
	    var areaTypeForm = null;
	    var deletegeneral = null;
            var mapingForm = null;
	    
	    //MODAL SETTING
	    var modalSetting = function(elementId, backdrop, keyboard, show){
		$(elementId).modal({
		    backdrop	: backdrop,
		    keyboard	: keyboard,
		    show	: show
		});
	    };
	    
	    var getDataFunction = function(onDone, onSuccess, approot, command, dataSend, servletName, dataAppendTo, notification, dataType){
		/*
		 * getDataFor	: # FOR PROCCESS FILTER
		 * onDone	: # ON DONE FUNCTION,
		 * onSuccess	: # ON ON SUCCESS FUNCTION,
		 * approot	: # APPLICATION ROOT,
		 * dataSend	: # DATA TO SEND TO THE SERVLET,
		 * servletName  : # SERVLET'S NAME,
		 * dataType	: # Data Type (JSON, HTML, TEXT)
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
		   notification : notification,
		   ajaxDataType	: dataType
		});
	    }
	    
	    //SHOW ADD OR EDIT FORM
	    addeditgeneral = function(elementId){
		$(elementId).click(function(){
                    $("#btngeneralform").show();
                    $("#btnmappingsave").hide();
                    $("#btngeneralform").html("Save").removeAttr("disabled");
		    $("#addeditgeneral").modal("show");
		    command = $("#command").val();
		    oid = $(this).data('oid');
		    dataFor = $(this).data('for');
		    $("#generaldatafor").val(dataFor);
		    $("#oid").val(oid);

		    //SET TITLE MODAL
		    if(oid != 0){
			if(dataFor == 'showfilemaingroupform'){
			    $(".addeditgeneral-title").html("Edit File Main Group");
			}

		    }else{
			if(dataFor == 'showfilemaingroupform'){
			    $(".addeditgeneral-title").html("Add File Main Group");
			}
		    }


		    dataSend = {
			"FRM_FIELD_DATA_FOR"	: dataFor,
			"FRM_FIELD_OID"		 : oid,
			"command"		 : command
		    }
		    onDone = function(data){
			
		    };
		    onSuccess = function(data){
			
		    };
		    getDataFunction(onDone, onSuccess, approot, command, dataSend, "AjaxFileMainGroup", ".addeditgeneral-body", false, "json");
		});
	    };
	    
	    //DELETE GENERAL
	    deletegeneral = function(elementId, checkboxelement){

		$(elementId).click(function(){
		    dataFor = $(this).data("for");
		    var checkBoxes = (checkboxelement);
		    var vals = "";
		    $(checkboxelement).each(function(i){

			if($(this).is(":checked")){
			    if(vals.length == 0){
				vals += ""+$(this).val();
			    }else{
				vals += ","+$(this).val();
			    }
			}
		    });
		    var confirmText = "Are you sure want to delete these data?";
		    if(vals.length == 0){
			alert("Please select the data");
		    }else{
			command = <%= Command.DELETEALL %>;
			var currentHtml = $(this).html();
			$(this).html("Deleting...").attr({"disabled":true});
			if(confirm(confirmText)){
			    dataSend = {
				"FRM_FIELD_DATA_FOR"	    : dataFor,
				"FRM_FIELD_OID_DELETE"	    : vals,
				"command"		    : command
			    };
			    onSuccess = function(data){

			    };
			    if(dataFor == "filemaingroup"){
				onDone = function(data){
				    dataTablesOptions("#fileMainGroup", "tableAreaTypeElement", "AjaxFileMainGroup", "listfilemaingroup", callBackDataTables);
				};
			    }
			    getDataFunction(onDone, onSuccess, approot, command, dataSend, "AjaxFileMainGroup", null, true, "json");
			    $(this).removeAttr("disabled").html(currentHtml);
			}else{
			    $(this).removeAttr("disabled").html(currentHtml);
			}
		    }

		});
	    };
	    
	    //FUNCTION FOR DATA TABLES
	    callBackDataTables = function(){
		addeditgeneral(".btneditgeneral");                
		iCheckBox();
	    }

	    //FORM HANDLER
	    areaUsageForm = function(){
		validateOptions("#<%= FrmDataUploadGroup.fieldNames[FrmDataUploadGroup.FRM_FIELD_SYSTEM_NAME] %>", 'text', 'has-error', 1, null);
		validateOptions("#<%= FrmDataUploadGroup.fieldNames[FrmDataUploadGroup.FRM_FIELD_MODUL] %>", 'text', 'has-error', 1, null);
                validateOptions("#<%= FrmDataUploadGroup.fieldNames[FrmDataUploadGroup.FRM_FIELD_DATA_GROUP_TIPE] %>", 'text', 'has-error', 1, null);
                validateOptions("#<%= FrmDataUploadGroup.fieldNames[FrmDataUploadGroup.FRM_FIELD_DATA_GROUP_TITLE] %>", 'text', 'has-error', 1, null);
                validateOptions("#<%= FrmDataUploadGroup.fieldNames[FrmDataUploadGroup.FRM_FIELD_DATA_GROUP_DESC] %>", 'text', 'has-error', 1, null);
	    }

	    //VALIDATE FORM
	    function validateOptions (elementId, checkType, classError, minLength, matchValue){

		/* OPTIONS
		 * minLength    : INT VALUE,
		 * matchValue   : STRING OR INT VALUE,
		 * classError   : STRING VALUE,
		 * checkType    : STRING VALUE ('text' for default, 'email' for email check
		 */

		$(elementId).validate({
		    minLength   : minLength,
		    matchValue  : matchValue,
		    classError  : classError,
		    checkType   : checkType
		});
	    }

	    //iCheck
	    iCheckBox = function(){
		$("input[type='checkbox'], input[type='radio']").iCheck({
		    checkboxClass: 'icheckbox_minimal-blue',
                    radioClass: 'iradio_minimal-blue'
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


	    //DATA TABLES SETTING
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
		    "sAjaxSource" : "<%= approot %>/"+servletName+"?command=<%= Command.LIST%>&FRM_FIELD_DATA_FILTER="+datafilter+"&FRM_FIELD_DATA_FOR="+dataFor+"&privUpdate="+privUpdate+"&FRM_FLD_APP_ROOT=<%=approot%>",
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
	    
	    function runDataTables(){
		dataTablesOptions("#fileMainGroup", "tableAreaTypeElement", "AjaxFileMainGroup", "listfilemaingroup", callBackDataTables);
	    }
            

            //////////////////////////////////////
	    
	    modalSetting("#addeditgeneral", "static", false, false);
	    addeditgeneral(".btnaddgeneral");
	    deletegeneral(".btndeleteareausage", ".<%= FrmDataUploadGroup.fieldNames[FrmDataUploadGroup.FRM_FIELD_DATA_GROUP_ID]%>");
		    
	    runDataTables();           
            
	    //FORM SUBMIT
	    $("form#generalform").submit(function(){
		var currentBtnHtml = $("#btngeneralform").html();
		$("#btngeneralform").html("Saving...").attr({"disabled":"true"});
		var generaldatafor = $("#generaldatafor").val();
		if(generaldatafor == "showfilemaingroupform"){
		    areaUsageForm();
		    onDone = function(data){
                        //alert("test");
			runDataTables();
		    };
		}


		if($(this).find(".has-error").length == 0){
		    onSuccess = function(data){
			$("#btngeneralform").removeAttr("disabled").html(currentBtnHtml);
			$("#addeditgeneral").modal("hide");
		    };

		    dataSend = $(this).serialize();
		    getDataFunction(onDone, onSuccess, approot, command, dataSend, "AjaxFileMainGroup", null, true, "json");
		}else{
		    $("#btngeneralform").removeAttr("disabled").html(currentBtnHtml);
		}

		return false;
	    });
	});
      </script>
      <div id="addeditgeneral" class="modal fade nonprint" tabindex="-1">
	<div class="modal-dialog nonprint">
	    <div class="modal-content">
		<div class="modal-header">
		    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		    <h4 class="addeditgeneral-title"></h4>
		</div>
		<form id="generalform">
		    <input type="hidden" name="FRM_FIELD_DATA_FOR" id="generaldatafor">
		    <input type="hidden" name="command" value="<%= Command.SAVE %>">
		    <input type="hidden" name="FRM_FIELD_OID" id="oid">
		    <div class="modal-body ">
			<div class="row">
			    <div class="col-md-12">
				<div class="box-body addeditgeneral-body">

				</div>
			    </div>
			</div>
		    </div>
		    <div class="modal-footer">
			<button type="submit" class="btn btn-primary" id="btngeneralform"><i class="fa fa-check"></i> Save</button>
                        <button data-for="savemapping" type="button" class="btn btn-primary" id="btnmappingsave"><i class="fa fa-check"></i> Save</button>
			<button  type="button" data-dismiss="modal" class="btn btn-danger"><i class="fa fa-ban"></i> Close</button>
		    </div>
		</form>
	    </div>
	</div>
    </div>
    
                    
    </div><!-- ./wrapper -->
  </body>
</html>
