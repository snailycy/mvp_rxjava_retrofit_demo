package com.ycy.demo.base;

import android.app.Activity;

/**
 * Created by YCY.
 */
public interface IBaseView {
    Activity getActivity();

    void showProgress();

    void hideProgress();

    void showToast(String content);

}
