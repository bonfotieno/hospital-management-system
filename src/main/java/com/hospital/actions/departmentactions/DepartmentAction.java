package com.hospital.actions.departmentactions;

import com.hospital.actions.HomeAction;
import com.hospital.model.Department;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;


import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/department")
public class DepartmentAction extends HttpServlet {
    private HttpSession session;
    private List<Department> departments = new ArrayList<>();
    ServletContext servletCtx = null;
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        servletCtx = config.getServletContext();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        session = req.getSession();
        if (session.getAttribute("username") == null) { //checks if the previous session expired
            session.invalidate();
            resp.sendRedirect("");
            return;
        }
        resp.getWriter().print(departmentPage());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        session = req.getSession();
        if (session.getAttribute("username") == null) { //checks if the previous session expired
            session.invalidate();
            resp.sendRedirect("");
            return;
        }
        PrintWriter wr = resp.getWriter();
        Department department = new Department();
        try {
            BeanUtils.populate(department, req.getParameterMap());
            department.setId(this.generateID(departments));
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        if (StringUtils.isBlank(department.getDeptName())) {
            //wr.print(this.addStudent("Name is required<br/>"));
            return;
        }

        if (StringUtils.isBlank(department.getDeptDesc())) {
            //wr.print(this.addStudent("Reg No is required<br/>"));
            return;
        }
        this.addNewDepartment(department);
        resp.sendRedirect("./department");
    }

