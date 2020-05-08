
<!DOCTYPE html>
<%@include file="prevent.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" import="java.util.*"%>
<html>
<head>
<title>Daily Theory Attendance Result</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet" type="text/css" href="customCss/branchSubDetail.css">
<link rel="stylesheet" type="text/css" href="customCss/subjectList.css">
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
			<div class="col-md-10 offset-md-1">
				<form action="dailyAttendance" name="dailyAttendance"
					class="form-data">
					<%
							String heading=(String)request.getAttribute("heading");
							if(heading!=null)
							{
								%>
								<h5 class="text-center"><%=heading %></h5>
								<%
							}
							else
							{
								%>
					<h3 class="text-center">Daily Attendance</h3>
								<%
							}
							%>
					<div class="row">
						<div class="col-md-5 col-xs-12">

							<div class="form-resp">
								<H4 class="text-center">MANDATORY FIELDS</H4>


								<div class="form-group">
								
									<label for="datetime">Date And Time</label>
									<h4>${datetime}</h4>
									 <input type="datetime-local" class="form-control" name="datetime"
										value="${datetime}" required="required">

								</div>

								<div class="form-group">
									<label for="branch">Branch</label> <select
										class="form-control form-control-md" id="bname" name="branch" required="required">
										<option
											value="<%=(String) session.getAttribute("userBranch")%>"><%=(String) session.getAttribute("userBranch")%></option>
									</select>
								</div>

								<div class="form-group">
									<label for="division">Divison</label> <select
										class="form-control form-control-md" id="division"
										name="division" required="required">

										<%
											String divsion = (String) request.getAttribute("division");
											if (divsion != null) {
										%>
										<option value="<%=divsion%>"><%=divsion%></option>
										<%
										} 
										%>

										<option disabled value="">Select Division</option>
										<option value="A">A</option>
										<option value="B">B</option>
										<option value="C">C</option>
										<option value="D">D</option>


									</select>
								</div>
								
								

								<div class="form-group">
									<label for="sem">Sem</label> <select
										class="form-control form-control-md" id="sem" name="sem" required="required">

										<%
											String sem = (String) request.getAttribute("sem");
											if (sem != null) {
										%>
										<option value="<%=sem%>"><%=sem%></option>
										<%
											}
											%>
										<option disabled value="00">Select Sem</option>
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
									<label for="subject">Subject</label> <select
										class="form-control form-control-md" id="subject"
										name="subject" required="required">
										<%
											String subject = (String) request.getAttribute("subject");
											if (subject != null) {
										%>
										<option value="<%=subject%>"><%=subject%></option>
										<%
											} 
										%>
										<option value="">Select Subject</option>
										
									</select>
								</div>

								<button type="submit" class="btn btn-primary btn-block "
									value="class" name="action">Theory Attendance</button>
									<button type="submit" class="btn btn-primary btn-block "
									value="practical" name="action">Practical Attendance</button>

							</div>
						</div>

						<div class="col-md-7 col-xs-12">
							<div class="form-resp">


								<div class="table-data">
								<table class="table table-dark table-striped" border="1" id="example" style="width: 100%">

										<thead>
											<tr>
												<th class="text-center" style="width: 30px;">Roll.NO</th>
												<th class="text-center">Name</th>
											</tr>
										</thead>
										<tbody>

											<%
												Iterator itr2;
												List dayAttendance = (List) request.getAttribute("dayAttendance");
												if (dayAttendance != null) {
													for (itr2 = dayAttendance.iterator(); itr2.hasNext();) {
											%>

											<tr>
												<td class="text-center"><%=itr2.next()%></td>
												<td class="text-center"><%=itr2.next()%></td>
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
					</div>
				</form>
			</div>
		</div>
	</div>
	<script>
		$(document).ready(function() {
			$("#sem").on("change", function() {
				var bname = $("#bname").val();
				var division = $("#division").val();
				var sem = $("#sem").val();
				
				$.ajax({
					url : "getClassSubject.jsp",
					data : {
						bname : bname,
						sem : sem,
						division:division
					},
					method : "POST",//HTTP method.
					success : function(data) {
						$("#subject").html(data);//output or response will display in subject select box.
					}
				});
			});
		});
		  $(document).ready(function () {             //get subject
		        $("#division").on("change", function () {
		           var bname = $("#bname").val();
		          	var sem= $("#sem").val();
		          	var division= $("#division").val();
		           
		                $.ajax({
		                    url: "getClassSubject.jsp",
		                    data: {bname: bname,sem: sem,division:division},//sending request to getclassssubject.jsp page.
		                    method: "POST",//HTTP method.
		                    success: function (data)
		                    {
		                        $("#subject").html(data);//output or response will display in subject select box.
		                    }
		                });
		        });
		    });
	</script>
	
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

