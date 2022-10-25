<%@ page import="com.hospital.controllers.DepartmentController" %>
<%@ page import="com.hospital.model.Department" %>
<%@ page import="com.hospital.common.CommonMethods" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ page import="java.util.List" %>
<%@ include file="./main_content_header.jsp" %>
<%! DepartmentController departmentController = new DepartmentController();
    private long generateID(List<Department> departments){
            if (!departments.isEmpty()) {
                return departments.get(departments.size()-1).getId()+1;
            }else
                return 1;
        }
%>
<%
    if (CommonMethods.IsSessionExpired(request, response)) {
                return;
            }
%>
<div class="row">
  <%@ include file="./menu_admin.jsp" %>
  <!-------   Content Area start  --------->
  <div class="col-md-10 maincontent">
      <!-----------  Content Menu Tab Start   ------------>
      <div class="panel panel-default contentinside">
          <div class="panel-heading">Manage Department</div>
          <!----------------   Panel Body Start   --------------->
          <div class="panel-body">
              <ul class="nav nav-tabs doctor">
                  <li role="presentation"><a href="#doctorlist">Department List</a></li>
                  <li role="presentation"><a href="#adddoctor">Add Department</a></li>
              </ul>
              <!----------------   Display Department Data List start   --------------->

              <div id="doctorlist" class="switchgroup">
                <table class="table table-bordered table-hover">
                     <tr class="active">
                         <td>Department ID</td>
                         <td>Department Name</td>
                         <td>Department Description</td>
                         <td>Options</td>
                     </tr>
                    <%
                        List<Department> departments = departmentController.list((Connection) application.getAttribute("dbConnection"), new Department());
                        pageContext.setAttribute("departments", departments);
                    %>
                    <c:forEach items="${departments}" var="department">
                    <c:set var = "id" value = "${department.id}" />
                        <tr>
                             <td><fmt:formatNumber value = "${id}" type = "number" minFractionDigits = "10" /></td>
                             <td>${department.deptName}</td>
                             <td>${department.deptDesc}</td>
                             <td>
                                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal${department.deptName}">
                                    <span class="glyphicon glyphicon-wrench" aria-hidden="true"></span>
                                </button>
                                <a data-confirm="Are you sure?" href="./department-delete?deptId=${department.deptName}" class="btn btn-danger" onclick="return confirmDelete()">
                                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                </a>
                             </td>
                        </tr>
                    </c:forEach>
                </table>
              </div>
              <!----------------   Display Department Data List ends   --------------->

              <!------ Edit Department Modal Start ---------->
              <%
                for (Department department : departments) {
              %>
               <div class="modal fade" id="myModal<%= department.getId() %>" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                     <div class="modal-dialog" role="document">
                         <div class="modal-content">
                             <div class="modal-header">
                                 <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                 <h4 class="modal-title" id="myModalLabel">Edit Department Information </h4>
                             </div>
                             <div class="modal-body">
                                 <div class="panel panel-default">
                                     <div class="panel-body">
                                         <form class="form-horizontal" action="./department-edit" method="post">
                                             <!----- <input type="hidden" name="id" value="<%= department.getId() %>"> ---->
                                             <div class="form-group">
                                                 <label class="col-sm-4 control-label">Department ID</label>
                                                 <div class="col-sm-4">
                                                     <input type="number" class="form-control" name="id" value="<%= department.getId() %>" readonly="readonly">
                                                 </div>
                                             </div>
     
                                             <div class="form-group">
                                                 <label class="col-sm-4 control-label">Department Name</label>
                                                 <div class="col-sm-4">
                                                     <input type="text" class="form-control" name="deptName" value="<%= department.getDeptName() %>">
                                                 </div>
                                             </div>
        
                                             <div class="form-group">
                                                 <label class="col-sm-4 control-label">Department Description</label>
                                                 <div class="col-sm-4">
                                                     <input type="text" class="form-control" name="deptDesc" value="<%= department.getDeptDesc() %>">
                                                 </div>
                                             </div>
        
                                             <div class="modal-footer">
                                                 <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                    <input type="submit" class="btn btn-primary" value="Update">
                                                 </button>
                                             </div>
                                         </form>
                                     </div>
                                 </div>
                             </div>
                         </div>
                     </div>
                </div>
              <% } %>
              <!----------------   Modal ends here  --------------->

              <!----------------   Add Department Start   --------------->
              <div id="adddoctor" class="switchgroup">
                  <div class="panel panel-default">
                      <div class="panel-body">
                          <form class="form-horizontal" action="./department" method="post">
                              <div class="form-group">
                                  <label class="col-sm-4 control-label">Department ID</label>
                                  <div class="col-sm-4">
                                      <input type="number" class="form-control" name="deptId" placeholder="<%= this.generateID(departments) %>" readonly>
                                  </div>
                              </div>

                              <div class="form-group">
                                  <label class="col-sm-4 control-label">Department Name</label>
                                  <div class="col-sm-4">
                                      <input type="text" class="form-control" name="deptName" placeholder="Enter Department Name">
                                  </div>
                              </div>

                              <div class="form-group">
                                  <label class="col-sm-4 control-label">Department Description</label>
                                  <div class="col-sm-4">
                                      <input type="text" class="form-control" name="deptDesc" placeholder="Enter Department Description here...">
                                  </div>
                              </div>
                              <div class="form-group">
                                  <div class="col-sm-offset-4 col-sm-10">
                                      <button type="submit" class="btn btn-primary">Add Department</button>
                                  </div>
                              </div>
                          </form>
                      </div>
                  </div>
              </div>
              <!----------------   Add Department Ends   --------------->
          </div>
          <!----------------   Panel Body Ends   --------------->
      </div>
      <!-----------  Content Menu Tab Ends   ------------>
  </div>
  <!-------   Content Area Ends  --------->
</div>
<%@ include file="./main_content_footer.jsp" %>