package com.hospital.controllers;

import com.hospital.model.Billing;

import java.util.List;

public interface BillingBeanI {
    void save(Billing billing);

    void update();

    void delete(Billing billing);

    List<Billing> getList();

    List<Billing> getBillingListByEmail(String email);
}
