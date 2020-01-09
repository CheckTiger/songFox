package cn.sxh.songfox.pattern.proxypattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @package-name: cn.sxh.songfox.pattern.proxypattern
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2020/1/9 0009 : 11 :09
 * @project-name: songFox
 */
public class DynamicProxy implements InvocationHandler {
    private Object object;

    public DynamicProxy(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(object, args);
        return result;
    }
}
