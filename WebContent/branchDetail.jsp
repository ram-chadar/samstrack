<%
if(!session.getAttribute("userType").equals("Principle"))
	response.sendRedirect("index.jsp");
%>
<!DOCTYPE html>
<%@include file="prevent.jsp"%>
<html>
<head>
<title>Branch Detail</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css"
	href="customCss/branchSubDetail.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<form action="branchDetail" name="branchDetail" class="form-data">


					<div class="form-resp">
						<H4 class="text-center">Branch Detail</H4>

						<div class="form-group">
							<label for="branchName">Branch Name</label> <input type="text"
								class="form-control" id="bname" placeholder="Branch Name"
								name="branchname"
								onkeyup="
  var start = this.selectionStart;
  var end = this.selectionEnd;
  this.value = this.value.toUpperCase();
  this.setSelectionRange(start, end);
">
						</div>

						<div class="form-group">
							<label for="branchcode">Branch Code</label> <input type="text"
								class="form-control" id="bcode"
								placeholder="Branch Code [Optional]" name="branchCode">
						</div>

						<div class="button-group">
							<button type="submit" class="btn btn-primary btn-block"
								value="ADD" name="action">ADD</button>
							<button type="submit" class="btn btn-primary btn-block "
								value="VIEW ALL" name="action">VIEW ALL BRANCH</button>
						</div>

					</div>


				</form>
			</div>
		</div>
	</div>



</body>
</html>
<%@include file="footer.html"%>