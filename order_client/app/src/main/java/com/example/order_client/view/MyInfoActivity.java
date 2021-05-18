package com.example.order_client.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.order_client.R;
import com.example.order_client.activity.HistoricalOrdersActivity;
import com.example.order_client.activity.LoginActivity;
import com.example.order_client.activity.SettingActivity;
import com.example.order_client.po.UserInfo;
import com.example.order_client.utils.JsonParse;
import com.example.order_client.utils.SaveUserInfoUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.util.Map;

public class MyInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private RadioGroup radioGroup;
    private RadioButton rb_home;
    private RadioButton rb_order;
    private RadioButton rb_take;
    private RadioButton rb_myInfo;
    private RelativeLayout main_tile;
    private ImageView iv_back;
    private TextView tv_title;
    private LinearLayout ll_head;
    private ImageView iv_user_icon;
    private TextView nickname;
    private String username;
    private UserInfo userInfo;
    private RelativeLayout rl_setting;
    private TextView tv_setting;
    private TextView logout;
    private TextView tv_historical_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        initView();
        fillData();
    }

    /**
     * 获取用户个人信息
     */
    private void fillData() {
        Map<String, String> userInfoS = SaveUserInfoUtil.getUserInfo(this);
        username = userInfoS.get("username");
        String url = getString(R.string.server_url) + getString(R.string.user_info);
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("username", username);
        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                try {
                    String json = new String(bytes, "utf-8");
                    userInfo = JsonParse.getUserInfo(json);
//                    nickname.setText(userInfo.getUser_nick());
                    if (userInfo.getUser_nick() == null) {
                        nickname.setText(userInfo.getUser_name());
                    } else nickname.setText(userInfo.getUser_nick());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(MyInfoActivity.this, "错误,请检查网络", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        //获取用户UI控件
        ll_head = (LinearLayout) findViewById(R.id.ll_head);
        iv_user_icon = (ImageView) findViewById(R.id.iv_user_icon);
        nickname = (TextView) findViewById(R.id.tv_nickname);
        //查看历史订单
        tv_historical_order = (TextView) findViewById(R.id.tv_historical_order);
        tv_historical_order.setOnClickListener(this);
        //用户信息设置
        rl_setting = (RelativeLayout) findViewById(R.id.rl_setting);
        tv_setting = (TextView) findViewById(R.id.tv_setting);
        rl_setting.setOnClickListener(this);
        tv_setting.setOnClickListener(this);
        //退出登录
        logout = (TextView) findViewById(R.id.btn_logout);
        logout.setOnClickListener(this);
        //获取顶部导航UI控件
        main_tile = (RelativeLayout) findViewById(R.id.main_tile);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        main_tile.setVisibility(View.GONE);
        //获取底部导航UI控件
        radioGroup = (RadioGroup) findViewById(R.id.main_footer);
        rb_home = (RadioButton) findViewById(R.id.rb_home);
        rb_order = (RadioButton) findViewById(R.id.rb_order);
        rb_take = (RadioButton) findViewById(R.id.rb_take);
        rb_myInfo = (RadioButton) findViewById(R.id.rb_myInfo);
        rb_myInfo.setChecked(true);
        //RadioGroup选中状态改变监听事件
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        rb_home.setChecked(true);
                        tv_title.setText("首页");
                        main_tile.setVisibility(View.VISIBLE);
                        Intent intent0 = new Intent(MyInfoActivity.this, HomeActivity.class);
                        startActivity(intent0);
                        MyInfoActivity.this.finish();
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.rb_order:
                        rb_order.setChecked(true);
                        tv_title.setText("点餐");
                        main_tile.setVisibility(View.VISIBLE);
                        Intent intent1 = new Intent(MyInfoActivity.this, ShopsActivity.class);
                        startActivity(intent1);
                        MyInfoActivity.this.finish();
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.rb_take:
                        rb_take.setChecked(true);
                        tv_title.setText("取餐");
                        main_tile.setVisibility(View.VISIBLE);
                        Intent intent2 = new Intent(MyInfoActivity.this, OrderActivity.class);
                        startActivity(intent2);
                        MyInfoActivity.this.finish();
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.rb_myInfo:
                        rb_myInfo.setChecked(true);
                        main_tile.setVisibility(View.GONE);
                        Intent intent3 = new Intent(MyInfoActivity.this, MyInfoActivity.class);
                        startActivity(intent3);
                        MyInfoActivity.this.finish();
                        overridePendingTransition(0, 0);
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //查看历史订单
            case R.id.tv_historical_order:
                Intent intent = new Intent(this, HistoricalOrdersActivity.class);
                startActivity(intent);
                break;
            //用户设置
            case R.id.tv_setting:
                Intent intent0 = new Intent(MyInfoActivity.this, SettingActivity.class);
                startActivity(intent0);
                break;
            //退出登录
            case R.id.btn_logout:
                SharedPreferences sp = getSharedPreferences("data", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.commit();
                Intent intent1 = new Intent(MyInfoActivity.this, LoginActivity.class);
                startActivity(intent1);
                finish();
                break;
        }
    }
}
