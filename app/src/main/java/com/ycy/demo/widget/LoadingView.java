package com.ycy.demo.widget;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ycy.demo.R;
import com.ycy.demo.utils.LogUtils;


/**
 * Created by YCY.
 */
public class LoadingView {
    private static final String TAG = "LoadingView";
    private View loadingView;
    private TextView loadingTV;

    public void showLoading(View containerView) {
        showLoading(containerView, null);
    }

    public void showLoading(View containerView, String message) {

        if (containerView == null || containerView.getContext() == null) {
            return;
        }
        if (!(containerView instanceof FrameLayout)) {
            LogUtils.e(TAG, "container view should be FrameLayout");
            return;
        }
        Context context = containerView.getContext();
        FrameLayout container = (FrameLayout) containerView;
        if (loadingView == null) {
            loadingView = LayoutInflater.from(context).inflate(R.layout.progress_view, container, false);
            loadingTV = (TextView) loadingView.findViewById(R.id.msg_tv);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            container.addView(loadingView, layoutParams);
        }
        if (TextUtils.isEmpty(message)) {
            message = context.getString(R.string.loading_default_desc);
        }
        loadingTV.setText(message);
        loadingView.setVisibility(View.VISIBLE);
        loadingView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    public void hideLoading() {
        if (loadingView != null && loadingView.getVisibility() == View.VISIBLE) {
            loadingView.setVisibility(View.GONE);
        }
    }


}
