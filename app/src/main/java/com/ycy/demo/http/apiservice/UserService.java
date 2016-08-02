package com.ycy.demo.http.apiservice;

import com.ycy.demo.bean.HttpReqUser;
import com.ycy.demo.bean.HttpRspUser;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by YCY.
 */
public interface UserService {
    @POST("user/login")
    Observable<HttpRspUser> requestUserLogin(@Body HttpReqUser httpReqUser);
}
