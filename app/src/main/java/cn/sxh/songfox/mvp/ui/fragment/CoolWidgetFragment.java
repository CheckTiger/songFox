package cn.sxh.songfox.mvp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import cn.sxh.base.BaseFragment;

/**
 * @package-name: cn.sxh.songfox.mvp.UI.fragment
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/8/13 0013 : 15 :58
 * @project-name: songFox
 */
public class CoolWidgetFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(getContext());
        textView.setText("炫酷控件");
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
