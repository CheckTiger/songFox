package cn.sxh.technology.opensource;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import cn.sxh.base.BaseFragment;
import cn.sxh.technology.R;

public class OpenSourceFragment extends BaseFragment  {

    private ListView mListView;
    private UtilsFragmentAdapter fragmentAdapter;


    @Override
    protected int getContentView() {
        return R.layout.open_source_fragment;
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initUI(View view) {
        mListView = view.findViewById(R.id.open_source_fragment_listView);
    }

    @Override
    protected void initData() {
        List<String> list = Arrays.asList(getContext().
                getResources().getStringArray(R.array.open_source_fragment_item));
        fragmentAdapter = new UtilsFragmentAdapter(getContext(),list);
        mListView.setAdapter(fragmentAdapter);
    }

    @Override
    protected void createPresenter() {

    }


}
