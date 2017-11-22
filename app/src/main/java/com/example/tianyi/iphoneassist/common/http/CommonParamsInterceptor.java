package com.example.tianyi.iphoneassist.common.http;

import android.content.Context;

import com.example.tianyi.iphoneassist.common.Constant;
import com.example.tianyi.iphoneassist.common.util.ACache;
import com.example.tianyi.iphoneassist.common.util.DensityUtil;
import com.example.tianyi.iphoneassist.common.util.DeviceUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Created by Tianyi on 2017/11/17.
 */

public class CommonParamsInterceptor implements Interceptor {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private Context mContext;
    private Gson mGson;

    public CommonParamsInterceptor(Context context, Gson gson){
        mContext = context;
        mGson = gson;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        //公共参数
        HashMap<String, Object> commomParamsMap = new HashMap<>();
        commomParamsMap.put(Constant.IMEI, DeviceUtils.getIMEI(mContext));
        commomParamsMap.put(Constant.MODEL, DeviceUtils.getModel());
        commomParamsMap.put(Constant.LANGUAGE,DeviceUtils.getLanguage());
        commomParamsMap.put(Constant.os,DeviceUtils.getBuildVersionIncremental());
        commomParamsMap.put(Constant.RESOLUTION, DensityUtil.getScreenW(mContext)+"*" + DensityUtil.getScreenH(mContext));
        commomParamsMap.put(Constant.SDK,DeviceUtils.getBuildVersionSDK()+"");
        commomParamsMap.put(Constant.DENSITY_SCALE_FACTOR,mContext.getResources().getDisplayMetrics().density+"");

        commomParamsMap.put(Constant.TOKEN, ACache.get(mContext).getAsString(Constant.TOKEN));


        Request request = chain.request();
        String method = request.method();
        if (method.equals("GET")){

            HttpUrl httpUrl = request.url();

            Set<String> oldParamsSet = httpUrl.queryParameterNames();
            HashMap<String, Object> rootMap = new HashMap<>();
            if (oldParamsSet != null){
                for (String key : oldParamsSet){
                    if (key.equals(Constant.PARAM)){
                        String oldParams = httpUrl.queryParameter(key);
                        HashMap<String, Object> p = mGson.fromJson(oldParams, HashMap.class);
                        if (p != null){
                            for (Map.Entry<String, Object> entry : p.entrySet()){
                                rootMap.put(entry.getKey(), entry.getValue());
                            }
                        }
                    }else{
                        rootMap.put(key, httpUrl.queryParameter(key));
                    }
                }
            }

            rootMap.put("publicParams", commomParamsMap);
            String newParamsStr = mGson.toJson(rootMap);

            String url = httpUrl.toString();
            int index = url.indexOf("?");
            if (index > 0){
                url = url.substring(0, index);
            }

            url = url + "?" + Constant.PARAM + "=" + newParamsStr;

            request = request.newBuilder().url(url).build();

        }else if (method.equals("POST")){
            RequestBody body = request.body();

            HashMap<String, Object> rootMap = new HashMap<>();

            if (body instanceof FormBody){
                for (int i = 0 ; i < ((FormBody) body).size(); i++){
                    rootMap.put(((FormBody) body).encodedName(i), ((FormBody) body).encodedValue(i));
                }
            }else{

                Buffer buffer = new Buffer();

                body.writeTo(buffer);

                String oldJsonParams = buffer.readUtf8();
                rootMap = mGson.fromJson(oldJsonParams, HashMap.class);
                rootMap.put("publicParams", commomParamsMap);
                String newJsonParams = mGson.toJson(rootMap);

                request = request.newBuilder().post(RequestBody.create(JSON, newJsonParams)).build();
            }
        }

        return chain.proceed(request);
    }
}
