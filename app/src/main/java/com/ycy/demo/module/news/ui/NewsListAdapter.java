package com.ycy.demo.module.news.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ycy.demo.R;
import com.ycy.demo.base.BaseListAdapter;
import com.ycy.demo.bean.News;

import java.util.List;

/**
 * Created by YCY.
 */
public class NewsListAdapter extends BaseListAdapter<News> {

    public NewsListAdapter(Context context, List<News> newsList) {
        super(context, newsList);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder getItemView(ViewGroup parent) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_news_list, parent, false);
        final NewsListItemViewHolder itemViewHolder = new NewsListItemViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(itemViewHolder.getAdapterPosition());
                }
            }
        });
        return itemViewHolder;
    }

    @Override
    protected void bindItemViewHolder(RecyclerView.ViewHolder holder, int position, News news) {
        if (holder instanceof NewsListItemViewHolder) {
            NewsListItemViewHolder itemViewHolder = (NewsListItemViewHolder) holder;
            itemViewHolder.tvNewsTitle.setText(news.getTitle());
            itemViewHolder.tvNewsDesc.setText(news.getDesc());
        }
    }
}
