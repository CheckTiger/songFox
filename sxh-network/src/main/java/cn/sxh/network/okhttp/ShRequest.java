package cn.sxh.network.okhttp;

import androidx.annotation.Nullable;

import java.net.URL;
import java.util.List;

import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.HttpUrl;

import static okhttp3.internal.Util.EMPTY_BYTE_ARRAY;

/**
 * @package-name: cn.sxh.base.okhttp
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2020/4/29 0029 : 21 :00
 * @project-name: songFox
 */
public final class ShRequest {

    final HttpUrl url;
    final String method;
    final Headers headers;
    final ShRequestBody body;
    final Object tag;

    public ShRequest(Builder builder) {
        this.url = builder.url;
        this.method = builder.method;
        this.headers = builder.headers.build();
        this.body = builder.body;
        this.tag = builder.tag != null ? builder.tag : this;
    }

    public HttpUrl url() {
        return url;
    }

    public String method() {
        return method;
    }

    public Headers headers() {
        return headers;
    }

    public String header(String name){
        return headers.get(name);
    }

    public List<String> headers(String name) {
        return headers.values(name);
    }

    public ShRequestBody body() {
        return body;
    }

    public Object tag() {
        return tag;
    }

    public Builder newBuilder() {
        return new Builder(this);
    }

    public static class Builder {
        HttpUrl url;
        String method;
        Headers.Builder headers;
        ShRequestBody body;
        Object tag;

        public Builder() {
            this.method = "GET";
            this.headers = new Headers.Builder();
        }


        public Builder(ShRequest request) {
            this.url = request.url;
            this.method = request.method;
            this.body = request.body;
            this.tag = request.tag;
            this.headers = request.headers.newBuilder();
        }

        public Builder url(HttpUrl url) {
            if (url == null) throw new NullPointerException("url == null");
            this.url = url;
            return this;
        }

        public Builder url(String url) {
            if (url == null) throw new NullPointerException("url == null");

            // Silently replace web socket URLs with HTTP URLs.
            if (url.regionMatches(true, 0, "ws:", 0, 3)) {
                url = "http:" + url.substring(3);
            } else if (url.regionMatches(true, 0, "wss:", 0, 4)) {
                url = "https:" + url.substring(4);
            }

            HttpUrl parsed = HttpUrl.parse(url);
            if (parsed == null) throw new IllegalArgumentException("unexpected url: " + url);
            return url(parsed);
        }


        public Builder url(URL url) {
            if (url == null) throw new NullPointerException("url == null");
            HttpUrl parsed = HttpUrl.get(url);
            if (parsed == null) throw new IllegalArgumentException("unexpected url: " + url);
            return url(parsed);
        }

        public Builder header(String name, String value) {
            headers.set(name, value);
            return this;
        }

        public Builder addHeader(String name, String value) {
            headers.add(name, value);
            return this;
        }

        /**
         * Removes all headers named {@code name} on this builder.
         */
        public Builder removeHeader(String name) {
            headers.removeAll(name);
            return this;
        }

        /**
         * Removes all headers on this builder and adds {@code headers}.
         */
        public Builder headers(Headers headers) {
            this.headers = headers.newBuilder();
            return this;
        }

        /**
         * Sets this request's {@code Cache-Control} header, replacing any cache control headers already
         * present. If {@code cacheControl} doesn't define any directives, this clears this request's
         * cache-control headers.
         */
        public Builder cacheControl(CacheControl cacheControl) {
            String value = cacheControl.toString();
            if (value.isEmpty()) return removeHeader("Cache-Control");
            return header("Cache-Control", value);
        }


        public Builder get() {
            return method("GET", null);
        }

        public Builder head() {
            return method("HEAD", null);
        }

        public Builder post(ShRequestBody body) {
            return method("POST", body);
        }

        public Builder delete(@Nullable ShRequestBody body) {
            return method("DELETE", body);
        }

        public Builder delete() {
            return delete(ShRequestBody.create(null, EMPTY_BYTE_ARRAY));
        }

        public Builder put(ShRequestBody body) {
            return method("PUT", body);
        }

        public Builder patch(ShRequestBody body) {
            return method("PATCH", body);
        }


        public Builder method(String method, @Nullable ShRequestBody body) {
            if (method == null) throw new NullPointerException("method == null");
            if (method.length() == 0) throw new IllegalArgumentException("method.length() == 0");
            if (body != null && !ShHttpMethod.permitsRequestBody(method)) {
                throw new IllegalArgumentException("method " + method + " must not have a request body.");
            }
            if (body == null && ShHttpMethod.requiresRequestBody(method)) {
                throw new IllegalArgumentException("method " + method + " must have a request body.");
            }
            this.method = method;
            this.body = body;
            return this;
        }

        public Builder tag(Object tag) {
            this.tag = tag;
            return this;
        }

        public ShRequest build() {
            if (url == null) throw new IllegalStateException("url == null");
            return new ShRequest(this);
        }

    }
}
