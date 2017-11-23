package com.example.tianyi.iphoneassist.common.rx;

import com.example.tianyi.iphoneassist.bean.BaseBean;
import com.example.tianyi.iphoneassist.common.exception.ApiException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Tianyi on 2017/11/16.
 */

public class RxHttpResponseCompose {

    public static <T> ObservableTransformer<BaseBean<T>, T> compatResult(){
        return new ObservableTransformer<BaseBean<T>, T>() {
            @Override
            public ObservableSource<T> apply(io.reactivex.Observable<BaseBean<T>> baseBeanObservable) {
                return baseBeanObservable.flatMap(new Function<BaseBean<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(@NonNull final BaseBean<T> tBaseBean) throws Exception {
                        if (tBaseBean.success()){
                            return Observable.create(new ObservableOnSubscribe<T>() {
                                @Override
                                public void subscribe(ObservableEmitter<T> e) throws Exception {
                                    e.onNext(tBaseBean.getData());
                                    e.onComplete();
                                }
                            });
                        }else{
                            return Observable.error(new ApiException(tBaseBean.getStatus(), tBaseBean.getMessage()));
                        }
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

   /* public static <T> Observable.Transformer<BaseBean<T>, T> compatResult(){
        return new Observable.Transformer<BaseBean<T>, T>() {
            @Override
            public Observable<T> call(Observable<BaseBean<T>> baseBeanObservable) {

                return baseBeanObservable.flatMap(new Func1<BaseBean<T>, Observable<T>>() {

                    @Override
                    public Observable<T> call(final BaseBean<T> tBaseBean) {

                        if (tBaseBean.success()){
                            return Observable.create(new Observable.OnSubscribe<T>() {
                                @Override
                                public void call(Subscriber<? super T> subscriber) {
                                    try {
                                        subscriber.onNext(tBaseBean.getData());
                                        subscriber.onCompleted();
                                    }catch(Exception e){
                                        subscriber.onError(e);
                                    }
                                }
                            });
                        }else{
                            return Observable.error(new ApiException(tBaseBean.getStatus(), tBaseBean.getMessage()));
                        }
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }*/
}
