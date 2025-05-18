package Control;

import model.UserCheck;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateUserServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
        boolean captchaVerified = verifyRecaptcha(gRecaptchaResponse);
        if (!captchaVerified) {
            response.sendRedirect("view/error_401.jsp"); // Or a specific captcha error page
            return;
        }
        
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
    
    private boolean verifyRecaptcha(String gRecaptchaResponse) {
        String secret = "6LcuHD8rAAAAAMz-mX6-fnEI7XXBoYEseGihKRmb";
        try {
            String url = "https://www.google.com/recaptcha/api/siteverify";
            String postParams = "secret=" + secret + "&response=" + gRecaptchaResponse;

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("POST");
            con.setDoOutput(true);
            OutputStream outStream = con.getOutputStream();
            outStream.write(postParams.getBytes());
            outStream.flush();
            outStream.close();

            Scanner in = new Scanner(con.getInputStream());
            String jsonResponse = "";
            while (in.hasNext()) {
                jsonResponse += in.nextLine();
            }
            in.close();

            return jsonResponse.contains("\"success\": true");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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
