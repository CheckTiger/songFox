package cn.sxh.songfox.letcode;

/**
 * 一维数组动态之和
 */
public class LeetCode2021_1480 {

    public static void main(String[] args) {

    }
    public int[] runningSum(int[] nums) {
        int m = nums.length;
        int first = nums[0];
        int[] newInt = new int[m];
        newInt[0] = first;
        for(int i = 1; i < m; i++){
            newInt[i] = nums[i] + newInt[i-1];
        }
        return newInt;
    }

}
