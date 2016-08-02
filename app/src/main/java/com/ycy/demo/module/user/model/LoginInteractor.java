package com.ycy.demo.module.user.model;

import com.google.gson.Gson;
import com.ycy.demo.bean.HttpReqUser;
import com.ycy.demo.bean.HttpRspUser;
import com.ycy.demo.callback.RequestCallback;
import com.ycy.demo.common.Constant;
import com.ycy.demo.http.manager.RetrofitManager;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by YCY.
 */
public class LoginInteractor implements ILoginInteractor {
    @Override
    public Subscription requestUserLogin(final RequestCallback<String> callback, final HttpReqUser httpReqUser) {
        return RetrofitManager.getInstance().getUserLoginObservable(httpReqUser)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        // 订阅之前回调回去显示加载动画
                        callback.onBefore();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpRspUser>() {
                    @Override
                    public void onCompleted() {
                        callback.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);
                        //TODO：TEST，需要用API返回数据时，请删掉一下测试代码
                        onNext(getTestData());
                    }

                    @Override
                    public void onNext(HttpRspUser httpRspUser) {
                        if (httpRspUser != null) {
                            String status = httpRspUser.getStatus();
                            if (Constant.STATUS_OK.equals(status)) {
                                callback.onSuccess(httpRspUser.getToken());
                            }
                        } else {
                            // TODO
                        }
                    }
                });
    }

    //测试数据
    private HttpRspUser getTestData() {
        String testData = "{\"status\":\"ok\",\"token\":\"asdfjlk-qwrqtoi-zvncmnm\"}";
        return new Gson().fromJson(testData, HttpRspUser.class);
    }
}
