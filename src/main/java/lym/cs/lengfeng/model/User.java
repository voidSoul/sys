package lym.cs.lengfeng.model;

import com.alibaba.fastjson.annotation.JSONField;
import lym.cs.lengfeng.utils.JsonUtil;
import org.apache.ibatis.annotations.SelectKey;

import javax.annotation.Generated;

public class User {
    private int id;
    @JSONField(name = "nickname")
    private String nickName;
    @JSONField(name = "truename")
    private String trueName;
    private String password;
    private int role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
