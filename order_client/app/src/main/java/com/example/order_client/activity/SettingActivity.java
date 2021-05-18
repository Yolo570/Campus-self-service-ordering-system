package com.example.order_client.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.order_client.R;
import com.example.order_client.po.UserInfo;
import com.example.order_client.utils.JsonParse;
import com.example.order_client.utils.SaveUserInfoUtil;
import com.example.order_client.view.MyInfoActivity;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.util.Map;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout main_tile;
    private ImageView iv_back;
    private TextView tv_title;
    private EditText setting_nickname;
    private EditText setting_sex;
    private EditText setting_address;
    private EditText setting_tel;
    private Button btn_save;
    private EditText setting_age;
    private String username;
    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
        fillData();
    }

    private void fillData() {
        String url = getString(R.string.server_url) + getString(R.string.user_info);
        Map<String, String> userInfoS = SaveUserInfoUtil.getUserInfo(this);
        username = userInfoS.get("username");
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("username", username);
        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                try {
                    String json = new String(bytes, "utf-8");
                    userInfo = JsonParse.getUserInfo(json);
                    setting_nickname.setText(userInfo.getUser_nick());
                    setting_sex.setText(userInfo.getUser_gender());
                    setting_age.setText(userInfo.getUser_age());
                    setting_address.setText(userInfo.getUser_addr());
                    setting_tel.setText(userInfo.getUser_tel());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(SettingActivity.this, "错误,请检查网络", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        //获取顶部导航UI控件
        main_tile = (RelativeLayout) findViewById(R.id.main_tile);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("用户设置");
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, MyInfoActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //用户信息
        setting_nickname = (EditText) findViewById(R.id.setting_nickname);
        setting_sex = (EditText) findViewById(R.id.setting_sex);
        setting_tel = (EditText) findViewById(R.id.setting_tel);
        setting_address = (EditText) findViewById(R.id.setting_address);
        setting_age = (EditText) findViewById(R.id.setting_age);

        //保存用户信息
        btn_save = (Button) findViewById(R.id.btn_save);
        //设置点击事件
        btn_save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                //修改后的用户信息
                String rest_nickname = setting_nickname.getText().toString().trim();
                String rest_sex = setting_sex.getText().toString().trim();
                String rest_tel = setting_tel.getText().toString().trim();
                String rest_address = setting_address.getText().toString().trim();
                String rest_age = setting_age.getText().toString().trim();
                String url = getString(R.string.server_url) + getString(R.string.rest_info);
                RequestParams params = new RequestParams();
                params.put("username", username);
                params.put("nickname", rest_nickname);
                params.put("gender", rest_sex);
                params.put("phone", rest_tel);
                params.put("age", rest_age);
                params.put("addr", rest_address);
                final AsyncHttpClient client = new AsyncHttpClient();
                client.post(url, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        try {
                            Toast.makeText(SettingActivity.this, "修改成功...", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SettingActivity.this, MyInfoActivity.class);
                            startActivity(intent);
                            finish();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        Toast.makeText(SettingActivity.this, "网络异常...", Toast.LENGTH_SHORT).show();
                    }
                });
//                SaveUserInfo();
                break;
        }
    }

    private void SaveUserInfo() {


    }


}

