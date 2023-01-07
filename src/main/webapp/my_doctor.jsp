<%@ page isELIgnored="false" %>
<%@ page import="com.hospital.common.CommonMethods" %>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix ="c" %>

<%
    if (CommonMethods.IsSessionExpired(request, response)) {
                return;
            }
%>

<%@include file="./patient_content_header.jsp" %>
<div class="row">
    <%@include file="./menu_patient.jsp" %>
    <!---- Content Ares Start  -------->
    <div class="col-md-10 maincontent">
        <!----------------   Menu Tab   --------------->
        <div class="panel panel-default contentinside">
            <div class="panel-heading">Manage Doctor</div>
            <!----------------   Panel body Start   --------------->
            <div class="panel-body">
                <ul class="nav nav-tabs panelBodyItem">
                    <li role="presentation"><a href="#panelBodyItemlist">Doctor List</a></li>                    
                </ul>
                <!----------------   Display Doctor Data List Start  --------------->
                <div id="panelBodyItemlist" class="switchgroup">
                    <table class="table table-bordered table-hover">
                        <tr class="active">
                            <td>Doctor ID</td>
                            <td>Doctor Name</td>
                            <td>Email</td>
                            <td>Address</td>
                            <td>Phone No.</td>
                            <td>Department</td>                            
                        </tr>
                        <c:forEach items="${doctorBean.list}" var="doctor">
                            <tr>
                                <td>
                                    ${doctor.id}
                                </td>
                                <td>
                                    Dr. ${doctor.name}
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
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <!----------------   Display Doctor Data List Ends  --------------->
            </div>
            <!----------------   Panel body Ends   --------------->
        </div>
        <!-----------  Content Menu Tab Ends   ------------>
    </div>
    <!-------   Content Area Ends  --------->
</div>
<%@ include file="./main_content_footer.jsp" %>