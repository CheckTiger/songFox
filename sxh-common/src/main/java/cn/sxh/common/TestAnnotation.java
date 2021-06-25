package cn.sxh.common;

import cn.sxh.common.annotation.ClassInfo;
import cn.sxh.common.annotation.FieldInfo;
import cn.sxh.common.annotation.MethodInfo;

@ClassInfo(table = "snow")
public class TestAnnotation {

    @FieldInfo(column = "snow_id",type = "string",generator = "file")
    public String fieldInfo = "FieldInfo";

    @FieldInfo(column = "song_id",type = "long",generator = "xml")
    public int i = 100;

    @MethodInfo(name = "BlueBird",data = "Big")
    public static String getMethodInfo(){
        return TestAnnotation.class.getSimpleName();
    }

}
