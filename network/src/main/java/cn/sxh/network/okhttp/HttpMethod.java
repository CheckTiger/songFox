package cn.sxh.network.okhttp;

/**
 * @package-name: cn.sxh.base.okhttp.internal
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2020/4/29 0029 : 20 :44
 * @project-name: songFox
 */
public final class HttpMethod {

    public static boolean invalidatesCache(String method) {
        return method.equals("POST")
                ||method.equals("PATCH")
                ||method.equals("PUT")
                ||method.equals("DELETE")
                ||method.equals("MOVE");//WebDAV
    }


    public static boolean requiresRequestBody(String method) {
        return method.equals("POST")
                ||method.equals("PATCH")
                ||method.equals("PUT")
                ||method.equals("PROPPATCH")
                ||method.equals("REPORT");//WebDAV
    }


    public static boolean permitsRequestBody(String method) {
        return !(method.equals("GET") || method.equals("HEAD"));
    }

    public static boolean redirectsWithBody(String method) {
        return method.equals("PROPFIND");
    }

    public static boolean redirectsToGet(String method) {
        return !method.equals("PROPFIND");
    }

    private HttpMethod(){}
}
