package com.ycy.demo.module.user.presenter;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.ycy.demo.base.BasePresenter;
import com.ycy.demo.bean.HttpReqUser;
import com.ycy.demo.module.news.ui.NewsListListActivity;
import com.ycy.demo.module.user.model.LoginInteractor;
import com.ycy.demo.module.user.view.ILoginView;

/**
 * Created by YCY.
 */
public class LoginPresenter extends BasePresenter<ILoginView, String> implements ILoginPresenter {

    private LoginInteractor iLoginInteractor;

    public LoginPresenter(ILoginView mView) {
        super(mView);
        iLoginInteractor = new LoginInteractor();
    }

    @Override
    public void doUserLogin(String userName, String password) {
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            mView.showToast("请输入用户名和密码");
            return;
        }
        HttpReqUser httpReqUser = new HttpReqUser(userName, password);
        mSubscription = iLoginInteractor.requestUserLogin(this, httpReqUser);
    }

    @Override
    public void onSuccess(String data) {
        super.onSuccess(data);
        //TODO:存储token信息

        //跳转到NewsListActivity
        Activity activity = mView.getActivity();
        activity.startActivity(new Intent(activity, NewsListListActivity.class));
        activity.finish();
    }


}
