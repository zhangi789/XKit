package com.tool.cn;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.tool.cn.title.util.StatusBarUtil;
import com.tool.cn.util.BaseKit;
import com.tool.cn.util.SPKit;

/**
 * author 张海洋
 * create on   2019/11/20 10:08
 * description   父类
 */

public class XKit {
    public static DisplayMetrics getDisplayMetrics() {
        return Resources.getSystem().getDisplayMetrics();
    }

    /**
     * 设置状态栏字体的颜色
     * 默认状态栏字体的颜色为白色
     *
     * @param isWhite
     * @param activity
     */
    public static void setStatusBarTextColor(boolean isWhite, Activity activity) {

        if (isWhite) {
            StatusBarUtil.setStatusBarDarkMode(activity);
        } else {
            StatusBarUtil.setStatusBarLightMode(activity);
        }
    }

    /**
     * 设置全屏    用于闪屏页使用
     *
     * @param activity
     */
    public static void setFullScreen(Activity activity) {
        StatusBarUtil.setFullScreen(activity);
    }


    /**
     * 将dp值转换为px值
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    public static int getScreenWidth() {
        return getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return getDisplayMetrics().heightPixels;
    }

    public static int dp2px(float dpValue) {
        final float scale = getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2sp(float pxValue) {
        final float fontScale = getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }


    

    /**
     * 获得版本名称
     *
     * @param context
     * @return
     */
    public static String getAppVersionName(Context context) {
        return BaseKit.getAppVersionName(context);
    }


    /**
     * 获得版本号
     *
     * @param context
     * @return
     */
    public static int getAppVersionCode(Context context) {
        return BaseKit.getAppVersionCode(context);
    }


    /**
     * 正则表达式  判断是否是手机号
     *
     * @param mobiles
     * @return
     */

    public static boolean isMobile(String mobiles) {
        return BaseKit.isMobileNO(mobiles);
    }


    /**
     * 判断字符串是否为空
     *
     * @param result
     * @return
     */
    public static boolean isNotEmpty(String result) {
        return BaseKit.isNotEmpty(result);
    }
    /**
     * SPKit  使用方式
     * SharedPreferences 使用一致
     */

    public static void putString(Context context, String key, String value) {
        SPKit.putString(context, key, value);
    }

    public static String getString(Context context, String key, String defValue) {
        return SPKit.getString(context, key, defValue);
    }


    public static void putInt(Context context, String key, int value) {
        SPKit.putInt(context, key, value);
    }

    public static int getInt(Context context, String key, int defValue) {
        return SPKit.getInt(context, key, defValue);
    }

    public static void putBoolean(Context context, String key, boolean value) {
        SPKit.putBoolean(context, key, value);
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        return SPKit.getBoolean(context, key, defValue);
    }


    public static void putFloat(Context context, String key, float value) {
        SPKit.putFloat(context, key, value);
    }

    public static float getFloat(Context context, String key, float defValue) {
        return SPKit.getFloat(context, key, defValue);
    }


}
