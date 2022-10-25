package com.hospital.model;

public class Department extends BaseEntity {
    private String deptName;
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
