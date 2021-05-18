package com.example.order_client.po;

import java.io.Serializable;

public class FoodStyle implements Serializable {
    private static final long SerialVersionUID = 1L;
    private int foodStyle_id;//食品类型id
    private String food_style_name;//食品类型名称
    private FoodInfo foodInfo;
    private ShopInfo shopInfo;

    public static long getSerialVersionUID() {
        return SerialVersionUID;
    }

    public int getFoodStyle_id() {
        return foodStyle_id;
    }

    public void setFoodStyle_id(int foodStyle_id) {
        this.foodStyle_id = foodStyle_id;
    }

    public String getFood_style_name() {
        return food_style_name;
    }

    public void setFood_style_name(String food_style_name) {
        this.food_style_name = food_style_name;
    }

    public FoodInfo getFoodInfo() {
        return foodInfo;
    }

    public void setFoodInfo(FoodInfo foodInfo) {
        this.foodInfo = foodInfo;
    }

    public ShopInfo getShopInfo() {
        return shopInfo;
    }

    public void setShopInfo(ShopInfo shopInfo) {
        this.shopInfo = shopInfo;
    }
}
