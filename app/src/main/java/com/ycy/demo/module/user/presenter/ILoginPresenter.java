package com.ycy.demo.module.user.presenter;

/**
 * Created by YCY.
 */
public interface ILoginPresenter {
    void doUserLogin(String userName,String password);

    void onDestroy();
}
