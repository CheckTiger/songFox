package cn.sxh.animation.one;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

public class TestServiceConnection implements ServiceConnection {


    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        if (service != null) {
            ((TestService.ITestBinder) service).invokeMethodInMyService();
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }

}