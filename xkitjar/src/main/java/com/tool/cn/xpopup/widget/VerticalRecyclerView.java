package com.tool.cn.xpopup.widget;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.tool.cn.R;
import com.tool.cn.xpopup.util.XPopupUtils;

/**
 * Description:
 * Create by dance, at 2018/12/12
 */
public class VerticalRecyclerView extends RecyclerView {


    public VerticalRecyclerView( Context context) {
        this(context, null);
    }

    public VerticalRecyclerView( Context context,  AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerticalRecyclerView( Context context,  AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void setupDivider(){
        DividerItemDecoration decoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setColor(getResources().getColor(R.color._xpopup_list_divider));
        drawable.setSize(10, XPopupUtils.dp2px(getContext(), .4f));
        decoration.setDrawable(drawable);
        addItemDecoration(decoration);
    }

}
