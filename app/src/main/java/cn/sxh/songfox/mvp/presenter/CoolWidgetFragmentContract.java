package cn.sxh.songfox.mvp.presenter;

import java.util.List;

import cn.sxh.base.BasePresenter;
import cn.sxh.base.BaseView;

public interface CoolWidgetFragmentContract {

    interface CoolWidgetFragmentView extends BaseView{
        //展示控件列表
        void showWidgetList(List<String> result);
    }

    interface CoolWidgetFragmentPresenter extends BasePresenter<CoolWidgetFragmentView>{
        //请求控件列表
        void requestWidgetList();
    }
}
