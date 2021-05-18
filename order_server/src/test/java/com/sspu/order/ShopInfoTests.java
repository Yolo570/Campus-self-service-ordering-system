package com.sspu.order;

import com.sspu.order.po.ShopInfo;
import com.sspu.order.po.UserInfo;
import com.sspu.order.service.ShopInfoService;
import com.sspu.order.service.UserInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.util.List;
@SpringBootTest
class ShopInfoTests {
    @Autowired
    private ShopInfoService shopInfoService;

    @Test
   void addShop() {

        ShopInfo shopInfo = new ShopInfo();
        shopInfo.setShop_name("麻辣香锅");
        shopInfo.setShop_saleNum(100);
        shopInfo.setShop_adNotice("好吃，下次继续点!!!");
        shopInfo.setShop_offerPrice(new BigDecimal("20"));
        shopInfo.setShop_distributionCost(new BigDecimal("0"));
        shopInfo.setShop_time("7:00~20:00");
        shopInfo.setShop_address("西食苑一楼");
        ShopInfo shop = shopInfoService.addShopInfo(shopInfo);
        System.out.println(shop);
    }

    @Test
    void findAll(){
        List<ShopInfo> allShops = shopInfoService.findAllShops();
        for (ShopInfo shop : allShops) {
            System.out.println(shop);
        }
    }
    @Test
    void findOne(){
        ShopInfo shopInfo = shopInfoService.getOne(1);
        System.out.println(shopInfo);
    }

}
