<!-- Landing Page-->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&family=Suez+One&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/view/styles.css" />
        <title>ActiveLearningPH</title>
    </head>
    <body>
        <!-- Top NavBar -->
        <header class = "app-header">
            <nav class="navbar bg-dark">
                <div class="nav-container">
                    <!-- Logo -->
                    <img src="https://activelearning.ph/wp-content/uploads/2021/03/logo-white.png" class="logo-nav" alt="ActiveLearningPH">

                    <!-- Main NavBar -->
                    <div class="main-nav">
                        <!-- Login Button -->
                        <button class="btn-nav login" onclick="window.location.href = 'view/login.jsp'">
                            <span class="login-btn-text">Login</span>
                        </button>
                        <!-- Sign-up Button -->
                        <button class="btn-nav signup" onclick="window.location.href = 'view/signup.jsp'">
                            <span class="login-btn-text">Sign-up</span>
                        </button> 
                    </div>
                </div>
            </nav>
        </header>

        <!-- Content -->
        <div class="before-content">
            <div class="content-container">
                <h2 class="text-center">
                    Learn where to start and what to take next
                    <br>
                    with our popular IT learning paths
                </h2>

                <div class="row">
                    <div class="col-4 offset-4">
                        <hr class="separator border-info">
                    </div>
                </div>

                <!-- Course Cards - Static only -->
                <div id="course-area" class="row text-center pt-3 justify-content-lg-center">
                    <div class="course-card">
                        <!-- Card Image -->
                        <div class="card-img-container">
                        <img src="https://activelearning.ph/wp-content/themes/understrap-child/img/home/itil.png" class="card-img-top" alt="ITIL">
                        </div>
                        <div class="card-body">
                            <!-- Course Logo -->
                            <img src="https://activelearning.ph/wp-content/uploads/2022/10/itil-ato-1.png" class="card-icon" alt="ITIL Training Philippines">
                            <br>
                            <span>ITIL</span>
                        </div>
                    </div>
                    <div class="course-card">
                        <!-- Card Image -->
                        <div class="card-img-container">
                        <img src="https://activelearning.ph/wp-content/themes/understrap-child/img/home/java.png" class="card-img-top" alt="java">
                        </div>
                        <div class="card-body">
                            <!-- Course Logo -->
                            <img src="https://activelearning.ph/wp-content/themes/understrap-child/img/home/logo-course-java.jpg" class="card-icon" alt="Java">
                            <br>
                            <span>Java</span>
                        </div>
                    </div>
                    <div class="course-card">
                        <!-- Card Image -->
                        <div class="card-img-container">
                        <img src="https://activelearning.ph/wp-content/themes/understrap-child/img/home/security.png" class="card-img-top" alt="Cybersecurity">
                        </div>
                        <div class="card-body">
                            <!-- Course Logo -->
                            <img src="https://activelearning.ph/wp-content/themes/understrap-child/img/home/logo-course-security.jpg" class="card-icon" alt="Cybersecurity">
                            <br>
                            <span>Cybersecurity</span>
                        </div>
                    </div>
                    <div class="course-card">
                        <!-- Card Image -->
                        <div class="card-img-container">
                        <img src="https://activelearning.ph/wp-content/themes/understrap-child/img/home/project-management.png" class="card-img-top" alt="Project Management">
                        </div>
                        <div class="card-body">
                            <!-- Course Logo -->
                            <img src="https://activelearning.ph/wp-content/themes/understrap-child/img/home/logo-course-project-management.jpg" class="card-icon" alt="Project Management">
                            <br>
                            <span>Project Management</span>
                        </div>
                    </div>
                    <div class="course-card">
                        <!-- Card Image -->
                        <div class="card-img-container">
                        <img src="https://activelearning.ph/wp-content/themes/understrap-child/img/home/digital-transformation.png" class="card-img-top" alt="Digital Transformation">
                        </div>
                        <div class="card-body">
                            <!-- Course Logo -->
                            <img src="https://activelearning.ph/wp-content/themes/understrap-child/img/home/logo-course-digital-transformation.jpg" class="card-icon" alt="Digital Transformation">
                            <br>
                            <span>Digital Transformation</span>
                        </div>
                    </div>
                    <div class="course-card">
                        <!-- Card Image -->
                        <div class="card-img-container">
                        <img src="https://activelearning.ph/wp-content/themes/understrap-child/img/home/web-dev.png" class="card-img-top" alt="Web Development">
                        </div>
                        <div class="card-body">
                            <!-- Course Logo -->
                            <img src="https://activelearning.ph/wp-content/themes/understrap-child/img/home/logo-course-web.jpg" class="card-icon" alt="Web Development">
                            <br>
                            <span>Web Development</span>
                        </div>
                    </div>
                    <div class="course-card">
                        <!-- Card Image -->
                        <div class="card-img-container">
                        <img src="https://activelearning.ph/wp-content/uploads/2022/05/microsoft-excel-home-logo.png" class="card-img-top" alt="Microsoft Excel">
                        </div>
                        <div class="card-body">
                            <!-- Course Logo -->
                            <img src="https://activelearning.ph/wp-content/uploads/2022/05/microsoft-excel-logo.png" class="card-icon" alt="Microsoft Excel">
                            <br>
                            <span>Microsoft Excel</span>
                        </div>
                    </div>
                    <div class="course-card">
                        <!-- Card Image -->
                        <div class="card-img-container">
                        <img src="https://activelearning.ph/wp-content/uploads/2022/06/python-homepage.jpg" class="card-img-top" alt="Python Training Philippines">
                        </div>
                        <div class="card-body">
                            <!-- Course Logo -->
                            <img src="https://activelearning.ph/wp-content/uploads/2019/03/Python-100x100-1.png" class="card-icon" alt="Python Training Philippines">
                            <br>
                            <span>Python Programming</span>
                        </div>
                    </div>
                    <div class="course-card">
                        <!-- Card Image -->
                        <div class="card-img-container">
                        <img src="https://activelearning.ph/wp-content/uploads/2022/06/microsoft-azure-homepage.png" class="card-img-top" alt="Microsoft Azure Training Philippines">
                        </div>
                        <div class="card-body">
                            <!-- Course Logo -->
                            <img src="https://activelearning.ph/wp-content/uploads/2025/02/Microsoft-Azure-logo-100-x-100.png" class="card-icon" alt="Microsoft Azure Training Philippines">
                            <br>
                            <span>Microsoft Azure</span>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="row">
                <div class="col-4 offset-4">
                    <hr class="separator border-info">
                </div>
            </div>
        </div>
    </body>
</html>
