package com.sspu.order;

import com.sspu.order.po.FoodInfo;
import com.sspu.order.po.FoodStyle;
import com.sspu.order.po.ShopInfo;
import com.sspu.order.service.FoodInfoService;
import com.sspu.order.service.FoodStyleService;
import com.sspu.order.service.ShopInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
class FoodInfoTests {
    @Autowired
    private FoodInfoService foodInfoService;
    @Autowired
    private FoodStyleService foodStyleService;
    @Test
    void save(){
        FoodStyle foodStyle = foodStyleService.getOne(foodStyleService.findFoodStyleByFood_style_name("肉类").getFood_style_id());
        FoodInfo foodInfo = new FoodInfo();
        foodInfo.setFood_name("牛肉卷");
        foodInfo.setFood_price(3.2);
        foodInfo.setFood_like(3);
        foodInfo.setFood_saleNum(112);
        foodInfo.setFood_style(foodStyle);
        FoodInfo info = foodInfoService.save(foodInfo);
        System.out.println(info);
    }

}
