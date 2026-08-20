
<%@page import="com.dimata.balismartisland.form.FrmAreaList"%>
<%@page import="com.dimata.saras.form.studentinformation.FrmFamily"%>
<%@page import="com.dimata.balismartisland.form.FrmReport"%>
<%@page import="com.dimata.gui.jsp.ControlCombo"%>
<%@page import="com.dimata.balismartisland.entity.Chanel"%>
<%@page import="com.dimata.balismartisland.entity.PstChanel"%>
<%@page import="com.dimata.balismartisland.entity.ChanelType"%>
<%@page import="com.dimata.balismartisland.entity.PstChanelType"%>
<%@page import="com.dimata.system.form.dataupload.FrmDataUploadGroup"%>
<%@page import="com.dimata.util.Command"%>
<%@page import="com.dimata.balismartisland.entity.AreaList"%>
<%@page import="com.dimata.balismartisland.entity.AreaType"%>
<%@page import="com.dimata.balismartisland.entity.AreaUsage"%>
<%@page import="com.dimata.balismartisland.entity.PstAreaType"%>
<%@page import="com.dimata.balismartisland.entity.PstAreaUsage"%>
<%@page import="java.util.Vector"%>
<%@page import="com.dimata.balismartisland.form.FrmChanel"%>
<%@page import="com.dimata.balismartisland.entity.PstAreaList"%>
<%@page import="com.dimata.balismartisland.form.FrmAreaUsage"%>
<%@page import="com.dimata.balismartisland.form.FrmAreaType"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<!DOCTYPE html>
<%@include file="../../main/javainit.jsp" %>

<%
    String searchTerm = FRMQueryString.requestString(request, "typeSearch");
    long oidAreaType = FRMQueryString.requestLong(request, ""+FrmAreaType.fieldNames[FrmAreaType.FRM_FIELD_AREA_TYPE_ID]+"");
    long oidAreaUsage = FRMQueryString.requestLong(request, ""+FrmAreaUsage.fieldNames[FrmAreaUsage.FRM_FIELD_AREA_USAGE_ID]+""); 
    long oidChanel = FRMQueryString.requestLong(request, FrmChanel.fieldNames[FrmChanel.FRM_FIELD_CHANEL_ID]);
    long oidGroup = oidAreaType;
    long oidCategory = oidAreaUsage;
    long oidContent = FRMQueryString.requestLong(request, FrmAreaList.fieldNames[FrmAreaList.FRM_FIELD_AREA_LIST_ID]);
    
    String whereAreaList = ""
        + " ("+PstAreaList.fieldNames[PstAreaList.FLD_AREA_LIST_NAME]+" like '%"+searchTerm+"%'"
        + " OR "+PstAreaList.fieldNames[PstAreaList.FLD_AREA_LIST_ADDRESS]+" like '%"+searchTerm+"%')";
        
    if (oidAreaType!=0){
        whereAreaList +=""
        + " AND "+PstAreaList.fieldNames[PstAreaList.FLD_AREA_LIST_TYPE_ID]+"='"+oidAreaType+"'";
    }
        
    if (oidAreaUsage!=0){
       whereAreaList +=""
        + " AND "+PstAreaList.fieldNames[PstAreaList.FLD_AREA_LIST_USAGE_ID]+"='"+oidAreaUsage+"'"; 
    }
    
    //Vector listArea = PstAreaList.list(0, 0, whereAreaList, "");   
    Vector listAreaUsage = PstAreaUsage.list(0, 0, ""+PstAreaUsage.fieldNames[PstAreaUsage.FLD_AREA_TYPE_ID]+"='"+oidAreaType+"'", "");
    Vector listAreaType = PstAreaType.list(0,0,""+PstAreaType.fieldNames[PstAreaType.FLD_CHANEL_ID]+"='"+oidChanel+"'","");
    
    Vector areaUsageId = new Vector (1,1);
    Vector areaUsageName = new Vector (1,1);
    Vector areaTypeId = new Vector (1,1);
    Vector areaTypeName = new Vector(1,1);
    
    for (int k = 0;k<listAreaUsage.size();k++){
        AreaUsage areaUsage = (AreaUsage) listAreaUsage.get(k);      
        areaUsageId.add(""+areaUsage.getOID());
        areaUsageName.add(""+areaUsage.getAreaUsageName());
    }
    
    for (int h = 0;h<listAreaType.size();h++){
        AreaType areaType = (AreaType) listAreaType.get(h);      
        areaTypeId.add(""+areaType.getOID());
        areaTypeName.add(""+areaType.getAreaTypeName());
    }
    
    
