<%@ page isELIgnored="false" %>
<%@ page import="com.hospital.common.CommonMethods" %>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix ="c" %>

<% if (CommonMethods.IsSessionExpired(request, response)) { return; } %>

<jsp:include page="./main_content_header.jsp" />


<div class="row">

    <%@include file="./menu_admin.jsp"%>

    <!---- Content Ares Start  -------->
    <div class="col-md-10 maincontent" >
        <!----------------   Menu Tab   --------------->
        <div class="panel panel-default contentinside">
            <div class="panel-heading">Manage Billing Information</div>
            <!----------------   Panel body Start   --------------->
            <div class="panel-body">
                <ul class="nav nav-tabs panelBodyItem">
                    <li role="presentation"><a href="#panelBodyItemlist">Billing List</a></li>
                    <li role="presentation"><a href="#addpanelBodyItem">Add Billing Info</a></li>
                </ul>

                <!----------------   Display Billing Data List Start  --------------->

                <div id="panelBodyItemlist" class="switchgroup">
                    <table class="table table-bordered table-hover">
                        <tr class="active">
                            <td>Patient ID</td>
                            <td>Patient Name</td>
                            <td>Bill No</td>
                            <td>Pathology Charges(Ksh)</td>
                            <td>Other Charges(Ksh)</td>
                            <td>Misc Charges(Ksh)</td>
                            <td>Entry Date</td>
                            <td>Discharge Date</td>
                            <td>Options</td>
                        </tr>
                        <c:forEach items="${billingBean.list}" var="billing">
                            <tr>                            
                                <td>${billing.patient.id}</td>
                                <td>${billing.patient.name}</td>
                                <td>${billing.billNo}</td>
                                <td>${billing.pathologyCharge}</td>
                                <td>${billing.otherCharge}</td>
                                <td>${billing.miscCharge}</td>
                                <td>${billing.timeCreated}</td>
                                <td>${billing.dischargeDate}</td>
                                <td>
                                    <a href="#"><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal${billing.id}" ><span class="glyphicon glyphicon-wrench" aria-hidden="true"></span></button></a>
                                    <a  href="./billing-delete?id=${billing.id}" onclick="return confirmDelete()" class="btn btn-danger" ><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <!----------------   Display Billing Data List Ends  --------------->

                <!------ Billing Edit Info Modal Start Here ---------->
                <c:forEach items="${billingBean.list}" var="billing">
                    <div class="modal fade" id="myModal${billing.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">

                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="myModalLabel">Edit Billing Information</h4>
                                </div>

                                <div class="modal-body">
                                    <div class="panel panel-default">
                                        <div class="panel-body">
                                            <form class="form-horizontal" action="./billing-edit" method="post">

                                                <input type="hidden" name="id" value="${billing.id}">

                                                <div class="form-group">
                                                    <label  class="col-sm-2 control-label">Patient</label>
                                                    <div class="col-sm-10">
                                                        <select class="form-control" name="patientId">
                                                        <option value="${billing.patient.id}" selected="selected" hidden>
                                                            ${billing.patient.name}
                                                        </option>
                                                        <c:forEach items="${patientBean.list}" var="patient">
                                                            <option value="${patient.id}">
                                                                (${patient.id})${patient.name}
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label  class="col-sm-2 control-label">Bill Number:</label>
                                                    <div class="col-sm-10">
                                                        <input type="text" class="form-control" name="billNo" placeholder="Bill Number" value="${billing.billNo}">
                                                    </div>
                                                </div>                                                

                                                <div class="form-group">
                                                    <label  class="col-sm-2 control-label">Pathology Charges(Ksh)</label>
                                                    <div class="col-sm-10">
                                                        <input type="number" class="form-control" name="pathologyCharge" value="${billing.pathologyCharge}" placeholder="pathology" >
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label  class="col-sm-2 control-label"> Othe Charges(Ksh)</label>
                                                    <div class="col-sm-10">
                                                        <input type="number" class="form-control" name="otherCharge" value="${billing.otherCharge}" placeholder="Other Charge" >
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">Misc Charge(Ksh)</label>
                                                    <div class="col-sm-10">
                                                        <input type="number" class="form-control" name="miscCharge" value="${billing.miscCharge}" placeholder="Misc Charge" >
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label  class="col-sm-2 control-label">Discharge Date</label>
                                                    <div class="col-sm-10">
                                                        <input type="date" class="form-control" name="discharge_date" value="${billing.dischargeDate}" placeholder="Discharge Date" >
                                                    </div>
                                                </div>

                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
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


                <!----------------   Add Billing Info Start   --------------->
                <div id="addpanelBodyItem" class="switchgroup">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <form class="form-horizontal" action="./billing-add" method="post">                                

                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">Patient</label>
                                    <div class="col-sm-6">                                                                                
                                        <select class="form-control" name="patientId">
                                            <option value="none" selected disabled hidden>Select a Patient</option>
                                            <c:forEach items="${patientBean.list}" var="patient">
                                                <option value="${patient.id}">
                                                    (${patient.id})${patient.name}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">Billing No:</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="billNo" placeholder="Enter Bill Number">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">Pathology Charge(Ksh)</label>
                                    <div class="col-sm-6">
                                        <input type="number" class="form-control" name="pathologyCharge" placeholder="Pathology Charge" required="required">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">Other Charge(Ksh)</label>
                                    <div class="col-sm-6">
                                        <input type="number" class="form-control" name="otherCharge" placeholder="Charge">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Misc Charge(Ksh)</label>
                                    <div class="col-sm-6">
                                        <input type="number" class="form-control" name="miscCharge" placeholder="Misc Charge">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">Discharge Date</label>
                                    <div class="col-sm-6">
                                        <input type="date" class="form-control" name="discharge_date" placeholder="Discharge date">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                        <button type="submit" class="btn btn-primary">Add Billing Info</button>
                                    </div>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
                <!----------------   Add Billing Ends   --------------->
            </div>
            <!----------------   Panel body Ends   --------------->
        </div>
    </div>
</div>
<script>
    function confirmDelete() {
        return confirm("Do You Really Want to Delete the Billing Info?");
    }
</script>
<jsp:include page="./main_content_footer.jsp" />