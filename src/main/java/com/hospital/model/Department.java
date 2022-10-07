package com.hospital.model;

import java.io.Serializable;

public class Department implements Serializable {
    private int deptId;
    private String deptName;
    private String deptDesc;

    public int getDeptId() {
        return deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public String getDeptDesc() {
        return deptDesc;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public void setDeptDesc(String deptDesc) {
        this.deptDesc = deptDesc;
    }
}
