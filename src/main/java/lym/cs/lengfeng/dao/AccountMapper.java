package lym.cs.lengfeng.dao;

import lym.cs.lengfeng.model.Account;
import lym.cs.lengfeng.model.AccountResult;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface AccountMapper {

    @Select("select user.truename, items.name, items.description, items.type, account.money, account.description, account.date from  account " +
            "left join items on account.item_id = items.id " +
            "left join user on account.user_id = user.id " +
            "where account.date between #{from} and #{to} and account.user_id = #{uid}")
    List<AccountResult> getSingleAccount(@Param("uid") int id, @Param("from")String from, @Param("to") String to);


    @Select("select user.truename, items.name, items.description, items.type, account.money, account.description, account.date from  account " +
            "left join items on account.item_id = items.id " +
            "left join user on account.user_id = user.id " +
            "where account.date between #{from} and #{to}")
    List<AccountResult> getTotalAccount(@Param("from") String from, @Param("to") String to);

    @Insert("insert into account(money, description, date, item_id, user_id) values (#{ac.money}, #{ac.description}, #{ac.date}, #{ac.itemId}, #{ac.userId})")
    @Options(useGeneratedKeys = true, keyProperty = "ac.id")
    void addRecord(@Param("ac") Account account);

    @Delete("delete from account where id = #{id}")
    void deleteRecord(@Param("id") int accountId);

    @Select("select * from account where id = #{id} and user_id = #{uid} or (select role from user where user.id = #{uid}) = 1")
    Account getRecord(@Param("id") int id, @Param("uid") int userId);

    @Update("update account set money = #{ac.money}, description = #{ac.description}, date = #{ac.date} where id = #{ac.id}")
    @Options(useGeneratedKeys = true, keyProperty = "ac.id")
    void updateRecord(@Param("ac") Account account);

    @Select("select * from account where item_id = #{id} and user_id = #{uid} or (select role from user where user.id = #{uid}) = 1")
    List<Account> getRecordsOfOneItem(@Param("id") int itemId, @Param("uid") int userId);

}
