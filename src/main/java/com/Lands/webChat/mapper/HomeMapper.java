package com.Lands.webChat.mapper;

import com.Lands.webChat.model.Home;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface HomeMapper {
    @Delete({
        "delete from home",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into home (id, created_date, ",
        "modified_date)",
        "values (#{id,jdbcType=VARCHAR}, #{createdDate,jdbcType=TIMESTAMP}, ",
        "#{modifiedDate,jdbcType=TIMESTAMP})"
    })
    int insert(Home record);

    @InsertProvider(type=HomeSqlProvider.class, method="insertSelective")
    int insertSelective(Home record);

    @Select({
        "select",
        "id, created_date, modified_date",
        "from home",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="created_date", property="createdDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modified_date", property="modifiedDate", jdbcType=JdbcType.TIMESTAMP)
    })
    Home selectByPrimaryKey(String id);

    @UpdateProvider(type=HomeSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Home record);

    @Update({
        "update home",
        "set created_date = #{createdDate,jdbcType=TIMESTAMP},",
          "modified_date = #{modifiedDate,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Home record);
}