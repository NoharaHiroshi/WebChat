package com.Lands.webChat.mapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.Lands.webChat.model.Home;

public class HomeSqlProvider {

    public String insertSelective(Home record) {
        BEGIN();
        INSERT_INTO("home");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedDate() != null) {
            VALUES("created_date", "#{createdDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifiedDate() != null) {
            VALUES("modified_date", "#{modifiedDate,jdbcType=TIMESTAMP}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(Home record) {
        BEGIN();
        UPDATE("home");
        
        if (record.getCreatedDate() != null) {
            SET("created_date = #{createdDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifiedDate() != null) {
            SET("modified_date = #{modifiedDate,jdbcType=TIMESTAMP}");
        }
        
        WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return SQL();
    }
}