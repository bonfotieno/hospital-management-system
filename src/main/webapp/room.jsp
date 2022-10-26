<%@ page isELIgnored="false" %>
<%@ page import="com.hospital.model.Room" %>
<%@ page import="com.hospital.common.CommonMethods" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<jsp:useBean id="roomController" class="com.hospital.controllers.RoomController" />

<%
    if (CommonMethods.IsSessionExpired(request, response)) {
                return;
            }
%>

<jsp:include page="./main_content_header.jsp" />

<div class="row">
    <%@ include file="./menu_admin.jsp" %>
    <!-------   Content Area start  --------->
    
    <!-------   Content Area Ends  --------->
</div>

<script>
    function confirmDelete()
    {
        return confirm("Do You Really Want to Delete the Room?");
    }
</script>
<jsp:include page="./main_content_footer.jsp" />