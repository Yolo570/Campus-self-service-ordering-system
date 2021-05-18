package com.sspu.order.controller;

import com.sspu.order.po.ShopInfo;
import com.sspu.order.service.ShopInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    private ShopInfoService shopInfoService;

    /**
     * 查询所有店铺信息
     *
     * @return
     */
    @GetMapping("/AllShops")
    public List<ShopInfo> findAllShops() {
        return shopInfoService.findAllShops();
    }

    /**
     * 查询某个店铺信息
     */
    @GetMapping("/oneShop.action")
    public ShopInfo findOneShop(int shop_id) {
        ShopInfo shopInfo = shopInfoService.getOne(shop_id);
        return shopInfo;
    }

    /**
     * 更新店铺销售量
     */
}
