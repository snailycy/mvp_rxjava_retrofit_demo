package com.ycy.demo.module.news.model;

import com.google.gson.Gson;
import com.ycy.demo.bean.HttpRspNews;
import com.ycy.demo.bean.News;
import com.ycy.demo.callback.RequestCallback;
import com.ycy.demo.common.Constant;
import com.ycy.demo.http.manager.RetrofitManager;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;

/**
 * Created by YCY.
 */
public class NewsListInteractor implements INewsListInteractor {

    @Override
    public Subscription requestNewsList(final RequestCallback<List<News>> callback, int page, int pageSize) {
        return RetrofitManager.getInstance().getNewsListObservable(page, pageSize)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        callback.onBefore();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpRspNews>() {
                    @Override
                    public void onCompleted() {
                        callback.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);
                        //TODO：TEST，需要用API返回数据时，请删掉一下测试代码
                        onNext(getTestData());
                    }

                    @Override
                    public void onNext(HttpRspNews httpRspNews) {
                        if (httpRspNews != null) {
                            String status = httpRspNews.getStatus();
                            if (Constant.STATUS_OK.equals(status)) {
                                callback.onSuccess(httpRspNews.getNewsList());
                            }
                        } else {
                            // TODO
                        }
                    }
                });
    }

    private HttpRspNews getTestData() {
        String testData = "{\"status\":\"ok\",\"newsList\":[" +
                "{\"id\":1,\"title\":\"标题1\",\"desc\":\"据说描述很长会有不一样的不一样的不一样的效果1\",\"imgUrl\":\"\"}," +
                "{\"id\":2,\"title\":\"标题2\",\"desc\":\"据说描述很长会有不一样的不一样的不一样的效果2\",\"imgUrl\":\"\"}," +
                "{\"id\":3,\"title\":\"标题3\",\"desc\":\"据说描述很长会有不一样的不一样的不一样的效果3\",\"imgUrl\":\"\"}," +
                "{\"id\":4,\"title\":\"标题4\",\"desc\":\"据说描述很长会有不一样的不一样的不一样的效果4\",\"imgUrl\":\"\"}," +
                "{\"id\":5,\"title\":\"标题5\",\"desc\":\"据说描述很长会有不一样的不一样的不一样的效果5\",\"imgUrl\":\"\"}," +
                "{\"id\":6,\"title\":\"标题6\",\"desc\":\"据说描述很长会有不一样的不一样的不一样的效果6\",\"imgUrl\":\"\"}," +
                "{\"id\":7,\"title\":\"标题7\",\"desc\":\"据说描述很长会有不一样的不一样的不一样的效果7\",\"imgUrl\":\"\"}," +
                "{\"id\":8,\"title\":\"标题8\",\"desc\":\"据说描述很长会有不一样的不一样的不一样的效果8\",\"imgUrl\":\"\"}," +
                "{\"id\":9,\"title\":\"标题9\",\"desc\":\"据说描述很长会有不一样的不一样的不一样的效果9\",\"imgUrl\":\"\"}," +
                "{\"id\":10,\"title\":\"标题10\",\"desc\":\"据说描述很长会有不一样的不一样的不一样的效果10\",\"imgUrl\":\"\"}," +
                "{\"id\":11,\"title\":\"标题11\",\"desc\":\"据说描述很长会有不一样的不一样的不一样的效果11\",\"imgUrl\":\"\"}," +
                "{\"id\":12,\"title\":\"标题12\",\"desc\":\"据说描述很长会有不一样的不一样的不一样的效果12\",\"imgUrl\":\"\"}," +
                "{\"id\":13,\"title\":\"标题13\",\"desc\":\"据说描述很长会有不一样的不一样的不一样的效果13\",\"imgUrl\":\"\"}," +
                "{\"id\":14,\"title\":\"标题14\",\"desc\":\"据说描述很长会有不一样的不一样的不一样的效果14\",\"imgUrl\":\"\"}," +
                "{\"id\":15,\"title\":\"标题15\",\"desc\":\"据说描述很长会有不一样的不一样的不一样的效果15\",\"imgUrl\":\"\"}" +
                "]}";
        return new Gson().fromJson(testData, HttpRspNews.class);
    }
}
