package com.example.order_client.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.order_client.R;
import com.example.order_client.adapter.HistoricalOrderAdapter;
import com.example.order_client.po.Order;
import com.example.order_client.utils.JsonParse;
import com.example.order_client.utils.SaveUserInfoUtil;
import com.example.order_client.view.MyInfoActivity;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public class HistoricalOrdersActivity extends AppCompatActivity {

    private String username;
    private ListView lv_orders;
    private List<Order> orders;
    private RelativeLayout main_tile;
    private ImageView iv_back;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historical_orders);
        //获取用户名
        Map<String, String> userInfo = SaveUserInfoUtil.getUserInfo(this);
        username = userInfo.get("username");
        initView();
        getOrdersData();
    }

    /**
     * 初始化历史订单界面
     */
    private void initView() {
        //顶部导航
        main_tile = (RelativeLayout) findViewById(R.id.main_tile);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("历史订单");
        lv_orders = (ListView) findViewById(R.id.lv_orders);
        iv_back.setOnClickListener(v -> {
            Intent intent = new Intent(HistoricalOrdersActivity.this, MyInfoActivity.class);
            startActivity(intent);
            finish();
        });
    }

    /**
     * 获取历史订单信息
     */
    private void getOrdersData() {
        String url = getString(R.string.server_url) + getString(R.string.orders_info) + "?username=" + username;
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                try {
                    String json = new String(bytes, "utf-8");
                    orders = JsonParse.getOrdersInfo(json);
                    //初始化历史订单适配器
                    lv_orders.setAdapter(new HistoricalOrderAdapter(HistoricalOrdersActivity.this, orders));
                    //设置每个历史订单点击事件
                    lv_orders.setOnItemClickListener((parent, view, position, id) -> {
                        Order order = (Order) parent.getItemAtPosition(position);
                        Intent intent = new Intent(HistoricalOrdersActivity.this, OrderDetailsActivity.class);
                        intent.putExtra(OrderDetailsActivity.ORDER_ID, order.getOrderId());
                        startActivity(intent);
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });
    }

}
