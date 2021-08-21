package cn.sxh.leetcode;

/**
 * 两数相加
 * <p>
 * 描述：
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，
 * 并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头
 * 例如 链表1 2->4->3->5
 * 例如 链表2 2->4->3->5
 * 输出  4->8->6->0->1
 */
public class LeetCode015 {
    private static int carry = 0;//记录进位

    public static void main(String[] args) {

        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(5);

        ListNode l2 = new ListNode(2);
        l2.next = new ListNode(4);
        l2.next.next = new ListNode(3);
        l2.next.next.next = new ListNode(5);

        ListNode listNode = addTwoNumbers(l1, l2);
        assert listNode != null;
        StringBuilder sb = new StringBuilder();
        while (listNode != null) {
            sb.append(listNode.val);
            listNode = listNode.next;
        }
        System.out.println(sb.toString());
    }


    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        int addOne = 0;
        while (l1 != null || l2 != null || addOne != 0) {
            int val1 = l1 == null ? 0 : l1.val;
            int val2 = l2 == null ? 0 : l2.val;
            int sum = val1 + val2 + addOne;
            head.next = new ListNode(sum % 10);
            head = head.next;
            addOne = sum / 10;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        return dummy.next;

    }


    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //定义终止条件，当l1,l2指针都为null时且进位为0 ->null
        if (l1 == null && l2 == null && carry == 0) return null;

        //当有一条链表为null 且 进位为0时，next指针直接指向另外一条链表返回
        if (l1 != null && l2 == null && carry == 0) {
            return l1;
        } else if (l1 == null && l2 != null && carry == 0) {
            return l2;
        }

        //sum = 两链表指针位置上的数字加上进位
        int sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
        //计算进位
        carry = sum / 10;
        //计算链表的value
        int value = sum % 10;
        ListNode node = new ListNode(value);

        //递归算出这个node的next指向
        node.next = addTwoNumbers((l1 == null ? null : l1.next), (l2 == null ? null : l2.next));

        return node;
    }
}
