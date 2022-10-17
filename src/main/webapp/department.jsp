<%@ include file="./main_content_header.jsp" %>
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
              createDepartmentTable()
              </div>
              <!----------------   Display Department Data List ends   --------------->

              <!------ Edit Department Modal Start ---------->
             createEditModal(departments)
              <!----------------   Modal ends here  --------------->

              <!----------------   Add Department Start   --------------->
              <div id="adddoctor" class="switchgroup">
                  <div class="panel panel-default">
                      <div class="panel-body">
                          <form class="form-horizontal" action="./department" method="post">
                              <div class="form-group">
                                  <label class="col-sm-4 control-label">Department ID</label>
                                  <div class="col-sm-4">
                                      <input type="number" class="form-control" name="deptId" placeholder="this.generateID(departments)" readonly>
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