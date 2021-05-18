package com.example.order_client.utils;

import com.example.order_client.po.FoodInfo;
import com.example.order_client.po.FoodStyle;
import com.example.order_client.po.Order;
import com.example.order_client.po.ShopInfo;
import com.example.order_client.po.UserInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class JsonParse {

    public static FoodInfo getFood(String json) {
        Gson gson = new Gson();
        FoodInfo foodInfo = gson.fromJson(json, FoodInfo.class);
        return foodInfo;
    }

    public static ShopInfo getShopDetail(String json) {
        Gson gson = new Gson();
        ShopInfo shopInfo = gson.fromJson(json, ShopInfo.class);
        return shopInfo;
    }

    public static List<ShopInfo> getShopInfo(String json) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<ShopInfo>>() {
        }.getType();
        List<ShopInfo> shopInfos = gson.fromJson(json, listType);
        return shopInfos;
    }

    public static UserInfo getUserInfo(String json) {
        Gson gson = new Gson();
        UserInfo userInfo = gson.fromJson(json, UserInfo.class);
        return userInfo;
    }

    public static List<FoodStyle> getFoodStyle(String json) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<FoodStyle>>() {
        }.getType();
        List<FoodStyle> foodStyles = gson.fromJson(json, listType);
        return foodStyles;
    }

    public static List<FoodInfo> getFoodInfos(String json) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<FoodInfo>>() {
        }.getType();
        List<FoodInfo> foodInfos = gson.fromJson(json, listType);
        return foodInfos;
    }

    /**
     * 获取历史订单
     * @param json
     * @return
     */
    public static List<Order> getOrdersInfo(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Order>>() {
        }.getType();
        List<Order> orders = gson.fromJson(json, type);
        return orders;
    }

    /**
     * 获取订单具体信息
     * @param json
     * @return
     */
    public static Order getOrderDetailInfo(String json) {
        Gson gson = new Gson();
        Order order = gson.fromJson(json, Order.class);
        return order;
    }
}
