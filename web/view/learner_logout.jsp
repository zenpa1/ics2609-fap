<!-- Learner's Logout Page-->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logout</title>
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
            <div class="site_logout_layout">
                <h1>Logout</h1>
                <h2>Are you sure you want to logout?</h2>
                <form action="${pageContext.request.contextPath}/index.jsp">
                <button>Confirm Logout</button> <!-- LOGOUT BUTTON -->
                </form>
            </div>
        </div>
    </body>
</html>
