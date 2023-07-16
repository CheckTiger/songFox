package cn.sxh.network.socket;

import java.util.HashMap;

public class WSClient implements IWSClient, SocketCallback {

    private static WSClient instance;
    public static WSClient getInstance(){
        if (instance == null) {
            synchronized (WSClient.class) {
                if (instance == null) {
                    instance = new WSClient();
                }
            }
        }
        return instance;
    }

    @Override
    public void prepareBeforeConnectServer() {
        //{"zone":"8","time":"1660871218969","lang":"cn","user":"1835729563","version":"1.0.0"}
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("zone", 8);
        hashMap.put("time", System.currentTimeMillis() / 1000);
        hashMap.put("lang", "cn");
        hashMap.put("user", APIConstant.USERNAME);
        hashMap.put("version", "1.0.0");
        APIManager.getInstance().requestAuthorization(APIConstant.SERVER_IP +
                APIConstant.HC_FIRST_AUTHORIZATION, hashMap, this);
    }

    @Override
    public void startConnectServer() {

    }

    @Override
    public void disconnectServer() {

    }

    @Override
    public void onError(Exception e) {

    }

    @Override
    public void onSuccess(String result) {

    }
}
