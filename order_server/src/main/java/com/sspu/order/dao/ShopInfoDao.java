package com.sspu.order.dao;

import com.sspu.order.po.ShopInfo;
import com.sspu.order.po.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShopInfoDao extends JpaRepository<ShopInfo, Integer> {
    //查询所有店铺
    List<ShopInfo> findAll();

    //模糊查询

    //更改店铺信息
    @Query(value = "select b from ShopInfo b where b.shop_name=:shop_name")
    ShopInfo updateShopInfo(@Param("shop_name") String shop_name);

    //查询店铺信息
    @Query(value = "select a from ShopInfo a where a.shop_name=:shop_name")
    ShopInfo findShopInfoByShop_name(@Param("shop_name") String shop_name);
}
