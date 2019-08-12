package cn.sxh.songfox.util;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * @package-name: cn.sxh.songfox.util
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/8/7 0007 : 14 :06
 * @project-name: songFox
 */
public class DpUtils {

    public static float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                Resources.getSystem().getDisplayMetrics());
    }
}
