package lym.cs.lengfeng.utils;

import lym.cs.lengfeng.model.User;

import javax.servlet.http.HttpSession;

public class UserUtil {
    private UserUtil() {

    }

    public static User getCurrentUser(HttpSession session) {
        return (User) session.getAttribute(Constants.SESSION_KEY);
    }
}
