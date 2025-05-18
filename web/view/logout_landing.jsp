<!-- Log-out Landing Page -->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&family=Suez+One&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/view/styles.css" />
        <title>Thank you!</title>
    </head>
    <body>
        <header class = "app-header">
            <nav class="navbar bg-dark">
                <div class="nav-container">
                    <!-- Logo -->
                    <img src="https://activelearning.ph/wp-content/uploads/2021/03/logo-white.png" class="logo-nav" alt="ActiveLearningPH">
                </div>
            </nav>
        </header>
        
        <div class="msg-container">
            <h1 class="msg-title">Successfully Logged Out</h1>
            <p class="logout-message">
                You have successfully logged out.
                <br>
                We hope to see you again soon!
            </p>
            <form action="<%= request.getContextPath()%>/index.jsp" method="post">
                <button type="submit" class="logout-button">Go Back to Home</button>
            </form>
        </div>
    </body>
</html>
