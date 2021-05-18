package com.example.order_client.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.example.order_client.R;
import com.example.order_client.view.ShopsActivity;

public class OrderNone {
    private FragmentActivity mContext;
    private final LayoutInflater mInflater;
    private View mOrderNoneView;
    private Button btn_to_order;
    private ImageView iv_order;
    private TextView tv_order;
    private LinearLayout ll_order_none;

    public OrderNone(FragmentActivity mContext) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    public View createView() {
        initView();
        return mOrderNoneView;
    }

    private void initView() {
        mOrderNoneView = mInflater.inflate(R.layout.order_none, null);
        //取餐界面UI控件
        btn_to_order = mOrderNoneView.findViewById(R.id.btn_order_return);
        iv_order = mOrderNoneView.findViewById(R.id.iv_order_none);
        tv_order = mOrderNoneView.findViewById(R.id.tv_order);
        ll_order_none = mOrderNoneView.findViewById(R.id.ll_order_none);
        btn_to_order.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, ShopsActivity.class);
            mContext.startActivity(intent);
        });
    }

    public void showView() {
        mOrderNoneView.setVisibility(View.VISIBLE);
    }
}
