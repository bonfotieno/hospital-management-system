package com.hospital.controllers;

import com.hospital.model.Pathology;

import java.util.List;

public interface PathologyBeanI {
    void save(Pathology pathology);

    void update();

    void delete(Pathology pathology);

    List<Pathology> getList();

    List<Pathology> getPathologyListByEmail(String email);

}
