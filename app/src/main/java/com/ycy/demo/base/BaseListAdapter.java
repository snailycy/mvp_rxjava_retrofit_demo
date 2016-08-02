package com.ycy.demo.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ycy.demo.R;

import java.util.List;

public abstract class BaseListAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_ITEM = 1;
    public static final int TYPE_FOOTER = 2;

    protected Context context;
    protected LayoutInflater inflater;
    protected List<T> mTList;
    protected boolean showFooter;
    protected OnItemClickListener onItemClickListener;
    protected OnLoadMoreListener onLoadMoreListener;

    public BaseListAdapter(Context context, List<T> tList) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        mTList = tList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {//列表item
            return getItemView(parent);
        } else if (viewType == TYPE_FOOTER) {//尾
            View itemView = this.inflater.inflate(R.layout.load_more_view, parent, false);
            return new ProgressbarViewholder(itemView);
        }
        return null;
    }

    @NonNull
    public abstract RecyclerView.ViewHolder getItemView(ViewGroup parent);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_ITEM) {
            bindItemViewHolder(holder, position, mTList.get(position));
        } else if (getItemViewType(position) == TYPE_FOOTER) {
            ProgressbarViewholder progressbarViewholder = (ProgressbarViewholder) holder;
            if (showFooter) {
                progressbarViewholder.loadMoreView.setVisibility(View.VISIBLE);
                if (onLoadMoreListener != null)
                    onLoadMoreListener.onLoadMore();
            } else {
                progressbarViewholder.loadMoreView.setVisibility(View.INVISIBLE);
            }
        }
    }

    protected abstract void bindItemViewHolder(RecyclerView.ViewHolder holder, int position, T t);

    @Override
    public int getItemCount() {
        int num = showFooter ? 1 : 0;
        return mTList != null ? (mTList.size() + num) : 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (showFooter && position == mTList.size()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    public void showFooter() {
        notifyItemInserted(getItemCount());
        showFooter = true;
    }

    public boolean isFooterShowing() {
        return showFooter;
    }

    public void hideFooter() {
        notifyItemRemoved(getItemCount() - 1);
        showFooter = false;
    }

    class ProgressbarViewholder extends RecyclerView.ViewHolder {
        public View loadMoreView;

        public ProgressbarViewholder(View itemView) {
            super(itemView);
            loadMoreView = itemView.findViewById(R.id.load_more_view);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }
}
