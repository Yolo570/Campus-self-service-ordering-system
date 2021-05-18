package com.sspu.order.dao;

import com.sspu.order.po.FoodStyle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodStyleDao extends JpaRepository<FoodStyle, Integer> {


    @Query(value = "select  a from FoodStyle a where a.shopInfo.shop_id=:shop_id")
    List<FoodStyle> findAllByShop_id(@Param("shop_id") int shop_id);

    @Query(value = "select a from FoodStyle a where a.food_style_name=:food_style_name")
    FoodStyle findFoodStyleByFood_style_name(@Param("food_style_name") String food_style_name);
}
