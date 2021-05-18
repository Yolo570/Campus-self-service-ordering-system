package com.example.order_client.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.order_client.R;
import com.example.order_client.po.FoodInfo;

import java.util.List;

public class OrderAdapter extends BaseAdapter {
    private Context mContext;
    private List<FoodInfo> foodInfos;
    private ViewHolder vh;

    public OrderAdapter(List<FoodInfo> foodInfos, Context mContext) {
        this.mContext = mContext;
        this.foodInfos = foodInfos;
    }

    @Override
    public int getCount() {
        return foodInfos == null ? 0 : foodInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return foodInfos == null ? 0 : foodInfos.get(position);
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
        FoodInfo foodInfo = foodInfos.get(position);
        if (foodInfo != null) {
            vh.tv_food_name.setText(foodInfo.getFood_name());
            vh.tv_food_count.setText("X " +foodInfo.getFood_num());
            vh.food_price.setText("￥ " + foodInfo.getFood_price());
        }
        return convertView;
    }

    class ViewHolder {
        TextView tv_food_name, tv_food_count, food_price;
    }


}
