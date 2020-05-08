<!DOCTYPE html>
<%@include file="prevent.jsp"%>
<%@page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="customCss/subjectList.css">
<link rel="stylesheet" type="text/css" href="customCss/branchSubDetail.css">
<link rel="stylesheet" type="text/css" href="onlineCss/cloudflare.css">
<link rel="stylesheet" type="text/css" href="onlineCss/dataTables.bootstrap4.min.css">
<script type="text/javascript" src="onlineJs/jquery-3.3.1.js"></script> 
<script type="text/javascript" src="onlineJs/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="onlineJs/dataTables.bootstrap4.min.js"></script>

<style type="text/css">
.bs-example {
	margin: 2px;
}
</style>

</head>
<body>

	<div class="bs-example">
		<h3 class="text-center">All Class Room Subjects</h3>
		
		<div class="table-data form-resp">
			<table class="table table-dark table-striped" border="1" id="example">
				<thead>
					<tr>
						<!--  <th class="text-center">Branch Name</th> -->
						<th class="text-center">Sem</th>
						<th class="text-center">Subject Name</th>
						<th class="text-center">Subject Code</th>

						<th class="text-center">Action</th>
					</tr>
				</thead>
				<tbody id="myTable">
					<c:forEach items="${subjects}" var="subject">
						<tr>
							<%--  <td class="text-center"><c:out value="${subject.branchName}" /></td> --%>
							<td class="text-center"><c:out value="${subject.sem}" /></td>
							<td class="text-center"><c:out
									value="${subject.subjectName}" /></td>
							<td class="text-center"><c:out
									value="${subject.subjectCode}" /></td>
							<td class="text-center"><a class="clickme lookdanger"
								href="deleteSubject?branchName=${subject.branchName}&sem=${subject.sem}&subjectName=${subject.subjectName}"
								onclick="if(!(confirm('Are You Sure To Delete'))) return false">Delete</a>
							</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
			
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
