package cn.sxh.songfox.mvp.ui.fragment;

import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.sxh.base.BaseFragment;
import cn.sxh.songfox.R;
import cn.sxh.songfox.adapter.CoolWidgetAdapter;
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
    private CoolWidgetAdapter coolWidgetAdapter;
    private List<String> name = new ArrayList<>();

    @Override
    protected int getContentView() {
        return R.layout.fragment_cool_widget_layout;
    }

    @Override
    protected void initUI(View view) {
        mListView = view.findViewById(R.id.weigetList);
        coolWidgetAdapter = new CoolWidgetAdapter(getContext(), name);
        mListView.setAdapter(coolWidgetAdapter);
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
        this.name.clear();
        this.name.addAll(result);
        coolWidgetAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.requestWidgetList();
    }
}
