package cn.sxh.network.http;

import java.util.Map;

public interface IHttpEngine {

    //get请求
    void get(String url, Map<String, Object> params, EngineCallBack callBack);

    //post请求
    void post(String url, Map<String, Object> params, EngineCallBack callBack);

    //下载文件


    //https添加证书
}
