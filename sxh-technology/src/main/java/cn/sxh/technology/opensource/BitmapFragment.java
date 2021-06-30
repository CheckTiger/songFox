package cn.sxh.technology.opensource;

import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.sxh.base.BaseFragment;
import cn.sxh.base.BitmapRecyclerViewAdapter;
import cn.sxh.base.FirstPageBean;
import cn.sxh.base.UtilsFragmentView;
import cn.sxh.technology.R;

/**
 * @package-name: cn.sxh.songfox.mvp.UI.fragment
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/8/13 0013 : 15 :58
 * @project-name: songFox
 */
public class BitmapFragment extends BaseFragment implements UtilsFragmentView {

    private static final String TAG = "BitmapFragment";
    private RecyclerView mRecyclerView;
    private List<Integer> list = new ArrayList<>();
    private BitmapRecyclerViewAdapter fragmentAdapter;

    @Override
    protected int getContentView() {
        return R.layout.bitmap_fragment_layout;
    }

    @Override
    protected void initUI(View view) {
        mRecyclerView = view.findViewById(R.id.recyclerView);
        fragmentAdapter = new BitmapRecyclerViewAdapter(getContext());
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void initData() {
        int pic003 = R.drawable.pic003;
        list.add(pic003);
        for (int i = 1; i < 100; i++) {
            list.add(pic003+i);
        }
        GridLayoutManager managerVertical = new GridLayoutManager(getContext(),3);
        managerVertical.setOrientation(GridLayoutManager.VERTICAL);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(managerVertical);
        mRecyclerView.setAdapter(fragmentAdapter);
        fragmentAdapter.setList(list);

    }



    @Override
    public void notifyDataReceive(FirstPageBean firstPageBean) {

    }
}
