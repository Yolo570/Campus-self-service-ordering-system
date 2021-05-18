package com.example.order_client.po;

import java.io.Serializable;

public class FoodInfo implements Serializable {
    private static final long SerialVersionUID = 1L;
    private int food_id;
    private String food_name;
    private double food_price;
    private int food_num;
    private String food_icon;
    private int food_saleNum;
    private int food_like;
    private FoodStyle food_style;
    private ShopInfo shopInfo;

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public double getFood_price() {
        return food_price;
    }

    public void setFood_price(double food_price) {
        this.food_price = food_price;
    }

    public int getFood_num() {
        return food_num;
    }

    public void setFood_num(int food_num) {
        this.food_num = food_num;
    }

    public String getFood_icon() {
        return food_icon;
    }

    public void setFood_icon(String food_icon) {
        this.food_icon = food_icon;
    }

    public int getFood_saleNum() {
        return food_saleNum;
    }

    public void setFood_saleNum(int food_saleNum) {
        this.food_saleNum = food_saleNum;
    }

    public int getFood_like() {
        return food_like;
    }

    public void setFood_like(int food_like) {
        this.food_like = food_like;
    }

    public FoodStyle getFood_style() {
        return food_style;
    }

    public void setFood_style(FoodStyle food_style) {
        this.food_style = food_style;
    }

    public ShopInfo getShopInfo() {
        return shopInfo;
    }

    public void setShopInfo(ShopInfo shopInfo) {
        this.shopInfo = shopInfo;
    }
}
