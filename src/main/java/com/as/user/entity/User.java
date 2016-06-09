package com.as.user.entity;

import com.as.common.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Newbody on 2016/3/1.
 */
@Entity
@Table(name = "as_tb_user")
public class User extends BaseEntity {
    public final static String ROLE_ADMIN = "admin";
    public final static String ROLE_STANDARD = "standard";

    private String role;

    private String username;
    private String adminPassword;
    private String standardPassword;

    @Transient
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getStandardPassword() {
        return standardPassword;
    }

    public void setStandardPassword(String standardPassword) {
        this.standardPassword = standardPassword;
    }
}
