<%@ page isELIgnored="false" %>
<%@ page import="com.hospital.common.CommonMethods" %>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix ="c" %>

<% if (CommonMethods.IsSessionExpired(request, response)) { return; } %>

<%@include file="./patient_content_header.jsp" %>

<div class="row">

    <%@include file="./menu_patient.jsp" %>
        <!---- Content Ares Start  -------->
        <div class="col-md-10 maincontent">
            <!----------------   Menu Tab   --------------->

            <div class="panel panel-default contentinside">
                <div class="panel-heading">Manage Patient</div>
                <!----------------   Panel body Start   --------------->
                <div class="panel-body">
                    <ul class="nav nav-tabs panelBodyItem">
                        <li role="presentation"><a href="#panelBodyItemlist">Patient List</a></li>
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
                            <c:set var = "email" value = "${sessionScope.username}" />
                            <c:forEach items="${patientBean.getPatientListByEmail(email)}" var="patient">
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
                                        ${patient.referredTo}
                                    </td>
                                    <td>
                                        <a href="#"><button type="button" class="btn btn-primary" data-toggle="modal"
                                                data-target="#myModal${patient.id}"><span class="glyphicon glyphicon-wrench"
                                                    aria-hidden="true"></span></button></a>
                                    </td>                            
                                </tr>
                            </c:forEach>

                        </table>
                    </div>
                    <!----------------   Display Patient Data List Ends  --------------->

                    <!------ Patient Edit Info Modal Start Here ---------->

                    <c:forEach items="${patientBean.getPatientListByEmail(email)}" var="patient">
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
                                            <form class="form-horizontal" action="./patient-edit-as-patient"
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
                    
                </div>
                <!----------------   Panel body Ends   --------------->
            </div>
        </div>
</div>

<jsp:include page="./main_content_footer.jsp" />