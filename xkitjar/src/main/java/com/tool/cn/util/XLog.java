package com.tool.cn.util;

import android.util.Log;

import com.tool.cn.XKit;

/**
 * author 张海洋
 * create on   2019/12/04 13:37
 * description   Log 打印工具
 */

public class XLog {
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");
    public static final int JSON_INDENT = 4;
    public static final int V = 0x1;
    public static final int D = 0x2;
    public static final int I = 0x3;
    public static final int W = 0x4;
    public static final int E = 0x5;
    public static final int A = 0x6;
    private static final int JSON = 0x7;
    private static final int XML = 0x8;
    private static final String NULL = "null";
    private static final String TAG_DEFAULT = "XLog";
    private static final String PARAM = "Param";
    private static final String SUFFIX = ".java";
    public static final String NULL_TIPS = "Log with null object";
    private static final int MAX_LENGTH = 4000;
    private static final int STACK_TRACE_INDEX_5 = 5;


    public static void json(boolean isDebug, String tag, String jsonFormat) {
        printLog(JSON, isDebug, tag, jsonFormat);
    }

    public static void i(boolean isDebug, String tag, Object... objects) {
        printLog(I, isDebug, tag, objects);
    }


    private static void printLog(int type, boolean isDebug, String tagStr, Object... objects) {

        if (!isDebug) {
            return;
        }
        String[] contents = wrapperContent(STACK_TRACE_INDEX_5, tagStr, objects);
        String tag = contents[0];
        String msg = contents[1];
        String headString = contents[2];

        switch (type) {
            case V:
            case D:
            case I:
            case W:
            case E:
            case A:
                printDefault(type, tag, msg);
                break;
            case JSON:
                JsonLog.printJson(tag, msg, headString);
                break;

        }

    }


    private static String[] wrapperContent(int stackTraceIndex, String tagStr, Object... objects) {

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        StackTraceElement targetElement = stackTrace[stackTraceIndex];
        String className = targetElement.getClassName();
        String[] classNameInfo = className.split("\\.");
        if (classNameInfo.length > 0) {
            className = classNameInfo[classNameInfo.length - 1] + SUFFIX;
        }

        if (className.contains("$")) {
            className = className.split("\\$")[0] + SUFFIX;
        }

        String methodName = targetElement.getMethodName();
        int lineNumber = targetElement.getLineNumber();

        if (lineNumber < 0) {
            lineNumber = 0;
        }
        boolean notEmpty = XKit.isNotEmpty(tagStr);
        String tag = "";
        if (notEmpty) {
            tag = tagStr;
        } else {
            tag = TAG_DEFAULT;
        }
        String msg = (objects == null) ? NULL_TIPS : getObjectsString(objects);
        String headString = "[ (" + className + ":" + lineNumber + ")#" + methodName + " ] ";

        return new String[]{tag, msg, headString};
    }

//
    public static void printLine(String tag, boolean isTop) {
        if (isTop) {
            Log.d(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
        } else {
            Log.d(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
        }
    }


    private static String getObjectsString(Object... objects) {

        if (objects.length > 1) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\n");
            for (int i = 0; i < objects.length; i++) {
                Object object = objects[i];
                if (object == null) {
                    stringBuilder.append(PARAM).append("[").append(i).append("]").append(" = ").append(NULL).append("\n");
                } else {
                    stringBuilder.append(PARAM).append("[").append(i).append("]").append(" = ").append(object.toString()).append("\n");
                }
            }
            return stringBuilder.toString();
        } else if (objects.length == 1) {
            Object object = objects[0];
            return object == null ? NULL : object.toString();
        } else {
            return NULL;
        }
    }


    public static void printDefault(int type, String tag, String msg) {

        int index = 0;
        int length = msg.length();
        int countOfSub = length / MAX_LENGTH;

        if (countOfSub > 0) {
            for (int i = 0; i < countOfSub; i++) {
                String sub = msg.substring(index, index + MAX_LENGTH);
                printSub(type, tag, sub);
                index += MAX_LENGTH;
            }
            printSub(type, tag, msg.substring(index, length));
        } else {
            printSub(type, tag, msg);
        }
    }

    private static void printSub(int type, String tag, String sub) {
        switch (type) {
            case XLog.V:
                Log.v(tag, sub);
                break;
            case XLog.D:
                Log.d(tag, sub);
                break;
            case XLog.I:
                Log.i(tag, sub);
                break;
            case XLog.W:
                Log.w(tag, sub);
                break;
            case XLog.E:
                Log.e(tag, sub);
                break;
            case XLog.A:
                Log.wtf(tag, sub);
                break;
        }
    }

}
