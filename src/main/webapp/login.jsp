<jsp:include page="./header.jsp" />
<div class="row">
    <div class="col-md-12">
        <br /><br /><br /><br /><br /><br /><br /><br />
        <div class="panel panel-default login">
            <div class="panel-heading logintitle">Login</div>
            <div class="panel-body">
                <form class="form-horizontal center-block" role="form" action="./login" method="post">
                    <input type="hidden" name="action" value="login">
                    <div class="input-group input-group-lg">
                        <span class="input-group-addon" id="sizing-addon1"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></span>
                        <select class="form-control" name="userrole">
                            <option selected="selected">Select User</option>
                            <option value="admin">Admin</option>
                            <option value="patient">Patient</option>
                        </select>
                    </div><br/>
                    <div>
                    </div>
                    <div class="input-group input-group-lg">
                        <span class="input-group-addon" id="sizing-addon1"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span></span>
                        <input type="email" class="form-control" name="email" placeholder="example@gmail.com" required aria-describedby="sizing-addon1">
                    </div>
                    <br/>
                    <div class="input-group input-group-lg">
                        <span class="input-group-addon" id="sizing-addon1"><span class="glyphicon glyphicon-lock" aria-hidden="true"></span></span>
                        <input type="password" name="password" class="form-control" placeholder="Password" required aria-describedby="sizing-addon1">
                    </div>
                    <br/>
                    <div class="col-sm-7 col-sm-offset-2">
                        <button type="submit" class="btn btn-primary btn-block btn-lg">Login</button>
                    </div>
                </form>
            </div>
            <div style="text-align:center;font-weight:bold;color:red">
                <%
                    String loginError = (String) application.getAttribute("loginError");

                    if (loginError != null && !loginError.equals("")) {

                %>
                    <span style="color:red"> <%= application.getAttribute("loginError") %> </span><br/>

                <% } %>
            </div>
            <a href="./register.jsp" style="text-align:Center;font-weight:bold;font-size:120%;padding: 0 2%">Register As Patient</a>
        </div>
    </div>
</div>
<jsp:include page="./footer.jsp" />