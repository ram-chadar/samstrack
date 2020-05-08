 
<!DOCTYPE html>
<%@include file="prevent.jsp"%>


<html>
<head>
<title>User Subjects</title>
<link rel="icon" type="image/png" href="login/images/icons/logo.png"/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="customCss/branchSubDetail.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<form action="allocateClassSubject" name="userSubject"
					class="form-data" method="post">
					<h4 class="text-center">Allocate Theory Subjects</h4>
					<div class="form-resp">

						<div class="form-group">
							<label for="branch">Branch</label> <select
								class="form-control form-control-md" id="bname" name="branch"
								required="required">
								<option value="">Select Branch</option>
								<option value="<%=(String) session.getAttribute("userBranch")%>"><%=(String) session.getAttribute("userBranch")%></option>

							</select>
						</div>

						<div class="form-group">
							<label for="sem">Sem</label> <select
								class="form-control form-control-md" id="sem" name="sem"
								required="required">

								<option value="">Select Sem</option>
								<option value="01">1</option>
								<option value="02">2</option>
								<option value="03">3</option>
								<option value="04">4</option>
								<option value="05">5</option>
								<option value="06">6</option>
								<option value="07">7</option>
								<option value="08">8</option>
							</select>
						</div>

						<div class="form-group">
							<label for="division">Divison</label> <select
								class="form-control form-control-md" id="divsion"
								name="division" required="required">
								<option value="">Select Division</option>
								<option value="A">A</option>
								<option value="B">B</option>
								<option value="C">C</option>
								<option value="D">D</option>
							</select>
						</div>


						<div class="form-group">
							<label for="subject">Subject</label> <select
								class="form-control form-control-md" id="subject" name="subject"
								required="required">

								<option disabled value="">Select Subject</option>

							</select>
						</div>

						<!-- ***************** user start -->
						<div class="form-group">
							<label for="user">User</label> <select
								class="form-control form-control-md" id="user" name="user"
								required="required">

								<option disabled="disabled" value="">Select User</option>

							</select>
						</div>

						<!-- ******************user stop -->




						<div class="button-group">
							<button type="submit" class="btn btn-primary btn-block"
								value="Allocate Subjects" name="action">Allocate
								Subjects</button>


						</div>

					</div>

				</form>
			</div>
		</div>
	</div>
	<script>
		$(document).ready(function() {
			$("#sem").on("change", function() {
				var bname = $("#bname").val();
				var sem = $("#sem").val();
				/* alert(bname);
				alert(sem); */
				$.ajax({
					url : "getClassSubject.jsp",//your jsp page name
					data : {
						bname : bname,
						sem : sem
					},//sending request to state.jsp page.
					method : "POST",//HTTP method.
					success : function(data) {
						$("#subject").html(data);//output or response will display in state select box.
					}
				});
			});
		});
	</script>


	<!-- for user   -->
	<script>
		$(document).ready(function() {
			$("#bname").on("change", function() {
				var bname = $("#bname").val();

				/* alert(bname);
				alert(sem); */
				$.ajax({
					url : "getuser.jsp",//your jsp page name
					data : {
						bname : bname
					},//sending request to state.jsp page.
					method : "POST",//HTTP method.
					success : function(data) {
						$("#user").html(data);//output or response will display in state select box.
					}
				});
			});
		});
	</script>
	<script src="onlineJs/jquery.min.js"></script>
	
	
</body>
</html>
<%@include file="footer.html"%>
