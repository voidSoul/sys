package lym.cs.lengfeng.dao;

import lym.cs.lengfeng.model.Item;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ItemMapper {

    @Select("select * from items where owner = #{ownerId} or (select role from user where id = #{ownerId}) = 1 order by id asc limit #{sr}, #{size}")
    List<Item> getAllItems(@Param("ownerId") int ownerId, @Param("sr") int startRow, @Param("size") int size);

    @Select("select * from items where id = #{id} and owner = #{owner}")
    Item select(@Param("id") int id, @Param("owner") int owner);

    @Update("update items set name = #{item.name}, type = #{item.type}, detail = #{item.description} where id = #{item.id}")
    @Options(useGeneratedKeys = true, keyProperty = "item.id")
    void update(@Param("item") Item item);

    @Delete("delete from items where id = #{id}")
    void delete(@Param("id") int id);

    @Insert("insert into items(name, type, detail, owner) values(#{item.name}, #{item.type}, #{item.description}, #{item.owner})")
    @Options(useGeneratedKeys = true, keyProperty = "item.id")
    void insert(@Param("item") Item item);

    @Select("select owner from item where id = #{id}")
    int getOwner(@Param("id") int id);


}
