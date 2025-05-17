<!-- Learner's Schedule -->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Learner's Schedule</title>
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
            <div class="schedule_layout">
                <div class="site_body_header_horizontal">
                    <div class="site_body_header_left">
                        <h1>Schedule</h1>
                    </div>
                    <div class="site_body_header_right">
                        <button>Download Schedule</button> <!-- Insert Download Schedule Functionality Here -->
                    </div>
                </div>
                <div class="schedule_main">
                    <table class="schedule_table">
                        <tr>
                            <th>Monday</th>
                            <th>Tuesday</th>
                            <th>Wednesday</th>
                            <th>Thursday</th>
                            <th>Friday</th>
                            <th>Saturday</th>
                        </tr>
                        <tr>
                            <!-- Insert Schedule Here -->
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
