package cn.sxh.songfox.pattern.proxypattern;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import cn.sxh.common.annotation.ClassInfo;
import cn.sxh.common.annotation.FieldInfo;
import cn.sxh.common.annotation.MethodInfo;
import cn.sxh.common.TestAnnotation;

/**
 * @package-name: cn.sxh.songfox.pattern.proxypattern
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2020/1/8 0008 : 17 :18
 * @project-name: songFox
 */
public class Client {
    public static void main(String[] args) {
        ILawsuit song = new snow();
        //===============================================================
//        ILawsuit lawyer = new Lawyer(song);//静态代理实现方式
        //===============================================================
        DynamicProxy proxy = new DynamicProxy(song);//动态代理实现方式
        ClassLoader loader = proxy.getClass().getClassLoader();
        ILawsuit lawyer = (ILawsuit) Proxy.newProxyInstance(loader, new Class[]{ILawsuit.class}, proxy);
        //===============================================================
        lawyer.submit();
        lawyer.burden();
        lawyer.defend();
        lawyer.finish();

        testRuntimeAnnotation();

    }


    private static void testRuntimeAnnotation() {
        StringBuffer sb = new StringBuffer();
        Class<?> cls = TestAnnotation.class;
        Constructor<?>[] constructors = cls.getConstructors();
        // 获取指定类型的注解
        sb.append("Class注解：").append("\n");
        ClassInfo classInfo = cls.getAnnotation(ClassInfo.class);
        if (classInfo != null) {
            sb.append(Modifier.toString(cls.getModifiers())).append(" ")
                    .append(cls.getSimpleName()).append("\n");
            sb.append("注解值: ").append(classInfo.table()).append("\n\n");
        }

        sb.append("Field注解：").append("\n");
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            FieldInfo fieldInfo = field.getAnnotation(FieldInfo.class);
            if (fieldInfo != null) {
                sb.append(Modifier.toString(field.getModifiers())).append(" ")
                        .append(field.getType().getSimpleName()).append(" ")
                        .append(field.getName()).append("\n");
                sb.append("注解值: ").append(fieldInfo.column()).append("\n\n");
            }
        }

        sb.append("Method注解：").append("\n");
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            MethodInfo methodInfo = method.getAnnotation(MethodInfo.class);
            if (methodInfo != null) {
                sb.append(Modifier.toString(method.getModifiers())).append(" ")
                        .append(method.getReturnType().getSimpleName()).append(" ")
                        .append(method.getName()).append("\n");
                sb.append("注解值: ").append("\n");
                sb.append("name: ").append(methodInfo.name()).append("\n");
                sb.append("data: ").append(methodInfo.data()).append("\n");
                sb.append("age: ").append(methodInfo.age()).append("\n");
            }
        }

        System.out.print(sb.toString());

        int[] result = largeSumAfterKNegations(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 7);
        System.out.print(Arrays.toString(result));
    }


    public static int[] largeSumAfterKNegations(int[] nums, int target) {
        if (nums.length <= 0) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }


}
