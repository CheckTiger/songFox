package cn.sxh.songfox.xutil;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ViewUtils {

    public static void inject(Activity activity) {
        inject(new ViewFinder(activity),activity);
    }

    public static void inject(View view,Object object) {
        inject(new ViewFinder(view),object);
    }

    public static void injectView(View view) {
        inject(new ViewFinder(view),view);
    }

    private static void inject(ViewFinder finder,Object object){
        injectFiled(finder, object);
        injectEvent(finder, object);
    }

    /**
     * 注入属性
     * @param finder
     * @param object
     */
    private static void injectFiled(ViewFinder finder, Object object) {
        //1.获取类里面的所有属性 利用反射原理
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        //2.获取ViewById里面的value值
        for (Field field : fields) {
            ViewById viewById = field.getAnnotation(ViewById.class);
            if (viewById != null) {
                int viewId = viewById.value();
                //3.findViewById 找到view
                View view = finder.findViewById(viewId);
                if (view != null) {
                    //能够注入所有的修饰符
                    field.setAccessible(true);
                    //4.动态的注入找到的view
                    try {
                        field.set(object, view);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static void injectEvent(ViewFinder finder, Object object) {
        //1.获取类里面的所有属性 利用反射原理
        Class<?> clazz = object.getClass();
        Method[] methods = clazz.getMethods();

        //2.获取ViewById里面的value值
        for (Method method : methods) {
            OnClick onClick = method.getAnnotation(OnClick.class);
            if (onClick != null) {
                int[] viewIds = onClick.value();
                for (int viewId : viewIds) {
                    //3.findViewById 找到view
                    View view = finder.findViewById(viewId);
                    if (view != null) {
                        view.setOnClickListener(new DeclaredOnClickListener(method,object));
                    }
                }
            }
        }
    }


    public static class DeclaredOnClickListener implements View.OnClickListener{
        private Object object;
        private Method mMethod;
        public DeclaredOnClickListener(Method method, Object object) {
            this.mMethod = method;
            this.object = object;
        }

        @Override
        public void onClick(View view) {
            try {
                //能够注入所有的修饰符
                mMethod.setAccessible(true);
                mMethod.invoke(object, view);
            } catch (Exception e) {
                try {
                    mMethod.invoke(object, null);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
