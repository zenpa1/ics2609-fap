

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;

public class CourseServlet extends HttpServlet {
    private Connection conn;

    // Database credentials - CHANGE THESE TO YOUR VALUES
    private static final String DB_URL = "jdbc:mysql://localhost:3306/learning_platform";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";
    
    @Override
    public void init() throws ServletException {
        // try {
        //     Class.forName(getServletConfig().getInitParameter("jdbcSQLClassName"));
        //     String username = getServletConfig().getInitParameter("dbSQLUserName");
        //     String password = getServletConfig().getInitParameter("dbSQLPassword");
        //     StringBuffer url = new StringBuffer(getServletConfig().getInitParameter("jdbcSQLDriverURL"))
        //             .append("://")
        //             .append(getServletConfig().getInitParameter("dbSQLHostName"))
        //             .append(":")
        //             .append(getServletConfig().getInitParameter("dbSQLPort"))
        //             .append("/")
        //             .append(getServletConfig().getInitParameter("databaseSQLName"))
        //             .append("?useSSL=false&allowPublicKeyRetrieval=true&zeroDateTimeBehavior=CONVERT_TO_NULL");
        //     conn = DriverManager.getConnection(url.toString(), username, password);
        //     System.out.println("Database connected!");
        // } catch (SQLException | ClassNotFoundException ex) {
        //     throw new ServletException("Database connection error: " + ex.getMessage());
        // }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
 
        Connection conn = null;
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
 
            // Attempt to establish connection
            out.println("<h2>Connecting to database...</h2>");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
 
            if (conn != null) {
                out.println("<h2 style='color:green'>Connection successful!</h2>");
                out.println("<p>Database: " + conn.getMetaData().getDatabaseProductName() + "</p>");
                out.println("<p>Version: " + conn.getMetaData().getDatabaseProductVersion() + "</p>");
            } else {
                out.println("<h2 style='color:red'>Connection failed!</h2>");
            }
        } catch (ClassNotFoundException e) {
            out.println("<h2 style='color:red'>MySQL JDBC Driver not found!</h2>");
            e.printStackTrace(out);
        } catch (SQLException e) {
            out.println("<h2 style='color:red'>Connection failed!</h2>");
            out.println("<p>SQLException: " + e.getMessage() + "</p>");
            out.println("<p>SQLState: " + e.getSQLState() + "</p>");
            out.println("<p>VendorError: " + e.getErrorCode() + "</p>");
        } 

    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");

        try {
            if (null == action) {
                // Display all courses
                List<Course> courses = getAllCourses();
                request.setAttribute("courses", courses);
                request.getRequestDispatcher("/view/instructor_dashboard.jsp").forward(request, response);
            } else switch (action) {
                case "create":
                    {
                        // Create course       
                        String courseName = request.getParameter("courseName");
                        String schedule = request.getParameter("schedule");
                        String instructor = request.getParameter("instructor");

                        createCourse(courseName, instructor, schedule);
                        response.sendRedirect(request.getContextPath() + "/CourseServlet");
                    }
                case "update":
                    {   
                        // Update course
                        String oldName = request.getParameter("oldName");
                        String newName = request.getParameter("newName");
                        String newSchedule = request.getParameter("newSchedule");
                        String instructor = request.getParameter("instructor");
                        updateCourse(oldName, newName, instructor, newSchedule);
                        response.sendRedirect(request.getContextPath() + "/CourseServlet");
                        break;
                    }
                case "delete":
                    {
                        // Delete course
                        String courseName = request.getParameter("courseName");
                        deleteCourse(courseName);
                        response.sendRedirect(request.getContextPath() + "/CourseServlet");
                        break;
                    }
                default:
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException("Database error: " + ex.getMessage(), ex);
        }
    }
    
    // CRUD Operations
    private List<Course> getAllCourses() throws SQLException {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT course_name, course_instructor, schedule FROM Course";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                courses.add(new Course(
                    rs.getString("course_name"),
                    rs.getString("course_instructor"),
                    rs.getTime("schedule").toString()
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        return courses;
    }
    
    private void createCourse(String name, String instructor, String schedule) throws SQLException {
        String query = "INSERT INTO Course (course_name, course_instructor, schedule) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, instructor);

            try {
                // Try with seconds first
                String timeValue = schedule.contains(":") ? schedule : schedule + ":00:00";
                if (timeValue.split(":").length == 2) {
                    timeValue += ":00"; // Add seconds if missing
                }
                stmt.setTime(3, Time.valueOf(timeValue));
            } catch (IllegalArgumentException e) {
                throw new SQLException("Invalid time format. Please use HH:mm or HH:mm:ss format");
            }

            System.out.println("Attempting to create course: " + name + ", " + instructor + ", " + schedule);
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected == 0) {
                throw new SQLException("Creating course failed, no rows affected.");
            }
        }
    }
    
    private void updateCourse(String oldName, String newName, String instructor, String schedule) throws SQLException {
        String query = "UPDATE Course SET course_name = ?, course_instructor = ?, schedule = ? WHERE course_name = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newName);
            stmt.setString(2, instructor);
            stmt.setTime(3, java.sql.Time.valueOf(schedule));
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
    
    // Helper class
    private class Course {
        String name;
        String instructor;
        String schedule;
        
        public Course(String name, String instructor, String schedule) {
            this.name = name;
            this.instructor = instructor;
            this.schedule = schedule;
        }
        
        public String getName() {
            return name;
        }

        public String getInstructor() {
            return instructor;
        }

        public String getSchedule() {
            return schedule;
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
            // Log error
        }
    }
}