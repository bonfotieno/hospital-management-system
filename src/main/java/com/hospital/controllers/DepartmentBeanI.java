package com.hospital.controllers;

import com.hospital.model.Department;

import java.util.List;

public interface DepartmentBeanI {
    void add(Department department);

    void update(Department department);

    void delete(Department department);

    List<Department> getList();
    void setList(List<Department> list);
}
