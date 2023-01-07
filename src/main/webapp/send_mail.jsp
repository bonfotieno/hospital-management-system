<%@ page isELIgnored="false" %>
<%@ page import="com.hospital.common.CommonMethods" %>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix ="c" %>

<%
    if (CommonMethods.IsSessionExpired(request, response)) {
                return;
            }
%>

<%@ include file="./main_content_header.jsp" %>
<div class="row">
    <%@ include file="./menu_admin.jsp" %>
    <!---- Content Ares Start  -------->
    <div class="col-md-10 maincontent">
        <!----------------   Menu Tab   --------------->
        <div class="panel panel-default contentinside">
            <div class="panel-heading">Send Emails</div>
            <!----------------   Panel body Start   --------------->
            <div class="panel-body">
                <ul class="nav nav-tabs panelBodyItem">
                    <li role="presentation"><a href="#panelBodyItemlist">Patient Email</a></li>
                    <li role="presentation"><a href="#addpanelBodyItem"> General Email</a></li>
                </ul>

                <!----------------   Send Email Form Patient Start   --------------->
                <div id="panelBodyItemlist" class="switchgroup">
                    <div class="panel panel-default">
                        <div class="panel-body">                            
                            <form class="form-horizontal" action="./send-mail" method="post" enctype="multipart/form-data">
                                
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Patient</label>
                                    <div class="col-sm-6">
                                        <select class="form-control" name="recipient">
                                            <option value="none" selected disabled hidden>Select a Patient</option>
                                            <c:forEach items="${patientBean.list}" var="patient">
                                                <option value="${patient.email}">
                                                    (${patient.id})${patient.name}
                                                </option>
                                            </c:forEach>
                                        </select>                                        
                                    </div>

                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Subject</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="subject"
                                            placeholder="Subject" required="required"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Content</label>
                                    <div class="col-sm-6">                                      
                                        <textarea class="form-control" name="content" placeholder="Enter Content here..." rows="7" required="required"></textarea>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Attach file</label>
                                    <div class="col-sm-6">
                                        <input type="file" class="form-control" name="file" size="50" />                                        
                                    </div>
                                </div>


                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                        <button type="submit" class="btn btn-primary">Send Email Now</button>
                                    </div>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
                <!----------------   Send Email Patient Ends   --------------->

                <!----------------   Send Email Form General Start   --------------->
                <div id="addpanelBodyItem" class="switchgroup">
                    <div class="panel panel-default">
                        <div class="panel-body">                            
                            <form class="form-horizontal" action="./send-mail" method="post" enctype="multipart/form-data">
                                
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Recipient address</label>
                                    <div class="col-sm-6">
                                        <input type="email" class="form-control" name="recipient"
                                            placeholder="recipient email address" required="required"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Subject</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="subject"
                                            placeholder="Subject" required="required"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Content</label>
                                    <div class="col-sm-6">                                      
                                        <textarea class="form-control" name="content" placeholder="Enter Content here..." rows="7" required="required"></textarea>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Attach file</label>
                                    <div class="col-sm-6">
                                        <input type="file" class="form-control" name="file" size="50" />                                        
                                    </div>
                                </div>


                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                        <button type="submit" class="btn btn-primary">Send Email Now</button>
                                    </div>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
                <!----------------   Send Email General Ends   --------------->

            </div>
            <!----------------   Panel body Ends   --------------->
        </div>
        <!-----------  Content Menu Tab Ends   ------------>
    </div>
    <!-------   Content Area Ends  --------->
</div>
<%@ include file="./main_content_footer.jsp" %>