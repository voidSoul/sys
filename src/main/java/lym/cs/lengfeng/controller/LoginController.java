package lym.cs.lengfeng.controller;


import lym.cs.lengfeng.model.User;
import lym.cs.lengfeng.service.UserService;
import lym.cs.lengfeng.utils.Constants;
import lym.cs.lengfeng.utils.Response;
import lym.cs.lengfeng.utils.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public @ResponseBody Response login(@RequestBody User user, HttpSession session) {
        User u = userService.verify(user);
        if (u != null) {
            session.setAttribute(Constants.SESSION_KEY, u);
            User re = userService.findById(u.getId());
            return ResponseFactory.success(re);
        } else {
            return ResponseFactory.fail(Constants.REQUEST_FAIL, Constants.LOGIN_FAIL);
        }
    }
}
