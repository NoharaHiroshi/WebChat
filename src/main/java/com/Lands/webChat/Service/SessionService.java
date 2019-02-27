package com.Lands.webChat.Service;

import com.Lands.webChat.mapper.SessionMapper;
import com.Lands.webChat.model.MsgSession;
import com.Lands.webChat.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.Lands.webChat.model.Home;

import java.util.ArrayList;
import java.util.List;

@Service("SessionService")
public class SessionService {
    private static final Logger LOG = LoggerFactory.getLogger(SessionService.class);

    @Resource
    private SessionMapper sessionMapper;

    public int addSession(MsgSession session) {
        try {
            return sessionMapper.insert(session);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return -1;
        }
    }

    // 搜索当前用户已创建房间号
    public MsgSession[] getSessionByUserId(String userId) {
        try {
            MsgSession[] sessionList = sessionMapper.selectByUserId(userId);
            return sessionList;
        } catch (Exception e){
            LOG.error(e.getMessage());
            return null;
        }
    }

    // 判断当前用户的房间号是否已创建过
    public boolean isInHome(String homeId, String userId) {
        try {
            MsgSession[] sessionList = sessionMapper.selectByHomeUserId(homeId, userId);
            if(sessionList.length != 0) {
                return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return false;
        }
    }

    // 获取当前房间号内的所有会话
    public MsgSession[] getSessionByHomeId(String homeId) {
        try {
            MsgSession[] sessionList = sessionMapper.selectByHomeId(homeId);
            return sessionList;
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return null;
        }
    }
}
