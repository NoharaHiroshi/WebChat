package com.Lands.webChat.model;

import com.Lands.webChat.util.CipherUtil;
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

    public User(){ }

    // 初始化User
    public static User userFactory(User user) {
        user.id = Util.idGenerator();
        user.createdDate = new Date();
        user.modifiedDate = new Date();
        user.password = CipherUtil.encrypt(user.password);
        return user;
    }

    // 返回User数据，一般是不需要的直接返回对象就行，User需要将password隐藏
    public static User getUser(User user) {
        user.setPassword("");
        return user;
    }

    // jsonToUser时默认调用setter方法
    // 从数据库中读出数据，构建user对象会调用User()方法
    // 从数据库中读出的值，要赋值给User的属性默认会作为参数调用setter方法
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean authPassword(String password) {
        try {
            // this.password = password 调用了setPassword的方法？是的，即使没有显示声明setter，也是默认通过setter方式赋值的
            String deStr = CipherUtil.decrypt(this.password);
            boolean result = deStr.equals(password);
            return result;
        } catch (Exception e) {
            return false;
        }
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