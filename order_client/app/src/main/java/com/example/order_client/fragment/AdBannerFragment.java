package com.example.order_client.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.order_client.R;


public class AdBannerFragment extends Fragment {

    private String ad;
    private ImageView iv;

    public static AdBannerFragment getInstance(Bundle args) {
        AdBannerFragment af = new AdBannerFragment();
        af.setArguments(args);
        return af;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        //获取广告栏图片名称
        ad = args.getString("ad");

    }

    @Override
    public void onResume() {
        super.onResume();
        if (ad != null) {
            if ("banner1".equals(ad)) {
                iv.setImageResource(R.drawable.banner1);
            } else if ("banner2".equals(ad)) {
                iv.setImageResource(R.drawable.banner2);
            } else if ("banner3".equals(ad)) {
                iv.setImageResource(R.drawable.banner3);
            } else if ("banner4".equals(ad)) {
                iv.setImageResource(R.drawable.banner4);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (iv != null) {
            iv.setImageDrawable(null);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //创建广告图片空间
        iv = new ImageView(getActivity());
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
        iv.setLayoutParams(lp);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        return iv;
    }
}
