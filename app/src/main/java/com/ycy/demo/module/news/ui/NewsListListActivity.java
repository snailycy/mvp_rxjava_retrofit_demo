package com.ycy.demo.module.news.ui;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ycy.demo.R;
import com.ycy.demo.base.BaseActivity;
import com.ycy.demo.base.BaseListAdapter;
import com.ycy.demo.bean.News;
import com.ycy.demo.module.news.presenter.NewsListPresenter;
import com.ycy.demo.module.news.view.INewsListView;

import java.util.ArrayList;
import java.util.List;

/**
 * https://github.com/snailycy/mvp_rxjava_retrofit_demo
 */
public class NewsListListActivity extends BaseActivity implements INewsListView, SwipeRefreshLayout.OnRefreshListener {

    private NewsListPresenter iNewsListPresenter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<News> newsList = new ArrayList<>();
    private NewsListAdapter newsListAdapter;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_news_list);
        initRefreshLayout();
        initRecyclerView();

        iNewsListPresenter = new NewsListPresenter(this);
        iNewsListPresenter.requestNewsList(true);
    }

    private void initRefreshLayout() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_red_light, android.R.color.holo_orange_light,
                android.R.color.holo_green_light, android.R.color.holo_blue_light);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void initRecyclerView() {
        RecyclerView rvList = (RecyclerView) findViewById(R.id.rv_list);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvList.setLayoutManager(layoutManager);
        newsListAdapter = new NewsListAdapter(this, newsList);
        rvList.setAdapter(newsListAdapter);
        //监听列表滑动
        rvList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && !newsListAdapter.isFooterShowing()) {
                    //列表滑动过并且footer没有显示，则显示出footer
                    int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                    if (lastVisibleItemPosition == newsListAdapter.getItemCount() - 1)
                        newsListAdapter.showFooter();
                }
            }
        });
        //监听item点击
        newsListAdapter.setOnItemClickListener(new BaseListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                showToast("点击了：" + position);
            }
        });
        //监听加载更多
        newsListAdapter.setOnLoadMoreListener(new BaseListAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                iNewsListPresenter.requestNewsList(false);
            }
        });
    }

    @Override
    protected View getRootView() {
        return findViewById(R.id.root);
    }

    @Override
    public void onRefresh() {
        iNewsListPresenter.requestNewsList(true);
    }

    @Override
    public void dismissRefreshView() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void updateNewsList(List<News> newsList) {
        this.newsList.clear();
        this.newsList.addAll(newsList);
        newsListAdapter.notifyDataSetChanged();
        dismissRefreshView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iNewsListPresenter.onDestroy();
    }
}
