
<%@page import="com.dimata.balismartisland.entity.dismartcityadmin.AppUser"%>
<%@page import="com.dimata.balismartisland.session.SessUserSession"%>
<%@page import="java.util.Date"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<!DOCTYPE html>
<%@include file="../../main/javainit.jsp" %>

<%!
    final static int CMD_LOGIN = 1;
    final static int MAX_SESSION_IDLE=100000;
%>

<%
    int iCommand = Integer.parseInt((request.getParameter("command")==null) ? "0" : request.getParameter("command"));
    int typeView = FRMQueryString.requestInt(request, "typeView");
    String  noLog = FRMQueryString.requestString(request, "nodocument");
    int deviceUse = FRMQueryString.requestInt(request,"deviceuse");

    int dologin = 0;
    int dologinSales = 0;
    int appLanguage  =  0;
    String salesCode="";
    boolean dayAssign=false;
    Date dateNow = new Date();
    int strDate = Integer.parseInt(String.valueOf(dateNow.getDate()));
    int strhourNow = Integer.parseInt(String.valueOf(dateNow.getHours()));
    int opie=0;

    // Then get the day of week from the Date based on specific locale.
    int userGroup = -1;

    if(iCommand==CMD_LOGIN){
	String loginID = FRMQueryString.requestString(request,"login_id");
	String passwd  = FRMQueryString.requestString(request,"pass_wd");
	appLanguage  = FRMQueryString.requestInt(request,"app_language");

	String remoteIP = request.getRemoteAddr();
	SessUserSession userSess = new SessUserSession(remoteIP );
	dologin=userSess.doLogin(loginID, passwd);
	//dologinSales = userSess.doLoginSales(loginID, passwd);
	if(dologin==SessUserSession.DO_LOGIN_OK){
	    AppUser appUser = userSess.getAppUser();
	    dayAssign  = true;
	    //int strhour = Integer.parseInt(String.valueOf(appUser.getStartTime().getHours()));
	    //int endhour = Integer.parseInt(String.valueOf(appUser.getEndTime().getHours()));
	    //cek group
	    userGroup = appUser.getUserGroupNew();
	    //cek hari assign
	    if(dayAssign==true){
		session.setMaxInactiveInterval(MAX_SESSION_IDLE);
		session.putValue(SessUserSession.HTTP_SESSION_NAME, userSess);
		userSess = (SessUserSession) session.getValue(SessUserSession.HTTP_SESSION_NAME);

		session.putValue("APPLICATION_LANGUAGE", String.valueOf(appLanguage));
		session.putValue("APPLICATION_DEVICE_USE", String.valueOf(deviceUse));
		String strLang = "";
		String strDevUse="";

		if(session.getValue("APPLICATION_LANGUAGE")!=null){
		    strLang = String.valueOf(session.getValue("APPLICATION_LANGUAGE"));
		}

		if(session.getValue("APPLICATION_DEVICE_USE")!=null){
		    strDevUse = String.valueOf(session.getValue("APPLICATION_DEVICE_USE"));
		}

		appLanguage = (strLang!=null && strLang.length()>0) ? Integer.parseInt(strLang) : 0;

		deviceUse = (strDevUse!=null && strDevUse.length()>0) ? Integer.parseInt(strDevUse) : 0;

	    }else{

		dologin=SessUserSession.DO_LOGIN_NO_PRIV_ASIGNED;

	    }
	}
    }
%>
<html>
    <head>
    <meta charset="UTF-8">
    <title>Di-SMARTCITY | LOGIN</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <%@include file="../../template-component/css-component.jsp" %>
    <style>
        html { 
            background: url(../../styles/bootstrap/css/bg.png) no-repeat center center fixed; 
            -webkit-background-size: cover;
            -moz-background-size: cover;
            -o-background-size: cover;
            background-size: cover;  
            
        }
        
    </style>
    </head>
    <body style="background: none;">
        <div class="login-box" style="margin: 3% auto;">
            <div class="login-logo">
                <img class="logos" src="../../styles/bootstrap/css/logo4.png" width="200px;">
            </div><!-- /.login-logo -->
            <div class="login-box-body" style="border-radius:5px;background-color: #f1f1f1;box-shadow: 0px 5px 5px #ccc;">
	  
	  <h3 class="login-box-msg">USER LOGIN</h3>
          <form name="frmLogin" action="<%=approot%>/page/frontend/login.jsp" onsubmit="window.status=''" method="post">
	   <input type="hidden" name="command" value="<%=CMD_LOGIN%>">
	    <input type="hidden" name="typeView" value="<%=typeView%>">
	    <input type="hidden" name="nodocument" value="<%=noLog%>">
	    <%
		if((iCommand==CMD_LOGIN)&&(dologin == SessUserSession.DO_LOGIN_OK)){
		     response.sendRedirect(approot+"/monitor.jsp");
		 }
	    %>
	      <div class="form-group has-feedback">
		<input name="login_id" type="text" class="form-control" placeholder="User ID" required="True"/>
		<span class="glyphicon glyphicon-user form-control-feedback"></span>
	      </div>
	      <div class="form-group has-feedback">
		<input name="pass_wd" type="password" class="form-control" placeholder="Password" required="True"/>
		<span class="glyphicon glyphicon-lock form-control-feedback"></span>
	      </div>
	      <div class="form-group has-feedback">
		  
	      </div>
	      <div class="row">
		<div class="col-xs-12">
		    <button type="submit" class="btn btn-primary btn-block btn-flat" onclick="javascript:cmdLogin()">Sign In</button>
		</div><!-- /.col -->
	      </div>
	      <%
		if((iCommand==CMD_LOGIN) && (dologin != SessUserSession.DO_LOGIN_OK)||(iCommand==CMD_LOGIN)) {
		    %>
		    <div class="row">
			<div class="col-md-12">
			    <div class="label-danger">
				<i class="fa fa-exclamation"></i> 
				<%
				if(appLanguage==com.dimata.util.lang.I_Language.LANGUAGE_DEFAULT){
				    %>
				    nama atau password salah...
				    <%
				}else{
				    %>
				    username or password wrong, try again...
				    <%
				}
				%>
			    </div>
			</div>
		    </div>
		    <%
		}
	    %>
	    </form>
            <div class="margin text-center">
                <span>Copyright &copy; 2010 Dimata&reg; IT Solution<br>
                              Jl. Imam Bonjol, Perum Cipta Selaras no. 12, Denpasar 80119 - BALI.<br>
                              Telp. (0361) 499029, 7869752; Fax (0361) 499029<br>
                              Website : <a href="http://www.dimata.com" class="footerLink">www.dimata.com</a><br>
                              Email : <a href="mailto:marketing@dimata.com" class="footerLink">marketing@dimata.com</a></span>
            </div>
            <br>
      </div><!-- /.login-box-body -->
    </div>
    </body>
</html>

    
  