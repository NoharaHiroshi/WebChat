package com.Lands.webChat.model;

import com.Lands.webChat.util.Util;

import java.util.Date;

public class Home {
    private String id;

    private Date createdDate;

    private Date modifiedDate;

    public Home() {}

    public static Home homeFactory(Home home){
        home.id = Util.idGenerator();
        home.createdDate = new Date();
        home.modifiedDate = new Date();
        return home;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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
}