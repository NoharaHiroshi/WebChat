package com.Lands.webChat.Controller;

import com.Lands.webChat.Service.UserService;
import com.Lands.webChat.model.User;
import java.lang.reflect.Type;

import com.Lands.webChat.webSocket.WebSocket;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import com.google.gson.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class IndexController {
    // 日志
    private static final Logger LOG = LoggerFactory.getLogger(IndexController.class);

    @Resource
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestBody User user) {
        LOG.info(user.toString());
        int result = userService.addUser(user);
        LOG.info(String.valueOf(result));
        return "success";
    }
}
