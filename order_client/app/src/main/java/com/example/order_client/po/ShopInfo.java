package com.example.order_client.po;

import java.io.Serializable;

public class ShopInfo implements Serializable {
    private static final long SerialVersionUID = 1L;

    private int shop_id;
    private String shop_icon;
    private String shop_name;
    private int shop_saleNum;
    private int shop_offerPrice;
    private int shop_distributionCost;
    private String shop_adNotice;
    private String shop_time;
    private String shop_address;
    private FoodStyle foodStyle;

    public ShopInfo(int shop_id, String shop_icon, String shop_name, int shop_saleNum, int shop_offerPrice, int shop_distributionCost, String shop_adNotice, String shop_time, String shop_address, FoodStyle foodStyle) {
        this.shop_id = shop_id;
        this.shop_icon = shop_icon;
        this.shop_name = shop_name;
        this.shop_saleNum = shop_saleNum;
        this.shop_offerPrice = shop_offerPrice;
        this.shop_distributionCost = shop_distributionCost;
        this.shop_adNotice = shop_adNotice;
        this.shop_time = shop_time;
        this.shop_address = shop_address;
        this.foodStyle = foodStyle;
    }

    public static long getSerialVersionUID() {
        return SerialVersionUID;
    }

    public FoodStyle getFoodStyle() {
        return foodStyle;
    }

    public void setFoodStyle(FoodStyle foodStyle) {
        this.foodStyle = foodStyle;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public String getShop_icon() {
        return shop_icon;
    }

    public void setShop_icon(String shop_icon) {
        this.shop_icon = shop_icon;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public int getShop_saleNum() {
        return shop_saleNum;
    }

    public void setShop_saleNum(int shop_saleNum) {
        this.shop_saleNum = shop_saleNum;
    }

    public int getShop_offerPrice() {
        return shop_offerPrice;
    }

    public void setShop_offerPrice(int shop_offerPrice) {
        this.shop_offerPrice = shop_offerPrice;
    }

    public int getShop_distributionCost() {
        return shop_distributionCost;
    }

    public void setShop_distributionCost(int shop_distributionCost) {
        this.shop_distributionCost = shop_distributionCost;
    }

    public String getShop_adNotice() {
        return shop_adNotice;
    }

    public void setShop_adNotice(String shop_adNotice) {
        this.shop_adNotice = shop_adNotice;
    }

    public String getShop_time() {
        return shop_time;
    }

    public void setShop_time(String shop_time) {
        this.shop_time = shop_time;
    }

    public String getShop_address() {
        return shop_address;
    }

    public void setShop_address(String shop_address) {
        this.shop_address = shop_address;
    }
}
