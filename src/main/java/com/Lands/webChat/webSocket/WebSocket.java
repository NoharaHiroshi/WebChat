package com.Lands.webChat.webSocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;


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

    // 上线
    private Integer OnlineAddCount() {
        connectCount++;
        LOG.info("用户上线， 当前用户：" + connectCount);
        return connectCount;
    }

    // 下线
    private Integer OnlineSubCount() {
        connectCount--;
        LOG.info("用户下线， 当前用户：" + connectCount);
        return connectCount;
    }

    /**
     * 连接建立成功的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        LOG.info("用户【" + userId + "】登录");
        this.session = session;
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
        LOG.info("用户【" + userId + "】退出");
        OnlineSubCount();
        webSocketSet.remove(this);
    }

    /**
     * 收到客户端发来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("userId") String userId) {
        LOG.info("用户【" + userId + "】发送信息： " + message);
    }

    /** 发送信息
     *
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

}
