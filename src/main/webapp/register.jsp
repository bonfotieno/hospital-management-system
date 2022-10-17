<jsp:include page="./header.jsp" />
<div class="row">
    <div class="col-md-12">
        <div class="panel panel-default login">
            <div class="panel-heading logintitle">Register As Patient</div>
            <div class="panel-body">
                <form class="form-horizontal center-block" role="form" action="./register" method="POST">
                    <input type="hidden" name="action" value="register">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Patient Id:</label>
                        <div class="col-sm-10">
                            <input type="number" class="form-control" name="patientid" placeholder="unique_id auto generated" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Name</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="patientname" placeholder="Name" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Email</label>
                        <div class="col-sm-10">
                            <input type="email" class="form-control" name="email" placeholder="Email" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Password</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" name="password" placeholder="Password" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Confirm Password</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" name="confirmPassword" placeholder="Password" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Address</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="add" placeholder="Address" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Phone</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="phone" placeholder="Phone No." required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Reason Of Visit</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="rov" placeholder="Reason Of Visit" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Room No</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" value="" name="roomNo" placeholder="Left for Admin" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Bed No</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="bedNo" placeholder="Left for Admin" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">To Be reffered To</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="doct" placeholder="Left for Admin" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Gender</label>
                        <div class="col-sm-2">
                            <select class="form-control" name="gender">
                                <option>Male</option>
                                <option>Female</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Admission Date</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="joindate" placeholder="Left For Admin" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Age</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="age" placeholder="Age" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Blood Group</label>
                        <div class="col-sm-2">
                            <select class="form-control" name="bgroup">
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
                    <div style="text-align:center;font-weight:bold;color:red">
                        <%
                            String loginError = (String) application.getAttribute("loginError");

                            if (loginError != null && !loginError.equals("")) {

                        %>
                            <span style="color:red"> <%= application.getAttribute("loginError") %> </span><br/>

                        <% } %>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-7 col-sm-offset-2" style="margin:0 0 0 40%">
                            <button type="submit" class="btn btn-primary">Register As Patient Now</button>
                        </div>
                    </div>
                    <br><Br><Br>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="./footer.jsp" />