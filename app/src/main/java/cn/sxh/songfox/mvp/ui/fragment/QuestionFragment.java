package cn.sxh.songfox.mvp.ui.fragment;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.sxh.songfox.AppContext;
import cn.sxh.songfox.R;
import cn.sxh.songfox.adapter.UtilsFragmentAdapter;
import cn.sxh.base.BaseFragment;

/**
 * @package-name: cn.sxh.songfox.mvp.UI.fragment
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/8/13 0013 : 15 :59
 * @project-name: songFox
 */
@RequiresApi(api = Build.VERSION_CODES.P)
public class QuestionFragment extends BaseFragment {

    private static final String TAG = "QuestionFragment";
    private ListView mListView;
    private List<String> list = new ArrayList<>();
    private UtilsFragmentAdapter fragmentAdapter;

    @Override
    protected int getContentView() {
        return R.layout.question_fragment_layout;
    }

    @Override
    protected void initUI(View view) {
        mListView = view.findViewById(R.id.tools_fragment_listView);
    }

    @Override
    protected void initData() {
        list = Arrays.asList(AppContext.getInstance().
                getResources().getStringArray(R.array.tools_fragment_item));
        fragmentAdapter = new UtilsFragmentAdapter(getContext(),list);
        mListView.setAdapter(fragmentAdapter);
    }
    private void gotoActivity(int position) {
        Log.e(TAG, "--------->" + position);
    }
}
