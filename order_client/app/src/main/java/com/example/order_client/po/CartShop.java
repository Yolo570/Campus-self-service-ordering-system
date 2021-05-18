package com.example.order_client.po;

import java.io.Serializable;


public class CartShop implements Serializable {
    private static final long SerialVersionUID = 1L;
    private int shop_id;//商店id
    private String shop_icon;//商店图标
    private String shop_name;//商店名称

    public static long getSerialVersionUID() {
        return SerialVersionUID;
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
}
