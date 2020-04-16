package cn.sxh.technology.opensource;

import android.view.MotionEvent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

import cn.sxh.base.BaseFragment;
import cn.sxh.technology.RecyclerViewVerticalAdapter;
import cn.sxh.technology.R;

public class OpenSourceFragment extends BaseFragment implements View.OnTouchListener {

    private RecyclerView mRecyclerView;
    private RecyclerViewVerticalAdapter recyclerViewVerticalAdapter;

    @Override
    protected int getContentView() {
        return R.layout.open_source_fragment;
    }

    @Override
    protected void initUI(View view) {
        mRecyclerView = view.findViewById(R.id.open_source_fragment_listView);
        recyclerViewVerticalAdapter = new RecyclerViewVerticalAdapter(getContext());
        recyclerViewVerticalAdapter.setItemType(1);
        mRecyclerView.setOnTouchListener(this);
    }

    @Override
    protected void initData() {
        List<String> list = Arrays.asList(getContext().
                getResources().getStringArray(R.array.open_source_fragment_item));
        LinearLayoutManager managerVertical = new LinearLayoutManager(getContext());
        managerVertical.setOrientation(LinearLayoutManager.VERTICAL);
//        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(managerVertical);
        mRecyclerView.setAdapter(recyclerViewVerticalAdapter);
        recyclerViewVerticalAdapter.setList(list);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v instanceof RecyclerView) {
            mRecyclerView.setFocusableInTouchMode(false);
        }
        return false;
    }
}
