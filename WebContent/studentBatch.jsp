<!DOCTYPE html>
<%@include file="prevent.jsp"%>

<%@ page language="java" import="java.util.*"%>

<html>
<head>

 <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="customCss/branchSubDetail.css">

</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-md-10 offset-md-2">
				<form action="studentBatch" name="studentBatch" class="form-data"
					method="post">
								<h4 class="text-center">Generate Batch</h4>

					<div class="row">
						<div class="col-md-5 col-xs-12">

							<div class="form-resp">

								<div class="form-group">
									<label for="accYear">Accadmin Year</label> <input type="number"
										class="form-control" id="prn" placeholder="Accadmic Year"
										name="accYear" value="<%=new Date().getYear()+1900%>" readonly="readonly" required="required">
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
										<option disabled="disabled" value="">Select Division</option>
										<option value="A">A</option>
										<option value="B">B</option>
										<option value="C">C</option>
										<option value="D">D</option>
									</select>
								</div>

								<div class="form-group">
									<label for="year">Year</label> <select
										class="form-control form-control-md" id="year" name="year" required="required">
										<option disabled="disabled" value="">Select Year</option>
										<option value="FE">FE</option>
										<option value="SE">SE</option>
										<option value="TE">TE</option>
										<option value="BE">BE</option>
									</select>
								</div>


							</div>
						</div>

						<div class="col-md-5 col-xs-12 offset-md-1">

							<div class="form-resp">

								<div class="form-group">
									<label for="batch">Batch</label> <select
										class="form-control form-control-md" id="batch" name="batch" required="required">
										<option disabled="disabled" value="">Select Batch</option>
										<option value="B1">B1</option>
										<option value="B2">B2</option>
										<option value="B3">B3</option>
										<option value="B4">B4</option>
									</select>
								</div>

								<div class="form-group">
									<label for="rollnofrom">Roll.No. FROM</label> <input
										type="number" class="form-control" id="rollnofrom"
										placeholder="Roll No.From" name="rollNoFrom"required="required">
								</div>

								<div class="form-group">
									<label for="rollnoto">Roll.No. TO</label> <input type="number"
										class="form-control" id="rollnoto" placeholder="Roll No.To"
										name="rollNoTo" required="required">
								</div>

								<div>
									<button type="submit" class="btn btn-primary btn-block "
										value="Get Roll.NO" name="action">Generate Batch</button>
								</div>


							</div>

						</div>
					</div>
				</form>
			</div>
		</div>
	</div>


</body>
</html>
<%@include file="footer.html"%>

