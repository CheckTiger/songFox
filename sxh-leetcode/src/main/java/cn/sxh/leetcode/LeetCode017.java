package cn.sxh.leetcode;

import java.util.Arrays;

/**
 * 题目：最少移动次数使数组元素相等
 *
 * 描述：给定一个非空整数数组，找到使所有数组元素相等所需的最小移动数，
 * 其中每次移动可将选定的一个元素加1或减1。
 * 您可以假设数组的长度最多为10000。
 *
 * 例如:输入:
 * [1,2,3]
 *
 * 输出:
 * 2
 *
 * 说明：
 * 只有两个动作是必要的（记得每一步仅可使其中一个元素加1或减1）：
 *
 * [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
 *
 */
public class LeetCode017 {

    public static void main(String[] args) {
        int[] num = new int[]{0, 6, 4, 2, 1, 7, 3, 8, 54, 14, 62, 22, 12, 2};
        int sum = minMoves(num);
        System.out.println(sum);
    }

    /**
     * 给出非空数组，找出移动次数最少移动数使数组中元素相等
     * 即所有元素移动不同步数加或者减 最终的值相等，这个过程中移动的步数相加最小
     * 换位思考 水平线上一点 所有点到此点的距离相加最小，也就是中间点
     * 换位到数组中也就是说数组中的中位数到两端的移动距离之和最小
     * @param numbers
     * @return
     */
    public static int minMoves(int[] numbers) {
        Arrays.sort(numbers);
        int sum = 0;
        for (int num : numbers) {
            sum += Math.abs(numbers[numbers.length / 2] - num);
        }
        return sum;
    }

}
