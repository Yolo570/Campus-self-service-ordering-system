package com.sspu.order.po;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Document(collection = "order")
@CompoundIndex(def = "{'createdDate': -1}")
public class Order implements Serializable {
    private static final long SerializableUID = 1L;
    /**
     * 订单ID
     **/
    @Id
    private long orderId;
    /**
     * 下单时间
     **/
    private Date createdDate;
    /**
     * 下单用户
     **/
    private String userName;
    /**
     * 购买商品json
     **/
    private List<CartFood> cartFoods;
    /**
     * 总价
     */
    private double totalPrice;
    //订单对应的店铺
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", createdDate=" + createdDate +
                ", userName='" + userName + '\'' +
                ", foodInfos=" + cartFoods +
                '}';
    }
}