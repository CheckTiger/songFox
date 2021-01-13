package cn.sxh.songfox.Interface;

import cn.sxh.network.inter.INetworkRequestInfo;
import cn.sxh.songfox.BuildConfig;

public class NetWorkRequestInfoImpl implements INetworkRequestInfo {

    @Override
    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }
}
