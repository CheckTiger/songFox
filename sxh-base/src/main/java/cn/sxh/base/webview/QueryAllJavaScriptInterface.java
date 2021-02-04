package cn.sxh.base.webview;

import android.webkit.WebView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Set;

public class QueryAllJavaScriptInterface extends BaseJavaScriptInterface {

    private static final String KEY_HANDLER_NAME = "handlername";

    public QueryAllJavaScriptInterface() {
    }

    @Override
    public void onEventAction(WebView webView, String callBackId, String message) {
        super.onEventAction(webView, callBackId, message);
        this.onActionCallBack(this.getAllInterfaceName());
    }

    private JSONArray getAllInterfaceName() {
        Set<String> keySet = JavaScriptInterfaceFactory.mJavaScriptInterfaces.keySet();
        JSONArray jsonArray = new JSONArray();
        JSONObject tempJsonObject = null;
        if (keySet != null) {
            try {
                Iterator iterator = keySet.iterator();
                while (iterator.hasNext()) {
                    String key = (String) iterator.next();
                    tempJsonObject = new JSONObject();
                    tempJsonObject.put("handlername", key);
                    jsonArray.put(tempJsonObject);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return jsonArray;
    }
}
