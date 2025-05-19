import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;

public class CourseServlet extends HttpServlet {

    private Connection conn;

    // Database credentials
    private static final String DB_URL = "jdbc:mysql://localhost:3306/learning_platform?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    @Override
    public void init() throws ServletException {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Initialize connection with auto-commit set to false
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            conn.setAutoCommit(false); // Disable auto-commit for transaction control
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("Database initialization failed", e);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            conn.setAutoCommit(false);

            String action = request.getParameter("action");
            String message = null;
            String messageType = null;

            if (action == null) {
                // Display all courses
                List<Course> courses = getAllCourses();
                request.setAttribute("courses", courses);
                request.getRequestDispatcher("/view/instructor_dashboard.jsp").forward(request, response);
            } else {
                try {
                    switch (action) {
                        case "create":
                            createCourse(
                                    request.getParameter("courseName"),
                                    request.getParameter("instructor"),
                                    request.getParameter("schedule") 
                            );
                            message = "Course created successfully!";
                            messageType = "success";
                            request.getRequestDispatcher("/view/instructor_dashboard.jsp").forward(request, response);
                            break;
                        case "update":
                            updateCourse(
                                    request.getParameter("oldName"),
                                    request.getParameter("newName"),
                                    request.getParameter("instructor"),
                                    request.getParameter("newSchedule")
                            );
                            message = "Course updated successfully!";
                            messageType = "success";
                            break;
                        case "delete":
                            deleteCourse(request.getParameter("courseName"));
                            message = "Course deleted successfully!";
                            messageType = "success";
                            break;
                    }
                    conn.commit();

                    // Store message in session
                    HttpSession session = request.getSession();
                    session.setAttribute("message", message);
                    session.setAttribute("messageType", messageType);

                    response.sendRedirect(request.getContextPath() + "/CourseServlet");
                    return;

                } catch (SQLException e) {
                    conn.rollback();
                    message = "Operation failed: " + e.getMessage();
                    messageType = "error";
                    request.setAttribute("message", message);
                    request.setAttribute("messageType", messageType);
                }
            }

            // Refresh course list
            List<Course> courses = getAllCourses();
            request.setAttribute("courses", courses);
            

        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    // Log error
                }
            }
        }
    }

    private List<Course> getAllCourses() throws SQLException {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT course_name, course_instructor, schedule FROM Course";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                courses.add(new Course(
                        rs.getString("course_name"),
                        rs.getString("course_instructor"),
                        rs.getTime("schedule").toString()
                ));
            }
        }
        return courses;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Check for session messages before processing
        HttpSession session = request.getSession(false);
        if (session != null) {
            String message = (String) session.getAttribute("message");
            String messageType = (String) session.getAttribute("messageType");
            if (message != null) {
                request.setAttribute("message", message);
                request.setAttribute("messageType", messageType);
                session.removeAttribute("message");
                session.removeAttribute("messageType");
            }
        }
        processRequest(request, response);
    }

    private void createCourse(String name, String instructor, String schedule) throws SQLException {
        String query = "INSERT INTO Course (course_name, course_instructor, schedule) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, instructor);
            stmt.setTime(3, Time.valueOf(validateTimeFormat(schedule)));
            stmt.executeUpdate();
        }
    }

    private void updateCourse(String oldName, String newName, String instructor, String schedule) throws SQLException {
        String query = "UPDATE Course SET course_name = ?, course_instructor = ?, schedule = ? WHERE course_name = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newName);
            stmt.setString(2, instructor);
            stmt.setTime(3, Time.valueOf(validateTimeFormat(schedule)));
            stmt.setString(4, oldName);
            stmt.executeUpdate();
        }
    }

    private void deleteCourse(String name) throws SQLException {
        String query = "DELETE FROM Course WHERE course_name = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.executeUpdate();
        }
    }

    private String validateTimeFormat(String time) throws SQLException {
        try {
            String timeValue = time.contains(":") ? time : time + ":00:00";
            if (timeValue.split(":").length == 2) {
                timeValue += ":00";
            }
            Time.valueOf(timeValue); // Validate format
            return timeValue;
        } catch (IllegalArgumentException e) {
            throw new SQLException("Invalid time format. Please use HH:mm or HH:mm:ss format");
        }
    }

    // Course helper class remains the same
    private class Course {
        private String name;
        private String instructor;
        private String schedule;

        public Course(String name, String instructor, String schedule) {
            this.name = name;
            this.instructor = instructor;
            this.schedule = schedule;
        }

        // Getters remain the same
        public String getName() { return name; }
        public String getInstructor() { return instructor; }
        public String getSchedule() { return schedule; }
    }

//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public void destroy() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.err.println("Error closing database connection: " + ex.getMessage());
        }
    }
}