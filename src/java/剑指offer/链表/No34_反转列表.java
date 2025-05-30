package 剑指offer.链表;

import 剑指offer.公共.ListNode;

import java.util.ArrayList;
import java.util.List;

public class No34_反转列表 {
    public static void main(String[] args) {
        ListNode node5 = new ListNode(5, null);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);

        ListNode listNode = trainningPlan3(node1);
        System.out.println("node1 = " + listNode);
    }

    //1.暴力法 (只把其中的数倒着换一下，并不处理指针)
    public static ListNode trainningPlan(ListNode head) {
        if (null == head) {
            return null;
        }
        List<Integer> bucket = new ArrayList<>();
        ListNode cur = head;
        while (null != cur) {
            bucket.add(cur.val);
            cur = cur.next;
        }
        cur = head;
        int count = bucket.size() - 1;
        while (null != cur) {
            cur.val = bucket.get(count--);
            cur = cur.next;
        }
        return head;
    }

    //2.如果想时间复杂度更低，那么就分治(感觉将列表分治 是个很傻逼的想法，因为其链表的总长度不知道)
    //3.硬生生改变指针指向
    public static ListNode trainningPlan3(ListNode head) {
        if (null == head) {
            return null;
        }
        ListNode cur = head;
        ListNode pre = null;
        while (null != cur) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }
}
