package cn.sxh.songfox.mvp.ui.fragment;

import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

import cn.sxh.songfox.AppContext;
import cn.sxh.songfox.R;
import cn.sxh.songfox.adapter.RecyclerViewVerticalAdapter;
import cn.sxh.songfox.base.BaseFragment;
import cn.sxh.songfox.util.AppUtil;

/**
 * @package-name: cn.sxh.songfox.mvp.UI.fragment
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/7/3 0003 : 19 :39
 * @project-name: songFox
 */

@RequiresApi(api = Build.VERSION_CODES.P)
public class TechnologyFragment extends BaseFragment {


    private RecyclerView mRecyclerView;
    private RecyclerViewVerticalAdapter recyclerViewVerticalAdapter;

    @Override
    protected int getContentView() {
        return R.layout.technology_fragment_layout;
    }

    @Override
    protected void initUI(View view) {
        mRecyclerView = view.findViewById(R.id.Technology_fragment_listView);
        recyclerViewVerticalAdapter = new RecyclerViewVerticalAdapter(AppUtil.getContext());
    }

    @Override
    protected void initData() {
        List<String> list = Arrays.asList(AppContext.getInstance().
                getResources().getStringArray(R.array.technology_fragment_item));
        LinearLayoutManager managerVertical = new LinearLayoutManager(AppUtil.getContext());
        managerVertical.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(managerVertical);
        mRecyclerView.setAdapter(recyclerViewVerticalAdapter);
        recyclerViewVerticalAdapter.setList(list);
    }
}
