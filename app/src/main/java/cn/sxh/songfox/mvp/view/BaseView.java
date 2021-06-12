package cn.sxh.songfox.mvp.view;

import com.trello.rxlifecycle.LifecycleTransformer;

public interface BaseView {

    <T> LifecycleTransformer<T> bindToLife();

}
