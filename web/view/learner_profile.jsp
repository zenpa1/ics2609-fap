<!-- Learner's Profile -->
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Learner's Profile</title>
    </head>
    <body class="site_layout">
        <nav class="site_layout_navbar">
            <div class="nav_profile">
                <a class="nav_item" href="${pageContext.request.contextPath}/ProfileServlet?action=learner">Username</a>
            </div>
            <div class="nav_list">
                <a class="nav_item" href="${pageContext.request.contextPath}/LearnerCourseServlet">Dashboard</a>
                <a class="nav_item" href="${pageContext.request.contextPath}/LearnerCourseServlet?action=viewAvailable">Courses</a>
                <a class="nav_item" href="${pageContext.request.contextPath}/ScheduleServlet?action=learner">Schedule</a>
            </div>
            <div class="nav_logout">
                <a class="nav_item"  href="${pageContext.request.contextPath}/view/learner_logout.jsp">Logout</a>
            </div>
        </nav>
        <div class="site_layout_body"> 
            <div class="profile_layout">
                <div class="profile_account">
                    <div class="profile_account_left">
                        <!-- Profile Image Here -->
                    </div>
                    <div class="profile_account_right">
                        <h1>Username</h1>
                        <p>Learner</p>
                    </div>
                </div>
                <h2>Currently Enrolled To: </h2>
                <div class="profile_courses"> <!-- Insert Enrolled Courses -->
                    <a>
                        <div class="profile_courses_box"> </div>
                    </a>
                    <a>        
                <%
                    List<String> enrolledCourses = (List<String>)request.getAttribute("enrolledCourses");
                    if (enrolledCourses != null && !enrolledCourses.isEmpty()) {
                %>
                        <ul>
                        <% for (String course : enrolledCourses) { %>
                            <li><%= course %></li>
                        <% } %>
                        </ul>
                <%
                    } else {
                %>
                        <p>You are not enrolled in any courses yet.</p>
                <%
                    }
                %>  
                        <div class="profile_courses_box"> </div>
                    </a>
                </div>
                <button>Download Account Details</button> <!-- Insert Download Account Details Functionality Here -->
            </div>
        </div>
    </body>
</html>
