package 剑指offer.树;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class No51_二叉搜索树与双向链表 {
    static List<Node> bucket = new ArrayList<>();

    public static void main(String[] args) {
        // 1. 创建根节点 4
        Node root = new Node(4);

        // 2. 插入节点 2 (比4小，放在左子树)
        root.left = new Node(2);

        // 3. 插入节点 5 (比4大，放在右子树)
        root.right = new Node(5);

        // 4. 插入节点 1 (比4小，先到左子树2；比2小，放在2的左子树)
        root.left.left = new Node(1);

        // 5. 插入节点 3 (比4小，先到左子树2；比2大，放在2的右子树)
        root.left.right = new Node(3);

        Node node = treeToDoublyList(root);
        int c = 0;
    }

    /*
    *         4
            /   \
           2     5
          / \
         1   3
    * */

    public static Node treeToDoublyList(Node root) {
        if (null == root) {
            return null;
        }
        dfs(root);
        for (int i = 1; i < bucket.size(); i++) {
            Node cur = bucket.get(i);
            Node pre = bucket.get(i - 1);
            pre.right = cur;
            cur.left = pre;
        }
        Node head = bucket.get(0);
        Node tail = bucket.get(bucket.size() - 1);
        head.left = tail;
        tail.right = head;
        return head;
    }

    public static void dfs(Node root) {
        if (null == root) {
            return;
        }
        //中序遍历
        dfs(root.left);
        bucket.add(root);
        dfs(root.right);
    }

    //2.还可以不使用bucket，外面定义一个pre变量，在dfs中处理连接问题

}
