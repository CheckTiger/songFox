package cn.sxh.utils;

import android.app.Activity;
import android.content.Intent;

public class ActivityManager {

    public static void gotoPage(Activity activity,Class cla) {
        Intent intent = new Intent(activity, cla);
        activity.startActivity(intent);
    }
}
