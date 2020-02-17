package com.fosustu.omas.pojo;

public class UserRole {
    private String userid;

    private String roleid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getRole() {
        return roleid;
    }

    public void setRole(String role) {
        this.roleid = role == null ? null : role.trim();
    }
}