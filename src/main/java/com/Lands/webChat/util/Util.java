package com.Lands.webChat.util;

import com.Lands.webChat.Controller.IndexController;
import org.omg.CORBA.DATA_CONVERSION;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Date;
import java.util.Random;

public class Util {
    // 日志
    private static final Logger LOG = LoggerFactory.getLogger(Util.class);

    // 生成ID
    public static String idGenerator() {
        Long date = new Date().getTime();
        String datapPart = date.toString();
        Random random = new Random();
        StringBuffer randomPart = new StringBuffer();
        for(int i=0; i<6; i++) {
            String randomNum = String.valueOf(random.nextInt(9));
            randomPart.append(randomNum);
        }
        return datapPart + randomPart.toString();
    }

    public static void main(String[] args) {
        String  id = idGenerator();
        System.out.println(id);
    }
}
