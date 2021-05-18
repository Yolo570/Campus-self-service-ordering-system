package com.sspu.order.po;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@DynamicUpdate
@DynamicInsert
@Entity
@Proxy(lazy = false)
public class ShopInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int shop_id;//商店id
    private String shop_icon;//商店图标
    private String shop_name;//商店名称
    private int shop_saleNum;//月销售量
    private BigDecimal shop_offerPrice;//起送价格
    private BigDecimal shop_distributionCost;//配送费用
    private String shop_adNotice;//店铺广告
    private String shop_time;//配送时间
    private String shop_address;
    @JsonBackReference
    @OneToMany(mappedBy = "shopInfo",fetch=FetchType.EAGER)
    private Set<FoodStyle> foodStyles = new HashSet<>();

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

    public BigDecimal getShop_offerPrice() {
        return shop_offerPrice;
    }

    public void setShop_offerPrice(BigDecimal shop_offerPrice) {
        this.shop_offerPrice = shop_offerPrice;
    }

    public BigDecimal getShop_distributionCost() {
        return shop_distributionCost;
    }

    public void setShop_distributionCost(BigDecimal shop_distributionCost) {
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

    public Set<FoodStyle> getFoodStyles() {
        return foodStyles;
    }

    public void setFoodStyles(Set<FoodStyle> foodStyles) {
        this.foodStyles = foodStyles;
    }

    @Override
    public String toString() {
        return "ShopInfo{" +
                "shop_id=" + shop_id +
                ", shop_icon='" + shop_icon + '\'' +
                ", shop_name='" + shop_name + '\'' +
                ", shop_saleNum=" + shop_saleNum +
                ", shop_offerPrice=" + shop_offerPrice +
                ", shop_distributionCost=" + shop_distributionCost +
                ", shop_adNotice='" + shop_adNotice + '\'' +
                ", shop_time='" + shop_time + '\'' +
                ", shop_address='" + shop_address + '\'' +
//                ", foodStyles=" + foodStyles +
                '}';
    }
}
