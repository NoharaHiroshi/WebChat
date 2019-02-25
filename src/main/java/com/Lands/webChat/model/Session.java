package com.Lands.webChat.model;

import com.google.gson.Gson;
import com.Lands.webChat.util.Util;

import java.util.Date;

public class Session {
    private String id;

    private Date createdDate;

    private Date modifiedDate;

    private String userId;

    private String userName;

    private String homeId;

    private String content;

    public Session() {}

    public static Session sessionFactory(Session session) {
        session.id = Util.idGenerator();
        session.createdDate = new Date();
        session.modifiedDate = new Date();
        return session;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getHomeId() {
        return homeId;
    }

    public void setHomeId(String homeId) {
        this.homeId = homeId == null ? null : homeId.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
    public String toString() {
        Gson json = new Gson();
        return json.toJson(this);
    }
}