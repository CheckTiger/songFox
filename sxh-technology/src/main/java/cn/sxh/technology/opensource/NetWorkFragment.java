package cn.sxh.technology.opensource;

import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.sxh.base.BaseFragment;
import cn.sxh.technology.R;

public class NetWorkFragment extends BaseFragment {

    private static final String TAG = "QuestionFragment";
    private ListView mListView;
    private List<String> list = new ArrayList<>();
    private UtilsFragmentAdapter fragmentAdapter;

    @Override
    protected int getContentView() {
        return R.layout.network_fragment_layout;
    }

    @Override
    protected void initUI(View view) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void createPresenter() {

    }
}
