package lym.cs.lengfeng.controller;


import lym.cs.lengfeng.model.Account;
import lym.cs.lengfeng.model.AccountResult;
import lym.cs.lengfeng.model.User;
import lym.cs.lengfeng.service.AccountService;
import lym.cs.lengfeng.utils.Constants;
import lym.cs.lengfeng.utils.Response;
import lym.cs.lengfeng.utils.ResponseFactory;
import lym.cs.lengfeng.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/account")
public class AccountController {


    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody Response findAllOfOneItem(@RequestParam("id") int id, HttpSession session) {
        User user = UserUtil.getCurrentUser(session);
        List<Account> accounts = accountService.getRecordsOfOneItem(id, user.getId());
        return ResponseFactory.success(accounts);
    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody Response findById(@RequestParam("id") int accountId, HttpSession session) {
        User user = UserUtil.getCurrentUser(session);
        Account account = accountService.findRecordById(accountId, user.getId());
        return ResponseFactory.response(account);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody Response updateRecord(@RequestBody Account account, HttpSession session) {
        User user = UserUtil.getCurrentUser(session);
        Account act = accountService.updateRecord(account, user.getId());
        return ResponseFactory.response(act);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody Response addRecord(@RequestBody Account account, HttpSession session) {
        User user = UserUtil.getCurrentUser(session);
        account.setUserId(user.getId());
        if (accountService.addRecord(account)) {
            return ResponseFactory.success(null);
        } else {
            return ResponseFactory.fail(Constants.REQUEST_FAIL, Constants.NOT_AUTHORIZED);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public @ResponseBody Response deleteRecord(@RequestParam("id") int accountId, HttpSession session) {
        User user = UserUtil.getCurrentUser(session);
        if(accountService.deleteRecord(accountId, user.getId())) {
            return ResponseFactory.success(null);
        } else {
            return ResponseFactory.fail(Constants.REQUEST_FAIL, Constants.NOT_AUTHORIZED);
        }
    }

    @RequestMapping(value = "/chart", method = RequestMethod.GET)
    public @ResponseBody Response viewSingleChart(@RequestParam("from") String from, @RequestParam("to") String to, HttpSession session) {
        User u = UserUtil.getCurrentUser(session);
        List<AccountResult> ars = accountService.getSingleChartResult(u.getId(), from, to);


        HashMap<String, HashMap<String, String>> result = new HashMap<>();
        for(AccountResult ar : ars) {
            String itemName = ar.getName();
            HashMap<String, String> mid = new HashMap<>();
            if (result.containsKey(itemName)) {
                mid = result.get(itemName);
                int preMoney = Integer.parseInt(mid.get("money"));
                mid.put("money", String.valueOf((preMoney + Integer.valueOf(ar.getMoney()))));
                result.put(itemName, mid);
            } else {
                mid.put("type", ar.getType() == 1 ? "支出" : "收入");
                mid.put("money", ar.getMoney());
                result.put(itemName, mid);
            }
        }
        if (!result.isEmpty()) {
            return ResponseFactory.success(result);
        } else {
            return ResponseFactory.fail(Constants.REQUEST_FAIL, Constants.NOT_AUTHORIZED);
        }
    }

}
