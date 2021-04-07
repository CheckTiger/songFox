package cn.sxh.animation.one;

import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

import cn.sxh.animation.R;
import cn.sxh.base.BaseFragment;
import cn.sxh.base.RecyclerViewVerticalAdapter;

/**
 * @package-name: cn.sxh.songfox.mvp.UI.fragment
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/7/3 0003 : 19 :39
 * @project-name: songFox
 */

@RequiresApi(api = Build.VERSION_CODES.P)
public class AnimationFragment extends BaseFragment implements RecyclerViewVerticalAdapter.OnRecyclerViewItemClickListener{


    private RecyclerView mRecyclerView;
    private RecyclerViewVerticalAdapter recyclerViewVerticalAdapter;
    private static final int ALPHA_SCALE_ROTATE_TRANSLATE = 0;

    @Override
    protected int getContentView() {
        return R.layout.animation_fragment_layout;
    }

    @Override
    protected void initUI(View view) {
        mRecyclerView = view.findViewById(R.id.animation_fragment_listView);
        recyclerViewVerticalAdapter = new RecyclerViewVerticalAdapter(getContext());
        recyclerViewVerticalAdapter.setOnRecyclerViewItemClickListener(this);
    }


    @Override
    protected void initData() {
        List<String> list = Arrays.asList(getContext().
                getResources().getStringArray(R.array.animation_fragment_item));
        LinearLayoutManager managerVertical = new LinearLayoutManager(getContext());
        managerVertical.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(managerVertical);
        mRecyclerView.setAdapter(recyclerViewVerticalAdapter);
        recyclerViewVerticalAdapter.setList(list);
    }

    @Override
    public void dispatchListener(int position, String title) {
        Log.e("sxh", "========================" + position);
        Log.e("sxh", "========================" + title);
        switch (position) {
            case ALPHA_SCALE_ROTATE_TRANSLATE:
                Intent intent = new Intent(getActivity(), animation_alpha_scale.class);
                startActivity(intent);
                break;
        }
    }
}
