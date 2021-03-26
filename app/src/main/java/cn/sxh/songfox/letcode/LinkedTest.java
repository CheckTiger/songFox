package cn.sxh.songfox.letcode;

import java.util.LinkedHashMap;
import java.util.Set;

public class LinkedTest {

    public static void main(String[] args) {
        init();
    }

    private static void init() {
        LinkedHashMap map = new LinkedHashMap();
        map.put("2", "b");
        map.put("3", "c");
        map.put("7", "g");
        map.put("4", "d");
        map.put("5", "e");
        map.put("9", "i");
        map.put("1", "a");
        map.put("6", "f");
        map.put("8", "h");
//        map.eldest();
//        map.
        Set set = map.keySet();
        for (Object obj : set) {
            System.out.println("值------>>>>"+obj.toString());
        }
    }
}
