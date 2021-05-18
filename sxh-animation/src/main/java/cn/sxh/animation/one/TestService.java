package cn.sxh.animation.one;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class TestService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new TestServiceBinder();
    }

    class TestServiceBinder extends Binder implements ITestBinder{

        public TestService getService(){
            return TestService.this;
        }

        @Override
        public void invokeMethodInMyService() {
            Log.e("sxh", "invokeMethodInMyService--->方法执行了");
        }
    }


    public interface ITestBinder {
        void invokeMethodInMyService();
    }
}
