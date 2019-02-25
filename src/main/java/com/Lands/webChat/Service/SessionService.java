package com.Lands.webChat.Service;

import com.Lands.webChat.mapper.SessionMapper;
import com.Lands.webChat.model.Session;
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

    public int addSession(Session session) {
        try {
            return sessionMapper.insert(session);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return -1;
        }
    }

    public ArrayList<String> searchUserAllHome(String userId) {
        try {
            ArrayList<String> result = new ArrayList<>();
            Session[] homeList = sessionMapper.selectByUserId(userId);
            for(Session home: homeList){
                result.add(home.getHomeId());
            }
            return result;
        } catch (Exception e){
            LOG.error(e.getMessage());
            return null;
        }
    }

    public boolean isInHome(String homeId, String userId) {
        try {
            Session[] sessionList = sessionMapper.selectByHomeId(homeId, userId);
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

    public Session[] getSessionByHomeId(String homeId, String userId) {
        try {
            Session[] sessionList = sessionMapper.selectByHomeId(homeId, userId);
            return sessionList;
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return null;
        }
    }
}
