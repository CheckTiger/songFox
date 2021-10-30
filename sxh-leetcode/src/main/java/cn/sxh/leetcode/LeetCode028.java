package cn.sxh.leetcode;

/**
 * Given an array and a value, remove all instances of that value in place and return the new length.
 * The order of elements can be changed. It doesn’t matter what you leave beyond the new length.
 *
 *
 */
public class LeetCode028 {

    public static void main(String[] args) {

    }

    public static int removeElement(int[] nums, int target) {
        int index = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != target) {
                nums[index++] = nums[i];
            }
        }
        return index;
    }
}
