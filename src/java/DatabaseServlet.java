import java.io.IOException;
import java.sql.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DatabaseServlet extends HttpServlet {

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            // Retrieve all users and store in List<Map>
            List<Map<String, String>> userList = getAllUsers();
            
            // Set the list as a request attribute
            request.setAttribute("userList", userList);
            
            
            request.getRequestDispatcher("/view/instructor_database.jsp").forward(request, response);
            
        } catch (SQLException ex) {
            throw new ServletException("Database error", ex);
        }
    }

    private List<Map<String, String>> getAllUsers() throws SQLException {
        List<Map<String, String>> userList = new ArrayList<>();
        String query = "SELECT * FROM USERSDB";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                Map<String, String> user = new HashMap<>();
                user.put("username", rs.getString("USERNAME"));
                user.put("password", rs.getString("PASSWORD"));
                user.put("role", rs.getString("ROLE"));
                userList.add(user);
            }
        }
        return userList;
    }

    @Override
    public void destroy() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException ex) {
            // Log the error
        }
    }
}