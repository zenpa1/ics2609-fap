<!-- Instructor Dashboard -->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instructor's Dashboard</title>
    </head>
    <body class="site_layout">
        <nav class="site_layout_navbar">
            <div class="nav_profile">
                <a class="nav_item" href="instructor_profile.jsp">Username</a>
            </div>
            <div class="nav_list">
                <a class="nav_item" href="instructor_dashboard.jsp">Dashboard</a>
                <a class="nav_item" href="${pageContext.request.contextPath}/DatabaseServlet">Learner's Database</a>
                <a class="nav_item" href="${pageContext.request.contextPath}/ScheduleServlet?action=instructor">Schedule</a>
            </div>
            <div class="nav_logout">
                <a class="nav_item" href="${pageContext.request.contextPath}/view/instructor_logout.jsp">Logout</a>
            </div>
        </nav>
        <div class="site_layout_body"> 
            <div class="instructor_dashboard_layout">
                <div class="site_body_header_horizontal">
                    <button>Create Course</button>
                    <button>Delete Course</button>
                </div>
                <div class="instructor_dashboard_main"> <!-- Insert CRUD BOXES here -->
                    <a>
                        <div class="courses_box"> 
                            <button>Update Course</button>
                        </div>
                    </a>
                    <a>
                        <div class="courses_box">
                            <button>Update Course</button>
                        </div>
                    </a>
                </div>
            </div>
        </div>
    </body>
</html>
