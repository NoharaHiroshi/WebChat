package com.Lands.webChat.Service;

import com.Lands.webChat.mapper.UserMapper;
import com.Lands.webChat.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("UserService")
public class UserService {

    @Resource
    private UserMapper userMapper;

    public int addUser(User user) {
        try {
            int result = userMapper.insert(user);
            return result;
        } catch (Exception e){
            return -1;
        }
    }
}
