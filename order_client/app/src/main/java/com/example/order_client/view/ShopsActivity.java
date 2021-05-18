package com.example.order_client.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.order_client.R;
import com.example.order_client.adapter.ShopsAdapter;
import com.example.order_client.po.ShopInfo;
import com.example.order_client.utils.JsonParse;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.io.Serializable;
import java.util.List;

public class ShopsActivity extends AppCompatActivity implements View.OnClickListener {
    private RadioGroup radioGroup;
    private RadioButton rb_home;
    private RadioButton rb_order;
    private RadioButton rb_take;
    private RadioButton rb_myInfo;
    private RelativeLayout main_tile;
    private ImageView iv_back;
    private TextView tv_title;
    private LinearLayout loading;
    private ListView lv_shops;
    private List<ShopInfo> shopInfos;
    private ProgressBar progressBar;
    private RadioButton rb_takeout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);
        initView();
        fillData();
    }

    //获取店铺信息
    private void fillData() {
        String url = getString(R.string.server_url) + getString(R.string.shop_info);
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                try {
                    String json = new String(bytes, "utf-8");
                    shopInfos = JsonParse.getShopInfo(json);
                    if (shopInfos == null) {
                        Toast.makeText(ShopsActivity.this, "网络异常。。。", Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            Thread.sleep(2000);
                            loading.setVisibility(View.INVISIBLE);
                            lv_shops.setAdapter(new ShopsAdapter(shopInfos, ShopsActivity.this));
                            //设置listview的点击事件
                            lv_shops.setOnItemClickListener((parent, view, position, id) -> {
                                ShopInfo shopInfo = (ShopInfo) parent.getItemAtPosition(position);
                                Intent intent = new Intent(ShopsActivity.this, MenuActivity.class);
                                intent.putExtra("shopInfo",shopInfo);
                                intent.putExtra(MenuActivity.FOOD_STYLES, shopInfo.getShop_id());
                                startActivity(intent);
                            });
                        } catch (Exception e) {
                            return;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                loading.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initView() {

        //获取点餐选择方式以及网络加载UI控件
        rb_takeout = (RadioButton) findViewById(R.id.rbtn_takeout);
        rb_takeout.setOnClickListener(this);
        loading = (LinearLayout) findViewById(R.id.loading);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //获取店铺列表UI控件
        lv_shops = (ListView) findViewById(R.id.lv_shops);
        //获取顶部导航UI控件
        main_tile = (RelativeLayout) findViewById(R.id.main_tile);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("点餐");
        //让返回图标隐藏
        iv_back.setVisibility(View.GONE);
        //获取底部导航UI控件
        radioGroup = (RadioGroup) findViewById(R.id.main_footer);
        rb_home = (RadioButton) findViewById(R.id.rb_home);
        rb_order = (RadioButton) findViewById(R.id.rb_order);
        rb_take = (RadioButton) findViewById(R.id.rb_take);
        rb_myInfo = (RadioButton) findViewById(R.id.rb_myInfo);
        rb_order.setChecked(true);
        //RadioGroup选中状态改变监听事件
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_home:
                    rb_home.setChecked(true);
                    tv_title.setText("首页");
                    main_tile.setVisibility(View.VISIBLE);
                    Intent intent0 = new Intent(ShopsActivity.this, HomeActivity.class);
                    startActivity(intent0);
                    ShopsActivity.this.finish();
                    overridePendingTransition(0, 0);
                    break;
                case R.id.rb_order:
                    rb_order.setChecked(true);
                    tv_title.setText("点餐");
                    main_tile.setVisibility(View.VISIBLE);
                    Intent intent1 = new Intent(ShopsActivity.this, ShopsActivity.class);
                    startActivity(intent1);
                    ShopsActivity.this.finish();
                    overridePendingTransition(0, 0);
                    break;
                case R.id.rb_take:
                    rb_take.setChecked(true);
                    tv_title.setText("取餐");
                    main_tile.setVisibility(View.VISIBLE);
                    Intent intent2 = new Intent(ShopsActivity.this, OrderActivity.class);
                    startActivity(intent2);
                    ShopsActivity.this.finish();
                    overridePendingTransition(0, 0);
                    break;
                case R.id.rb_myInfo:
                    rb_myInfo.setChecked(true);
                    main_tile.setVisibility(View.GONE);
                    Intent intent3 = new Intent(ShopsActivity.this, MyInfoActivity.class);
                    startActivity(intent3);
                    ShopsActivity.this.finish();
                    overridePendingTransition(0, 0);
                    break;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //外卖进入设置地址
            case R.id.rbtn_takeout:

                break;
        }
    }
}
