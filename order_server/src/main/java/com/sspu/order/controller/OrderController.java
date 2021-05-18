package com.sspu.order.controller;

import com.google.gson.Gson;
import com.sspu.order.po.Order;
import com.sspu.order.service.OrderService;
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
    }

    /**
     * 查询所有订单信息
     *
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
