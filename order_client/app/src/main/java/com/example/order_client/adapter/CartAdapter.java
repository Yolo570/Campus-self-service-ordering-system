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

public class CartAdapter extends BaseAdapter {
    private Context mContext;
    private List<FoodInfo> foodInfos;
    private ViewHolder vh;
    private OnSelectListener onSelectListener;

    public CartAdapter(List<FoodInfo> foodInfos, Context mContext, OnSelectListener onSelectListener) {
        this.mContext = mContext;
        this.foodInfos = foodInfos;
        this.onSelectListener = onSelectListener;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.cart_item, null);
//            vh.style_name = convertView.findViewById(R.id.style_name);
            vh.food_name = convertView.findViewById(R.id.food_name);
            vh.tv_food_num = convertView.findViewById(R.id.tv_food_num);
            vh.tv_food_price = convertView.findViewById(R.id.tv_food_price);
            vh.iv_cart_add = convertView.findViewById(R.id.iv_cart_add);
            vh.iv_cart_reduce = convertView.findViewById(R.id.iv_cart_reduce);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        FoodInfo foodInfo = foodInfos.get(position);
        if (foodInfo != null) {
            vh.food_name.setText(foodInfo.getFood_name());
            vh.tv_food_num.setText(foodInfo.getFood_num() + "");
            vh.tv_food_price.setText("￥" + (foodInfo.getFood_price() * foodInfo.getFood_num()));
        }
        vh.iv_cart_add.setOnClickListener(v -> {
            onSelectListener.onSelectAdd(position, vh.tv_food_price, vh.tv_food_num);
        });
        vh.iv_cart_reduce.setOnClickListener(v -> {
            onSelectListener.onSelectReduce(position, vh.tv_food_price, vh.tv_food_num);
        });
        return convertView;
    }

    public void setData(List<FoodInfo> cartList) {
        foodInfos = cartList;
        notifyDataSetChanged();
    }

    class ViewHolder {
        TextView food_name, tv_food_num, tv_food_price;
        ImageView iv_cart_add, iv_cart_reduce;
    }

    public interface OnSelectListener {
        void onSelectAdd(int position, TextView tv_food_price, TextView tv_food_num);

        void onSelectReduce(int position, TextView tv_food_price, TextView tv_food_num);
    }
}
