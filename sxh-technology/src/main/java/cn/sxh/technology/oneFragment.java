package cn.sxh.technology;

import android.view.View;
import android.widget.TextView;

import cn.sxh.base.BaseFragment;

public class oneFragment extends BaseFragment {


    private TextView mTextView;
    private String title;
    private int mIndex;

    public oneFragment(String title,int index) {
        this.title = title;
        this.mIndex = index;
    }

    @Override
    protected int getContentView() {
        return R.layout.one_fragment_layout;
    }

    @Override
    protected void initUI(View view) {
        mTextView = view.findViewById(R.id.animation_fragment_listView);
    }

    @Override
    protected void initData() {
        mTextView.setText(title);
    }

}
