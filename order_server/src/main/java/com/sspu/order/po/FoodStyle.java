package com.sspu.order.po;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@DynamicUpdate
@DynamicInsert
@Entity
@Proxy(lazy = false)
public class FoodStyle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int foodStyle_id;//食品类型id
    private String food_style_name;//食品类型名称
    @JsonBackReference
    @OneToMany(mappedBy = "foodStyle",fetch=FetchType.EAGER)
    private Set<FoodInfo> foodInfos = new HashSet<>();
    @ManyToOne
    private ShopInfo shopInfo;

    public int getFood_style_id() {
        return foodStyle_id;
    }

    public void setFood_style_id(int food_style_id) {
        this.foodStyle_id = food_style_id;
    }

    public String getFood_style_name() {
        return food_style_name;
    }

    public void setFood_style_name(String food_style_name) {
        this.food_style_name = food_style_name;
    }

    public Set<FoodInfo> getFoodInfos() {
        return foodInfos;
    }

    public void setFoodInfos(Set<FoodInfo> foodInfos) {
        this.foodInfos = foodInfos;
    }

    public ShopInfo getShopInfo() {
        return shopInfo;
    }

    public void setShopInfo(ShopInfo shopInfo) {
        this.shopInfo = shopInfo;
    }

    @Override
    public String toString() {
        return "FoodStyle{" +
                "food_style_id=" + foodStyle_id +
                ", food_style_name='" + food_style_name + '\'' +
//                ", foodInfos=" + foodInfos +
//                ", shopInfo=" + shopInfo +
                '}';
    }
}
