<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
      <div class="container"> 
        <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span> 
        </a>
        <a class="brand" href="<%= request.getContextPath() %>/wppbbonline/wp-pbb-online-home.jsp">Wajib Pajak Online</a>
        <!--Sign out -->
        <div class="nav-collapse">
            <ul class="nav pull-right">
                <li>						
                        <a href="<%= request.getContextPath() %>/wppbbonline/wp-pbb-online-logout.jsp">
                                <i class="icon-signout"></i>
                                Keluar
                        </a>			
                </li>

            </ul>
        </div>
      </div>
    </div>
</div>
