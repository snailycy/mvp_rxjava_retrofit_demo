package com.ycy.demo.base;

import android.text.TextUtils;

import com.ycy.demo.callback.RequestCallback;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscription;

/**
 * Created by YCY.
 */
public class BasePresenter<V extends IBaseView, D> implements RequestCallback<D> {
    protected Subscription mSubscription;
    protected V mView;

    public BasePresenter(V mView) {
        this.mView = mView;
    }

    public void onDestroy() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
        mView = null;
    }

    @Override
    public void onBefore() {
        mView.showProgress();
    }

    @Override
    public void onCompleted() {
        mView.hideProgress();
    }

    @Override
    public void onSuccess(D data) {
        mView.hideProgress();
    }

    /**
     * onError统一在这里处理
     */
    @Override
    public void onError(Throwable throwable) {
        if (throwable instanceof HttpException) {
            try {
                ResponseBody responseBody = ((HttpException) throwable).response().errorBody();
                String errorbody = responseBody.string();
                if (!TextUtils.isEmpty(errorbody)) {
                    // TODO: 这里处理放在errorbody中的错误信息
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
