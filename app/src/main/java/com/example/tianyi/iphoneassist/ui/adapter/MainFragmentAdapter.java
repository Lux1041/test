package com.example.tianyi.iphoneassist.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.tianyi.iphoneassist.ui.fragment.DownLoadFragment;
import com.example.tianyi.iphoneassist.ui.fragment.HistoryFragment;
import com.example.tianyi.iphoneassist.ui.fragment.JokeFragment;
import com.example.tianyi.iphoneassist.ui.fragment.NewsTopicFragment;

/**
 * Created by Tianyi on 2017/11/13.
 */

public class MainFragmentAdapter extends FragmentStatePagerAdapter {

    static CharSequence[] titles = {"下载", "历史", "笑话", "新闻"};

    public MainFragmentAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new DownLoadFragment();
                break;
            case 1:
                fragment = new HistoryFragment();
                break;
            case 2:
                fragment = new JokeFragment();
                break;
            case 3:
                fragment = new NewsTopicFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return titles[position];
    }
}
