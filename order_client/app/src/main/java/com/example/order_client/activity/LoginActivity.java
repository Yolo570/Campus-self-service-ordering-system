package com.example.order_client.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.order_client.R;
import com.example.order_client.po.UserInfo;
import com.example.order_client.utils.AlgorithmUtil;
import com.example.order_client.utils.JsonParse;
import com.example.order_client.utils.SaveUserInfoUtil;
import com.example.order_client.view.HomeActivity;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private EditText edit_username;
    private EditText edit_password;
    private Button btn_login;
    private Button btn_register;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //判断是否已经有保存成功的用户名和密码
        Map<String, String> userInfo = SaveUserInfoUtil.getUserInfo(this);
        if (userInfo.get("username").isEmpty() || userInfo.get("password").isEmpty()) {
            initView();
            setOnClickListener();
        } else {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            intent.putExtra("username", userInfo.get("username"));
            startActivity(intent);
            LoginActivity.this.finish();
        }
    }

    private void setOnClickListener() {
        //用户登录
        btn_login.setOnClickListener(v -> {
            username = edit_username.getText().toString().trim();
            password = edit_password.getText().toString().trim();
            /**
             * 1.判断用户名和密码是否为空
             * 2.判断用户名和密码是否正确
             * 3.向服务端发送http请求获取用户信息
             * 4.若用户存在，则登录进入主页
             * 5.若用户不存在则自动跳转到注册界面进行用户注册
             */
            //1.判断用户名和密码是否为空
            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                Toast.makeText(LoginActivity.this, "用户名或密码为空...", Toast.LENGTH_LONG).show();
                return;
                // 2.判断用户名和密码是否正确
            } else {
                String url = getString(R.string.server_url) + getString(R.string.user_login);
                RequestParams params = new RequestParams();
                params.put("username", username);
                String pwd_md5 = AlgorithmUtil.md5(password);
                params.put("password", pwd_md5);
                final AsyncHttpClient client = new AsyncHttpClient();
                client.post(url, params, new AsyncHttpResponseHandler() {

                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        try {
                            //判断是否成功
                            String json = new String(bytes, "utf-8");
                            UserInfo userInfo = JsonParse.getUserInfo(json);
                            if (userInfo==null) {
                                Toast.makeText(LoginActivity.this, "用户名或密码错误...", Toast.LENGTH_SHORT).show();
                            }else {
                                //存储
                                boolean isSaveSuccess = SaveUserInfoUtil.saveUserInfo(LoginActivity.this, username, pwd_md5);
                                if (isSaveSuccess) {
                                    Intent intent = new Intent();
                                    intent.setClass(LoginActivity.this, HomeActivity.class);
                                    intent.putExtra("username", username);
                                    startActivity(intent);
                                    LoginActivity.this.finish();
                                } else {
                                    Toast.makeText(LoginActivity.this, "网络异常!!!请重新尝试...", Toast.LENGTH_SHORT).show();
                                }
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        Toast.makeText(LoginActivity.this, "网络异常!!!请重新尝试...", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        //用户注册
        btn_register.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

    }

    private void initView() {
        //登录界面
        edit_username = (EditText) findViewById(R.id.edit_username);
        edit_password = (EditText) findViewById(R.id.edit_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_register = (Button) findViewById(R.id.btn_register);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
