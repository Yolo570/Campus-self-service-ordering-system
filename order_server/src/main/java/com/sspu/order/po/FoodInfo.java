package com.sspu.order.po;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@DynamicUpdate
@DynamicInsert
@Entity
@Proxy(lazy = false)
public class FoodInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int food_id;//食品id
    private String food_name;//食品名称
    private double food_price;//食品价格
    private int food_num;//选择食品的数量
    private String food_icon;//食品图片
    private int food_saleNum;//月销售量
    private int food_like;//点评
    @ManyToOne
    private FoodStyle foodStyle;//食品类型

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
        return foodStyle;
    }

    public void setFood_style(FoodStyle food_style) {
        this.foodStyle = food_style;
    }

    public FoodStyle getFoodStyle() {
        return foodStyle;
    }

    public void setFoodStyle(FoodStyle foodStyle) {
        this.foodStyle = foodStyle;
    }


    @Override
    public String toString() {
        return "FoodInfo{" +
                "food_id=" + food_id +
                ", food_name='" + food_name + '\'' +
                ", food_price=" + food_price +
                ", food_num=" + food_num +
                ", food_icon='" + food_icon + '\'' +
                ", food_saleNum=" + food_saleNum +
                ", food_like=" + food_like +
                ", food_style=" + foodStyle +
                '}';
    }
}
