package com.example.order_client.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.order_client.R;
import com.example.order_client.adapter.CartAdapter;
import com.example.order_client.adapter.FoodInfoAdapter;
import com.example.order_client.adapter.FoodStyleAdapter;
import com.example.order_client.po.CartFood;
import com.example.order_client.po.CartShop;
import com.example.order_client.po.FoodInfo;
import com.example.order_client.po.FoodStyle;
import com.example.order_client.po.Order;
import com.example.order_client.po.ShopInfo;
import com.example.order_client.utils.JsonParse;
import com.example.order_client.utils.RandomNumUtil;
import com.example.order_client.utils.SaveUserInfoUtil;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.image.SmartImageView;

import org.apache.http.Header;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String FOOD_STYLES = "SHOP_INFO_SHOP_ID";
    private static final int MSG_COUNT_OK = 1;
    private RelativeLayout main_tile;
    private ImageView iv_back;
    private TextView tv_title;
    private LinearLayout loading;
    private ListView lv_food_info;
    private ListView lv_food_style;
    private List<FoodInfo> foodInfos;
    private List<FoodStyle> foodStyles;
    private int shop_id;
    private TextView tv_detail_shop_name;
    private SmartImageView iv_shop_pic;
    private TextView tv_shop_time;
    private TextView tv_all_food_pay;
    private TextView tv_all_food_money;
    private TextView tv_shop_cost;
    private TextView all_food_num;
    private ImageView iv_shop_cart_icon;
    private TextView tv_shop_addr;
    private RadioGroup menu_group;
    private RadioButton rbtn_menu;
    private RadioButton rbtn_comment;
    private FoodInfoAdapter adapter;
    private int num;
    private RelativeLayout cart_list;
    private List<FoodInfo> cartList;
    private double totalPrice;
    private ListView lv_cart;
    private TextView clean_cart;
    private Bundle bundle;
    private MHandler mHandler;
    private CartAdapter cartAdapter;
    private Gson gson;
    private ShopInfo shopInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        shop_id = getIntent().getExtras().getInt(FOOD_STYLES);//?????????????????????id
        shopInfo = (ShopInfo) getIntent().getSerializableExtra("shopInfo");//??????????????????
        mHandler = new MHandler();
        num = 0;//???????????????????????????
        totalPrice = 0;//?????????????????????????????????
        cartList = new ArrayList<>();//??????????????????????????????
        initView();//?????????????????????UI??????
        fillShopDetailData();//??????????????????????????????
        fillFoodStyleData();//?????????????????????????????????????????????????????????
        initAdapter();//???????????????????????????
    }

    /**
     * ??????UI??????
     */
    private void initView() {
        gson = new Gson();
        //?????????????????????UI??????
        tv_all_food_pay = (TextView) findViewById(R.id.tv_all_food_pay);//???????????????
        tv_all_food_pay.setOnClickListener(this);
        tv_all_food_money = (TextView) findViewById(R.id.tv_all_food_money);//??????????????????
        tv_shop_cost = (TextView) findViewById(R.id.tv_shop_cost);//??????????????????
        iv_shop_cart_icon = (ImageView) findViewById(R.id.iv_shop_cart_icon);//???????????????
        iv_shop_cart_icon.setOnClickListener(this);
        all_food_num = (TextView) findViewById(R.id.all_food_num);//???????????????????????????
        cart_list = (RelativeLayout) findViewById(R.id.cart_list);//?????????????????????
        lv_cart = (ListView) findViewById(R.id.lv_cart);
        //???????????????
        clean_cart = (TextView) findViewById(R.id.clear_cart);
        clean_cart.setOnClickListener(this);
        //???????????????????????????
        all_food_num.setVisibility(View.GONE);
        tv_shop_cost.setVisibility(View.GONE);
        tv_all_food_pay.setVisibility(View.GONE);
        //????????????????????????UI??????
        tv_detail_shop_name = (TextView) findViewById(R.id.tv_detail_shop_name);
        iv_shop_pic = (SmartImageView) findViewById(R.id.iv_shop_pic);
        tv_shop_time = (TextView) findViewById(R.id.tv_shop_time);
        tv_shop_addr = (TextView) findViewById(R.id.tv_shop_addr);
        //????????????????????????UI??????
        loading = (LinearLayout) findViewById(R.id.menu_loading);
        lv_food_style = (ListView) findViewById(R.id.lv_food_style);
        lv_food_info = (ListView) findViewById(R.id.lv_food_info);
        //??????????????????UI??????
        main_tile = (RelativeLayout) findViewById(R.id.main_tile);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("????????????");
        //????????????
        menu_group = (RadioGroup) findViewById(R.id.menu_group);
        rbtn_menu = (RadioButton) findViewById(R.id.rbtn_menu);
        rbtn_comment = (RadioButton) findViewById(R.id.rbtn_comment);
        rbtn_menu.setOnClickListener(this);
        rbtn_comment.setOnClickListener(this);
        rbtn_menu.setChecked(true);
        menu_group.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                //????????????????????????
                case R.id.rbtn_menu:
                    rbtn_menu.setChecked(true);
                    break;
                //??????????????????
                case R.id.rbtn_comment:
                    rbtn_comment.setChecked(true);
                    break;
            }
        });
        //???????????????????????????????????????????????????
        cart_list.setOnTouchListener((v, event) -> {
            if (cart_list.getVisibility() == View.VISIBLE) {
                cart_list.setVisibility(View.INVISIBLE);
            }
            return false;
        });
    }

    /**
     * ????????????
     */
    private void fillShopDetailData() {
        String shop_detail_url = getString(R.string.server_url) + getString(R.string.shop_detail) + "?shop_id=" + shop_id;
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(shop_detail_url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                try {
                    String json = new String(bytes, "utf-8");
                    ShopInfo shopInfo = JsonParse.getShopDetail(json);
                    Thread.sleep(1000);
                    loading.setVisibility(View.INVISIBLE);
                    tv_detail_shop_name.setText(shopInfo.getShop_name());
                    tv_shop_time.setText(shopInfo.getShop_time());
                    tv_shop_addr.setText(shopInfo.getShop_address());
                    iv_shop_pic.setImageUrl(getString(R.string.server_image_url) + shopInfo.getShop_icon());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(MenuActivity.this, "???????????????????????????...", Toast.LENGTH_LONG).show();
            }
        });
    }


    /**
     * ???????????????????????????????????????????????????
     */
    private void fillFoodStyleData() {
        String food_style_url = getString(R.string.server_url) + getString(R.string.food_style) + "?shop_id=" + shop_id;
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(food_style_url, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                try {
                    String json = new String(bytes, "utf-8");
                    foodStyles = JsonParse.getFoodStyle(json);
                    lv_food_style.setAdapter(new FoodStyleAdapter(foodStyles, MenuActivity.this));
                    //??????lv_food_style????????????
                    lv_food_style.setOnItemClickListener((parent, view, position, id) -> {
                        //??????????????????
                        for (int j = 0; j < parent.getChildCount(); j++) {
                            View child = parent.getChildAt(j);
                            if (position == j) {
                                child.setBackgroundColor(Color.WHITE);
                            } else {
                                child.setBackgroundColor(Color.TRANSPARENT);
                            }
                        }
                        //???????????????????????????????????????
                        FoodStyle foodStyle = (FoodStyle) lv_food_style.getItemAtPosition(position);
                        String food_info_url = getString(R.string.server_url) + getString(R.string.food_info) + "?food_style_name=" + foodStyle.getFood_style_name();
                        AsyncHttpClient food_client = new AsyncHttpClient();
                        food_client.get(food_info_url, new AsyncHttpResponseHandler() {
                            @Override
                            public void onSuccess(int i1, Header[] headers1, byte[] bytes1) {
                                try {
                                    String json1 = new String(bytes1, "utf-8");
                                    foodInfos = JsonParse.getFoodInfos(json1);
                                    adapter.setData(foodInfos);
                                    lv_food_info.setAdapter(adapter);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(int i1, Header[] headers1, byte[] bytes1, Throwable throwable) {
                                Toast.makeText(MenuActivity.this, "???????????????????????????...", Toast.LENGTH_LONG).show();
                            }
                        });
                    });
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

    /**
     * ???????????????????????????
     */
    private void initAdapter() {
        //?????????????????????
        adapter = new FoodInfoAdapter(foodInfos, this, new FoodInfoAdapter.OnSelectListener() {
            @Override
            public void onSelectAdd(int position, TextView tv_food_num, ImageView iv_reduce) {
                FoodInfo foodInfo = foodInfos.get(position);
                foodInfo.setFood_num(foodInfo.getFood_num() + 1);
                //???????????????????????????????????????????????????cartList???????????????
                Iterator<FoodInfo> iterator = cartList.iterator();
                while (iterator.hasNext()) {
                    FoodInfo food = iterator.next();
                    if (food.getFood_id() == foodInfo.getFood_id()) {
                        iterator.remove();
                    }
                }
                num++;
                totalPrice += foodInfo.getFood_price();
                cartList.add(foodInfo);
                //??????????????????????????????????????????Handler????????????????????????
                cartDataMag();
            }

            @Override
            public void onSelectMin(int position, TextView tv_food_num, ImageView iv_reduce) {

            }
        });
        //??????????????????
        cartAdapter = new CartAdapter(foodInfos, this, new CartAdapter.OnSelectListener() {
            @Override
            public void onSelectAdd(int position, TextView tv_food_price, TextView tv_food_num) {
                FoodInfo foodInfo = cartList.get(position);//????????????????????????
                tv_food_num.setText(foodInfo.getFood_num() + 1 + "");//???????????????????????????????????????
                foodInfo.setFood_num(foodInfo.getFood_num() + 1);//?????????????????????????????????????????????????????????
                double count = foodInfo.getFood_price() * foodInfo.getFood_num(); //????????????????????????
                tv_food_price.setText("???" + count);//????????????????????????
                Iterator<FoodInfo> iterator = cartList.iterator();
                while (iterator.hasNext()) {//???????????????????????????
                    FoodInfo food = iterator.next();
                    if (food.getFood_id() == foodInfo.getFood_id()) {//??????????????????
                        iterator.remove();//??????????????????????????????????????????
                    }
                }
                cartList.add(position, foodInfo);//????????????????????????????????????????????????????????????
                num++;//???????????????????????????
                totalPrice += foodInfo.getFood_price();//???????????????=????????????+??????????????????
                cartAdapter.notifyDataSetChanged();
                cartDataMag();
            }

            @Override
            public void onSelectReduce(int position, TextView tv_food_price, TextView tv_food_num) {
                FoodInfo foodInfo = cartList.get(position);
                tv_food_num.setText(foodInfo.getFood_num() - 1 + "");
                double count = foodInfo.getFood_price() * (foodInfo.getFood_num() - 1);
                tv_food_price.setText(count + "");
                cartAdapter.notifyDataSetChanged();
                deleteCartData(foodInfo, position);
            }
        });
    }

    /**
     * ?????????????????????
     */
    private void cartDataMag() {
        Message msg = Message.obtain();
        msg.what = MSG_COUNT_OK;
        Bundle bundle = new Bundle();
        bundle.putString("totalCount", num + "");
        bundle.putString("totalPrice", totalPrice + "");
        msg.setData(bundle);
        mHandler.sendMessage(msg);
    }

    /**
     * ???????????????????????????
     */
    private void deleteCartData(FoodInfo foodInfo, int position) {
        int count = foodInfo.getFood_num() - 1;
        foodInfo.setFood_num(count);
        Iterator<FoodInfo> iterator = cartList.iterator();
        while (iterator.hasNext()) {
            FoodInfo food = iterator.next();
            if (food.getFood_id() == foodInfo.getFood_id()) {
                iterator.remove();
            }
        }
        //??????????????????????????????1????????????0??????????????????????????????????????????????????????
        if (count > 0) cartList.add(position, foodInfo);
        else {

        }
        num--;//????????????????????????????????????
        //????????????????????????-??????????????????
        totalPrice -= foodInfo.getFood_price();
        cartDataMag();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //????????????????????????
            case R.id.iv_back:
                Intent intent = new Intent(MenuActivity.this, ShopsActivity.class);
                startActivity(intent);
                finish();
                break;
            //????????????????????????
            case R.id.rbtn_menu:
                rbtn_menu.setChecked(true);
                break;
            //??????????????????
            case R.id.rbtn_comment:
                rbtn_comment.setChecked(true);
                break;
            //???????????????
            case R.id.iv_shop_cart_icon:
                if (num == 0) return;
                else {
                    if (cart_list.getVisibility() == View.VISIBLE) {
                        cart_list.setVisibility(View.INVISIBLE);
                    } else {
                        cart_list.setVisibility(View.VISIBLE);
                        Animation animation = AnimationUtils.loadAnimation(MenuActivity.this, R.anim.slide_cart_bottom_to_top);
                        cart_list.setAnimation(animation);
                    }
                    cartAdapter.setData(cartList);
                    lv_cart.setAdapter(cartAdapter);
                }
                break;
            //???????????????
            case R.id.clear_cart:
                if (cartList == null) return;
                for (FoodInfo foodInfo : cartList) {
                    foodInfo.setFood_num(0);
                }
                cartList.clear();
                cartAdapter.notifyDataSetChanged();
                num = 0;
                totalPrice = 0;
                cartDataMag();
                break;
            //??????
            case R.id.tv_all_food_pay:
                //?????????????????????
                saveOrderInfo();
                Intent intent1 = new Intent(MenuActivity.this, OrderActivity.class);
                intent1.putExtra("cartList", (Serializable) cartList);
                startActivity(intent1);
                finish();
                break;
        }
    }

    /**
     * ??????????????????
     */
    private void saveOrderInfo() {
        Map<String, String> userInfoS = SaveUserInfoUtil.getUserInfo(this);
        String username = userInfoS.get("username");
        String url = getString(R.string.server_url) + getString(R.string.order_info);
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        Order order = new Order();
        order.setOrderId(RandomNumUtil.Num());
        order.setUserName(username);
        List<CartFood> cartFoods = fillCartFood();
        order.setCartFoods(cartFoods);
        order.setTotalPrice(totalPrice);
        //???????????????????????????
        CartShop cartShop = new CartShop();
        cartShop.setShop_id(shopInfo.getShop_id());
        cartShop.setShop_icon(shopInfo.getShop_icon());
        cartShop.setShop_name(shopInfo.getShop_name());
        order.setCartShop(cartShop);
        params.put("order", gson.toJson(order));
        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                Toast.makeText(MenuActivity.this, "?????????????????????????????????...", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(MenuActivity.this, "???????????????...", Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * ???????????????????????????????????????
     */
    private List<CartFood> fillCartFood() {
        List<CartFood> cartFoods = new ArrayList<>();
        for (FoodInfo foodInfo : cartList) {
            CartFood cartFood = new CartFood();
            cartFood.setFood_id(foodInfo.getFood_id());
            cartFood.setFood_icon(foodInfo.getFood_icon());
            cartFood.setFood_name(foodInfo.getFood_name());
            cartFood.setFood_num(foodInfo.getFood_num());
            cartFood.setTotalPrice(foodInfo.getFood_price() * foodInfo.getFood_num());
            cartFoods.add(cartFood);
        }
        return cartFoods;
    }

    private class MHandler extends Handler {
        @Override
        public void dispatchMessage(Message msg) {
            switch (msg.what) {
                case MSG_COUNT_OK:
                    Bundle bundle = msg.getData();
                    String totalCount = bundle.getString("totalCount", "");
                    String totalPrice = bundle.getString("totalPrice", "");
                    if (bundle != null) {
                        if (Integer.parseInt(totalCount) > 0) {
                            iv_shop_cart_icon.setImageResource(R.drawable.cart_has);
                            tv_all_food_pay.setVisibility(View.VISIBLE);
                            all_food_num.setVisibility(View.VISIBLE);
                            all_food_num.setText(String.valueOf(num));
                            tv_all_food_money.setText("??? " + totalPrice);
                        } else {
                            if (cart_list.getVisibility() == View.VISIBLE) {
                                cart_list.setVisibility(View.GONE);
                            }
                            iv_shop_cart_icon.setImageResource(R.drawable.cart);
                            tv_all_food_pay.setVisibility(View.INVISIBLE);
                            all_food_num.setVisibility(View.INVISIBLE);
                            tv_all_food_money.setText("???????????????");
                        }
                    }
            }
        }
    }
}
