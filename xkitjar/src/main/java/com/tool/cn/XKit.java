package com.tool.cn;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.DisplayMetrics;

import com.tool.cn.util.BaseKit;
import com.tool.cn.util.SBarUtil;
import com.tool.cn.util.SPKit;
import com.tool.cn.util.XLog;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author 张海洋
 * create on   2019/11/20 10:08
 * description   父类
 * <p>
 * 基本工具类
 * 1：Sp 工具
 * 2：Log
 * 3: 状态栏工具类
 * 4：d2p 转化工具
 * 5：获得app 版本和版本号
 */

public class XKit {
    public static DisplayMetrics getDisplayMetrics() {
        return Resources.getSystem().getDisplayMetrics();
    }

    /**
     * @param isWhite     字体的颜色
     * @param activity
     * @param sBarBgColor 状态栏背景色颜色
     */
    public static void setStatusBarTextColor(Activity activity,boolean isWhite,int sBarBgColor) {

        if (isWhite) {
            SBarUtil.setStatusBarDarkMode(activity, sBarBgColor, Color.TRANSPARENT, false, false);
        } else {
            SBarUtil.setStatusBarLightMode(activity, sBarBgColor, Color.TRANSPARENT, false, false);
        }
    }


    /**
     * @param activity
     * @param isWhiteFont 状态栏是否是白色字体
     */
    public static void setFullScreen(Activity activity, boolean isWhiteFont) {
        if (isWhiteFont) {
            SBarUtil.setStatusBarDarkMode(activity, Color.TRANSPARENT, Color.TRANSPARENT, true, true);
        } else {
            SBarUtil.setStatusBarLightMode(activity, Color.TRANSPARENT, Color.TRANSPARENT, true, true);
        }
    }
    public static void setImmerse(Activity activity, boolean isWhiteFont,int navBarBgColor) {
        if (isWhiteFont) {
            SBarUtil.setStatusBarDarkMode(activity, Color.TRANSPARENT, navBarBgColor, true, false);
        } else {
            SBarUtil.setStatusBarLightMode(activity, Color.TRANSPARENT, navBarBgColor, true, false);
        }
    }
    /**
     * 获得状态栏高度
     *
     * @return
     */
    public static int getStatusHeight() {
        return SBarUtil.getStatusBarHeight();

    }

    /**
     * 将dp值转换为px值
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int dp2px(Context context, double dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int px2dp(Context context, double pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    public static int getScreenWidth() {
        return getDisplayMetrics().widthPixels;
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
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
     * 获得系统的当前时间  24时制度
     */


    public static String getSystemCurTime() {
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(day);
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


    /**
     * 常用打印json   和 Log.i   支持自定义debug和realse版本
     */


    public static void json(boolean isDebug, String tag, String jsonStr) {
        XLog.json(isDebug, tag, jsonStr);
    }

    public static void i(boolean isDebug, String tag, Object... objects) {
        XLog.i(isDebug, tag, objects);
    }


    /**
     * colorString="#ffffff"
     *
     * @param colorString ""
     * @return
     */

    public static int getColor(String colorString) {
        return Color.parseColor(colorString);
    }

    /**
     * setTextColor(context.getResources().getColor(R.color.Check));
     *
     * @param mContext
     * @param resourceId
     * @return
     */
    public static int getColor(Context mContext, int resourceId) {
        return mContext.getResources().getColor(resourceId);
    }

    /**
     * Activity的跳转动画     scale和alpha特效
     *
     * @param mContext
     * @param cls
     */

    public static void scale(Activity mContext, Class<?> cls) {
        mContext.startActivity(new Intent(mContext, cls));
        mContext.overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
    }

    /**
     * Activity的跳转动画    scale和alpha特效
     *
     * @param mContext
     * @param mIntent
     */

    public static void scale(Activity mContext, Intent mIntent) {
        mContext.startActivity(mIntent);
        mContext.overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
    }


    /**
     * Activity的跳转动画    从下向上滑动 加Alpha 变动特效
     *
     * @param mContext
     * @param cls
     */

    public static void slideToUp(Activity mContext, Class<?> cls) {
        mContext.startActivity(new Intent(mContext, cls));
        mContext.overridePendingTransition(R.anim.activity_up_in, R.anim.activity_up_out);
    }

    /**
     * Activity的跳转动画    从下向上滑动 加Alpha 变动特效
     *
     * @param mContext
     * @param mIntent
     */

    public static void slideToUp(Activity mContext, Intent mIntent) {
        mContext.startActivity(mIntent);
        mContext.overridePendingTransition(R.anim.activity_up_in, R.anim.activity_up_out);
    }
    /**
     * Activity的跳转动画 页面销毁动画   在finish（）  回掉即可
     *
     * @param mContext
     */
    public static void slideToDowm(Activity mContext) {
        mContext.overridePendingTransition(R.anim.activity_down_in, R.anim.activity_down_out);
    }
}
