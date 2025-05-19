import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnector {
	public Connection connection;

	/**
	 * Initialize a DatabaseConnector instance
	 *
	 * @usage
	 * ```java
	 * // get an instance and assign to `db` DatabaseConnector db
	 * = new DatabaseConnector();
	 *
	 * // initialize the instance with connection info db.init(jdbcClassName,
	 * jdbcDriverURL, dbHostName, dbPort, dbName, dbUsername, dbPassword);
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
	 * DatabaseConnector db = new DatabaseConnector();
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

	public Course getCourseInfo(String courseName) throws SQLException {
		PreparedStatement x = connection.prepareStatement("SELECT * FROM COURSE WHERE COURSE_NAME=?");
		x.setString(1, courseName);
		ResultSet results = x.executeQuery();
		if (results.next())
			return new Course(
				results.getString("COURSE_NAME"),
				results.getString("COURSE_INSTRUCTOR"),
				results.getString("SCHEDULE")
                        );
		else
			return null;
	}
        
        public List<Course> getAllCourses() throws SQLException {
            ResultSet counter = runQuery("SELECT COUNT(*) FROM COURSE");
            
            ArrayList<Course> courseList = new ArrayList();
            while (counter.next()) {
                courseList.add(
                new Course(
				counter.getString("COURSE_NAME"),
				counter.getString("COURSE_INSTRUCTOR"),
				counter.getString("SCHEDULE")
                        )
                );
            }
            
            return courseList;
        }
}

class Course {
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