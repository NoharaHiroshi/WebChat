package com.Lands.webChat.Controller;

import com.Lands.webChat.Service.UserService;
import com.Lands.webChat.model.User;
import java.lang.reflect.Type;

import com.Lands.webChat.util.ServiceResult;
import com.Lands.webChat.util.Util;
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
    public ServiceResult register(@RequestBody User user) {
        LOG.info(user.toString());
        int serviceRes = userService.addUser(user);
        try {
            if(serviceRes == 1) {
                ServiceResult result = ServiceResult.success("");
                return result;
            }else {
                ServiceResult result = ServiceResult.failure(-1, "创建失败");
                return result;
            }
        } catch (Exception e) {
            ServiceResult result = ServiceResult.failure(-99, "发送错误");
            return result;
        }
    }

    // 接收参数的方式，如果是String params
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ServiceResult login(@RequestBody Map<String, Object> params) {
        System.out.println(params);
        String name = params.get("name").toString();
        String password = params.get("password").toString();
        User user = userService.searchUser(name);
        System.out.println(user);
        return null;
    }
}
