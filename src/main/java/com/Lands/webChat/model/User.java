package com.Lands.webChat.model;

import com.Lands.webChat.util.Util;
import com.google.gson.Gson;

import java.util.Date;

public class User {
    private String id;

    private String name;

    private String password;

    private Date lastLoginTime;

    private Integer status;

    private Date createdDate;

    private Date modifiedDate;

    public User(){
        this.id = Util.idGenerator();
        this.createdDate = new Date();
        this.modifiedDate = new Date();
    }

    public void setPassword(String password) {
        this.password = Util.aes(password, 0);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public String toString() {
        Gson json = new Gson();
        return json.toJson(this);
    }
}