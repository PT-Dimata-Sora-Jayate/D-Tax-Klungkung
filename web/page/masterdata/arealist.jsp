<%@page import="com.dimata.balismartisland.form.FrmAreaUsage"%>
<%@page import="com.dimata.balismartisland.form.FrmChanel"%>
<%@page import="com.dimata.balismartisland.entity.Chanel"%>
<%@page import="com.dimata.balismartisland.entity.PstChanel"%>
<%@page import="com.dimata.saras.entity.studentinformation.PstAcademicHistoryDetail"%>
<%@page import="com.dimata.system.form.dataupload.FrmDataUploadDetail"%>
<%@page import="java.util.Vector"%>
<%@page import="com.dimata.gui.jsp.ControlCombo"%>
<%@page import="com.dimata.system.form.dataupload.FrmDataUploadMain"%>
<%@page import="com.dimata.balismartisland.form.FrmAreaList"%>
<%@page import="com.dimata.balismartisland.form.FrmAreaType"%>
<%@page import="com.dimata.util.Command"%>
<!DOCTYPE html>
<%@include file="../../main/javainit.jsp" %>
<html>
  <head>
    <meta charset="UTF-8">
    <title>D-SMARTCITY | CONTENT</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <%@include file="../../template-component/css-component.jsp" %>
    <style type="text/css">
	.modal-lg{
	    max-width: 1200px;
	    width: 100%;
	}
    </style>
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
            <small>Content</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Master Data</a></li>
            <li class="active">Content</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
          <!-- Small boxes (Stat box) -->
          <div class="row">
            <div class="col-md-12">
		<div class='box box-primary'>
		    <div class='box-header'>
			Content
		    </div>
		    <div class="box-body">
			<div class='row'>
			<%
			    Vector chanelList = PstChanel.listAll();
			    Vector chanelKey = new Vector(1,1);
			    Vector chanelVal = new Vector(1,1);
			    
			    if(chanelList.size() > 0){
				for(int i = 0; i < chanelList.size(); i++){
				    Chanel chanel = (Chanel) chanelList.get(i);
				    chanelKey.add(""+chanel.getOID());
				    chanelVal.add(""+chanel.getChanelName());
				}
			    }
			%>
			<div class='col-md-3'><%= ControlCombo.draw("chanelcombo", "-- Select Chanel", "", chanelKey, chanelVal, "data-for='getgroup' data-append-to='#groupcombo'","form-control controlcombo2") %></div>
			<div class='col-md-3'><select id='groupcombo' class='form-control controlcombo2' data-append-to='#categorycombo' data-for='getcategory'><option value=''>-- Select Group --</option></select></div>
			<div class='col-md-3'><select id='categorycombo' class='form-control controlcombo2'><option value=''>-- Select Category --</option></select></div>
			</div>
			<div class='row'>
			    <div class='col-md-12'>&nbsp;</div>
			</div>
			</div>
			<div class='row'>
			    <div class='col-md-12'>
			<div id="areaListElement" style="overflow-x:auto;">
			    <table class="table table-bordered table-striped">
				<thead>
				    <tr>
					<th>No.</th>
					<th>Content Name</th>
					<th>Content Address</th>
					<th>Long, Lat
					<th>Group / Desc</th>
					<th>Category</th>
					<th>Detail</th>
					<th style="width:25%">Action</th>
				    </tr>
				</thead>
			    </table>
			</div>
			    </div>
		    </div>
		    <div class='box-footer'>
			<button class="btn btn-primary btnshowmap" data-oid="0" data-for="showmap">
			    <i class="fa fa-plus"></i> Add Content
			</button>
			<button class="btn btn-danger btndeleteareatype" data-for="arealist">
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
      <script src='https://maps.googleapis.com/maps/api/js?key=AIzaSyCVHaChwTJ1045ZRf57k4waY28m7M7mXjQ&libraries=drawing' async defer></script>

      <script type="text/javascript">
	$(document).ready(function(){
	    //SET ACTIVE MENU
	    var activeMenu = function(parentId, childId){
		$(parentId).addClass("active").find(".treeview-menu").slideDown();
		$(childId).addClass("active");
	    }

	    activeMenu("#masterdata", "#arealist");
	    
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
	    var areaListForm = null;
	    var deletegeneral = null;
	    var videoStream = null;
	    
	   
	    function takePicture(){
		var video = document.getElementById("video");

		// Test browser support
		window.navigator = window.navigator || {};
		navigator.getUserMedia = navigator.getUserMedia       ||
					 navigator.webkitGetUserMedia ||
					 navigator.mozGetUserMedia    ||
					 null;

		if (navigator.getUserMedia === null) {
		  document.getElementById('gum-unsupported').classList.remove('hidden');
		  document.getElementById('cancelcapture').setAttribute('disabled', 'disabled');
		  document.getElementById('takepicture').setAttribute('disabled', 'disabled');
		} else {
		  // Opera <= 12.16 accepts the direct stream.
		  // More on this here: http://dev.opera.com/articles/view/playing-with-html5-video-and-getusermedia-support/
		  var createSrc = window.URL ? window.URL.createObjectURL : function(stream) {return stream;};

		  // Opera <= 12.16 support video only.
		  var audioContext = window.AudioContext       ||
				     window.webkitAudioContext ||
				     null;
		  if (audioContext === null) {
		    document.getElementById('gum-partially-supported').classList.remove('hidden');
		  }

		  $("#takepicture").click(function() {
		    $("#capturearea").modal("show");
		    navigator.getUserMedia({
		      video: true,
		      audio: false
		    },
		    function(stream) {
		      videoStream = stream;
		      // Stream the data
		      video.src = createSrc(stream);
		      video.play();
		    },
		    function(error) {
		      console.log("Video capture error: ", error.code);
		    });
		  });
		  $(".cancelcapture").click(function(){
		    $("#videoarea").hide();
		    $("#takepicture").fadeIn("medium");
		    $("#capturearea").modal("hide");
		    // Pause the video
		    video.pause();
		    // Stop the stream
		    videoStream.stop();
		  });
		  
		}
	   };
	    function snapPicture(){
		$("#capture").click(function() {
		   var currentHtml = $(this).html();
		   $(this).html("Please Wait..").attr({"disabled" : "true"});
		   var getHeight = $("#video").height();
		   var getWidth = $("#video").width();
		   var video = document.getElementById("video");
		   var c1Canvas = document.getElementById("picturecaptured");
		   var ctx1= c1Canvas.getContext('2d');
		   c1Canvas.width = getWidth;
		   c1Canvas.height = getHeight;
		   ctx1.drawImage(video, 0, 0, getWidth, getHeight);
		   $("#imgString").val(c1Canvas.toDataURL());
		   $("#imgdisplay").attr({"src" : c1Canvas.toDataURL()}).fadeIn("medium");
		   $(this).removeAttr("disabled").html(currentHtml);
		   $("#capturearea").modal("hide");
		});
	   };
	   
	   var getBase64 = function(files) {
		var file = files[0];
		if (files && file) {
		    var reader = new FileReader();

		    reader.onload = function(readerEvt) {
			var binaryString = readerEvt.target.result;
			$("#imgdisplay").attr({"src" : binaryString}).fadeIn("medium");
			$("#imgString").val(binaryString);	
		    };

		    reader.readAsDataURL(file);
		}
	    };

	    
	    //MODAL SETTING
	    var modalSetting = function(elementId, backdrop, keyboard, show){
		$(elementId).modal({
		    backdrop	: backdrop,
		    keyboard	: keyboard,
		    show	: show
		});
	    };
	    
	    var getDataFunction = function(onDone, onSuccess, approot, command, dataSend, servletName, dataAppendTo, notification, dataType, contentType, processData){
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
		   ajaxDataType	: dataType,
		   ajaxContentType : contentType,
		   ajaxProcessData : processData
		});
	    }
	    
	    function uploadFile(){
		$(".uploadfile").click(function(){
		    $("#uploadphoto").modal("show");
		    var oid = $(this).data("oid");
		    var dataFor = $(this).data("for");
		    onDone = function(data){
			
		    };
		    onSuccess = function(data){
			
		    };
		    dataSend = {
			"FRM_FIELD_DATA_FOR"	: dataFor,
			"FRM_FIELD_OID"		 : oid,
			"command"		 : command
		    }
		    getDataFunction(onDone, onSuccess, approot, command, dataSend, "AjaxAreaList", "#<%= FrmDataUploadDetail.fieldNames[FrmDataUploadDetail.FRM_FIELD_DATA_MAIN_ID] %>", false, "json");
		});
	    }
	    //SHOW ADD OR EDIT FORM
	    addeditgeneral = function(elementId){
		$(elementId).click(function(){
		    command = $("#command").val();
		    oid = $(this).data('oid');
		    dataFor = $(this).data('for');
		    $("#addeditgeneral").modal("show");
		    
		    
		    $("#generaldatafor").val(dataFor);
		    $("#oid").val(oid);
		    $("#addeditgeneral").find(".modal-dialog").removeClass("modal-lg");
		    //SET TITLE MODAL
		    if(oid != 0){
			if(dataFor == 'showarealistform'){
			    $(".addeditgeneral-title").html("Edit Content");
			}else if(dataFor == 'showmap'){
			    $(".addeditgeneral-title").html("Edit Content");
			    $("#addeditgeneral").find(".modal-dialog").addClass("modal-lg");
			    
			    onDone = function(data){
				
				takePicture();
				snapPicture();
				controlCombo();
				$("#uploadimgbtn").click(function(){
				    $("#uploadimg").click();
				});
				$("#uploadimg").change(function(){
				    if (window.File && window.FileReader && window.FileList && window.Blob) {
					
					getBase64(this.files);
				    }else{
					alert('The File APIs are not fully supported in this browser.');
				    }
				});
				datePicker(".datepicker", "yyyy-mm-dd");
				$("#<%= FrmAreaList.fieldNames[FrmAreaList.FRM_FIELD_AREA_LIST_USAGE_ID] %>").change(function(){
				   var oid = $(this).val();
				   var oidAreaList = $("#oid").val();
				   var dataSend = {
					"FRM_FIELD_DATA_FOR"	: "showmapcontent",
					"FRM_FIELD_OID"		 : oid,
					"command"		 : command,
					"FRM_FIELD_OID_AREA_LIST" : oidAreaList
				    }

				    var onDoneS = function(data){
					if(data.FRM_SHOW_FIELD == 1){
					    $("#formbentuk").show();
					}else{
					    $("#formbentuk").hide();
					}
				    };

				    var onSuccessS = function(data){
                                        
				    }
                                    //alert("test");
				    getDataFunction(onDoneS, onSuccessS, approot, command, dataSend, "AjaxAreaList", "#mapcontent", false, "json");
				    
				    var dataSend = {
					"FRM_FIELD_DATA_FOR"	: "getareatype",
					"FRM_FIELD_OID"		 : oid,
					"command"		 : command,
					"FRM_FIELD_OID_AREA_LIST" : oidAreaList
				    }

				    var onDoneS = function(data){
                                        //alert(data.FRM_FIELD_HTML);
                                        $("#FRM_FIELD_AREA_LIST_TYPE_ID_GET").val(data.FRM_FIELD_HTML);
                                        var dataSend = {
                                                "FRM_FIELD_DATA_FOR"	: "showmapareausage",
                                                "FRM_FIELD_OID"		 : oid,
                                                "command"		 : command,
                                                "FRM_FIELD_OID_AREA_LIST" : oidAreaList
                                            }

                                            var onDone = function(data){

                                            };

                                            var onSuccess = function(data){

                                            }

                                            getDataFunction(onDone, onSuccess, approot, command, dataSend, "AjaxAreaList", "#mapareausage", false, "json");
				    };

				    var onSuccessS = function(data){
                                        
				    }
                                    //alert("test");
				    getDataFunction(onDoneS, onSuccessS, approot, command, dataSend, "AjaxAreaList", "", false, "json");
				});
				
				$("#<%= FrmAreaList.fieldNames[FrmAreaList.FRM_FIELD_BENTUK] %>").change(function(){
				   var oid = $("#<%= FrmAreaList.fieldNames[FrmAreaList.FRM_FIELD_AREA_LIST_USAGE_ID] %>").val();
				   var oidAreaList = $("#oid").val();
				   var oidBentuk = $(this).val();
				   var dataSend = {
					"FRM_FIELD_DATA_FOR"	: "showmapcontent",
					"FRM_FIELD_OID"		 : oid,
					"command"		 : command,
					"FRM_FIELD_OID_AREA_LIST" : oidAreaList,
					"FRM_FIELD_BENTUK" : oidBentuk,
				    }

				    var onDoneS = function(data){
				    };

				    var onSuccessS = function(data){
                                        
				    }
                                    //alert("test");
				    getDataFunction(onDoneS, onSuccessS, approot, command, dataSend, "AjaxAreaList", "#mapcontent", false, "json");
				});
			    };
			}else if(dataFor == 'uploadmapfile'){
			    $(".addeditgeneral-title").html("Upload File");
			    $("#addeditgeneral").find(".modal-dialog").addClass("modal-lg");
			    onDone = function(data){
				dataTablesOptions("#fileListElement", "tableFileListElement", "AjaxAreaList", "listfile", callBackDataTables);			
			    }
			}else if(dataFor == 'takepicture'){
			    $(".addeditgeneral-title").html("Upload File");
			    $("#addeditgeneral").find(".modal-dialog").addClass("modal-lg");
			    onDone = function(data){
				takePicture();
				snapPicture();
			    }
			}

		    }else{
			if(dataFor == 'showarealistform'){
			    $(".addeditgeneral-title").html("Add Content");
			}else if(dataFor == 'showmap'){
			    $(".addeditgeneral-title").html("Add Content");
			    $("#addeditgeneral").find(".modal-dialog").addClass("modal-lg");
			    
			    onDone = function(data){
				takePicture();
				snapPicture();
				controlCombo();
				
				$("#uploadimgbtn").click(function(){
				    $("#uploadimg").click();
				});
				$("#uploadimg").change(function(){
				    if (window.File && window.FileReader && window.FileList && window.Blob) {
					
					getBase64(this.files);
				    }else{
					alert('The File APIs are not fully supported in this browser.');
				    }
				});
				
				datePicker(".datepicker", "yyyy-mm-dd");
				$("#<%= FrmAreaList.fieldNames[FrmAreaList.FRM_FIELD_AREA_LIST_USAGE_ID] %>").change(function(){
				   var oid = $(this).val();
				   var oidAreaList = $("#oid").val();
				   var dataSend = {
					"FRM_FIELD_DATA_FOR"	: "showmapcontent",
					"FRM_FIELD_OID"		 : oid,
					"command"		 : command,
					"FRM_FIELD_OID_AREA_LIST" : oidAreaList
				    }

				    var onDoneS = function(data){
					if(data.FRM_SHOW_FIELD == 1){
					    $("#formbentuk").show();
					}else{
					    $("#formbentuk").hide();
					}
				    };

				    var onSuccessS = function(data){
                                        
				    }
                                    //alert("test");
				    getDataFunction(onDoneS, onSuccessS, approot, command, dataSend, "AjaxAreaList", "#mapcontent", false, "json");
				    
				    var dataSend = {
					"FRM_FIELD_DATA_FOR"	: "getareatype",
					"FRM_FIELD_OID"		 : oid,
					"command"		 : command,
					"FRM_FIELD_OID_AREA_LIST" : oidAreaList
				    }

				    var onDoneS = function(data){
                                        //alert(data.FRM_FIELD_HTML);
                                        $("#FRM_FIELD_AREA_LIST_TYPE_ID_GET").val(data.FRM_FIELD_HTML);
                                        var dataSend = {
                                                "FRM_FIELD_DATA_FOR"	: "showmapareausage",
                                                "FRM_FIELD_OID"		 : oid,
                                                "command"		 : command,
                                                "FRM_FIELD_OID_AREA_LIST" : oidAreaList
                                            }

                                            var onDone = function(data){

                                            };

                                            var onSuccess = function(data){

                                            }

                                            getDataFunction(onDone, onSuccess, approot, command, dataSend, "AjaxAreaList", "#mapareausage", false, "json");
				    };

				    var onSuccessS = function(data){
                                        
				    }
                                    //alert("test");
				    getDataFunction(onDoneS, onSuccessS, approot, command, dataSend, "AjaxAreaList", "", false, "json");
				});
				
				$("#<%= FrmAreaList.fieldNames[FrmAreaList.FRM_FIELD_BENTUK] %>").change(function(){
				   var oid = $("#<%= FrmAreaList.fieldNames[FrmAreaList.FRM_FIELD_AREA_LIST_USAGE_ID] %>").val();
				   var oidAreaList = $("#oid").val();
				   var oidBentuk = $(this).val();
				   var dataSend = {
					"FRM_FIELD_DATA_FOR"	: "showmapcontent",
					"FRM_FIELD_OID"		 : oid,
					"command"		 : command,
					"FRM_FIELD_OID_AREA_LIST" : oidAreaList,
					"FRM_FIELD_BENTUK" : oidBentuk
				    }

				    var onDoneS = function(data){
				    };

				    var onSuccessS = function(data){
                                        
				    }
                                    //alert("test");
				    getDataFunction(onDoneS, onSuccessS, approot, command, dataSend, "AjaxAreaList", "#mapcontent", false, "json");
				});
			    };
			}else if(dataFor == 'uploadmapfile'){
			    $(".addeditgeneral-title").html("Upload File");
			    $("#addeditgeneral").find(".modal-dialog").addClass("modal-lg");
			    onDone = function(data){
				dataTablesOptions("#fileListElement", "tableFileListElement", "AjaxAreaList", "listfile", callBackDataTables);			
			    }
			}else if(dataFor == 'takepicture'){
			    $(".addeditgeneral-title").html("Upload File");
			    $("#addeditgeneral").find(".modal-dialog").addClass("modal-lg");
			    onDone = function(data){
				takePicture();
				snapPicture();
			    }
			}
		    }


		    dataSend = {
			"FRM_FIELD_DATA_FOR"	: dataFor,
			"FRM_FIELD_OID"		 : oid,
			"command"		 : command,
			"FRM_FIELD_APPROOT" : approot
		    }
		    onSuccess = function(data){
			
		    };
		    getDataFunction(onDone, onSuccess, approot, command, dataSend, "AjaxAreaList", ".addeditgeneral-body", false, "json");
		});
	    };
	    
	    function uploadTrigger(){
                $("#uploadtrigger").click(function(){
                    $("#FRM_ICON").trigger('click');
                });
                
                $("#FRM_ICON").change(function(){
                    var icon = $("#FRM_ICON").val();
                    $("#tempname").val(icon);
                });
                
                $("#tempname").click(function(){
                    $("#FRM_ICON").trigger('click');
                });
            }
	    
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
			    if(dataFor == "arealist"){
				onDone = function(data){
				    runDataTables();
				};
			    }
			    getDataFunction(onDone, onSuccess, approot, command, dataSend, "AjaxAreaList", null, true, "json");
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
		addeditgeneral(".btnshowmap");
		iCheckBox();
		uploadFile();
	    }

	    //FORM HANDLER
	    areaListForm = function(){
		validateOptions("#<%= FrmAreaList.fieldNames[FrmAreaList.FRM_FIELD_STATUS] %>", 'text', 'has-error', 1, null);
		validateOptions("#<%= FrmAreaList.fieldNames[FrmAreaList.FRM_FIELD_BENTUK] %>", 'text', 'has-error', 1, null);
		validateOptions("#<%= FrmAreaList.fieldNames[FrmAreaList.FRM_FIELD_AREA_LIST_USAGE_ID] %>", 'text', 'has-error', 1, null);
		validateOptions("#<%= FrmAreaList.fieldNames[FrmAreaList.FRM_FIELD_AREA_LIST_TYPE_ID] %>", 'text', 'has-error', 1, null);
		validateOptions("#<%= FrmAreaList.fieldNames[FrmAreaList.FRM_FIELD_AREA_LIST_ADDRESS] %>", 'text', 'has-error', 1, null);
		validateOptions("#<%= FrmAreaList.fieldNames[FrmAreaList.FRM_FIELD_AREA_LIST_NAME] %>", 'text', 'has-error', 1, null);
		validateOptions("#dataValue", 'text', 'has-error', 1, null);
		alert($("#<%= FrmAreaList.fieldNames[FrmAreaList.FRM_FIELD_STATUS] %>").val());
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

		
	    }


	    //DATA TABLES SETTING
	    function dataTablesOptions(elementIdParent, elementId, servletName, dataFor, callBackDataTables){
		var datafilter = $("#datafilter").val();
		var privUpdate = $("#privUpdate").val();
		var chanel = $("#chanelcombo").val();
		var group = $("#groupcombo").val();
		var category = $("#categorycombo").val();
		var accountId = "<%= accountId %>"
		var oid = $("#oid").val();
		$(elementIdParent).find('table').addClass('table-bordered table-striped table-hover').attr({'id':elementId});
		$("#"+elementId).dataTable({"bDestroy": true,
		    "iDisplayLength": 10,
		    "bProcessing" : true,
		    "oLanguage" : {
			"sProcessing" : "<div class='progress progress-striped active'><div class='progress-bar progress-bar-primary' style='width: 100%'><b>Please Wait...</b></div></div>"
		    },
		    "bServerSide" : true,
		    "sAjaxSource" : "<%= approot %>/"+servletName+"?FRM_FIELD_ACCOUNT_ID="+accountId+"&command=<%= Command.LIST%>&FRM_FIELD_DATA_FILTER="+datafilter+"&FRM_FIELD_DATA_FOR="+dataFor+"&privUpdate="+privUpdate+"&FRM_FIELD_OID="+oid+"&<%= FrmChanel.fieldNames[FrmChanel.FRM_FIELD_CHANEL_ID] %>="+chanel+"&<%= FrmAreaType.fieldNames[FrmAreaType.FRM_FIELD_AREA_TYPE_ID] %>="+group+"&<%= FrmAreaUsage.fieldNames[FrmAreaUsage.FRM_FIELD_AREA_USAGE_ID] %>="+category,
		    aoColumnDefs: [
			{
			   bSortable: false,
			   aTargets: [ -1, -2 ]
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
		dataTablesOptions("#areaListElement", "tableAreaListElement", "AjaxAreaList", "listarealist", callBackDataTables);
	    }
	    
	    function controlCombo(){
		$(".controlcombo").change(function(){
		    var getOid = $(this).val();
		    var dataFor = $(this).data("for");
		    var replaceTo = $(this).data("replaceTo");
		    onSuccess = function(data){

		    };
		    onDone = function(data){
		    };

		    dataSend = {
			"FRM_FIELD_DATA_FOR": dataFor,
			"FRM_FIELD_OID"     : getOid,
			"command"	    : command,
			"FRM_FIELD_APPROOT" : approot
		    }

		    getDataFunction(onDone, onSuccess, approot, command, dataSend, "AjaxAreaList", replaceTo, false, "json");
		});
	    }
	    
	    function controlCombo2(){
		$(".controlcombo2").change(function(){
		    var getOid = $(this).val();
		    var dataFor = $(this).data("for");
		    var replaceTo = $(this).data("appendTo");
		    onSuccess = function(data){

		    };
		    onDone = function(data){
			runDataTables();
		    };

		    dataSend = {
			"FRM_FIELD_DATA_FOR": dataFor,
			"FRM_FIELD_OID"     : getOid,
			"command"	    : command,
			"FRM_FIELD_APPROOT" : approot
		    }

		    getDataFunction(onDone, onSuccess, approot, command, dataSend, "AjaxAreaList", replaceTo, false, "json");
		});
	    }
	    controlCombo2();
	    function datePicker(contentId, formatDate){
		$(contentId).datepicker({
		    format : formatDate
		});
		$(contentId).on('changeDate', function(ev){
		    $(this).datepicker('hide');
		});
	    }
	    
	    modalSetting("#uploadphoto", "static", false, false);
	    modalSetting("#addeditgeneral", "static", false, false);
	    modalSetting("#capturearea", "static", false, false);
	    addeditgeneral(".btnaddgeneral");
	    
	    deletegeneral(".btndeleteareatype", ".<%= FrmAreaList.fieldNames[FrmAreaList.FRM_FIELD_AREA_LIST_ID]%>");
		    
	    runDataTables();

	    //FORM SUBMIT
	    $("form#generalform").submit(function(){
		
		if(videoStream !== null){
		    videoStream.stop();
		}
		var currentBtnHtml = $("#btngeneralform").html();
		$("#btngeneralform").html("Saving...").attr({"disabled":"true"});
		var generaldatafor = $("#generaldatafor").val();
		if(generaldatafor == "showmap"){
		    areaListForm();
		    onDone = function(data){
			runDataTables();
		    };
		}


		if($(this).find(".has-error").length == 0){
		    onSuccess = function(data){
			$("#btngeneralform").removeAttr("disabled").html(currentBtnHtml);
			$("#addeditgeneral").modal("hide");
		    };

		    dataSend = $(this).serialize();
		    getDataFunction(onDone, onSuccess, approot, command, dataSend, "AjaxAreaList", null, true, "json");
		}else{
		    $("#btngeneralform").removeAttr("disabled").html(currentBtnHtml);
		}

		return false;
	    });
	    
	    $("form#formupload").submit(function(){
		$("#btnuploadicon").html("Uploading...").attr({"disabled" : "true"});
		var mainId = $("#<%= FrmDataUploadDetail.fieldNames[FrmDataUploadDetail.FRM_FIELD_DATA_MAIN_ID] %>").val();
		var title = $("#<%= FrmDataUploadDetail.fieldNames[FrmDataUploadDetail.FRM_FIELD_DATA_DETAIL_TITLE] %>").val();
		var desc = $("#<%= FrmDataUploadDetail.fieldNames[FrmDataUploadDetail.FRM_FIELD_DATA_DETAIL_DESC] %>").val();
		var filename = $("#tempname").val();
		var dataSend = "<%= FrmDataUploadDetail.fieldNames[FrmDataUploadDetail.FRM_FIELD_DATA_MAIN_ID] %>="+mainId+"&<%= FrmDataUploadDetail.fieldNames[FrmDataUploadDetail.FRM_FIELD_DATA_DETAIL_TITLE] %>="+title+"&<%= FrmDataUploadDetail.fieldNames[FrmDataUploadDetail.FRM_FIELD_DATA_DETAIL_DESC] %>="+desc+"&<%= FrmDataUploadDetail.fieldNames[FrmDataUploadDetail.FRM_FIELD_FILENAME] %>="+filename; 
		$(this).attr({"action": "<%= approot %>/page/masterdata/uploadfile.jsp?"+dataSend});
		
	    });
	    uploadTrigger();
	    
	})
      </script>
      
      <div id="addeditgeneral" class="modal fade nonprint" tabindex="-1" style="overflow-y:auto;">
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
		    <input type="hidden" name="FRM_FIELD_APPROOT" value="<%= approot %>">
		    <input type="hidden" name="<%= FrmAreaList.fieldNames[FrmAreaList.FRM_FIELD_AREA_ACCOUNT_ID] %>" value="<%= accountId %>">
		    <div class="modal-body">
			<div class="row">
			    <div class="col-md-12">
				<div class="box-body addeditgeneral-body">

				</div>
			    </div>
			</div>
		    </div>
		    <div class="modal-footer">
			<button type="submit" class="btn btn-primary cancelcapture" id="btngeneralform"><i class="fa fa-check"></i> Save</button>
			<button type="button" data-dismiss="modal" class="btn btn-danger cancelcapture"><i class="fa fa-ban"></i> Close</button>
		    </div>
		</form>
	    </div>
	</div>
    </div>
    <div id="uploadphoto" class="modal fade nonprint" tabindex="-1">
	<div class="modal-dialog nonprint">
	    <div class="modal-content">
		<div class="modal-header">
		    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		    <h4 class="addeditgeneral-title">Upload Icon</h4>
		</div>
                <form method="POST" id="formupload" enctype="multipart/form-data">		    
		    <input type="hidden" name="command" value="<%= Command.POST %>">
                    <input style="width:0px; height:0px;"  type="file" name="FRM_ICON" id="FRM_ICON">
		   <input type="hidden" name="FRM_FIELD_OID" id="oiddata">
		    <div class="modal-body ">
			<div class="row">
			    <div class="col-md-12">
				<div class="box-body uploadicon-body">
                                    <div class="row">
                                        <div class="col-md-12">
					    <div class="form-group">
						<label>Sub Group</label>
						<%
						    Vector main_key = new Vector(1,1);
						    Vector main_val = new Vector(1,1);
						%>
						<%= ControlCombo.draw(FrmDataUploadDetail.fieldNames[FrmDataUploadDetail.FRM_FIELD_DATA_MAIN_ID], "-- Select --", "", main_key, main_val, "", "form-control") %>
					    </div>
					    <div class="form-group">
						<label>Title</label>
						<input type="text" name="<%= FrmDataUploadDetail.fieldNames[FrmDataUploadDetail.FRM_FIELD_DATA_DETAIL_TITLE] %>" id="<%= FrmDataUploadDetail.fieldNames[FrmDataUploadDetail.FRM_FIELD_DATA_DETAIL_TITLE] %>" class="form-control">
					    </div>
					    <div class="form-group">
						<label>Description</label>
						<textarea name="<%= FrmDataUploadDetail.fieldNames[FrmDataUploadDetail.FRM_FIELD_DATA_DETAIL_DESC] %>" id="<%= FrmDataUploadDetail.fieldNames[FrmDataUploadDetail.FRM_FIELD_DATA_DETAIL_DESC] %>" class="form-control"></textarea>
					    </div>
                                            <div class="form-group">
                                                <label>File Upload</label>
                                                <div class="input-group my-colorpicker2 colorpicker-element">
                                                    <input required id="tempname" class="form-control" type="text">
                                                    <div style="cursor: pointer" class="input-group-addon" id="uploadtrigger">
                                                        <i class="fa fa-file-image-o"></i>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>    
				</div>
			    </div>
			</div>
		    </div>
		    <div class="modal-footer">
			<button type="submit" class="btn btn-primary" id="btnuploadicon"><i class="fa fa-check"></i> Save</button>
			<button type="button" data-dismiss="modal" class="btn btn-danger"><i class="fa fa-ban"></i> Close</button>
		    </div>
		</form>
	    </div>
	</div>
    </div>
    
    <div id="capturearea" class="modal fade nonprint" tabindex="-1">
	<div class="modal-dialog nonprint">
	    <div class="modal-content" >
		 <div class="modal-body">
		     <div class="row">
			 <div class='col-md-12'>
			    <video id='video' autoplay='autoplay' style='width:100%;'></video>
			    <canvas id='picturecaptured' style="display:none;"></canvas>
			    <button class='btn btn-flat btn-primary form-control cancelcapture col-md-6' type='button' id='capture'>Capture</button>
			    <button class='btn btn-flat btn-danger form-control cancelcapture col-md-6' type='button'>Cancel</button>
			</div>
		     </div>
		 </div>
	    </div>
	</div>
    </div>
    </div><!-- ./wrapper -->
  </body>
</html>
