<!-- Instructor's Database -->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List,java.util.Map"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instructor's Database</title>
    </head>
    <body>
        <nav>
            <div>
                <a href="instructor_profile.jsp">Username</a>
            </div>
            <div>
                <a href="instructor_dashboard.jsp">Dashboard</a>
                <a href="${pageContext.request.contextPath}/DatabaseServlet">Learner's Database</a>
                <a href="${pageContext.request.contextPath}/ScheduleServlet?action=instructor">Schedule</a>
            </div>
            <div>
                <a href="${pageContext.request.contextPath}/view/instructor_logout.jsp">Logout</a>
            </div>
        </nav>
        <div> 
            <div>
                <div>
                    <div>
                        <h1>Account Database</h1>
                    </div>
                    <div>
                        <button>Download Account Report</button>
                    </div>
                </div>
                <div> 
                    <table border="1">
                        <tr>
                            <th>Username</th>
                            <th>Role</th>
                        </tr>
                        <%
                            List<Map<String, String>> userList = (List<Map<String, String>>) request.getAttribute("userList");
                            if (userList != null) {
                                for (Map<String, String> user : userList) {
                        %>
                        <tr>
                            <td><%= user.get("username") %></td>
                            <td><%= user.get("role") %></td>
                        </tr>
                        <%
                                }
                            } else {
                        %>
                        <tr>
                            <td colspan="2">No user data available</td>
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