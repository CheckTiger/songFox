package cn.sxh.animation.one;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import cn.sxh.animation.R;
import cn.sxh.base.BaseFragment;
import cn.sxh.base.RecyclerViewVerticalAdapter;
import cn.sxh.utils.ActivityManager;

/**
 * @package-name: cn.sxh.songfox.mvp.UI.fragment
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/7/3 0003 : 19 :39
 * @project-name: songFox
 */

@RequiresApi(api = Build.VERSION_CODES.P)
public class AnimationFragment extends BaseFragment implements RecyclerViewVerticalAdapter.OnRecyclerViewItemClickListener {


    private RecyclerView mRecyclerView;
    private WebView mWebView;
    private TextView mTitle;
    private RecyclerViewVerticalAdapter recyclerViewVerticalAdapter;
    private static final int ALPHA_SCALE_ROTATE_TRANSLATE = 0;
    private static final int ANDROID_ANIMATION_VIEW_INTERPOLATOR = 1;
    private static final int ANDROID_ALPHA_SCALE_ROTATE_TRANSLATE_CODE = 2;
    private static final int ANDROID_ANIMATION_VIEW_INTERPOLATOR_CODE = 3;
    private static final int ANDROID_ANIMATION_VIEW_VALUE_OBJECT = 4;
    private List<String> listUrl;

    @Override
    protected int getContentView() {
        return R.layout.animation_fragment_layout;
    }

    @Override
    protected void initUI(View view) {
        mRecyclerView = view.findViewById(R.id.animation_fragment_listView);
        mWebView = view.findViewById(R.id.wv);
        mTitle = view.findViewById(R.id.title);
        recyclerViewVerticalAdapter = new RecyclerViewVerticalAdapter(getContext());
        recyclerViewVerticalAdapter.setOnRecyclerViewItemClickListener(this);
        listUrl = Arrays.asList(getContext().
                getResources().getStringArray(R.array.animation_fragment_item_url));
        Random random = new Random();
        mWebView.loadUrl(listUrl.get(Math.abs(random.nextInt(37) % 10)));
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
    protected void createPresenter() {

    }

    @Override
    public void dispatchListener(int position, String title) {
        Log.e("sxh", position + "==============" + title);
        switch (position) {
            case ALPHA_SCALE_ROTATE_TRANSLATE:
            case ANDROID_ALPHA_SCALE_ROTATE_TRANSLATE_CODE:
                ActivityManager.gotoPage(getActivity(), AlphaScaleActivity.class);
                break;
            case ANDROID_ANIMATION_VIEW_INTERPOLATOR:
            case ANDROID_ANIMATION_VIEW_INTERPOLATOR_CODE:
                ActivityManager.gotoPage(getActivity(), InterpolatorActivity.class);
                break;
            case ANDROID_ANIMATION_VIEW_VALUE_OBJECT:
                ActivityManager.gotoPage(getActivity(), ValueAnimatorActivity.class);
                break;
            default:
                Log.e("sxh", "url==============" + listUrl.get(position - 5));
                mWebView.loadUrl(listUrl.get(position - 5));
                break;
        }
        mTitle.setText(title);
    }
}
