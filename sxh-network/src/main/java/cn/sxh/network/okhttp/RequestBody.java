package cn.sxh.network.okhttp;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.internal.Util;
import okio.BufferedSink;
import okio.ByteString;
import okio.Okio;
import okio.Source;

public abstract class RequestBody {

    public abstract MediaType contentType();

    public long contentLength() throws IOException {
        return -1;
    }

    public abstract void writeTo(BufferedSink sink) throws IOException;


    public static RequestBody create(MediaType contentType, String content) {
        Charset charset = Util.UTF_8;
        if (contentType != null) {
            charset = contentType.charset();
            if (charset == null) {
                charset = Util.UTF_8;
                contentType = MediaType.parse(contentType + ";charset=utf-8");
            }
        }
        byte[] bytes = content.getBytes();
        return create(contentType, bytes);
    }

    public static RequestBody create(MediaType contentType, final byte[] content) {
        return create(contentType, content, 0, content.length);
    }

    private static RequestBody create(final MediaType contentType, final byte[] content,
                                      final int offset, final int byteCount) {
        if (content == null) throw new NullPointerException("content == null");
        Util.checkOffsetAndCount(content.length, offset, byteCount);
        return new RequestBody() {
            @Override
            public MediaType contentType() {
                return contentType;
            }

            @Override
            public long contentLength() throws IOException {
                return byteCount;
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                sink.write(content, offset, byteCount);
            }
        };

    }



    /** Returns a new request body that transmits {@code content}. */
    public static RequestBody create(
            final  MediaType contentType, final ByteString content) {
        return new RequestBody() {
            @Override public  MediaType contentType() {
                return contentType;
            }

            @Override public long contentLength() throws IOException {
                return content.size();
            }

            @Override public void writeTo(BufferedSink sink) throws IOException {
                sink.write(content);
            }
        };
    }


    public static RequestBody create(final MediaType contentType, final File file) {
        if (file==null) throw new NullPointerException("content == null");
        return new RequestBody() {
            @Override
            public MediaType contentType() {
                return contentType;
            }

            @Override
            public long contentLength() throws IOException {
                return file.length();
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                Source source = null;
                try {
                    source = Okio.source(file);
                    sink.writeAll(source);
                }finally {
                    Util.closeQuietly(source);
                }
            }
        };
    }


}
