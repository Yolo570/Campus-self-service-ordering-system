package com.sspu.order.service;

import com.sspu.order.dao.FoodStyleDao;
import com.sspu.order.po.FoodStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodStyleService {
    @Autowired
    private FoodStyleDao foodStyleDao;

    public List<FoodStyle> findAllFoodStyle(int shop_id) {
        return foodStyleDao.findAllByShop_id(shop_id);
    }

    public FoodStyle save(FoodStyle foodStyle) {
        return foodStyleDao.save(foodStyle);
    }

    //获取食品类型对应的食品
    public FoodStyle findFoodStyleByFood_style_name(String food_style_name) {
        return foodStyleDao.findFoodStyleByFood_style_name(food_style_name);
    }

    public FoodStyle getOne(int foodStyle_id) {
      return  foodStyleDao.getOne(foodStyle_id);
    }
}
