package lym.cs.lengfeng.controller;

import lym.cs.lengfeng.model.Item;
import lym.cs.lengfeng.model.User;
import lym.cs.lengfeng.service.ItemService;
import lym.cs.lengfeng.utils.Constants;
import lym.cs.lengfeng.utils.Response;
import lym.cs.lengfeng.utils.ResponseFactory;
import lym.cs.lengfeng.utils.UserUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody Response getItemList(@RequestParam("page") int page, HttpSession session) {
        User user = UserUtil.getCurrentUser(session);
        int startRow = (page - 1) * Constants.PAGE_SIZE;
        return ResponseFactory.success(itemService.getAllItems(user.getId(), startRow));
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody Response getItem(@RequestParam("id") int itemId, HttpSession session) {
        User user = UserUtil.getCurrentUser(session);
        Item item = itemService.findById(itemId, user.getId());
        if (item != null) {
            return ResponseFactory.success(item);    
        } else {
            return ResponseFactory.fail(Constants.UN_AUTHORIZED, Constants.NOT_AUTHORIZED);
        }
        
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody Response addItem(@RequestBody Item item, HttpSession session) {
        User user = UserUtil.getCurrentUser(session);
        item.setOwner(user.getId());
        itemService.addItem(item);
        return ResponseFactory.success(Constants.REQUEST_SUCCESS, Constants.SUCCESS);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody Response update(@RequestBody Item item, HttpSession session) {
        User user = UserUtil.getCurrentUser(session);
        Item itm = itemService.updateItem(item, user.getId());
        if (itm != null) {
            return ResponseFactory.success(itm);
        } else {
            return ResponseFactory.fail(Constants.UN_AUTHORIZED, Constants.NOT_AUTHORIZED);
        }

    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public @ResponseBody Response delete(@RequestParam("id") int itemId, HttpSession session) {
        User user = UserUtil.getCurrentUser(session);
        if (itemService.deleteItem(itemId, user.getId())) {
            return ResponseFactory.success(Constants.REQUEST_SUCCESS, Constants.SUCCESS);
        } else {
            return ResponseFactory.fail(Constants.REQUEST_FAIL, Constants.NOT_AUTHORIZED);
        }
    }
}
