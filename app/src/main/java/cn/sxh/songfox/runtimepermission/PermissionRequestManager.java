package cn.sxh.songfox.runtimepermission;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.SparseArray;
import android.util.SparseBooleanArray;

import androidx.core.app.ActivityCompat;

import com.socks.library.KLog;

import cn.sxh.songfox.util.AppUtil;

/**
 * @package-name: cn.sxh.songfox.runtimepermission
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/11/15 0015 : 14 :34
 * @project-name: songFox
 */
@SuppressLint("InlinedApi")
public class PermissionRequestManager {

    public static final String TAG = "PermissionRequestManager";
    public static final int REQUEST_CODE_START = 1000;
    public static PermissionRequestManager mInstance;
    private static int maxRequestCode = REQUEST_CODE_START;

    //敏感权限请求回调相关对象维护
    private SparseArray<OnPermissionRequestResultCallback> requestCodeVsCallbackMaps = new SparseArray<>();
    //悬浮窗显示权限请求回调
    private OnFloatPermissionRequestCallback mOnFloatPermissionRequestCallback;
    //修改系统设置权限请求回调
    private OnChangeSysSettingPermissionRequestCallback mOnChangeSysSettingPermissionRequestCallback;
    private SparseBooleanArray mCouldShowSystemDialog = new SparseBooleanArray();
    private RuntimePermissionModel mRuntimePermissionModel;//跳转到设置应用详情页申请权限的回调模型

    /**
     * 在申请权限时，系统弹框往往会触发当前的onResume方法，
     */
    private boolean resultinBackground = false;

    /**
     * g
     * 在需要跳转到应用详情页去帮助用户打开权限时，需要保持ResultingBackground状态不变
     */
    private boolean isGoingToAppDetails = false;

    /**
     * 在用户授予了获取设备信息的权限后，应当实时更新设备信息
     */
    private boolean needUpDateHardInfo = false;

    private PermissionRequestManager() {
    }

