	<link rel="icon" type="image/png" href="login/images/icons/logo.ico"/>
	
<!-- 	 <link rel="stylesheet" type="text/css" href="customCss/loader.css">
	<script src="onlineJs/loader.js"></script>
	  <div id="loader" class="center"></div> --> 
	
<!-- <script type="text/javascript" >
   function preventBack(){window.history.forward();}
    setTimeout("preventBack()", 0);
    window.onunload=function(){null};
</script>  -->

<%

response.setHeader("Cache-Control","no-cache,must-revalidate,no-store");
	if(session.getAttribute("user")==null )
	response.sendRedirect("index.jsp");	

	else if(session.getAttribute("userType").equals("Hod"))
	{
	%>
		<%@include file="hodMenu.jsp" %>
		<marquee><b>S A M S &nbsp;&nbsp;  T R A C K  &nbsp;&nbsp;  H O D &nbsp;&nbsp;L O G I N</b></marquee>
		
	<% 		
	}
	else if(session.getAttribute("userType").equals("Teacher"))
	{
	%>
		<%@include file="teacherMenu.jsp" %>
		<marquee><b>S A M S &nbsp;&nbsp;  T R A C K  &nbsp;&nbsp; T E A C H E R &nbsp;&nbsp;L O G I N</b></marquee>
		
	<% 	
	}
	
	else if(session.getAttribute("userType").equals("Principle"))
	{
		%>
			<%@include file="principleMenu.jsp" %>
			<marquee><b>S A M S &nbsp;&nbsp;  T R A C K  &nbsp;&nbsp; P R I N C I P L E &nbsp;&nbsp;L O G I N</b></marquee>
			
		<% 	
	}

%>


  <%String msg=(String)request.getAttribute("msg");

if(msg!=null)
{%>
<script type="text/javascript">
	alert("${msg}");
  </script>
<% 
}

%> 

  <%String msg1=(String)request.getParameter("msg1");
if(msg1!=null)
{%>
<script type="text/javascript">
alert("${msg1}");
  </script>
<% 

}

%> 

<%
		String mailMsg = (String) request.getAttribute("mailMsg");

		if (mailMsg != null) {
	%>
	<script type="text/javascript">
		alert("${mailMsg}");
	</script>
	<%
	
		}
	%>