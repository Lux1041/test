package com.example.tianyi.iphoneassist.bean;

import java.util.List;

/**
 * Created by Tianyi on 2017/11/13.
 */

public class PageBean<T> {
    private int status;
    private String message;
    private boolean hasMore;
    private List<T> datas;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }
}
