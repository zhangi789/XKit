package com.tool.cn.title.util;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.tool.cn.R;
import com.tool.cn.XKit;
import com.tool.cn.title.helper.NavigationViewHelper;


/**
 * @Author: AriesHoo on 2019/4/23 14:50
 * @E-Mail: AriesHoo@126.com
 * @Function:
 * @Description:
 */
public class NavigationBarControlUtil {

    public static NavigationBarControl getNavigationBarControl(final boolean isNavigation, final boolean isNavigationPlus){
        return new NavigationBarControl() {
            @Override
            public boolean setNavigationBar(Dialog dialog, NavigationViewHelper helper, View bottomView) {
                Drawable drawableTop = ContextCompat.getDrawable(dialog.getContext(), R.color.colorLineGray);
                DrawableUtil.setDrawableWidthHeight(drawableTop, XKit.getScreenWidth(), XKit.dp2px(0.5f));
                helper.setNavigationViewDrawableTop(drawableTop)
                        .setPlusNavigationViewEnable(isNavigationPlus)
                        .setNavigationBarLightMode(true)
                        .setNavigationViewColor(Color.argb(isDialogDarkIcon() ? 0 : 60, 0, 0, 0))
                        .setNavigationLayoutColor(Color.WHITE);
                //导航栏在底部控制才有意义,不然会很丑;开发者自己决定;这里仅供参考
                return NavigationBarUtil.isNavigationAtBottom(dialog.getWindow()) && isNavigation;
            }
        };
    }

    public static boolean isDialogDarkIcon() {
        return (RomUtil.isEMUI() && (RomUtil.getEMUIVersion().compareTo("EmotionUI_4.1") > 0));
    }
}
