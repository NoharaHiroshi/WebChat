package com.Lands.webChat.Controller;

import com.Lands.webChat.Service.HomeService;
import com.Lands.webChat.Service.SessionService;
import com.Lands.webChat.Service.UserService;
import com.Lands.webChat.model.Home;
import com.Lands.webChat.model.Session;
import com.Lands.webChat.model.User;
import com.Lands.webChat.util.ServiceResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/chat")
public class ChatController {
    private static final Logger LOG = LoggerFactory.getLogger(ChatController.class);

    @Resource
    private UserService userService;

    @Resource
    private SessionService sessionService;

    @Resource
    private HomeService homeService;

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
    @RequestMapping(value = "/connectChatHome", method = RequestMethod.POST)
    public ServiceResult connectChatHome(@RequestBody Map<String, Object> params) {
        LOG.info(params.toString());
        // 查找发起人user关联的房间号
        try {
            String userId = params.get("userId").toString();
            User user = userService.getUser(userId);
            String oUserId = params.get("oUserId").toString();
            User oUser = userService.getUser(oUserId);
            if(user == null || oUser == null){
                ServiceResult result = ServiceResult.failure(-1, "当前用户不存在");
                return result;
            }else {
                // 查找当前用户房间号
                ArrayList<String> homeIds = sessionService.searchUserAllHome(userId);
                boolean isInHome = false;
                String existHomeId = "";
                // 遍历房间号和目标用户关系，如果没有，则表示之前没有创建房间，否则使用之前房间号
                for(String homeId: homeIds) {
                    if(sessionService.isInHome(homeId, oUserId)){
                        isInHome = true;
                        existHomeId = homeId;
                        break;
                    }
                }
                // 没有房间号则新创建
                if(!isInHome){
                    Home home = new Home();
                    Home homeObj = Home.homeFactory(home);
                    homeService.addHome(homeObj);
                    String homeId = homeObj.getId();

                    Session session = new Session();
                    session.setHomeId(homeId);
                    Session sessionObj = Session.sessionFactory(session);
                    sessionObj.setUserId(userId);
                    sessionObj.setUserName(user.getName());
                    sessionService.addSession(sessionObj);

                    Session sessionObj1 = Session.sessionFactory(session);
                    sessionObj1.setUserId(oUserId);
                    sessionObj1.setUserName(oUser.getName());
                    sessionService.addSession(sessionObj1);

                    HashMap<String, Object> map = new HashMap<>();
                    map.put("homeId", homeId);
                    return ServiceResult.success(map, "查询成功");

                }else {
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("homeId", existHomeId);
                    return ServiceResult.success(map, "查询成功");
                }
            }
        } catch (Exception e){
            ServiceResult result = ServiceResult.failure(-99, "发生错误");
            LOG.error(e.getMessage());
            return result;
        }
    }
}
