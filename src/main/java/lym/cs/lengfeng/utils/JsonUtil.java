package lym.cs.lengfeng.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lym.cs.lengfeng.model.User;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonUtil {

    private JsonUtil() {

    }


    public static <T> T fromJson(String jsonString, Class<T> cls){
        T t = null;
        try {
            t = JSON.parseObject(jsonString,cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public static String toJson(Object obj){
        return JSON.toJSONString(obj);
    }

//    public static void main(String[] args) {
//        JSONObject jo = new JSONObject();
//        jo.put("a", 1);
//        System.out.println(toJson(jo));
//    }

}
