package cn.sxh.base.webview;

import android.webkit.WebView;

import org.json.JSONObject;

public class QueryJavaScriptInterface extends BaseJavaScriptInterface {

    private static final String KEY_HANDLER_NAME = "handlername";
    private static final String KEY_SUPPORTED = "isSupported";
    private static final String KEY_HANDLER_VERSION = "version";
    private static final String KEY_SUPPORTED_YES = "yes";
    private static final String KEY_SUPPORTED_NO = "no";

    public QueryJavaScriptInterface() {
    }

    @Override
    public void onEventAction(WebView webView, String callBackId, final String message) {
        super.onEventAction(webView, callBackId, message);
        if (webView != null) {
            webView.post(new Runnable() {
                @Override
                public void run() {
                    String handlerName = QueryJavaScriptInterface.this.getHandlerName(message);
                    String handlerPath = JavaScriptInterfaceFactory.mJavaScriptInterfaces.get(handlerName);
                    String support = KEY_SUPPORTED_YES;
                    if (handlerPath == null || "".equals(handlerPath)) {
                        support = KEY_SUPPORTED_NO;
                    }
                    QueryJavaScriptInterface.this.onActionCallBack(
                            QueryJavaScriptInterface.this.createResponseJSONObject(support, handlerPath)
                    );
                }
            });
        }
    }

    private Object createResponseJSONObject(String support, String handlerPath) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(KEY_SUPPORTED, support);
            if (KEY_SUPPORTED_YES.equals(support)) {
                jsonObject.put(KEY_HANDLER_VERSION, this.getVersion(handlerPath));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }


    private String getHandlerName(String message) {

        if (message != null && !"".equals(message)) {
            String str = null;
            try {
                JSONObject jsonObject = new JSONObject(message);
                str = jsonObject.optString(KEY_HANDLER_NAME);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return str;
        } else {
            return null;
        }
    }

    private String getVersion(String handlerPath) {

        String version = JavaScriptInterfaceFactory.DEFAULT_INTERFACE_VERSION;
        if (handlerPath != null && !"".equals(handlerPath)) {
            try {
                Class<BaseJavaScriptInterface> clazz = (Class<BaseJavaScriptInterface>) Class.forName(handlerPath);
                if (clazz != null && clazz.newInstance() != null) {
                    version = clazz.newInstance().getInterfaceVersion();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return version;
        } else {
            return version;
        }
    }


}
