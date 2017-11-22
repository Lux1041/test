package com.example.tianyi.iphoneassist.common.imageloader;

import android.graphics.Bitmap;

/**
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date
 */

public interface BitmapLoadingListener {

    void onSuccess(Bitmap b);

    void onError();
}
