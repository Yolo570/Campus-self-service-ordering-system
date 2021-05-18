package com.sspu.order;

import com.sspu.order.po.UserInfo;
import com.sspu.order.service.UserInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class UserInfoTests {
    @Autowired
    private UserInfoService userInfoService;

    @Test
    void addUser() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUser_name("123456@qq.com");
        userInfo.setUser_password("123");
        userInfo.setUser_nick("Tom");
        UserInfo user = userInfoService.saveUserInfo(userInfo);
        System.out.println(user);
    }

    @Test
    void loginUserInfo() {
        UserInfo info = userInfoService.loginUserInfo("123456@qq.com", "123");
        System.out.println(info);
    }

    @Test
    void updateUserInfo() {
        UserInfo userInfo = userInfoService.getOne(userInfoService.findUserByUserName("123456@qq.com").getUser_id());
        userInfo.setUser_icon("X");
        userInfo.setUser_nick("沐沐");
        userInfo.setUser_gender("女");
        userInfo.setUser_tel("12345678901");
        userInfo.setUser_age("16");
        userInfo.setUser_addr("上海浦东新区");
        UserInfo info = userInfoService.saveUserInfo(userInfo);
        System.out.println(info);
    }


}
