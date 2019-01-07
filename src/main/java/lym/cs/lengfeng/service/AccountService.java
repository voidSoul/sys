package lym.cs.lengfeng.service;

import lym.cs.lengfeng.dao.AccountMapper;
import lym.cs.lengfeng.dao.ItemMapper;
import lym.cs.lengfeng.model.Account;
import lym.cs.lengfeng.model.AccountResult;
import lym.cs.lengfeng.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private ItemMapper itemMapper;

    public Account findRecordById(int accountId, int userId) {
        return accountMapper.getRecord(accountId, userId);
    }

    public Account updateRecord(Account account, int userId) {
        if (findRecordById(account.getId(), userId) != null) {
            accountMapper.updateRecord(account);
            return account;
        } else {
            return null;
        }
    }

    public boolean deleteRecord(int accountId, int userId) {
        if (findRecordById(accountId, userId) != null) {
            accountMapper.deleteRecord(accountId);
            return true;
        } else {
            return false;
        }
    }

    public boolean addRecord(Account account) {
        Item item = itemMapper.select(account.getUserId(), account.getItemId());
        if (item != null) {
            accountMapper.addRecord(account);
            return true;
        } else {
            return false;
        }
    }

    public List<Account> getRecordsOfOneItem(int itemId, int userId) {
        return accountMapper.getRecordsOfOneItem(itemId, userId);
    }


    public List<AccountResult> getSingleChartResult(int id, String from, String to) {
        return accountMapper.getSingleAccount(id, from, to);
    }
}
