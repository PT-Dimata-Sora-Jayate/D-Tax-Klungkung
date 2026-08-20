<%-- 
    Document   : bi-home
    Created on : Oct 30, 2015, 9:24:55 AM
    Author     : Ardiadi
--%>
<!DOCTYPE html>
<%@include file="../main/javainit_bi.jsp" %>
<%@ include file = "../main/checkuser_bi.jsp" %>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
        <title>Business Intellegence</title>
	<%@include file="bi-css.jsp" %>
	<%@include file="bi-jquery-angular.jsp" %>
    </head>
    <body ng-app="bi" class="main-theme fixed">
	<header class="header" ng-include src="'bi-header.jsp'">
	    <!-- header content -->
	</header>
	<div class="wrapper row-offcanvas row-offcanvas-left">
	    <!-- Left Side Column. Contains Sidebar -->
	    <aside class="hidden-xs leftmenu sidebar-offcanvas" ng-include src="'bi-sidebar.jsp'">
		<!-- side bar content -->
	    </aside>
	    <!-- End of Left Side Column. Contains Sidebar -->
	    <!-- Right side column. Contains the navbar and content of the page -->
	    <aside class="right-side">
		<!-- MAIN CONTENT -->
		<section class="content">

		    <div style="position:relative">
			<div style="width:100%" ng-view ng-animate="{enter: 'view-enter', leave: 'view-leave'}">
			</div>

		    </div>
		    <!-- ./Modal profile pop up -->
		    <div ng-include src="'views/profile.html'"></div>

		</section>
		<!-- ./MAIN CONTENT -->
		<footer id="footer" ng-include src="'bi-footer.jsp'">
		    <!-- footer content -->
		</footer>
	    </aside>
	    <!-- /.right-side -->
	</div>
	<div class="col-md-4 notifications bottom-right" id="notifications"></div>
	<input type="hidden" name="FRM_FIELD_LANGUAGE" id="FRM_FIELD_LANGUAGE" value="<%= SESS_LANGUAGE %>">
	<!-- ./wrapper -->
	
	<!-- PLUGIN -->
	<%@include file="bi-bootstrap-chart.jsp" %>
    </body>
</html>
