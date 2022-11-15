<%@ page isELIgnored="false" %>
<%@ page import="com.hospital.model.Nurse" %>
<%@ page import="com.hospital.common.CommonMethods" %>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix ="c" %>

<% if (CommonMethods.IsSessionExpired(request, response)) { return; } %>

<jsp:include page="./main_content_header.jsp" />


<div class="row">
    <%@ include file="./menu_admin.jsp" %>
    <!---- Content Ares Start  -------->
    <div class="col-md-10 maincontent">

        <!----------------   Menu Tab Start   --------------->
        <div class="panel panel-default contentinside">
            <div class="panel-heading">Manage Nurse</div>

            <!----------------   Panel body Start   --------------->
            <div class="panel-body">
                <ul class="nav nav-tabs panelBodyItem">
                    <li role="presentation"><a href="#panelBodyItemlist">Nurse List</a></li>
                    <li role="presentation"><a href="#addpanelBodyItem">Add Nurse</a></li>
                    <li role="presentation"><a href="#nurseRoomCrossJoin">Nurse Room Cross Join</a></li>
                </ul>

                <!----------------   Display Nurse Data List Start  --------------->
                <div id="panelBodyItemlist" class="switchgroup">
                    <table class="table table-bordered table-hover">
                        <tr class="active">
                            <td>Nurse ID</td>
                            <td>Nurse Name</td>
                            <td>Email</td>
                            <td>Address</td>
                            <td>Phone No.</td>
                            <td>Room To Attend</td>
                            <td>Options</td>
                        </tr>
                        <c:forEach items="${nurseBean.list}" var="nurse">
                            <tr>
                                <td>
                                    ${nurse.id}
                                </td>
                                <td>
                                    ${nurse.name}
                                </td>
                                <td>
                                    ${nurse.email}
                                </td>
                                <td>
                                    ${nurse.address}
                                </td>
                                <td>
                                    ${nurse.phone}
                                </td>
                                <td>
                                    ${nurse.room.roomNo}
                                </td>
                                <td>
                                    <button type="button" class="btn btn-primary" data-toggle="modal"
                                        data-target="#myModal${nurse.id}"><span class="glyphicon glyphicon-wrench"
                                            aria-hidden="true"></span></button>
                                    <a href="./nurse-delete?id=${nurse.id}"
                                        onclick="return confirmDelete()" class="btn btn-danger"><span
                                            class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
                                </td>
                            </tr>
                        </c:forEach>  
                    </table>
                </div>
                <!----------------   Display Nurse Data List Ends  --------------->

                <!------ Edit Nurse Modal Start ---------->
                <c:forEach items="${nurseBean.list}" var="nurse">
                    <div class="modal fade" id="myModal${nurse.id}" tabindex="-1" role="dialog"
                        aria-labelledby="myModalLabel">

                        <div class="modal-dialog" role="document">
                            <div class="modal-content">


                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                            aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="myModalLabel">Edit Nurse Information</h4>
                                </div>

                                <div class="modal-body">
                                    <div class="panel panel-default">
                                        <div class="panel-body">
                                            <form class="form-horizontal" action="./nurse-edit" method="POST">

                                                <div class="form-group">
                                                    <label class="col-sm-4 control-label">Nurse Id</label>
                                                    <div class="col-sm-4">
                                                        <input type="number" class="form-control" name="id"
                                                            value="${nurse.id}" readonly="readonly">
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="col-sm-4 control-label">Name</label>
                                                    <div class="col-sm-4">
                                                        <input type="text" class="form-control" name="name"
                                                            value="${nurse.name}">
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="col-sm-4 control-label">Email</label>
                                                    <div class="col-sm-4">
                                                        <input type="text" class="form-control" name="email"
                                                            value="${nurse.email}">
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="col-sm-4 control-label">Address</label>
                                                    <div class="col-sm-4">
                                                        <input type="text" class="form-control" name="address" value="${nurse.address}">
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="col-sm-4 control-label">Phone</label>
                                                    <div class="col-sm-4">
                                                        <input type="text" class="form-control" name="phone"
                                                            value="${nurse.phone}">
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="col-sm-4 control-label">Room To Attend</label>
                                                    <div class="col-sm-4">
                                                        <select class="form-control" name="roomId">
                                                            <option value="${nurse.room.id}" selected="selected" disabled hidden>
                                                                ${nurse.room.roomNo}
                                                            </option>
                                                            <c:forEach items="${roomBean.groupedList}" var="room">
                                                                <option value="${room.id}">
                                                                    ${room.roomNo}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>

                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default"
                                                        data-dismiss="modal">Close</button>
                                                    <input type="submit" class="btn btn-primary" value="Update"></button>
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

                <!----------------   Add Nurse Start   --------------->
                <div id="addpanelBodyItem" class="switchgroup">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <form class="form-horizontal" action="./nurse-add" method="POST">

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Nurse Id:</label>
                                    <div class="col-sm-10">
                                        <input type="number" class="form-control" name="id"
                                            placeholder="Nurse ID auto generated" readonly>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Name</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="name" placeholder="Name">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Email</label>
                                    <div class="col-sm-10">
                                        <input type="Email" class="form-control" name="email" placeholder="Email">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Address</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="address" placeholder="Address">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Phone</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="phone" placeholder="Phone No.">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Room To Attend</label>
                                    <div class="col-sm-10">
                                        <select class="form-control" name="roomId">
                                            <option value="none" selected disabled hidden>Select a Room Number</option>
                                            <c:forEach items="${roomBean.list}" var="room">
                                                <option value="${room.id}">
                                                    ${room.roomNo}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                        <button type="submit" class="btn btn-primary">Add Nurse</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <!----------------   Add Nurse Ends   --------------->

                <!----------------   Cross Join   --------------->
                <div id="nurseRoomCrossJoin" class="switchgroup">
                    <table class="table table-bordered table-hover">
                        <tr class="active">                            
                            <td>Nurse Name</td>                            
                            <td>Room To Attend</td>                            
                        </tr>

                        <c:forEach items="${nurseBean.leftJoinedLIst}" var="nurse">
                            <tr>
                                <td>
                                    ${nurse.name}
                                </td>
                                <td>
                                    ${nurse.roomNo}
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <!----------------   Cross Join Ends   --------------->

            </div>
            <!----------------   Panel body Ends   --------------->
        </div>
        <!----------------   Menu Tab Ends   --------------->
    </div>
    <!---- Content Ares Ends  -------->
</div>
<script>
    function confirmDelete() {
        return confirm("Do You Really Want to Delete the Room?");
    }
</script>
<jsp:include page="./main_content_footer.jsp" />