<%-- 
    Document   : menu_left_mobile_student
    Created on : Mar 11, 2015, 9:43:53 PM
    Author     : dimata005
--%>

<%-- 
    Document   : menu_left_mobile
    Created on : Aug 8, 2014, 2:06:00 PM
    Author     : dimata005
--%>

<aside class="left-side sidebar-offcanvas">
<!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <div class="user-panel">
            <div class="pull-left image">
                <img src="<%=approot%>/styles/bootstrap3.1/img/avatar3.png" class="img-circle" alt="User Image" />
            </div>
            <div class="pull-left info">
                <p>Hello, <%=userName%></p>
            </div>
        </div>
         <ul class="sidebar-menu">
                <li class="active">
                    <a href="<%=approot%>/admission/student/student_app.jsp">
                        <span>Student Applicant</span>
                    </a>
                </li>
                <li class="active">
                     <a href="<%=approot%>/admission/student/studentinformation.jsp">
                        <span>Student Information</span>
                    </a>
                </li>
                 <li class="active">
                   <a href="<%=approot%>/admission/student/studentidcard.jsp">
                       <span>Student Card</span>
                    </a>
                </li>
                <li class="active">
                    <a href="<%=approot%>/admission/student/studentmovement.jsp">
                        <span>Student Movement</span>
                    </a>
                </li>
                <li class="active">
                    <a href="<%=approot%>/admission/student/studentsearch.jsp">
                        <span>Search</span>
                    </a>
                </li>
            </ul>
    </section>
<!-- /.sidebar -->
</aside>
