package com.Lands.webChat.model;

import java.util.Date;
import com.Lands.webChat.util.*;

public class User {
    private String id;

    private String name;

    private String password;

    private Integer status;

    private Date lastLoginTime;

    public User(){
        id = Util.idGenerator();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
