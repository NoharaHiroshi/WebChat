package com.Lands.webChat.util;

import com.Lands.webChat.Controller.IndexController;
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

    // 加密
    public static String aes(String data, int mode) {
        String key = "123456";
        try {
            //1 加密内容 2 解密内容
            byte[] content;
            boolean encrypt = mode == Cipher.ENCRYPT_MODE;
            if (encrypt) {
                content = data.getBytes("UTF-8");
            } else {
                content = parseHexStr2Byte(data);
            }
            //1.构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            //2.根据ecnodeRules规则初始化密钥生成器
            //生成一个128位的随机源,根据传入的字节数组
            kgen.init(128, new SecureRandom(key.getBytes()));
            //3.产生原始对称密钥
            SecretKey secretKey = kgen.generateKey();
            //4.获得原始对称密钥的字节数组
            byte[] enCodeFormat = secretKey.getEncoded();
            //5.根据字节数组生成AES密钥
            SecretKeySpec keySpec = new SecretKeySpec(enCodeFormat, key);
            //6.根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance("AES");
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(mode, keySpec);
            byte[] result = cipher.doFinal(content);//加密或解密
            if (encrypt) {
                //将二进制转换成16进制
                LOG.info("加密结果： " + parseByte2HexStr(result));
                return parseByte2HexStr(result);
            } else {
                LOG.info("解密结果： " + new String(result, "UTF-8"));
                return new String(result, "UTF-8");
            }
        } catch (Exception e) {
            LOG.warn(e.toString());
            return "fail";
        }
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    public static void main(String[] args) {
        String  id = idGenerator();
        System.out.println(id);
    }
}
