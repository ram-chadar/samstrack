
<!DOCTYPE html>
<%@include file="prevent.jsp"%>

<%@ page language="java" import="java.util.*"%>

<html>
<head>

<meta name="viewport" content="=600, initial-scale=1">

<link rel="stylesheet" type="text/css" href="customCss/attendance.css">

</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-md-12 offset-md-2">
				<form action="showClassStatus" name="showClassStatus"
					class="form-data" method="post">
					<div class="row">
						<div class="col-md-4 col-xs-12">

							<div class="form-resp">

								<div class="form-group">
									<label for="Year">Year</label> <input type="number"
										class="form-control" id="ayear" placeholder="Acadmic Year"
										name="accYear">
								</div>

								<div class="form-group">
									<label for="month">Month</label> <select
										class="form-control form-control-md" id="month" name="month">

										<option disabled="disabled" value="00">Select Month [
											only For Month Status ]</option>
										<option value="01">01</option>
										<option value="02">02</option>
										<option value="03">03</option>
										<option value="04">04</option>
										<option value="05">05</option>
										<option value="06">06</option>
										<option value="07">07</option>
										<option value="08">08</option>
										<option value="09">09</option>
										<option value="10">10</option>
										<option value="11">11</option>
										<option value="12">12</option>
									</select>
								</div>


								<div class="form-group">
									<label for="branch">Branch</label> <select
										class="form-control form-control-md" id="bname" name="branch">
										<option
											value="<%=(String) session.getAttribute("userBranch")%>"><%=(String) session.getAttribute("userBranch")%>
										</option>
									</select>

								</div>
								<div class="button-group ">

									<button type="submit" class="btn btn-primary btn-block"
										value="Show Month Status" name="action">Show Month
										Status</button>
								</div>

							</div>
						</div>

						<div class="col-md-4 col-xs-12">
							<div class="form-resp">

								<div class="form-group">
									<label for="division">Divison</label> <select
										class="form-control form-control-md" id="divsion"
										name="division">
										
										<option value="A">A</option>
										<option value="B">B</option>
										<option value="C">C</option>
										<option value="D">D</option>
									</select>
								</div>

								<div class="form-group">
									<label for="batch">Batch</label> <select
										class="form-control form-control-md" id="batch" name="batch">
										
										<option value="B1">B1</option>
										<option value="B2">B2</option>
										<option value="B3">B3</option>
										<option value="B4">B4</option>
									</select>
								</div>


								<div class="form-group">
									<label for="sem">Sem</label> <select
										class="form-control form-control-md" id="sem" name="sem">

										
										<option value="">Select Sem</option>
										<option value="01">01</option>
										<option value="02">02</option>
										<option value="03">03</option>
										<option value="04">04</option>
										<option value="05">05</option>
										<option value="06">06</option>
										<option value="07">07</option>
										<option value="08">08</option>
									</select>
								</div>

								<div class="form-group">
									<label for="subject">Subject</label> <select
										class="form-control form-control-md" id="subject"
										name="subject">
									
										<option>Select Subject</option>

									</select>
								</div>

								<div class="button-group ">

									<button type="submit" class="btn btn-primary btn-block "
										value="Show Sem Status" name="action">Show Sem Status</button>
								</div>



							</div>

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
					url : "getSubject.jsp",//your jsp page name
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


</body>
</html>
<%@include file="footer.html"%>

