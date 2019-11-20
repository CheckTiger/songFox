package cn.sxh.songfox.runtimepermission;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

import cn.sxh.songfox.util.AppUtil;
import cn.sxh.songfox.util.SPUtils;

/**
 * @package-name: cn.sxh.songfox.runtimepermission
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/11/14 0014 : 10 :52
 * @project-name: songFox
 */
public class RuntimePermissionUtil {

    public static final int PERMISSION_ACTIVITY_REQUEST_CODE = 3001;//普通权限
    public static final int FLOAT_PERMISSION_ACTIVITY_REQUEST_CODE = 3002;//悬浮窗权限
    public static final int SYS_SETTING_PERMISSION_ACTIVITY_REQUEST_CODE = 3003;//系统设置权限
    private static final String TAG = "RuntimePermissionUtil";

    /**
     * 适配Android6.0运行时权限，检查是否有权限
     *
     * @param context    context 上下文
     * @param permission permission 申请的权限
     * @return
     */
    private static boolean checkSelfPermission(Context context, String permission) {
        return context != null && ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 申请权限，判断是否已经获取权限
     *
     * @param context
     * @param permissions
     * @return
     */
    public static boolean checkPermissionArray(Context context, String[] permissions) {
        if (context == null || permissions == null || permissions.length == 0) {
            return false;
        }

        for (String temp : permissions) {
            if (!checkSelfPermission(context, temp)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 过滤未获取权限的数组
     *
     * @param context
     * @param permissions
     * @return
     */
    public static String[] getPermissionsArray(Context context, String[] permissions) {
        if (context == null || permissions == null || permissions.length == 0) {
            return null;
        }
        List<String> list = new ArrayList<>();
        for (String temp : permissions) {
            if (!checkSelfPermission(context, temp)) {
                list.add(temp);
            }
        }
        String[] permissionArray = list.toArray(new String[list.size()]);
        return permissionArray;
    }

    /**
     * 申请权限时候，是否弹出系统对话框
     *
     * @param activity
     * @param permissions
     * @return
     */
    public static boolean couldPopUpSystemDialogForPermission(Activity activity, String[] permissions) {
        if (activity == null || permissions == null || permissions.length == 0) {
            KLog.e(TAG, "shouldShowDescriptionForRequest()-->activity == null || permissions==null||permissions.length == 0");
            return false;
        }
        for (String permission : permissions) {
            if (!couldPopUpSystemDialogForPermission(activity, permission)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 申请权限时候，是否弹出系统对话框
     *
     * @param activity
     * @param permission
     * @return
     */
    private static boolean couldPopUpSystemDialogForPermission(Activity activity, String permission) {
        if (activity == null || TextUtils.isEmpty(permission)) {
            KLog.e(TAG, "shouldShowDescriptionForRequest()-->activity == null || TextUtils.isEmpty(permissions)");
            return false;
        }
        return !getHasPermissionRequested(permission) || ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
    }

    /**
     * 权限是否已经申请过
     *
     * @param permission
     * @return
     */
    private static boolean getHasPermissionRequested(String permission) {
        return SPUtils.getBooleanSPValue(SPUtils.SP_NAME_RUNTIME_PERMISSION, permission, false);
    }


    /**
     * 保存已经申请过权限的标识
     *
     * @param permissions
     * @return
     */
    public static void saveHasPermissionsRequested(String[] permissions) {
        if (permissions == null || permissions.length == 0) {
            return;
        }
        for (String permission : permissions) {
            saveHasPermissionRequested(permission);
        }
    }

    /**
     * 保存已经申请过权限的标识
     *
     * @param permission
     * @return
     */
    private static void saveHasPermissionRequested(String permission) {
        SPUtils.saveBooleanSPValue(SPUtils.SP_NAME_RUNTIME_PERMISSION, permission, true);
    }

    /**
     * 设置动态权限申请回调模型
     *
     * @param permissions
     * @param callback
     */
    private static void setPermissionCallBackModel(String[] permissions, PermissionRequestManager.OnPermissionRequestResultCallback callback) {
        if (permissions == null || permissions.length == 0 || callback == null) {
            KLog.e(TAG, "setPermissionCallBackModel()-->callback == null || permissions==null||permissions.length == 0");
            return;
        }
        RuntimePermissionModel callbackModel = new RuntimePermissionModel(callback, permissions);
        PermissionRequestManager.getInstance().setRuntimePermissionModel(callbackModel);
    }

    /**
     * 跳转到应用设置页面，并保存请求权限
     *
     * @param activity
     * @param permission
     * @param callback
     */
    static void gotoAppDetails(Activity activity, String[] permission, PermissionRequestManager.OnPermissionRequestResultCallback callback) {
        if (activity == null) {
            KLog.e(TAG, "gotoAppDetails()-->activity == null ");
            return;
        }

        setPermissionCallBackModel(permission, callback);
        Intent settingIntent = new Intent();
        settingIntent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        settingIntent.setData(Uri.fromParts("package", activity.getPackageName(), null));
        activity.startActivityForResult(settingIntent, RuntimePermissionUtil.PERMISSION_ACTIVITY_REQUEST_CODE);
    }

    @SuppressLint("ServiceCast")
    public static boolean hasPermissionGetPhoneInfo() {
        Context context = AppUtil.getContext();
        if (!RuntimePermissionUtil.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE)) {
            KLog.e(TAG, "readFromSystem()-->" + !RuntimePermissionUtil.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE));
            return false;
        }

        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (telephonyManager != null) {
                KLog.e(TAG, "IMEI-->= " + telephonyManager.getDeviceId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
