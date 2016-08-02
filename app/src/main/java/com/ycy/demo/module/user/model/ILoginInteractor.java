package com.ycy.demo.module.user.model;

import com.ycy.demo.bean.HttpReqUser;
import com.ycy.demo.callback.RequestCallback;

import rx.Subscription;

/**
 * Created by YCY.
 */
public interface ILoginInteractor {
    Subscription requestUserLogin(RequestCallback<String> callback, HttpReqUser httpReqUser);
}
