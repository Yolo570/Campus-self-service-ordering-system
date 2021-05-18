package com.example.order_client.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.order_client.R;
import com.example.order_client.po.FoodStyle;

import java.util.List;

public class FoodStyleAdapter extends BaseAdapter {
    private List<FoodStyle> foodStyles;
    private Context mContext;
    private ViewHolder vh;

    public FoodStyleAdapter(List<FoodStyle> foodStyles, Context context) {
        this.foodStyles = foodStyles;
        this.mContext = context;
    }


    @Override
    public int getCount() {
        return foodStyles == null ? 0 : foodStyles.size();
    }

    @Override
    public Object getItem(int position) {
        return foodStyles == null ? 0 : foodStyles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.food_style_item, null);
            vh.food_style_name = convertView.findViewById(R.id.food_style_name);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        FoodStyle foodStyle = foodStyles.get(position);
        if (foodStyle != null) {
            vh.food_style_name.setText(foodStyle.getFood_style_name());

//            if (position==0){
//                vh.food_style_name.setBackgroundColor(Color.WHITE);
//            }
        }
        return convertView;
    }


    public void setSelectItem(int selectItem) {

        this.selectItem = selectItem;
        vh.food_style_name.setBackgroundColor(Color.WHITE);
    }

    private int selectItem = 0;
}

class ViewHolder {
    TextView food_style_name;
//        RadioButton food_style_name;
}

