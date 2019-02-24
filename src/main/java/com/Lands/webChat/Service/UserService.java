package com.Lands.webChat.Service;

import com.Lands.webChat.mapper.UserMapper;
import com.Lands.webChat.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("UserService")
public class UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Resource
    private UserMapper userMapper;

    public int addUser(User user) {
        try {
            int result = userMapper.insert(user);
            return result;
        } catch (Exception e){
            LOG.error(e.getMessage());
            return -1;
        }
    }

    public User searchUser(String name) {
        try {
            User user = userMapper.selectByName(name);
            return user;
        } catch (Exception e){
            LOG.error(e.getMessage());
            return null;
        }
    }
}
