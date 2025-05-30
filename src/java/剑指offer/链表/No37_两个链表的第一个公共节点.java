package 剑指offer.链表;

import 剑指offer.公共.ListNode;

import java.util.*;

public class No37_两个链表的第一个公共节点 {
    public static void main(String[] args) {

    }

    //1. 双指针暴力 (但是执行速度比方法2快)
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (null == headA || null == headB) {
            return null;
        }
        int countA = 0;
        int countB = 0;
        ListNode aPtr = headA;
        ListNode bPtr = headB;
        while (null != aPtr) {
            countA++;
            aPtr = aPtr.next;
        }
        while (null != bPtr) {
            countB++;
            bPtr = bPtr.next;
        }
        aPtr = headA;
        bPtr = headB;
        int diff = Math.abs(countA - countB);
        if (0 != diff) {
            if (countA > countB) {
                int i = 0;
                while (i < diff) {
                    aPtr = aPtr.next;
                    i++;
                }
            } else if (countA < countB) {
                int i = 0;
                while (i < diff) {
                    bPtr = bPtr.next;
                    i++;
                }
            }
        }
        //协同走
        while (null != aPtr && null != bPtr && aPtr != bPtr) {
            aPtr = aPtr.next;
            bPtr = bPtr.next;
        }
        return aPtr;
    }

    //2. 哈希法，如果两个链表存在公共节点，那么其地址指向相等
    public static ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (null == headA || null == headB) {
            return null;
        }
        Set<ListNode> cache = new HashSet<>();
        ListNode aPtr = headA;
        ListNode bPtr = headB;
        while (null != aPtr) {
            cache.add(aPtr);
            aPtr = aPtr.next;
        }
        while (null != bPtr) {
            if (cache.contains(bPtr)) {
                return bPtr;
            }
            bPtr = bPtr.next;
        }
        return null;
    }

    //3.双指针（非人类的解法）
    public static ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        if (null == headA || null == headB) {
            return null;
        }
        ListNode aPtr = headA;
        ListNode bPtr = headB;
        while (aPtr != bPtr) {
            aPtr = null == aPtr ? headB : aPtr.next;
            bPtr = null == bPtr ? headA : bPtr.next;
        }
        return aPtr;
    }
}
