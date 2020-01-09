package cn.sxh.songfox.pattern.proxypattern;

import java.lang.reflect.Proxy;

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
    }
}
