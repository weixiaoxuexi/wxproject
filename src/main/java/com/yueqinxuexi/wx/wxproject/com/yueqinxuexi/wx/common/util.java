package com.yueqinxuexi.wx.wxproject.com.yueqinxuexi.wx.common;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class util {
    // 定义基础的token信息，用于开发者验证
    private static String TOKEN = "helloqin";
    private static byte[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private static Logger log = LoggerFactory.getLogger(util.class);

    /**
     * 微信开发者验证
     * 1）将token、timestamp、nonce三个参数进行字典序排序 2）将三个参数字符串拼接成一个字符串进行sha1加密 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     * <p>
     * Author: Lixu
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        String[] arrs = new String[]{TOKEN, timestamp, nonce};
        // 字典排序
        Arrays.sort(arrs);

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < arrs.length; i++) {
            str.append(arrs[i]);
        }
        log.info("str is: "+ str);
        MessageDigest md = null;
        String temStr = null;
        try {
            md = MessageDigest.getInstance("SHA1");
            // byte[] digest = md.digest(str.toString().getBytes());
            md.update(str.toString().getBytes());
            temStr = code(md.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        log.info("signature is: " + signature);
        log.info("timestamp is: " + timestamp);
        log.info("nonce is: " + nonce);
        log.info("temStr is: " + temStr);
        return temStr != null ? temStr.equals(signature.toUpperCase()) : false;
    }


    /**
     * 将密文编码为16进制的字符串
     *
     * @param bytes
     * @return
     */
    public static String code(byte[] bytes) {
        int len = bytes.length;
        StringBuilder str = new StringBuilder(len * 2);
        for (int i = 0; i < len; i++) {
            str.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            str.append(HEX_DIGITS[(bytes[i] & 0x0f)]);
        }
        return str.toString();
    }

}