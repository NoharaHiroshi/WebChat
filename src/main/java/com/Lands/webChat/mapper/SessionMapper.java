package com.Lands.webChat.mapper;

import com.Lands.webChat.model.Session;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface SessionMapper {
    @Delete({
        "delete from session",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into session (id, created_date, ",
        "modified_date, userId, ",
        "userName, homeId, ",
        "content)",
        "values (#{id,jdbcType=VARCHAR}, #{createdDate,jdbcType=TIMESTAMP}, ",
        "#{modifiedDate,jdbcType=TIMESTAMP}, #{userid,jdbcType=VARCHAR}, ",
        "#{username,jdbcType=VARCHAR}, #{homeid,jdbcType=VARCHAR}, ",
        "#{content,jdbcType=LONGVARCHAR})"
    })
    int insert(Session record);

    @InsertProvider(type=SessionSqlProvider.class, method="insertSelective")
    int insertSelective(Session record);

    @Select({
        "select",
        "id, created_date, modified_date, userId, userName, homeId, content",
        "from session",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="created_date", property="createdDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modified_date", property="modifiedDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="userId", property="userid", jdbcType=JdbcType.VARCHAR),
        @Result(column="userName", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="homeId", property="homeid", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    Session selectByPrimaryKey(String id);

    @UpdateProvider(type=SessionSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Session record);

    @Update({
        "update session",
        "set created_date = #{createdDate,jdbcType=TIMESTAMP},",
          "modified_date = #{modifiedDate,jdbcType=TIMESTAMP},",
          "userId = #{userid,jdbcType=VARCHAR},",
          "userName = #{username,jdbcType=VARCHAR},",
          "homeId = #{homeid,jdbcType=VARCHAR},",
          "content = #{content,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKeyWithBLOBs(Session record);

    @Update({
        "update session",
        "set created_date = #{createdDate,jdbcType=TIMESTAMP},",
          "modified_date = #{modifiedDate,jdbcType=TIMESTAMP},",
          "userId = #{userid,jdbcType=VARCHAR},",
          "userName = #{username,jdbcType=VARCHAR},",
          "homeId = #{homeid,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Session record);
}