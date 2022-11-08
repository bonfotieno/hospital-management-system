package com.hospital.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "admins")
public class Admin extends BaseEntity{
    @Column
    private String profile;
    @Column
    private String email;

    @Column
    private String phone;
    @Transient
    private String password;

    @Transient
    private String confirmPassword;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<Auth> auths = new ArrayList<Auth>();


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public List<Auth> getAuths() {
        return auths;
    }

    public void setAuths(List<Auth> auths) {
        this.auths = auths;
    }

    public void addAuth(Auth auth){
        auth.setAdmin(this);
        getAuths().add(auth);
    }
}
