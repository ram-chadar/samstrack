<!DOCTYPE html>
<%@include file="prevent.jsp"%>
<%@ page language="java" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Allocated Subjects</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

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

<%
Iterator division;
List divdata = (List) request.getAttribute("theoryDivision");
int theoryDivSize=divdata.size()+1;
Iterator allocatedSubject;
List data = (List) request.getAttribute("allocatedTheorySubject");

Iterator division2;
List divdata2 = (List) request.getAttribute("practicalDivision");
int practicalDivSize=divdata2.size();

Iterator batch;
List batchdata = (List) request.getAttribute("batch");
int batchSize=batchdata.size()	;


Iterator allocatedpracticalSubject;
List data2 = (List) request.getAttribute("allocatedPracticalSubject");
%>


	<div class="form-resp">
		<div class="bs-example">
			<h3 class="text-center">Allocated Theory Subjects</h3>


			<div class="table-data table-responsive">
				<table class="table table-dark table-striped " border="1"
					id="example" style="width: 100%">
					<thead>
						<tr>
							<th class="text-center">Subject</th>
							<%
								for (division = divdata.iterator(); division.hasNext();) {
							%>
							<th class="text-center"><%=division.next()%> Division</th>

							<%
								}
							%>
						</tr>
					</thead>
					<tbody>

						
						<tr>
						<%
							
							if (!data.isEmpty()) {
									for (allocatedSubject = data.iterator(); allocatedSubject.hasNext();) {
						
						for (int i = 0; i<theoryDivSize; i++) {
							%>
							
							<td class="text-center"><div><%=allocatedSubject.next()%></div></td>
							<%	
						}
						%>
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
			$('#example').DataTable({
				"pagingType" : "full_numbers",

				"scrollY" : 300,
				"scrollX" : true
			});
		});
	</script>



 <div class="form-resp">
		<div class="bs-example">
			<h3 class="text-center">Allocated Practical Subjects</h3>


			<div class="table-data table-responsive">
				<table class="table table-dark table-striped " border="1"
					id="example2" style="width: 100%">
					<thead>
						<tr>
							<th class="text-center">Subject</th>
							<%
								

								for (division2 = divdata2.iterator(); division2.hasNext();) {
							%>
							<th colspan="<%=batchSize %>" class="text-center"><%=division2.next()%> Division</th>

							<%
								}
							%>
						</tr>
						<tr>
							<th></th>
							<%
								for (int i = 1; i <= divdata2.size(); i++) {
									
									if (!batchdata.isEmpty()) {
										for (batch = batchdata.iterator(); batch.hasNext();) {
							%>
							<th class="text-center"><%=batch.next()%></th>

							<%
								}

									}
								}
							%>

						</tr>
					</thead>
					<tbody>

						
						<tr>
						<%
							

							if (!data2.isEmpty()) {
								for (allocatedpracticalSubject = data2.iterator(); allocatedpracticalSubject.hasNext();) {
						
						
							for (int j = 0; j<practicalDivSize*batchSize+1; j++) {
							%>
							<td class="text-center"><div><%=allocatedpracticalSubject.next()%></div></td>
							<%	
							}
							
						
						%>
							

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
			$('#example2').DataTable({
				"pagingType" : "full_numbers",
				
				"scrollY" : 200,
				"scrollX" : true
			});
		});
	</script>
 



</body>
</html>
<%@include file="footer.html"%>
