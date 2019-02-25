package com.Lands.webChat.mapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.Lands.webChat.model.Session;

public class SessionSqlProvider {

    public String insertSelective(Session record) {
        BEGIN();
        INSERT_INTO("session");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedDate() != null) {
            VALUES("created_date", "#{createdDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifiedDate() != null) {
            VALUES("modified_date", "#{modifiedDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUserid() != null) {
            VALUES("userId", "#{userid,jdbcType=VARCHAR}");
        }
        
        if (record.getUsername() != null) {
            VALUES("userName", "#{username,jdbcType=VARCHAR}");
        }
        
        if (record.getHomeid() != null) {
            VALUES("homeId", "#{homeid,jdbcType=VARCHAR}");
        }
        
        if (record.getContent() != null) {
            VALUES("content", "#{content,jdbcType=LONGVARCHAR}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(Session record) {
        BEGIN();
        UPDATE("session");
        
        if (record.getCreatedDate() != null) {
            SET("created_date = #{createdDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifiedDate() != null) {
            SET("modified_date = #{modifiedDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUserid() != null) {
            SET("userId = #{userid,jdbcType=VARCHAR}");
        }
        
        if (record.getUsername() != null) {
            SET("userName = #{username,jdbcType=VARCHAR}");
        }
        
        if (record.getHomeid() != null) {
            SET("homeId = #{homeid,jdbcType=VARCHAR}");
        }
        
        if (record.getContent() != null) {
            SET("content = #{content,jdbcType=LONGVARCHAR}");
        }
        
        WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return SQL();
    }
}