package com.sspu.order.dao;

import com.sspu.order.po.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserInfoDao extends JpaRepository<UserInfo, Integer> {
    @Query(value = "select a from UserInfo a where a.user_name=:username and a.user_password=:password")
    UserInfo loginUserInfo(@Param("username") String username, @Param("password") String password);

    @Query(value = "select a from UserInfo a where a.user_name=:username")
    UserInfo findUserInfoByUser_name(@Param("username") String username);


}
