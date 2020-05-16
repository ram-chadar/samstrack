<!DOCTYPE html>
<%@include file="prevent.jsp" %>
<%@ page language="java" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
  <head>
<title>Attendance Status</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" type="text/css" href="customCss/branchSubDetail.css">
<link rel="stylesheet" href="customCss/subjectList.css">
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
						<div class="col-md-4 col-xs-12 offset-md-2">
								<H5 class="text-center">Subject :-${subject}</H5>

						</div>

						<div class="col-md-4 col-xs-12 offset-md-1">
								<H5 class="text-center">Sem :-${sem}</H5>
						
						</div>
					</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				
					<div class="row">
						<div class="col-md-4 col-xs-12 offset-md-2">
								<H5 class="text-center">Batch :-${batch}</H5>

						</div>

						<div class="col-md-4 col-xs-12 offset-md-1">
								<H5 class="text-center">Month :-${month}</H5>
						
						</div>
					</div>
			</div>
		</div>
	</div>
 <div class="form-resp">
<div class="bs-example">
      
     			
                   <h3 class="text-center">${head}</h3>
                   <div class="table-data"> 
                    <table class="table table-dark table-striped" border="1" id="example" style="width: 100%">
                     <thead>
                    <tr>
									<th class="text-center">Roll No.</th>
									<th class="text-center">Student Name</th>
									<th class="text-center">Days</th>
									<th class="text-center">Status %</th>
									
								</tr>
							</thead>
							<tbody>
					<%
									Iterator status;
									List data = (List) request.getAttribute("StatusList");
								
										for (status = data.iterator(); status.hasNext();) {
								%>
								<tr>
									<td class="text-center"><div><%=status.next()%></div></td>
									<td class="text-center"><div><%=status.next()%></div></td>
									<td class="text-center"><div><%=status.next()%></div></td>
									<td class="text-center"><div><%=status.next()%> %</div></td>


								</tr>
								<%
									}
									
								%>
								
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
		<script type="text/javascript" src="jspdf/jspdf.min.js"></script>

<script type="text/javascript" src="jspdf/jspdf.plugin.autotable.min.js"></script>

<script type="text/javascript" src="jspdf/tableHTMLExport.js"></script>

<script type="text/javascript">
	$("#json").on("click", function() {
		$("#example").tableHTMLExport({
			type : 'json',
			filename : 'Attendance.json'
		});
	});

	$("#pdf").on("click", function() {
		$("#example").tableHTMLExport({
			type : 'pdf',
			filename : 'Attendance.pdf'
		});
	});

	$("#csv").on("click", function() {
		$("#example").tableHTMLExport({
			type : 'csv',
			filename : 'Attendance.csv'
		});
	});
</script>

  </body>
</html>
<%@include file="footer.html" %>
