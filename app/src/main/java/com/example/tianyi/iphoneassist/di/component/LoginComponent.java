package com.example.tianyi.iphoneassist.di.component;

import com.example.tianyi.iphoneassist.di.FragmentScope;
import com.example.tianyi.iphoneassist.di.module.LoginModule;
import com.example.tianyi.iphoneassist.ui.activity.LoginActivity;

import dagger.Component;

/**
 * Created by Tianyi on 2017/11/20.
 */

@FragmentScope
@Component(modules = LoginModule.class, dependencies = AppComponent.class)
public interface LoginComponent {
    void inject(LoginActivity loginActivity);
}
