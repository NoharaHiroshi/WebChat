package com.Lands.webChat.Controller;

import com.Lands.webChat.Service.UserService;
import com.Lands.webChat.model.User;
import com.Lands.webChat.util.ServiceResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping(value = "/chat")
public class ChatController {
    private static final Logger LOG = LoggerFactory.getLogger(ChatController.class);

    @Resource
    private UserService userService;

    /**
     * 获取当前所有在线的用户
     */
    @RequestMapping(value = "/getOnlineUser", method = RequestMethod.GET)
    public ServiceResult getOnlineUser() {
        try {
            User[] userList = userService.getOnlineUser();
            ServiceResult result = ServiceResult.success(userList, "获取用户列表成功");
            return result;
        } catch (Exception e) {
            ServiceResult result = ServiceResult.failure(-99, "发送错误");
            return result;
        }
    }

    /**
     * 选择联系人，创建房间聊天
     */
    @RequestMapping(value = "connectChatHome", method = RequestMethod.POST)
    public ServiceResult connectChatHome(@RequestBody Map<String, Object> params) {
        LOG.info(params.toString());
        // 通过通信双方获取房间号，如果没有获取到，新建房间号

        return null;
    }
}
