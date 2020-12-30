package cn.sxh.technology.opensource;

import android.view.View;
import android.widget.ListView;


import com.socks.library.KLog;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

import cn.sxh.base.BaseFragment;
import cn.sxh.base.FirstPageBean;
import cn.sxh.technology.R;
import cn.sxh.technology.presenter.NewsPresenter;
import cn.sxh.technology.view.NewsView;
import cn.sxh.utils.AppUtil;

public class OpenSourceFragment extends BaseFragment implements NewsView {

    private ListView mListView;
    private UtilsFragmentAdapter fragmentAdapter;
    private NewsPresenter presenter;

    @Override
    protected int getContentView() {
        return R.layout.open_source_fragment;
    }

    @Override
    protected void initUI(View view) {
        mListView = view.findViewById(R.id.open_source_fragment_listView);
        presenter = new NewsPresenter(this);
        presenter.requestNews();
    }

    @Override
    protected void initData() {
        List<String> list = Arrays.asList(getContext().
                getResources().getStringArray(R.array.open_source_fragment_item));
        fragmentAdapter = new UtilsFragmentAdapter(getContext(),list);
        mListView.setAdapter(fragmentAdapter);
    }

    @Override
    public void notifyHandleNewsData(@NotNull FirstPageBean news) {
        List<FirstPageBean.ContentBean> content = news.getContent();
        KLog.e(TAG, "--------->" + content.size());
        for (int i = 0; i < content.size(); i++) {
            KLog.e(TAG, "--------->" + content.get(i).getIconurl());
            KLog.e(TAG, "--------->" + content.get(i).getTitle());
            KLog.e(TAG, "--------->" + content.get(i).getUrl());
        }
    }
}
