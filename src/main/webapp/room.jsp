<%@ page isELIgnored="false" %>
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
                    <li role="presentation"><a href="#bedlist">Bed List</a></li>
                    <li role="presentation"><a href="#addBedItem">Add Bed</a></li>
                </ul>

                <!----------------   Display Room Data List start   --------------->

                <div id="panelBodyItemlist" class="switchgroup">
                    <table class="table table-bordered table-hover">
                        <tr class="active">
                            <td>Room Number</td>
                            <td>Availability Status</td>
                            <td>Room Description</td>
                            <td>Options</td>
                        </tr>
                        <c:forEach items="${roomBean.list}" var="room">
                            <tr>
                                <td>${room.roomNo}</td>
                                <td>${room.roomStatus}</td>
                                <td>${room.roomDescription}</td>
                                <td>
                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal${room.id}">
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
                    <div class="modal fade" id="myModal${room.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
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
                                                    <div class="col-sm-6">
                                                        <input type="text" class="form-control"
                                                            name="roomNo"
                                                            value="${room.roomNo}">
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label
                                                        class="col-sm-4 control-label">Status</label>
                                                    <div class="col-sm-6">
                                                        <select class="form-control" name="roomStatus">
                                                            <option value="${room.roomStatus}" selected hidden>${room.roomStatus}</option>
                                                            <option value="Available">Available</option>
                                                            <option value="Unavailable">Unavailable</option>
                                                        </select>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label
                                                        class="col-sm-4 control-label">Room Description</label>
                                                    <div class="col-sm-6">
                                                        <input type="text"
                                                            class="form-control"
                                                            name="roomDescription" value="${room.roomDescription}">
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
                                    <label class="col-sm-4 control-label">Room Description</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" name="roomDescription" placeholder="Room Description" required>
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

                <!----------------   Display Bed Data List start  --------------->

                <div id="bedlist" class="switchgroup">
                    <table class="table table-bordered table-hover">
                        <tr class="active">
                            <td>Bed Number</td>
                            <td>Availability Status</td>
                            <td>Bed Description</td>
                            <td>Room</td>
                            <td>Options</td>
                        </tr>
                        <c:forEach items="${bedBean.list}" var="bed">
                            <tr>
                                <td>${bed.bedNo}</td>
                                <td>${bed.bedStatus}</td>
                                <td>${bed.bedDescription}</td>
                                <td>${bed.room.roomNo}</td>
                                <td>
                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#bedModal${bed.id}">
                                        <span class="glyphicon glyphicon-wrench" aria-hidden="true"></span>
                                    </button>
                                    <a href="./bed-delete?id=${bed.id}" class="btn btn-danger" onclick="return confirmDelete()">
                                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <!----------------   Display Bed Data List ends   --------------->

                <!------ Edit Bed Modal Start ---------->
                <c:forEach items="${bedBean.list}" var="bed">
                    <div class="modal fade" id="bedModal${bed.id}" tabindex="-1" role="dialog" aria-labelledby="bedModalLabel">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">

                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                       <span aria-hidden="true">&times;</span>
                                    </button>
                                    <h4 class="modal-title" id="bedModalLabel">Edit Room Information</h4>
                                </div>

                                <div class="modal-body">
                                    <div class="panel panel-default">
                                        <div class="panel-body">
                                            <form class="form-horizontal" action="./bed-edit" method="post">
                                                <input type="hidden" name="id" value="${bed.id}">

                                                <div class="form-group">
                                                    <label class="col-sm-4 control-label">Room No</label>
                                                    <div class="col-sm-6">
                                                        <input type="text" class="form-control"
                                                            name="bedNo"
                                                            value="${bed.bedNo}"
                                                        >
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label
                                                        class="col-sm-4 control-label">Status</label>
                                                    <div class="col-sm-6">
                                                        <select class="form-control" name="bedStatus">
                                                            <option value="${bed.bedStatus}" selected hidden>${bed.bedStatus}</option>
                                                            <option value="Available">Available</option>
                                                            <option value="Unavailable">Unavailable</option>
                                                        </select>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label
                                                        class="col-sm-4 control-label">Bed Description</label>
                                                    <div class="col-sm-6">
                                                        <input type="text"
                                                            class="form-control"
                                                            name="bedDescription" value="${bed.bedDescription}"
                                                        >
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="col-sm-4 control-label">Room</label>
                                                    <div class="col-sm-6">
                                                        <select class="form-control" name="roomId">
                                                            <option value="${bed.room.id}" selected="selected" disabled hidden>
                                                                ${bed.room.roomNo}
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

                <!----------------   Add Bed Start   --------------->
                <div id="addBedItem" class="switchgroup">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <form class="form-horizontal" action="./bed-add" method="post">

                                <div class="form-group">
                                    <label class="col-sm-4 control-label">Bed No</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" name="bedNo" placeholder="Bed Number" required>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-4 control-label">Availability Status</label>
                                    <div class="col-sm-4">
                                        <select class="form-control" name="bedStatus">
                                            <option value="none" selected disabled hidden>Select a Status</option>
                                            <option value="Available">Available</option>
                                            <option value="Unavailable">Unavailable</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-4 control-label">Bed Description</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" name="bedDescription" placeholder="Bed Description" required>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-4 control-label">Room</label>
                                    <div class="col-sm-4">
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
                                    <div class="col-sm-offset-4 col-sm-10">
                                        <button type="submit" class="btn btn-primary">Add Bed Now</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <!----------------   Add Bed Ends   --------------->

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