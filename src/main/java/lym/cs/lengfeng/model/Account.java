package lym.cs.lengfeng.model;

import com.alibaba.fastjson.annotation.JSONField;
import lym.cs.lengfeng.utils.JsonUtil;

import java.sql.Date;

public class Account {
    private int id;
    private String money;
    private String description;
    private String date;
    @JSONField(name = "item_id")
    private int itemId;
    @JSONField(name = "user_id")
    private int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }

}
