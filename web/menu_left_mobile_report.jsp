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
                    <a href="<%=approot%>/admission/report/student.jsp">
                        <span>Student</span>
                    </a>
                </li>
                <li class="active">
                     <a href="<%=approot%>/admission/report/admission.jsp">
                        <span>Admission</span>
                    </a>
                </li>
                 <li class="active">
                   <a href="<%=approot%>/admission/report/transaction.jsp">
                       <span>Transaction</span>
                    </a>
                </li>
                <li class="active">
                    <a href="<%=approot%>/admission/report/payment.jsp">
                        <span>Payment</span>
                    </a>
                </li>
            </ul>
    </section>
<!-- /.sidebar -->
</aside>
