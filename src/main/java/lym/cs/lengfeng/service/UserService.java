package lym.cs.lengfeng.service;

import lym.cs.lengfeng.dao.UserMapper;
import lym.cs.lengfeng.model.User;
import lym.cs.lengfeng.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getLengfeng(User user) {
        return userMapper.getLengfeng(user);
    }

    public User findById(int id) {
        return userMapper.findById(id);
    }

    public void addMember(User user) {
       userMapper.addMember(user);
    }

    public boolean deleteMember(int id) {
        if (findById(id) == null) {
            return false;
        } else {
            userMapper.delete(id);
            return true;
        }
    }

    public User modify(User user) {
        userMapper.update(user);
        return findById(user.getId());
    }

    public User verify(User user) {
        User u = userMapper.findWithPwd(user);
        if ( u != null) {
            return u;
        } else {
            return null;
        }
    }

    public List<User> getAllMember(int startRow) {
        return userMapper.getAllMember(startRow, Constants.PAGE_SIZE);
    }

    public boolean isAdmin(int userId) {
        if (userMapper.isAdmin(userId) == 1) {
            return true;
        } else {
            return false;
        }
    }
}
