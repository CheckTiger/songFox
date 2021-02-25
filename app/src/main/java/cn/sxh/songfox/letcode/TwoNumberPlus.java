package cn.sxh.songfox.letcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个整数目标值 target，
 * 请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * 你可以按任意顺序返回答案。
 */
public class TwoNumberPlus {

    public static void main(String[] args) {
        int[] ints1 = solution1(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 9);
        int[] ints2 = solution2(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 9);
    }

    /**
     * 方法一：暴力枚举
     * 思路及算法
     * 最容易想到的方法是枚举数组中的每一个数 x，寻找数组中是否存在 target - x。
     * 当我们使用遍历整个数组的方式寻找 target - x 时
     * 需要注意到每一个位于 x 之前的元素都已经和 x 匹配过，
     * 因此不需要再进行匹配。而每一个元素不能被使用两次，
     * 所以我们只需要在 x 后面的元素中寻找 target - x。
     *
     * 时间复杂度O（N^2） N为数组的元素数量 遍历两次即N^2
     * 空间复杂度：O(1)。只需要执行一次即可得出结果
     * @param nums 传入的数组
     * @param target 目标值 即求和值
     */
    private static int[] solution1(int[] nums, int target) {
        int n = nums.length;
        System.out.println(System.currentTimeMillis());
        for (int i = 0; i < n; i++) {
            int one = nums[i];
            for (int j = 0; j < n; j++) {
                if (one + nums[j] == target) {
                    System.out.println(System.currentTimeMillis());
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }



    /**
     * 方法二：哈希表
     * 注意到方法一的时间复杂度较高的原因是寻找 target - x 的时间复杂度过高。因此，我们需要一种更优秀的方法，能够快速寻找数组中是否存在目标元素。如果存在，我们需要找出它的索引。
     *
     * 使用哈希表，可以将寻找 target - x 的时间复杂度降低到从 O(N)O(N) 降低到 O(1)O(1)。
     *
     * 这样我们创建一个哈希表，对于每一个 x，我们首先查询哈希表中是否存在 target - x，然后将 x 插入到哈希表中，即可保证不会让 x 和自己匹配。
     * 时间复杂度：O(N)，其中 N 是数组中的元素数量。对于每一个元素 x，我们可以 O(1)O(1) 地寻找 target - x。
     * 空间复杂度：O(N)，其中 N 是数组中的元素数量。主要为哈希表的开销
     * @param nums 传入的数组
     * @param target 目标值 即求和值
     */
    private static int[] solution2(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<>();
        System.out.println(System.currentTimeMillis());
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                System.out.println(System.currentTimeMillis());
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }

}
