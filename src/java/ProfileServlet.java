import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProfileServlet extends HttpServlet {
    
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
            if (action.equals("learner")) {
                // Function 1: Display enrolled courses for learners
                List<String> enrolledCourses = enrolledCourses(username);
                request.setAttribute("enrolledCourses", enrolledCourses);
                request.getRequestDispatcher("/view/learner_profile.jsp").forward(request, response);
            } 
            else if (action.equals("instructor")) {
                // Function 2: Display instructed courses for instructors
                List<String> availableCourses = instructorCourses(username);
                request.setAttribute("availableCourses", availableCourses);
                request.getRequestDispatcher("/view/instructor_profile.jsp").forward(request, response);
            } 
        } catch (SQLException ex) {
            throw new ServletException("Database error: " + ex.getMessage(), ex);
        }
    }
    
    // Method to get enrolled courses
    private List<String> enrolledCourses(String username) throws SQLException {
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
    private List<String> instructorCourses(String username) throws SQLException {
        List<String> courses = new ArrayList<>();
        String query = "SELECT COURSE_NAME FROM COURSEDB WHERE COURSE_INSTRUCTOR = ?";
        
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
    public String getServletInfo() {
        return "Short description";
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
