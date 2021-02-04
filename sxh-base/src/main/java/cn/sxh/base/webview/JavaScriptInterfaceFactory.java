package cn.sxh.base.webview;

import java.util.HashMap;

public class JavaScriptInterfaceFactory {

    public static HashMap<String, String> mJavaScriptInterfaces = new HashMap<>();
    public static String DEFAULT_INTERFACE_VERSION = "1.0";

    public JavaScriptInterfaceFactory() {
    }

    static{
        mJavaScriptInterfaces.put("jse_queryAll", "cn.sxh.base.webview.QueryAllJavaScriptInterface");
        mJavaScriptInterfaces.put("jse_query", "cn.sxh.base.webview.QueryJavaScriptInterface");
    }
}
