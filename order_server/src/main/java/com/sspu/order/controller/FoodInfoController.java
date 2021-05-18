package com.sspu.order.controller;

import com.sspu.order.po.FoodInfo;
import com.sspu.order.service.FoodInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/foodInfo")
public class FoodInfoController {

    @Autowired
    private FoodInfoService foodInfoService;

    @GetMapping("/foodInfos.action")
    public List<FoodInfo> findAllFoodInfos(String food_style_name) {
        List<FoodInfo> foodInfos = foodInfoService.findAllFoodInfos(food_style_name);
        return foodInfos;
    }

    @GetMapping("/allFood.action")
    public List<FoodInfo> findAll() {
        return foodInfoService.findAll();
    }


    @GetMapping("/foodInfo.action")
    public FoodInfo updateFoodInfo(@Param("food_name") String food_name, @Param("food_num") int food_num) {
        FoodInfo foodInfo = foodInfoService.getOne(foodInfoService.findFoodByFood_Name(food_name).getFood_id());
        foodInfo.setFood_num(food_num);
        FoodInfo save = foodInfoService.save(foodInfo);
        return save;
    }
/**
 * 更新商品销售量
 */
}
