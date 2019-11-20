package cn.sxh.songfox.runtimepermission;

/**
 * @package-name: cn.sxh.songfox.runtimepermission
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/11/15 0015 : 14 :33
 * @project-name: songFox
 */
public class RuntimePermissionModel {

    public PermissionRequestManager.OnPermissionRequestResultCallback callback;
    public String[] permissions;

    public RuntimePermissionModel(PermissionRequestManager.OnPermissionRequestResultCallback callback, String[] permissions) {
        this.callback = callback;
        this.permissions = permissions;
    }

    public boolean isValid() {
        return callback != null && permissions != null && permissions.length > 0;
    }
}
