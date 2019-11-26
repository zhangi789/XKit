package com.tool.cn.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 张海洋
 * @Date on 2019/05/14.
 * @org 上海..科技有限公司
 * @describe
 */
public class BaseKit {

    /**
     * 获取版本号
     *
     * @param context
     * @return
     */
    public static String getAppVersionName(Context context) {
        String version = "0.0.0";
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            version = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
        }
        return version;
    }

    /**
     * 获取当前程序的版本号
     *
     * @param context
     * @return
     */
    public static int getAppVersionCode(Context context) {
        PackageInfo info = null;
        try {
            info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            int a = info.versionCode; //是AndroidManifest.xml文件里面的android:versionCode="1"这个值
            String b = info.versionName; //是AndroidManifest.xml文件里面的android:versionName="1.0"这个值
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return info.versionCode;
    }

    /**
     * @param mobiles 正则表达式    判断输入是否是手机号
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^1(3[0-9]|4[57]|5[0-35-9]|7[0135678]|8[0-9])\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }



    /**
     * 字符串判空
     *
     * @param result
     * @return
     */
    public static boolean isNotEmpty(String result) {
        if (null == result || "".equals(result)) {
            return false;
        }
        return true;
    }


    /**
     * 隐藏手机中间4位号码
     * 130****0000
     *
     * @param mobile_phone 手机号码
     * @return 130****0000
     */
    public static String hideMobilePhone4(String mobile_phone) {
        if (mobile_phone.length() != 11) {
            return "手机号码不正确";
        }
        return mobile_phone.substring(0, 3) + "****" + mobile_phone.substring(7, 11);
    }



}
