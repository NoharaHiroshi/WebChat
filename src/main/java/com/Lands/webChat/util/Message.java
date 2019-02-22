package com.Lands.webChat.util;

public class Message {

    // 房间号，通过房间号来转发消息
    private Integer homeId;

    // 消息内容
    private String msgContent;

    public Integer getHomeId(){
        return homeId;
    }

    public void setHomeId(Integer homeId) {
        this.homeId = homeId;
    }

    public String getMsgContent(){
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    @Override
    public String toString() {
        return "Message{homeId: " + homeId +
                ", msgContent: " + msgContent +
                "}";
    }
}
