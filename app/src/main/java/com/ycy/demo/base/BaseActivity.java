package com.ycy.demo.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import com.ycy.demo.widget.LoadingView;

/**
 * Created by YCY.
 */
public abstract class BaseActivity extends FragmentActivity implements IBaseView {
    private LoadingView loadingView = new LoadingView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initArgs();
        initView();
    }

    protected void initArgs() {
    }

    protected abstract void initView();

    @Override
    protected void onResume() {
        super.onResume();
        //统计
    }

    @Override
    protected void onPause() {
        super.onPause();
        //统计
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void showProgress() {
        loadingView.showLoading(getRootView());
    }

    @Override
    public void hideProgress() {
        loadingView.hideLoading();
    }

    @Override
    public void showToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_LONG).show();
    }

    protected abstract View getRootView();

}
