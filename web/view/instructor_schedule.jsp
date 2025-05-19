<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<!-- Instructor's Schedule -->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&family=Suez+One&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/css/styles.css" />
        <title>Instructor's Schedule</title>
    </head>
    <body class="site_layout">
        <nav class="site_layout_navbar">
            <div class="nav_profile">
                <img src="https://activelearning.ph/wp-content/uploads/2021/03/logo-white.png" class="logo-nav" alt="ActiveLearningPH">
                <a class="nav_item" href="instructor_profile.jsp">Username</a>
            </div>
            <div class="nav_list">
                <a class="nav_item" href="${pageContext.request.contextPath}/view/instructor_dashboard.jsp">Dashboard</a>
                <a class="nav_item" href="${pageContext.request.contextPath}/DatabaseServlet">Learner's Database</a>
                <a class="nav_item" href="${pageContext.request.contextPath}/ScheduleServlet?action=instructor">Schedule</a>
            </div>
            <div class="nav_logout">
                <a class="nav_item" href="${pageContext.request.contextPath}/view/instructor_logout.jsp">Logout</a>
            </div>
        </nav>
        <div class="site_layout_body"> 
            <div class="schedule_layout">
                <div class="site_body_header_horizontal">
                    <div class="site_body_header_left">
                        <h1>Schedule</h1>
                    </div>
                    <div class="site_body_header_right">
                        <button class="btn-site-body-header">Download Schedule</button> <!-- Insert Download Schedule Functionality Here -->
                    </div>
                </div>
                <div class="schedule_main">
                    <table class="schedule_table">
                        <tr>
                            <th>Course Name</th>
                            <th>Instructor</th>
                            <th>Schedule</th>
                        </tr>
                        <%
                            List<Map<String, String>> courseList = (List<Map<String, String>>) request.getAttribute("courseList");
                            if (courseList != null && !courseList.isEmpty()) {
                                for (Map<String, String> course : courseList) {
                        %>
                        <tr>
                            <td><%= course.get("name")%></td>
                            <td><%= course.get("instructor")%></td>
                            <td><%= course.get("schedule")%></td>
                        </tr>
                        <%
                            }
                        } else {
                        %>
                        <tr>
                            <td colspan="3">No courses scheduled</td>
                        </tr>
                        <%
                            }
                        %>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
