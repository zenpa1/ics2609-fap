package Control;

// Import Necessities
import model.UserCheck;
import model.InvalidCredentialsException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;

public class LoginServlet extends HttpServlet {
   
     protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // Retrieve user input
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Get the ServletContext
        ServletContext context = getServletContext();

        // Debug Code Lines
        System.out.println("Received username: " + username);
        System.out.println("Received password: " + password);
        //String role = UserCheck.validateUser(username, password, context);

        try {
            // Blank input
            if ((username == null || username.trim().isEmpty()) && (password == null || password.trim().isEmpty())) {
                throw new InvalidCredentialsException("Both username and password are empty.");
            }

            // UserCheck
            boolean userExists = UserCheck.usernameExists(username, context);
            String storedPassword = UserCheck.getPassword(username, context);
            String role = UserCheck.getUserRole(username, context);
            
            if (!userExists && !storedPassword.equals(password)) { // Username & Pass does not exist in DB
                throw new InvalidCredentialsException("Both username and password are incorrect");
            }
            if (!userExists) { // If username does not exist
                throw new InvalidCredentialsException("User does not exist.");
            }
            if (!storedPassword.equals(password)) { // Username exists but password is incorrect
                throw new InvalidCredentialsException("Password is incorrect.");
            }

            // If both credentials are correct
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("role", role);
            
            if ("instructor".equalsIgnoreCase(role)) {
                response.sendRedirect("view/instructor_dashboard.jsp");
            } else if ("learner".equalsIgnoreCase(role)) {
                response.sendRedirect("view/learner_dashboard.jsp");
            }
            
         // Error Redirect  
        } catch (InvalidCredentialsException e) {
            response.sendRedirect("view/error_401.jsp"); // General Rejection
        }  catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("view/error_401.jsp"); // yes, you failed.
        }
    }
    
    protected String getContextParameter(String name) {
        return getServletContext().getInitParameter(name);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("doGet Achieved");
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("doPost Achieved");
        processRequest(request, response);
    }
    @Override
    public String getServletInfo() {
        return "Short desc@ription";
    }// </editor-fold>
}
