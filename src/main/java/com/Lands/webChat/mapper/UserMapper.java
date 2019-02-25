package com.Lands.webChat.mapper;

import com.Lands.webChat.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface UserMapper {
    @Delete({
        "delete from user",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into user (created_date, modified_date, id, name, ",
        "password, last_login_time, ",
        "status)",
        "values (#{createdDate,jdbcType=TIMESTAMP}, #{modifiedDate,jdbcType=TIMESTAMP},",
                "#{id,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{lastLoginTime,jdbcType=TIMESTAMP}, ",
        "#{status,jdbcType=INTEGER})"
    })
    int insert(User record);

    @InsertProvider(type=UserSqlProvider.class, method="insertSelective")
    int insertSelective(User record);

    @Select({
        "select",
        "created_date, modified_date, id, name, password, last_login_time, status",
        "from user",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="created_date", property="createdDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modified_date", property="modifiedDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="last_login_time", property="lastLoginTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER)
    })
    User selectByPrimaryKey(String id);

    @Select({
            "select",
            "created_date, modified_date, id, name, password, last_login_time, status",
            "from user",
            "where name = #{name,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="created_date", property="createdDate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="modified_date", property="modifiedDate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="last_login_time", property="lastLoginTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType=JdbcType.INTEGER)
    })
    User selectByName(String name);

    @Select({
            "select",
            "created_date, modified_date, id, name, password, last_login_time, status",
            "from user",
            "where status = 1"
    })
    @Results({
            @Result(column="created_date", property="createdDate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="modified_date", property="modifiedDate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="last_login_time", property="lastLoginTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType=JdbcType.INTEGER)
    })
    User[] selectAllOnlineUser();

    @UpdateProvider(type=UserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(User record);

    @Update({
        "update user",
        "set name = #{name,jdbcType=VARCHAR},",
          "created_date = #{createdDate,jdbcType=TIMESTAMP},",
          "modified_date = #{modifiedDate,jdbcType=TIMESTAMP},",
          "password = #{password,jdbcType=VARCHAR},",
          "last_login_time = #{lastLoginTime,jdbcType=TIMESTAMP},",
          "status = #{status,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(User record);
}