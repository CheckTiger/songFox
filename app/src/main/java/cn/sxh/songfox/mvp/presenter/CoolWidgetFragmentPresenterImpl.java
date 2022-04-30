package cn.sxh.songfox.mvp.presenter;

import java.util.ArrayList;
import java.util.List;

public class CoolWidgetFragmentPresenterImpl implements CoolWidgetFragmentContract.
        CoolWidgetFragmentPresenter {

    private CoolWidgetFragmentContract.CoolWidgetFragmentView view;

    @Override
    public void requestWidgetList() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("控件" + i);
        }

        view.showWidgetList(list);
    }

    @Override
    public void attachView(CoolWidgetFragmentContract.CoolWidgetFragmentView view) {
        this.view = view;
    }

    @Override
    public void unAttachView() {

        if (view != null) {
            view = null;
        }
    }
}
