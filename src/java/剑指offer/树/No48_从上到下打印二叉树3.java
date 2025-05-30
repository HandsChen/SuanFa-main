package 剑指offer.树;

import common.TreeNode;

import java.util.*;

public class No48_从上到下打印二叉树3 {
    public static void main(String[] args) {
        TreeNode node_8 = new TreeNode(8);
        TreeNode node_17 = new TreeNode(17);
        TreeNode node_21 = new TreeNode(21);
        TreeNode node_18 = new TreeNode(18);
        TreeNode node_6 = new TreeNode(6);

        node_8.left = node_17;
        node_8.right = node_21;
        node_17.left = node_18;
        node_21.right = node_6;
        System.out.println("decorateRecord() = " + decorateRecord(node_8));
    }

    public static List<List<Integer>> decorateRecord(TreeNode root) {
        if (null == root) {
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<List<Integer>> res = new ArrayList<>();
        boolean reverse = false;
        while (!queue.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            Deque<Integer> stack = new ArrayDeque<>();
            for (int i = queue.size(); i > 0; i--) { //for倒着来可以预防 queue因为poll导致size变动的问题
                TreeNode curNode = queue.poll();
                if (reverse) { //如果反转
                    stack.push(curNode.val);
                } else { //如果不反转
                    tmp.add(curNode.val);
                }
                if (null != curNode.left) {
                    queue.offer(curNode.left);
                }
                if (null != curNode.right) {
                    queue.offer(curNode.right);
                }
            }
            //将stack中的元素注入tmp中
            while (!stack.isEmpty()) {
                tmp.add(stack.pop());
            }
            reverse = !reverse;
            res.add(tmp);
        }
        return res;
    }

}
