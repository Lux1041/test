package com.example.tianyi.iphoneassist.di.component;

import com.example.tianyi.iphoneassist.di.FragmentScope;
import com.example.tianyi.iphoneassist.di.module.AppInfoModule;
import com.example.tianyi.iphoneassist.ui.fragment.HistoryFragment;

import dagger.Component;

/**
 * Created by Tianyi on 2017/11/19.
 */

@FragmentScope
@Component(modules = AppInfoModule.class, dependencies = AppComponent.class)
public interface AppInfoComponent {

    void inject(HistoryFragment historyFragment);

}
