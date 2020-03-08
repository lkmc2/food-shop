package com.lin.utils;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5 工具类
 * @author lkmc2
 * @date 2020/3/8 13:34
 */
public final class Md5Utils {

    /**
     * 对字符串进行 MD5 加密
     * @param str 字符串
     * @return 加密后的字符串
     */
    public static String getMd5Str(String str) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if (md5 == null) {
            throw new RuntimeException("获取MD5失败");
        }

        return Base64.encodeBase64String(md5.digest(str.getBytes()));
    }

    public static void main(String[] args) {
        String result = getMd5Str("123456");
        System.out.println(result);
    }

}
