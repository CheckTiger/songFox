package cn.sxh.base.webview;

import android.os.Build;
import android.webkit.WebView;

import org.json.JSONObject;

import java.lang.ref.SoftReference;

public class BaseJavaScriptInterface implements JavaScriptInterface {
    private SoftReference<WebView> mWebViewRef = null;
    private String mCallBackId = null;
    private String mHandlerName = null;

    public BaseJavaScriptInterface() {
    }

    protected String getInterfaceVersion() {
        return JavaScriptInterfaceFactory.DEFAULT_INTERFACE_VERSION;
    }


    @Override
    public void onEventAction(WebView webView, String callBackId, String message) {

        if (webView != null && callBackId != null) {
            this.mWebViewRef = new SoftReference<>(webView);
            this.mCallBackId = callBackId;
        }
    }

    @Override
    public void onEventAction(WebView webView, String callBackId, String handlerName, String message) {
        if (webView != null) {
            this.mWebViewRef = new SoftReference<>(webView);
            this.mCallBackId = callBackId;
            this.mHandlerName = handlerName;
        }
    }

    @Override
    public void onInterfaceRemoved() {

        if (this.mWebViewRef != null) {
            synchronized (this.mWebViewRef) {
                this.mWebViewRef.clear();
                this.mWebViewRef = null;
            }
        }
        this.mCallBackId = null;
    }

    @Override
    public void onActionCallBack(Object object) {
        if (this.mWebViewRef != null && this.mWebViewRef.get() != null) {
            synchronized (this.mWebViewRef) {
                JSONObject jsonObject = new JSONObject();
                String jsonString = null;
                try {
                    jsonObject.put("responseId", this.mCallBackId);
                    jsonObject.put("handlerName", this.mHandlerName);
                    jsonObject.put("responseData", object);
                    jsonString = jsonObject.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (jsonString != null && this.mWebViewRef != null && this.mWebViewRef.get() != null) {
                    final WebView webView = this.mWebViewRef.get();
                    if (webView != null) {
                        final String finalJsonString = jsonString;
                        webView.post(new Runnable() {
                            @Override
                            public void run() {
                                if (Build.VERSION.SDK_INT < 19) {
                                    webView.loadUrl("javascript:window.WebViewJavascriptBridge._handleMessageFromObjc('" + finalJsonString + "')");
                                } else {
                                    webView.evaluateJavascript("javascript:window.WebViewJavascriptBridge._handleMessageFromObjc('" + finalJsonString + "')",null);
                                }
                            }
                        });
                    }
                }
            }
        }
    }
}
