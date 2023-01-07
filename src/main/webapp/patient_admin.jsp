<%@ page isELIgnored="false" %>
<%@ page import="com.hospital.model.Patient" %>
<%@ page import="com.hospital.common.CommonMethods" %>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix ="c" %>

<% if (CommonMethods.IsSessionExpired(request, response)) { return; } %>

<jsp:include page="./main_content_header.jsp" />


<div class="row">
    <%@ include file="./menu_admin.jsp" %>
    <!---- Content Ares Start  -------->
    <div class="col-md-10 maincontent">
        <!----------------   Menu Tab   --------------->

        <div class="panel panel-default contentinside">
            <div class="panel-heading">Manage Patient</div>
            <!----------------   Panel body Start   --------------->
            <div class="panel-body">
                <ul class="nav nav-tabs panelBodyItem">
                    <li role="presentation"><a href="#panelBodyItemlist">Patient List</a></li>
                    <li role="presentation"><a href="#addpanelBodyItem">Add Patient</a></li>
                </ul>

                <!----------------   Display Patients Data List Start  --------------->
                <div id="panelBodyItemlist" class="switchgroup">
                    <table class="table table-bordered table-hover">
                        <tr class="active">
                            <td>#</td>
                            <td>Patient Name</td>
                            <td>Age</td>
                            <td>Gender</td>
                            <td>Phone</td>
                            <td>Address</td>
                            <td>Reason Of Visit</td>
                            <td>Blood Grp</td>
                            <td>Admission Date</td>
                            <td>Room No</td>
                            <td>Bed No</td>                            
                            <td>Referred To</td>
                            <td>Options</td>
                        </tr>

                        <c:forEach items="${patientBean.list}" var="patient">
                            <tr>                            
                                <td>
                                    ${patient.id}
                                </td>
                                <td>
                                    ${patient.name}
                                </td>                                
                                <td>
                                    ${patient.age}
                                </td>
                                <td>
                                    ${patient.gender}
                                </td>
                                <td>
                                    ${patient.phone}
                                </td>
                                <td>
                                    ${patient.address}
                                </td>
                                <td>
                                    ${patient.reasonOfVisit}
                                </td>
                                <td>
                                    ${patient.bloodGroup}
                                </td>
                                <td>
                                    ${patient.admissionDate}
                                </td>
                                <td>
                                    ${patient.roomAdmitted.roomNo}
                                </td>
                                <td>
                                    ${patient.bedAdmitted.bedNo}
                                </td>
                                <td>
                                    Dr. ${patient.referredTo}
                                </td>
                                <td>
                                    <a href="#"><button type="button" class="btn btn-primary" data-toggle="modal"
                                            data-target="#myModal${patient.id}"><span class="glyphicon glyphicon-wrench"
                                                aria-hidden="true"></span></button></a>
                                    <a href="./patient-delete?id=${patient.id}"
                                        onclick="return confirmDelete()" class="btn btn-danger"><span
                                            class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
                                </td>                            
                            </tr>
                        </c:forEach>

                    </table>
                </div>
                <!----------------   Display Patient Data List Ends  --------------->

                <!------ Patient Edit Patient Info Modal Start Here ---------->
                <c:forEach items="${patientBean.list}" var="patient">
                    <div class="modal fade" id="myModal${patient.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">


                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                            aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="myModalLabel">Edit Patient Information</h4>
                                </div>

                                <div class="modal-body">
                                    <div class="panel panel-default">
                                        <div class="panel-body">
                                            <form class="form-horizontal" action="./patient-edit"
                                                method="post">
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">Patient Id</label>
                                                    <div class="col-sm-10">
                                                        <input type="number" class="form-control" name="id"
                                                            placeholder="Patient ID" value="${patient.id}" readonly="readonly">
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">Name</label>
                                                    <div class="col-sm-10">
                                                        <input type="text" class="form-control" name="name"
                                                            value="${patient.name}" placeholder="Name">
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">Email</label>
                                                    <div class="col-sm-10">
                                                        <input type="email" class="form-control" name="email"
                                                            value="${patient.email}" placeholder="Email">
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">Gender</label>
                                                    <div class="col-sm-10">                                                        
                                                        <select class="form-control" name="gender">
                                                            <option value="${patient.gender}" selected="selected" hidden>
                                                                ${patient.gender}
                                                            </option>
                                                            <option>Male</option>
                                                            <option>Female</option>
                                                            <option>Non-binary</option>
                                                        </select>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">Age</label>
                                                    <div class="col-sm-10">
                                                        <input type="text" class="form-control" name="age" value="${patient.age}"
                                                            placeholder="Age">
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">Address</label>
                                                    <div class="col-sm-10">
                                                        <input type="text" class="form-control" name="address" value="${patient.address}"
                                                            placeholder="Address">
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">Phone</label>
                                                    <div class="col-sm-10">
                                                        <input type="text" class="form-control" name="phone"
                                                            value="${patient.phone}" placeholder="Phone">
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">Reason Of Visit</label>
                                                    <div class="col-sm-10">
                                                        <input type="text" class="form-control" name="reasonOfVisit" value="${patient.reasonOfVisit}"
                                                            placeholder="Reason Of Visit">
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">Room Number</label>
                                                    <div class="col-sm-10">
                                                        <select class="form-control" name="roomId">
                                                            <option value="${patient.roomAdmitted.id}"  selected="selected" hidden>
                                                                ${patient.roomAdmitted.roomNo}
                                                            </option>
                                                            <c:forEach items="${roomBean.list}" var="room">
                                                                <option onclick="loadBeds(${room.id})" value="${room.id}">
                                                                    ${room.roomNo}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">Bed No.</label>
                                                    <div class="col-sm-10" >                                                                                      
                                                        <select id="beddropdownlist" class="form-control" name="bedId">
                                                            <option value="${patient.bedAdmitted.id}" selected="selected" hidden>
                                                                ${patient.bedAdmitted.bedNo}
                                                            </option>
                                                        </select>                                                        
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">Reffered To</label>
                                                    <div class="col-sm-10">
                                                        <select class="form-control" name="referredTo">
                                                            <option value="${patient.referredTo}" selected="selected" hidden>
                                                                ${patient.referredTo}
                                                            </option>
                                                            <c:forEach items="${doctorBean.list}" var="doctor">
                                                                <option value="${doctor.name}">
                                                                    Dr. ${doctor.name}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">Admission Date</label>
                                                    <div class="col-sm-10">
                                                        <input type="date" class="form-control" name="admission_date"
                                                            value="${patient.admissionDate}" placeholder="Admission Date ">
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">Blood Group</label>                                                    
                                                    <div class="col-sm-10">
                                                        <select class="form-control" name="bloodGroup">
                                                            <option value="${patient.bloodGroup}" selected="selected" hidden>
                                                                ${patient.bloodGroup}
                                                            </option>
                                                            <option>A<sup>+</sup></option>
                                                            <option>A<sup>-</sup></option>
                                                            <option>B<sup>+</sup></option>
                                                            <option>B<sup>-</sup></option>
                                                            <option>AB<sup>+</sup></option>
                                                            <option>AB<sup>-</sup></option>
                                                            <option>O<sup>+</sup></option>
                                                            <option>O<sup>-</sup></option>
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
                <!----------------   Add Patient Start   --------------->
                <div id="addpanelBodyItem" class="switchgroup">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <form class="form-horizontal" action="./patient-add" method="post">
                                
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Name</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="name" placeholder="Name" required="required">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Email</label>
                                    <div class="col-sm-6">
                                        <input type="email" class="form-control" name="email" placeholder="Email">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Address</label>
                                    <div class="col-sm-6">
                                        <input type="text" maxlength="255" class="form-control" name="address" placeholder="Address" required="required">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Phone</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="phone" placeholder="Phone No." required="required">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Gender</label>
                                    <div class="col-sm-6">
                                        <select class="form-control" name="gender">
                                            <option>Male</option>
                                            <option>Female</option>
                                            <option>Non-binary</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Age</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="age" placeholder="Age" required="required">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Reason Of Visit</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="reasonOfVisit" placeholder="Reason Of Visit" required="required">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Room Admitted To</label>
                                    <div class="col-sm-6">
                                        <select class="form-control" name="roomId">
                                            <option value="none" selected disabled hidden>Select a Room Number</option>
                                            <c:forEach items="${roomBean.list}" var="room">
                                                <option onclick="loadBeds(${room.id})" value="${room.id}">
                                                    ${room.roomNo}
                                                </option>
                                            </c:forEach>
                                            <option value="">Not Admitted</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Bed No.</label>
                                    <div class="col-sm-6" id="beds">
                                        <select id="beddropdownlist" class="form-control" name="bedId">
                                            <option selected="selected" disabled hidden>Select Bed</option>
                                            <option selected="selected" disabled hidden>Select Bed</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Referred To</label>
                                    <div class="col-sm-6">
                                        <select class="form-control" name="referredTo">
                                            <option value="none" selected disabled hidden>Select a Doctor</option>
                                            <c:forEach items="${doctorBean.list}" var="doctor">
                                                <option value="${doctor.name}">
                                                    Dr. ${doctor.name}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Admission Date</label>
                                    <div class="col-sm-6">
                                        <input type="date" class="form-control" name="admission_date"
                                            placeholder="Admission date">
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Blood Group</label>
                                    <div class="col-sm-2">
                                        <select class="form-control" name="bloodGroup">
                                            <option>A<sup>+</sup></option>
                                            <option>A<sup>-</sup></option>
                                            <option>B<sup>+</sup></option>
                                            <option>B<sup>-</sup></option>
                                            <option>AB<sup>+</sup></option>
                                            <option>AB<sup>-</sup></option>
                                            <option>O<sup>+</sup></option>
                                            <option>O<sup>-</sup></option>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                        <button type="submit" class="btn btn-primary">Add Patient</button>
                                    </div>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
                <!----------------   Add Patients Ends   --------------->
            </div>
            <!----------------   Panel body Ends   --------------->
        </div>
    </div>
</div>

<script>
    function confirmDelete() {
        return confirm("Do You Really Want to Delete the Patient?");
    }

    

    function loadBeds(id)
    { 
        var params = {id : id};
        $.get("./bed-list-by-room", $.param(params), function(responseJson) {
            let selected = $("#beddropdownlist");
            selected.find("option").remove();
            $.each(responseJson, function(index, bed) {
                $("<option>").val(bed.id).text(bed.bedNo).appendTo(selected);
            });    
            $("<option>").val("").text("Not Admitted").appendTo(selected);               
    });
    }

</script>
<jsp:include page="./main_content_footer.jsp" />
