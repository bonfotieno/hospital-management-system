<%@ page isELIgnored="false" %>
<%@ page import="com.hospital.model.Doctor" %>
<%@ page import="com.hospital.common.CommonMethods" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<jsp:useBean id="doctorController" class="com.hospital.controllers.DoctorController" />

<%
    if (CommonMethods.IsSessionExpired(request, response)) {
                return;
            }
%>

<%@ include file="./main_content_header.jsp" %>
<div class="row">
    <%@ include file="./menu_admin.jsp" %>
    <!---- Content Ares Start  -------->
    <div class="col-md-10 maincontent">
        <!----------------   Menu Tab   --------------->
        <div class="panel panel-default contentinside">
            <div class="panel-heading">Manage Doctor</div>
            <!----------------   Panel body Start   --------------->
            <div class="panel-body">
                <ul class="nav nav-tabs doctor">
                    <li role="presentation"><a href="#doctorlist">Doctor List</a></li>
                    <li role="presentation"><a href="#adddoctor">Add Doctor</a></li>
                </ul>
                <!----------------   Display Doctor Data List Start  --------------->
                <div id="doctorlist" class="switchgroup">
                    <table class="table table-bordered table-hover">
                        <tr class="active">
                            <td>Doctor ID</td>
                            <td>Doctor Name</td>
                            <td>Email</td>
                            <td>Address</td>
                            <td>Phone No.</td>
                            <td>Department</td>
                            <td>Options</td>
                        </tr>
                        <%
                            List<Doctor> doctors = doctorController.list((Connection) application.getAttribute("dbConnection"), new Doctor());
                            for (Doctor doctor : doctors){
                        %>
                        <tr>
                            <td>
                                <%= doctor.getId() %>
                            </td>
                            <td>
                                <%= doctor.getName() %>
                            </td>
                            <td>
                                <%= doctor.getEmail() %>
                            </td>
                            <td>
                                <%= doctor.getAddress() %>
                            </td>
                            <td>
                                <%= doctor.getPhone() %>
                            </td>
                            <td>
                                <%= doctor.getDepartmentName() %>
                            </td>
                            <td>
                                <a href="#"><button type="button" class="btn btn-primary" data-toggle="modal"
                                        data-target="#myModal<%= doctor.getId() %>"><span class="glyphicon glyphicon-wrench"
                                            aria-hidden="true"></span></button></a>
                                <a href="delete_doct_validation.jsp?doctId=<%= doctor.getId() %>"
                                    onclick="return confirmDelete()" class="btn btn-danger"><span
                                        class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
                            </td>
                        </tr>
                        <% } %>
                    </table>
                </div>
                <!----------------   Display Doctor Data List Ends  --------------->

                <!------ Doctor Edit Info Modal Start Here ---------->
                <%
                    for (Doctor doctor : doctors) {
                  %>
                <div class="modal fade" id="myModal<%= doctor.getId() %>" tabindex="-1" role="dialog"
                    aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">

                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"
                                    aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="myModalLabel">Edit Doctor Information</h4>
                            </div>

                            <div class="modal-body">
                                <div class="panel panel-default">
                                    <div class="panel-body">
                                        <form class="form-horizontal" action="./doctor-edit" method="post">
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Doctor Id:</label>
                                                <div class="col-sm-10">
                                                    <input type="number" class="form-control" name="doctid"
                                                        placeholder="Doctor ID" value="<%= doctor.getId() %>"
                                                        readonly="readonly">
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Name</label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="form-control" name="doctname"
                                                        value="<%= doctor.getName() %>" placeholder="Name">
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Email</label>
                                                <div class="col-sm-10">
                                                    <input type="email" class="form-control" name="email"
                                                        value="<%= doctor.getEmail() %>" placeholder="Email">
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Password</label>
                                                <div class="col-sm-10">
                                                    <input type="password" class="form-control" name="pwd"
                                                        value="passwoord_here" placeholder="Password">
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Address</label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="form-control" name="address"
                                                        value="<%= doctor.getAddress() %>" placeholder="Address">
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Phone</label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="form-control" name="phone"
                                                        value="<%= doctor.getPhone() %>" placeholder="Phone No.">
                                                </div>
                                            </div>


                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Department</label>
                                                <div class="col-sm-10">

                                                    <select class="form-control" name="dept">
                                                        <option selected="selected">
                                                            <%= doctor.getDepartmentName() %>
                                                        </option>
                                                        <option> Neurology</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default"
                                                    data-dismiss="modal">Close</button>
                                                <input type="submit" class="btn btn-primary"
                                                    value="Update"></button>
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

                <!----------------   Add Doctor Start   --------------->
                <div id="adddoctor" class="switchgroup">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <form class="form-horizontal" action="./doctor-add" method="post">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Doctor Id:</label>
                                    <div class="col-sm-10">
                                        <input type="number" class="form-control" name="doctid"
                                            placeholder="Doctor ID Auto Generated" readonly>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Name</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="doctname"
                                            placeholder="Name" required="required">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Email</label>
                                    <div class="col-sm-10">
                                        <input type="email" class="form-control" name="email"
                                            placeholder="Email" required="required">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Password</label>
                                    <div class="col-sm-10">
                                        <input type="password" class="form-control" name="pwd"
                                            placeholder="Password" required="required">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Address</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="address" placeholder="Address"
                                            required="required">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Phone</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="phone"
                                            placeholder="Phone No." required="required">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Department</label>
                                    <div class="col-sm-10">
                                        <select class="form-control" name="dept">
                                            <option selected="selected">Select Department</option>
                                            <%  Connection c = (Connection) application.getAttribute("dbConnection");
                                                String deptName; PreparedStatement ps; ResultSet resultSet;
                                                ps=c.prepareStatement("select name from departments",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                                                resultSet=ps.executeQuery();
                                                while(resultSet.next()) {
                                                    deptName=resultSet.getString(1); %>
                                                    <option value="<%=deptName%>">
                                                        <%= deptName %>
                                                    </option>
                                                <% } %>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                        <button type="submit" class="btn btn-primary">Add Doctor</button>
                                    </div>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
                <!----------------   Add Doctor Ends   --------------->
            </div>
            <!----------------   Panel body Ends   --------------->
        </div>
        <!-----------  Content Menu Tab Ends   ------------>
    </div>
    <!-------   Content Area Ends  --------->
</div>
<%@ include file="./main_content_footer.jsp" %>