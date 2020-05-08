<!DOCTYPE html>
<%@include file="prevent.jsp"%>

<%@ page language="java" import="java.util.*"%>

<html>
<head>
<title>Monthly Theory Attendance Result</title>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet" href="customCss/subjectList.css">
<link rel="stylesheet" type="text/css" href="customCss/branchSubDetail.css">
<link rel="stylesheet" type="text/css" href="onlineCss/pretty-checkbox.min.css">
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
			
				<form action="monthlyAttendance" name="monthlyAttendance"
					class="form-data" method="post">
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
						<h5 class="text-center">MONTHLY ATTENDANCE RESULT</h5>
								
								<%
							}
							%>
					<div class="row">
						<div class="col-md-4 col-xs-12">

							<div class="form-resp">
								<H4 class="text-center">MANDATORY FIELDS</H4>

								<div class="form-group">
									<label for="Year">Year</label> <input type="number"
										class="form-control" id="ayear" placeholder="Acadmic Year"
										name="accYear" value="<%=(new java.util.Date()).getYear()+1900%>" required="required">
								</div>

								<div class="form-group">
									<label for="month">Month</label> <select
										class="form-control form-control-md" id="month" name="month" required="required">
										
										<%
										String month=(String)request.getAttribute("month");
											if(month!=null)
											{
												%>
												<option value="<%=month%>"><%=month%></option>
												<%
											}
											
												%>
										<option disabled="disabled" value="00">Select Month</option>
										<option value="01">01</option>
										<option value="02">02</option>
										<option value="03">03</option>
										<option value="04">04</option>
										<option value="05">05</option>
										<option value="06">06</option>
										<option value="07">07</option>
										<option value="08">08</option>
										<option value="09">09</option>
										<option value="10">10</option>
										<option value="11">11</option>
										<option value="12">12</option>
											
										
									</select>
								</div>

								<div class="form-group">
									<label for="branch">Branch</label> <select
										class="form-control form-control-md" id="bname" name="branch" required="required">
										<option
											value="<%=(String) session.getAttribute("userBranch")%>"><%=(String) session.getAttribute("userBranch")%>
										</option>
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
										
										<option disabled="disabled" value="">Select Division</option>
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

										<option disabled="disabled" value="00">Select Sem</option>
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
										<option>Select Subject</option>

									</select>
								</div>


								<div class="form-group">
									<label for="rollNo">Roll No.</label> <input type="number"
										class="form-control" id="rollNo" placeholder="Roll NO."
										name="rollNO" value="${rollNo}" required="required">
								</div>
								<div>
									<button type="submit" class="btn btn-primary btn-block "
										value="class" name="action">Show Theory Attendance</button>
										<button type="submit" class="btn btn-primary btn-block "
										value="practical" name="action">Show Practical Attendance</button>

								</div>

							</div>
						</div>

						<div class="col-md-4 col-xs-12">
							<div class="form-resp">
							
							<div class="table-data">
									<table class="table table-dark table-striped" border="1" id="example" style="width: 100%">
										<thead>
											<tr>
												<th class="text-center">Total Lecture</th>
											</tr>
										</thead>
										<tbody>
											<!-- Total Lectue  -->
											<%
												Iterator itr2;
												List totalDate = (List) request.getAttribute("totaldate");
												if (totalDate != null) {
													for (itr2 = totalDate.iterator(); itr2.hasNext();) {
											%>

											<tr>
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

						<!-- 2 nd col end   -->
						<!-- 3 rd col begin   -->

						<div class="col-md-4 col-xs-12">
							<div class="form-resp">

								<div class="table-data">
									<table class="table table-dark table-striped" border="1" id="example2" style="width: 100%">
										<thead>
											<tr>
												<th class="text-center">Attend Lecture</th>
											</tr>
										</thead>
										<tbody>
											<!-- Attend Lectue  -->
											<%
												Iterator itr3;
												List attendDate = (List) request.getAttribute("attenddate");
												if (attendDate != null) {
													for (itr3 = attendDate.iterator(); itr3.hasNext();) {
											%>

											<tr>
												<td class="text-center"><%=itr3.next()%></td>
											</tr>
											<%
												}
												}
											%>





										</tbody>
									</table>

									</div>
									<div class="col-md-12 col-xs-12">
										<div class="form-resp">
											<H4 class="text-center">Over View</H4>
											<div class="form-group">
												<label for="branch">Total Lecture = ${totlec}</label><br>
												<label for="branch">Attend Lecture = ${attlec}</label><br>
												<label for="branch">Non-Attend Lecture =
													${nonattendlec}</label> <br> <label for="branch">Status
													= ${status} %</label> <br>



											</div>

										</div>
									</div>
								

							</div>
						</div>
						<!-- 3 rd col end  -->
					</div>
				</form>
			</div>
		</div>
	</div>
	<script>
		$(document).ready(function() {
			$("#sem").on("change", function() {
				var bname = $("#bname").val();
				var division=$("#division").val();
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
						$("#subject").html(data);
					}
				});
			});
		});

		$(document).ready(function () {             //get subject
	        $("#division").on("change", function () {
	           var bname = $("#bname").val();
	          	var sem= $("#sem").val();
	          	var division= $("#division").val();
	            /* alert(bname);
	            alert(sem); */
	                $.ajax({
	                    url: "getClassSubject.jsp",//your jsp page name
	                    data: {bname: bname,sem: sem,division:division},//sending request to getsubject.jsp page.
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
		<script>
		$(document).ready(function() {
		    $('#example2').DataTable( {
		    	"pagingType": "full_numbers",
			    
		        "scrollY": 400,
		        "scrollX": true
		    } );
		} );
		</script>
</body>
</html>
<%@include file="footer.html"%>
