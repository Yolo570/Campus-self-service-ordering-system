package com.example.order_client.po;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {
    private static final long SerializableUID = 1L;
    private long orderId;
    private String userName;
    private Date createdDate;
    private List<CartFood> cartFoods;
    private double totalPrice;
    private CartShop cartShop;

    public CartShop getCartShop() {
        return cartShop;
    }

    public void setCartShop(CartShop cartShop) {
        this.cartShop = cartShop;
    }
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static long getSerializableUID() {
        return SerializableUID;
    }

    public List<CartFood> getCartFoods() {
        return cartFoods;
    }

    public void setCartFoods(List<CartFood> cartFoods) {
        this.cartFoods = cartFoods;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
