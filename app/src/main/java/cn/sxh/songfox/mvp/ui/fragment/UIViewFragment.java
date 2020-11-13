package cn.sxh.songfox.mvp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.sxh.base.BaseFragment;
import cn.sxh.songfox.ContentAdapter;
import cn.sxh.songfox.CustomHorizontalScrollView;
import cn.sxh.songfox.Entity;
import cn.sxh.songfox.R;
import cn.sxh.songfox.TopTabAdpater;
import cn.sxh.songfox.adapter.UtilsFragmentAdapter;
import cn.sxh.songfox.mvp.presenter.UtilsFragmentPresenter;

/**
 * @package-name: cn.sxh.songfox.mvp.UI.fragment
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/7/3 0003 : 19 :40
 * @project-name: songFox
 */
public class UIViewFragment extends BaseFragment implements ContentAdapter.OnContentScrollListener {

    private static final String TAG = "UIViewFragment";
    private RecyclerView mListView;
    private List<String> list = new ArrayList<>();

    TextView tvLeftTitle;
    RecyclerView rvTabRight;
    CustomHorizontalScrollView horScrollview;
    LinearLayout llTopRoot;
    RecyclerView recyclerContent;
    private List<Entity> mEntities = new ArrayList<>();
    private List<String> rightMoveDatas = new ArrayList<>();
    private List<String> topTabs = new ArrayList<>();

    @Override
    protected int getContentView() {
        return R.layout.ui_fragment_layout;
    }

    @Override
    protected void initUI(View view) {
        llTopRoot = view.findViewById(R.id.ll_top_root);
        tvLeftTitle = view.findViewById(R.id.tv_left_title);
        rvTabRight = view.findViewById(R.id.rv_tab_right);
        horScrollview = view.findViewById(R.id.hor_scrollview);
        recyclerContent = view.findViewById(R.id.recycler_content);

        //添加Android自带的分割线
        rvTabRight.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.HORIZONTAL));
        recyclerContent.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        //处理顶部标题部分
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvTabRight.setLayoutManager(linearLayoutManager);
        TopTabAdpater topTabAdpater = new TopTabAdpater(getContext());
        rvTabRight.setAdapter(topTabAdpater);
        topTabs.add("委托/均价");
        topTabs.add("委托/成交");
        topTabs.add("状态/时间");
        topTabs.add("操作/类型");
        topTabs.add("操作/类型");
        topTabAdpater.setDatas(topTabs);
        //处理内容部分
        recyclerContent.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerContent.setHasFixedSize(true);
        final ContentAdapter contentAdapter = new ContentAdapter(getContext());
        recyclerContent.setAdapter(contentAdapter);
        contentAdapter.setOnContentScrollListener(this);


        recyclerContent.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    Entity entity = new Entity();
                    entity.setLeftTitle("腾讯控股" + i);
                    rightMoveDatas.clear();
                    for (int j = 0; j < 5; j++) {
                        rightMoveDatas.add("价格" + j);
                    }
                    entity.setRightDatas(rightMoveDatas);
                    mEntities.add(entity);
                }
                contentAdapter.setDatas(mEntities);
            }
        }, 1500);

        recyclerContent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                List<ContentAdapter.ItemViewHolder> viewHolderCacheList = contentAdapter.getViewHolderCacheList();
                if (null != viewHolderCacheList) {
                    int size = viewHolderCacheList.size();
                    for (int i = 0; i < size; i++) {
                        viewHolderCacheList.get(i).horItemScrollview.scrollTo(contentAdapter.getOffestX(), 0);
                    }
                }

            }
        });

        horScrollview.setOnCustomScrollChangeListener((listener, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            //代码重复,可以抽取/////
            List<ContentAdapter.ItemViewHolder> viewHolderCacheList = contentAdapter.getViewHolderCacheList();
            if (null != viewHolderCacheList) {
                int size = viewHolderCacheList.size();
                for (int i = 0; i < size; i++) {
                    viewHolderCacheList.get(i).horItemScrollview.scrollTo(scrollX, 0);
                }
            }
        });

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onScroll(int offestX) {
        if (null != horScrollview) horScrollview.scrollTo(offestX, 0);
    }
}
