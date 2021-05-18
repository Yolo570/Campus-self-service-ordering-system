package com.example.order_client.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.order_client.R;
import com.example.order_client.po.CartFood;
import com.example.order_client.po.FoodInfo;

import java.util.List;

public class OrderDetailsAdapter extends BaseAdapter {
    private Context mContext;
    private List<CartFood> cartFoods;
    private ViewHolder vh;

    public OrderDetailsAdapter(List<CartFood> cartFoods, Context mContext) {
        this.mContext = mContext;
        this.cartFoods = cartFoods;
    }

    @Override
    public int getCount() {
        return cartFoods == null ? 0 : cartFoods.size();
    }

    @Override
    public Object getItem(int position) {
        return cartFoods == null ? 0 : cartFoods.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.order_item, null);
            vh.tv_food_name = convertView.findViewById(R.id.tv_food_name);
            vh.tv_food_count = convertView.findViewById(R.id.tv_food_count);
            vh.food_price = convertView.findViewById(R.id.food_price);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        CartFood cartFood = cartFoods.get(position);
        if (cartFood != null) {
            vh.tv_food_name.setText(cartFood.getFood_name());
            vh.tv_food_count.setText("X " + cartFood.getFood_num());
            vh.food_price.setText("￥ " + cartFood.getTotalPrice());
        }
        return convertView;
    }

    class ViewHolder {
        TextView tv_food_name, tv_food_count, food_price;
    }


}
