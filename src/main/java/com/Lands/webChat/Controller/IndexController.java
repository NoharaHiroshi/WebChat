package com.Lands.webChat.Controller;

import com.Lands.webChat.Service.UserService;
import com.Lands.webChat.model.User;

import com.Lands.webChat.util.ServiceResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import com.google.gson.*;

import javax.annotation.Resource;
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
        User userObj = User.userFactory(user);
        LOG.info(user.toString());
        int serviceRes = userService.addUser(userObj);
        try {
            if(serviceRes == 1) {
                ServiceResult result = ServiceResult.success("新建用户成功");
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
        LOG.info(params.toString());
        try {
            String name = params.get("name").toString();
            String password = params.get("password").toString();
            if (name.equals("") || password.equals("")) {
                ServiceResult result = ServiceResult.failure(-1, "用户名或密码未填写");
                return result;
            } else {
                // 创建了user对象时，默认调用了setPassword，所以又加了一次密
                User user = userService.searchUser(name);
                if (user == null){
                    ServiceResult result = ServiceResult.failure(-2, "当前用户不存在");
                    return result;
                } else {
                    if(user.authPassword(password)){
                        ServiceResult result = ServiceResult.success(User.getUser(user), "登录成功");
                        return result;
                    }else {
                        ServiceResult result = ServiceResult.failure(-3, "用户密码错误");
                        return result;
                    }
                }
            }
        } catch (Exception e) {
            LOG.error(e.toString());
            ServiceResult result = ServiceResult.failure(-99, "发生错误");
            return result;
        }
    }
}
