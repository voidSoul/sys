package lym.cs.lengfeng.service;


import lym.cs.lengfeng.dao.ItemMapper;
import lym.cs.lengfeng.model.Item;
import lym.cs.lengfeng.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private UserService userService;

    public List<Item> getAllItems(int userId, int startRow) {
        return itemMapper.getAllItems(userId, startRow, Constants.PAGE_SIZE);
    }

    public Item findById(int itemId, int userId) {
        return itemMapper.select(itemId, userId);
    }

    public void addItem(Item item) {
        itemMapper.insert(item);
    }

    public boolean deleteItem(int itemId, int userId) {
        if (userService.isAdmin(userId) || findById(itemId, userId) != null) {
            itemMapper.delete(itemId);
            return true;
        } else {
            return false;
        }
    }

    public Item updateItem(Item item, int userId) {
        if (userService.isAdmin(userId) || findById(item.getId(), userId) != null) {
            itemMapper.update(item);
            return item;
        } else {
            return null;
        }

    }

    public List<Item> getItemsByUid(int uid) {
        return itemMapper.getItemsByUserId(uid);
    }
}
