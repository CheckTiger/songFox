package cn.sxh.leetcode;

/**
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * <p>
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 * <p>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 * <p>
 * 请你实现这个将字符串进行指定行数变换的函数：
 * <p>
 * string convert(string s, int numRows);
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 * 示例 2：
 * 输入：s = "PAYPALISHIRING", numRows = 4
 * 输出："PINALSIGYAHRPI"
 * 解释：
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * 示例 3：
 * <p>
 * 输入：s = "A", numRows = 1
 * 输出："A"
 */
public class LeetCode013 {


    public static void main(String[] args) {
        String s = convert("songxuehuloveshishanshan", 4);
        System.out.println(s);
    }
    public static String convert(String s, int numRows) {
        if (s == null || s.length() <=1 || numRows == 1) {
            return s;
        }
        StringBuilder[] array = new StringBuilder[numRows];
        for (int i = 0; i < array.length; i++) {
            array[i] = new StringBuilder();
        }
        int dir = 1, index = 0;
        for (char c : s.toCharArray()) {
            array[index].append(c);
            index = index + dir;
            if (index == numRows - 1 || index == 0) {
                dir = -dir;
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            result.append(array[i]);
        }
        return result.toString();
    }
}
