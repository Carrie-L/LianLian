package com.carrie.lianlian.activity;


import android.util.Log;

import com.carrie.lianlian.utils.LogUtil;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by Carrie on 2017/8/7.
 */

public class RxJavaTest  {
    private static final String TAG="RxJavaTest";

    public void testRxJava(){
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                LogUtil.i(TAG, "onSubscribe: " );
            }

            @Override
            public void onNext(@NonNull String s) {
                LogUtil.i(TAG, "onNext: " + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                LogUtil.i(TAG, "onError " );
            }

            @Override
            public void onComplete() {
                LogUtil.i(TAG, "onComplete " );
            }
        };

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        };

        Observable observable = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(@NonNull ObservableEmitter e) throws Exception {

            }
        });

    }


}
