package cn.sxh.songfox.mvp.presenter;

import java.util.Arrays;
import java.util.List;

import cn.sxh.songfox.AppContext;
import cn.sxh.songfox.R;

public class CoolWidgetFragmentPresenterImpl implements CoolWidgetFragmentContract.
        CoolWidgetFragmentPresenter {

    private CoolWidgetFragmentContract.CoolWidgetFragmentView view;


    @Override
    public void requestWidgetList() {
        List<String> list = Arrays.asList(AppContext.getInstance().
                getResources().getStringArray(R.array.all_defined_view));
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
