package cn.sxh.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * @package-name: cn.sxh.songfox.util
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/11/14 0014 : 19 :53
 * @project-name: songFox
 */
public class SPUtils {


    public static final String SP_NAME_RUNTIME_PERMISSION = "SP_NAME_RUNTIME_PERMISSION";//权限管理
    /**
     * 保存String类型数据
     * @param spName 存储文件名
     * @param key 存储key值
     * @param value 数据
     */
    public static void saveStringSPValue(String spName, String key, String value) {
        saveStringSPValue(AppUtil.getContext(), spName, key, value);
    }


    /**
     * 保存String类型数据
     * @param spName 存储文件名
     * @param key 存储key值
     * @param value 数据
     */
    private static void saveStringSPValue(Context context, String spName, String key, String value) {
        if (context == null) {
            return;
        }
        Editor editor = context.getSharedPreferences(spName, Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * 更新保存的Long类型数据
     *
     * @param spName 存储文件名
     * @param key    存储key值
     * @param value  数据
     */
    public static void upDateSaveStringSPValue(String spName, String key, String value) {
        upDateSaveStringSPValue(AppUtil.getContext(),spName,key,value);
    }

    /**
     * 更新保存的String类型数据
     * @param spName 存储文件名
     * @param key 存储key值
     * @param value 数据
     */
    private static void upDateSaveStringSPValue(Context context, String spName, String key, String value) {
        if (context == null) {
            return;
        }
        Editor editor = context.getSharedPreferences(spName, Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * 更新保存的Long类型数据
     *
     * @param spName 存储文件名
     * @param key    存储key值
     * @param value  数据
     */
    public static void clearStringSPValue(String spName, String key, String value) {
        clearStringSPValue(AppUtil.getContext(),spName,key,value);
    }

    /**
     * 清除保存String类型数据
     * @param spName 存储文件名
     * @param key 存储key值
     * @param value 数据
     */
    private static void clearStringSPValue(Context context, String spName, String key, String value) {
        if (context == null) {
            return;
        }
        Editor editor = context.getSharedPreferences(spName, Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * 保存Int类型数据
     * @param spName 存储文件名
     * @param key 存储key值
     * @param value 数据
     */
    public static void saveIntSPValue(String spName, String key, int value) {
        saveIntSPValue(AppUtil.getContext(), spName, key, value);
    }


    /**
     * 保存Int类型数据
     * @param spName 存储文件名
     * @param key 存储key值
     * @param value 数据
     */
    private static void saveIntSPValue(Context context, String spName, String key, int value) {
        if (context == null) {
            return;
        }
        Editor editor = context.getSharedPreferences(spName, Context.MODE_PRIVATE).edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * 更新保存的Long类型数据
     *
     * @param spName 存储文件名
     * @param key    存储key值
     * @param value  数据
     */
    public static void upDateSaveIntSPValue(String spName, String key, int value) {
        upDateSaveIntSPValue(AppUtil.getContext(),spName,key,value);
    }

    /**
     * 更新保存的Int类型数据
     * @param spName 存储文件名
     * @param key 存储key值
     * @param value 数据
     */
    private static void upDateSaveIntSPValue(Context context, String spName, String key, int value) {
        if (context == null) {
            return;
        }
        Editor editor = context.getSharedPreferences(spName, Context.MODE_PRIVATE).edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * 更新保存的Long类型数据
     *
     * @param spName 存储文件名
     * @param key    存储key值
     * @param value  数据
     */
    public static void clearIntSPValue(String spName, String key, int value) {
        clearIntSPValue(AppUtil.getContext(),spName,key,value);
    }

    /**
     * 清除保存Int类型数据
     * @param spName 存储文件名
     * @param key 存储key值
     * @param value 数据
     */
    private static void clearIntSPValue(Context context, String spName, String key, int value) {
        if (context == null) {
            return;
        }
        Editor editor = context.getSharedPreferences(spName, Context.MODE_PRIVATE).edit();
        editor.putInt(key, value);
        editor.apply();
    }


    /**
     * 保存Long类型数据
     * @param spName 存储文件名
     * @param key 存储key值
     * @param value 数据
     */
    public static void saveLongSPValue(String spName, String key, long value) {
        saveLongSPValue(AppUtil.getContext(), spName, key, value);
    }


    /**
     * 保存Long类型数据
     * @param spName 存储文件名
     * @param key 存储key值
     * @param value 数据
     */
    private static void saveLongSPValue(Context context, String spName, String key, long value) {
        if (context == null) {
            return;
        }
        Editor editor = context.getSharedPreferences(spName, Context.MODE_PRIVATE).edit();
        editor.putLong(key, value);
        editor.apply();
    }

    /**
     * 更新保存的Long类型数据
     *
     * @param spName 存储文件名
     * @param key    存储key值
     * @param value  数据
     */
    public static void upDateSaveLongSPValue(String spName, String key, long value) {
        upDateSaveLongSPValue(AppUtil.getContext(),spName,key,value);
    }

    /**
     * 更新保存的Long类型数据
     * @param spName 存储文件名
     * @param key 存储key值
     * @param value 数据
     */
    private static void upDateSaveLongSPValue(Context context, String spName, String key, long value) {
        if (context == null) {
            return;
        }
        Editor editor = context.getSharedPreferences(spName, Context.MODE_PRIVATE).edit();
        editor.putLong(key,value);
        editor.apply();
    }

    /**
     * 清除保存Long类型数据
     *
     * @param spName 存储文件名
     * @param key    存储key值
     * @param value  数据
     */
    public static void clearLongSPValue(String spName, String key, long value) {
        clearLongSPValue(AppUtil.getContext(),spName,key,value);
    }

    /**
     * 清除保存Long类型数据
     *
     * @param spName 存储文件名
     * @param key    存储key值
     * @param value  数据
     */
    private static void clearLongSPValue(Context context, String spName, String key, long value) {
        if (context == null) {
            return;
        }
        Editor editor = context.getSharedPreferences(spName, Context.MODE_PRIVATE).edit();
        editor.putLong(key, value);
        editor.apply();
    }


    /**
     * 保存Boolean类型数据
     * @param spName 存储文件名
     * @param key 存储key值
     * @param value 数据
     */
    public static void saveBooleanSPValue(String spName, String key, boolean value) {
        saveBooleanSPValue(AppUtil.getContext(), spName, key, value);
    }


    /**
     * 保存Long类型数据
     * @param spName 存储文件名
     * @param key 存储key值
     * @param value 数据
     */
    private static void saveBooleanSPValue(Context context, String spName, String key, boolean value) {
        if (context == null) {
            return;
        }
        Editor editor = context.getSharedPreferences(spName, Context.MODE_PRIVATE).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * 更新保存的Boolean类型数据
     * @param spName 存储文件名
     * @param key 存储key值
     * @param value 数据
     */
    public static void upDateSaveBooleanSPValue(String spName, String key, boolean value) {
        upDateSaveBooleanSPValue(AppUtil.getContext(),spName,key,value);
    }

    /**
     * 更新保存的Boolean类型数据
     * @param spName 存储文件名
     * @param key 存储key值
     * @param value 数据
     */
    private static void upDateSaveBooleanSPValue(Context context, String spName, String key, boolean value) {
        if (context == null) {
            return;
        }
        Editor editor = context.getSharedPreferences(spName, Context.MODE_PRIVATE).edit();
        editor.putBoolean(key,value);
        editor.apply();
    }

    /**
     * 清除保存Boolean类型数据
     *
     * @param spName 存储文件名
     * @param key    存储key值
     * @param value  数据
     */
    public static void clearBooleanSPValue(String spName, String key, boolean value) {
        clearBooleanSPValue(AppUtil.getContext(),spName,key,value);
    }

    /**
     * 清除保存Boolean类型数据
     *
     * @param spName 存储文件名
     * @param key    存储key值
     * @param value  数据
     */
    private static void clearBooleanSPValue(Context context, String spName, String key, boolean value) {
        if (context == null) {
            return;
        }
        Editor editor = context.getSharedPreferences(spName, Context.MODE_PRIVATE).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * 获取String类型数据
     * @param spName 存储文件名
     * @param key 存储key值
     */
    public static String getStringSPValue(String spName, String key) {
        return getStringSPValue(AppUtil.getContext(), spName, key);
    }

    /**
     * 获取String类型数据
     * @param spName 存储文件名
     * @param key 存储key值
     */
    private static String getStringSPValue(Context context, String spName, String key) {
        String value = "";
        if (context == null) {
            return value;
        }
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        if (sp != null && key != null) {
            value = sp.getString(key, null);
        }
        return value;
    }


    /**
     * 获取Int类型数据
     * @param spName 存储文件名
     * @param key 存储key值
     */
    public static int getIntSPValue(String spName, String key,int defaultValue) {
        return getIntSPValue(AppUtil.getContext(), spName, key,defaultValue);
    }

    /**
     * 获取Int类型数据
     * @param spName 存储文件名
     * @param key 存储key值
     */
    private static int getIntSPValue(Context context, String spName, String key,int defaultValue) {
        int value = defaultValue;
        if (context == null) {
            return value;
        }
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        if (sp != null && key != null) {
            value = sp.getInt(key, defaultValue);
        }
        return value;
    }


    /**
     * 获取Long类型数据
     * @param spName 存储文件名
     * @param key 存储key值
     */
    public static long getLongSPValue(String spName, String key) {
        return getLongSPValue(AppUtil.getContext(), spName, key);
    }

    /**
     * 获取Long类型数据
     * @param spName 存储文件名
     * @param key 存储key值
     */
    private static long getLongSPValue(Context context, String spName, String key) {
        long value = 0;
        if (context == null) {
            return value;
        }
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        if (sp != null && key != null) {
            value = sp.getLong(key, 0);
        }
        return value;
    }

    /**
     * 获取Boolean类型数据
     * @param spName 存储文件名
     * @param key 存储key值
     */
    public static boolean getBooleanSPValue(String spName, String key,boolean defaultValue) {
        return getBooleanSPValue(AppUtil.getContext(), spName, key,defaultValue);
    }

    /**
     * 获取Boolean类型数据
     * @param spName 存储文件名
     * @param key 存储key值
     */
    private static boolean getBooleanSPValue(Context context, String spName, String key,boolean defaultValue) {
        boolean value = defaultValue;
        if (context == null) {
            return value;
        }
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        if (sp != null && key != null) {
            value = sp.getBoolean(key, defaultValue);
        }
        return value;
    }


    /**
     * 删除键值
     * @param spName 存储文件名
     * @param key 存储key值
     */
    public static void removeSPValue(String spName, String key) {
        removeSPValue(AppUtil.getContext(),spName,key);
    }

    /**
     * 删除键值
     * @param spName 存储文件名
     * @param key 存储key值
     */
    private static void removeSPValue(Context context, String spName, String key) {
        if (context == null) {
            return;
        }
        Editor editor = context.getSharedPreferences(spName, Context.MODE_PRIVATE).edit();
        editor.remove(key);
        editor.apply();
    }

}
