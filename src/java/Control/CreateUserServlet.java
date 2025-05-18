package Control;

import model.UserCheck;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateUserServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("re-password"); // Get re-entered password
        String role = request.getParameter("role");

        // Password match validation
        if (!password.equals(rePassword)) {
            request.setAttribute("error", "Passwords do not match.");
            request.getRequestDispatcher("/view/signup.jsp").forward(request, response);
            return;
        }

        ServletContext context = getServletContext();

        // Check if username exists
        if (UserCheck.usernameExists(username, context)) {
            request.setAttribute("error", "Username already exists.");
            request.getRequestDispatcher("/view/signup.jsp").forward(request, response);
            return;
        }

        boolean success = UserCheck.createUser(username, password, role, context);

        if (success) {
            // Add success message to session
            request.getSession().setAttribute("signupSuccess", "Account created successfully! Please login.");
            response.sendRedirect(request.getContextPath() + "/view/login.jsp");
        } else {
            request.setAttribute("error", "Error creating user. Please try again.");
            request.getRequestDispatcher("/view/signup.jsp").forward(request, response);
        }
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
        return "Short description";
    }// </editor-fold>
}
