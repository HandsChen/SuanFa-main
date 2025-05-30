package 剑指offer.链表;

import 剑指offer.公共.ListNode;

import java.util.Arrays;

public class No30_从尾到头打印链表 {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node4 = new ListNode(4, node1);
        ListNode node6 = new ListNode(6, node4);
        ListNode node3 = new ListNode(3, node6);
        System.out.println("reverseBookList = " + Arrays.toString(reverseBookList(node3)));
    }

    //反转链表
    public static int[] reverseBookList(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        ListNode next = null;
        int count = 0; //记录共有多少个节点
        while (null != cur) {
            next = cur.next;
            cur.next = pre;

            pre = cur;
            cur = next;

            count++;
        }
        int[] res = new int[count];
        int cursor = 0;
        while (null != pre) {
            res[cursor++] = pre.val;
            pre = pre.next;
        }
        return res;
    }
}
