package com.tool.cn.title.helper.delegate;

import android.view.View;

import com.tool.cn.title.helper.AlphaViewHelper;


/**
 * @Author: AriesHoo on 2018/7/19 9:52
 * @E-Mail: AriesHoo@126.com
 * Function: 控制View alpha度代理类
 * Description:
 */
public class AlphaDelegate {

    private View mView;
    private AlphaViewHelper mAlphaViewHelper;

    public AlphaDelegate(View view) {
        this.mView = view;
    }

    public AlphaViewHelper getAlphaViewHelper() {
        if (mAlphaViewHelper == null) {
            mAlphaViewHelper = new AlphaViewHelper(mView);
        }
        return mAlphaViewHelper;
    }
}
