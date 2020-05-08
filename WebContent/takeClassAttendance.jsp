<!DOCTYPE html>
<%@include file="prevent.jsp" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" import="java.util.*"%>

<html>
  <head>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="customCss/subjectList.css">
<link rel="stylesheet" type="text/css" href="customCss/branchSubDetail.css">
<link rel="stylesheet" type="text/css" href="onlineCss/pretty-checkbox.min.css">
<link rel="stylesheet" type="text/css" href="onlineCss/cloudflare.css">
<link rel="stylesheet" type="text/css" href="onlineCss/dataTables.bootstrap4.min.css">
<script type="text/javascript" src="onlineJs/jquery-3.3.1.js"></script> 
<script type="text/javascript" src="onlineJs/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="onlineJs/dataTables.bootstrap4.min.js"></script>
 
 
<style type="text/css">
.bs-example {
	margin: 2px;
}
</style>
	

    
  </head>
  <body>

<div class="container">
      <div class="row">
          <div class="col-md-10 offset-md-1">
            <form action="TakeClassAttendance" name="takeClassAttendance" class="form-data" method="post">
            <h3 class="text-center">Take Theory Attendance</h3>
              <div class="row">
                <div class="col-md-5 col-xs-12">

                  <div class="form-resp">
                    <H4 class="text-center">MANDATORY FIELDS</H4>
                    

                    <div class="form-group">
                     <label for="datetime">Date And Time</label> 
                            <input type="datetime-local" class="form-control" name="datetime" value="${dateTime}">

                    </div>

                     <div class="form-group">
                      <label for="branch">Branch</label> 
                      <select class="form-control form-control-md" id="bname" name="branch">
                     
										<option
											value="<%=(String) session.getAttribute("userBranch")%>"><%=(String) session.getAttribute("userBranch")%>
										</option>
                      </select>
                    </div>

                     <div class="form-group">
                      <label for="division">Divison</label> 
                      <select class="form-control form-control-md" id="division" name="division">
                      <%
                      String divsion=(String)request.getAttribute("division");
                    		  if(divsion!=null)
                    		  {
                    			%>
                    			<option value="<%=divsion%>"><%=divsion%></option>
                    			<%  
                    		  }
                    		  else
                    		  {
                    			%>
                    <option disabled value="">Select Division</option>
                        <option value="A">A</option>
                        <option value="B">B</option>
                        <option value="C">C</option>
                        <option value="D">D</option>
                      			<%   
                    		  }
                      %>
                     
                      </select>
                    </div>

                    <div class="form-group">
                     <label for="sem">Sem</label> 
                        <select class="form-control form-control-md" id="sem" name="sem">
                        
                        <%
                        String sem=(String)request.getAttribute("sem");
                    		 if(sem!=null)
                    		 {
                    			%>
                    			<option value="<%=sem%>"><%=sem%></option>
                    			<% 
                    		 }
                    		 else
                    		 {
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
                    			<% 
                    		 }
                        %>
                        
	                   
                      </select> 
                    </div>

                    <div class="form-group">
                      <label for="subject">Subject</label> 
                      <select class="form-control form-control-md" id="subject" name="subject">
                      <%
                      String subject=(String)request.getAttribute("subject");
                    		  if(subject!=null)
                    		  {
                    			  %>
                    			  <option value="<%=subject%>"><%=subject%></option>
                    			  <%
                    		  }
                    		  else
                    		  {
                    			%>
                    			<option value="">Select Subject</option>
                    			<%  
                    		  }
                      %>
                      </select>
                    </div>
				<div>
                        <button type="submit" class="btn btn-primary btn-block " value="Get Roll.NO" name="action">Get Roll.NO</button>
                </div>
                   
                  </div>
              </div>
              
                 <div class="col-md-7 col-xs-12">
                  <div class="form-resp">
                    <div class="button-group ">
                        
                        <button type="button" class="btn btn-primary btn-block" value="Select All" onclick='selectAll()'>Select All</button>
                       <button type="button" class="btn btn-primary btn-block " value="Un-Select All" onclick='UnSelectAll()'>UnSelect All</button>
                    </div>
                 <div class="table-responsive">
                    <div class="table-data">
                    <table class="table table-dark table-striped table-bordered table-hover dataTables-example" border="1" id="example" style="width: 100%">
                    
    <thead>
      <tr>
        <th class="text-center">Roll.NO</th>
        <th class="text-center"> Student Name</th>
        
      </tr>
    </thead>
    <tbody><!--  style="overflow: auto;height: 200px;" -->
    <c:forEach items="${students}" var="student">
       <tr>
       
		<td class="text-center">
		  <div class="pretty p-image p-plain">
       		<input type="checkbox" id="rollNo" checked="checked" name="rollNo" value="<c:out value="${student.rollNo}" />" />
        		<div class="state">
            		<img class="image" src="img/check.png">               
           			 <label style="color: #ffffff"><c:out value="${student.rollNo}" /></label>
        		</div>                                                           
   		 </div>
    	</td>																			<!-- Roll no and Check Box  -->
    
     	<td class="text-center"><c:out value="${student.studentName}" /></td>			 <!-- Student Name -->
    
       
      </tr>
				
    </c:forEach> 
      
      
    </tbody>
  </table>
                   </div>                   
                  </div><br>
                        <button type="submit" class="btn btn-primary btn-block " value="SUBMIT ATTENDANCE" name="action">SUBMIT ATTENDANCE</button>
                       <button type="submit" class="btn btn-danger btn-block" value="Delete Attendance" name="action" onclick=" if(!confirm('Are You Sure To Delete')) return false">Delete Attendance</button>
                    
                </div> 
                
          
              </div>
         </div>
                  </form>
              </div>
          </div>
      </div>
        <script>
      function selectAll(){
        var items=document.getElementsByName('rollNo');
        for(var i=0; i<items.length; i++){
          if(items[i].type=='checkbox')
            items[i].checked=true;
        }
      }
      
      function UnSelectAll(){
        var items=document.getElementsByName('rollNo');
        for(var i=0; i<items.length; i++){
          if(items[i].type=='checkbox')
            items[i].checked=false;
        }
      }     
   
    $(document).ready(function () {             //get subject
        $("#sem").on("change", function () {
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
					
  </body>
  </html>
  <%@include file="footer.html" %>
  
