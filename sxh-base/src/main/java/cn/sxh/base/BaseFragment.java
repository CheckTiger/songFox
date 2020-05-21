package cn.sxh.base;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @package-name: cn.sxh.songfox.base
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/7/3 0003 : 15 :12
 * @project-name: songFox
 */
public abstract class BaseFragment extends Fragment {
    protected static final String TAG = BaseFragment.class.getSimpleName();
    protected View mRootView;//根布局
    private boolean isVisible;//控制dialog的显示与关闭

    /**
     * 获取布局
     * @return
     */
    protected abstract  int getContentView();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,"-------------------onCreate----------->>>>>>");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isVisible = true;
        mRootView = inflater.inflate(getContentView(),container,false);
        Log.e(TAG,"-------------------onCreateView----------->>>>>>");
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
        initData();
        Log.e(TAG,"-------------------onViewCreated----------->>>>>>");
    }

    protected abstract void initUI(View view);
    protected abstract void initData();

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
