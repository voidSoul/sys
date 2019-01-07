package lym.cs.lengfeng.controller;

import lym.cs.lengfeng.model.User;
import lym.cs.lengfeng.service.UserService;
import lym.cs.lengfeng.utils.Constants;
import lym.cs.lengfeng.utils.Response;
import lym.cs.lengfeng.utils.ResponseFactory;
import lym.cs.lengfeng.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public Response profile(HttpSession session) {
        User user = UserUtil.getCurrentUser(session);
        if (user != null) {
            return ResponseFactory.success(user);
        } else {
            return ResponseFactory.fail(Constants.UN_AUTHORIZED, Constants.NOT_AUTHORIZED);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody Response modify(HttpSession session, @RequestBody User user) {
        User cu = UserUtil.getCurrentUser(session);
        if (cu.getId() == user.getId()) {
            userService.modify(user);
            return ResponseFactory.success(Constants.REQUEST_SUCCESS, Constants.SUCCESS);
        } else {
            return ResponseFactory.fail(Constants.UN_AUTHORIZED, Constants.NOT_AUTHORIZED);
        }
    }


}
