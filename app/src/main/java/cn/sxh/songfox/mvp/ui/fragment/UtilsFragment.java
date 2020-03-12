package cn.sxh.songfox.mvp.ui.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.sxh.songfox.AppContext;
import cn.sxh.songfox.R;
import cn.sxh.songfox.adapter.UtilsFragmentAdapter;
import cn.sxh.songfox.base.BaseFragment;
import cn.sxh.songfox.base.UtilsFragmentView;
import cn.sxh.songfox.bean.FirstPageBean;
import cn.sxh.songfox.mvp.presenter.UtilsFragmentPresenter;

/**
 * @package-name: cn.sxh.songfox.mvp.UI.fragment
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/8/13 0013 : 15 :58
 * @project-name: songFox
 */
public class UtilsFragment extends BaseFragment implements UtilsFragmentView {

    private static final String TAG = "UtilsFragment";
    private ListView mListView;
    private List<String> list = new ArrayList<>();
    private UtilsFragmentAdapter fragmentAdapter;
    private UtilsFragmentPresenter fragmentPresenter;

    @Override
    protected int getContentView() {
        return R.layout.tools_fragment_layout;
    }

    @Override
    protected void initUI(View view) {
        mListView = view.findViewById(R.id.tools_fragment_listView);
        fragmentPresenter = new UtilsFragmentPresenter(this);
    }

    @Override
    protected void initData() {
        list = Arrays.asList(AppContext.getInstance().
                getResources().getStringArray(R.array.tools_fragment_item));
        fragmentAdapter = new UtilsFragmentAdapter(getContext(),list);
        mListView.setAdapter(fragmentAdapter);
        fragmentAdapter.setOnLinearLayoutListener((holder, position)
                -> gotoActivity(position));
        fragmentPresenter.getFirstPageData();
    }

    private void gotoActivity(int position) {
        Log.e(TAG, "--------->" + position);
        fragmentPresenter.getFirstPageData();
    }

    @Override
    public void notifyDataReceive(FirstPageBean firstPageBean) {
        Log.e(TAG, "--------->" + firstPageBean.getTime());
        List<FirstPageBean.ContentBean> content = firstPageBean.getContent();
        for (int i = 0; i < content.size(); i++) {
            Log.e(TAG, "--------->" + content.get(i).getPosition());
            Log.e(TAG, "--------->" + content.get(i).getIconurl());
            Log.e(TAG, "--------->" + content.get(i).getTitle());
            Log.e(TAG, "--------->" + content.get(i).getUrl());
            Log.e(TAG, "--------->" + content.get(i).getTjid());
        }

    }
}
