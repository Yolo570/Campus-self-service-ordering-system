package com.example.order_client.po;


public class UserInfo {

    /**
     * user_id : 1
     * user_icon : null
     * user_name : 123456@qq.com
     * user_password : 123
     * user_nick : 沐沐
     * user_gender : 女
     * user_tel : 12345678901
     * user_age : 16
     * user_addr : 上海浦东新区
     */

    private int user_id;
    private Object user_icon;
    private String user_name;
    private String user_password;
    private String user_nick;
    private String user_gender;
    private String user_tel;
    private String user_age;
    private String user_addr;

    public UserInfo() {
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Object getUser_icon() {
        return user_icon;
    }

    public void setUser_icon(Object user_icon) {
        this.user_icon = user_icon;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_nick() {
        return user_nick;
    }

    public void setUser_nick(String user_nick) {
        this.user_nick = user_nick;
    }

    public String getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }

    public String getUser_tel() {
        return user_tel;
    }

    public void setUser_tel(String user_tel) {
        this.user_tel = user_tel;
    }

    public String getUser_age() {
        return user_age;
    }

    public void setUser_age(String user_age) {
        this.user_age = user_age;
    }

    public String getUser_addr() {
        return user_addr;
    }

    public void setUser_addr(String user_addr) {
        this.user_addr = user_addr;
    }
}
