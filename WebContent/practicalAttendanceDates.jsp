<!DOCTYPE html>
<%@include file="prevent.jsp"%>
<%@page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Practical Attendance Dates</title>
<link rel="stylesheet" href="customCss/subjectList.css">
<link rel="stylesheet" type="text/css"
	href="customCss/branchSubDetail.css">

<link rel="stylesheet" type="text/css" href="onlineCss/cloudflare.css">
<link rel="stylesheet" type="text/css"
	href="onlineCss/dataTables.bootstrap4.min.css">
<script type="text/javascript" src="onlineJs/jquery-3.3.1.js"></script>
<script type="text/javascript" src="onlineJs/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="onlineJs/dataTables.bootstrap4.min.js"></script>


<style type="text/css">
.bs-example {
	margin: 2px;
}
</style>


</head>
<body>

	<div class="container">
	<div class="row">
			<div class="col-md-12">

				<div class="row">

					<div class="col-md-3 col-xs-12 offset-md-1">
						<H5 class="text-center">Sem :-${sem}</H5>

					</div>
					<div class="col-md-3 col-xs-12 offset-md-1">
						<H5 class="text-center">Batch :-${batch}</H5>
					</div>

					<div class="col-md-3 col-xs-12 offset-md-1">
						<H5 class="text-center">Subject :-${subject}</H5>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">

				<div class="row">
					<div class="col-md-4 col-xs-12 offset-md-2">

						<H5 class="text-center">Branch :-${branch}</H5>


					</div>

					<div class="col-md-4 col-xs-12 offset-md-1">
						<H5 class="text-center">Division :-${division}</H5>
					</div>
				</div>
			</div>
		</div>
		

	</div>


	<div class="form-resp">
		<div class="bs-example">
			<h3 class="text-center">Practical Dates</h3>


			<div class="table-data">

				<table class="table table-dark table-striped" border="1"
					id="example" style="width: 100%">
					<thead>
						<tr>
							<th class="text-center">No.</th>
							<th class="text-center ">Date</th>
							<th class="text-center">Month</th>

							<th class="text-center">View</th>

						</tr>
					</thead>
					<tbody>
						<%
							Iterator dates;
							List data = (List) request.getAttribute("dates");
							if (!data.isEmpty()) {
								for (dates = data.iterator(); dates.hasNext();) {
						%>
						<tr>
							<td class="text-center"><div><%=dates.next()%></div></td>
							<td class="text-center"><div><%=dates.next()%></div></td>
							<td class="text-center"><div><%=dates.next()%></div></td>


							<td class="text-center">
								<div>
									<a class="clickme lookinfo"
										href="dailyAttendance?datetime=<%=dates.next()%>
										 
							&branch=<%=request.getAttribute("branch")%> 
							&division=<%=request.getAttribute("division")%> 
							&sem=<%=request.getAttribute("sem")%> 
							&subject=<%=request.getAttribute("subject")%>
							&action=practical">Detail</a>
								</div>
							</td>


						</tr>
						<%
							}
							}
						%>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<script>
	$(document).ready(function() {
	    $('#example').DataTable( {
	    	"pagingType": "full_numbers",
		    
	        "scrollY": 400,
	        "scrollX": true
	    } );
	} );
	</script>
</body>
</html>
<%@include file="footer.html"%>
