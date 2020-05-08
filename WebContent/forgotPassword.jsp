<!DOCTYPE html>

<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1">
 <link href="https://fonts.googleapis.com/css?family=Fahkwang" rel="stylesheet">

	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>

    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
        
        <link href="customCss/dashboard.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="customCss/gredient.css">
        <link rel="icon" type="image/png" href="login/images/icons/logo.ico"/>
        <link rel="stylesheet" type="text/css" href="customCss/branchSubDetail.css">


</head>
<body>
        <div class="navbar navbar-expand-lg navbar-dark bg-dark navbar-fixed-top" role="navigation" >
            <a class="navbar-brand" href="index.jsp"  >SAMS</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
 </div>
	<marquee style="color: red; background-color: yellow;">S A M
		S &nbsp; T R A C K</marquee>
	<%
		String msg = (String) request.getAttribute("msg");

		if (msg != null) {
	%>
	<script type="text/javascript">
	alert("${msg}");
  </script>
	<%
		}
	%>
	<div class="container">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<form action="forgotPassword" name="forgotPassword"
					class="form-data"  method="post">


					<div class="form-resp">
						<H4 class="text-center">FORGOT PASSWORD</H4>

						<div class="form-group">
							<label for="branchName">Username Name</label> <input type="text"
								class="form-control" id="userName" placeholder="Enter UserName"
								name="userName" required="required">
						</div>

						<div class="form-group">
							<label for="year"> Security Question</label> <select
								class="form-control form-control-md" id="question"
								name="question" required="required">
								<option value="">Select Question</option>
								<option value="WHAT IS YOUR NICK NAME?">What IS YOUR
									NICK NAME?</option>
								<option value="WHAT IS YOUR BIRTH YEAR?">WHAT IS YOUR
									BIRTH YEAR?</option>
								<option value="WHAT IS YOUR FAVORITE COLOR?">WHAT IS
									YOUR FAVORITE COLOR?</option>
							</select>
						</div>

						<div class="form-group">
							<label for="answer">Answer</label> <input type="text"
								class="form-control" id="answer" placeholder="Enter Answer"
								name="answer" required="required">
						</div>

						<div class="form-group">
							<label for="newPassword">New Password</label> <input type="text"
								class="form-control" id="newPassword"
								placeholder="Enter New Password" name="newPassword"
								required="required">
						</div>

						<div class="form-group">
							<label for="confirmPassword">Confirm Password</label> <input
								type="text" class="form-control" id="confPassword"
								placeholder="Confirm password" name="confirmPassword"
								required="required">
						</div>

						<div class="button-group">
							<button type="submit" class="btn btn-primary btn-block"
								value="Reset Password" name="action">Reset Password</button>
							<br>
						</div>
						<a href="index.jsp" class="btn btn-info btn-block" role="button">Back
							To Login</a>


					</div>


				</form>
			</div>
		</div>
	</div>

</body>
</html>
<%@include file="footer.html"%>