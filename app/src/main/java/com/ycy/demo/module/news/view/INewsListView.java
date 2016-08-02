package com.ycy.demo.module.news.view;

import com.ycy.demo.base.IBaseView;
import com.ycy.demo.bean.News;

import java.util.List;

/**
 * Created by YCY.
 */
public interface INewsListView extends IBaseView {
    void dismissRefreshView();

    void updateNewsList(List<News> newsList);
}
