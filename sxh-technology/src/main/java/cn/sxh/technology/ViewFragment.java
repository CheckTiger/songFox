package cn.sxh.technology;

import android.os.Build;
import android.util.Log;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

import cn.sxh.base.RecyclerViewVerticalAdapter;

import cn.sxh.base.BaseFragment;

/**
 * @package-name: cn.sxh.songfox.mvp.UI.fragment
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/7/3 0003 : 19 :39
 * @project-name: songFox
 */

@RequiresApi(api = Build.VERSION_CODES.P)
public class ViewFragment extends BaseFragment implements RecyclerViewVerticalAdapter.OnRecyclerViewItemClickListener {


    private RecyclerView mRecyclerView;
    private RecyclerViewVerticalAdapter recyclerViewVerticalAdapter;

    private oneFragment fragment;

    @Override
    protected int getContentView() {
        return R.layout.view_fragment_layout;
    }

    @Override
    protected void initUI(View view) {
        mRecyclerView = view.findViewById(R.id.view_fragment_listView);
        recyclerViewVerticalAdapter = new RecyclerViewVerticalAdapter(getContext());
        recyclerViewVerticalAdapter.setOnRecyclerViewItemClickListener(this);
    }


    @Override
    protected void initData() {
        List<String> list = Arrays.asList(getContext().
                getResources().getStringArray(R.array.technology_fragment_item));
        LinearLayoutManager managerVertical = new LinearLayoutManager(getContext());
        managerVertical.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(managerVertical);
        mRecyclerView.setAdapter(recyclerViewVerticalAdapter);
        recyclerViewVerticalAdapter.setList(list);
    }

    @Override
    public void dispatchListener(int position) {
        Log.e("sxh", "点击的item位置：" + position);
        FragmentManager manager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        hideTagFragment(fragmentTransaction);
        switch (position) {
            case 0:
                if (fragment == null) {
                    fragment = new oneFragment();
                    fragmentTransaction.add(R.id.view_content, fragment);
                } else {
                    fragmentTransaction.show(fragment);
                }
                break;
        }
        fragmentTransaction.commit();
    }


    private void hideTagFragment(FragmentTransaction transaction) {
        if (fragment != null) {
            transaction.hide(fragment);
        }
    }

}
