<!-- Instructor's Database -->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instructor's Database</title>
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
            <div class="instructor_database_layout">
                <div class="site_body_header_horizontal">
                    <div class="site_body_header_left">
                        <h1>Account Database</h1>
                    </div>
                    <div class="site_body_header_right">
                        <button>Download Account Report</button> <!-- Insert Download Account Report Functionality Here -->
                    </div>
                </div>
                <div class="instructor_database_main"> 
                    <table class="instructor_database_table"> <!-- Insert Database Table Here -->
                        <tr>
                            <td>Username</td>
                            <td>Role</td>
                        </tr>
                        <tr>
                            <td>Username</td>
                            <td>Role</td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
