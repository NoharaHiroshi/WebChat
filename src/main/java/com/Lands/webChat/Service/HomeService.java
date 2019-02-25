package com.Lands.webChat.Service;

import com.Lands.webChat.mapper.HomeMapper;
import com.Lands.webChat.model.Home;
import com.Lands.webChat.model.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("HomeService")
public class HomeService {
    private static final Logger LOG = LoggerFactory.getLogger(HomeService.class);

    @Resource
    private HomeMapper homeMapper;

    public int addHome(Home home) {
        try {
            return homeMapper.insert(home);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return -1;
        }
    }
}
