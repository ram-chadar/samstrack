<!DOCTYPE html>
<%@include file="prevent.jsp"%>
<html>
<head>
<script type="text/javascript">
function checkfile(sender) {
    var validExts = new Array(".xls");
    var fileExt = sender.value;
    fileExt = fileExt.substring(fileExt.lastIndexOf('.'));
    if (validExts.indexOf(fileExt) < 0) {
      alert("Invalid file selected, valid files are of " +
               validExts.toString() + " types.");
      sender.value="";
      return false;
    }
    else return true;
}
</script>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" type="text/css" href="customCss/branchSubDetail.css">

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<form action="uploadsheet" name="uploadsheet" class="form-data"
					method="post" enctype="multipart/form-data">


					<div class="form-resp">
						<H4 class="text-center">UPLOAD VIA EXCELSHEET</H4>
						<div class="form-group">

							<input type="file" class="file-data btn btn-primary btn-block" name="file" onchange="checkfile(this);"accept="application/xls" required="required"><br> <br> 
							<button type="submit" class="btn btn-primary btn-block"
								name="action">Upload</button>
						</div>

					</div>


				</form>
			</div>
		</div>
	</div>



</body>
</html>
<%@include file="footer.html"%>