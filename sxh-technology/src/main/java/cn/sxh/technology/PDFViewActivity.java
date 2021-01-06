package cn.sxh.technology;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

public class PDFViewActivity extends Activity implements OnPageChangeListener,OnLoadCompleteListener, OnPageErrorListener {

    private PDFView pdfView;
    private static final String TAG = "cn.sxh.technology.PDFViewActivity";
    private final static int REQUEST_CODE = 44;
    private final static int REQUEST_PERMISSION_CODE = 212121;//请求权限



    private Uri fileUri;
    private String path;
    Integer pageNumbers = 0;//当前页

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_show_pdf_conten);
        initView();
        showFile();

    }

    void initView(){
        pdfView = findViewById(R.id.pdf_view);
        pdfView.setBackgroundColor(Color.GRAY);
    }




    void showFile(){
        fileUri = getIntent().getData();
        path = getIntent().getStringExtra("path");
        displayFromUri(fileUri);
    }

    public String getFileName(Uri uri){
        String result = null;
        if (uri.getScheme().equals("content")){
            Cursor cursor = this.getContentResolver().query(uri,null,null,null,null);
            try {
                if (cursor!=null&&cursor.moveToFirst()){
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            }catch (Exception e){
                e.printStackTrace();
            } finally{
                if (cursor!=null){
                    cursor.close();
                }
            }
        }
        if (result == null){
            result  =  uri.getLastPathSegment();
        }
        return result;

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE){
            if (grantResults.length>0&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
                displayFromUri(fileUri);
            }

        }
    }


    private void displayFromUri(Uri uri){
        if (uri == null)
            return;
            pdfView .fromUri(uri)
                .defaultPage(pageNumbers)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(this))
                .spacing(10) //间距
                .onPageError(this)
                .load();//加载PDF

    }


    @Override
    public void loadComplete(int nbPages) {

    }

    @Override
    public void onPageChanged(int page, int pageCount) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onPageError(int page, Throwable t) {

    }
}
