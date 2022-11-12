<%@ page isELIgnored="false" %>
<%@ page import="com.hospital.model.Room" %>
<%@ page import="com.hospital.common.CommonMethods" %>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix ="c" %>

<% if (CommonMethods.IsSessionExpired(request, response)) { return; } %>

<jsp:include page="./main_content_header.jsp" />

<div class="row">
    <%@ include file="./menu_admin.jsp" %>
    <!-------   Content Area start  --------->
    <div class="col-md-10 maincontent">

        <!-----------  Content Menu Tab Start   ------------>
        <div class="panel panel-default contentinside">
            <div class="panel-heading">Manage Room</div>

            <!----------------   Panel Body Start   --------------->
            <div class="panel-body">
                <ul class="nav nav-tabs panelBodyItem">
                    <li role="presentation"><a href="#panelBodyItemlist">Room List</a></li>
                    <li role="presentation"><a href="#addpanelBodyItem">Add Room</a></li>
                </ul>

                <!----------------   Display Room Data List start   --------------->

                <div id="panelBodyItemlist" class="switchgroup">
                    <table class="table table-bordered table-hover">
                        <tr class="active">
                            <td>Room Number</td>
                            <td>Bed No</td>
                            <td>Availability Status</td>
                            <td>Options</td>
                        </tr>
                        <c:forEach items="${roomBean.list}" var="room">
                            <tr>
                                <td>${room.roomNo}</td>
                                <td>${room.bedNo}</td>
                                <td>${room.roomStatus}</td>
                                <td>
                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal${room.uniqueID}">
                                        <span class="glyphicon glyphicon-wrench" aria-hidden="true"></span>
                                    </button>
                                    <a href="./room-delete?id=${room.id}" class="btn btn-danger" onclick="return confirmDelete()">
                                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <!----------------   Display Room Data List ends   --------------->

                <!------ Edit Room Modal Start ---------->
                <c:forEach items="${roomBean.list}" var="room">
                    <div class="modal fade" id="myModal${room.uniqueID}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">

                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                       <span aria-hidden="true">&times;</span>
                                    </button>
                                    <h4 class="modal-title" id="myModalLabel">Edit Room Information</h4>
                                </div>

                                <div class="modal-body">
                                    <div class="panel panel-default">
                                        <div class="panel-body">
                                            <form class="form-horizontal" action="./room-edit" method="post">
                                                <input type="hidden" name="id" value="${room.id}">

                                                <div class="form-group">
                                                    <label class="col-sm-4 control-label">Room No</label>
                                                    <div class="col-sm-4">
                                                        <input type="text" class="form-control"
                                                            name="roomNo"
                                                            value="${room.roomNo}"
                                                            readonly="readonly">
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label
                                                        class="col-sm-4 control-label">Bed No</label>
                                                    <div class="col-sm-4">
                                                        <input type="text"
                                                            class="form-control"
                                                            name="bedNo" value="${room.bedNo}"
                                                            readonly>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label
                                                        class="col-sm-4 control-label">Status</label>
                                                    <div class="col-sm-4">
                                                        <select class="form-control" name="roomStatus">
                                                            <option value="${room.roomStatus}" selected disabled hidden>${room.roomStatus}</option>
                                                            <option value="Available">Available</option>
                                                            <option value="Unavailable">Unavailable</option>
                                                        </select>
                                                    </div>
                                                </div>

                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                    <input type="submit"
                                                        class="btn btn-primary"
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

                <!----------------   Add Room Start   --------------->
                <div id="addpanelBodyItem" class="switchgroup">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <form class="form-horizontal" action="./room-add" method="post">

                                <div class="form-group">
                                    <label class="col-sm-4 control-label">Room No</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" name="roomNo" placeholder="Room Number" required>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-4 control-label">Bed No</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" name="bedNo" placeholder="Bed No" required>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-4 control-label">Availability Status</label>
                                    <div class="col-sm-4">
                                        <select class="form-control" name="roomStatus">
                                            <option value="none" selected disabled hidden>Select a Status</option>
                                            <option value="Available">Available</option>
                                            <option value="Unavailable">Unavailable</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-offset-4 col-sm-10">
                                        <button type="submit" class="btn btn-primary">Add Room Now</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <!----------------   Add Room Ends   --------------->
            </div>
            <!----------------   Panel Body Ends   --------------->
        </div>
        <!-----------  Content Menu Tab Ends   ------------>
    </div>
    <!-------   Content Area Ends  --------->
</div>

<script>
    function confirmDelete() {
        return confirm("Do You Really Want to Delete the Room?");
    }
</script>
<jsp:include page="./main_content_footer.jsp" />