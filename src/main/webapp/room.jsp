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

<%@ include file="./main_content_header.jsp" %>

<%@ include file="./main_content_footer.jsp" %>