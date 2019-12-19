package com.tool.cn;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.tool.cn.title.util.StatusBarUtil;
import com.tool.cn.util.BaseKit;
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
        StatusBarUtil.setFullScreenWithSplash(activity);
    }

    /**
     * 获得状态栏高度
     *
     * @return
     */
    public static int getStatusHeight() {
        return StatusBarUtil.getStatusBarHeight();

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


}
