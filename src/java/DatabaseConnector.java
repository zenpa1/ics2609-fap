import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {
    public Connection connection;
	
	/**
	 * Initialize a DatabaseConnector instance
	 * 
	 * @usage
	 * ```java
	 * // get an instance and assign to `db`
	 * DatabaseConnector db = new DatabaseConnector();
	 * 
	 * // initialize the instance with connection info
	 * db.init(jdbcClassName, jdbcDriverURL, dbHostName, dbPort, dbName, dbUsername, dbPassword);
	 * ```
	 */
    public void init(String jdbcClassName, String jdbcDriverURL, String dbHostName, String dbPort, String dbName, String dbUsername, String dbPassword) {
        try {
            Class.forName(jdbcClassName);
            String url = jdbcDriverURL + "://" + dbHostName + ":" + dbPort + "/" + dbName;
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
	public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	/**
	 * Convert a String to a query and run that query on the database
	 * 
	 * @usage
	 * ```java
	 * // get the instance
	 * DatabaseConnector db = DatabaseConnector.getInstance();
	 * 
	 * // run a query
	 * String query = "SELECT * FROM USER_INFO"
	 * ResultSet users = db.runQuery(query);
	 * 
	 * // handle data
	 * while (users.next()) {
	 *     // do stuff
	 * }
	 * ``` 
	 * @param query
	 * @return 
	 */
    public ResultSet runQuery(String query) {
        // Create and Execute the Statement
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public int addUser(String username, String password, String role) {
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO USER_INFO (USERNAME, PASSWORD, ROLE) VALUES (?, ?, ?)");
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, role);
            
            int x = stmt.executeUpdate();
            return x;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int deleteUser(String username) {
        try {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM USER_INFO WHERE USERNAME=?");
            stmt.setString(1, username);
            
            int x = stmt.executeUpdate();
            return x;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int editUser(String username, String password, String role) {
        try {
            PreparedStatement stmt = connection.prepareStatement("UPDATE USER_INFO SET PASSWORD=?, ROLE=? WHERE USERNAME=?");
            stmt.setString(1, password);
            stmt.setString(2, role);
            stmt.setString(3, username);
            
            int x = stmt.executeUpdate();
            return x;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public boolean userExists(String username) {
        try {
            ResultSet users = this.runQuery("SELECT * FROM USER_INFO");
            
            while (users.next())
                if (users.getString("USERNAME").equals(username))
                    return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return false;
    }
	
	public String getPassword(String username) {
		ResultSet users = this.runQuery("SELECT * FROM APP.USER_INFO");
		
		try {
			while (users.next()) {
				if (username.equals(users.getString("USERNAME")))
					return users.getString("PASSWORD");
			}
			
			users.close();
		} catch (SQLException e) {
			
		}
		// nobody was found with the username
		return null;
	}

}
