package com.sspu.order;

import com.sspu.order.po.FoodStyle;
import com.sspu.order.po.ShopInfo;
import com.sspu.order.service.FoodStyleService;
import com.sspu.order.service.ShopInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
class FoodStyleTests {
    @Autowired
    private FoodStyleService foodStyleService;
    @Autowired
    private ShopInfoService shopInfoService;

    @Test
    void addFoodStyle() {
        ShopInfo shopInfo = shopInfoService.getOne(shopInfoService.findShopInfoByShop_name("麻辣香锅").getShop_id());
        FoodStyle foodStyle = new FoodStyle();
        foodStyle.setFood_style_name("肉类");
        foodStyle.setShopInfo(shopInfo);
        FoodStyle style = foodStyleService.save(foodStyle);
        System.out.println(style);

    }


}
