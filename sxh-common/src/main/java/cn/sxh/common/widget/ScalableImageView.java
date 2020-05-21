package cn.sxh.common.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import cn.sxh.common.R;
import cn.sxh.utils.DpUtils;


/**
 * @package-name: cn.sxh.songfox.widget
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/12/3 0003 : 14 :46
 * @project-name: songFox
 */
public class ScalableImageView extends View {

    public static final float IMAGE_WIDTH = DpUtils.dp2px(300);
    Bitmap bitmap;
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public ScalableImageView(Context context) {
        super(context);
    }

    public ScalableImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dialog_loading);
    }
}
