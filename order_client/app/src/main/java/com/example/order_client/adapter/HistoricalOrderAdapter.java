package com.example.order_client.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.order_client.R;
import com.example.order_client.activity.HistoricalOrdersActivity;
import com.example.order_client.po.Order;
import com.example.order_client.view.MenuActivity;
import com.example.order_client.view.ShopsActivity;
import com.loopj.android.image.SmartImageView;

import java.time.ZoneId;
import java.util.List;

public class HistoricalOrderAdapter extends BaseAdapter {
    private Context mContext;
    private List<Order> orders;
    private ViewHolder vh;

    public HistoricalOrderAdapter(Context mContext, List<Order> orders) {
        this.mContext = mContext;
        this.orders = orders;
    }

    @Override
    public int getCount() {
        return orders == null ? 0 : orders.size();
    }

    @Override
    public Object getItem(int position) {
        return orders == null ? null : orders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.orders_info, null);
            vh.tv_shop_name = convertView.findViewById(R.id.tv_shop_name);
            vh.tv_my_comment = convertView.findViewById(R.id.tv_my_comment);
            vh.tv_order_time = convertView.findViewById(R.id.tv_order_time);
            vh.tv_totalPrice = convertView.findViewById(R.id.tv_totalPrice);
            vh.siv_shop_icon = convertView.findViewById(R.id.siv_shop_icon);
            vh.btn_again_order = convertView.findViewById(R.id.btn_again_order);
            vh.btn_to_comment = convertView.findViewById(R.id.btn_to_comment);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        //获取每个订单的部分信息
        Order order = orders.get(position);
        if (order != null) {
            vh.siv_shop_icon.setImageUrl(mContext.getString(R.string.server_image_url) + order.getCartShop().getShop_icon(), R.drawable.error, R.drawable.error);
            vh.tv_shop_name.setText(order.getCartShop().getShop_name());
            vh.tv_order_time.setText("下单时间：" + order.getCreatedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            vh.tv_totalPrice.setText("总价：￥" + order.getTotalPrice());
        }
        //TODO:再来一单
        vh.btn_again_order.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, ShopsActivity.class);
//            intent.putExtra("shopId",order.getCartShop().getShop_id());
            mContext.startActivity(intent);
            HistoricalOrdersActivity ordersActivity = (HistoricalOrdersActivity) HistoricalOrderAdapter.this.mContext;
            ordersActivity.finish();
        });
        //TODO:进行评论
        vh.btn_to_comment.setOnClickListener(v -> {

        });
        return convertView;
    }

    class ViewHolder {
        TextView tv_shop_name, tv_my_comment, tv_order_time, tv_totalPrice;
        TextView btn_again_order, btn_to_comment;
        SmartImageView siv_shop_icon;
    }
}
