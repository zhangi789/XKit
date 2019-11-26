package com.tool.cn.scwang.listener;

import android.support.annotation.NonNull;

import com.tool.cn.scwang.api.RefreshLayout;


/**
 * 加载更多监听器
 * Created by scwang on 2017/5/26.
 */
public interface OnLoadMoreListener {
    void onLoadMore(@NonNull RefreshLayout refreshLayout);
}
