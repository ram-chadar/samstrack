<!DOCTYPE html>
<%@include file="prevent.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>List Of Users</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
	
<link rel="stylesheet" type="text/css" href="customCss/subjectList.css">
<link rel="stylesheet" type="text/css" href="customCss/branchSubDetail.css">

<link rel="stylesheet" type="text/css" href="onlineCss/cloudflare.css">
<link rel="stylesheet" type="text/css" href="onlineCss/dataTables.bootstrap4.min.css">
<script type="text/javascript" src="onlineJs/jquery-3.3.1.js"></script> 
<script type="text/javascript" src="onlineJs/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="onlineJs/dataTables.bootstrap4.min.js"></script>

<style type="text/css">
    .bs-example{
    	margin: 2px;
    }
 </style>


</head>
<body>
	<div class="form-resp">
		<div class="bs-example">
			<h3 class="text-center">List Of Users</h3>
			<div class="table-data">
			<table class="table table-dark table-striped" border="1" id="example" style="width: 100%">
					<thead>
						<tr>
							<th class="text-center">User Name</th>
							<!--  <th>Password</th> -->
							<th class="text-center">User Type</th>
							<!--    <th>Security_Question</th>
                       <th>Answer</th> -->
							<th class="text-center">Email</th>
							<!--   <th>Branch</th> -->
							<th class="text-center">Action</th>

						</tr>
					</thead>
					<tbody>

						<c:forEach items="${users}" var="users">
							<tr>
								<td class="text-center"><c:out value="${users.userName}" /></td>
								<%--  <td><c:out value="${users.password}" /></td> --%>

								<td class="text-center"><c:out value="${users.userType}" /></td>
								<%--   <td><c:out value="${users.question}" /></td>
                      
                      <td><c:out value="${users.answer}" /></td> --%>
								<td class="text-center"><c:out value="${users.email}" /></td>
								<%-- <td><c:out value="${users.branch}" /></td> --%>
								<td class="text-center"><a class="clickme lookdanger"
									href="deleteUser?user=${users.userName}"
									onclick="if(!(confirm('Are You Sure To Delete'))) return false"> Delete</i></a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<script>
	$(document).ready(function() {
	    $('#example').DataTable( {
	        pagingType: "full_numbers",
	            
	        scrollY: 400,
	        scrollX: true,
	       
	    } );
	} );
		</script>
</html>
<%@include file="footer.html"%>
