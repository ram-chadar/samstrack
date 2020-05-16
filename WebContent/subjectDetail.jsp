<!DOCTYPE html>
<%@include file="prevent.jsp"%>

<%@ page language="java" import="java.util.*"%>

<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" type="text/css" href="customCss/branchSubDetail.css">

</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<form action="subjectDetail" name="subjectDetail" class="form-data">


					<div class="form-resp">
						<H3 class="text-center">Theory Subject Detail</H3>

						<div class="form-group">
							<label for="branchName">Select Branch</label> <select
								class="form-control form-control-md" id="bn" name="bn"
								required="required">

								<option value="<%=(String) session.getAttribute("userBranch")%>"><%=(String) session.getAttribute("userBranch")%>
								</option>
							</select>
						</div>

						<div class="form-group">
							<label for="sem">Sem</label> <select
								class="form-control form-control-md" id="sem" name="sem"
								required="required">
								<option disabled value="">Select Sem</option>
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
							<label for="subjectname">Subject Name</label> <input type="text"
								class="form-control" id="subname" placeholder="Subject Name"
								name="subname"
								onkeyup="
  var start = this.selectionStart;
  var end = this.selectionEnd;
  this.value = this.value.toUpperCase();
  this.setSelectionRange(start, end);">
						</div>

						<div class="form-group">
							<label for="subjectcode">Subject Code [ Optional ]</label> <input
								type="text" class="form-control" id="subcode"
								placeholder="Subject Code [Optional]" name="subcode">
						</div>

						<div class="button-group" id="bttn">
							<button type="submit" id="but1" class="btn btn-primary btn-block"
								value="ADD" name="action">Add</button>
							<button type="submit" id="but2" class="btn btn-primary btn-block"
								value="VIEW ALL" name="action">View All</button>
						</div>

					</div>
			</form>

			</div>
		</div>
	</div>


</body>
</html>
<%@include file="footer.html"%>