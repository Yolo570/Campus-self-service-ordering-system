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
import com.loopj.android.image.SmartImageView;

import java.math.BigDecimal;
import java.util.List;

public class FoodInfoAdapter extends BaseAdapter {
    private List<FoodInfo> foodInfos;
    private Context mContext;
    private ViewHolder vh;
    private OnSelectListener onSelectListener;


    public FoodInfoAdapter(List<FoodInfo> foodInfos, Context mContext, OnSelectListener onSelectListener) {
        this.foodInfos = foodInfos;
        this.mContext = mContext;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.food_info_item, null);
            vh.tv_food_name = convertView.findViewById(R.id.tv_food_name);
            vh.tv_food_sale_num = convertView.findViewById(R.id.tv_food_sale_num);
            vh.tv_food_cost = convertView.findViewById(R.id.tv_food_cost);
            vh.iv_food_icon = convertView.findViewById(R.id.iv_food_icon);
            vh.iv_add_food = convertView.findViewById(R.id.iv_add_food);//添加商品
            vh.iv_reduce = convertView.findViewById(R.id.iv_reduce);//减少商品
            vh.tv_food_num = convertView.findViewById(R.id.food_num);//商品数量
            //未添加商品隐藏减少图标以及商品数量
//            vh.iv_reduce.setVisibility(View.INVISIBLE);
////            vh.tv_food_num.setVisibility(View.INVISIBLE);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        FoodInfo foodInfo = foodInfos.get(position);
        if (foodInfo != null) {
            vh.tv_food_name.setText(foodInfo.getFood_name());
            vh.tv_food_sale_num.setText("已售" + foodInfo.getFood_saleNum() + "  " + "好评" + foodInfo.getFood_like());
            vh.tv_food_cost.setText("￥" + foodInfo.getFood_price() + "/份");
            vh.iv_food_icon.setImageUrl(mContext.getString(R.string.server_image_url) + foodInfo.getFood_icon(), R.drawable.error, R.drawable.error);
            vh.tv_food_num.setText(foodInfo.getFood_num()+"");
        }
        vh.iv_add_food.setOnClickListener(v ->{
//            vh.iv_reduce.setVisibility(View.VISIBLE);
//            vh.tv_food_num.setVisibility(View.VISIBLE);
            onSelectListener.onSelectAdd(position, vh.tv_food_num, vh.iv_reduce);
        } );
        vh.iv_reduce.setOnClickListener(v -> {
//            if ("".equals(vh.tv_food_num.getText())){
//                vh.iv_reduce.setVisibility(View.INVISIBLE);
//                vh.tv_food_num.setVisibility(View.INVISIBLE);
//            }
            onSelectListener.onSelectMin(position, vh.tv_food_num, vh.iv_reduce);
        });
        return convertView;
    }

    public void setData(List<FoodInfo> foodInfos) {
        this.foodInfos=foodInfos;
        notifyDataSetChanged();
    }

    class ViewHolder {
        TextView tv_food_name, tv_food_sale_num, tv_food_cost, tv_food_num;
        ImageView iv_add_food, iv_reduce;
        SmartImageView iv_food_icon;
    }

    public interface OnSelectListener {
        void onSelectAdd(int position, TextView tv_food_num, ImageView iv_reduce);

        void onSelectMin(int position, TextView tv_food_num, ImageView iv_reduce);

    }
}
