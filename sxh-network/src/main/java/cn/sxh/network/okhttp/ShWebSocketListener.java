package cn.sxh.network.okhttp;


import okio.ByteString;

public abstract class ShWebSocketListener {

    public void onOpen(ShWebSocket webSocket, ShResponse response) {
    }

    public void onMessage(ShWebSocket webSocket, String text) {
    }

    public void onMessage(ShWebSocket webSocket, ByteString bytes) {
    }

    public void onClosing(ShWebSocket webSocket, int code, String reason) {
    }

    public void onClosed(ShWebSocket webSocket, int code, String reason) {
    }

    public void onFailure(ShWebSocket webSocket, Throwable t, ShResponse response) {
    }

}
