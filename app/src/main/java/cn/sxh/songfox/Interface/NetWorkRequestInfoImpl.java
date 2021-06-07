package cn.sxh.songfox.Interface;

import cn.sxh.network.interfaces.INetworkRequestInfo;
import cn.sxh.songfox.BuildConfig;

public class NetWorkRequestInfoImpl implements INetworkRequestInfo {

    @Override
    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    @Override
    public String appVersionName() {
        return "1.0.0";
    }

    @Override
    public String appVersionCode() {
        return "2";
    }
}
