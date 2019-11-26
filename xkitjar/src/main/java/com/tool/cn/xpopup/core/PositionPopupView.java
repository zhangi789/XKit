package com.tool.cn.xpopup.core;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tool.cn.R;
import com.tool.cn.xpopup.animator.PopupAnimator;
import com.tool.cn.xpopup.animator.ScrollScaleAnimator;
import com.tool.cn.xpopup.util.XPopupUtils;
import com.tool.cn.xpopup.widget.PartShadowContainer;

import static com.tool.cn.xpopup.enums.PopupAnimation.ScaleAlphaFromCenter;


/**
 * Description: 用于自由定位的弹窗
 * Create by dance, at 2019/6/14
 */
public class PositionPopupView extends BasePopupView {
    PartShadowContainer attachPopupContainer;

    public PositionPopupView(@NonNull Context context) {
        super(context);
        attachPopupContainer = findViewById(R.id.attachPopupContainer);

        View contentView = LayoutInflater.from(getContext()).inflate(getImplLayoutId(), attachPopupContainer, false);
        attachPopupContainer.addView(contentView);
    }

    @Override
    protected int getPopupLayoutId() {
        return R.layout._xpopup_attach_popup_view;
    }

    @Override
    protected void initPopupContent() {
        super.initPopupContent();
        XPopupUtils.applyPopupSize((ViewGroup) getPopupContentView(), getMaxWidth(), getMaxHeight(), new Runnable() {
            @Override
            public void run() {
                if (popupInfo.isCenterHorizontal) {
                    float left = (XPopupUtils.getWindowWidth(getContext())-attachPopupContainer.getMeasuredWidth())/2f;
                    attachPopupContainer.setTranslationX(left);
                }else {
                    attachPopupContainer.setTranslationX(popupInfo.offsetX);
                }
                attachPopupContainer.setTranslationY(popupInfo.offsetY);
            }
        });
    }

    @Override
    protected PopupAnimator getPopupAnimator() {
        return new ScrollScaleAnimator(getPopupContentView(), ScaleAlphaFromCenter);
    }
}