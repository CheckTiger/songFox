package cn.sxh.network.socket;

public interface SocketCallback {

    void onError(Exception e);

    void onSuccess(String result);
}
