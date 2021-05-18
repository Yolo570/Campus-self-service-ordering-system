package com.example.order_client.adapter;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.order_client.fragment.AdBannerFragment;
import com.example.order_client.po.Banner;
import com.example.order_client.view.HomeActivity;

import java.util.ArrayList;
import java.util.List;

public class AdBannerAdapter extends FragmentStatePagerAdapter implements View.OnTouchListener {

    private List<Banner> bb;
    private Handler mHandler;
    public AdBannerAdapter(FragmentManager fm) {
        super(fm);
        bb = new ArrayList<>();
    } public AdBannerAdapter(FragmentManager fm, Handler handler) {
        super(fm);
        bb = new ArrayList<>();
        mHandler =handler;
    }

    /**
     * 设置数据，更新界面
     *
     * @param bb
     */
    public void setDatas(List<Banner> bb) {
        this.bb = bb;
        notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        //防止刷新结果显示列表时出现缓存数据，重载此方法，使之默认返回POSITION_NONE
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle args = new Bundle();
        if (bb.size() > 0) {
            args.putString("ad", bb.get(position % bb.size()).icon);
        }
        return AdBannerFragment.getInstance(args);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    public int getSize() {
        return bb == null ? 0 : bb.size();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        mHandler.removeMessages(HomeActivity.MSG_AD_SLID);
        return false;
    }
}
