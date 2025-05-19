<%@page import="java.util.Map"%>
<!-- Learner's Dashboard -->
<%@page import="java.util.List"%>
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
        <title>Learner's Dashboard</title>
    </head>
    <body class="site_layout">
        <nav class="site_layout_navbar">
            <div class="nav_profile">
                <img src="https://activelearning.ph/wp-content/uploads/2021/03/logo-white.png" class="logo-nav" alt="ActiveLearningPH">
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
            <div class="learner_dashboard_layout">
                <div class="site_body_header_vertical">
                    <h1>My Enrolled Courses</h1>
                </div>
                <div class="learner_dashboard_main">
                    <%
                        List<Map<String, String>> availableCourses = (List<Map<String, String>>) request.getAttribute("enrolledCourses");
                        if (availableCourses != null && !availableCourses.isEmpty()) {
                    %>


                    <%for (Map<String, String> course : availableCourses) {%>

                    <div class="courses_box">
                            <h3><%= course.get("name")%></h3>
                            <p><%= course.get("instructor")%></p>
                            <p><%= course.get("schedule")%></p>
                      
                    </div>   



        <%
            }
            } else {
        %>
                <p>You are not enrolled in any courses yet.</p>
        <%
            }
        %>
                </div>
            </div>
        </div>
    </body>
</html>