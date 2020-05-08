<!DOCTYPE html>
<%@include file="prevent.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>List Of Student</title>
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
	<div class="form-resp">
		<div class="bs-example">
			<h3 class="text-center">List Of Students</h3>
			
			
			<div class="table-data table-responsive">
				<table class="table table-dark table-striped " border="1"
					id="example"  style="width:100%">
					<thead>
						<tr>
							<th class="text-center">Roll No.</th>
							<th class="text-center">Student Name</th>
							<th class="text-center">Batch</th>
							<th class="text-center">Delete</th>
							<th class="text-center">Edit</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach items="${students}" var="student">
							<tr>
								<td class="text-center"><c:out value="${student.rollNo}" /></td>
								<td class="text-center"><c:out
										value="${student.studentName}" /></td>
								<td class="text-center"><c:out value="${student.batch}" /></td>

								<td class="text-center"><a class="clickme lookdanger"
									href="DeleteStudent?accYear=${accYear} &year=${year} &branch=${branch} &division=${division} &rollNo=${student.rollNo}"
									onclick="if(!(confirm('Are You Sure To Delete'))) return false">Delete</a>&nbsp;
								</td>
								<td class="text-center"><a class="clickme lookinfo"
									href="selectStudent?action=edit &accYear=${accYear} &year=${year} &branch=${branch} &division=${division} &rollNo=${student.rollNo}">Edit</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<div class="container">
					<div class="row">
						<div class="col-md-3 offset-md-5">
								<button class="btn btn-success" id="json">JSON</button>

								<button class="btn btn-success" id="pdf">PDF</button>

								<button class="btn btn-success" id="csv">CSV</button>
						</div>
					</div>
				</div>

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

<!-- 	<script type="text/javascript" src="jspdf/jquery-3.3.1.slim.min.js"></script>
 -->
	<script type="text/javascript" src="jspdf/jspdf.min.js"></script>

	<script type="text/javascript" src="jspdf/jspdf.plugin.autotable.min.js"></script>

	<script type="text/javascript" src="jspdf/tableHTMLExport.js"></script>

	<script type="text/javascript">
		$("#json").on("click", function() {
			$("#example").tableHTMLExport({
				type : 'json',
				filename : 'student list.json'
			});
		});

		$("#pdf").on("click", function() {
			$("#example").tableHTMLExport({
				type : 'pdf',
				filename : 'student list.pdf'
			});
		});

		$("#csv").on("click", function() {
			$(".example").tableHTMLExport({
				type : 'csv',
				filename : 'student list.csv'
			});
		});
	</script>
</body>
</html>
<%@include file="footer.html"%>
