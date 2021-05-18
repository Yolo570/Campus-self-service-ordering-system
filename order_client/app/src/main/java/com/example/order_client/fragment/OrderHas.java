package com.example.order_client.fragment;

import android.os.Parcel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.example.order_client.R;
import com.example.order_client.adapter.OrderAdapter;
import com.example.order_client.po.FoodInfo;
import com.example.order_client.utils.RandomNumUtil;

import java.util.List;

public class OrderHas {
    private FragmentActivity mContext;
    private final LayoutInflater mInflater;
    private View mOrderHasView;
    private TextView order_num;
    private TextView waiting;
    private TextView order_detail;
    private ListView lv_order_info;
    private List<FoodInfo> cartList;
    private volatile String result = null;
    private TextView price;
    private double totalPrice = 0;
    private LinearLayout ll_order_info;


    public OrderHas(FragmentActivity mContext) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    public View createView() {
        cartList = (List<FoodInfo>) mContext.getIntent().getSerializableExtra("cartList");
        for (FoodInfo foodInfo : cartList) {
            totalPrice += (foodInfo.getFood_price() * foodInfo.getFood_num());
        }
        initView();
        return mOrderHasView;
    }

    private void initView() {
        mOrderHasView = mInflater.inflate(R.layout.order_has, null);
        order_num = (TextView) mOrderHasView.findViewById(R.id.order_num);//取餐码
        waiting = (TextView) mOrderHasView.findViewById(R.id.waiting);//制作等待
        order_detail = (TextView) mOrderHasView.findViewById(R.id.order_detail);//点击详情
        lv_order_info = (ListView) mOrderHasView.findViewById(R.id.lv_order_info);//订单详情
        ll_order_info = (LinearLayout) mOrderHasView.findViewById(R.id.ll_order_info);
        price = (TextView) mOrderHasView.findViewById(R.id.price);//订单总价格
        order_num.setText("取餐码：" + RandomNumUtil.Num());
        price.setText("￥" + totalPrice);
        lv_order_info.setAdapter(new OrderAdapter(cartList, mContext));
        setListViewHeightBasedOnChildren(lv_order_info);
        order_detail.setOnClickListener(v -> {
            if (ll_order_info.getVisibility() == View.VISIBLE) {
                ll_order_info.setVisibility(View.INVISIBLE);
            } else {
                ll_order_info.setVisibility(View.VISIBLE);
            }
        });

        Thread wait = new Thread(() -> {
            int count = 0;
            while (result == null) {
                setWaiting(count++);
            }
        });
        //制作的等待时长
        wait.start();
    }

    /**
     * 展示订单详情列表信息
     *
     * @param lv_order_info
     */
    private void setListViewHeightBasedOnChildren(ListView lv_order_info) {
        ListAdapter adapter = lv_order_info.getAdapter();
        if (adapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = adapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = adapter.getView(i, null, lv_order_info);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = lv_order_info.getLayoutParams();
        params.height = totalHeight + (lv_order_info.getDividerHeight() * (lv_order_info.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        lv_order_info.setLayoutParams(params);
    }

    private void setWaiting(int count) {
        int num = count % 4;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {
            sb.append(".");
        }
        mContext.runOnUiThread(() -> {
            waiting.setText(sb);
        });
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void showView() {
        mOrderHasView.setVisibility(View.VISIBLE);
    }
}
