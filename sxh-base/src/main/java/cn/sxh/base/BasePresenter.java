package cn.sxh.base;

public interface BasePresenter<T extends BaseView> {


    void attachView(T view);

    void unAttachView();
}
