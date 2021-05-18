package com.sspu.order.dao;

import com.sspu.order.po.FoodInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodInfoDao extends JpaRepository<FoodInfo, Integer> {

    //根据商品类型名称查询对应商品
    @Query(value = "select a from FoodInfo a where a.foodStyle.food_style_name=:food_style_name")
    List<FoodInfo> findFoodInfosByFoodStyleFood_style_name(@Param("food_style_name") String food_style_name);

    /**
     * 1.查询某个商品信息
     * 2.更新商品的销售数量
     */
    @Query(value = "select a from FoodInfo a where a.food_name=:food_name")
    FoodInfo findFoodInfoByFood_name(@Param("food_name") String food_name);

    /**
     * 查询店铺所有商品
     */
//      @Query(value = "select a from FoodInfo a "
//    List<FoodInfo> findFoodInfosBy(@Param("shopId") int shop_id);
}
