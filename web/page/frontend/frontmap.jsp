
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
    long oidChanel = FRMQueryString.requestLong(request, FrmChanel.fieldNames[FrmChanel.FRM_FIELD_CHANEL_TYPE_ID]);
    
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
    
    Vector listArea = PstAreaList.list(0, 0, whereAreaList, "");   
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
    <%@include file="../../template-component/plugins-component.jsp" %>
    <script>
        var oidSelected;
        var map;
	var smallMap;
	var smallMarker;
        var navigator;
	var markerPosition;
	var markersArray = [];
        var oid = [
            <%
            if (listArea.size()>0){
                for (int a = 0;a<listArea.size();a++){
                    AreaList areaList = (AreaList)listArea.get(a);                  
                    out.println("\""+areaList.getOID()+"\"");
                    if (a!=listArea.size()-1){
                        out.print(",");
                    }
                }
            }
            %>
        ];
        var fillColor = [
            <%
            if (listArea.size()>0){
                for (int a = 0;a<listArea.size();a++){
                    AreaList areaList = (AreaList)listArea.get(a);
                    AreaType areaType = new AreaType();
                   

                    try {     
                        areaType = PstAreaType.fetchExc(areaList.getAreaListTypeId());
                    }catch(Exception ex){                   
                    }
                    
                   out.println("\""+areaType.getAreaTypeColor()+"\"");
                   if (a!=listArea.size()-1){
                       out.print(",");
                   }
                }
            }
            %>
        ];
        var editedPolygons =[
            <%
            if (listArea.size()>0){
                for (int i = 0;i<listArea.size();i++){
                    AreaList areaList = (AreaList)listArea.get(i);
                    AreaType areaType = new AreaType();
                    AreaUsage areaUsage =new AreaUsage();
                    
                    try {     
                        areaUsage = PstAreaUsage.fetchExc(areaList.getAreaListUsageId());
                        areaType = PstAreaType.fetchExc(areaList.getAreaListTypeId());
                    }catch(Exception ex){                   
                    }
                    
                    out.println("[");

                    String path = areaList.getAreaListPath();
                    String pathArr[] = path.split(",");
                    
                    for (int j= 0;j<pathArr.length;j++){
                        if (pathArr[j].length()>0 ){
                            if (j==0){
                                out.println("{");
                                out.println("\"lat\":"+Double.parseDouble(pathArr[j])+",");
                            }else{
                                if (j%2!=0){
                                    out.println("\"lng\":"+Double.parseDouble(pathArr[j])+"");
                                    if (j==pathArr.length-1){
                                        out.println("}");
                                    }else{
                                        out.println("},");
                                    }
                                }else{
                                    out.println("{");
                                    out.println("\"lat\":"+Double.parseDouble(pathArr[j])+",");
                                }
                                
                            }
                            
                        }
                        
                    }
                    
                    if (i==listArea.size()-1){
                        out.println("]");
                    }else{
                        out.println("],");
                    }
                        
                }
            }           
        %>       
        ];
        
        function initMap() {
            var initialLocation;
            navigator.geolocation.getCurrentPosition(function(position) {          
                initialLocation = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
                map.setCenter(initialLocation);
		smallMap.setCenter(initialLocation);
		smallMarker.setPosition(initialLocation);
                marker.setPosition(initialLocation);
            });


            map = new google.maps.Map(document.getElementById('map'), { //var
                zoom: 18,//10,
                mapTypeId: google.maps.MapTypeId.SATELLITE,
		disableDefaultUI: true,
		scrollwheel: false,
		draggable : false

            });
            
	    smallMap = new google.maps.Map(document.getElementById('smallmap'), { //var
                zoom: 13,//10,
                mapTypeId: google.maps.MapTypeId.ROADMAP,
		disableDefaultUI: true,
		scrollwheel: true,
		draggable : true

            });
	    
            var addListenersOnPolygon = function(polygon) {
                google.maps.event.addListener(polygon, 'click', function (event) {
                    oidSelected = polygon.index;
                    document.getElementById('tempbutton').click();
                });  
            }
            
            
            for (var i = 0; i < editedPolygons.length; i++) {
                var poly = new google.maps.Polygon({
                    path: editedPolygons[i],
                    strokeColor: fillColor[i],
                    fillColor : fillColor[i],
                    map: smallMap,
                    index : oid[i]

                });
                addListenersOnPolygon(poly);
            }
	    
	    smallMarker = new google.maps.Marker({
		map: smallMap,
		draggable : true
	    });
	    marker = new google.maps.Marker({
		map: map
	    });
	     // add a click event handler to the map object
            google.maps.event.addListener(smallMap, "click", function(event){
                // place a marker
                placeMarker(event.latLng);
            });
	    
	    google.maps.event.addListener(smallMarker, 'dragend', function(evt){
		map.setCenter(evt.latLng);
	    });
            
            google.maps.event.addListener(smallMarker, 'click', function(evt){
		document.getElementById('tempbutton2').click();
		markerPosition = this.position;
	    });
	    
	    
        }
	
	function placeMarker(location) {
	    
            smallMarker.setPosition(location);
	    map.setCenter(location);
        }
        $(document).ready(function(){
            
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
            var listfiles= null;
            var listfilesbytype= null;
            var imageZoom = null;
            var listfiles2 = null;
            
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
            
            addeditgeneral = function(elementId){
		$(elementId).click(function(){ 
                    $("#savegeneral").hide();
                    $("#btngeneralform").show();
                    $("#addeditgeneral").find(".modal-dialog").addClass("modal-sm");
		    $("#addeditgeneral").modal("show");
		    command = $("#command").val();
		    oid = oidSelected;
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
			"command"		 : command
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
		    $("#addeditgeneral").modal("show");
                    $("#savegeneral").show();
                    $("#btngeneralform").hide();
		    command = $("#command").val();
		    if(oidSelected != null || oidSelected != ""){
			oid = 0;//oidSelected;
		    }else{
			oid = 0;
		    }
		    
		    
		    dataFor = $(this).data('for');
		    $("#oid").val(oid);
		    $("#ltlang").val(markerPosition);
		    
		    $("#generaldatafor").val("takepicture2");
		    //SET TITLE MODAL
		    if(oid != 0){
			if(dataFor == 'showdetailinfo'){
			    $(".addeditgeneral-title").html("Detail Information");
			}else if(dataFor == 'takepicture'){
			    $(".addeditgeneral-title").html("Upload File");
			    $("#addeditgeneral").find(".modal-dialog").addClass("modal-lg");
			    onDone = function(data){
				takePicture();
				snapPicture();
			    }
			}

		    }else{
			if(dataFor == 'showareausageform'){
			    $(".addeditgeneral-title").html("Detail Information");
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
			"FRM_FIELD_DATA_FOR"	: "takepicture2",
			"FRM_FIELD_OID"		 : oid,
			"command"		 : command
		    }
		    
		    onSuccess = function(data){
			
		    };
                    
		    getDataFunction(onDone, onSuccess, approot, command, dataSend, "AjaxAreaList", ".addeditgeneral-body", false, "json");
		});
	    };
            
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
		    // Capture user's audio and video source
		    $("#takepicture").hide();
		    $("#videoarea").fadeIn("medium");
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
		    // Pause the video
		    video.pause();
		    // Stop the stream
		    videoStream.stop();
		  });
		  
		}
	   };
	   
	   
	   function snapPicture(){
		document.getElementById('capture').addEventListener('click', function() {
		   
		   var getHeight = $("#video").height();
		   var getWidth = $("#video").width();
		   
		   
		   
		   var c1Canvas=document.getElementById("picturecaptured");
		   var ctx1=c1Canvas.getContext('2d');
		   c1Canvas.width = getWidth;
		   c1Canvas.height = getHeight;
		   ctx1.drawImage(video, 0, 0, getWidth, getHeight);
		   $("#imgString").val(c1Canvas.toDataURL());
		});
	   };
            
            
            listfiles = function(elementId){
		$(elementId).click(function(){                  
		    $("#files").modal("show");
		    command = <%= Command.NONE%>;
		    oid = oidSelected;
		    dataFor = $(this).data('for');
		    $("#oid").val(oid);
                    
                    dataSend = {
			"FRM_FIELD_DATA_FOR"	: dataFor,
			"FRM_FIELD_OID"		 : oid,
			"command"		 : command
		    }
		    onDone = function(data){
			loadListDetailFiles(1);
		    };
		    onSuccess = function(data){
			
		    };
                    
                    getDataFunction(onDone, onSuccess, approot, command, dataSend, "AjaxShowAreaList", ".files-body", false, "json");
		});
	    };
            
            listfiles2 = function(elementId){
		$(elementId).click(function(){                  		   
		    command = <%= Command.NONE%>;
		    oid = oidSelected;
		    dataFor = $(this).data('for');
		    $("#oid").val(oid);
                    
                    dataSend = {
			"FRM_FIELD_DATA_FOR"	: dataFor,
			"FRM_FIELD_OID"		 : oid,
			"command"		 : command
		    }
		    onDone = function(data){
			loadListDetailFiles(1);
		    };
		    onSuccess = function(data){
			
		    };
                    
                    getDataFunction(onDone, onSuccess, approot, command, dataSend, "AjaxShowAreaList", ".files-body", false, "json");
		});
	    };
            
            
            imageZoom = function(elementId){
		$(elementId).click(function(){   
                    //alert("test");
                    command = <%= Command.NONE%>;
		    oid = $(this).data('oid');
		    dataFor = $(this).data('for');
                    dataSend = {
			"FRM_FIELD_DATA_FOR"	 : dataFor,
			"FRM_FIELD_OID"		 : oid,
			"command"		 : command,
                        "approot"                : approot
		    }
		    onDone = function(data){
			listfiles2('.backtolist');
		    };
		    onSuccess = function(data){
			
		    };
                    
                    getDataFunction(onDone, onSuccess, approot, command, dataSend, "AjaxShowAreaList", ".files-body", false, "json");
		});
	    };
            
            function loadListDetailFiles(groupType){
                command = <%= Command.NONE%>;
                oid = oidSelected;
                dataFor = "showattachmentdetail";
                $("#oid").val(oid);

                dataSend = {
                    "FRM_FIELD_DATA_FOR": dataFor,
                    "FRM_FIELD_OID"     : oid,
                    "command"		: command,
                    "<%=FrmDataUploadGroup.fieldNames[FrmDataUploadGroup.FRM_FIELD_DATA_GROUP_TIPE]%>"		: groupType,
                    "approot"           : approot
                }
                
                onDone = function(data){
                    listfilesbytype(".grouptipes");
                    imageZoom(".fileimage");
                };
                onSuccess = function(data){

                };
                
                getDataFunction(onDone, onSuccess, approot, command, dataSend, "AjaxShowAreaList", ".detailattachplace", false, "json");
            }
            
            listfilesbytype = function(elementId){
		$(elementId).click(function(){                  
		    var tipe = $(this).data('tipe');
                    if (tipe==0){
                        $("#filetipe").addClass('active');
                        $("#icontipe").removeClass('active');
                    }else{
                        $("#filetipe").removeClass('active');
                        $("#icontipe").addClass('active');
                    }
                    
                    loadListDetailFiles(tipe);
                    
		});
	    };
            

            addeditgeneral("#tempbutton");
            addeditgeneral2("#tempbutton2");
            
            function login(){
                $("#login").click(function(){
                    window.location = "<%=approot%>/page/frontend/login.jsp";
                });
            }
            
            login();
            listfiles("#btngeneralform");
	    
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
		    "FRM_FIELD_APPROOT" : approot
		}
		
		getDataFunction(onDone, onSuccess, approot, command, dataSend, "AjaxFrontEnd", replaceTo, false, "json");
	    });
            
	    $("form#generalform").submit(function(){
		
		if(videoStream !== null){
		    videoStream.stop();
		}
		var currentBtnHtml = $("#btngeneralform").html();
		$("#btngeneralform").html("Saving...").attr({"disabled":"true"});
		var generaldatafor = $("#generaldatafor").val();
		if(generaldatafor == "showmap"){
		    onDone = function(data){
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
        });

    </script>
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCVHaChwTJ1045ZRf57k4waY28m7M7mXjQ&callback=initMap">
    </script>
    </head> 
    <body class="<%= skin %>">
        <nav class="navbar navbar-static-top" id="head-front" role="navigation">
            <div class="navbar-custom-menu">
                <form action="<%=approot%>/page/frontend/frontmap.jsp" method="POST">
                <div class="col-xs-4 pull-left" style="margin-top:8px; color: #fff;font-size: 16px;">
                    <strong>Di-SmartCity </strong>
                </div>
                <div class="col-xs-8 pull-right" style="margin-top:8px;">
		    <div class="col-xs-2">
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
                    <div class="col-xs-2">
                        <%= ControlCombo.draw(FrmAreaType.fieldNames[FrmAreaType.FRM_FIELD_AREA_TYPE_ID], "-- All --", ""+oidAreaType, areaTypeId, areaTypeName, "data-for='showcategory' data-replace-to='#"+FrmAreaUsage.fieldNames[FrmAreaUsage.FRM_FIELD_AREA_USAGE_ID]+"' style='height:30px;'", "form-control controlcombo") %>                                            
                    </div>
                    <div class="col-xs-2">
                        <%= ControlCombo.draw(FrmAreaUsage.fieldNames[FrmAreaUsage.FRM_FIELD_AREA_USAGE_ID], "-- All --", ""+oidAreaUsage, areaUsageId, areaUsageName, "style='height:30px;'", "form-control") %> 
                    </div>
                    <div class="col-xs-6">
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
            <strong>Copyright © 2016 <a href="http://dimata.com">PT. Dimata Sora Jayate-Denpasar.</a></strong>
            <div class="pull-right hidden-xs">
                <b>Version</b> 1.0
            </div>
        </div>
        <div id="addeditgeneral" class="modal fade nonprint" tabindex="-1">
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
			<input type="hidden" name="<%= FrmReport.fieldNames[FrmReport.FRM_FIELD_LOCATION] %>" id="ltlang">
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
                            <button type="submit" class="btn btn-primary cancelcapture" id="savegeneral"><i class="fa fa-check"></i> Save</button>
                            <button type="button" data-dismiss="modal" class="btn btn-danger cancelcapture"><i class="fa fa-ban"></i> Close</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div id="files" class="modal fade nonprint" tabindex="-1">
            <div class="modal-dialog nonprint ">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="files-title"><i class="fa fa-tag"></i> Supporting Data</h4>
                    </div>                   
                    <form id="generalform2">
                        <input type="hidden" name="FRM_FIELD_DATA_FOR" id="generaldatafor">
                        <input type="hidden" name="command" value="<%= Command.SAVE %>">
                        <input type="hidden" name="FRM_FIELD_OID" id="oids">
                        <div class="modal-body ">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="box-body files-body">
      
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">                                                    
                            <button type="button" data-dismiss="modal" class="btn btn-danger"><i class="fa fa-ban"></i> Close</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        
    </body>
</html>
