<!-- Learner's Profile -->

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
                        <div class="profile_courses_box"> </div>
                    </a>
                </div>
                <button>Download Account Details</button> <!-- Insert Download Account Details Functionality Here -->
            </div>
        </div>
    </body>
</html>
