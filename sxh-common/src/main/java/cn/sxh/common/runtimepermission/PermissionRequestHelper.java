package cn.sxh.common.runtimepermission;

import android.app.Activity;
import android.os.Build;

/**
 * @package-name: cn.sxh.songfox.runtimepermission
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/11/18 0018 : 17 :03
 * @project-name: songFox
 */
public class PermissionRequestHelper {

    public static void requestPermission(Activity activity, String permissions, String content, PermissionGrantedCallback callback) {
        requestPermission(activity,new String[]{permissions},content,callback);
    }

    private static void requestPermission(Activity activity, String[] permissions, String content, final PermissionGrantedCallback callback) {
        if (activity == null) {
            return;
        }

        //android6.0以后才执行动态权限申请
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            if (callback != null) {
                callback.granted();
            }
            return;
        }

        //筛选掉已经获取到的权限，预防小米旧版多个权限单一允许，请求后会弹出已允许权限弹窗的bug
        final String[] permissionList = RuntimePermissionUtil.getPermissionsArray(activity, permissions);

        if (permissionList == null || permissionList.length == 0) {
            if (callback != null) {
                callback.granted();
            }
            return;
        }
        PermissionRequestManager.getInstance().requestPermission(permissionList, activity, new PermissionRequestManager.OnPermissionRequestResultCallback() {
            @Override
            public void onPermissionRequestResult(boolean isGranted, boolean couldNotice) {
                if (isGranted) {
                    if (callback != null) {
                        callback.granted();
                    }
                } else {
                    // TODO: 2019/11/19 0019 此处自己实现Dialog提示框 跳转到应用详情设置页，打开权限
                    //具体方法在showGotoDetailDialog
                }
            }
        });
    }


    public interface PermissionGrantedCallback{
        void granted();

        void deny();
    }
}
