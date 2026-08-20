<%-- 
    Document   : wp_ajax_data_source
    Created on : Sep 6, 2015, 4:55:24 PM
    Author     : dimata005
--%>
<%@page import="com.dimata.wpupload.entity.wpuser.PstAppUserWP"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<%
    int pageShow = FRMQueryString.requestInt(request, "pageShow");
    String userId = FRMQueryString.requestString(request, "user");
    switch(pageShow){
	case 1:
            %>
            <div class="widget widget-nopad">
                <div class="widget-header"> 
                    <i class="icon-home"></i>
                  <h3>Mengganti Password</h3>
                </div>
                <input type="hidden" name="userNameWp" value="<%=userId%>" >
                <div class="widget-content" id="resultPassword">
                    <div class="widget big-stats-container">
                    <div class="widget-content">
                        <div class="row">
                            <div class="span12">&nbsp;</div>
                        </div>
                        <div class="row">
                            <div class="span12">
                                    <div class="span2">
                                        <b>Password Saat Ini</b>
                                    </div>
                                    <div class="span3">
                                        : <input type="password" name="currentpassword" value="" class="form-control" required>
                                    </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="span12">
                                    <div class="span2">
                                        <b>Password Baru</b>
                                    </div>
                                    <div class="span3">
                                        : <input type="password" name="newpassword" id="pass1" value="" class="form-control" required>
                                    </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="span12">
                                    <div class="span2">
                                        <b>Verifikasi Password Baru</b>
                                    </div>
                                    <div class="span3">
                                        : <input type="password" name="verifiypassword" id="pass2" value="" class="form-control" required onkeyup="checkPass(); return false;">
                                        <span id="confirmMessage" class="confirmMessage"></span>
                                    </div>
                            </div>
                        </div>
                    </div>
                  </div>
                </div>
              </div>
            <%
            break;
        default:
            break;
    }        
%>
