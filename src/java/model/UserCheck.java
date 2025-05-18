package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;

public class UserCheck {
    
    public static String validateUser(String username, String password, ServletContext context) {
        String role = null;

        try (Connection conn = DBConnection.getConnection(context); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM USER_INFO WHERE username=? AND password=?")) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) { role = rs.getString("role");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print error in log
        }

        return role;
    }
    
    public static List<User> getAllUsers(ServletContext context) {
        List<User> userList = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection(context); PreparedStatement stmt = conn.prepareStatement("SELECT username, password, role FROM USER_INFO"); ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");

                userList.add(new User(username, password, role));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print the error in logs
        }
        return userList;
    }
    
    public static boolean usernameExists(String username, ServletContext context) {
        boolean exists = false;  
        try (Connection conn = DBConnection.getConnection(context); PreparedStatement stmt = conn.prepareStatement("SELECT username FROM USER_INFO WHERE username = ?")) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            exists = rs.next(); // If a result exists, username is in DB
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }
    
    public static String getPassword(String username, ServletContext context) {
        String password = null;
        try (Connection conn = DBConnection.getConnection(context); PreparedStatement stmt = conn.prepareStatement("SELECT password FROM USER_INFO WHERE username = ?")) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                password = rs.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return password;
    }
    
    public static String getUserRole(String username, ServletContext context) {
        String role = null;
        try (Connection conn = DBConnection.getConnection(context); PreparedStatement stmt = conn.prepareStatement("SELECT role FROM USER_INFO WHERE username = ?")) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                role = rs.getString("role");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }
    
    public static boolean createUser(String username, String password, String role, ServletContext context) {
        try (Connection conn = DBConnection.getConnection(context); PreparedStatement stmt = conn.prepareStatement("INSERT INTO USER_INFO (username, password, role) VALUES (?, ?, ?)")) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, role);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}


