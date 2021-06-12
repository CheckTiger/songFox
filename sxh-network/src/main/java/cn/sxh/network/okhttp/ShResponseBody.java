package cn.sxh.network.okhttp;

import androidx.annotation.Nullable;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import okhttp3.internal.Util;
import okio.BufferedSource;

public abstract class ShResponseBody implements Closeable {

    private Reader reader;
    public abstract @Nullable ShMediaType contentType();

    public abstract long contentLength();

    public final InputStream byteStream() {
        return source().inputStream();
    }

    public abstract BufferedSource source();

    public final byte[] bytes() throws IOException {
        long contentLength = contentLength();
        if (contentLength > Integer.MAX_VALUE) {
            throw new IOException("Cannot buffer entire body for content length: " + contentLength);
        }

        BufferedSource source = source();
        byte[] bytes;
        try {
            bytes = source.readByteArray();
        } finally {
            Util.closeQuietly(source);
        }
        if (contentLength != -1 && contentLength != bytes.length) {
            throw new IOException("Content-Length ("
                    + contentLength
                    + ") and stream length ("
                    + bytes.length
                    + ") disagree");
        }
        return bytes;
    }



    @Override
    public void close() throws IOException {

    }
}
