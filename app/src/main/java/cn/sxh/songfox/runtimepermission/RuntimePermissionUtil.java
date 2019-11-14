package cn.sxh.songfox.runtimepermission;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * @package-name: cn.sxh.songfox.runtimepermission
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/11/14 0014 : 10 :52
 * @project-name: songFox
 */
public class RuntimePermissionUtil {

    public static final int permission_activity_request_code = 3001;
    public static final int float_permission_activity_request_code = 3002;
    public static final int sys_setting_permission_activity_request_code = 3003;
    public static final String TAG = "RuntimePermissionUtil";

    /**
     * 适配Android6.0运行时权限，检查是否有权限
     * @param context context 上下文
     * @param permission permission 申请的权限
     * @return
     */
    private static boolean checkSelfPermission(Context context, String permission) {
        return context != null && ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 申请权限，判断是否已经获取权限
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



}
