package 剑指offer.链表;

import 剑指offer.公共.ListNode;

import java.util.Arrays;

public class No32_链表中倒数第k个节点 {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(8);
        ListNode node4 = new ListNode(7, node1);
        ListNode node6 = new ListNode(4, node4);
        ListNode node3 = new ListNode(2, node6);
        System.out.println("trainingPlan = " + trainingPlan(node3, 1));

    }

    //双指针法
    public static ListNode trainingPlan(ListNode head, int cnt) {
        //新建快慢指针
        ListNode fast = head;
        ListNode slow = head;

        //快指针先走cnt步
        for (int i = 0; i < cnt; i++) {
            fast = fast.next;
        }
        //然后快慢指针一起走
        while (null != fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
