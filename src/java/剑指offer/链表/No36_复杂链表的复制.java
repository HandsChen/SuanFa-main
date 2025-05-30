package 剑指offer.链表;

import 剑指offer.公共.Node;

import java.util.HashMap;
import java.util.Map;

public class No36_复杂链表的复制 {
    public static void main(String[] args) {
        // 创建所有节点
        Node node0 = new Node(7);
        Node node1 = new Node(13);
        Node node2 = new Node(11);
        Node node3 = new Node(10);
        Node node4 = new Node(1);

        // 设置next指针
        node0.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = null;

        // 设置random指针
        node0.random = null;
        node1.random = node0;
        node2.random = node4;
        node3.random = node2;
        node4.random = node0;


        Node node = copyRandomList2(node0);
        System.out.println("res = " + node);

    }

    //1. 难点在于确定random节点指向 （哈希）
    public static Node copyRandomList(Node head) {
        if (null == head) return null;
        Node start = new Node(0);
        Node tail = start;
        Node cur = head;
        //新建一个map
        Map<Node, Node> cache = new HashMap<>(); //存放旧的值和新值的映射
        //开始遍历
        while (null != cur) {
            Node newNode = new Node(cur.val);
            cache.put(cur, newNode);
            tail.next = newNode;
            cur = cur.next;
            tail = tail.next;
        }
        //然后再遍历一次，复制random的指针指向
        cur = head;
        while (null != cur) {
            Node curRandom = cur.random;
            Node newNodeOfCur = cache.get(cur);
            newNodeOfCur.random = cache.get(curRandom);
            cur = cur.next;
        }
        return start.next;
    }

    //拼接+拆分
    public static Node copyRandomList2(Node head) {
        if (null == head) return null;
        Node cur = head;
        //将新节点拼接到旧节点后面
        while (null != cur) {
            Node newNode = new Node(cur.val);
            Node tmp = cur.next;
            cur.next = newNode;
            newNode.next = tmp;
            //遍历
            cur = tmp;
        }
        //参考旧节点的random指向，重置新节点的random指向
        cur = head;
        while (null != cur) {
            if (null != cur.random) {
                Node randomOfCur = cur.random;
                cur.next.random = randomOfCur.next;
            }
            cur = cur.next.next;
        }
        //将新生成的节点从拼接的节点中拆出来 （原来的节点结构也不能变，这意味着我们需要将原来的链表原样恢复，否则会产生报错）
        Node res = head.next;
        cur = head;
        while (null != cur) {
            Node newNode = cur.next;
            cur.next = cur.next.next;
            if (null != cur.next) {
                newNode.next = newNode.next.next;
            }
            cur = cur.next;
        }
        return res;
    }
}
