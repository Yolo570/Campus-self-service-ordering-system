package com.sspu.order.po;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
@Document(collection = "food")
public class CartFood implements Serializable {
    private static final long SerialVersionUID = 1L;
    private int food_id;
    private String food_name;
    private int food_num;
    private String food_icon;
    private double totalPrice;

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public static long getSerialVersionUID() {
        return SerialVersionUID;
    }

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


}
