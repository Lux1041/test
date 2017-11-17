package com.example.tianyi.iphoneassist.di.module;

import android.app.ProgressDialog;

import com.example.tianyi.iphoneassist.http.ApiServer;
import com.example.tianyi.iphoneassist.presenter.contact.DownLoadContact;
import com.example.tianyi.iphoneassist.ui.fragment.DownLoadFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Tianyi on 2017/11/13.
 */

@Module
public class DownLoadModule {

    private DownLoadContact.View mView;
    public DownLoadModule(DownLoadContact.View view){
        this.mView = view;
    }

    @Provides
    public com.example.tianyi.iphoneassist.data.DownLoadModule provideDownLoadModule(ApiServer apiServer){
        return new com.example.tianyi.iphoneassist.data.DownLoadModule(apiServer);
    }

    @Provides
    public DownLoadContact.View provideView(){
        return mView;
    }

    @Provides
    public ProgressDialog provideProgressDislog(DownLoadContact.View view){
        return new ProgressDialog(((DownLoadFragment)view).getActivity());
    }


}
