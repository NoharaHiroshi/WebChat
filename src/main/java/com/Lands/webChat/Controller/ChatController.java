package com.Lands.webChat.Controller;

import com.Lands.webChat.Service.HomeService;
import com.Lands.webChat.Service.SessionService;
import com.Lands.webChat.Service.UserService;
import com.Lands.webChat.model.Home;
import com.Lands.webChat.model.MsgSession;
import com.Lands.webChat.model.User;
import com.Lands.webChat.util.ServiceResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

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
     * 获取当前用户的历史房间号
     */
    @RequestMapping(value = "/getHistoryHomeId", method = RequestMethod.GET)
    public ServiceResult getHistoryHomeId(
            @RequestParam(value = "userId") String userId
    ) {
        try {
            MsgSession[] sessionList = sessionService.getSessionByUserId(userId);
            ServiceResult result = ServiceResult.success(sessionList, "获取用户会话编号成功");
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
                MsgSession[] sessionList = sessionService.getSessionByUserId(userId);
                boolean isInHome = false;
                String existHomeId = "";
                // 遍历房间号和目标用户关系，如果没有，则表示之前没有创建房间，否则使用之前房间号
                for(MsgSession session: sessionList) {
                    String homeId = session.getHomeId();
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

                    MsgSession session = new MsgSession();
                    session.setHomeId(homeId);
                    MsgSession sessionObj = MsgSession.sessionFactory(session);
                    sessionObj.setUserId(userId);
                    sessionObj.setUserName(user.getName());
                    sessionService.addSession(sessionObj);

                    MsgSession sessionObj1 = MsgSession.sessionFactory(session);
                    sessionObj1.setUserId(oUserId);
                    sessionObj1.setUserName(oUser.getName());
                    sessionService.addSession(sessionObj1);

                    HashMap<String, Object> map = new HashMap<>();
                    map.put("homeId", homeId);
                    return ServiceResult.success(map, "创建房间成功");

                }else {
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("homeId", existHomeId);
                    return ServiceResult.success(map, "查询房间成功");
                }
            }
        } catch (Exception e){
            ServiceResult result = ServiceResult.failure(-99, "发生错误");
            LOG.error(e.getMessage());
            return result;
        }
    }
}
