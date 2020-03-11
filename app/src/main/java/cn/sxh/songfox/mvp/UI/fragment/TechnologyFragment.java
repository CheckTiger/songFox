package cn.sxh.songfox.mvp.UI.fragment;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import cn.sxh.songfox.R;
import cn.sxh.songfox.base.BaseFragment;

/**
 * @package-name: cn.sxh.songfox.mvp.UI.fragment
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/7/3 0003 : 19 :39
 * @project-name: songFox
 */
public class TechnologyFragment extends BaseFragment {

    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;


    @Override
    protected int getContentView() {
        return R.layout.fragment_technology;
    }

    @Override
    protected void initUI(View view) {

    }

    @Override
    protected void initData() {

    }
}
