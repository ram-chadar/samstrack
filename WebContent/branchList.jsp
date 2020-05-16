<!DOCTYPE html>
<%@include file="prevent.jsp" %>
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
    .bs-example{
    	margin: 2px;
    }
 </style>


</head>
<body>

<div class="form-resp">
<div class="bs-example">
  <h3 class="text-center">All Branches</h3>
    
  <div class="table-data">
  
<table class="table table-dark table-striped" border="1" id="example"style="width:100%">
    <thead>
      <tr>
        <th class="text-center">Branch Name</th>
        <th class="text-center ">Branch Code</th>
        <th class="text-center">Action</th>
        <th class="text-center">Class Subject</th>
        <th class="text-center">Practical Subject</th>
      </tr>
    </thead>
    <tbody>
  <c:forEach items="${branches}" var="branch">
    <tr>
        <td class="text-center"><c:out value="${branch.branchname}" /></td>
		<td class="text-center"><c:out value="${branch.branchCode}" /></td>
        <td class="text-center">
       <a class="clickme lookdanger" href="deleteBranch?branchname=${branch.branchname}" onclick="if(!(confirm('Are You Sure To Delete'))) return false">Delete</a>
		</td>
		<td class="text-center"> <a class="clickme lookinfo" href="subjectDetail?bn=${branch.branchname}&action=VIEW ALL">View</a></td>
		<td class="text-center"> <a class="clickme lookinfo" href="practicalSubjectDetail?bn=${branch.branchname}&action=VIEW ALL">View</a></td>

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
    	"pagingType": "full_numbers",
        "scrollY": 400,
        "scrollX": true
    } );
} );
		</script>
</body>
</html>
<%@include file="footer.html" %>
