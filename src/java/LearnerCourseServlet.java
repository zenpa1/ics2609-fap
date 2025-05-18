/*
CHECKLIST: 
ESTABLISH WEB.XML PARAMETERS || DONE
ESTABLISH CONNECTION AND CLASS || DONE 
1. DISPLAY COURSES ENROLLED USING SESSION || DONE
RECHECK IF RESULT SET RETURNS CORRECT DATA || DONE

2. DISPLAY COURSES UNENROLLED USING SESSION || DONE
SEE IF RESULT SET RETURNS CORRECT DATA || DONE

3. ALLOW LEARNER TO ENROLL TO COURSE || DONE
DOUBLE CHECK IN DB IF ITS ADDED || DONE

*/
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;

public class LearnerCourseServlet extends HttpServlet {
    
    private Connection conn;
    
    @Override
    public void init() throws ServletException {
        try {
            Class.forName(getServletConfig().getInitParameter("jdbcClassName"));
            String username = getServletConfig().getInitParameter("dbUserName");
            String password = getServletConfig().getInitParameter("dbPassword");
            StringBuffer url = new StringBuffer(getServletConfig().getInitParameter("jdbcDriverURL"))
                    .append("://")
                    .append(getServletConfig().getInitParameter("dbHostName"))
                    .append(":")
                    .append(getServletConfig().getInitParameter("dbPort"))
                    .append("/")
                    .append(getServletConfig().getInitParameter("databaseName"));
            conn = DriverManager.getConnection(url.toString(), username, password);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException("Database connection error: " + ex.getMessage());
        }
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        String username = "user1@example.com"; // Replace with session username getter LATER T_T 
        
        try {
            if (action == null) {
                // Function 1: Display enrolled courses
                List<String> enrolledCourses = getEnrolledCourses(username);
                request.setAttribute("enrolledCourses", enrolledCourses);
                request.getRequestDispatcher("/view/learner_dashboard.jsp").forward(request, response);
            } 
            else if (action.equals("viewAvailable")) {
                // Function 2: Display available courses (not enrolled)
                List<String> availableCourses = getAvailableCourses(username);
                request.setAttribute("availableCourses", availableCourses);
                request.getRequestDispatcher("/view/learner_courses.jsp").forward(request, response);
            } 
            else if (action.equals("enroll")) {
                // Function 3: Enroll in a course
                String courseName = request.getParameter("courseName");
                enrollInCourse(username, courseName);
                List<String> availableCourses = getAvailableCourses(username);
                request.setAttribute("availableCourses", availableCourses);
                request.getRequestDispatcher("/view/learner_courses.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            throw new ServletException("Database error: " + ex.getMessage(), ex);
        }
    }
    
    // Method to get enrolled courses
    private List<String> getEnrolledCourses(String username) throws SQLException {
        List<String> courses = new ArrayList<>();
        String query = "SELECT COURSE_NAME FROM COURSE_LEARNERS WHERE USERNAME = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    courses.add(rs.getString("COURSE_NAME"));
                }
            }
        }
        return courses;
    }
    
    // Helper method to get available courses (not enrolled)
    private List<String> getAvailableCourses(String username) throws SQLException {
        List<String> courses = new ArrayList<>();
        String query = "SELECT COURSE_NAME FROM COURSEDB WHERE COURSE_NAME NOT IN " +
                  "(SELECT COURSE_NAME FROM COURSE_LEARNERS WHERE USERNAME = ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    courses.add(rs.getString("COURSE_NAME"));
                }
            }
        }
        return courses;
    }
    
    // Helper method to enroll in a course
    private void enrollInCourse(String username, String courseName) throws SQLException {
        String query = "INSERT INTO COURSE_LEARNERS (USERNAME, COURSE_NAME) VALUES (?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, courseName);
            stmt.executeUpdate();
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
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
            // Log this error
        }
    }
}