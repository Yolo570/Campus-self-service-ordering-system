package com.sspu.order.service;

import com.sspu.order.dao.FoodDao;
import com.sspu.order.dao.FoodInfoDao;
import com.sspu.order.po.CartFood;
import com.sspu.order.po.FoodInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodInfoService {
    @Autowired
    private FoodInfoDao foodInfoDao;


    public FoodInfo save(FoodInfo foodInfo) {
        return foodInfoDao.save(foodInfo);
    }

    public List<FoodInfo> findAllFoodInfos(String food_style_name) {
        return foodInfoDao.findFoodInfosByFoodStyleFood_style_name(food_style_name);
    }

    /**
     * 根据店铺id查询所有商品信息
     */
    public List<FoodInfo> findAll() {
        return foodInfoDao.findAll();
    }

    /**
     * 获取某个商品信息
     */
    public FoodInfo findFoodByFood_Name(String food_name) {
        return foodInfoDao.findFoodInfoByFood_name(food_name);
    }

    /**
     * 更新商品销售数量
     *
     * @return
     */
    public FoodInfo updateFoodInfo(String food_name) {
        return foodInfoDao.findFoodInfoByFood_name(food_name);
    }

    public FoodInfo getOne(int food_id) {
        return foodInfoDao.getOne(food_id);
    }
}
