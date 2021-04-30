package cn.sxh.network.http;

public interface EngineCallBack {

     void onError(Exception e);

     void onSuccess(String result);

     EngineCallBack DEFAULT_CALL_BACK = new EngineCallBack() {
         @Override
         public void onError(Exception e) {

         }

         @Override
         public void onSuccess(String result) {

         }
     };
}
