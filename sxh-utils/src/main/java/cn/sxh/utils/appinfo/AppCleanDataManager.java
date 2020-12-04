//package cn.sxh.utils.appinfo;
//
//import android.content.Context;
//import android.os.Environment;
//
//import java.io.File;
//import java.text.DecimalFormat;
//
//public class AppCleanDataManager {
//
//    /**
//     * 清除本应用内部缓存数据(/data/data/com.xxx.xxx/cache)
//     * @param context 上下文
//     * @return void
//     */
//    public static void cleanInternalCache(Context context) {
//        AppCleanDataManager.deleteFilesByDirectory(context.getCacheDir());
//    }
//
//
//
//    /**
//     * 清除本应用外部缓存数据(/mnt/sdcard/android/data/com.xxx.xxx/cache)
//     * @param context 上下文
//     * @return void
//     */
//    public static void cleanExternalCache(Context context) {
//        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//            AppCleanDataManager.deleteFilesByDirectory(context.getExternalCacheDir());
//        }
//    }
//
//
//    /**
//     * 清除本应用所有数据库(/data/data/com.xxx.xxx/databases)
//     * @param context 上下文
//     * @return void
//     */
//    public static void cleanDatabases(Context context) {
//        AppCleanDataManager.deleteFilesByDirectory(new File("/data/data/" + context.getPackageName() + "/databases"));
//    }
//
//
//    /**
//     * 清除本应用SharedPreference(/data/data/com.xxx.xxx/shared_prefs)
//     * @param context 上下文
//     * @return void
//     */
//    public static void cleanSharedPreference(Context context) {
//        AppCleanDataManager.deleteFilesByDirectory(new File("/data/data/" + context.getPackageName() + "/shared_prefs"));
//    }
//
//
//    /**
//     * 根据名字清除本应用数据库
//     * @param context 上下文
//     * @param dbName
//     * @return void
//     */
//    public static void cleanDatabaseByName(Context context, String dbName) {
//        context.deleteDatabase(dbName);
//    }
//
//
//    /**
//     * 清除本应用files文件(/data/data/com.xxx.xxx/files)
//     * @param context 上下文
//     * @return void
//     */
//    public static void cleanFiles(Context context) {
//        AppCleanDataManager.deleteFilesByDirectory(context.getFilesDir());
//    }
//
//
//    /**
//     * 清除本应用所有的数据
//     * @param context 上下文
//     * @return int
//     */
//    public static int cleanApplicationData(Context context) {
//        //清除本应用内部缓存数据
//        cleanInternalCache(context);
//        //清除本应用外部缓存数据
//        cleanExternalCache(context);
//        //清除本应用SharedPreference
//        cleanSharedPreference(context);
//        //清除本应用files文件
//        cleanFiles(context);
//        return 1;
//    }
//
//
//    /**
//     * 获取App应用缓存的大小
//     * @param context 上下文
//     * @return String
//     */
//    public static String getAppClearSize(Context context) {
//        long clearSize = 0;
//        String fileSizeStr = "";
//        DecimalFormat df = new DecimalFormat("0.00");
//
//        //获得应用内部缓存大小
//        clearSize = AppFileMgr.getFileSize(context.getCacheDir());
//        //获得应用SharedPreference缓存数据大小
//        clearSize += AppFileMgr.getFileSize(new File("/data/data/" + context.getPackageName() + "/shared_prefs"));
//        //获得应用data/data/com.xxx.xxx/files下的内容文件大小
//        clearSize += AppFileMgr.getFileSize(context.getFilesDir());
//        //获取应用外部缓存大小
//        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//            clearSize += AppFileMgr.getFileSize(context.getExternalCacheDir());
//        }
//        if(clearSize > 5000)  {
//            //转换缓存大小Byte为MB
//            fileSizeStr = df.format((double) clearSize / 1048576) + "MB";
//        }
//        return fileSizeStr;
//    }
//}
