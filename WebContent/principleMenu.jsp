<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
    <link href="https://fonts.googleapis.com/css?family=Fahkwang" rel="stylesheet">

	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>

    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
        
        <script src="onlineJs/dashboard.js"></script>
    <link href="customCss/dashboard.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="customCss/gredient.css">
 
        
</head>
 
<body>
    
  <div class="text-cenetr" >
        <div class="navbar navbar-expand-lg navbar-dark bg-dark" >
        
           <a class="navbar-brand" href="home.jsp">
    		<img src="login/images/icons/logo.ico" width="30" height="30">
  		  </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse " id="navbarCollapse">
                <ul class="navbar-nav mr-auto">

		  <li class="nav-item dropdown active">
	                        <a class="nav-link dropdown-toggle" id="dropdown1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Branch/Subject</a>
	                        <ul class="dropdown-menu" aria-labelledby="dropdown1">
	                            <li class="dropdown-item"><a href="branchDetail.jsp">Branch</a></li>
									                            
	                           <li class="dropdown-item"><a href="allocatedSubjectOption.jsp">Allocated Subjects</a></li>
	                          
	                            
	                        </ul>
	                    </li>

                  

                    <li class="nav-item dropdown active">
                        <a class="nav-link dropdown-toggle" id="dropdown1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Student Detail</a>
                        <ul class="dropdown-menu" aria-labelledby="dropdown1">
                        
                        
                            <li class="dropdown-item"><a href="studentListOption.jsp" >View All Student</a></li>
                             <li class="dropdown-item"><a href="updateAccYear" >Update Accadmic Year</a></li>
                            
                        </ul>
                    </li>

                  

                    <li class="nav-item dropdown active">
                        <a class="nav-link dropdown-toggle" id="dropdown1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">View Status</a>
                        <ul class="dropdown-menu" aria-labelledby="dropdown1">
                            <li class="dropdown-item"><a href="classStatusOption.jsp" >Theory</a></li>
                            <li class="dropdown-item"><a href="practicalStatusOption.jsp" >Practical</a></li>
                            
                        </ul>
                    </li>

                     <li class="nav-item dropdown active">
                        <a class="nav-link dropdown-toggle" id="dropdown1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Overall Status</a>
                        <ul class="dropdown-menu" aria-labelledby="dropdown1">
                            <li class="dropdown-item"><a href="overAllClassStatus.jsp" >Theory</a></li>
                            <li class="dropdown-item"><a href="overAllPracticalStatus.jsp" >Practical</a></li>
                            
                        </ul>
                    </li>

                    <li class="nav-item active">
                        <a class="nav-link" href="defaulterOption.jsp" >Defaulter</a>
                    </li>

                    <li class="nav-item active">
                        <a class="nav-link" href="detainOption.jsp">Detain</a>
                    </li>

                    
                     <li class="nav-item dropdown active">
	                        <a class="nav-link dropdown-toggle" id="dropdown1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">User Detail</a>
	                        <ul class="dropdown-menu" aria-labelledby="dropdown1">
	                            <li class="dropdown-item"><a href="userDetailPrinciple.jsp">Add User</a></li>
	                           <li class="dropdown-item"><a href="listOfUsers">View All User</a></li>
	                            
	                            <!-- <li class="dropdown-item"><a href="subjectDetail.jsp">Subject</a></li> -->
	                           
	                            
	                        </ul>
	                    </li>
                    
                  <li class="nav-item dropdown active right-dropDown">
                        <a class="nav-link dropdown-toggle" id="dropdown1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><%=(String)session.getAttribute("user") %></a>
                        
                        <ul class="dropdown-menu" aria-labelledby="dropdown1">
                            <li class="dropdown-item"><a href="profileServlet" >Profile</a></li>
                            <li class="dropdown-item"><a href="#" >Setting</a></li>
                            <div class="dropdown-divider"></div>
                            <li class="dropdown-item"><a href="logoutServlet">Logut</a></li>
						</ul>

                    </li>
               

                </ul>
               
            </div>
        </div>
        </div>
       
        
        
</body>
</html>