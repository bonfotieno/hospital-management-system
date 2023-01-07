<%@ page isELIgnored="false" %>
<%@ page import="com.hospital.common.CommonMethods" %>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix ="c" %>

<% if (CommonMethods.IsSessionExpired(request, response)) { return; } %>

<jsp:include page="./patient_content_header.jsp" />


<div class="row">

    <%@ include file="./menu_patient.jsp" %>

    <!---- Content Ares Start  -------->
    <div class="col-md-10 maincontent" >
        <!----------------   Menu Tab   --------------->
        <div class="panel panel-default contentinside">
            <div class="panel-heading">Manage Billing Information</div>
            <!----------------   Panel body Start   --------------->
            <div class="panel-body">
                <ul class="nav nav-tabs panelBodyItem">
                    <li role="presentation"><a href="#panelBodyItemlist">Billing List</a></li>                    
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
                        </tr>
                        <c:set var = "email" value = "${sessionScope.username}" />
                        <c:forEach items="${billingBean.getBillingListByEmail(email)}" var="billing">
                            <tr>                            
                                <td>${billing.patient.id}</td>
                                <td>${billing.patient.name}</td>
                                <td>${billing.billNo}</td>
                                <td>${billing.pathologyCharge}</td>
                                <td>${billing.otherCharge}</td>
                                <td>${billing.miscCharge}</td>
                                <td>${billing.timeCreated}</td>
                                <td>${billing.dischargeDate}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <!----------------   Display Billing Data List Ends  --------------->

            </div>
            <!----------------   Panel body Ends   --------------->
        </div>
    </div>
</div>
<jsp:include page="./main_content_footer.jsp" />