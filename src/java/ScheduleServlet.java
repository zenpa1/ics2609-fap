import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ScheduleServlet extends HttpServlet {

    private Connection conn;
    
    @Override
    public void init() throws ServletException {
        try {
            // Initialize database connection
            Class.forName(getServletConfig().getInitParameter("jdbcClassName"));
            String username = getServletConfig().getInitParameter("dbUserName");
            String password = getServletConfig().getInitParameter("dbPassword");
            String url = getServletConfig().getInitParameter("jdbcDriverURL") + "://" +
                       getServletConfig().getInitParameter("dbHostName") + ":" +
                       getServletConfig().getInitParameter("dbPort") + "/" +
                       getServletConfig().getInitParameter("databaseName");
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new ServletException("Database connection error", ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        String username = "user1@example.com";
        
        try {
            List<Map<String, String>> courseList = new ArrayList<>();
            
            if (action.equals("learner")) {
                courseList = getLearnerSchedule(username);
            } else if (action.equals("instructor")) {
                courseList = getInstructorSchedule(username);
            }
            
            // Set the courseList as a request attribute
            request.setAttribute("courseList", courseList);
            
            // Forward to JSP page
            if(action.equals("learner"))
                request.getRequestDispatcher("/view/learner_schedule.jsp").forward(request, response);
            else if(action.equals("instructor"))
                request.getRequestDispatcher("/view/instructor_schedule.jsp").forward(request, response);

        } catch (SQLException ex) {
            throw new ServletException("Database error", ex);
        }
    }
    
    private List<Map<String, String>> getLearnerSchedule(String username) throws SQLException {
        List<Map<String, String>> courseList = new ArrayList<>();
        String query = "SELECT c.COURSE_NAME, c.COURSE_INSTRUCTOR, c.SCHEDULE "
                + "FROM COURSEDB c "
                + "JOIN COURSE_LEARNERS cl ON c.COURSE_NAME = cl.COURSE_NAME "
                + "WHERE cl.USERNAME = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Map<String, String> course = new HashMap<>();
                course.put("name", rs.getString("COURSE_NAME"));
                course.put("instructor", rs.getString("COURSE_INSTRUCTOR"));
                course.put("schedule", rs.getString("SCHEDULE"));
                courseList.add(course);
            }
        }
        return courseList;
    }
    
    private List<Map<String, String>> getInstructorSchedule(String instructorName) throws SQLException {
        List<Map<String, String>> courseList = new ArrayList<>();
        String query = "SELECT COURSE_NAME, COURSE_INSTRUCTOR, SCHEDULE " +
                      "FROM COURSEDB " +
                      "WHERE COURSE_INSTRUCTOR = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, instructorName);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Map<String, String> course = new HashMap<>();
                course.put("name", rs.getString("COURSE_NAME"));
                course.put("instructor", rs.getString("COURSE_INSTRUCTOR"));
                course.put("schedule", rs.getString("SCHEDULE"));
                courseList.add(course);
            }
        }
        return courseList;
    }
    
    @Override
    public void destroy() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            // Log the error
        }
    }
}