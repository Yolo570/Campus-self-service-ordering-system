package com.sspu.order.controller;

import com.sspu.order.po.FoodStyle;
import com.sspu.order.service.FoodStyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/foodStyle")
public class FoodStyleController {
    @Autowired
    private FoodStyleService foodStyleService;

    @GetMapping("/allFoodStyle.action")
    public List<FoodStyle> findAllFoodStyle(int shop_id) {
        List<FoodStyle> foodStyles = foodStyleService.findAllFoodStyle(shop_id);
        return foodStyles;
    }
}
