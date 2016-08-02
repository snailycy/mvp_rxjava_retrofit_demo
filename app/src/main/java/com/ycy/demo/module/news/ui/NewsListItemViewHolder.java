package com.ycy.demo.module.news.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ycy.demo.R;

/**
 * Created by YCY.
 */
public class NewsListItemViewHolder extends RecyclerView.ViewHolder {
    public TextView tvNewsTitle, tvNewsDesc;

    public NewsListItemViewHolder(View itemView) {
        super(itemView);
        tvNewsTitle = (TextView) itemView.findViewById(R.id.tv_news_title);
        tvNewsDesc = (TextView) itemView.findViewById(R.id.tv_news_desc);
    }
}
