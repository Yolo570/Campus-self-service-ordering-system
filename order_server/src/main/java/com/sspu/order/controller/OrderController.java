package com.sspu.order.controller;

import com.google.gson.Gson;
import com.sspu.order.po.CartFood;
import com.sspu.order.po.FoodInfo;
import com.sspu.order.po.Order;
import com.sspu.order.po.ShopInfo;
import com.sspu.order.service.FoodInfoService;
import com.sspu.order.service.OrderService;
import com.sspu.order.service.ShopInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ShopInfoService shopInfoService;

    @Autowired
    private FoodInfoService foodInfoService;
    private Gson gson;


    /**
     * 保存订单信息
     *
     * @return
     */
    @PostMapping("/orderInfo.action")
    public void saveOrderInfo(@RequestParam(value = "order", required = false) String json) {
        gson = new Gson();
        Order order = gson.fromJson(json, Order.class);
        order.setCreatedDate(new Date());
        orderService.save(order);
        //更新店铺的销售量
        ShopInfo shopInfo = shopInfoService.getOne(shopInfoService.updateShopInfo(order.getCartShop().getShop_name()).getShop_id());
        shopInfo.setShop_saleNum(shopInfo.getShop_saleNum()+1);
        shopInfoService.addShopInfo(shopInfo);
        //更新购买商品的销售量
        List<CartFood> cartFoods = order.getCartFoods();
        for (CartFood cartFood : cartFoods) {
            FoodInfo foodInfo = foodInfoService.getOne(cartFood.getFood_id());
            foodInfo.setFood_saleNum(foodInfo.getFood_saleNum()+cartFood.getFood_num());
            foodInfoService.save(foodInfo);
        }
    }

    /**
     * 查询所有订单信息
     * @return
     */
    @GetMapping("/allOrderInfo.action")
    public List<Order> findAllOrderInfos(@Param("username") String username) {
        return orderService.findAllByUserName(username);
    }

    /**
     * 根据订单Id查询订单详情
     */
    @GetMapping("/findOrderInfo.action")
    public Order findOrderInfoByOrderId(@Param("orderId") long order_id){
        return orderService.findOneByOrderId(order_id);
    }
}
