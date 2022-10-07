package com.hospital.common;

public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    NON_BINARY("Non Binary");

    private String name;

    Gender(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
