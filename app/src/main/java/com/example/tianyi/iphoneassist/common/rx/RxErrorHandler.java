package com.example.tianyi.iphoneassist.common.rx;

import android.content.Context;
import android.widget.Toast;

import com.example.tianyi.iphoneassist.common.exception.ApiException;
import com.example.tianyi.iphoneassist.common.exception.BaseException;
import com.example.tianyi.iphoneassist.common.exception.ErrorMessageFactory;
import com.google.gson.JsonParseException;

import java.net.SocketException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by Tianyi on 2017/11/16.
 */

public class RxErrorHandler {

    private Context mContext;
    public RxErrorHandler(Context context){
        mContext = context;
    }


    public BaseException handlerError(Throwable e) {
        BaseException exception = new BaseException();

        if(e instanceof ApiException){

            exception.setCode(((ApiException)e).getCode());

        }
        else if (e instanceof JsonParseException){
            exception.setCode(BaseException.JSON_ERROR);
        }
        else  if(e instanceof HttpException){

            exception.setCode(((HttpException)e).code());
        }
        else  if(e instanceof SocketTimeoutException){

            exception.setCode(BaseException.SOCKET_TIMEOUT_ERROR);
        }
        else if(e instanceof SocketException){


        }
        else {

            exception.setCode(BaseException.UNKNOWN_ERROR);

        }

        exception.setDisplayMessage(ErrorMessageFactory.create(mContext,exception.getCode()));

        return  exception;
    }


    public void showError(BaseException e){
        Toast.makeText(mContext, e.getDisplayMessage(), Toast.LENGTH_SHORT).show();
    }

}
