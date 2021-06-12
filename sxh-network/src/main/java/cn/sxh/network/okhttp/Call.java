package cn.sxh.network.okhttp;

import java.io.IOException;

/**
 * @package-name: cn.sxh.base.okhttp
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2020/4/29 0029 : 20 :32
 * @project-name: songFox
 */
public interface Call extends Cloneable {

    /**
     * 初始化Call回调 返回原始请求对象Request
     * @return
     */
    ShRequest request();


    ShResponse execute() throws IOException;

    void enqueue(CallBack responseCallBack);

    void cancel();

    boolean isExecuted();

    boolean isCanceled();

    Call clone();

    interface Factory{
        Call newCall(ShRequest request);
    }

}
