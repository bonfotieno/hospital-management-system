package com.hospital.model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
public class Department extends BaseEntity {

    @Column(name = "name")
    private String deptName;

    @Column(name = "description")
    private String deptDesc;

    public String getDeptName() {
        return deptName;
    }

    public String getDeptDesc() {
        return deptDesc;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public void setDeptDesc(String deptDesc) {
        this.deptDesc = deptDesc;
    }
}
