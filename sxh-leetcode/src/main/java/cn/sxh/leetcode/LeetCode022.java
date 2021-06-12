package cn.sxh.leetcode;

/**
 * Remove Duplicates from Sorted Array
 * Given a sorted array, remove the duplicates in place such that each element appear only once
 * and return the new length.
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * For example, Given input array A = [1,1,2],
 * Your function should return length = 2, and A is now [1,2]
 */
public class LeetCode022 {

    public static void main(String[] args) {

        int[] nums = {1, 1, 1, 2, 2, 3, 4, 5};
        int[] i = removeDuplicates(nums);
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < i.length; j++) {
            sb.append(i[j]);
        }
        System.out.println(sb.toString());
    }

    public static int[] removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }

        int index = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[index] != nums[i]) {
                nums[++index] = nums[i];
            }
        }
        int[] ints = new int[index + 1];
        System.arraycopy(nums, 0, ints, 0, index + 1);
        return ints;
    }

}
