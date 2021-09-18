package cn.sxh.leetcode;

public class LeetCode027 {

    public static void main(String[] args) {
        float test = test(8.975f);
        System.out.println(test);
    }


    public static float test(float a) {

        String aa = String.valueOf(a);
        if (!aa.contains(".")) {
            return a;
        }
        if (aa.contains(".")) {
            String[] spilt = aa.split("\\.");
            if (Float.valueOf(spilt[1]) < 5) {
                return Float.valueOf(spilt[0]);
            } else {
                return Float.valueOf(spilt[0])+1;
            }
        }
        return a;
    }

}
