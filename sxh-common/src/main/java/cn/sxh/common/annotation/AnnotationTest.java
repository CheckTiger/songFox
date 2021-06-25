package cn.sxh.common.annotation;

public class AnnotationTest {

    @GET(value = "http://www.baidu.com")
    public String getIpMsg(){
        return "";
    }

    @GET(value = "http://www.baidu.com")
    public String getIp(){
        return "";
    }

}
