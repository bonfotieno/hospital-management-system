package com.hospital.controllers;

import com.hospital.model.Department;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

import java.util.List;

@Named("departmentBean")
@Stateless
@Remote
@TransactionManagement(TransactionManagementType.CONTAINER)
public class DepartmentBean implements Serializable {

    @PersistenceContext
    EntityManager em;

    private List<Department> list;
    public void add(Department department){
        if (department == null || StringUtils.isBlank(department.getDeptName()) || StringUtils.isBlank(department.getDeptDesc())){
            return;}
        em.merge(department);
    }

    public void update(Department department){
        Department dept = em.find(Department.class, department.getId());
        dept.setDeptName(department.getDeptName());
        dept.setDeptDesc(department.getDeptDesc());
    }

    public void delete(Department department){
        Department dept = em.find(Department.class, department.getId());
        em.remove(dept);
    }

    public List<Department> getList() {
        return em.createQuery("FROM Department d", Department.class).getResultList();
    }
    public void setList(List<Department> list) {
        this.list = list;
    }
}

/* Getting name
*
* <%  Connection c = (Connection) application.getAttribute("dbConnection");
    String deptName; PreparedStatement ps; ResultSet resultSet;
    ps=c.prepareStatement("select name from departments",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
    resultSet=ps.executeQuery();
    while(resultSet.next()) {
        deptName=resultSet.getString(1); %>
        <option value="<%=deptName%>">
            <%= deptName %>
        </option>
    <% } %>
*
* */