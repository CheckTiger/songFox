package cn.sxh.technology;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;

import cn.sxh.base.BaseFragment;

public class oneFragment extends BaseFragment {


    private TextView mTextView;
    private String title;
    private PDFView mPDFView;
    private int mIndex;
    private DownLoadPdf downLoadPdf;

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
        mPDFView = view.findViewById(R.id.pdf_content);
        downLoadPdf = new DownLoadPdf();
    }

    @Override
    protected void initData() {
        mTextView.setText(title);

        mTextView.setOnClickListener(v -> {
            downLoadPdf.setContextAndUrl(getActivity(),
                    "https://tradeuat1.hrif.com.hk/aos/a_common/a_ajax/downloadPDF.php?fileName=1AOSFORM&fileType=PDF");
            downLoadPdf.handlePdf();
        });

        final int myPage = 0;
        //选择pdf
        mPDFView.fromAsset("android.pdf")
//                .pages(0, 2, 3, 4, 5); // 把0 , 2 , 3 , 4 , 5 过滤掉
                //是否允许翻页，默认是允许翻页
                .enableSwipe(true)
                //pdf文档翻页是否是垂直翻页，默认是左右滑动翻页
                .swipeHorizontal(false)
                //
                .enableDoubletap(false)
                //设置默认显示第0页
                .defaultPage(myPage)
                .onLoad(new OnLoadCompleteListener() {
                    @Override
                    public void loadComplete(int nbPages) {
                        Log.e(TAG, "loadComplete: "+nbPages);
                    }
                })
                //设置翻页监听
                .onPageChange(new OnPageChangeListener() {

                    @Override
                    public void onPageChanged(int page, int pageCount) {
                        Log.e(TAG, "onPageChanged: "+page);
                        Log.e(TAG, "onPageChanged: "+pageCount);
                    }
                })
                // 渲染风格（就像注释，颜色或表单）
                .enableAnnotationRendering(false)
                .password(null)
                .scrollHandle(null)
                // 改善低分辨率屏幕上的渲染
                .enableAntialiasing(true)
                // 页面间的间距。定义间距颜色，设置背景视图
                .spacing(0)
                .load();

    }

    @Override
    protected void createPresenter() {

    }

}
