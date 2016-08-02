package com.ycy.demo.http.apiservice;

import com.ycy.demo.bean.HttpRspNews;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by YCY.
 */
public interface NewsService {
    /**
     * 获取新闻列表
     */
    @GET("news/list")
    Observable<HttpRspNews> requestNewsList(@Header("Cache-Control") String cacheControl,
                                            @Query("page") int page,
                                            @Query("pageSize") int pageSize);
}
