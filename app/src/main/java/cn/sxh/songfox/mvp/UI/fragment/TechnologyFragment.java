package cn.sxh.songfox.mvp.UI.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.sxh.songfox.base.BaseFragment;

/**
 * @package-name: cn.sxh.songfox.mvp.UI.fragment
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/7/3 0003 : 19 :39
 * @project-name: songFox
 */
public class TechnologyFragment extends BaseFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(getContext());
        textView.setText("技术天地");
        return textView;
    }

    @Override
    protected int getContentView() {
        return 0;
    }

    @Override
    protected void initUI(View view) {

    }

    @Override
    protected void initData() {

    }
}
