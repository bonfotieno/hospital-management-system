<%@ page isELIgnored="false" %>
<%@ page import="com.hospital.model.Pathology" %>
<%@ page import="com.hospital.common.CommonMethods" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<% if (CommonMethods.IsSessionExpired(request, response)) { return; } %>

<jsp:include page="./patient_content_header.jsp" />

<div class="row">

<%@ include file="./menu_patient.jsp" %>

<!---- Content Ares Start  -------->
<div class="col-md-10 maincontent">

    <!----------------   Menu Tab   --------------->
    <div class="panel panel-default contentinside">
        <div class="panel-heading">Manage Patient's Pathological Information</div>
        <!----------------   Panel body Start   --------------->
        <div class="panel-body">
            <ul class="nav nav-tabs panelBodyItem">
                <li role="presentation"><a href="#panelBodyItemlist">Pathology List</a></li>                
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
                    </tr>
                    <c:set var = "email" value = "${sessionScope.username}" />
                    <c:forEach items="${pathologyBean.getPathologyListByEmail(email)}" var="pathology">
                        <tr>
                            <td>${pathology.patient.id}</td>
                            <td>${pathology.patient.name}</td>
                            <td>${pathology.generalSymptoms}</td>
                            <td>${pathology.xrayResults}</td>
                            <td>${pathology.ultraSound}</td>
                            <td>${pathology.bloodTest}</td>
                            <td>${pathology.ctScan}</td>
                            <td>${pathology.MRI}</td>                            
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <!----------------   Display Pathology Data List Ends  --------------->
        </div>
        <!----------------   Panel body Ends   --------------->
    </div>
</div>
</div>
<jsp:include page="./main_content_footer.jsp" />