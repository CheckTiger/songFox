package cn.sxh.leetcode;

/**
 * Remove Duplicates from Sorted Array II
 * <p>
 * Follow up for ”Remove Duplicates”: What if duplicates are allowed at most twice?
 * For example, Given sorted array A = [1,1,1,2,2,3],
 * Your function should return length = 5, and A is now [1,1,2,2,3]
 * <p>
 * 分析:
 * 加入一个变量记录一下出现的次数即可，这题因为是已经排序的数组，所以一个变量即可
 * 解决。如果是没有排序的数组，则需要引入一个hashMap来记录出现的次数
 */
public class LeetCode023 {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2,2, 3, 4,4,4, 5};
        int[] i = removeDuplicates(nums);

        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < i.length; j++) {
            sb.append(i[j]);
        }
        System.out.println(sb.toString());

    }

    public static int[] removeDuplicates(int[] nums) {
        if (nums == null || nums.length <= 2) {
            return nums;
        }

        int index = 2;

        for (int i = 2; i < nums.length; i++) {
            if (nums[i] != nums[index-2]) {
                nums[index++] = nums[i];
            }
        }
        int[] ints = new int[index];
        System.arraycopy(nums, 0, ints, 0, index);
        return ints;
    }
}
