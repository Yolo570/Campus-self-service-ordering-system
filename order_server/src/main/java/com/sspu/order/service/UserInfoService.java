package com.sspu.order.service;

import com.sspu.order.dao.UserInfoDao;
import com.sspu.order.po.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {
    @Autowired
    private UserInfoDao userInfoDao;

    public UserInfo saveUserInfo(UserInfo userInfo) {
        return userInfoDao.save(userInfo);
    }

    public UserInfo loginUserInfo(String username, String password) {
        return userInfoDao.loginUserInfo(username, password);
    }

    public UserInfo findUserByUserName(String username) {
        return userInfoDao.findUserInfoByUser_name(username);
    }

    public UserInfo getOne(int id) {
        return userInfoDao.getOne(id);
    }

    /**
     * 判断数据库是否存在该用户名
     */
    public boolean checkedUser(String username) {
        UserInfo user = userInfoDao.findUserInfoByUser_name(username);
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }
}
