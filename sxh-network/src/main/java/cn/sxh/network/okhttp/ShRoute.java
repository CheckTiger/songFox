package cn.sxh.network.okhttp;


import android.os.Build;

import androidx.annotation.RequiresApi;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Objects;

public final class ShRoute {
    final ShAddress address;
    final Proxy proxy;
    final InetSocketAddress inetSocketAddress;

    public ShRoute(ShAddress address, Proxy proxy, InetSocketAddress inetSocketAddress) {
        if (address == null) {
            throw new NullPointerException("address ==null");
        }

        if (proxy == null) {
            throw new NullPointerException("address ==null");
        }

        if (inetSocketAddress == null) {
            throw new NullPointerException("address ==null");
        }


        this.address = address;
        this.proxy = proxy;
        this.inetSocketAddress = inetSocketAddress;
    }

    public ShAddress address() {
        return address;
    }

    public Proxy proxy() {
        return proxy;
    }

    public InetSocketAddress socketAddress() {
        return inetSocketAddress;
    }

    public boolean requiresTunnel() {
        /*address.sslSocketFactory != null &&*/
        return  proxy.type() == Proxy.Type.HTTP;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShRoute shRoute = (ShRoute) o;
        return Objects.equals(address, shRoute.address) &&
                Objects.equals(proxy, shRoute.proxy) &&
                Objects.equals(inetSocketAddress, shRoute.inetSocketAddress);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + address.hashCode();
        result = 31 * result + proxy.hashCode();
        result = 31 * result + inetSocketAddress.hashCode();
        return result;
    }

    public String toString() {
        return "Route{" + inetSocketAddress + "}";
    }
}
