package com.example.order_client.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.order_client.R;

public class ViewPagerIndicator extends LinearLayout {

    private int mCount; //小圆点总个数
    private int mIndex;//小圆点当前位置
    private Context context;

    public ViewPagerIndicator(Context context) {
        this(context, null);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        setGravity(Gravity.CENTER);
        this.context=context;
    }

    public void setCurrentPosition(int currentIndex) {
        mIndex = currentIndex;
        removeAllViews();
        int pex = 5;// 内边距
        for (int i = 0; i < mCount; i++) {
            ImageView imageView = new ImageView(context);
            if (mIndex==i){
                //白色为选中小圆点
                imageView.setImageResource(R.drawable.indicator_on);
            }else {
                imageView.setImageResource(R.drawable.indicator_off);
            }
            imageView.setPadding(pex,0,pex,0);
            addView(imageView);
        }
    }
    public void setmCount(int count){
        this.mCount=count;
    }
}
