package cn.sxh.leetcode;

/**
 * 汉明距离
 *
 * 描述：两个整数之间x和y的汉明距离指的是两个数字对应二进制位不同的位置的数目
 * 给出两个整数x和y，计算他们之间的汉明距离
 *
 * java中运算符：https://www.runoob.com/java/java-operators.html?_t_t_t=0.3133259497117251
 */
public class LeetCode016 {

    public static void main(String[] args) {

        int i = method1(5, 8);
        int j = method2(9, 8);
        int k = method3(60, 8);
        System.out.println("i==>"+i+":j==>"+j+":k==>"+k);
    }

    /**
     * ^ 按位异或 java内置方法处理
     * @param x
     * @param y
     * @return
     */
    public static int method1(int x, int y) {
        return Integer.bitCount(x ^ y);
    }

    /**
     * ^ 按位异或 相同为0 不同为1
     *
     * 位移运算
     * @param x
     * @param y
     * @return
     */
    public static int method2(int x, int y) {
        int s = x ^ y, ret = 0;
        while (s != 0) {
            ret += s & 1;
            s >>= 1;
        }
        return ret;
    }

    /**
     * ^ 按位异或 相同为0 不同为1
     * @param x
     * @param y
     * @return
     */
    public static int method3(int x, int y) {
        int s = x ^ y, ret = 0;
        while (s != 0) {
            s&=s-1;
            ret++;
        }
        return ret;
    }

}
