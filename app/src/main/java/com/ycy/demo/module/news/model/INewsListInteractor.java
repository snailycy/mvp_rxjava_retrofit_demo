package com.ycy.demo.module.news.model;

import com.ycy.demo.bean.News;
import com.ycy.demo.callback.RequestCallback;

import java.util.List;

import rx.Subscription;

/**
 * Created by YCY.
 */
public interface INewsListInteractor {
    Subscription requestNewsList(RequestCallback<List<News>> callback, int page, int pageSize);
}
