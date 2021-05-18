package com.sspu.order.controller;

import com.sspu.order.po.UserInfo;
import com.sspu.order.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 用户登录
     */
    @PostMapping("/login.action")
    public UserInfo loginUserInfo(@Param("username") String username, @Param("password") String password) {
        return userInfoService.loginUserInfo(username, password);
    }

    /**
     * 用户注册
     */
    @PostMapping("/register.action")
    public UserInfo registerUserInfo(@Param("username") String username, @Param("password") String password) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUser_name(username);
        userInfo.setUser_password(password);
        return userInfoService.saveUserInfo(userInfo);
    }

    /**
     * 用户个人信息
     */
    @PostMapping("/findByUserName.action")
    public UserInfo findByUserName(@Param("username") String username) {
        System.out.println(1);
        return userInfoService.findUserByUserName(username);
    }

    /**
     * 用户更新信息
     */
    @PostMapping("/resetUserInfo.action")
    public UserInfo updateUserInfo(@Param("username") String username,
                                   @Param("nickname") String nickname,
                                   @Param("gender") String gender,
                                   @Param("phone") String phone,
                                   @Param("age") String age,
                                   @Param("addr") String addr) {
        UserInfo userInfo = userInfoService.getOne(userInfoService.findUserByUserName(username).getUser_id());
        userInfo.setUser_nick(nickname);
        userInfo.setUser_gender(gender);
        userInfo.setUser_tel(phone);
        userInfo.setUser_age(age);
        userInfo.setUser_addr(addr);
        userInfoService.saveUserInfo(userInfo);
        return userInfo;
    }

    /**
     * 用户修改密码
     */
}
