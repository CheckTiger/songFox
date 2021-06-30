package cn.sxh.songfox.mvp.ui.fragment;

import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.sxh.animation.one.AnimationFragment;
import cn.sxh.base.BaseFragment;
import cn.sxh.songfox.AppContext;
import cn.sxh.songfox.R;
import cn.sxh.songfox.adapter.TechnologyFragmentAdapter;
import cn.sxh.technology.ViewFragment;
import cn.sxh.technology.opensource.BitmapFragment;
import cn.sxh.technology.opensource.NetWorkFragment;
import cn.sxh.technology.opensource.OpenSourceFragment;

/**
 * @package-name: cn.sxh.songfox.mvp.UI.fragment
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/7/3 0003 : 19 :39
 * @project-name: songFox
 */

@RequiresApi(api = Build.VERSION_CODES.P)
public class TechnologyFragment extends BaseFragment {


    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected int getContentView() {
        return R.layout.technology_fragment_layout;
    }

    @Override
    protected void initUI(View view) {
        mTabLayout = view.findViewById(R.id.tabLayout);
        mViewPager = view.findViewById(R.id.viewPager);
    }

    @Override
    protected void initData() {
        initTabTitlesAndFragment();
    }


    private void initTabTitlesAndFragment() {
        List<String> tabTitle = Arrays.asList(AppContext.getInstance().
                getResources().getStringArray(R.array.tab_layout_titles));
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new ViewFragment());
        fragmentList.add(new OpenSourceFragment());
        fragmentList.add(new AnimationFragment());
        fragmentList.add(new NetWorkFragment());
        fragmentList.add(new BitmapFragment());
        fragmentList.add(new UtilsFragment());
        TechnologyFragmentAdapter myFragmentAdapter = new TechnologyFragmentAdapter(getFragmentManager(), fragmentList, tabTitle);

        mViewPager.setAdapter(myFragmentAdapter);

        mTabLayout.setupWithViewPager(mViewPager);
    }
}