    public static PermissionRequestManager getInstance() {
        if (mInstance == null) {
            synchronized (PermissionRequestManager.class) {
                if (mInstance == null) {
                    mInstance = new PermissionRequestManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 跳转设置悬浮窗权限
     *
     * @param activity
     * @param callback
     */
    public static void doSomethingWhenNeedWindowPermission(Activity activity, final OnFloatPermissionRequestCallback callback) {
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
        activity.startActivityForResult(intent, RuntimePermissionUtil.FLOAT_PERMISSION_ACTIVITY_REQUEST_CODE);
        PermissionRequestManager.getInstance().setOnFloatPermissionRequestCallback(callback);
    }

    /**
     * 此权限申请，一次请求多个，如果有一个未授权，就返回false
     *
     * @param permissions 请求权限组
     * @param activity    当前请求句柄
     * @param callback    请求回调
     */
    public void requestPermission(String[] permissions, Activity activity, final OnPermissionRequestResultCallback callback) {
        if (activity == null || permissions == null || permissions.length == 0) {
            KLog.e(TAG, "requestPermission()-->activity == null || permissions==null||permissions.length == 0");
            return;
        }
        //执行请求
        executeRequest(permissions, activity, callback);
        //记录某个权限已经申请过
        RuntimePermissionUtil.saveHasPermissionsRequested(permissions);
    }

    /**
     * 执行权限申请
     *
     * @param permissions 请求权限组
     * @param activity    当前请求句柄
     * @param callback    请求回调
     */
    private void executeRequest(String[] permissions, Activity activity, OnPermissionRequestResultCallback callback) {
        if (activity == null) {
            KLog.e(TAG, "executeRequest()-->activity == null");
        }
        setRequestPermissionFlag();
        boolean couldNotice = RuntimePermissionUtil.couldPopUpSystemDialogForPermission(activity, permissions);
        ActivityCompat.requestPermissions(activity, permissions, getRequestCode(callback, couldNotice));
    }

    private void setRequestPermissionFlag() {
        // TODO: 2019/11/15 0015 此处的Activity是当前栈顶的Activity，需要获取到当前页面上下文，需要根据自己的业务进行设置
        Activity curActivity = (Activity) AppUtil.getContext();
        if (curActivity != null) {
            resultinBackground = true;
        }
    }

    private int getRequestCode(OnPermissionRequestResultCallback callback, boolean couldNotice) {
        maxRequestCode++;
        requestCodeVsCallbackMaps.put(maxRequestCode, callback);
        mCouldShowSystemDialog.put(maxRequestCode, couldNotice);
        return maxRequestCode;
    }


    /**
     * 正常场景权限获取的回调方法，在Activity的onRequestPermissionResult 方法里调用
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults) {
        boolean isGrant = true;
        for (int granted : grantResults) {
            if (granted == PackageManager.PERMISSION_GRANTED) {
                isGrant = false;
                break;
            }
        }
        if (isGrant) {
            setNeedUpdateHardInfo(permissions);
        }
        OnPermissionRequestResultCallback callback = requestCodeVsCallbackMaps.get(requestCode);
        if (callback != null) {
            callback.onPermissionRequestResult(isGrant, mCouldShowSystemDialog.get(requestCode));
        }
        requestCodeVsCallbackMaps.remove(requestCode);
        mCouldShowSystemDialog.delete(requestCode);
    }

    public void onActivityResult() {
        if (mRuntimePermissionModel != null && mRuntimePermissionModel.isValid()) {
            boolean isGrant = RuntimePermissionUtil.checkPermissionArray(AppUtil.getContext(), mRuntimePermissionModel.permissions);
            if (isGrant) {
                setNeedUpdateHardInfo(mRuntimePermissionModel.permissions);
            }
            mRuntimePermissionModel.callback.onPermissionRequestResult(isGrant, false);
        }
        mRuntimePermissionModel = null;
        isGoingToAppDetails = false;
    }

    private void setNeedUpdateHardInfo(String[] permissions) {
        for (String permission : permissions) {
            if (TextUtils.equals(permission, Manifest.permission.READ_PHONE_STATE)) {
                needUpDateHardInfo = true;
            }
        }
    }

    void setRuntimePermissionModel(RuntimePermissionModel mRuntimePermissionModel) {
        this.mRuntimePermissionModel = mRuntimePermissionModel;
    }

    public void gotoAppDetailForPermission(Activity activity, String[] permissions, OnPermissionRequestResultCallback callback) {
        if (activity == null || permissions == null || permissions.length == 0) {
            return;
        }
        isGoingToAppDetails = true;
        setRequestPermissionFlag();
        RuntimePermissionUtil.gotoAppDetails(activity,permissions,callback);
    }

    public void resultForFloatPermission(){
        if (mOnFloatPermissionRequestCallback != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(AppUtil.getContext())) {
                mOnFloatPermissionRequestCallback.onFloatPermissionRequestResult(false);
            } else {
                mOnFloatPermissionRequestCallback.onFloatPermissionRequestResult(true);
            }
            mOnFloatPermissionRequestCallback = null;
        }
    }

    private void setOnFloatPermissionRequestCallback(OnFloatPermissionRequestCallback onFloatPermissionRequestCallback) {
        this.mOnFloatPermissionRequestCallback = onFloatPermissionRequestCallback;
    }

    public void resultForSysSettingsPermission(){
        if (mOnChangeSysSettingPermissionRequestCallback != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(AppUtil.getContext())) {
                mOnChangeSysSettingPermissionRequestCallback.onChangeSysSettingPermissionRequestResult(false);
            } else {
                mOnChangeSysSettingPermissionRequestCallback.onChangeSysSettingPermissionRequestResult(true);
            }
            mOnChangeSysSettingPermissionRequestCallback = null;
        }
    }

    public void requestSysSettingPermission(final Context context, OnChangeSysSettingPermissionRequestCallback callback) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        Activity activity = (Activity) AppUtil.getContext();
        if (activity != null) {
            activity.startActivityForResult(intent, RuntimePermissionUtil.SYS_SETTING_PERMISSION_ACTIVITY_REQUEST_CODE);
            this.mOnChangeSysSettingPermissionRequestCallback = callback;
            isGoingToAppDetails = true;
        } else {
            KLog.e("SwitchSetting","SysSetting-->activity ==null");
        }
    }


    public interface OnPermissionRequestResultCallback {
        /**
         * @param isGranted   权限是否授予
         * @param couldNotice 本次请求权限时是否能弹出系统权限框（用户选择不再提示后，
         *                    申请权限是不会在弹框供用户打开权限）用户可以根据此标识是否要跳转到设置界面或者其它操作
         */
        void onPermissionRequestResult(boolean isGranted, boolean couldNotice);
    }

    /**
     * 悬浮窗权限
     */
    public interface OnFloatPermissionRequestCallback {
        /**
         * @param isGranted 权限是否授予
         */
        void onFloatPermissionRequestResult(boolean isGranted);
    }

    /**
     * 修改系统设置权限请求回调
     */
    public interface OnChangeSysSettingPermissionRequestCallback {
        /**
         * @param isGranted 权限是否授予
         */
        void onChangeSysSettingPermissionRequestResult(boolean isGranted);
    }

}
