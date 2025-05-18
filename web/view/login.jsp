<!-- Login -->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&family=Suez+One&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/view/styles.css" />
        <title>Login</title>
    </head>
    <body>
        <div class="background">
            <div class="background-blur"></div>
            <div class="content-form">
                <div class="login-card">
                    <a href="../index.jsp">
                        <img src="https://activelearning.ph/wp-content/uploads/2021/03/logo-white.png" class="logo" alt="ActiveLearningPH" />
                    </a>
                    <h2 class="welcome">Welcome Back!</h2>
                    <p class="subtitle">Please enter your details.</p>
                    
                    <% if (session.getAttribute("signupSuccess") != null) {%>
                    <p style="color: green;"><%= session.getAttribute("signupSuccess")%></p>
                    <% session.removeAttribute("signupSuccess"); %>
                    <% }%>
                    
                    <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
                        <label for="username">Username</label>
                        <input type="username" name="username" placeholder="Enter your username" required />

                        <label for="password">Password</label>
                        <input type="password" name="password" placeholder="Enter your password" required />

                        <button type="submit" class="btn-primary">Login</button>
                        <div class="divider">or</div>
                        <p class="signup">Don't have an account? <a href="../view/signup.jsp">Sign up</a></p>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
