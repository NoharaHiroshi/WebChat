package com.Lands.webChat.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class CipherUtil {
    private static final String KEY = "123456";
    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final Logger LOG = LoggerFactory.getLogger(CipherUtil.class);

    public static String encrypt(String content) {
        try {
            // 加密对象
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            // String内容转化为Byte
            byte[] byteContent = content.getBytes("utf-8");
            // 初始化，指定操作和加密秘钥
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(KEY));
            byte[] result = cipher.doFinal(byteContent);
            // 返回Base64编码
            return Base64.getEncoder().encodeToString(result);
        }catch (Exception e) {
            LOG.error(e.toString());
            return null;
        }
    }

    public static String decrypt(String base64Content) {
        try {
            // 加密对象
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            // Base64内容转化为Byte
            byte[] content = Base64.getDecoder().decode(base64Content);
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(KEY));
            // 解密
            byte[] result = cipher.doFinal(content);
            return new String(result, "utf-8");
        }catch (Exception e) {
            LOG.error(e.toString());
            return null;
        }
    }

    /**
     * 生成加密秘钥
     *
     * @return
     */
    private static SecretKeySpec getSecretKey(final String password) {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;

        try {
            // key模式为AES
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);
            //AES 要求密钥长度为 128
            kg.init(128, new SecureRandom(password.getBytes()));
            //生成一个密钥
            SecretKey secretKey = kg.generateKey();

            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);// 转换为AES专用密钥
        } catch (NoSuchAlgorithmException ex) {
            LOG.error(ex.toString());
        }

        return null;
    }

    public static void main(String[] args) {
        String info = CipherUtil.encrypt("hello");
        System.out.println(info);
        String raw = CipherUtil.decrypt(info);
        System.out.println(raw);
    }
}
