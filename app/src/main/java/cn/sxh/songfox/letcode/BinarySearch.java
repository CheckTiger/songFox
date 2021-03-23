package cn.sxh.songfox.letcode;

/**
 * 二分查找法
 * 实现思想：
 * leetCode-35. 搜索插入位置
 */
public class BinarySearch {


    public static void main(String[] args) {
        int[] num = {5, 16, 20, 27, 30, 36, 44, 55, 60, 67, 71};
        binarySearch(num, num.length, 65);
    }
     static int binarySearch(int[] array, int size, int value) {
        int lo = 0;
        int hi = size - 1;

        while (lo <= hi) {
            final int mid = (lo + hi) >>> 1;
            final int midVal = array[mid];

            if (midVal < value) {
                lo = mid + 1;
            } else if (midVal > value) {
                hi = mid - 1;
            } else {
                return mid;  // value found
            }
        }
        return ~lo;  // value not present
    }
}