%>

<html>
    <head>
    <meta charset="UTF-8">
    <title>Di-SMARTCITY</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <%@include file="../../template-component/css-component.jsp" %>
    <link href="../../styles/dist/css/custom-style.css" rel="stylesheet">
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCVHaChwTJ1045ZRf57k4waY28m7M7mXjQ&callback=setContent">
    </script>
    </head> 
    <body class="<%= skin %>">
	
	<input type="hidden" id="approot" value="<%= approot %>">
	<input type="hidden" id="command" value="<%= Command.NONE %>">
	<input type="hidden" id="groupId" value="<%= oidGroup %>">
	<input type="hidden" id="categoryId" value="<%= oidCategory %>">
        <nav class="navbar navbar-static-top" id="head-front" role="navigation">
            <div class="navbar-custom-menu">
                <form action="<%=approot%>/page/frontend/frontmap_new.jsp" method="POST">
                <div class="col-xs-4 pull-left" style="margin-top:8px; color: #fff;font-size: 16px;">
                    <strong>Di-SmartCity </strong>
                </div>
                <div class="col-xs-8 pull-right" style="margin-top:8px;">
		    <div class="col-xs-2 menuflexible">
			<select name="<%= FrmChanel.fieldNames[FrmChanel.FRM_FIELD_CHANEL_ID] %>" id="<%= FrmChanel.fieldNames[FrmChanel.FRM_FIELD_CHANEL_ID] %>" class="form-control controlcombo" data-for="showgroup" data-replace-to="#<%= FrmAreaType.fieldNames[FrmAreaType.FRM_FIELD_AREA_TYPE_ID] %>" style='height:30px;'>
			    <option value="">-- All --</option>
			    <%
				Vector listChanelType = PstChanelType.listAll();
				if(listChanelType.size() > 0){
				    for(int i = 0; i < listChanelType.size();i++){
					ChanelType chanelType = (ChanelType) listChanelType.get(i);
					%>
					<optgroup label="<%= chanelType.getName() %>">
					    <%
						Vector listChanel = PstChanel.list(0, 0, ""+PstChanel.fieldNames[PstChanel.FLD_CHANEL_TYPE_ID]+"='"+chanelType.getOID()+"'", "");
						if(listChanel.size() > 0){
						    for(int iChanel = 0; iChanel < listChanel.size(); iChanel++){
							Chanel chanel = (Chanel) listChanel.get(iChanel);
							String selected = "";
							if(chanel.getOID() == oidChanel){
							    selected = "selected";
							}
							%>
							<option value="<%= chanel.getOID() %>" <%= selected %>><%= chanel.getChanelName() %></option>
							<%
						    }
						}
					    %>
					</optgroup>
					<%
				    }
				}
			    %>
			</select>
		    </div>
                    <div class="col-xs-2 menuflexible">
                        <%= ControlCombo.draw(FrmAreaType.fieldNames[FrmAreaType.FRM_FIELD_AREA_TYPE_ID], "-- All --", ""+oidAreaType, areaTypeId, areaTypeName, "data-for='showcategory' data-replace-to='#"+FrmAreaUsage.fieldNames[FrmAreaUsage.FRM_FIELD_AREA_USAGE_ID]+"' style='height:30px;'", "form-control controlcombo") %>                                            
                    </div>
                    <div class="col-xs-2 menuflexible">
                        <%= ControlCombo.draw(FrmAreaUsage.fieldNames[FrmAreaUsage.FRM_FIELD_AREA_USAGE_ID], "-- All --", ""+oidAreaUsage, areaUsageId, areaUsageName, "style='height:30px;'", "form-control") %> 
                    </div>
                    <div class="col-xs-6 menuflexible">
                        <div class="input-group input-group-sm">
                         
                            <input placeholder="Search..." value="<%= searchTerm%>" class="form-control" name="typeSearch" type="text">
                            <span class="input-group-btn">
                              <button id="searchs" class="btn btn-info btn-flat" type="submit">Search</button>
                            </span>

                            <span class="input-group-btn" >
                              <button style="margin-left:4px;" id="login" class="btn btn-warning btn-flat" type="button">Login</button>
                            </span>
                        </div>
                        
                    </div>
                    
                </div>
                </form>
            </div>
            
        </nav>
        <div class="row body" style="margin-left: 0px; margin-right: 0px;">
	    <div class="col-xs-3" id="smallmap"></div>
            <div class="col-xs-9" id="map"></div>
        </div>
        <div id="foot-front">
            <strong>Copyright © 2016 <a href="http://dimata.com">PT. Dimata Sora Jayate.</a></strong>
            <div class="pull-right hidden-xs">
                <b>Version</b> 1.0
            </div>
        </div>
	
    <%@include file="../../template-component/plugins-component.jsp" %>
	<script type="text/javascript" src="../../styles/dist/js/markerclusterer.js"></script>
      <script type="text/javascript">
	$(document).ready(function(){
	    //SET ACTIVE MENU
	    var activeMenu = function(parentId, childId){
		$(parentId).addClass("active").find(".treeview-menu").slideDown();
		$(childId).addClass("active");
	    }

	    activeMenu("#home", "#home");
	    
	    
	    ///AJAX FUNCTION
	    var onDone = function(data){
		
	    };
	    var onSuccess = function(data){
		
	    };
	    var dataSend = {};
	    var approot = $("#approot").val();
	    var command = $("#command").val();
	    var markers = [];
	    var events = [];
	    var events2 = []
	    var addeditgeneral;
	    var addeditgeneral2;
	    var dataFor;
	    var oid;
	    var videoStream;
	    var akses = $("#akses").val();

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
	    
	    //MODAL SETTING
	    var modalSetting = function(elementId, backdrop, keyboard, show){
		$(elementId).modal({
		    backdrop	: backdrop,
		    keyboard	: keyboard,
		    show	: show
		});
	    };
	    
	    function placeMarker(location, marker, markerMoved, map, mapmoved, clickFunction, dragendFunction, contentString) {
		marker.setPosition(location);
		marker.setMap(map);
		marker.setAnimation(google.maps.Animation.BOUNCE);
		
		if(clickFunction != null){
		    google.maps.event.addListener(marker, 'click', clickFunction);
		}

		if(dragendFunction != null){

		    google.maps.event.addListener(marker, 'dragend', function(event){
			dragendFunction(event, mapmoved, markerMoved);
		    });
		}
		$("#position").html(""+location);
	    }
	    
	    function dragendFunction(event, map, marker){
		map.setCenter(event.latLng);
		marker.setPosition(event.latLng);
		marker.setMap(map);
		marker.setAnimation(google.maps.Animation.BOUNCE);
		$("#position").html(""+event.latLng);
	    }

	    function clickFunction(event){
		document.getElementById('templatlng').value = ""+event.latLng;
		document.getElementById('tempbutton2').click();
	    }
	    
	    addeditgeneral = function(elementId){
		$(elementId).click(function(){
		    $("#addeditgeneral").modal("show");
		    command = $("#command").val();
		    dataFor = $(this).data('for');
		    $("#oid").val(oid);

		    //SET TITLE MODAL
		    if(oid != 0){
			if(dataFor == 'showdetailinfo'){
			    $(".addeditgeneral-title").html("Detail Information");
			}

		    }else{
			if(dataFor == 'showareausageform'){
			    $(".addeditgeneral-title").html("Detail Information");
			}
		    }


		    dataSend = {
			"FRM_FIELD_DATA_FOR"	: dataFor,
			"FRM_FIELD_OID"		 : oid,
			"command"		 : command,
			"FRM_FIELD_AKSES_TYPE" : akses	
		    }
		    onDone = function(data){
			
		    };
		    onSuccess = function(data){
			
		    };
                    
		    getDataFunction(onDone, onSuccess, approot, command, dataSend, "AjaxShowAreaList", ".addeditgeneral-body", false, "json");
		});
	    };
	    addeditgeneral2 = function(elementId){
		$(elementId).click(function(){
		    oid = $(this).data("oid");
		    var position = $("#position").html();
		    $("#addeditgeneral").modal("show");
                    $("#savegeneral").show();
                    $("#btngeneralform").hide();
		    command = $("#command").val();
		    var templatlng = $("#templatlng").val();
		    $(".btnedited").hide();
		    $("#savegeneral").show();
		    dataFor = $(this).data('for');
		    $("#oid").val(oid);
		    
		    $("#generaldatafor").val("takepicture3");
		    //SET TITLE MODAL
		    if(oid != 0){
			if(dataFor == 'showdetailinfo'){
			    $(".addeditgeneral-title").html("Detail Information");
			}else if(dataFor == 'takepicture3'){
			    $(".addeditgeneral-title").html("Report Information");
			    $("#addeditgeneral").find(".modal-dialog").addClass("modal-lg");
			    onDone = function(data){
				takePicture();
				snapPicture();
				datePicker(".datepicker","yyyy-mm-dd");
				$("#<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_LOCATION] %>").val(position);
			    }
			}

		    }else{
			if(dataFor == 'showareausageform'){
			    $(".addeditgeneral-title").html("Detail Information");
			}else if(dataFor == 'takepicture3'){
			
			    $(".addeditgeneral-title").html("Report Information");
			    $("#addeditgeneral").find(".modal-dialog").addClass("modal-lg");
			    onDone = function(data){
				takePicture();
				snapPicture();
				datePicker(".datepicker","yyyy-mm-dd");
				$("#<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_LOCATION] %>").val(position);
			    };
			}
		    }


		    dataSend = {
			"FRM_FIELD_DATA_FOR"	: "takepicture3",
			"FRM_FIELD_OID"		 : oid,
			"command"		 : command,
			"<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_CHANEL_ID] %>" : "<%= oidChanel %>",
			"<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_GROUP_ID] %>" : "<%= oidGroup %>",
			"<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_CATEGORY_ID] %>" : "<%= oidCategory %>",
			"<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_CONTENT_ID] %>" : "<%= oidContent %>",
			"<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_LOCATION] %>" : ""+templatlng,
			"FRM_FIELD_AKSES_TYPE" : akses
		    }
		    
		    onSuccess = function(data){
			
		    };
                    
		    getDataFunction(onDone, onSuccess, approot, command, dataSend, "AjaxAreaList", ".addeditgeneral-body", false, "json");
		});
	    };
	    addeditgeneral3 = function(elementId){
		$(elementId).click(function(){
		    oid = $("#oidSelected").val();
		    var addLinked = $(this).data("addLinked");
		    var followUp = $(this).data("followUp");
		    var position = $("#position").html();
		    $("#addeditgeneral").modal("show");
                    $("#savegeneral").show();
                    $("#btngeneralform").hide();
		    command = $("#command").val();
		    var templatlng = $("#templatlng").val();
		    
		    dataFor = $(this).data('for');
		    $("#oid").val(oid);
		    $(".btnedited").hide();
		    $("#savegeneral").show();
		    $("#generaldatafor").val(dataFor);
		    //SET TITLE MODAL
		    if(oid != 0){
			if(dataFor == 'showdetailinfo'){
			    $(".addeditgeneral-title").html("Detail Information");
			}else if(dataFor == 'inforeport'){
			    $(".addeditgeneral-title").html("Report Information");
			    $("#addeditgeneral").find(".modal-dialog").addClass("modal-lg");
			    onDone = function(data){
			    }
			    $("#addlinked").show().data("addLinked", oid);
			    $("#followup").hide().data("followUp", oid);
			    $("#editreport").hide().data("oid", oid);;
			    $("#savegeneral").hide();
			}

		    }else{
			if(dataFor == 'showareausageform'){
			    $(".addeditgeneral-title").html("Detail Information");
			}else if(dataFor == 'inforeport'){
			
			    $(".addeditgeneral-title").html("Report Information");
			    $("#addeditgeneral").find(".modal-dialog").addClass("modal-lg");
			    onDone = function(data){
			    };
			}
		    }


		    dataSend = {
			"FRM_FIELD_DATA_FOR"	: dataFor,
			"FRM_FIELD_OID"		 : oid,
			"command"		 : command,
			"<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_CHANEL_ID] %>" : "<%= oidChanel %>",
			"<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_GROUP_ID] %>" : "<%= oidGroup %>",
			"<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_CATEGORY_ID] %>" : "<%= oidCategory %>",
			"<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_CONTENT_ID] %>" : "<%= oidContent %>",
			"<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_LOCATION] %>" : ""+templatlng,
			"FRM_FIELD_ADD_LINKED" : addLinked,
			"FRM_FIELD_FOLLOW_UP" : followUp,
			"FRM_FIELD_APPROOT" : approot,
			"FRM_FIELD_AKSES_TYPE" : akses
		    }
		    
		    onSuccess = function(data){
			
		    };
                    
		    getDataFunction(onDone, onSuccess, approot, command, dataSend, "AjaxAreaList", ".addeditgeneral-body", false, "json");
		});
	    };
	    addeditgeneral4 = function(elementId){
		$(elementId).click(function(){
		   
		
		    oid = $(this).data("oid");
		    var addLinked = $(this).data("addLinked");
		    var followUp = $(this).data("followUp");
		    var position = $("#position").html();
		    $("#addeditgeneral").modal("show");
                    $("#savegeneral").show();
                    $("#btngeneralform").hide();
		    command = $("#command").val();
		    var templatlng = $("#templatlng").val();
		    var contentId = $(this).data("contentId");
		    dataFor = $(this).data('for');
		    $("#oid").val(oid);
		    $(".btnedited").hide();
		    $("#savegeneral").show();
		    $("#generaldatafor").val(dataFor);
		     $("#addfromcontent").hide();
		    //SET TITLE MODAL
		    if(oid != 0){
			if(dataFor == 'showdetailinfo'){
			    $(".addeditgeneral-title").html("Detail Information");
			}else if(dataFor == 'inforeport'){
			    $(".addeditgeneral-title").html("Report Information");
			    $("#addeditgeneral").find(".modal-dialog").addClass("modal-lg");
			    onDone = function(data){
			    }
			    $("#addlinked").show().data("addLinked", oid);
			    $("#followup").hide().data("followUp", oid);
			    $("#editreport").hide();
			    $("#savegeneral").hide();
			}else if(dataFor  == 'takepicture3'){
			    
			    $(".addeditgeneral-title").html("Report");
			    $("#savegeneral").show();
			    $("#backto").show().data("oid", oid);
			    onDone = function(data){
				
				takePicture();
				snapPicture();
				datePicker(".datepicker","yyyy-mm-dd");
			    }
			}else if(dataFor == 'followup'){
			    onDone = function(data){
				getCurrentLocation("#<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_LOCATION] %>");
				takePicture();
				snapPicture();
				datePicker(".datepicker","yyyy-mm-dd");
			    };
			    $("#savegeneral").show();
			    $("#backto").show().data("oid", oid);
			}else if(dataFor == "showinfocontent"){
			    $(".addeditgeneral-title").html("Content Info");
			    $("#savegeneral").hide();
			    $("#addfromcontent").show();
			}

		    }else{
			if(dataFor == 'showareausageform'){
			    $(".addeditgeneral-title").html("Detail Information");
			}else if(dataFor == 'inforeport'){
			    $("#addlinked").show().data("addLinked", oid);
			    $("#followup").hide().data("followUp", oid);
			    $("#editreport").hide();
			    $("#savegeneral").hide();
			    $(".addeditgeneral-title").html("Report Information");
			    $("#addeditgeneral").find(".modal-dialog").addClass("modal-lg");
			    onDone = function(data){
			    };
			}else if(dataFor  == 'takepicture3'){
			    $(".addeditgeneral-title").html("Report");
			    onDone = function(data){
				takePicture();
				snapPicture();
				datePicker(".datepicker","yyyy-mm-dd");
				getCurrentLocation("#<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_LOCATION] %>");
			    };
			    $("#savegeneral").show();
			    $("#backto").show().data("oid", oid);
			}else if(dataFor == 'followup'){
			    onDone = function(data){
				getCurrentLocation("#<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_LOCATION] %>");
				takePicture();
				snapPicture();
				datePicker(".datepicker","yyyy-mm-dd");
			    };
			    $("#savegeneral").show();
			    $("#backto").show().data("oid", oid);
			}else if(dataFor == "showinfocontent"){
			    $(".addeditgeneral-title").html("Content Info");
			    $("#savegeneral").hide();
			    $("#addfromcontent").show();
			}
		    }


		    dataSend = {
			"FRM_FIELD_DATA_FOR"	: dataFor,
			"FRM_FIELD_OID"		 : oid,
			"command"		 : command,
			"<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_CHANEL_ID] %>" : "<%= oidChanel %>",
			"<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_GROUP_ID] %>" : "<%= oidGroup %>",
			"<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_CATEGORY_ID] %>" : "<%= oidCategory %>",
			"<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_CONTENT_ID] %>" : "<%= oidContent %>",
			"<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_LOCATION] %>" : ""+templatlng,
			"FRM_FIELD_ADD_LINKED" : addLinked,
			"FRM_FIELD_FOLLOW_UP" : followUp,
			"FRM_FIELD_APPROOT" : approot,
			"<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_CONTENT_ID] %>" : contentId,
			"FRM_FIELD_AKSES_TYPE" : akses
		    }
		    
		    onSuccess = function(data){
			
		    };
                    
		    getDataFunction(onDone, onSuccess, approot, command, dataSend, "AjaxAreaList", ".addeditgeneral-body", false, "json");
		});
	    };
	    function takePicture(){
		var video = document.getElementById("video");

		// Test browser support
		 navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia;
		 window.URL = window.URL || window.webkitURL;

		if (navigator.getUserMedia === null) {
		  document.getElementById('gum-unsupported').classList.remove('hidden');
		  document.getElementById('cancelcapture').setAttribute('disabled', 'disabled');
		  document.getElementById('takepicture').setAttribute('disabled', 'disabled');
		} else {
		  // Opera <= 12.16 accepts the direct stream.
		  // More on this here: http://dev.opera.com/articles/view/playing-with-html5-video-and-getusermedia-support/
		  //var createSrc = window.URL ? window.URL.createObjectURL : function(stream) {return stream;};
		  $("#takepicture").click(function() {
		    $("#capturearea").modal("show");
		    if (navigator.getUserMedia) {
			    navigator.getUserMedia({ video: true,audio:false},
			      function(stream) {
				videoStream = stream.getTracks()[0];
				 video.src = window.URL.createObjectURL(stream);
				 video.onloadedmetadata = function(e) {
				   video.play();
				 };
			      },
			      function(err) {
				 console.log("The following error occured: " + err.name);
			      }
			   );
			} else {
			   console.log("getUserMedia not supported");
			}
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
		   
		   getCurrentLocation("#<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_REPORT_IMAGE_LOCATION] %>");
		   $("#imgString").val(c1Canvas.toDataURL());
		   $("#imgdisplay").attr({"src" : c1Canvas.toDataURL()}).fadeIn("medium");
		   $(this).removeAttr("disabled").html(currentHtml);
		   $("#capturearea").modal("hide");
		});
	   };
	    
	    function setContent(dataget){
		var groupId = $("#groupid").val();
		var categoryId = $("#categoryId").val();
		$("#loadmap").modal("show");
		var initialLocation;
	    
		var smallMarker = new google.maps.Marker({
		    draggable : true,
		    animation : google.maps.Animation.BOUNCE
		});
		
		var marker = new google.maps.Marker({
		    draggable : true
		});


		var map = new google.maps.Map(document.getElementById('map'), { //var
		    zoom: 18,//10,
		    mapTypeId: google.maps.MapTypeId.SATELLITE,
		    disableDefaultUI: false,
		    scrollwheel: true,
		    draggable : true

		});

		var smallMap = new google.maps.Map(document.getElementById('smallmap'), { //var
		    zoom: 13,//10,
		    mapTypeId: google.maps.MapTypeId.ROADMAP,
		    disableDefaultUI: false,
		    scrollwheel: true,
		    draggable : true

		});
		
		map.controls[google.maps.ControlPosition.RIGHT_TOP].push(document.getElementById('legend'));
		navigator.geolocation.getCurrentPosition(function(position) {          
		    initialLocation = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
		    map.setCenter(initialLocation);
		    smallMap.setCenter(initialLocation);
		    placeMarker(initialLocation, smallMarker, marker, smallMap, map, clickFunction, dragendFunction, "Your position");
		    placeMarker(initialLocation, marker, smallMarker, map, smallMap, null, dragendFunction);
		});
		
		google.maps.event.addListener(smallMap, "click", function(event){
		    // place a marker
		    placeMarker(event.latLng, smallMarker, marker, smallMap, map, null, dragendFunction, "Your position");
		    placeMarker(event.latLng, marker, smallMarker, map, smallMap, null, dragendFunction, "Your position");
		    map.setCenter(event.latLng);
		});
		
		google.maps.event.addListener(map, "click", function(event){
		    // place a marker
		    placeMarker(event.latLng, smallMarker, marker, smallMap, map, null, dragendFunction, "Your position");
		    placeMarker(event.latLng, marker, smallMarker, map, smallMap, null, dragendFunction, "Your position");
		    map.setCenter(event.latLng);
		});
		onDone = function(data){

		    for(var i = 0; i < data.markerDatas.markers.length; i++){
			var shape = new google.maps.Marker();
			shape.setOptions(data.markerDatas.markers[i]);
			var event = google.maps.event.addListener(shape,"click", function(){
			    $("#oidSelected").val(this.index);
			    $("#addfromcontent").data("contentId", this.index);
			    $("#editreport2").data("oid", this.index).click();
			});
			markers.push(shape);
			events2.push(event);
		    }
		    
		    for(var i = 0; i < data.reportDatas.reports.length; i++){
			var shape = new google.maps.Marker();
			shape.setOptions(data.reportDatas.reports[i]);
			
			var event = google.maps.event.addListener(shape,"click", function(){
			    $("#oidSelected").val(this.index);
			    $(".editreport").click();
			});
			markers.push(shape);
			events.push(event);
		    }
		    var mcOptions = {gridSize: 0, maxZoom: 14};
		    var markerCluster = new MarkerClusterer(map, markers, mcOptions);
		    $("#loadmap").modal("hide");
		};
		onSuccess = function(data){
		    
		};
		
		dataSend = {
		    "FRM_FIELD_OID_GROUP" : groupId,
		    "FRM_FIELD_OID_CATEGORY" : categoryId,
		    "command" : command,
		    "FRM_FIELD_DATA_FOR" : dataget,
		    "FRM_FIELD_AKSES_TYPE" : akses
		}
		
		getDataFunction(onDone, onSuccess, approot, command, dataSend, "AjaxMapContent", "#loadmapcontent", false, "json");
	    };
	    
	    function getCurrentLocation(selector){
		navigator.geolocation.getCurrentPosition(function(position) {          
		   $(selector).val(position.coords.latitude+", "+position.coords.longitude);
		});
	    }
	    setContent("getcontent");
	    modalSetting("#loadmap", "static", false,false);
	    modalSetting("#addeditgeneral", "static", false,false);
	    modalSetting("#capturearea", "static", false, false);
	    $("#toogleshow").click(function(){
		var getHtml = $(this).html();
		if(getHtml == "Show Legend"){
		    $(this).html("Hide Legend");
		}else{
		    $(this).html("Show Legend");
		}
		$("#legendcontent").slideToggle();
	    });
	    function datePicker(contentId, formatDate){
		$(contentId).datepicker({
		    format : formatDate
		});
		$(contentId).on('changeDate', function(ev){
		    $(this).datepicker('hide');
		});
	    }
	    addeditgeneral2(".addeditreport");
	    addeditgeneral3(".editreport");
	    addeditgeneral4(".editreport2");
	    addeditgeneral4("#editreport2");
	    $("form#generalform").submit(function(){
		getCurrentLocation("#<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_REPORT_POST_LOCATION] %>");
		var currentBtnHtml = $("#btngeneralform").html();
		$("#btngeneralform").html("Saving...").attr({"disabled":"true"});
		var generaldatafor = $("#generaldatafor").val();
		if(generaldatafor == "takepicture3"){
		    
		    onDone = function(data){
			window.location = window.location.href;
		    };
		}else if(generaldatafor == "followup"){
		    onDone = function(data){
			window.location = window.location.href;
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
                    "command"		: command,
		    "FRM_FIELD_APPROOT" : approot,
		    "FRM_FIELD_AKSES_TYPE" : akses
		}
		
		getDataFunction(onDone, onSuccess, approot, command, dataSend, "AjaxFrontEnd", replaceTo, false, "json");
	    });
	    
	    function login(){
                $("#login").click(function(){
                    window.location = "<%=approot%>/page/frontend/login.jsp";
                });
            }
            
            login();
	    
	    
	    
	    var screenWidth = window.screen.availWidth;
	    if(screenWidth <= 640){
		
		$(".menuflexible").removeClass("col-xs-2").removeClass("col-xs-6").attr({"style":"width:100%;"});
		$("#legend").removeClass("col-xs-2").addClass("col-xs-6");
	    }
	});
      </script>
      <style type="text/css">
	.dropdown-menu>li
	{	position:relative;
		-webkit-user-select: none; /* Chrome/Safari */        
		-moz-user-select: none; /* Firefox */
		-ms-user-select: none; /* IE10+ */
		/* Rules below not implemented in browsers yet */
		-o-user-select: none;
		user-select: none;
		cursor:pointer;
	}
	.dropdown-menu .sub-menu {
	    left: 100%;
	    position: absolute;
	    top: 0;
	    display:none;
	    margin-top: -1px;
	    border-top-left-radius:0;
	    border-bottom-left-radius:0;
	    border-left-color:#fff;
	    box-shadow:none;
	}
	.right-caret:after,.left-caret:after
	{	content:"";
	   border-bottom: 5px solid transparent;
	   border-top: 5px solid transparent;
	   display: inline-block;
	   height: 0;
	   vertical-align: middle;
	   width: 0;
	       margin-left:5px;
       }
       .right-caret:after
       {	border-left: 5px solid #ffaf46;
       }
       .left-caret:after
       {	border-right: 5px solid #ffaf46;
       }
    </style>
      <div id="addeditgeneral" class="modal fade nonprint" tabindex="-1" style="overflow-y: auto;">
            <div class="modal-dialog nonprint modal-sm">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="addeditgeneral-title"></h4>
                    </div>
                    <input type="hidden" id="command" value="<%= Command.NONE %>">
                    <input type="hidden" name="approot" id="approot" value="<%= approot %>">
                    <form id="generalform">
                        <input type="hidden" name="FRM_FIELD_DATA_FOR" id="generaldatafor">
                        <input type="hidden" name="command" value="<%= Command.SAVE %>">
                        <input type="hidden" name="FRM_FIELD_OID" id="oid">
			<input type="hidden" id="<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_REPORT_POST_LOCATION] %>" name="<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_REPORT_POST_LOCATION] %>">
                        <div class="modal-body ">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="box-body addeditgeneral-body">

                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button data-for="takepicture" id="tempbutton2" type="button" style="display:none"  class="btn btn-danger"><i class="fa fa-ban"></i> Close</button>
                            <button data-for="showdetailinfo" id="tempbutton" type="button" style="display:none"  class="btn btn-danger"><i class="fa fa-ban"></i> Close</button>
                            <button data-for="showattachmentmaster"  type="button" class="btn btn-warning" id="btngeneralform"><i class="fa fa-files"></i> Supporting Data</button>
			    <button type="button" class="btn btn-primary btnedited editreport" id="backto" data-for="inforeport" style="display:none;"><i class="fa fa-arrow-left"></i> Back</button>
			    <button type="button" class="btn btn-primary btnedited editreport2" id="addlinked" data-for="takepicture3" data-oid="0" style="display:none;"><i class="fa fa-link"></i> Add Linked Report</button>
			    <button type="button" class="btn btn-primary btnedited editreport2" id="followup" data-oid="0" data-for="followup" style="display:none;"><i class="fa fa-mail-forward"></i> Follow Up</button>
			    <button type="button" class="btn btn-primary btnedited editreport2" id="editreport" data-for="takepicture3" style="display:none;"><i class="fa fa-pencil"></i> Edit Report</button>
                            <button type="submit" class="btn btn-primary cancelcapture" id="savegeneral"><i class="fa fa-check"></i> Save</button>
                            <button type="button" data-dismiss="modal" class="btn btn-danger cancelcapture"><i class="fa fa-ban"></i> Close</button>
			    <button type="button" class="btn btn-primary btnedited editreport2" id="addfromcontent" data-for="takepicture3" style="display:none;"><i class="fa fa-plus"></i> Add Report</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
	<div id="loadmap" class="modal fade nonprint" tabindex="-1">
            <div class="modal-dialog nonprint modal-sm">
                <div class="modal-content" >
                     <div class="modal-body" id="loadmapcontent">
		     </div>
                </div>
            </div>
        </div>
	<input type="hidden" id="templatlng">
	<div id="legend" class="col-xs-2" style="background: white;">
	    <div class="row" id="legendcontent">
		<div class="col-xs-12">
		    <div class="form-group">
			<h5><b>Information</b></h5>
		    </div>
		    <div class="form-group">
			<b>Your Position</b>
			<div id="position"></div>
		    </div>
		    <div class="form-group">
			<b><a href="javascript:" class="addeditreport" data-for="takepicture3" data-oid="0"><i class="fa fa-plus"></i> Add Report</a></b>
		    </div>
		</div>
	    </div>
	    <div class="row">
		    <button type="button" class="btn btn-flat btn-sm btn-primary form-control" id="toogleshow">Hide Legend</button>
	    </div>
	</div>
	<div id="capturearea" class="modal fade" tabindex="-1">
            <div class="modal-dialog nonprint">
                <div class="modal-content" >
                     <div class="modal-body">
			 <div class="row">
			     <div class='col-md-12'>
				<video id='video' autoplay='autoplay' style='width:100%;'></video>
				<canvas id='picturecaptured' style="display:none;"></canvas>
				<button class='btn btn-flat btn-primary cancelcapture col-md-6' type='button' id='capture'>Capture</button>
				<button class='btn btn-flat btn-danger cancelcapture col-md-6' type='button'>Cancel</button>
			    </div>
			 </div>
		     </div>
                </div>
            </div>
        </div>
	<input type="hidden" id="oidSelected">
	<button class="editreport" style="display:none;" data-for="inforeport"></button>
	<button id="editreport2" style="display:none;" data-for="showinfocontent"></button>
        <input type='hidden' id='akses' value='public'>
    </body>
</html>
