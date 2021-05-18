package com.sspu.order.service;

import com.sspu.order.po.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private MongoTemplate mongoTemplate;


    /**
     * 保存订单信息
     *
     * @return
     */
    public void save(Order order) {
        mongoTemplate.save(order);
    }

    /**
     * 查询所有订单
     *
     * @return
     */
    public List<Order> findAllByUserName(String username) {
        Query query = new Query(Criteria.where("userName").is(username)).with(Sort.by(Sort.Order.desc("createdDate")));
        return mongoTemplate.find(query, Order.class);
    }

    /**
     * 根据order_id查询订单详情
     *
     * @param order_id
     * @return
     */
    public Order findOneByOrderId(long order_id) {
        Query query = new Query(Criteria.where("orderId").is(order_id));
        return mongoTemplate.findOne(query, Order.class);
    }
}
