package com.example.tianyi.iphoneassist.common.util;

import android.content.Context;

import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.Observable;


/**
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date
 */

public class PermissionUtil {





    public static Observable<Boolean> requestPermisson(Context activity, String permission){


        RxPermissions rxPermissions =  RxPermissions.getInstance(activity);


        return rxPermissions.request(permission);
    }





}