    public void addNewDepartment(Department department) {
        if (department == null || StringUtils.isBlank(department.getDeptName()) || StringUtils.isBlank(department.getDeptDesc()))
            return;

        try {
            Connection connection = (Connection) servletCtx.getAttribute("dbConnection");
            Statement sqlStmt = connection.createStatement();
            sqlStmt.executeUpdate("insert into departments(name,description) " +
                    "values('" + department.getDeptName() + "','" + department.getDeptDesc() + "')");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

    }
    private String departmentPage(){
        return HomeAction.adminDashboardHeader(HomeAction.user_email)+departmentContent()+HomeAction.dashboardFooter();
    }

    private String createDepartmentTable(){
        departments.clear();
        try {
            Connection connection = (Connection) servletCtx.getAttribute("dbConnection");
            Statement sqlStmt = connection.createStatement();
            ResultSet result = sqlStmt.executeQuery("select * from departments");
            while (result.next()) {
                Department department = new Department();
                department.setId((long) result.getInt("id"));
                department.setDeptName(result.getString("name"));
                department.setDeptDesc(result.getString("description"));

                departments.add(department);
            }

        }catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        String departmentTable = "<table class=\"table table-bordered table-hover\">\n" +
                "                   <tr class=\"active\">\n" +
                "                       <td>Department ID</td>\n" +
                "                       <td>Department Name</td>\n" +
                "                       <td>Department Description</td>\n" +
                "                       <td>Options</td>\n" +
                "                   </tr>\n";
        for (Department department : departments)
            departmentTable +=
                "<tr>" +
                "    <td>" + department.getId() + "</td>" +
                "    <td>" + department.getDeptName()+"</td>" +
                "    <td>" + department.getDeptDesc() + "</td>" +
                "    <td>" +
                "        <button type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\"" +
                "        data-target=\"#myModal"+department.getId()+"\"><span class=\"glyphicon glyphicon-wrench\"" +
                "        aria-hidden=\"true\"></span></button>" +
                "        <a href=\"./department/delete?deptId=" + department.getId()+"\" class=\"btn btn-danger\"" +
                "        onclick=\"return confirmDelete()\"><span class=\"glyphicon glyphicon-trash\"" +
                "        aria-hidden=\"true\"></span></a>" +
                "    </td>" +
                "</tr>";
        departmentTable += "</table>";
        return departmentTable;
    }
    private String createEditModal(List<Department> departments){
        String EditModals = "";
        for (Department department : departments) {
            EditModals+=
                            "                   <div class=\"modal fade\" id=\"myModal"+department.getId()+"\" tabindex=\"-1\" role=\"dialog\"\n" +
                            "                            aria-labelledby=\"myModalLabel\">\n" +
                            "                            <div class=\"modal-dialog\" role=\"document\">\n" +
                            "                                <div class=\"modal-content\">\n" +
                            "                                    <div class=\"modal-header\">\n" +
                            "                                        <button type=\"button\" class=\"close\" data-dismiss=\"modal\"\n" +
                            "                                            aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>\n" +
                            "                                        <h4 class=\"modal-title\" id=\"myModalLabel\">Edit Department Information\n" +
                            "                                        </h4>\n" +
                            "                                    </div>\n" +
                            "\n" +
                            "                                    <div class=\"modal-body\">\n" +
                            "                                        <div class=\"panel panel-default\">\n" +
                            "                                            <div class=\"panel-body\">\n" +
                            "                                                <form class=\"form-horizontal\" action=\"./department/edit\" method=\"post\">\n" +
                            "                                                   <input type=\"hidden\" id=\"custId\" name=\"debtId\" value=\""+department.getId()+"\">"+
                            "                                                    <div class=\"form-group\">\n" +
                            "                                                        <label class=\"col-sm-4 control-label\">Department\n" +
                            "                                                            ID</label>\n" +
                            "                                                        <div class=\"col-sm-4\">\n" +
                            "                                                            <input type=\"number\" class=\"form-control\" name=\"deptId\"\n" +
                            "                                                                value=\""+department.getId()+"\" readonly=\"readonly\">\n" +
                            "                                                        </div>\n" +
                            "                                                    </div>\n" +
                            "\n" +
                            "                                                    <div class=\"form-group\">\n" +
                            "                                                        <label class=\"col-sm-4 control-label\">Department\n" +
                            "                                                            Name</label>\n" +
                            "                                                        <div class=\"col-sm-4\">\n" +
                            "                                                            <input type=\"text\" class=\"form-control\" name=\"deptName\"\n" +
                            "                                                                value=\""+department.getDeptName()+"\">\n" +
                            "                                                        </div>\n" +
                            "                                                    </div>\n" +
                            "\n" +
                            "                                                    <div class=\"form-group\">\n" +
                            "                                                        <label class=\"col-sm-4 control-label\">Department\n" +
                            "                                                            Description</label>\n" +
                            "                                                        <div class=\"col-sm-4\">\n" +
                            "                                                            <input type=\"text\" class=\"form-control\" name=\"deptDesc\"\n" +
                            "                                                                value=\""+department.getDeptDesc()+"\">\n" +
                            "                                                        </div>\n" +
                            "                                                    </div>\n" +
                            "\n" +
                            "                                                    <div class=\"modal-footer\">\n" +
                            "                                                        <button type=\"button\" class=\"btn btn-default\"\n" +
                            "                                                            data-dismiss=\"modal\">Close</button>\n" +
                            "                                                        <input type=\"submit\" class=\"btn btn-primary\"\n" +
                            "                                                            value=\"Update\"></button>\n" +
                            "                                                    </div>\n" +
                            "                                                </form>\n" +
                            "                                            </div>\n" +
                            "                                        </div>\n" +
                            "                                    </div>\n" +
                            "                                </div>\n" +
                            "                            </div>\n" +
                            "                        </div>\n";
        }
        return EditModals;
    }
    private long generateID(List<Department> departments){
        if (!departments.isEmpty()) {
            return departments.get(departments.size()-1).getId()+1;
        }else
            return 1;
    }
    public String departmentContent(){
        return "        <div class=\"row\">\n" +
                "            <!----- Menu Area Start ------>\n" +
                "            <div class=\"col-md-2 menucontent\">\n" +
                "                <a href=\"#\">\n" +
                "                    <h1>Dashboard</h1>\n" +
                "                </a>\n" +
                "                <ul class=\"nav nav-pills nav-stacked\">\n" +
                "                    <li role=\"presentation\"><a href=\"#\">Department</a></li>\n" +
                "                    <li role=\"presentation\"><a href=\"#\">Doctors</a></li>\n" +
                "                    <li role=\"presentation\"><a href=\"#\">Patients</a></li>\n" +
                "                    <li role=\"presentation\"><a href=\"#\">Nurse</a></li>\n" +
                "                    <li role=\"presentation\"><a href=\"#\">Room</a></li>\n" +
                "                    <li role=\"presentation\"><a href=\"#\">Pathology</a></li>\n" +
                "                    <li role=\"presentation\"><a href=\"#\">Blood Donor</a></li>\n" +
                "                    <li role=\"presentation\"><a href=\"#\">Billing</a></li>\n" +
                "                    <li role=\"presentation\"><a href=\"#\">Search</a></li>\n" +
                "                    <li role=\"presentation\"><a href=\"#\">Settings</a></li>\n" +
                "                </ul>\n" +
                "            </div>\n" +
                "            <!---- Menu Ares Ends  -------->\n" +
                "            <!-------   Content Area start  --------->\n" +
                "            <div class=\"col-md-10 maincontent\">\n" +
                "                <!-----------  Content Menu Tab Start   ------------>\n" +
                "                <div class=\"panel panel-default contentinside\">\n" +
                "                    <div class=\"panel-heading\">Manage Department</div>\n" +
                "                    <!----------------   Panel Body Start   --------------->\n" +
                "                    <div class=\"panel-body\">\n" +
                "                        <ul class=\"nav nav-tabs doctor\">\n" +
                "                            <li role=\"presentation\"><a href=\"#doctorlist\">Department List</a></li>\n" +
                "                            <li role=\"presentation\"><a href=\"#adddoctor\">Add Department</a></li>\n" +
                "                        </ul>\n" +
                "                        <!----------------   Display Department Data List start   --------------->\n" +
                "\n" +
                "                        <div id=\"doctorlist\" class=\"switchgroup\">\n" +
                "                       "+ createDepartmentTable()+
                "                        </div>\n" +
                "                        <!----------------   Display Department Data List ends   --------------->\n" +
                "\n" +
                "                        <!------ Edit Department Modal Start ---------->\n" +
                "                       "+createEditModal(departments)+
                "                        <!----------------   Modal ends here  --------------->\n" +
                "\n" +
                "                        <!----------------   Add Department Start   --------------->\n" +
                "                        <div id=\"adddoctor\" class=\"switchgroup\">\n" +
                "                            <div class=\"panel panel-default\">\n" +
                "                                <div class=\"panel-body\">\n" +
                "                                    <form class=\"form-horizontal\" action=\"./department\" method=\"post\">\n" +
                "                                        <div class=\"form-group\">\n" +
                "                                            <label class=\"col-sm-4 control-label\">Department ID</label>\n" +
                "                                            <div class=\"col-sm-4\">\n" +
                "                                                <input type=\"number\" class=\"form-control\" name=\"deptId\"\n" +
                "                                                    placeholder=\""+this.generateID(departments)+"\" readonly>\n" +
                "                                            </div>\n" +
                "                                        </div>\n" +
                "\n" +
                "                                        <div class=\"form-group\">\n" +
                "                                            <label class=\"col-sm-4 control-label\">Department Name</label>\n" +
                "                                            <div class=\"col-sm-4\">\n" +
                "                                                <input type=\"text\" class=\"form-control\" name=\"deptName\"\n" +
                "                                                    placeholder=\"Enter Department Name\">\n" +
                "                                            </div>\n" +
                "                                        </div>\n" +
                "\n" +
                "                                        <div class=\"form-group\">\n" +
                "                                            <label class=\"col-sm-4 control-label\">Department\n" +
                "                                                Description</label>\n" +
                "                                            <div class=\"col-sm-4\">\n" +
                "                                                <input type=\"text\" class=\"form-control\" name=\"deptDesc\"\n" +
                "                                                    placeholder=\"Enter Department Description here...\">\n" +
                "                                            </div>\n" +
                "                                        </div>\n" +
                "\n" +
                "\n" +
                "                                        <div class=\"form-group\">\n" +
                "                                            <div class=\"col-sm-offset-4 col-sm-10\">\n" +
                "                                                <button type=\"submit\" class=\"btn btn-primary\">Add\n" +
                "                                                    Department</button>\n" +
                "                                            </div>\n" +
                "                                        </div>\n" +
                "                                    </form>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                        <!----------------   Add Department Ends   --------------->\n" +
                "                    </div>\n" +
                "                    <!----------------   Panel Body Ends   --------------->\n" +
                "                </div>\n" +
                "                <!-----------  Content Menu Tab Ends   ------------>\n" +
                "            </div>\n" +
                "            <!-------   Content Area Ends  --------->\n" +
                "        </div>\n";
    }
}