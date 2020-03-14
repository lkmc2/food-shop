package com.lin.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 手机号和邮箱工具类
 * @author lkmc2
 * @date 2020/3/14 17:39
 */
public class MobileEmailUtils {

    /**
     * 判断传入的手机号格式是否正确
     * @param mobile 手机号
     * @return 手机号格式是否正确
     */
    public static boolean checkMobileIsOk(String mobile) {
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(mobile);
        return m.matches();
    }

    /**
     * 判断传入的邮箱格式是否正确
     * @param email 邮箱
     * @return 邮箱格式是否正确
     */
    public static boolean checkEmailIsOk(String email) {
        boolean isMatch = true;
        if (!email.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")) {
            isMatch = false;
        }
        return isMatch;
    }

}

