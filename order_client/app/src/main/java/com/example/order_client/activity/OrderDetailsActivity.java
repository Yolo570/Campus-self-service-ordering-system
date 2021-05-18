package com.example.order_client.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.order_client.R;
import com.example.order_client.adapter.OrderDetailsAdapter;
import com.example.order_client.po.Order;
import com.example.order_client.utils.JsonParse;
import com.example.order_client.view.MyInfoActivity;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.image.SmartImageView;

import org.apache.http.Header;

import java.io.UnsupportedEncodingException;
import java.time.ZoneId;

public class OrderDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String ORDER_ID = "ORDER_ID";
    private long order_id;
    private RelativeLayout main_tile;
    private ImageView iv_back;
    private TextView tv_title;
    private ProgressBar progressBar;
    private LinearLayout ll_order;
    private LinearLayout ll_order_details;
    private SmartImageView siv_shop_icon;
    private TextView tv_shop_name;
    private Button btn_again_order;
    private Button btn_to_comment;
    private ListView lv_order_food;
    private TextView price;
    private TextView tv_order_id;
    private TextView tv_order_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        //获取订单id
        order_id = getIntent().getExtras().getLong(ORDER_ID);
        initView();
        getOrderData();
    }

    /**
     * 初始化UI控件
     */
    private void initView() {
        //获取顶部导航
        main_tile = (RelativeLayout) findViewById(R.id.main_tile);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("订单详情");
        //获取加载进度条
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //获取订单详情
        ll_order = (LinearLayout) findViewById(R.id.ll_order);
        ll_order_details = (LinearLayout) findViewById(R.id.ll_order_details);
        //店铺logo
        siv_shop_icon = (SmartImageView) findViewById(R.id.siv_shop_icon);
        //店铺名称
        tv_shop_name = (TextView) findViewById(R.id.tv_shop_name);
        //再来一单
        btn_again_order = (Button) findViewById(R.id.btn_again_order);
        //用户评论
        btn_to_comment = (Button) findViewById(R.id.btn_to_comment);
        //商品列表
        lv_order_food = (ListView) findViewById(R.id.lv_order_food);
        //商品总价
        price = (TextView) findViewById(R.id.price);
        //取餐码
        tv_order_id = (TextView) findViewById(R.id.tv_order_id);
        //下单时间
        tv_order_time = (TextView) findViewById(R.id.tv_order_time);
        //返回、再来一单、评论按钮监听事件
        iv_back.setOnClickListener(this);
        btn_again_order.setOnClickListener(this);
        btn_to_comment.setOnClickListener(this);

    }

    private void setListViewHeightBasedOnChildren(ListView lv_order_food) {
        ListAdapter adapter = lv_order_food.getAdapter();
        if (adapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = adapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = adapter.getView(i, null, lv_order_food);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = lv_order_food.getLayoutParams();
        params.height = totalHeight + (lv_order_food.getDividerHeight() * (lv_order_food.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        lv_order_food.setLayoutParams(params);
    }

    /**
     * 根据订单ID获取订单详情
     */
    private void getOrderData() {
        String url = getString(R.string.server_url) + getString(R.string.order) + "?order_id=" + order_id;
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new AsyncHttpResponseHandler() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                try {
                    String json = new String(bytes, "utf-8");
                    Order order = JsonParse.getOrderDetailInfo(json);
                    if (order != null) {
                        Thread.sleep(2000);
                        progressBar.setVisibility(View.GONE);
                        ll_order.setVisibility(View.VISIBLE);
                        ll_order_details.setVisibility(View.VISIBLE);
                        siv_shop_icon.setImageUrl(getString(R.string.server_image_url) + order.getCartShop().getShop_icon());
                        tv_shop_name.setText(order.getCartShop().getShop_name());
                        tv_order_id.setText(order_id + "");
                        tv_order_time.setText(order.getCreatedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() + "~耐心等待");
                        price.setText("￥" + order.getTotalPrice());
                        lv_order_food.setAdapter(new OrderDetailsAdapter(order.getCartFoods(), OrderDetailsActivity.this));
                        setListViewHeightBasedOnChildren(lv_order_food);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //返回按键
            case R.id.iv_back:
                Intent intent = new Intent(OrderDetailsActivity.this, HistoricalOrdersActivity.class);
                startActivity(intent);
                finish();
                break;
            //再来一单
            case R.id.btn_again_order:

                break;
            //评论
            case R.id.btn_to_comment:

                break;
        }
    }
}
