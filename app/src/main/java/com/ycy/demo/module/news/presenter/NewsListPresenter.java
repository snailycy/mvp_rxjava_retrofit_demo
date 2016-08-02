package com.ycy.demo.module.news.presenter;

import com.ycy.demo.base.BasePresenter;
import com.ycy.demo.bean.News;
import com.ycy.demo.module.news.model.NewsListInteractor;
import com.ycy.demo.module.news.view.INewsListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YCY.
 */
public class NewsListPresenter extends BasePresenter<INewsListView, List<News>> implements INewsListPresenter {

    private static final int pageSize = 15;
    private int page = 0;
    private boolean isRefresh;
    private NewsListInteractor iNewsListInteractor;
    private List<News> newsList = new ArrayList<>();

    public NewsListPresenter(INewsListView mView) {
        super(mView);
        iNewsListInteractor = new NewsListInteractor();
    }

    @Override
    public void requestNewsList(boolean isRefresh) {
        this.isRefresh = isRefresh;
        if (isRefresh) {
            page = 0;
        }
        mSubscription = iNewsListInteractor.requestNewsList(this, page, pageSize);
    }

    @Override
    public void onSuccess(List<News> data) {
        super.onSuccess(data);
        mView.dismissRefreshView();
        if (data == null || data.size() == 0) return;
        page++;
        if (isRefresh) this.newsList.clear();
        this.newsList.addAll(data);
        mView.updateNewsList(this.newsList);
    }
}
