<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Instructor's Dashboard</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                padding: 2rem;
                background-color: #f9f9f9;
            }
            h2 {
                margin-bottom: 1rem;
            }
            .message {
                padding: 1rem;
                margin-bottom: 1rem;
                border-radius: 5px;
            }
            .success {
                background-color: #d4edda;
                color: #155724;
            }
            .error {
                background-color: #f8d7da;
                color: #721c24;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 2rem;
            }
            th, td {
                border: 1px solid #ccc;
                padding: 0.75rem;
                text-align: left;
            }
            th {
                background-color: #3498db;
                color: white;
            }
            input[type="text"], input[type="time"] {
                padding: 0.5rem;
                width: 100%;
            }
            form {
                margin-bottom: 2rem;
            }
            button {
                background-color: #3498db;
                color: white;
                padding: 0.5rem 1rem;
                border: none;
                margin-right: 5px;
                border-radius: 3px;
                cursor: pointer;
            }
            .delete-btn {
                background-color: #e74c3c;
            }
        </style>
    </head>
    <body>

        <h2>Instructor Dashboard</h2>

        <!-- Feedback Message -->
        <c:if test="${not empty message}">
            <div class="message ${messageType}">
                ${message}
            </div>
        </c:if>

        <!-- Add New Course Form -->
        <h3>Create New Course</h3>
        <form action="../CourseServlet" method="post">
            <input type="hidden" name="action" value="create">
            <label>Course Name:</label>
            <input type="text" name="courseName" required>
            <label>Instructor:</label>
            <input type="text" name="instructor" required>
            <label>Schedule (HH:mm or HH:mm:ss):</label>
            <input type="text" name="schedule" required>
            <button type="submit">Add Course</button>
        </form>

        <!-- Course Table -->
        <h3>All Courses</h3>
        <table>
            <thead>
                <tr>
                    <th>Course Name</th>
                    <th>Instructor</th>
                    <th>Schedule</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="course" items="${DatabaseConnector.getAllCourses()}">
                    <tr>
                        <td>${course.name}</td>
                        <td>${course.instructor}</td>
                        <td>${course.schedule}</td>
                        <td>
                            <!-- Update Form -->
                            <form action="CourseServlet" method="post" style="display:inline;">
                                <input type="hidden" name="action" value="update">
                                <input type="hidden" name="oldName" value="${course.name}">
                                <input type="text" name="newName" value="${course.name}" required>
                                <input type="text" name="instructor" value="${course.instructor}" required>
                                <input type="text" name="newSchedule" value="${course.schedule}" required>
                                <button type="submit">Update</button>
                            </form>

                            <!-- Delete Form -->
                            <form action="CourseServlet" method="post" style="display:inline;">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="courseName" value="${course.name}">
                                <button type="submit" class="delete-btn">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>
