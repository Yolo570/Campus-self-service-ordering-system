package com.example.order_client.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.order_client.R;
import com.example.order_client.fragment.OrderHas;
import com.example.order_client.fragment.OrderNone;
import com.example.order_client.po.FoodInfo;

import java.util.List;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener {
    private RadioGroup radioGroup;
    private RadioButton rb_home;
    private RadioButton rb_order;
    private RadioButton rb_take;
    private RadioButton rb_myInfo;
    private RelativeLayout main_tile;
    private ImageView iv_back;
    private TextView tv_title;
    private List<FoodInfo> cartList;
    private FrameLayout body;
    private RelativeLayout rl_main_bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        //获取购物车菜品的数据
        cartList = (List<FoodInfo>) getIntent().getSerializableExtra("cartList");
        initView();
        fillData();
    }

    private void fillData() {
        if (cartList != null) {
            OrderHas orderHas = new OrderHas(this);
            body.addView(orderHas.createView());
            orderHas.showView();
            rl_main_bottom.setVisibility(View.GONE);
        }
        if (cartList == null) {
            OrderNone orderNone = new OrderNone(this);
            body.addView(orderNone.createView());
            orderNone.showView();
        }
    }

    private void initView() {
        body = (FrameLayout) findViewById(R.id.order_body);
        //获取顶部导航UI控件
        main_tile = (RelativeLayout) findViewById(R.id.main_tile);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("取餐");
        //让返回图标隐藏
        iv_back.setVisibility(View.GONE);
        //获取底部导航UI控件
        rl_main_bottom = (RelativeLayout) findViewById(R.id.main_bottom);
        radioGroup = (RadioGroup) findViewById(R.id.main_footer);
        rb_home = (RadioButton) findViewById(R.id.rb_home);
        rb_order = (RadioButton) findViewById(R.id.rb_order);
        rb_take = (RadioButton) findViewById(R.id.rb_take);
        rb_myInfo = (RadioButton) findViewById(R.id.rb_myInfo);
        rb_take.setChecked(true);
        //RadioGroup选中状态改变监听事件
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_home:
                    rb_home.setChecked(true);
                    tv_title.setText("首页");
                    main_tile.setVisibility(View.VISIBLE);
                    Intent intent0 = new Intent(OrderActivity.this, HomeActivity.class);
                    startActivity(intent0);
                    OrderActivity.this.finish();
                    overridePendingTransition(0, 0);
                    break;
                case R.id.rb_order:
                    rb_order.setChecked(true);
                    tv_title.setText("点餐");
                    main_tile.setVisibility(View.VISIBLE);
                    Intent intent1 = new Intent(OrderActivity.this, ShopsActivity.class);
                    startActivity(intent1);
                    OrderActivity.this.finish();
                    overridePendingTransition(0, 0);
                    break;
                case R.id.rb_take:
                    rb_take.setChecked(true);
                    tv_title.setText("取餐");
                    main_tile.setVisibility(View.VISIBLE);
                    Intent intent2 = new Intent(OrderActivity.this, OrderActivity.class);
                    startActivity(intent2);
                    OrderActivity.this.finish();
                    overridePendingTransition(0, 0);
                    break;
                case R.id.rb_myInfo:
                    rb_myInfo.setChecked(true);
                    main_tile.setVisibility(View.GONE);
                    Intent intent3 = new Intent(OrderActivity.this, MyInfoActivity.class);
                    startActivity(intent3);
                    OrderActivity.this.finish();
                    overridePendingTransition(0, 0);
                    break;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_order_return:
                Intent intent = new Intent(OrderActivity.this, ShopsActivity.class);
                startActivity(intent);
                OrderActivity.this.finish();
                overridePendingTransition(0, 0);
                break;
        }
    }
}
