package com.tool.cn.util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * author 张海洋
 * create on   2019/12/04 14:05
 * description   json 打印工具
 *
 */
//
public class JsonLog {
    public static void printJson(String tag, String msg, String headString) {

        String message;
        try {
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(XLog.JSON_INDENT);
            } else if (msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(XLog.JSON_INDENT);
            } else {
                message = msg;
            }
        } catch (JSONException e) {
            message = msg;
        }

        XLog.printLine(tag, true);
        message = message;
        String[] lines = message.split(XLog.LINE_SEPARATOR);
        for (String line : lines) {
            Log.d(tag, "║ " + line);
        }
        XLog.printLine(tag, false);
    }
}
