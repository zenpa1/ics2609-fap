<!-- Instructor's Profile -->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instructor's Profile</title>
    </head>
    <body class="site_layout">
        <nav class="site_layout_navbar">
            <div class="nav_profile">
                <a class="nav_item" href="instructor_profile.jsp">Username</a>
            </div>
            <div class="nav_list">
                <a class="nav_item" href="instructor_dashboard.jsp">Dashboard</a>
                <a class="nav_item" href="instructor_database.jsp">Learner's Database</a>
                <a class="nav_item" href="instructor_schedule.jsp">Schedule</a>
            </div>
            <div class="nav_logout">
                <a class="nav_item" href="instructor_logout.jsp">Logout</a>
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
                        <p>Instructor</p>
                    </div>
                </div>
                <h2>Currently Instructing: </h2>
                <div class="profile_courses"> <!-- Insert Instructing Courses -->
                    <a>
                        <div class="profile_courses_box"> </div>
                    </a>
                    <a>
                        <div class="profile_courses_box"> </div>
                    </a>
                </div>
                <button>Download Account Details</button> <!-- Insert Download Account Details Functionality Here -->
            </div>
        </div>
    </body>
</html>
