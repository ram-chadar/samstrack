
<%@include file="prevent.jsp"%>

<%@page language="java" import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>OverAll Class Status</title>
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

<div class="container">
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
		
		<div class="row">
			<div class="col-md-12">
				
					<div class="row">
						

						<div class="col-md-4 col-xs-12 offset-md-1">
								<H5 class="text-center">Sem :-${sem}</H5>
						
						</div>
						<div class="col-md-4 col-xs-12 offset-md-2">
								<H5 class="text-center">Batch :-${batch}</H5>

						</div>
					</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				
					<div class="row">
						

						<div class="col-md-4 col-xs-12 offset-md-1">
								<H5 class="text-center">Month :-${month}</H5>
						
						</div>
					</div>
			</div>
		</div>
		</div>
	<div class="form-resp">
		<div class="bs-example">

			<h3 class="text-center">${heading}</h3>
				<div class="table-data">

				<table class="table table-dark table-striped" id="example" style="width: 100%; border: 1;">
					<thead>
						<tr>
							<th class="text-center">Roll N0.</th>
							<th class="text-center">Student Name</th>
							<%
								Iterator<String> sub_itr;
								List<String> subjects = (List) request.getAttribute("subjects");
								for (sub_itr = subjects.iterator(); sub_itr.hasNext();) {
							%>
							<th colspan="2" class="text-center"><%=sub_itr.next()%></th>
							<!-- Subjects -->
							<%
								}
							%>

						</tr>
						<tr>
							<th></th>
							<th></th>
							<%
								Iterator<String> totLec;
								List<String> totLecture = (List) request.getAttribute("totLectureList");
								for (totLec = totLecture.iterator(); totLec.hasNext();) {
							%>
							<th class="text-center">Total <%=totLec.next()%></th>
							<!-- Total Lectures -->
							<%
								}
							%>
						</tr>

					</thead>
					<tbody id="myTable">
						<%
							Iterator status_itr;
							List status_data = (List) request.getAttribute("statusList");
							for (status_itr = status_data.iterator(); status_itr.hasNext();) {
						%>
						<tr>
							<td class="text-center"><div><%=status_itr.next()%></div></td>
							<!-- rollno -->
							<td class="text-center"><div><%=status_itr.next()%></div></td>
							<!-- name -->

							<%
								int subjectSize = (Integer) request.getAttribute("subjectSize");

									for (int i = 0; i < subjectSize * 2; i++) {
							%>
							<td class="text-center"><div><%=status_itr.next()%>
									<!-- total lecture attend by student and in percentage -->

								</div></td>

							<%
								}
								}
							%>
						
					</tbody>
				</table>
				<!-- <div class="container">
					<div class="row">
						<div class="col-md-3 offset-md-5">
							<button class="btn btn-success" id="json">JSON</button>

							<button class="btn btn-success" id="pdf">PDF</button>

							<button class="btn btn-success" id="csv">CSV</button>
						</div>
					</div>
				</div> -->
			</div>



		</div>
	</div>
</body>

<script>
$(document).ready(function() {
    $('#example').DataTable( {
        pagingType: "full_numbers",
            
        scrollY: 400,
        scrollX: true,
       
    } );
} );
</script>

<!-- <script type="text/javascript" src="jspdf/jspdf.min.js"></script>

<script type="text/javascript" src="jspdf/jspdf.plugin.autotable.min.js"></script>

<script type="text/javascript" src="jspdf/tableHTMLExport.js"></script>

<script type="text/javascript">
	$("#json").on("click", function() {
		$("#example").tableHTMLExport({
			type : 'json',
			filename : 'sample.json'
		});
	});

	$("#pdf").on("click", function() {
		$("#example").tableHTMLExport({
			type : 'pdf',
			filename : 'sample.pdf'
		});
	});

	$(".csv").on("click", function() {
		$("#example").tableHTMLExport({
			type : 'csv',
			filename : 'sample.csv'
		});
	});
</script> -->
</html>
<%@include file="footer.html"%>
