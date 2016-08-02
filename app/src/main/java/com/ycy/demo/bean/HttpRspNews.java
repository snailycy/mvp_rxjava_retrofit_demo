package com.ycy.demo.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 该API返回值结构如下：
 * {"status":"ok","newsList":[{"id":1,"title":"标题","desc":"描述","imgUrl":"http://cdn.demo.img/news/list/img01.jpg"}]}
 */
public class HttpRspNews implements Serializable {
    private String status;
    private List<News> newsList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }
}
