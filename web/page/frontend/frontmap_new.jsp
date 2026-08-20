
<%@page import="com.dimata.posbo.form.warehouse.FrmReceiveStockCode"%>
<%@page import="com.dimata.harisma.form.masterdata.FrmDusun"%>
<%@page import="com.dimata.harisma.form.masterdata.FrmDesa"%>
<%@page import="com.dimata.harisma.form.masterdata.FrmKecamatan"%>
<%@page import="com.dimata.harisma.entity.masterdata.Kabupaten"%>
<%@page import="com.dimata.harisma.entity.masterdata.PstKabupaten"%>
<%@page import="com.dimata.harisma.form.masterdata.FrmKabupaten"%>
<%@page import="com.dimata.harisma.form.masterdata.FrmNegara"%>
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
    
    Vector regionList = PstKabupaten.list(0, 0, ""+PstKabupaten.fieldNames[PstKabupaten.FLD_ID_PROPINSI]+"='40'", ""+PstKabupaten.fieldNames[PstKabupaten.FLD_NM_KABUPATEN]+" ASC");
    
%>
<html>
    <head>
	<meta charset="UTF-8">
	<title>Di-SMARTCITY</title>
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<%@include file="../../template-component/css-component.jsp" %>
	<style type="text/css">
	    .timeline:before{
		background: none;
	    }
	    .timeline-item{
		margin-left: 0px;
	    }
	</style>
    </head> 
    <body class="<%= skin %> layout-top-nav">
	<input type="hidden" id="approot" value="<%= approot %>">
	<input type="hidden" id="command" value="<%= Command.NONE %>">
        <input type="hidden" name="curentLocation" id="curentLocation">
        <div class="wrapper">
	    <header class="main-header">
		<nav class="navbar navbar-static-top">
		    <div class="container-fluid">
			<div class="navbar-header">
			    <a href="javascript:" class="navbar-brand"><b>DiSmartCity</b></a>
			    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse">
				<i class="fa fa-bars"></i>
			    </button>
			</div>
			<div class="collapse navbar-collapse" id="navbar-collapse">
			    <ul class="nav navbar-nav navbar-right">
				<li><a href="javascript:" id="filteredinfo"></a></li>
				<li data-toggle="tooltip" title="Advance Filter" data-placement="bottom"><a href="javascript:" id="filter"><i class="fa fa-filter"></i></a></li>
				<li data-toggle="tooltip" title="Add Report" data-placement="bottom"><a href="javascript:" class="addreport" data-for="addreport" data-oid="0" data-content-id="0" data-linked-id="0"><i class="fa fa-plus"></i></a></li>
				<li class="dropdown" data-toggle="tooltip" title="Information" data-placement="bottom">
				    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-info-circle"></i></a>
				    <ul class="dropdown-menu" role="menu">
					<li><a href="login.jsp"><i class="fa fa-sign-in"></i> Sign in</a></li>
					<li class="divider"></li>
					<li class="disabled"><a href="www.dimata.com"><b>Copyright &copy; 2016PT. Dimata Sora Jayate.</a></li>
				    </ul>
				</li>
			    </ul>
			    <ul class="nav navbar-nav">
				<li style="width:20%">
				    <form class="navbar-form opensearch" id="formsearch">
					<div class="col-md-12">
					    <div class="input-group">
						<input type="hidden" name="FRM_FIELD_DATA_FOR" value='contentsearch'>
						<input class="form-control" id="navbar-search-input searchbox" placeholder="Search" type="text" name="FRM_FIELD_SEARCH_TEXT">
						<div class="input-group-btn" style="padding:0px;">
						    <button class="btn btn-primary" style="border-color: rgba(255,255,255,0.2);"><i class="fa fa-search"></i></button>
						</div>
					    </div>
					</div>
				    </form>
				</li>
			    </ul>
			</div>
		    </div>
		</nav>
	    </header>
	    <div class="content-wrapper" style="padding-top:50px;">
		<section class="content" style="padding-top:0px;">
		    <div class="row">
			<div id="map" class="col-md-12"></div>
		    </div>
		</section>
	    </div>
	</div>
	
	<%@include file="../../template-component/plugins-component.jsp" %>
	<script async defer src="https://maps.googleapis.com/maps/api/js?v=3&key=AIzaSyCVHaChwTJ1045ZRf57k4waY28m7M7mXjQ&callback=setContent&libraries=geometry"></script>
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
		var maparea = document.getElementById("map");
		var map;
		var oid;
		var dataFor;
		var yourMarker;
		var akses = $("#akses").val();
		var closest = -1;
		var mcOptions = {gridSize: 0, maxZoom: 15};
		var markerCluster;
		var bounds;
		var distances = [];
		var timeOut;
		var timeOutList;
		//SET MAP
		setContent = function(){
		    map = new google.maps.Map(maparea,{
			mapTypeId : google.maps.MapTypeId.ROADMAP,
			disableDefaultUI : false,
			scrollwheel : true,
			draggable : true,
			mapTypeControl : false
		    });
		    yourMarker = new google.maps.Marker({
			draggable : false
		    });
		    
		    
		    
		    var zoomLevel = $("#zoomlevel").val();
		    map.setZoom(Number(zoomLevel));
		    getCurrentLocation(map);
		    
		    map.addListener("zoom_changed",function(){
			clearTimeout(timeOut);
			timeOut = setTimeout(function(){
			    showMarker();
			},3000);
		    });
		    
		    map.addListener("click", function(event){
			setYourPosition(event.latLng);
		    });
		    
		    map.addListener("dragend", function(event){
			clearTimeout(timeOut);
			timeOut = setTimeout(function(){
			    showMarker();
			},3000);
		    });
		    
		    map.addListener("idle", function(event){
			
			clearTimeout(timeOutList);
                        timeOutList = setTimeout(function(){
			    
			    if(navigator.geolocation){
				navigator.geolocation.getCurrentPosition(function(position) {
				    yourPosition = position.coords.latitude+", "+position.coords.longitude;
				    document.getElementById("curentLocation").value = yourPosition.toString();
				    getReportAndInfo("#dangerInfoPlace");
				    getAroundReport("#aroundInfoPlace");
				});
			    }else{
				$("#locationerr").modal("show");
			    }
                            
                        }, 3000);
		    });
		    
		    
		    //CUSTOM CONTROL MAP
		    map.controls[google.maps.ControlPosition.RIGHT_BOTTOM].push(document.getElementById('getyourposition'));
		    //PLACE CURENT LOCATION ON THE SELECTOR TAG
                    map.controls[google.maps.ControlPosition.LEFT_TOP].push(document.getElementById('placeReport'));
		};
		
		//SHOW MARKER TO MAP
		showMarker = function(){
		    var bounds = map.getBounds();
		    var ne = bounds.getNorthEast();
		    var sw = bounds.getSouthWest();
		    var nw = new google.maps.LatLng(ne.lat(), sw.lng());
		    var se = new google.maps.LatLng(sw.lat(), ne.lng());
		    var zoom = map.getZoom();
		    $("#ne").val(ne);
		    $("#nw").val(nw);
		    $("#sw").val(sw);
		    $("#se").val(se);
		    setShapeOnMap(null);
		    
		    $("#zoomlevel").val(map.getZoom());
		    $("#markerprocess").modal("show");
		    onSuccess = function(data){

		    };

		    onDone = function(data){
			if(markers.length > 0){
			    //markerCluster.clearMarkers();
			    markers = [];
			}
			var zoomLevel = map.getZoom();
			var markerlength = data.markerDatas.markers.length;
			if(data.markerDatas.markers.length > 0 || data.reportDatas.reports.length){
			    for(var i = 0; i < data.markerDatas.markers.length; i++){				
				var shape = new google.maps.Marker();
				shape.setOptions(data.markerDatas.markers[i]);
				var event = google.maps.event.addListener(shape,"click", function(){
				    if(zoomLevel <= 14){
					//zoom to 15
					map.setZoom(16);
					map.setCenter(this.getPosition());
				    }else{
					$("#oidSelected").val(this.index);
					$("#addfromcontent").data("contentId", this.index);
					$("#contentinfo").data("oid", this.index).click();
				    }
				
				});
				markers.push(shape);
				events.push(event);

			    }

			    for(var i = 0; i < data.reportDatas.reports.length; i++){
				var shape = new google.maps.Marker();
				shape.setOptions(data.reportDatas.reports[i]);
				var event = google.maps.event.addListener(shape,"click", function(){
				    $("#oidSelected").val(this.index);
				    $("#reportinfo").data("oid", this.index).click();
				});
				markers.push(shape);
				events.push(event);
			    }

			    setShapeOnMap(map);
			    /*if(zoom > 14){
				markerCluster = new MarkerClusterer(map, markers, mcOptions);
			    }*/
			}
			$("#markerprocess").modal("hide");
		    };

		    dataSend = $("form#filterform").serialize();
		    getDataFunction(onDone, onSuccess, approot, command, dataSend, "AjaxMapContent", null, false, "json");
		};
		
		//SET MARKER TO MAP
		setShapeOnMap = function(map){
		    for (var i = 0; i < markers.length; i++) {
			markers[i].setMap(map);
		    }
		};
		
		
		//GET HEIGHT SCREEN
		function setHeight(selector){
		    var screenWidth = window.screen.availWidth;
		    var screenHeight = window.screen.availHeight-130;
		    $(selector).height(screenHeight);
		};
		
		function setHeightControl(selector){
		    var screenWidth = window.screen.availWidth;
		    var screenHeight = window.screen.availHeight-200;
		    $(selector).height(screenHeight);
		};
		
		//GET CURRENT LOCATION
		function getCurrentLocation(selector){
		    var yourPosition;
		    if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(function(position) {
			    yourPosition = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
			    selector.setCenter(new google.maps.LatLng(position.coords.latitude, position.coords.longitude));
			    setYourPosition(yourPosition);
			});
		    }else{
			$("#locationerr").modal("show");
		    }
		};
		
		//SET YOUR POSITION
		function setYourPosition(position){
		    yourMarker.setMap(null);
		    yourMarker.setPosition(position);
		    yourMarker.setMap(map);
		    yourMarker.setAnimation(google.maps.Animation.BOUNCE);
		}
		
		function getDataCurrentPosition(selector){
		    var yourPosition;
		    document.getElementById(selector).value = "Getting your location";
		    if(navigator.geolocation){
			navigator.geolocation.getCurrentPosition(function(position) {
			    yourPosition = position.coords.latitude+", "+position.coords.longitude;
			    document.getElementById(selector).value = yourPosition.toString();
			});
		    }else{
			$("#locationerr").modal("show");
		    }
		}
		
		//MODAL SETTING
		var modalSetting = function(elementId, backdrop, keyboard, show){
		    $(elementId).modal({
			backdrop    : backdrop,
			keyboard    : keyboard,
			show	    : show
		    });
		};
		
		//AJAX GET DATA
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
		
		//ADD REPORT
		addEditReport = function(elementId){
		    $(elementId).click(function(){
			var oid = $(this).data("oid");
			command = $("#command").val();
			var contentId = $(this).data("contentId");
			var linkedId = $(this).data("linkedId");
			var groupId = $("#<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_GROUP_ID] %>").val();
			var categoryId = $("#<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_CATEGORY_ID] %>").val();
			var chanelId = $("#<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_CHANEL_ID] %>").val();
			dataFor = $(this).data('for');
			$("#oidreport").val(oid);
			$("#reportdatafor").val(dataFor);
			//DEFAULT BUTTON
			var btnAddFromContent = $("#addfromcontent");
			var btnSaveReport = $("#savereport");
			var btnBackButton = $("#backbutton");
			var btnLinkedReport = $("#linkedreport");
			btnAddFromContent.hide();
			btnSaveReport.hide();
			btnBackButton.hide();
			btnLinkedReport.hide();
			
			//SET TITLE MODAL
			if(dataFor == "showinfocontent"){
			    $(".report-title").html("Content Info");
			    btnAddFromContent.show();
			    btnBackButton.data("oid", oid);
			    onDone = function(data){

			    };
			}else if(dataFor == "inforeport"){
			    btnLinkedReport.show();
			    btnLinkedReport.data("linkedId", oid);
			    btnBackButton.data("oid", oid);
			    onDone = function(data){

			    };
			}else if(dataFor == "addreport"){
			    $(".report-title").html("Add New Report");
			    btnSaveReport.show();
			    if(contentId != 0 || linkedId != 0){
				if(contentId != 0){
				    btnBackButton.data("for", "showinfocontent");
				}else{
				    btnBackButton.data("for", "inforeport");
				}
				btnBackButton.show();

			    }
			    onDone = function(data){
				takePicture();
				snapPicture();
				
				var checkLatLng = $("#<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_LOCATION] %>").val();
				if(checkLatLng.length == 0){
				    getDataCurrentPosition("<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_LOCATION] %>");
				}
			    };
			}

			
			$("#reportmodal").modal("show");


			dataSend = {
			    "FRM_FIELD_DATA_FOR"	: dataFor,
			    "FRM_FIELD_OID"		 : oid,
			    "command"		 : command,
			    "FRM_FIELD_AKSES_TYPE" : akses,
			    "FRM_FIELD_APPROOT" : approot,
			    "FRM_FIELD_CONTENT_ID" : contentId,
			    "FRM_FIELD_CHANEL_ID" : chanelId,
			    "FRM_FIELD_CATEGORY_ID" : categoryId,
			    "FRM_FIELD_GROUP_ID" : groupId,
			    "FRM_FIELD_ADD_LINKED" : linkedId
			}

			onSuccess = function(data){

			};

			getDataFunction(onDone, onSuccess, approot, command, dataSend, "AjaxAreaList", ".report-body", false, "json");
		    });
		};
		
		//SET FILTER INFO
		setFilterInfo = function(){
		    var chanel = $("#<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_CHANEL_ID] %> option:selected");
		    var group = $("#<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_GROUP_ID] %> option:selected");
		    var category = $("#<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_CATEGORY_ID] %> option:selected");
		    var kabupaten = $("#<%= FrmKabupaten.fieldNames[FrmKabupaten.FRM_FIELD_ID_KABUPATEN] %> option:selected");
		    var kecamatan = $("#<%= FrmKecamatan.fieldNames[FrmKecamatan.FRM_FIELD_ID_KECAMATAN] %> option:selected");
		    var desa = $("#<%= FrmDesa.fieldNames[FrmDesa.FRM_FIELD_ID_DESA] %> option:selected");
		    var dusun = $("#<%= FrmDusun.fieldNames[FrmDusun.FRM_FIELD_ID_DUSUN] %> option:selected");
		    
		    var info = "";
		    if(chanel.val().length > 0){
			if(group.val().length > 0){
			    if(category.val().length > 0){
				info += ""+category.text();
			    }else{
				info += ""+group.text();
			    }
			}else{
			    info += ""+chanel.text();
			}
		    }
		    
		    if(info.length > 0){
			if(kabupaten.val().length > 0){
			    if(kecamatan.val().length > 0){
				if(desa.val().length > 0){
				    if(dusun.val().length > 0){
					info+=" & "+dusun.text();
				    }else{
					info+=" & "+desa.text();
				    }
				}else{
				    info+=" & "+kecamatan.text();
				}
			    }else{
				info+=" & "+kabupaten.text();
			    }
			}
		    }else{
			if(kabupaten.val().length > 0){
			    if(kecamatan.val().length > 0){
				if(desa.val().length > 0){
				    if(dusun.val().length > 0){
					info+=""+dusun.text();
				    }else{
					info+=""+desa.text();
				    }
				}else{
				    info+=""+kecamatan.text();
				}
			    }else{
				info+=""+kabupaten.text();
			    }
			}
		    }
		    if(info.length > 0){
			info = "Filtered by : "+info
		    }
		    $("#filteredinfo").html(info);
		}
		
		//GET CLOSEST MARKER
		function rad(x){
		    return x*Math.PI/180;
		}
		function find_closest_marker(mlat, mlng, lat, lng, i) {
		    var R = 6371; // radius of earth in km
		    var dLat  = rad(mlat - lat);
		    var dLong = rad(mlng - lng);
		    var a = Math.sin(dLat/2) * Math.sin(dLat/2) +
			Math.cos(rad(lat)) * Math.cos(rad(lat)) * Math.sin(dLong/2) * Math.sin(dLong/2);
		    var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		    var d = R * c;
		    distances[i] = d;
		    if ( closest == -1 || d < distances[closest] ) {
			closest = i;
		    }
		};
	    
		//TAKE PICTURE (OPEN MODAL)
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
	       
	        //SNAP PICTURE /CAPTURE PICTURE
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

		       getDataCurrentPosition("<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_REPORT_IMAGE_LOCATION] %>");
		       $("#imgString").val(c1Canvas.toDataURL());
		       $("#imgdisplay").attr({"src" : c1Canvas.toDataURL()}).fadeIn("medium");
		       $(this).removeAttr("disabled").html(currentHtml);
		       $("#capturearea").modal("hide");
		    });
	       };
	       
	       var getReportAndInfo = function(elemetPlaceId){
                    var curentLocation = $("#curentLocation").val();
                    var dataFor = "getDangerReport";
                    var command = <%=Command.LIST%>;
                    var paggingPlace = $("#paggingPlace").val();
                    $(elemetPlaceId).html("<option>Please wait...</option>").attr({"disabled":"true"});
                    
                    onSuccess = function(data){
			
		    };
		    onDone = function(data){
                        clickPaging(".pagings");
			viewOnMap(".viewonmap");
		    };

		    dataSend = {
			"FRM_FIELD_DATA_FOR"        : dataFor,		
			"command"		    : command,
			"FRM_FIELD_APPROOT"         : approot,
                        "FRM_FIELD_CUR_LOCATION"    : curentLocation,
                        "showPage"                  : paggingPlace
		    };
                    
                    //alert (JSON.stringify(dataSend));

		    getDataFunction(onDone, onSuccess, approot, command, dataSend, "AjaxReportInfo", elemetPlaceId, false, "json");
                };
                
                var clickPaging = function(elementId){
                    $(elementId).click(function(){
                        var showPage=0;
                        var type = $(this).data('type');
                        var curentPage = $("#paggingPlace").val();
                        var total = $("#totalPaging").val();

                        if (type==="first"){
                            showPage = 1;
                        }else if (type==="prev"){
                            showPage = Number(curentPage)-1;
                        }else if (type==="next"){
                            showPage = Number(curentPage)+1;
                        }else if (type=="last"){
                            showPage = total;
                        }
                        
                        $("#paggingPlace").val(showPage);
                        getReportAndInfo("#dangerInfoPlace");
                    });
                    
                };
                
                var getAroundReport = function(elemetPlaceId){
                    setBoundary();
                    var curentLocation = $("#curentLocation").val();
                    var ne = $("#ne").val();
		    var nw = $("#nw").val();
		    var sw = $("#sw").val();
		    var se = $("#se").val();
                    var dataFor = "getAreaReport";
                    var command = <%=Command.LIST%>;
                    var paggingPlace2 = $("#paggingPlace2").val();
                    $(elemetPlaceId).html("<option>Please wait...</option>").attr({"disabled":"true"});
                    
                    onSuccess = function(data){
			
		    };
		    onDone = function(data){
                        clickPaging2('.pagings2');
			viewOnMap(".viewonmap");
		    };

		    dataSend = {
			"FRM_FIELD_DATA_FOR"        : dataFor,		
			"command"		    : command,
			"FRM_FIELD_APPROOT"         : approot,
                        "FRM_FIELD_CUR_LOCATION"    : curentLocation,
                        "showPage"                  : paggingPlace2,
                        "FRM_FIELD_NE"              : ne,
                        "FRM_FIELD_NW"              : nw,
                        "FRM_FIELD_SE"              : se,
                        "FRM_FIELD_SW"              : sw
		    };
                    
                    getDataFunction(onDone, onSuccess, approot, command, dataSend, "AjaxReportInfo", elemetPlaceId, false, "json");
                };
                
                var clickPaging2 = function(elementId){
                    $(elementId).click(function(){
                        var showPage=0;
                        var type = $(this).data('type');
                        var curentPage = $("#paggingPlace2").val();
                        var total = $("#totalPaging2").val();

                        if (type==="first"){
                            showPage = 1;
                        }else if (type==="prev"){
                            showPage = Number(curentPage)-1;
                        }else if (type==="next"){
                            showPage = Number(curentPage)+1;
                        }else if (type=="last"){
                            showPage = total;
                        }
                        
                        $("#paggingPlace2").val(showPage);
                        getAroundReport("#aroundInfoPlace");
                    });
                    
                };
		
		function setBoundary(){
                    var bounds = map.getBounds();
		    var ne = bounds.getNorthEast();
		    var sw = bounds.getSouthWest();
		    var nw = new google.maps.LatLng(ne.lat(), sw.lng());
		    var se = new google.maps.LatLng(sw.lat(), ne.lng());
		    $("#ne").val(ne);
		    $("#nw").val(nw);
		    $("#sw").val(sw);
		    $("#se").val(se);
                }
		
		function viewOnMap(selector){
		    $(selector).click(function(){
			var latlng  = $(this).data("latlng").split(",");
			if(latlng.length > 1){
			    map.setCenter(new google.maps.LatLng(latlng[0], latlng[1]));
			    map.setZoom(15);
			    $("#reportinfocontent").slideUp();
			    $("#showReportInfo").html("Tampilkan Informasi");
			}
		    });
		}
		
		function viewOnMapSearch(selector, selectorModal){
		    $(selector).click(function(){
			var latlng  = $(this).data("latlng").split(",");
			if(latlng.length > 1){
			    map.setCenter(new google.maps.LatLng(latlng[0], latlng[1]));
			    showMarker();
			}
			$(selectorModal).modal("hide");
		    });
		}
		//IMPLEMENT THE FUNCTION
		setHeight("#map");
		setHeightControl("#placereportcontent");
		modalSetting("#filtermodal",'static',false, false);
		modalSetting("#reportmodal", 'static', false, false);
		modalSetting("#markerprocess", 'static', false, false);
		modalSetting("#filterinfo",'static',false, false);
		modalSetting("#locationerr", 'static', false, false);
		modalSetting("#searchmodal", 'static', false, false);
		addEditReport(".addreport");
		
		//OPEN FILTER MODAL
		$("#filter").click(function(){
		    $("#currentPosition").val(map.getCenter());
		    var bounds = map.getBounds();
		    var ne = bounds.getNorthEast();
		    var sw = bounds.getSouthWest();
		    var nw = new google.maps.LatLng(ne.lat(), sw.lng());
		    var se = new google.maps.LatLng(sw.lat(), ne.lng());
		    $("#ne").val(ne);
		    $("#nw").val(nw);
		    $("#sw").val(sw);
		    $("#se").val(se);
		    
		   $("#filtermodal").modal("show");
		});
		
		//COMBOBOX CHANED
		$(".controlcombo").change(function(){
		    var getOid = $(this).val();
		    var dataFor = $(this).data("for");
		    var replaceTo = $(this).data("replaceTo");
		    $(replaceTo).html("<option>Please wait...</option>").attr({"disabled":"true"});
		    onSuccess = function(data){
			$(replaceTo).removeAttr("disabled");
		    };
		    onDone = function(data){
		    };

		    dataSend = {
			"FRM_FIELD_DATA_FOR": dataFor,
			"FRM_FIELD_OID"     : getOid,
			"command"		: command,
			"FRM_FIELD_APPROOT" : approot
		    }

		    getDataFunction(onDone, onSuccess, approot, command, dataSend, "AjaxFrontEnd", replaceTo, false, "json");
		});
		
		//ON FILTER FORM SUBMITED
		$("form#filterform").submit(function(){
		    var currentBtnHtml = $("#btnfilter").html();
		    $("#btnfilter").html("Filtering...").attr({"disabled":"true"});
		    var generaldatafor = $("#filterdatafor").val();
		    var zoomLevel = $("#zoomlevel").val();
		    var kabupatenId = $("#<%= FrmKabupaten.fieldNames[FrmKabupaten.FRM_FIELD_ID_KABUPATEN] %> option:selected");
		    var kecamatanId = $("#<%= FrmKecamatan.fieldNames[FrmKecamatan.FRM_FIELD_ID_KECAMATAN] %> option:selected");
		    var desaId = $("#<%= FrmDesa.fieldNames[FrmDesa.FRM_FIELD_ID_DESA] %> option:selected");
		    var dusunId = $("#<%= FrmDusun.fieldNames[FrmDusun.FRM_FIELD_ID_DUSUN] %> option:selected");
		    var latlng = "";
		    if(kabupatenId.data("latlng").length > 0){
			latlng = kabupatenId.data("latlng").split(",");
			zoomLevel = kabupatenId.data("zoomLevel");
			if(kecamatanId.data("latlng").length > 0){
			    latlng = kecamatanId.data("latlng").split(",");
			    zoomLevel = kecamatanId.data("zoomLevel");
			    if(desaId.data("latlng").length > 0){
				latlng = desaId.data("latlng").split(",");
				zoomLevel = desaId.data("zoomLevel");
				if(dusunId.data("latlng").length > 0){
				    latlng = dusunId.data("latlng").split(",");
				    zoomLevel = dusunId.data("zoomLevel");
				}
			    }
			}
		    }
		    if(latlng.length > 1){
			map.setCenter(new google.maps.LatLng(latlng[0], latlng[1]));
			map.setZoom(zoomLevel)
		    }
		    setShapeOnMap(null);
		    
		    onSuccess = function(data){
			$("#btnfilter").removeAttr("disabled").html(currentBtnHtml);
		    };
			
		    onDone = function(data){
			setFilterInfo();
			if(markers.length > 0){
			    //markerCluster.clearMarkers();
			    markers = [];
			    distances = []
			}
			var zoomLevel = map.getZoom();
			var markerlength = data.markerDatas.markers.length;
			if(data.markerDatas.markers.length > 0 || data.reportDatas.reports.length){
			    for(var i = 0; i < data.markerDatas.markers.length; i++){				
				var shape = new google.maps.Marker();
				shape.setOptions(data.markerDatas.markers[i]);
				var event = google.maps.event.addListener(shape,"click", function(){
				    if(zoomLevel <= 14){
					//zoom to 15
					map.setZoom(16);
					map.setCenter(this.getPosition());
				    }else{
					$("#oidSelected").val(this.index);
					$("#addfromcontent").data("contentId", this.index);
					$("#contentinfo").data("oid", this.index).click();
				    }
				
				});
				markers.push(shape);
				events.push(event);
				find_closest_marker(data.markerDatas.markers[i].position.lat,data.markerDatas.markers[i].position.lng, map.getCenter().lat(), map.getCenter().lng(), i);
				
			    }
			    if(data.markerDatas.markers.length > 0){
				map.setCenter(data.markerDatas.markers[closest].position);
			    }
			    
			    
			    for(var i = 0; i < data.reportDatas.reports.length; i++){
				var shape = new google.maps.Marker();
				shape.setOptions(data.reportDatas.reports[i]);
				var event = google.maps.event.addListener(shape,"click", function(){
				    $("#oidSelected").val(this.index);
				    $("#reportinfo").data("oid", this.index).click();
				});
				markers.push(shape);
				events.push(event);
			    }
			    
			    setShapeOnMap(map);
			    if(zoomLevel > 10){
				//markerCluster = new MarkerClusterer(map, markers, mcOptions);
			    }
			    
			    $("#filtermodal").modal("hide");
			}else{
			    $("#filterinfo").modal("show");
			}
			
			$("#btnfilter").removeAttr("disabled").html(currentBtnHtml);
		    };
		    
		    dataSend = $(this).serialize();
		    getDataFunction(onDone, onSuccess, approot, command, dataSend, "AjaxMapContent", null, false, "json");
		    
		    return false;
		});
		
		//SEARCH FORM ANIMATION
		$(".opensearch").focusin(function(){
		    $(this).animate({
			width : "250px"
		    });
		});
		$(".opensearch").focusout(function(){
		    $(this).animate({
			width : "10%"
		    });
		});
		
		//SAVE REPORT
		$("form#reportform").submit(function(){
		    
		    var currentBtnHtml = $("#savereport").html();
		    $("#savereport").html("Saving...").attr({"disabled":"true"});
		    if(navigator.geolocation){
			navigator.geolocation.getCurrentPosition(function(position) {
			    document.getElementById("<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_REPORT_POST_LOCATION] %>").value = position.coords.latitude+", "+position.coords.longitude;
			    
			    onDone = function(data){
				showMarker();
			    }

			    if($(this).find(".has-error").length == 0){
				onSuccess = function(data){
				    $("#savereport").removeAttr("disabled").html(currentBtnHtml);
				    $("#reportmodal").modal("hide");
				};
				dataSend = $("form#reportform").serialize()+"&<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_REPORT_POST_LOCATION] %>="+position.coords.latitude+", "+position.coords.longitude;
				getDataFunction(onDone, onSuccess, approot, command, dataSend, "AjaxAreaList", null, true, "json");
			    }else{
				$("#savereport").removeAttr("disabled").html(currentBtnHtml);
			    }
			});
		    }else{
			$("#savereport").removeAttr("disabled").html(currentBtnHtml);
			$("#locationerr").modal("show");
		    }
		    return false;
		});
		
		$("#getyourposition").click(function(){
		    getCurrentLocation(map);
		});
		
		$("form#formsearch").submit(function(){
		    $("#searchmodal").modal("show");
		    onDone = function(data){
			viewOnMapSearch(".viewonmapsearch", "#searchmodal");
		    };
		    onSuccess = function(data){
		    };
		    dataSend = $(this).serialize();
		    getDataFunction(onDone, onSuccess, approot, command, dataSend, "AjaxAreaList", ".search-body", false, "json");
		    return false;
		});
		
		$("#showReportInfo").click(function(){
                    var getHtml = $(this).html();
                    if(getHtml == "Sembunyikan"){
                        $(this).html("Tampilkan Informasi");
                    }else{
                        $(this).html("Sembunyikan");
                    }
                    $("#reportinfocontent").slideToggle();
                });
	    });
	</script>

	<!-- MODAL FOR FILTER FORM -->
	<div id="filtermodal" class="modal fade" tabindex="-1" style="overflow-y: auto;">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4>Chanel & Location Filter</h4>
                    </div>
                    <input type="hidden" id="command" value="<%= Command.SAVE %>">
                    <input type="hidden" name="approot" id="approot" value="<%= approot %>">
                    <form id="filterform">
                        <input type="hidden" name="FRM_FIELD_DATA_FOR" id="filterdatafor" value="getcontent">
                        <input type="hidden" name="command" value="<%= Command.NONE %>">
			<input type="hidden" name="FRM_FIELD_ZOOM_LEVEL" id="zoomlevel" value="10">
			<input type="hidden" name="FRM_FIELD_CURRENT_POSITION" id="currentPosition">
			<input type="hidden" name="FRM_FIELD_NE" id="ne">
			<input type="hidden" name="FRM_FIELD_NW" id="nw">
			<input type="hidden" name="FRM_FIELD_SE" id="se">
			<input type="hidden" name="FRM_FIELD_SW" id="sw">
			<input type="hidden" id="<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_REPORT_POST_LOCATION] %>" name="<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_REPORT_POST_LOCATION] %>">
                        <div class="modal-body ">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="box-body">
					<div class="col-md-6">
					    <div class="form-horizontal">
						<div class="form-group">
						    <label class="control-label col-md-2">Chanel</label>
						    <div class="col-md-10">
							<%= ControlCombo.draw(FrmReport.fieldNames[FrmReport.FRM_FIELD_CHANEL_ID], "-- Select Chanel", "", chanelKey, chanelVal, "data-for='getgroup' data-replace-to='#"+FrmReport.fieldNames[FrmReport.FRM_FIELD_GROUP_ID]+"'", "form-control controlcombo") %>
						    </div>
						</div>
						<div class="form-group">
						    <label class="control-label col-md-2">Group</label>
						    <div class="col-md-10">
							<select id="<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_GROUP_ID] %>" name="<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_GROUP_ID] %>" data-for="getcategory" data-replace-to="#<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_CATEGORY_ID] %>" class="form-control controlcombo">
							    <option value=""> -- Select Group --</option>
							</select>
						    </div>
						</div>
						<div class="form-group">
						    <label class="control-label col-md-2">Category</label>
						    <div class="col-md-10">
							<select id="<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_CATEGORY_ID] %>" name="<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_CATEGORY_ID] %>" class="form-control">
							    <option value=""> -- Select Category --</option>
							</select>
						    </div>
						</div>
					    </div>
					    
					    
					</div>
					<div class="col-md-6">
					    <div class="form-horizontal">
						<div class="form-group">
						    <label class="control-label col-md-2">Region</label>
						    <div class="col-md-10">
							<select name="<%= FrmKabupaten.fieldNames[FrmKabupaten.FRM_FIELD_ID_KABUPATEN] %>" id="<%= FrmKabupaten.fieldNames[FrmKabupaten.FRM_FIELD_ID_KABUPATEN] %>" data-for='getkecamatan' data-replace-to='#<%=FrmKecamatan.fieldNames[FrmKecamatan.FRM_FIELD_ID_KECAMATAN]%>' class='form-control controlcombo'>
							    <option value="" data-latlng="" data-zoom-level="">-- Select Region --</option>
							    <%
								for(int i = 0; i < regionList.size(); i++){
								    Kabupaten kabupaten = (Kabupaten) regionList.get(i);
								    %>
									<option value="<%= ""+kabupaten.getOID() %>" data-latlng="<%= (kabupaten.getKabupatenLatLng() == null ? "" : kabupaten.getKabupatenLatLng()) %>" data-zoom-level="<%= kabupaten.getKabupatenZoomLevel() %>"><%= kabupaten.getNmKabupaten() %></option>
								    <%
								}
							    %>
							</select>
						    </div>
						</div>
						<div class="form-group">
						    <label class="control-label col-md-2">Sub-region</label>
						    <div class="col-md-10">
							<select id="<%= FrmKecamatan.fieldNames[FrmKecamatan.FRM_FIELD_ID_KECAMATAN] %>" name="<%= FrmKecamatan.fieldNames[FrmKecamatan.FRM_FIELD_ID_KECAMATAN] %>" data-for="getdesa" data-replace-to="#<%= FrmDesa.fieldNames[FrmDesa.FRM_FIELD_ID_DESA] %>" class="form-control controlcombo">
							    <option value="" data-latlng="" data-zoom-level=""> -- Select Sub-region --</option>
							</select>
						    </div> 
						</div>
						<div class="form-group">
						    <label class="col-md-2 control-label">Village</label>
						    <div class="col-md-10">
							<select id="<%= FrmDesa.fieldNames[FrmDesa.FRM_FIELD_ID_DESA] %>" name="<%= FrmDesa.fieldNames[FrmDesa.FRM_FIELD_ID_DESA] %>" data-for="getdusun" data-replace-to="#<%= FrmDusun.fieldNames[FrmDusun.FRM_FIELD_ID_DUSUN] %>" class="form-control controlcombo">
							    <option value="" data-latlng="" data-zoom-level=""> -- Select Village --</option>
							</select>
						    </div>
						</div>
						<div class="form-group">
						    <label class="col-md-2 control-label">Sub-village</label>
						    <div class="col-md-10">
							<select id="<%= FrmDusun.fieldNames[FrmDusun.FRM_FIELD_ID_DUSUN] %>" name="<%= FrmDusun.fieldNames[FrmDusun.FRM_FIELD_ID_DUSUN] %>" class="form-control">
							    <option value="" data-latlng="" data-zoom-level=""> -- Select Sub-village --</option>
							</select>
						    </div>
						</div>
					    </div>
					</div>
				    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-primary" type="submit" id="btnfilter"><i class="fa fa-filter"></i> Filter</button>
			    <button type="button" class="btn btn-danger" data-dismiss="modal"><i class="fa fa-close"></i> Close</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
	
	<!-- MODAL FOR REPORT OR INFO -->
	<div id="reportmodal" class="modal fade" tabindex="-1" style="overflow-y: auto;">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="report-title"></h4>
                    </div>
                    <form id="reportform">
                        <input type="hidden" name="FRM_FIELD_DATA_FOR" id="reportdatafor">
                        <input type="hidden" name="command" value="<%= Command.SAVE %>">
                        <input type="hidden" name="FRM_FIELD_OID" id="oidreport">
                        <div class="modal-body ">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="box-body report-body">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
			    <button class="btn btn-info addreport" type="button" data-for="showinfocontent" id="backbutton"><i class="fa fa-arrow-left"></i> Back</button>
			    <button class="btn btn-primary addreport" type="button" id="addfromcontent" data-oid="0" data-for="addreport" data-content-id="0" data-linked-id="0"><i class="fa fa-plus"></i> Add Report</button>
			    <button class="btn btn-primary addreport" type="button" id="linkedreport" data-oid="0" data-for="addreport" data-content-id="0" data-linked-id="0"><i class="fa fa-plus"></i> Add Report</button>
			    <button class="btn btn-primary" type="submit" id="savereport"><i class="fa fa-save"></i> Save Report</button>
			    <button class="btn btn-danger" data-dismiss="modal" type="button"><i class="fa fa-close"></i> Cancel</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
			
	<!-- MODAL FOR LOADING ANIMATION LOAD MARKER -->
	<div id="markerprocess" class="modal fade" tabindex="-1" style="overflow-y: auto;">
            <div class="modal-dialog">
                <div class="modal-content">
		    <div class="modal-body ">
			<div class='progress progress-striped active'><div class='progress-bar progress-bar-primary' style='width: 100%'><b>Please Wait...</b></div></div>
		    </div>
                </div>
            </div>
        </div>
	
	<!-- MODAL FOR INFORMATION MARKER / OBJECT NOT FOUND -->
	<div id="filterinfo" class="modal fade" tabindex="-1" style="overflow-y: auto;">
            <div class="modal-dialog">
                <div class="modal-content">
		    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4>Filter Result Information</h4>
                    </div>
		    <div class="modal-body ">
			<i class="fa fa-exclamation"></i> Sorry, no content or report found in these area. Please search at another area or another options
		    </div>
		    <div class="modal-footer">
			<button class="btn btn-danger" data-dismiss="modal" type="button"><i class="fa fa-close"></i> Cancel</button>
		    </div>
                </div>
            </div>
        </div>
	
	<!-- MODAL FOR INFORMATION MARKER / OBJECT NOT FOUND -->
	<div id="locationerr" class="modal fade" tabindex="-1" style="overflow-y: auto;">
            <div class="modal-dialog">
                <div class="modal-content">
		    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4>Failed to get your location</h4>
                    </div>
		    <div class="modal-body ">
			<i class="fa fa-exclamation"></i> Geolocation is not supported by this browser or the browser doesn't have permission to get your geolocation
		    </div>
		    <div class="modal-footer">
			<button class="btn btn-danger" data-dismiss="modal" type="button"><i class="fa fa-close"></i> Cancel</button>
		    </div>
                </div>
            </div>
        </div>
	
	<!-- MODAL FOR CAPTURING PICTURE -->
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
	
	<!-- MODAL FOR SEARCH -->
	<div id="searchmodal" class="modal fade" tabindex="-1" style="overflow-y: auto;">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="search-title">Search Result</h4>
                    </div>
		    <div class="modal-body " style='background: #f1f1f1;'>
			<div class="row">
			    <div class="col-md-12">
				<div class="box-body">
				    <ul class='timeline search-body'></ul>
				</div>
			    </div>
			</div>
		    </div>
		    <div class="modal-footer">
			<button class="btn btn-danger" data-dismiss="modal" type="button"><i class="fa fa-close"></i> Cancel</button>
		    </div>
                </div>
            </div>
        </div>
	<button id="getyourposition" class="btn btn-default" style="margin-right:0.5%;background-color: #fff;" data-toggle="tooltip" title="Get Your Position"><i class="fa fa-map-marker"></i></button>
	<button id="contentinfo" style="display:none;" data-for="showinfocontent" class="addreport"></button>
	<button id="reportinfo" style="display:none;" data-for="inforeport" class="addreport"></button>
	<input type="hidden" id="akses" value="public">
	<input type="hidden" id="yourposition">
	
	<div id="placeReport" class="col-md-4" style="background: white; opacity: 0.9;">
            <div class="row" id="reportinfocontent" style="margin-top: 10px;">
		<div class="col-md-12">
		   
                    <div class="form-group" id="placereportcontent" style="overflow-x: hidden;">
			<ul class="timeline" id="dangerInfoPlace">
                                                     
                        </ul>
                        <ul class="timeline" id="aroundInfoPlace">
                                                                 
                        </ul>
		    </div>
		</div>
	    </div>
	    <div class="row">
                <button type="button" class="btn btn-flat btn-sm btn-primary form-control" id="showReportInfo">Sembunyikan</button>
	    </div>
	</div>
    </body>
</html>
