package com.example.order_client.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.order_client.R;
import com.example.order_client.po.ShopInfo;
import com.example.order_client.view.ShopsActivity;
import com.loopj.android.image.SmartImageView;

import java.util.List;

public class ShopsAdapter extends BaseAdapter {
    private List<ShopInfo> shopInfos;
    private Context mContext;
    private ViewHolder viewHolder;

    public ShopsAdapter(List<ShopInfo> shopInfos, Context context) {
        this.mContext = context;
        this.shopInfos = shopInfos;
    }

    @Override
    public int getCount() {
        return shopInfos == null ? 0 : shopInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return shopInfos == null ? 0 : shopInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.shop_item, null);
            viewHolder.tv_shop_name = convertView.findViewById(R.id.tv_shop_name);
            viewHolder.tv_sale_num = convertView.findViewById(R.id.tv_sale_num);
            viewHolder.tv_cost = convertView.findViewById(R.id.tv_cost);
            viewHolder.tv_shop_address = convertView.findViewById(R.id.tv_shop_address);
            viewHolder.tv_delivery_time = convertView.findViewById(R.id.tv_delivery_time);
            viewHolder.tv_adNotice = convertView.findViewById(R.id.tv_adNotice);
            viewHolder.tv_shop_time = convertView.findViewById(R.id.tv_shop_time);
            viewHolder.iv_shop_icon= convertView.findViewById(R.id.iv_shop_icon);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ShopInfo shopInfo = shopInfos.get(position);
        if (shopInfo != null) {
            viewHolder.iv_shop_icon.setImageUrl(mContext.getString(R.string.server_image_url) + shopInfo.getShop_icon(),R.drawable.error,R.drawable.error);
            viewHolder.tv_shop_name.setText(shopInfo.getShop_name());
            viewHolder.tv_sale_num.setText("月售" + shopInfo.getShop_saleNum());
            viewHolder.tv_cost.setText("起送￥" + shopInfo.getShop_offerPrice() + "   配送￥" + shopInfo.getShop_distributionCost());
            viewHolder.tv_shop_time.setText(shopInfo.getShop_time());
            viewHolder.tv_adNotice.setText(shopInfo.getShop_adNotice());
            viewHolder.tv_shop_address.setText(shopInfo.getShop_address());
            viewHolder.tv_delivery_time.setText("预计送达时间8分钟");
        }
        return convertView;
    }

    class ViewHolder {
        public TextView tv_shop_name, tv_sale_num, tv_cost, tv_shop_address, tv_delivery_time, tv_adNotice, tv_shop_time;
        public SmartImageView iv_shop_icon;
    }
}
