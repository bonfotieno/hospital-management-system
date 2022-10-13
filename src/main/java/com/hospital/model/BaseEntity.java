package com.hospital.model;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseEntity implements Serializable {

    private Long id;

    private Date timeCreated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }
}
