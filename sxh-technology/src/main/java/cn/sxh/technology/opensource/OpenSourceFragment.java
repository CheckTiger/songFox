package cn.sxh.technology.opensource;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ListView;

import com.socks.library.KLog;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

import cn.sxh.base.BaseFragment;
import cn.sxh.base.FirstPageBean;
import cn.sxh.network.NetWorkApi;
import cn.sxh.network.bean.ThsNewsBean;
import cn.sxh.network.http.HttpCallBack;
import cn.sxh.network.http.HttpEngine;
import cn.sxh.network.http.HttpUtils;
import cn.sxh.network.observer.BaseObserver;
import cn.sxh.network.songFoxApi;
import cn.sxh.technology.R;
import cn.sxh.technology.presenter.NewsPresenter;
import cn.sxh.technology.view.NewsView;

public class OpenSourceFragment extends BaseFragment implements NewsView {

    private ListView mListView;
    private UtilsFragmentAdapter fragmentAdapter;
    private NewsPresenter presenter;

    @Override
    protected int getContentView() {
        return R.layout.open_source_fragment;
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initUI(View view) {
        mListView = view.findViewById(R.id.open_source_fragment_listView);
        presenter = new NewsPresenter(this);
        presenter.requestNews();
        HttpUtils.with(getContext()).exchangeEngine(new HttpEngine());
        HttpUtils.with(getContext()).host("http://www.baidu.com")
                .execute(new HttpCallBack() {
                    @Override
                    public void onError(Exception e) {

                    }

                    @Override
                    public void onSuccess(String result) {

                    }
                });
        NetWorkApi.getService(songFoxApi.class).getThsNews()
                .compose(NetWorkApi.applySchedulers(new BaseObserver<ThsNewsBean>() {
                    @Override
                    public void onSuccess(ThsNewsBean thsNewsBean) {
                        List<ThsNewsBean.ContentBean> content = thsNewsBean.getContent();
                        KLog.e(TAG, "--------->" + content.size());
                        for (int i = 0; i < content.size(); i++) {
                            KLog.e(TAG, "--------->" + content.get(i).getIconurl());
                            KLog.e(TAG, "--------->" + content.get(i).getTitle());
                            KLog.e(TAG, "--------->" + content.get(i).getUrl());
                        }
                    }

                    @Override
                    public void onFailure(@Nullable Throwable e) {

                    }
                }));
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
//        KLog.e(TAG, "--------->" + content.size());
//        for (int i = 0; i < content.size(); i++) {
//            KLog.e(TAG, "--------->" + content.get(i).getIconurl());
//            KLog.e(TAG, "--------->" + content.get(i).getTitle());
//            KLog.e(TAG, "--------->" + content.get(i).getUrl());
//        }
    }
}
