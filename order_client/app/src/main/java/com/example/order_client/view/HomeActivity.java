package com.example.order_client.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.order_client.R;
import com.example.order_client.activity.HistoricalOrdersActivity;
import com.example.order_client.adapter.AdBannerAdapter;
import com.example.order_client.po.Banner;
import com.example.order_client.utils.SaveUserInfoUtil;

import java.util.ArrayList;
import java.util.Map;

public class HomeActivity extends AppCompatActivity implements View.OnTouchListener {
    public static final int MSG_AD_SLID = 003;
    private ViewPager adPager;
    private AdBannerAdapter ada;
    private Handler mHandler;
    private ViewPagerIndicator vpi;
    private RelativeLayout adBannerLay;
    private ArrayList<Banner> ad;
    private RadioGroup radioGroup;
    private RadioButton rb_home;
    private RadioButton rb_order;
    private RadioButton rb_take;
    private RadioButton rb_myInfo;
    private RelativeLayout main_tile;
    private ImageView iv_back;
    private TextView tv_title;
    private Button btn_to_orders;
    private Button btn_toOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mHandler = new MyHandler();
        initAdData();
        initView();
        new AdAutoSlidThread().start();
    }

    private void initView() {
        //获取顶部导航UI控件
        main_tile = (RelativeLayout) findViewById(R.id.main_tile);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("首页");
        //让返回图标隐藏
        iv_back.setVisibility(View.GONE);
        //获取Button控件
        btn_toOrder = (Button) findViewById(R.id.btn_toOrder);
        btn_to_orders = (Button) findViewById(R.id.btn_to_orders);
        btn_to_orders.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, HistoricalOrdersActivity.class);
            startActivity(intent);
            finish();
        });
        btn_toOrder.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ShopsActivity.class);
            startActivity(intent);
            finish();
        });
        //获取底部导航UI控件
        radioGroup = (RadioGroup) findViewById(R.id.main_footer);
        rb_home = (RadioButton) findViewById(R.id.rb_home);
        rb_order = (RadioButton) findViewById(R.id.rb_order);
        rb_take = (RadioButton) findViewById(R.id.rb_take);
        rb_myInfo = (RadioButton) findViewById(R.id.rb_myInfo);
        rb_home.setChecked(true);
        //RadioGroup选中状态改变监听事件
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        rb_home.setChecked(true);
                        tv_title.setText("首页");
                        main_tile.setVisibility(View.VISIBLE);
                        Intent intent0 = new Intent(HomeActivity.this, HomeActivity.class);
                        startActivity(intent0);
                        HomeActivity.this.finish();
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.rb_order:
                        rb_order.setChecked(true);
                        tv_title.setText("点餐");
                        main_tile.setVisibility(View.VISIBLE);
                        Intent intent1 = new Intent(HomeActivity.this, ShopsActivity.class);
                        startActivity(intent1);
                        HomeActivity.this.finish();
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.rb_take:
                        rb_take.setChecked(true);
                        tv_title.setText("取餐");
                        main_tile.setVisibility(View.VISIBLE);
                        Intent intent2 = new Intent(HomeActivity.this, OrderActivity.class);
                        startActivity(intent2);
                        HomeActivity.this.finish();
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.rb_myInfo:
                        rb_myInfo.setChecked(true);
                        main_tile.setVisibility(View.GONE);
                        Intent intent3 = new Intent(HomeActivity.this, MyInfoActivity.class);
                        startActivity(intent3);
                        HomeActivity.this.finish();
                        overridePendingTransition(0, 0);
                        break;
                }
            }
        });
        //获取广告栏UI控件
        adPager = (ViewPager) findViewById(R.id.vp_adBanner);
        adPager.setLongClickable(false);
        ada = new AdBannerAdapter(this.getSupportFragmentManager(), mHandler);
        adPager.setAdapter(ada);
        adPager.setOnTouchListener(ada);
        vpi = findViewById(R.id.vpi_advert_indicator);
        vpi.setmCount(ada.getSize());
        adBannerLay = findViewById(R.id.rl_banner);
        adPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (ada.getSize() > 0) {
                    vpi.setCurrentPosition(position % ada.getSize());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        resetSize();
        if (ad != null) {
            if (ad.size() > 0) {
                vpi.setmCount(ad.size());
                vpi.setCurrentPosition(0);
            }
            ada.setDatas(ad);
        }
    }

    /**
     * 计算控件大小
     */
    private void resetSize() {
        int sw = getScreenWidth(this);
        int adLheight = sw / 2;
        ViewGroup.LayoutParams adlp = adBannerLay.getLayoutParams();
        adlp.width = sw;
        adlp.height = adLheight;
        adBannerLay.setLayoutParams(adlp);

    }

    /**
     * 读取屏幕宽度
     */
    private int getScreenWidth(Activity context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Display display = context.getWindowManager().getDefaultDisplay();
        display.getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    /**
     * 初始化广告中的数据
     */
    private void initAdData() {
        ad = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Banner bean = new Banner();
            bean.id = (i + 1);
            switch (i) {
                case 0:
                    bean.icon = "banner1";
                    break;
                case 1:
                    bean.icon = "banner2";
                    break;
                case 2:
                    bean.icon = "banner3";
                    break;
                case 3:
                    bean.icon = "banner4";
                    break;
                default:
                    break;
            }
            ad.add(bean);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    private class AdAutoSlidThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (true) {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (mHandler != null) {
                    mHandler.sendEmptyMessage(MSG_AD_SLID);
                }
            }
        }
    }

    private class MyHandler extends Handler {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what) {
                case MSG_AD_SLID:
                    if (ada.getCount() > 0) {
                        adPager.setCurrentItem(adPager.getCurrentItem() + 1);
                    }
                    break;
            }
        }
    }
}
