<!-- Learner's Dashboard -->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Learner's Dashboard</title>
    </head>
    <body class="site_layout">
        <nav class="site_layout_navbar">
            <div class="nav_profile">
                <a class="nav_item" href="learner_profile.jsp">Username</a>
            </div>
            <div class="nav_list">
                <a class="nav_item" href="learner_dashboard.jsp">Dashboard</a>
                <a class="nav_item" href="learner_courses.jsp">Courses</a>
                <a class="nav_item" href="learner_schedule.jsp">Schedule</a>
            </div>
            <div class="nav_logout">
                <a class="nav_item"  href="learner_logout.jsp">Logout</a>
            </div>
        </nav>
        <div class="site_layout_body"> 
            <div class="learner_dashboard_layout">
                <div class="learner_dashboard_main">
                    <a><div class="courses_box"></div></a>
                    <a><div class="courses_box"></div></a>
                </div>
            </div>
        </div>
    </body>
</html>
