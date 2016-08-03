package com.ycy.demo.module.news.presenter;

/**
 * Created by YCY.
 */
public interface INewsListPresenter {
    void requestNewsList(boolean isRefresh);

    void onDestroy();
}
