
<!DOCTYPE html>
<%@include file="prevent.jsp"%>
<html>
<head>
<title>Practical Attendance Dates</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css"
	href="customCss/branchSubDetail.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<form action="attendace_Dates" name="attendace_Dates" class="form-data">

					<H4 class="text-center">Practical Attendance Option</H4>

					<div class="form-resp">


						<div class="form-group">
							<label for="branch">Branch</label> <select
								class="form-control form-control-md" id="bname" name="branch"
								required="required">
								<option value="<%=(String) session.getAttribute("userBranch")%>"><%=(String) session.getAttribute("userBranch")%></option>

							</select>
						</div>

						<div class="form-group">
							<label for="division">Divison</label> <select
								class="form-control form-control-md" id="division"
								name="division" required="required">
								<option value="">Select Division</option>
								<option value="A">A</option>
								<option value="B">B</option>
								<option value="C">C</option>
								<option value="D">D</option>
							</select>
						</div>
						
							<div class="form-group">
									<label for="batch">Batch</label> <select
										class="form-control form-control-md" id="batch" name="batch">
										
										<option value="">Select Batch</option>
										<option value="B1">B1</option>
										<option value="B2">B2</option>
										<option value="B3">B3</option>
										<option value="B4">B4</option>
										


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
							<label for="subject">Subject</label> <select
								class="form-control form-control-md" id="subject" name="subject"
								required="required">

								<option disabled value="">Select Subject</option>

							</select>
						</div>



						<div class="button-group">

							<button type="submit" class="btn btn-primary btn-block "
								value="practical" name="action">Show Dates</button>
							
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
				var division=$("#division").val();
				var batch=$("#batch").val();
				$.ajax({
					url : "getPracticalSubject.jsp",//your jsp page name
					data : {
						bname : bname,
						sem : sem,
						division:division,
						batch:batch
					},//sending request to getsubject.jsp page.
					method : "POST",//HTTP method.
					success : function(data) {
						$("#subject").html(data);//output or response will display in subject select box.
					}
				});
			});
		});

		$(document).ready(function() {
			$("#division").on("change", function() {
				var bname = $("#bname").val();
				var sem = $("#sem").val();
				var division=$("#division").val();
				var batch=$("#batch").val();
				$.ajax({
					url : "getPracticalSubject.jsp",//your jsp page name
					data : {
						bname : bname,
						sem : sem,
						division:division,
						batch:batch
					},//sending request to getsubject.jsp page.
					method : "POST",//HTTP method.
					success : function(data) {
						$("#subject").html(data);//output or response will display in subject select box.
					}
				});
			});
		});

		$(document).ready(function() {
			$("#batch").on("change", function() {
				var bname = $("#bname").val();
				var sem = $("#sem").val();
				var division=$("#division").val();
				var batch=$("#batch").val();
				$.ajax({
					url : "getPracticalSubject.jsp",//your jsp page name
					data : {
						bname : bname,
						sem : sem,
						division:division,
						batch:batch
					},//sending request to getsubject.jsp page.
					method : "POST",//HTTP method.
					success : function(data) {
						$("#subject").html(data);//output or response will display in subject select box.
					}
				});
			});
		});
	</script>
	<script src="onlineJs/jquery.min.js"></script>

</body>
</html>
<%@include file="footer.html"%>