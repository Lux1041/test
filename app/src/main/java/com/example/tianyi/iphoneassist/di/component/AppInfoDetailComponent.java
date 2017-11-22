package com.example.tianyi.iphoneassist.di.component;

import com.example.tianyi.iphoneassist.di.FragmentScope;
import com.example.tianyi.iphoneassist.di.module.AppInfoDetailModule;
import com.example.tianyi.iphoneassist.ui.fragment.AppInfoFragment;

import dagger.Component;

/**
 * Created by Tianyi on 2017/11/19.
 */

@FragmentScope
@Component(modules = AppInfoDetailModule.class, dependencies = AppComponent.class)
public interface AppInfoDetailComponent {

    void inject(AppInfoFragment appInfoFragment);

}
