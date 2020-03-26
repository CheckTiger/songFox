package cn.sxh.technology;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * @package-name: cn.sxh.animation
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2020/3/19 0019 : 15 :40
 * @project-name: songFox
 */
public class UiUtils {

    static public int getScreenWidthPixels(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
                .getMetrics(dm);
        return dm.widthPixels;
    }

    static public int dipToPx(Context context, int dip) {
        return (int) (dip * getScreenDensity(context) + 0.5f);
    }

    static public float getScreenDensity(Context context) {
        try {
            DisplayMetrics dm = new DisplayMetrics();
            ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
                    .getMetrics(dm);
            return dm.density;
        } catch (Exception e) {
            return DisplayMetrics.DENSITY_DEFAULT;
        }
    }
}
