package cn.sxh.network;

public interface HttpCallBack<T> {

     T onSuccess(Object T);

     T onFail(Object T);
}
