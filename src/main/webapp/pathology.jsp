<%@ page isELIgnored="false" %>
<%@ page import="com.hospital.model.Pathology" %>
<%@ page import="com.hospital.common.CommonMethods" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<% if (CommonMethods.IsSessionExpired(request, response)) { return; } %>

<jsp:include page="./main_content_header.jsp" />

<div class="row">

<%@ include file="./menu_admin.jsp" %>

<!---- Content Ares Start  -------->
<div class="col-md-10 maincontent">

    <!----------------   Menu Tab   --------------->
    <div class="panel panel-default contentinside">
        <div class="panel-heading">Manage Patient's Pathological Information</div>
        <!----------------   Panel body Start   --------------->
        <div class="panel-body">
            <ul class="nav nav-tabs panelBodyItem">
                <li role="presentation"><a href="#panelBodyItemlist">Pathology List</a></li>
                <li role="presentation"><a href="#addpanelBodyItem">Add Pathology Info</a>
                </li>
            </ul>

            <!----------------   Display Pathology Data List Start  --------------->
            <div id="panelBodyItemlist" class="switchgroup">
                <table class="table table-bordered table-hover">
                    <tr class="active">
                        <td>Patient Id</td>
                        <td>Patient Name</td>
                        <td>General Symptoms</td>
                        <td>X-Ray Results</td>
                        <td>UltraSound</td>
                        <td>Blood Test</td>
                        <td>CT Scan</td>
                        <td>MRI Scan</td>
                        <td>Options</td>
                    </tr>
                    <c:forEach items="${pathologyBean.list}" var="pathology">
                        <tr>
                            <td>${pathology.patient.id}</td>
                            <td>${pathology.patient.name}</td>
                            <td>${pathology.generalSymptoms}</td>
                            <td>${pathology.xrayResults}</td>
                            <td>${pathology.ultraSound}</td>
                            <td>${pathology.bloodTest}</td>
                            <td>${pathology.ctScan}</td>
                            <td>${pathology.MRI}</td>
                            <td>
                                <a href="#">
                                    <button type="button" class="btn btn-primary"
                                        data-toggle="modal"
                                        data-target="#myModal${pathology.id}">
                                        <span class="glyphicon glyphicon-wrench"
                                            aria-hidden="true"></span>
                                    </button>
                                </a>
                                <a href="./pathology-delete?id=${pathology.id}"
                                    onclick="return confirmDelete()" class="btn btn-danger">
                                    <span class="glyphicon glyphicon-trash"
                                        aria-hidden="true"></span>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <!----------------   Display Pathology Data List Ends  --------------->

            <!------ Pathology Edit Info Modal Start Here ---------->
            <c:forEach items="${pathologyBean.list}" var="pathology">
                <div class="modal fade" id="myModal${pathology.id}" tabindex="-1"
                    role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">

                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"
                                    aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="myModalLabel">Edit Pathology
                                    Information</h4>
                            </div>

                            <div class="modal-body">
                                <div class="panel panel-default">
                                    <div class="panel-body">
                                        <form class="form-horizontal"
                                            action="./pathology-edit" method="post">

                                            <input type="hidden" name="id" value="${pathology.id}">
                                            
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Patient Name</label>
                                                <div class="col-sm-8">
                                                    <select class="form-control" name="patientId">
                                                        <option value="${pathology.patient.id}" selected="selected" disabled hidden>
                                                            ${pathology.patient.name}
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
                                                <label class="col-sm-2 control-label">General Symptoms</label>
                                                <div class="col-sm-8">
                                                    <textarea class="form-control" name="generalSymptoms" rows="4">
                                                        ${pathology.generalSymptoms}
                                                    </textarea>                                        
                                                </div>
                                            </div>
                                    
                                            <div class="form-group">
                                                <label
                                                    class="col-sm-2 control-label">X-Ray</label>
                                                <div class="col-sm-8">
                                                    <textarea class="form-control" name="xrayResults" rows="4">
                                                        ${pathology.xrayResults}
                                                    </textarea>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label
                                                    class="col-sm-2 control-label">Ultrasound</label>
                                                <div class="col-sm-8">
                                                    <textarea class="form-control" name="ultraSound" rows="4">
                                                        ${pathology.ultraSound}
                                                    </textarea>                                                    
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Blood
                                                    Test</label>
                                                <div class="col-sm-8">
                                                    <textarea class="form-control" name="bloodTest" rows="4">
                                                        ${pathology.bloodTest}
                                                    </textarea>                                                    
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label
                                                    class="col-sm-2 control-label">CT-Scan</label>
                                                <div class="col-sm-8">
                                                    <textarea class="form-control" name="ctScan" rows="4">
                                                        ${pathology.ctScan}
                                                    </textarea>                                                    
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label
                                                    class="col-sm-2 control-label">MRI-Scan</label>
                                                <div class="col-sm-8">
                                                    <textarea class="form-control" name="MRI" rows="4">
                                                        ${pathology.MRI}
                                                    </textarea>                                                    
                                                </div>
                                            </div>

                                            <div class="modal-footer">
                                                <button type="button"
                                                    class="btn btn-default"
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

            <!----------------   Add Pathology Info Start   --------------->
            <div id="addpanelBodyItem" class="switchgroup">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <form class="form-horizontal" action="./pathology-add"
                            method="post">
                                
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Patient</label>
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
                                    <label class="col-sm-2 control-label">General Symptoms</label>
                                    <div class="col-sm-6">
                                        <textarea class="form-control" name="generalSymptoms" placeholder="Enter general Symptoms here..." rows="4"></textarea>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">X-Ray</label>
                                    <div class="col-sm-6">
                                        <textarea class="form-control" name="xrayResults" placeholder="Enter X-Ray Results here..." rows="4"></textarea>                                        
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Ultrasound</label>
                                    <div class="col-sm-6">
                                        <textarea class="form-control" name="ultraSound" placeholder="Enter UltraSound Results here..." rows="4"></textarea>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Blood Test</label>
                                    <div class="col-sm-6">
                                        <textarea class="form-control" name="bloodTest" placeholder="Enter Blood Test Results here..." rows="4"></textarea>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">CT-Scan</label>
                                    <div class="col-sm-6">
                                        <textarea class="form-control" name="ctScan" placeholder="Enter CT-Scan Results here..." rows="4"></textarea>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">MRI-Scan</label>
                                    <div class="col-sm-6">
                                        <textarea class="form-control" name="MRI" placeholder="Enter MRI-Scan Results here..." rows="4"></textarea>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                        <button type="submit" class="btn btn-primary">Add Pathology Info</button>
                                    </div>
                                </div>
                        </form>

                    </div>
                </div>
            </div>
            <!----------------   Add Pathology Ends   --------------->
        </div>
        <!----------------   Panel body Ends   --------------->
    </div>
</div>
</div>

<script>
    function confirmDelete() {
        return confirm("Do You Really Want to Delete the Pathology Entry?");
    }
</script>
<jsp:include page="./main_content_footer.jsp" />