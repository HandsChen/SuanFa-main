package 剑指offer.链表;

import 剑指offer.公共.ListNode;

public class No31_删除链表的节点 {
    public static void main(String[] args) {

    }

    public ListNode deleteNode(ListNode head, int val) {
        //定义一个哑节点,并将其指向头节点
        ListNode dumb = new ListNode(-1, head);
        //开始不断遍历节点
        ListNode pre = dumb;
        ListNode cur = dumb.next;
        while (null != cur) {
            if (val == cur.val) { //为要删除的节点
                pre.next = cur.next;
                //删除当前节点
                cur.val = 0;
                cur.next = null;
                //删除完毕后，重置指针(pre不动,cur移动)
                cur = pre.next;
            } else { //不是要删除的节点
                pre = cur;
                cur = cur.next;
            }
        }
        return dumb.next;
    }
}
