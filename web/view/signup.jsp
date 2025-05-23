<!-- Signup -->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&family=Suez+One&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/css/styles.css" />
        <title>Sign-up</title>
    </head>
    <body>
        <div class="background">
            <div class="background-blur"></div>
            <div class="content-form">
                <div class="signup-card">
                    <a href="../index.jsp">
                        <img src="https://activelearning.ph/wp-content/uploads/2021/03/logo-white.png" class="logo" alt="ActiveLearningPH" />
                    </a>
                    
                    <h2 class="welcome">Hello!</h2>
                    <p class="subtitle">We're excited to have you join our community.</p>
                    
                    <% if (request.getAttribute("error") != null) {%>
                    <p style="color: red;"><%= request.getAttribute("error")%></p>
                    <% }%>
                    
                    <form action="${pageContext.request.contextPath}/CreateUserServlet" method="post">
                        <label for="username">Username</label>
                        <input type="username" name="username" placeholder="Enter your username" required />

                        <label for="password">Password</label>
                        <input type="password" name="password" placeholder="Enter your password" required />

                        <label for="re-password">Re-enter Password</label>
                        <input type="password" name="re-password" placeholder="Re-enter your password" required />

                        <label>I am a/an:</label>
                        <div class="role-selection">
                            <label class="role-option">
                                <input type="radio" name="role" value="Instructor" required />
                                Instructor
                            </label>
                            <label class="role-option">
                                <input type="radio" name="role" value="Learner" required />
                                Learner
                            </label>
                        </div>
                        
                        <div class="g-recaptcha" data-sitekey="6LcuHD8rAAAAAEEGD7d3891T1nEaXJzlHo0VRsvp"></div>

                        <button type="submit" class="btn-primary">Sign-up</button>
                        <div class="divider">or</div>
                        <p class="login-link">Already have an account? <a href="../view/login.jsp">Login</a></p>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
