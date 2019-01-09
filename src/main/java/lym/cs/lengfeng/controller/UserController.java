package lym.cs.lengfeng.controller;

import lym.cs.lengfeng.model.User;
import lym.cs.lengfeng.service.ItemService;
import lym.cs.lengfeng.service.UserService;
import lym.cs.lengfeng.utils.Constants;
import lym.cs.lengfeng.utils.Response;
import lym.cs.lengfeng.utils.ResponseFactory;
import lym.cs.lengfeng.utils.UserUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

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

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public @ResponseBody Response getItems(@RequestParam("id") Integer uid) {
        return ResponseFactory.success(itemService.getItemsByUid(uid));
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public @ResponseBody Response getInfo(@RequestParam("id") Integer uid) {
        User user = userService.findById(uid);
        if (user != null) {
            return ResponseFactory.success(user);
        } else {
            return ResponseFactory.fail(Constants.REQUEST_FAIL, Constants.NOT_AUTHORIZED);
        }
    }
}
