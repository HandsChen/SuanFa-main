package 剑指offer.树;

import common.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class No52_序列化二叉树 {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        // 创建节点
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);

        // 构建树结构
        root.left = node2;
        root.right = node3;
        node3.left = node4;
        node3.right = node5;

        System.out.println("root = " + serialize(root));
    }

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        if (null == root) {
            return null;
        }
        bfs(root);
        sb.deleteCharAt(sb.length() - 1); //删除后面的标点符号
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if (null == data || data.isEmpty()) {
            return null;
        }
        String[] str = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(str[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root); //添加root节点
        int cursor = 1;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (!str[cursor].equals("null")) {
                cur.left = new TreeNode(Integer.parseInt(str[cursor]));
                queue.add(cur.left);
            }
            cursor++;
            if (!str[cursor].equals("null")) {
                cur.right = new TreeNode(Integer.parseInt(str[cursor]));
                queue.add(cur.right);
            }
            cursor++;
        }
        return root;
    }

    //0 1,2
    //1 3,4
    //2 5,6
    //3 7,8
    public static void bfs(TreeNode root) {
        if (null == root) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (null != cur) {
                sb.append(cur.val);
                sb.append(",");
                queue.offer(cur.left);
                queue.offer(cur.right);
            } else {
                sb.append("null");
                sb.append(",");
            }
        }
    }
}
