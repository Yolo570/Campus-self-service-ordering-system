package com.sspu.order.dao;

import com.sspu.order.po.CartFood;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FoodDao extends MongoRepository<CartFood,String> {
}
