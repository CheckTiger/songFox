package cn.sxh.songfox.mvp.ui.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import cn.sxh.base.BaseFragment;
import cn.sxh.songfox.R;
import cn.sxh.songfox.mvp.presenter.CoolWidgetFragmentContract;
import cn.sxh.songfox.mvp.presenter.CoolWidgetFragmentPresenterImpl;

/**
 * @package-name: cn.sxh.songfox.mvp.UI.fragment
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/8/13 0013 : 15 :58
 * @project-name: songFox
 */
public class CoolWidgetFragment extends BaseFragment<CoolWidgetFragmentPresenterImpl> implements
        CoolWidgetFragmentContract.CoolWidgetFragmentView {

    private ListView mListView;
    @Override
    protected int getContentView() {
        return R.layout.fragment_cool_widget_layout;
    }

    @Override
    protected void initUI(View view) {
        mListView = view.findViewById(R.id.weigetList);
    }

    @Override
    protected void initData() {
        mPresenter.requestWidgetList();
    }

    @Override
    protected void createPresenter() {
        mPresenter = new CoolWidgetFragmentPresenterImpl();
    }

    @Override
    public void showWidgetList(List<String> result) {
        for (int i = 0; i < result.size(); i++) {
            Log.e(TAG, "---->" + result.get(i));
        }
    }
}
