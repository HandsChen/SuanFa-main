package 剑指offer.链表;

import 剑指offer.公共.ListNode;

public class No35_合并两个有序链表 {
    public static void main(String[] args) {

    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode firstPtr = list1;
        ListNode secondPtr = list2;
        ListNode head = new ListNode(0), tail = head;
        while (null != firstPtr && null != secondPtr) {
            if (firstPtr.val <= secondPtr.val) {
                tail.next = firstPtr;
                firstPtr = firstPtr.next;
            } else {
                tail.next = secondPtr;
                secondPtr = secondPtr.next;
            }
            tail = tail.next;
        }
        //可能还有剩余
        tail.next = null == firstPtr?secondPtr:firstPtr;
        return head.next;
    }
}
