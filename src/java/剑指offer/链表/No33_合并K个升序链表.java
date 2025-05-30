package 剑指offer.链表;

import 剑指offer.公共.ListNode;

import java.util.*;

public class No33_合并K个升序链表 {
    public static void main(String[] args) {
        ListNode node_a5 = new ListNode(5, null);
        ListNode node_a4 = new ListNode(4, node_a5);
        ListNode node_a1 = new ListNode(1, node_a4);

        ListNode node_b4 = new ListNode(4, null);
        ListNode node_b3 = new ListNode(3, node_b4);
        ListNode node_b1 = new ListNode(1, node_b3);

        ListNode node_c6 = new ListNode(6, null);
        ListNode node_c2 = new ListNode(2, node_c6);

        ListNode listNode = mergeKLists3(new ListNode[]{node_a1, node_b1, node_c2});
        System.out.println("node_c2 = " + listNode);
    }

    //1.暴力求解
    public static ListNode mergeKLists1(ListNode[] lists) {
        int len = lists.length;
        if (len == 0) {
            return null;
        }
        List<ListNode> res = new ArrayList<>();
        for (ListNode list : lists) {
            ListNode cur = list;
            while (null != cur) {
                res.add(cur);
                cur = cur.next;
            }
        }
        if (res.isEmpty()) {
            return null;
        }
        res.sort((Comparator.comparingInt(o -> o.val)));
        for (int i = 0; i < res.size() - 1; i++) {
            ListNode cur = res.get(i);
            cur.next = res.get(i + 1);
        }
        return res.get(0);
    }

    //2.队列进行比较
    public static ListNode mergeKLists2(ListNode[] lists) {
        ListNode dumb = new ListNode();
        ListNode cursor = null;
        //创建优先级队列
        Queue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val)); //小根堆
        //初始化队列
        for (ListNode list : lists) {
            if (null != list) {
                queue.offer(list);
            }
        }
        while (!queue.isEmpty()) {
            ListNode peekValue = queue.poll();
            ListNode next = peekValue.next;
            if (null != next) {
                queue.offer(next);
            }
            if (null == dumb.next) { //初始化
                dumb.next = peekValue;
                cursor = peekValue;
            } else {
                if (cursor != null) {
                    cursor.next = peekValue;
                    cursor = cursor.next;
                }
            }
        }
        return dumb.next;
    }

    //3.分治（每次将一半的链表进行合并）
    public static ListNode mergeKLists3(ListNode[] lists) {
        return mergePart(lists, 0, lists.length - 1);
    }

    public static ListNode mergePart(ListNode[] lists, int low, int high) {
        //终止条件
        if (low > high) {
            return null;
        }
        if (low == high) { //不需要合并
            return lists[low];
        }
        int mid = low + (high - low) >> 1;
        ListNode headNodeOfLeft = mergePart(lists, low, mid);
        ListNode headNodeOfRight = mergePart(lists, mid + 1, high);
        return mergeTwoLinkedList2(headNodeOfLeft, headNodeOfRight);
    }

    //合并两个列表 (自己写的)
    public static ListNode mergeTwoLinkedList(ListNode a, ListNode b) {
        //进行一些异常排除
        if (null == a && null == b) { //这里这么写实际上没啥意义，因为&&会短路运算
            return null;
        }
        if (null == a) {
            return b;
        }
        if (null == b) {
            return a;
        }

        //定义头节点
        ListNode head = null;
        if (a.val <= b.val) {
            head = a;
            a = a.next;
        } else {
            head = b;
            b = b.next;
        }
        ListNode cur = head;
        while (null != a && null != b) {
            if (a.val < b.val) {
                cur.next = a;
                cur = a;
                a = a.next;
            } else if (a.val > b.val) {
                cur.next = b;
                cur = b;
                b = b.next;
            } else {
                cur.next = a;
                cur = a;
                a = a.next;
                cur.next = b;
                cur = b;
                b = b.next;
            }
        }
        if (null == a) {
            cur.next = b;
        } else {
            cur.next = a;
        }
        return head;
    }

    //合并两个列表 (看了参考答案后自己写的)
    public static ListNode mergeTwoLinkedList2(ListNode a, ListNode b) {
        //进行一些异常排除
        if (null == a || null == b) {
            return null == a ? b : a;
        }

        //定义头节点
        ListNode head = new ListNode(0);
        ListNode tail = head;
        ListNode aPtr = a;
        ListNode bPtr = b;
        while (null != aPtr && null != bPtr) {
            if (aPtr.val < bPtr.val) {
                tail.next = aPtr;
                aPtr = aPtr.next;
            } else {
                tail.next = bPtr;
                bPtr = bPtr.next;
            }
            tail = tail.next;
        }
        tail.next = (null == aPtr ? bPtr : aPtr);
        return head.next;
    }
}
