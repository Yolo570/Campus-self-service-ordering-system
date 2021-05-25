package com.example.order_client.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.order_client.R;
import com.example.order_client.po.UserInfo;
import com.example.order_client.utils.AlgorithmUtil;
import com.example.order_client.utils.JsonParse;
import com.example.order_client.utils.SaveUserInfoUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnTouchListener {

    private ImageView tv_back;
    private Button btn_register;
    private Drawable drawable1, drawable2;
    private LinearLayout ll_body;
    private boolean isOpen1;
    private boolean isOpen2;
    private Toast toast;
    private TextView tv_title;
    private RelativeLayout main_tile;
    private EditText reg_username;
    private EditText reg_password;
    private EditText reg_password_again;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        setOnClickListener();
        setInitalState();
    }

    /**
     * 获取注册界面UI控件
     */
    private void initView() {
        //顶部导航UI控件
        tv_back = (ImageView) findViewById(R.id.iv_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        main_tile = (RelativeLayout) findViewById(R.id.main_tile);
        tv_title.setText("用户注册");
        //注册界面UI控件
        reg_username = (EditText) findViewById(R.id.reg_username);
        reg_password = (EditText) findViewById(R.id.reg_password);
        reg_password_again = (EditText) findViewById(R.id.reg_password_again);
        btn_register = (Button) findViewById(R.id.btn_register);
        ll_body = (LinearLayout) findViewById(R.id.register_body);
    }

    /**
     * 对注册界面每个UI控件进行监听
     */
    private void setOnClickListener() {
        //返回控件监听
        tv_back.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
        //注册控件监听
        btn_register.setOnClickListener(v -> {
            String username = reg_username.getText().toString().trim();
            String pwd = reg_password.getText().toString().trim();
            String pwd_again = reg_password_again.getText().toString().trim();
            if ("".equals(username)) {
                Toast.makeText(RegisterActivity.this, "您还没有输入用户名...", Toast.LENGTH_SHORT).show();
                return;
            } else if (!AlgorithmUtil.RegisterByUserName(username)) {
                Toast.makeText(RegisterActivity.this, "用户名长度在8~12之间且前三个要求字母...", Toast.LENGTH_SHORT).show();
                return;
            } else if (TextUtils.isEmpty(pwd)) {
                Toast.makeText(RegisterActivity.this, "您还没有输入密码...", Toast.LENGTH_SHORT).show();
                return;
            } else if (!AlgorithmUtil.RegisterByPwd(pwd)) {
                Toast.makeText(RegisterActivity.this, "密码长度过短，应在在6~11之间...", Toast.LENGTH_SHORT).show();
                return;
            } else if (TextUtils.isEmpty(pwd_again)) {
                Toast.makeText(RegisterActivity.this, "请您再次确认密码...", Toast.LENGTH_SHORT).show();
                return;
            } else if (!pwd.equals(pwd_again)) {
                Toast.makeText(RegisterActivity.this, "两次密码输入不同...", Toast.LENGTH_SHORT).show();
                return;
            } else {
                String url = getString(R.string.server_url) + getString(R.string.user_register);
                RequestParams params = new RequestParams();
                String pwd_md5 = AlgorithmUtil.md5(pwd);
                params.put("username", username);
                params.put("password", pwd_md5);
                final AsyncHttpClient client = new AsyncHttpClient();
                client.post(url, params, new AsyncHttpResponseHandler() {

                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        try {
                            //判断是否成功
                            String json = new String(bytes, "utf-8");
                            UserInfo userInfo = JsonParse.getUserInfo(json);
                            if (userInfo == null) {
                                Toast.makeText(RegisterActivity.this, "该用户名已存在，请重新注册...", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(RegisterActivity.this, "注册成功，请登录...", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        Toast.makeText(RegisterActivity.this, "网络异常请重新尝试...", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        //设置每个密码框右边不可见图片的监听器
        for (int i = 0; i < ll_body.getChildCount(); i++) {
            ll_body.getChildAt(i).setOnTouchListener(this);
        }
    }


    /**
     * 设置密码的可见性和不可见性
     */
    private void setInitalState() {
        //设置密码的输入模式,即不可见
        reg_password.setInputType(InputType.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
        reg_password_again.setInputType(InputType.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
        //获取EditText右边的不可见图标
        drawable1 = reg_password.getCompoundDrawables()[2];
        drawable2 = reg_password_again.getCompoundDrawables()[2];
        for (int i = 0; i < ll_body.getChildCount(); i++) {
            ll_body.getChildAt(i).setSelected(false);
        }
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (view.getId()) {
            case R.id.reg_password:
                if (motionEvent.getAction() == MotionEvent.ACTION_UP && drawable1 != null) {
                    int x = (int) motionEvent.getX();
                    int drawable_x = reg_password.getWidth() - reg_password.getPaddingRight() - drawable1.getIntrinsicWidth();
                    if (x > drawable_x && x < reg_password.getWidth()) {
                        //获取文本光标
                        int pos = reg_password.getSelectionStart();
                        //更改密码不可见图标
                        onDrawableClick(1);
                        //将文本光标恢复
                        reg_password.setSelection(pos);
                    }
                }
                break;
            case R.id.reg_password_again:
                if (motionEvent.getAction() == MotionEvent.ACTION_UP && drawable2 != null) {
                    int x = (int) motionEvent.getX();
                    int drawable_x = reg_password_again.getWidth() - reg_password_again.getPaddingRight() - drawable2.getIntrinsicWidth();
                    if (x > drawable_x && x < reg_password_again.getWidth()) {
                        //获取文本光标
                        int pos = reg_password_again.getSelectionStart();
                        //更改密码不可见图标
                        onDrawableClick(2);
                        //将文本光标恢复
                        reg_password_again.setSelection(pos);
                    }
                }
                break;
        }
        return false;
    }

    private void onDrawableClick(int index) {
        switch (index) {
            case 1:
                if (isOpen1) {
                    reg_password.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.password, 0, R.mipmap.password_invisible, 0);
                    reg_password.setInputType(InputType.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    reg_password.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.password, 0, R.mipmap.password_visible, 0);
                    reg_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                isOpen1 = !isOpen1;
                break;
            case 2:
                if (isOpen2) {
                    reg_password_again.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.password, 0, R.mipmap.password_again_invisible, 0);
                    reg_password_again.setInputType(InputType.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    reg_password_again.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.password, 0, R.mipmap.password_again_visible, 0);
                    reg_password_again.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                isOpen2 = !isOpen2;
                break;
        }
    }
}
