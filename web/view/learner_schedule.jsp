<%@page import="java.util.Map"%>
<!-- Learner's Schedule -->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Learner's Schedule</title>
    </head>
    <body>
        <nav>
            <div>
                <a href="learner_profile.jsp">${sessionScope.username}</a>
            </div>
            <div>
                <a href="${pageContext.request.contextPath}/LearnerCourseServlet">Dashboard</a>
                <a href="${pageContext.request.contextPath}/LearnerCourseServlet?action=viewAvailable">Courses</a>
                <a href="${pageContext.request.contextPath}/ScheduleServlet?action=learner">Schedule</a>
            </div>
            <div>
                <a href="${pageContext.request.contextPath}/view/learner_logout.jsp">Logout</a>
            </div>
        </nav>
        <div> 
            <div>
                <div>
                    <div>
                        <h1>Schedule</h1>
                    </div>
                    <div>
                        <button>Download Schedule</button>
                    </div>
                </div>
                <div>
                    <table border="1">
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
