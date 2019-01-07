package lym.cs.lengfeng.dao;

import lym.cs.lengfeng.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user where truename = #{user.trueName}")
    User getLengfeng(@Param("user") User user);

    @Select("select * from user where nickname = #{user.nickName} and password = #{user.password}")
    User findWithPwd(@Param("user") User user);

    @Select("select * from user where id = #{id}")
    User findById(@Param("id") int id);

    @Update("update user set role = #{user.role} where id = #{user.id}")
    void toAdmin(@Param("user") User user);

    @Insert("insert into user (nickname, truename, password, role) values(#{user.nickName}, #{user.trueName}, #{user.password}, #{user.role})")
    @Options(useGeneratedKeys = true, keyProperty = "user.id", keyColumn = "id")
    void addMember(@Param("user") User user);

    @Delete("delete from user where id = #{id}")
    void delete(@Param("id") int id);

    @Update("update user set nickname = #{user.nickName}, truename = #{user.trueName}, password = #{user.password}, role = #{user.role} where id = #{user.id}")
    @Options(useGeneratedKeys = true, keyProperty = "user.id", keyColumn = "id")
    void update(@Param("user") User user);

    @Select("select * from user order by id asc limit #{sr}, #{size}")
    List<User> getAllMember(@Param("sr") int startRow, @Param("size") int pageSize);

    @Select("select role from user where id = #{id}")
    int isAdmin(@Param("id") int id);
}
