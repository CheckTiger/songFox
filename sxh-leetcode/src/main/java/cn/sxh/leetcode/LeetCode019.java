package cn.sxh.leetcode;

/**
 * 100 game游戏
 * <p>
 * 描述：在 "100 game" 这个游戏中，两名玩家轮流选择从 1 到 10 的任意整数，
 * 累计整数和，先使得累计整数和达到或超过 100 的玩家，即为胜者。
 * <p>
 * 如果我们将游戏规则改为 “玩家不能重复使用整数” 呢？
 * <p>
 * 例如，两个玩家可以轮流从公共整数池中抽取从 1 到 15 的整数（不放回），直到累计整数和 >= 100。
 * <p>
 * 给定一个整数 maxChoosableInteger （整数池中可选择的最大数）和
 * 另一个整数 desiredTotal（累计和），判断先出手的玩家是否能稳赢（假设两位玩家游戏时都表现最佳）？
 * <p>
 * 你可以假设 maxChoosableInteger 不会大于 20， desiredTotal 不会大于 300。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/can-i-win
 */
public class LeetCode019 {

    public static void main(String[] args) {
        boolean isWin = canIWin(12, 65);
        System.out.println(isWin);
    }

    public static boolean canIWin(int maxChooseAbleInteger, int desiredTotal) {
        if (desiredTotal <= maxChooseAbleInteger) {
            return true;
        }
        if ((1 + maxChooseAbleInteger) * maxChooseAbleInteger / 2 < desiredTotal) {
            return false;
        }

        boolean[] isWin = new boolean[maxChooseAbleInteger + 1];
        return recur(maxChooseAbleInteger, desiredTotal, isWin);
    }

    private static boolean recur(int maxChooseAbleInteger, int desiredTotal, boolean[] isWin) {
        //通知上层，本层是失败的
        if (desiredTotal <= 0) {
            return false;
        }
        //这是跳出循环条件
        //首先。最终肯定是要回到第一次调用的for循环，也就是A第一个选的数，所以for
        //中被截断肯定return true才行
        //当前层需要知道上层的情况，因此需要返回上层的结果
        for (int i = 0; i <= maxChooseAbleInteger; i++) {
            if (isWin[i]) {
                continue;
            }
            isWin[i] = true;
            boolean last = recur(maxChooseAbleInteger, desiredTotal - i, isWin);
            isWin[i] = false;
            //上层没赢，这层才能返回这层的结果true，阶段for循环因为这层选小的都赢了
            //那么选大的也肯定赢
            if (!last) {
                return true;
            }
        }
        //如果for循环走完了，没被截断，说明当前层的选择，都是失败的
        //(放到第一个选择的人身上，就是false，放到第二个人身上，那就是会回溯到第一个人身上，那第一个人会返回true)
        return false;
    }

}
