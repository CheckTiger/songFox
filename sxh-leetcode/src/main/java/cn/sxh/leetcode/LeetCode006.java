package cn.sxh.leetcode;

/**
 * 1.寻找两个正序数组的中位数
 * 题目：给定两个大小分别为m和n的正序（从小到大）
 * 数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数 。
 *
 *
 * 解题思路：
 */
public class LeetCode006 {

    public static void main(String[] args) {

    }


    public static int findMiddleSortedArray(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int length = m + n;
        int left = -1, right = -1;
        int aStart = 0, bStart = 0;
        for (int i = 0; i <= length / 2; i++) {
            left = right;
            if (aStart < m && (bStart >= n || A[aStart] < B[bStart])) {
                right = A[aStart++];
            } else {
                right = B[bStart++];
            }
        }
        if ((length & 1) == 0) {
            return (int) ((left + right) / 2.0);
        } else {
            return right;
        }

    }

}
