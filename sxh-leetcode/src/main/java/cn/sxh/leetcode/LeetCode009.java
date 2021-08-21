package cn.sxh.leetcode;

/**
 * 删除排序链表中重复的元素
 */
public class LeetCode009 {


    public static void main(String[] args) {
        split(null);
    }

    public static ListNode split(ListNode head) {
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }
}
