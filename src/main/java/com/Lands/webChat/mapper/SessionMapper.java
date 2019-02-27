package com.Lands.webChat.mapper;

import com.Lands.webChat.model.MsgSession;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface SessionMapper {
    @Delete({
        "delete from session",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into session (id, created_date, ",
        "modified_date, user_id, ",
        "user_name, home_id, ",
        "content)",
        "values (#{id,jdbcType=VARCHAR}, #{createdDate,jdbcType=TIMESTAMP}, ",
        "#{modifiedDate,jdbcType=TIMESTAMP}, #{userId,jdbcType=VARCHAR}, ",
        "#{userName,jdbcType=VARCHAR}, #{homeId,jdbcType=VARCHAR}, ",
        "#{content,jdbcType=LONGVARCHAR})"
    })
    int insert(MsgSession record);

    @InsertProvider(type=SessionSqlProvider.class, method="insertSelective")
    int insertSelective(MsgSession record);

    // 主键搜索
    @Select({
        "select",
        "id, created_date, modified_date, user_id, user_name, home_id, content",
        "from session",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="created_date", property="createdDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modified_date", property="modifiedDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
        @Result(column="home_id", property="homeId", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    MsgSession selectByPrimaryKey(String id);

    @Select({
            "select",
            "id, created_date, modified_date, user_id, user_name, home_id, content",
            "from session",
            "where user_id = #{userId,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="created_date", property="createdDate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="modified_date", property="modifiedDate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
            @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
            @Result(column="home_id", property="homeId", jdbcType=JdbcType.VARCHAR),
            @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    MsgSession[] selectByUserId(String userId);

    @Select({
            "select",
            "id, created_date, modified_date, user_id, user_name, home_id, content",
            "from session",
            "where home_id = #{homeId,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="created_date", property="createdDate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="modified_date", property="modifiedDate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
            @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
            @Result(column="home_id", property="homeId", jdbcType=JdbcType.VARCHAR),
            @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    MsgSession[] selectByHomeId(String homeId);

    @Select({
            "select",
            "id, created_date, modified_date, user_id, user_name, home_id, content",
            "from session",
            "where home_id = #{homeId,jdbcType=VARCHAR} and user_id = #{userId, jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="created_date", property="createdDate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="modified_date", property="modifiedDate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
            @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
            @Result(column="home_id", property="homeId", jdbcType=JdbcType.VARCHAR),
            @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    MsgSession[] selectByHomeUserId(String homeId, String userId);

    @UpdateProvider(type=SessionSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(MsgSession record);

    @Update({
        "update session",
        "set created_date = #{createdDate,jdbcType=TIMESTAMP},",
          "modified_date = #{modifiedDate,jdbcType=TIMESTAMP},",
          "user_id = #{userId,jdbcType=VARCHAR},",
          "user_name = #{userName,jdbcType=VARCHAR},",
          "home_id = #{homeId,jdbcType=VARCHAR},",
          "content = #{content,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKeyWithBLOBs(MsgSession record);

    @Update({
        "update session",
        "set created_date = #{createdDate,jdbcType=TIMESTAMP},",
          "modified_date = #{modifiedDate,jdbcType=TIMESTAMP},",
          "user_id = #{userId,jdbcType=VARCHAR},",
          "user_name = #{userName,jdbcType=VARCHAR},",
          "home_id = #{homeId,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(MsgSession record);
}