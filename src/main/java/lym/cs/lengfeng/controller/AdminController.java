package lym.cs.lengfeng.controller;

import lym.cs.lengfeng.model.User;
import lym.cs.lengfeng.service.UserService;
import lym.cs.lengfeng.utils.Constants;
import lym.cs.lengfeng.utils.Response;
import lym.cs.lengfeng.utils.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin/user")
public class AdminController {

    @Autowired
    private UserService userService;
    // todo
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody Response index(@RequestParam("page") int page) {
        int startRow = (page - 1) * Constants.PAGE_SIZE;
        return ResponseFactory.success(userService.getAllMember(startRow));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody Response addMember(@RequestBody User user) {
        userService.addMember(user);
        return ResponseFactory.success(Constants.REQUEST_SUCCESS, Constants.SUCCESS);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public @ResponseBody Response deleteMember(@RequestParam("id") int id) {
        if (userService.deleteMember(id)){
            return ResponseFactory.success(Constants.REQUEST_SUCCESS, Constants.SUCCESS);
        } else {
            return ResponseFactory.fail(Constants.REQUEST_FAIL, Constants.FAIL);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody Response viewMember(@RequestParam("id") int id) {
        User user = userService.findById(id);
        if (user != null) {
            return ResponseFactory.success(userService.findById(id));
        } else {
            return ResponseFactory.fail(Constants.REQUEST_FAIL, Constants.NOT_AUTHORIZED);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody Response updateMember(@RequestBody User user) {
        return ResponseFactory.success(userService.modify(user));
    }
}
