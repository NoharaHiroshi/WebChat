package com.Lands.webChat.util;

public class Message {

    // 房间号，通过房间号来转发消息
    private String homeId;

    // 消息内容
    private String msgContent;

    // 发送方
    private String fromUserId;

    // 接收方
    private String toUserId;

    // 发送时间
    private Date sendMsgDate;

    public Date getSendMsgDate() {
        return sendMsgDate;
    }

    public void setSendMsgDate(Date sendMsgDate) {
        this.sendMsgDate = sendMsgDate;
    }

    public String getHomeId(){
        return homeId;
    }

    public void setHomeId(String homeId) {
        this.homeId = homeId;
    }

    public String getMsgContent(){
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    @Override
    public String toString() {
        Gson json = new Gson();
        return json.toJson(this);
    }
}
