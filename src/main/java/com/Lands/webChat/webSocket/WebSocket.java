package com.Lands.webChat.webSocket;

import com.Lands.webChat.Service.SessionService;
import com.Lands.webChat.Service.UserService;
import com.Lands.webChat.model.Message;
import com.Lands.webChat.model.MsgSession;
import com.Lands.webChat.model.User;
import com.google.gson.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.Resource;
import javax.websocket.Session;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.awt.event.MouseWheelEvent;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.HashMap;
import java.util.Map;


// 创建一个WebSocket站点
@ServerEndpoint(value = "/webSocket/{userId}")
@Component
public class WebSocket {
    // 日志
    private static final Logger LOG = LoggerFactory.getLogger(WebSocket.class);

    // 线上用户数量
    private static Integer connectCount = 0;

    // 存放每个连接对象的session
    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<WebSocket>();

    // 会话实例
    private Session session;

    // 用户ID
    private String userId;

    private User user;

    private static UserService userService;

    private static SessionService sessionService;

    @Autowired
    public void setUserService(UserService userService) {
        WebSocket.userService = userService;
    }

    @Autowired
    public void setSessionService(SessionService sessionService) {WebSocket.sessionService = sessionService; }

    // 上线
    private Integer OnlineAddCount() {
        connectCount++;
        LOG.info("用户上线， 当前用户：" + connectCount);
        this.user.setStatus(1);
        userService.updateUser(this.user);
        return connectCount;
    }

    // 下线
    private Integer OnlineSubCount() {
        connectCount--;
        LOG.info("用户下线， 当前用户：" + connectCount);
        this.user.setStatus(0);
        userService.updateUser(this.user);
        return connectCount;
    }

    /**
     * 连接建立成功的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        this.userId = userId;
        this.user = userService.getUser(userId);
        for(WebSocket item: webSocketSet) {
            if(item.userId.equals(userId)) {
                LOG.info("用户【" + this.user.getName() + "】已登录，请勿重复登录");
                return;
            }
        }
        LOG.info("用户【" + this.user.getName() + "】登录");
        OnlineAddCount();
        webSocketSet.add(this);
        try {
            this.sendMessage("Connection Success!");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * 连接关闭的方法,这个方法运行的时候已经断开了连接，因此不能再向客户端发送信息了
     */
    @OnClose
    public void onClose(@PathParam("userId") String userId) {
        LOG.info("用户【" + this.user.getName() + "】退出");
        OnlineSubCount();
        webSocketSet.remove(this);
    }

    /**
     * 收到客户端发来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("userId") String userId) {
        messageHandler(message);
    }

    /**
     * 处理客户端发来的消息
     */
    public Message messageHandler(String message) {
        Gson json = new Gson();
        Message msg = json.fromJson(message, Message.class);
        LOG.info("用户【" + this.user.getName() + "】发送信息： " + msg.toString());
        return msg;
    }

    /** 服务器发送信息
     *
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

}
