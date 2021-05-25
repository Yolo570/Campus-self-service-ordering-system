package com.example.order_client.utils;

import java.security.MessageDigest;
import java.util.Deque;
import java.util.LinkedList;

public class AlgorithmUtil {
    /**
     * 注册用户名要求
     */
    public static boolean RegisterByUserName(String username) {
        if (username.length() < 8 || username.length() > 12) {
            return false;
        }
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < username.length(); i++) {
            char c = username.charAt(i);
            stack.push(c);
            if (i < 3 && (c >= 'a' && c <= 'z')) {
                stack.pop();
            } else if (i >= 3) {
                if (c >= '0' && c <= '9') {
                    stack.pop();
                } else {
                    break;
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * 密码要求
     */
    public static boolean RegisterByPwd(String pwd) {
        if (pwd.length() < 6 || pwd.length() > 11) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 密码加密算法
     */
    public static String md5(String pwd) {
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(pwd.getBytes());
            StringBuffer sb = new StringBuffer();
            for (byte b : result) {
                int number = b & 0xff;
                String hex = Integer.toHexString(number);
                if (hex.length() == 1) {
                    sb.append("0" + hex);
                } else {
                    sb.append(hex);
                }
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
