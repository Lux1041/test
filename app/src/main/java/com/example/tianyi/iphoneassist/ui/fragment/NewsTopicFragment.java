package com.example.tianyi.iphoneassist.ui.fragment;

import android.graphics.Typeface;
import android.widget.TextView;

import com.example.tianyi.iphoneassist.R;
import com.example.tianyi.iphoneassist.di.component.AppComponent;

import butterknife.BindView;

/**
 * Created by Tianyi on 2017/11/13.
 */

public class NewsTopicFragment extends BaseFragment {
    @BindView(R.id.download_textview2)
    TextView tv2;
    @Override
    public int setContentView() {
        return R.layout.fragment_newstopic;
    }

    @Override
    public void initView() {
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "iconfont.ttf");
        tv2.setTypeface(typeface);
    }

    @Override
    public void setupAppComponent(AppComponent appComponent) {

    }
}
