package com.example.tianyi.iphoneassist.di.component;

import com.example.tianyi.iphoneassist.di.FragmentScope;
import com.example.tianyi.iphoneassist.di.module.DownLoadModule;
import com.example.tianyi.iphoneassist.ui.fragment.DownLoadFragment;

import dagger.Component;

/**
 * Created by Tianyi on 2017/11/13.
 */

@FragmentScope
@Component(modules = DownLoadModule.class, dependencies = AppComponent.class)
public interface DownLoadComponent {

    void inject(DownLoadFragment fragment);

}
