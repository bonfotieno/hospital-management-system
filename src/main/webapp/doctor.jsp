<%@ page isELIgnored="false" %>
<%@ page import="com.hospital.model.Doctor" %>
<%@ page import="com.hospital.common.CommonMethods" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix ="c" %>

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
                        <c:forEach items="${doctorBean.list}" var="doctor">
                            <tr>
                                <td>
                                    ${doctor.id}
                                </td>
                                <td>
                                    ${doctor.name}
                                </td>
                                <td>
                                    ${doctor.email}
                                </td>
                                <td>
                                    ${doctor.address}
                                </td>
                                <td>
                                    ${doctor.phone}
                                </td>
                                <td>
                                    ${doctor.department.deptName}
                                </td>
                                <td>
                                    <a href="#"><button type="button" class="btn btn-primary" data-toggle="modal"
                                            data-target="#myModal${doctor.id}"><span class="glyphicon glyphicon-wrench"
                                                aria-hidden="true"></span></button></a>
                                    <a href="./doctor-delete?id=${doctor.id}"
                                        onclick="return confirmDelete()" class="btn btn-danger"><span
                                            class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <!----------------   Display Doctor Data List Ends  --------------->

                <!------ Doctor Edit Info Modal Start Here ---------->
                <c:forEach items="${doctorBean.list}" var="doctor">
                <div class="modal fade" id="myModal${doctor.id}" tabindex="-1" role="dialog"
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
                                                    <input type="number" class="form-control" name="id"
                                                        placeholder="Doctor ID" value="${doctor.id}"
                                                        readonly="readonly">
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Name</label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="form-control" name="name"
                                                        value="${doctor.name}" placeholder="Name">
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Email</label>
                                                <div class="col-sm-10">
                                                    <input type="email" class="form-control" name="email"
                                                        value="${doctor.email}" placeholder="Email">
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Password</label>
                                                <div class="col-sm-10">
                                                    <input type="password" class="form-control" name="password"
                                                        value="${doctor.password}" placeholder="Password Here">
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Address</label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="form-control" name="address"
                                                        value="${doctor.address}" placeholder="Address">
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Phone</label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="form-control" name="phone"
                                                        value="${doctor.phone}" placeholder="Phone No.">
                                                </div>
                                            </div>


                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Department</label>
                                                <div class="col-sm-10">

                                                    <select class="form-control" name="departmentId">
                                                        <option value="${doctor.department.id}" selected="selected">
                                                            ${doctor.department.deptName}
                                                        </option>
                                                        <c:forEach items="${departmentBean.list}" var="department">
                                                            <option value="${department.id}">
                                                                ${department.deptName}
                                                            </option>
                                                        </c:forEach>
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
                </c:forEach>
                <!----------------   Modal ends here  --------------->

                <!----------------   Add Doctor Start   --------------->
                <div id="adddoctor" class="switchgroup">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <form class="form-horizontal" action="./doctor-add" method="post">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Doctor Id:</label>
                                    <div class="col-sm-10">
                                        <input type="number" class="form-control" name="id"
                                            placeholder="Doctor ID Auto Generated" readonly>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Name</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="name"
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
                                        <input type="password" class="form-control" name="password"
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
                                        <select class="form-control" name="departmentId">
                                            <option value="none" selected disabled hidden>Select a Department</option>
                                                <c:forEach items="${departmentBean.list}" var="department">
                                                    <option value="${department.id}">
                                                        ${department.deptName}
                                                    </option>
                                                </c:forEach>
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
<script>
    function confirmDelete()
    {
        return confirm("Do You Really Want to Delete Doctor");
    }
</script>
<%@ include file="./main_content_footer.jsp" %>