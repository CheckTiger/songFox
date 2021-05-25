package cn.sxh.leetcode;

/**
 * 两数相加
 * <p>
 * 描述：
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，
 * 并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头
 * 例如  2->4->3->5
 *       2->4->3->5
 * 输出  4->8->6->0->1
 */
public class LeetCode015 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(5);
        ListNode l2 = new ListNode(4);
        ListNode listNode = addTwoNumbers(l1, l2, 0);
        System.out.println(listNode.val );
    }


    public static ListNode addTwoNumbers(ListNode l1, ListNode l2, int a) {
        if (l1 == null && l2 == null) {
            return a == 0 ? null : new ListNode(a);
        }
        if (l1 != null) {
            a += l1.val;
            l1 = l1.next;
        }

        if (l2 != null) {
            a += l2.val;
            l2 = l2.next;
        }

        return new ListNode(a % 10, addTwoNumbers(l1, l2, a / 10));
    }
}
