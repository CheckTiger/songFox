package cn.sxh.network.socket;

public interface IWSClient {

    void prepareBeforeConnectServer();

    void startConnectServer();

    void disconnectServer();
}
