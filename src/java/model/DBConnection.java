package model;

// Import necessities
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletContext;    

public class DBConnection {
    public static Connection getConnection(ServletContext context) {
        Connection conn = null;

        try {
            // Retrieve database connection parameters from servlet context
            String driver = context.getInitParameter("DB_DRIVER");
            String url = context.getInitParameter("DB_URL");
            String user = context.getInitParameter("DB_USERNAME");
            String password = context.getInitParameter("DB_PASSWORD");

            // Debug: Check if parameters are retrieved correctly
            System.out.println("Driver: " + driver);
            System.out.println("URL: " + url);
            System.out.println("User: " + user);
            System.out.println("Password: " + password);

            // Load the database driver class
            Class.forName(driver);  // This can throw ClassNotFoundException

            // Establish the connection using the retrieved parameters
            conn = DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
}
