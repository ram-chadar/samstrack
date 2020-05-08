<!DOCTYPE html>
<%@include file="prevent.jsp" %>

<%@ page language="java" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ page language="java" import="java.util.*"%>


<html>
  <head>

    <meta name="viewport" content="=600, initial-scale=1">
       <link rel="stylesheet" type="text/css" href="customCss/studentRegistration.css">
    
  </head>
  <body>
         
    <div class="container">
      <div class="row">
          <div class="col-md-12">
            <form action="UpdateStudent" name="updateStudent" class="form-data">
            
            <input type="hidden" name="update_accYear" value="${accYear}">
            <input type="hidden" name="update_branch" value="${branchName}">
            <input type="hidden" name="update_year" value="${year}">
            <input type="hidden" name="update_division" value="${division}"> 
            
            
            
           <%--  <c:forEach items="${student_data}" var="student">  --%>
              <div class="row">
                <div class="col-md-4 col-xs-12">
                  <div class="form-resp">
                    <H4 class="text-center">MANDATORY FIELDS</H4>
                    
                    <div class="form-group">
                      <label for="accYear">Accadmin Year</label> 
                      <input type="number" class="form-control" id="accYear" placeholder="Accadmic Year" name="accYear" value="${accYear}">
                    </div>
                    
                    <div class="form-group">
                      <label for="branch">Select Branch</label> 
                      <select class="form-control form-control-md" id="branch" name="branch">
                          
                               <option disabled="disabled" value="">Select Branch</option>
										<option
											value="<%=(String) session.getAttribute("userBranch")%>"><%=(String) session.getAttribute("userBranch")%>
										</option>
                      </select>
                    </div>

                    <div class="form-group">
                      <label for="year">Year</label> 
                      <select class="form-control form-control-md" id="branchOption" name="year">
                   	   <option value="${year}">${year}</option>
                   	    <option value="FE">FE</option>
                        <option value="SE">SE</option>
                        <option value="TE">TE</option>
                        <option value="BE">BE</option>
                      </select>
                    </div>

                    <div class="form-group">
                      <label for="division">Divison</label> 
                      <select class="form-control form-control-md" id="branchOption" name="division">
                  
						<option value="${division}">${division}</option>
				
                       
                        <option value="A">A</option>
                        <option value="B">B</option>
                        <option value="C">C</option>
                        <option value="D">D</option>
                      </select>
                    </div>

                    <div class="form-group">
                      <label for="rollNo">Roll No</label> 
                      <input type="text" class="form-control" id="rollno" placeholder="Student's Roll no" name="rollNo" value="${rollNo }" readonly="readonly">
                    </div>
                  </div>
                </div>

                <div class="col-md-4 col-xs-12 ">
                  <div class="form-resp">
                    <H4>STUDENT DETAILS</H4>
                    <div class="form-group">
                      <label for="name">Name</label> 
                      <input type="text" class="form-control" id="studentName" placeholder="Student Name" name="studentName" value="${studentName }">
                    </div>
                    <div class="form-group">
                      <label for="gender">Gender</label> 
                        <select class="form-control form-control-md" id="gender" name="gender">
                        
					
						<option value="${gender}">${gender}</option>
					
					
				 
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                        <option value="NOTA">NOTA</option>
                      </select>
                    </div>

                    <div class="form-group">
                      <label for="sEmail">Email</label> 
                      <input type="email" class="form-control" id="sEmail" placeholder="Student Email" name="mail" value="${mail}">
                    </div>

                      <div class="form-group">
                        <label for="sPhone">Phone</label> 
                        <input type="number" class="form-control" id="sPhone" placeholder="Student Mobile" name="studentPhone" value="${studentPhone }">
                      </div>

                      <div class="form-group">
                        <label for="sAddress">Address</label> 
                        <textarea class="form-control" rows="5" id="comment" name="address">${address}</textarea>
                      </div>
                  </div>
                </div>

                <div class="col-md-4 col-xs-12">
                  <div class="form-resp">
                    <H4>PARENT DETAILS</H4>
                    <div class="form-group">
                      <label for="fathername">Father Name</label> 
                      <input type="text" class="form-control" id="fName" placeholder="Father's Name" name="fatherName" value="${fatherName }">
                    </div>
                    <div class="form-group">
                      <label for="mothername">Mother Name</label> 
                      <input type="text" class="form-control" id="mName" placeholder="Mother's Name" name="motherName" value="${motherName }">
                    </div>

                    <div class="form-group">
                      <label for="sPhone">Phone</label> 
                      <input type="number" class="form-control" id="cPhone" placeholder="Phone No." name="parentPhone" value="${parentPhone }">
                    </div>

                        <button type="submit" class="btn btn-primary btn-block" value="Update" name="action">Update</button>
                        
                  </div>
                </div>
               
              </div>
          
            </form>
        </div>
      </div>
    </div>


  </body>
</html>
<%@include file="footer.html" %>
