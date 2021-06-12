package cn.sxh.technology;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;


/**
 * 只下载PDf
 */

public class DownLoadPdf implements DownloadUtil.OnDownloadListener {


    private static final String FOLDER = "pdf";
    private final static String FILETYPE_PDF = "pdf";
    // 事件通知状态
    private static final int MESSAGE_WHAT_LOADFINISH = 2;                     // 下载完成
    private static final int MESSAGE_WHAT_LOADERROR = 4;// 下载错误
    private static final int MESSAGE_WHAT_LOADPROGRESS = 3;//下载中
    private static final int MESSAGE_WHAT_LOADPROGRESS_OTHER = 5;//下载中

    private static final long TOTALSIZE = 15 * 1024 * 1024;      // 文件存储大小为15M
    //---------------变量区域--------------
    private String filepath;                                         // 文件存储路径
    private String fileName = null;                  // 文件下载名称
    private String mUrl;
    private Activity context;
    private String callType;
    public DownloadUtil downloadUtil;
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == MESSAGE_WHAT_LOADFINISH) {
                openPdfFile(filepath + File.separator + fileName);
            }
        }

    };

    public void setContextAndUrl(Activity context, String mUrl) {
        this.context = context;
        this.mUrl = mUrl;
        downloadUtil = DownloadUtil.get();

    }

    public void handlePdf() {
        File sdcardDir = context.getExternalCacheDir();

        // 判断文件是否已超过15M了
        filepath = sdcardDir.getPath() + File.separator + FOLDER;
        if (judgeFileSizeAboveMax()) {
            // 保留最近下载的一半文件
            deleteHalfFiles();
        }
//        fileName = HexinUtils.createFileName(mUrl, "." + FILETYPE_PDF);
        fileName = "huarong" + "." + FILETYPE_PDF;
        String pdfName = filepath + File.separator + fileName;
        File fileDir = new File(filepath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        File file = new File(pdfName);
        if (file.exists() && file.isFile() && file.length() > 0) {
            //不支持断点下载
            file.delete();
        }
        callType = fileName;
        downloadUtil.download(mUrl, filepath, fileName, callType, this);
    }

    /**
     * 打开PDF文件
     *
     * @param fileName
     * @return
     */
    public boolean openPdfFile(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            Intent intent = new Intent();
            intent.setDataAndType(Uri.fromFile(file), "application/pdf");
            intent.setClass(context, PDFViewActivity.class);
            context.startActivity(intent);
            return true;
        }
        return false;
    }




    /**
     * 打开PDF文件
     *
     * @param fileName
     * @return
     */
    public boolean openPdfInputStream(Context context, InputStream fileName) {
        Intent intent = new Intent();
        byte[] bytes = fileName.toString().getBytes();
        intent.putExtra("pdf",bytes);
        intent.setClass(context, PDFViewActivity.class);
        context.startActivity(intent);
        return true;
    }




    /**
     * 获取下载文件是否已大于15M
     *
     * @return
     */
    private boolean judgeFileSizeAboveMax() {
        File file = new File(filepath);
        if (file.exists()) {
            long totalSize = 0;
            File[] pdfFiles = file.listFiles();
            for (File pdfFile : pdfFiles) {
                if (pdfFile.isFile()) {
                    totalSize += getFileSize(pdfFile);
                }
            }
            if (totalSize > TOTALSIZE) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取文件的大小
     *
     * @param file
     * @return 文件大小
     */
    private long getFileSize(File file) {
        FileInputStream fis = null;
        long fileSize = 0;
        try {
            fis = new FileInputStream(file);
            fileSize = fis.available();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileSize;
    }

    /**
     * 删除缓存中一般的文件
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private void deleteHalfFiles() {
        File file = new File(filepath);
        if (file.exists() && file.isDirectory()) {
            File[] pdfFiles = file.listFiles();
            // 假如文件只有1个,并且文件大于15M，则直接删除
            if (pdfFiles.length == 1 && getFileSize(pdfFiles[0]) > TOTALSIZE) {
                pdfFiles[0].delete();
            } else {
                // 先通过treemap对文件的进行排序
                TreeMap<Long, File> fileTree = new TreeMap<Long, File>(new Comparator() {

                    public int compare(Object o1, Object o2) {
                        if (o1 == null || o2 == null) {
                            return 0;
                        }
                        return (int) (((Long) o1) - ((Long) o2));
                    }
                });
                long fileTime = 0;
                for (File pdfFile : pdfFiles) {
                    fileTime = pdfFile.lastModified();
                    fileTree.put(fileTime, pdfFile);
                }
                // 删除一半的文件
                int needDeleteSize = fileTree.size() / 2;
                Set<Long> fileTimeSet = fileTree.keySet();
                Iterator<Long> fileTimeIterator = fileTimeSet.iterator();
                int index = 0;
                while (fileTimeIterator.hasNext() && index < needDeleteSize) {
                    long time = fileTimeIterator.next();
                    File delFile = fileTree.get(time);
                    if (delFile != null) delFile.delete();
                    index++;
                }

            }
        }
    }


    @Override
    public void onDownloadSuccess() {
        // 下载完成
        mHandler.sendEmptyMessage(MESSAGE_WHAT_LOADFINISH);
    }

    @Override
    public void onDownloading(final int progress) {

        Message message = Message.obtain();
        message.obj = progress;
        message.what = MESSAGE_WHAT_LOADPROGRESS;
        mHandler.sendMessage(message);
    }

    @Override
    public void onDownloadFailed() {
        Message message = Message.obtain();
        message.obj = "文件下载失败";
        message.what = MESSAGE_WHAT_LOADERROR;
        mHandler.sendMessage(message);
    }


    public void destroy() {
        if (mHandler != null)
            mHandler.removeCallbacksAndMessages(null);
        if (downloadUtil != null)
            downloadUtil.cancelCall(callType);
    }
}
