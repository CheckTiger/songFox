package cn.sxh.network.okhttp;

import java.io.Closeable;
import java.io.IOException;

public abstract class ShResponseBody implements Closeable {

    ShMediaType mediaType;
    @Override

    public void close() throws IOException {

    }
}
