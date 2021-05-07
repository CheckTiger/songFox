package cn.sxh.network.okhttp;

import okio.ByteString;

public interface ShWebSocket {

    ShRequest request();

    long queueSize();

    boolean send(String text);

    boolean send(ByteString bytes);

    boolean close(int code, String reason);

    void cancel();

    interface Factory {
        ShWebSocket newSongWebSocket(ShRequest request, ShWebSocketListener listener);
    }

}
