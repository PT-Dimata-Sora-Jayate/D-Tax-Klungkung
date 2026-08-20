<%@page import="com.dimata.dslik.entity.admin.AppObjInfo"%>
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
          <center><img class="logos" src="<%= approot %>/imgcompany/dimata_system_logo.png" width="120px"></center>
      </div>
      <hr>
      <!-- /.search form -->
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu">
            <li class="treeview" id="home">
                <a href="<%= approot %>/home_tapping.jsp">
                <i class="fa fa-home"></i> <span>Home</span>
              </a>
            </li>
            <li class="treeview" id="periode">
                <a href="#">
                  <i class="fa fa-th-list"></i>
                  <span>Setting Parameter</span>
                  <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li id="periodedata"><a href="#"><i class="fa fa-circle-o"></i>Setting Parameter Interface</a></li>
                    <li id="periodedata"><a href="#"><i class="fa fa-circle-o"></i>Download Otomatis</a></li>
                    <li id="periodedata"><a href="#"><i class="fa fa-circle-o"></i>Download Manual Data </a></li>
                </ul>
            </li>
            <li class="treeview" id="periode">
                <a href="#">
                  <i class="fa fa-th-list"></i>
                  <span>Report</span>
                  <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li id="periodedata"><a href="#"><i class="fa fa-circle-o"></i>Rekap Tapping Data</a></li>
                    <li id="periodedata"><a href="#"><i class="fa fa-circle-o"></i>Tapping Data Detail</a></li>
                    <li id="periodedata"><a href="#"><i class="fa fa-circle-o"></i>Rekap Tapping Data Per Jenis</a></li>
                </ul>
            </li>
      </ul>
                
    </section>
    <!-- /.sidebar -->
</aside>