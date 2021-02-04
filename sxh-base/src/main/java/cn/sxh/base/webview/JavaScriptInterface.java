package cn.sxh.base.webview;

import android.webkit.WebView;

public interface JavaScriptInterface {

    void onEventAction(WebView var1, String var2, String var3);

    void onEventAction(WebView var1, String var2, String var3,String var4);

    void onInterfaceRemoved();

    void onActionCallBack(Object var1);
}
