<!DOCTYPE html>
<html lang="en">
    <head>
    <%
    String userType=(String)session.getAttribute("userType");
    %>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>SAMS</title>

        <!-- Bootstrap -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link href="css/dashboard.css" rel="stylesheet">

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>

        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

        <script src="js/dashboard.js"></script>

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
  
        <div class="navbar navbar-expand-md navbar-dark bg-dark" role="navigation">
            <a class="navbar-brand" href="home.jsp" target="insideframe" >SAMS</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse " id="navbarCollapse">
                <ul class="navbar-nav mr-auto">

                    <li class="nav-item dropdown active">
                        <a class="nav-link dropdown-toggle" id="dropdown1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Branch/Subject</a>
                        <ul class="dropdown-menu" aria-labelledby="dropdown1">
                            <li class="dropdown-item"><a href="branchDetail.jsp">Branch</a></li>
                            <li class="dropdown-item"><a href="subjectDetail.jsp" name="link">Subject</a></li>
                            
                        </ul>
                    </li>

                    <li class="nav-item dropdown active">
                        <a class="nav-link dropdown-toggle" id="dropdown1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Student Detail</a>
                        <ul class="dropdown-menu" aria-labelledby="dropdown1">
                            <li class="dropdown-item"><a href="studentRegistration.jsp" name="link">Student Registration</a></li>
                            <li class="dropdown-item"><a href="studentListOption.jsp" name="link">View All Student</a></li>
                            <li class="dropdown-item"><a href="studentBatch.jsp" name="link">Generate Batch</a></li>
                            
                        </ul>
                    </li>

                    <li class="nav-item dropdown active">
                        <a class="nav-link dropdown-toggle" id="dropdown1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Take Attendance</a>
                        <ul class="dropdown-menu" aria-labelledby="dropdown1">
                            <li class="dropdown-item"><a  href="takeClassAttendance.jsp" name="link">Class Room</a></li>
                            <li class="dropdown-item"><a href="takePracticalAttendance.jsp" name="link">Practical</a></li>
                            
                        </ul>
                    </li>

                  
                    <li class="nav-item dropdown active">
                        <a class="nav-link dropdown-toggle" id="dropdown2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Result</a>
                        <ul class="dropdown-menu" aria-labelledby="dropdown2">
                           
                            <li class="dropdown-item dropdown">
                                <a class="dropdown-toggle" id="dropdown2-1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Day</a>
                                <ul class="dropdown-menu" aria-labelledby="dropdown2-1">
                                    <li class="dropdown-item"><a href="dailyClassAttendance.jsp" name="link">Class Room</a></li>
                                    <li class="dropdown-item"><a href="dailyPracticalAttendance.jsp" name="link">Practical</a></li>
                                </ul>
                            </li>
                            <li class="dropdown-item dropdown">
                                <a class="dropdown-toggle" id="dropdown2-1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Month</a>
                                <ul class="dropdown-menu" aria-labelledby="dropdown2-1">
                                    <li class="dropdown-item"><a href="monthlyClassAttendance.jsp" name="link">Class Room</a></li>
                                    <li class="dropdown-item"><a href="monthlyPractcalAttendance.jsp" name="link">Practical</a></li>
                                </ul>
                            </li>
                            <li class="dropdown-item dropdown">
                                <a class="dropdown-toggle" id="dropdown2-1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Sem</a>
                                <ul class="dropdown-menu" aria-labelledby="dropdown2-1">
                                    <li class="dropdown-item"><a href="semClassAttendance.jsp" name="link">Class Room</a></li>
                                    <li class="dropdown-item"><a href="semPracticalAttendance.jsp" name="link">Practical</a></li>
                                </ul>
                            </li>
                        </ul>
                    </li>


                     <li class="nav-item dropdown active">
                        <a class="nav-link dropdown-toggle" id="dropdown1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Generate Status</a>
                        <ul class="dropdown-menu" aria-labelledby="dropdown1">
                            <li class="dropdown-item"><a href="generateClassAttendance.jsp" name="link">Class Room</a></li>
                            <li class="dropdown-item"><a href="generatePracticalAttendance.jsp">Practical</a></li>
                            
                        </ul>
                    </li>

                    <li class="nav-item dropdown active">
                        <a class="nav-link dropdown-toggle" id="dropdown1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">View Status</a>
                        <ul class="dropdown-menu" aria-labelledby="dropdown1">
                            <li class="dropdown-item"><a href="#" >Class Room</a></li>
                            <li class="dropdown-item"><a href="#" >Practical</a></li>
                            
                        </ul>
                    </li>

                     <li class="nav-item dropdown active">
                        <a class="nav-link dropdown-toggle" id="dropdown1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Overall Status</a>
                        <ul class="dropdown-menu" aria-labelledby="dropdown1">
                            <li class="dropdown-item"><a href="#" >Class Room</a></li>
                            <li class="dropdown-item"><a href="#">Practical</a></li>
                            
                        </ul>
                    </li>

                    <li class="nav-item active">
                        <a class="nav-link" href="#">Defaulter</a>
                    </li>

                    <li class="nav-item active">
                        <a class="nav-link" href="#">Detain</a>
                    </li>

				<%
				if(userType.equals("Admin"))
				{
					%>
					<li class="nav-item active">
                        <a class="nav-link" href="userDetail.jsp">User Detail</a>
                    </li>
					<%
				}
				%> 
                    

                  <li class="nav-item dropdown active right-dropDown">
                        <a class="nav-link dropdown-toggle" id="dropdown1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Ram</a>
                        <ul class="dropdown-menu" aria-labelledby="dropdown1">
                            <li class="dropdown-item"><a href="#">Profile</a></li>
                            <li class="dropdown-item"><a href="#">Setting</a></li>
                            <div class="dropdown-divider"></div>
                            <li class="dropdown-item"><a href="#">Logut</a></li>
						</ul>
                    </li>
               

                </ul>
               
            </div>
        </div>
      

       <footer class="footer-section container-fluid text-center">      
        <div class="row">
          <div class="col-md-4">
            <div class="footer-borders">
            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3783.894442226106!2d73.78900235033723!3d18.488439987366025!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3bc2be2d85407927%3A0xcdb80a8f52de798!2sRMD+Sinhgad+School+of+Engineering!5e0!3m2!1sen!2sin!4v1532164356962" width="100%" height="200px" frameborder="0" style="border:0" allowfullscreen></iframe>
            </div>
          </div>
          <div class="col-md-4">
            <div class="footer-borders">
              <p>Contact Us:
              +91 7020192726
              <br>
              
              Ram Chadar 
              <br>
              <a href="mailto:>ramdevcs@gmail.com" class="email bottomlink">ramdevcs@gmail.com</a>
              <b>OR</b>
              <a href="mailto:>salikramchadar@gmail.com" class="email bottomlink">salikramchadar@gmail.com</a>
              </p>
              <p>
              +91 8830503006  
              <br>
              Akhilesh Pandey
              <br>
              <a href="mailto:akhilesh.cbsereg@gmail.com" class="email bottomlink">akhilesh.cbsereg@gmail.com</a>
              </p>
            </div>
          </div>
          <div class="col-md-4">
            <div class="footer-borders">
              <ul class="footer-nav">
                <li><a class="bottomlink"href="#">About</a></li>
                <li><a class="bottomlink"href="#">Team</a></li>
                <li><a class="bottomlink" href="#">Gallery</a></li>
                <li><a class="bottomlink" href="#">Sponsors</a></li>
              </ul>
            </div>
          </div>
        </div>
      </footer>
       
     
    </body>
</html>
