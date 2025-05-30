package 剑指offer.树;

import common.TreeNode;

import java.util.*;

public class No46_从上到下打印二叉树1 {
    public static void main(String[] args) {

    }

    //1.使用广度优先
    public static int[] decorateRecord(TreeNode root) {
        if (null == root) {
            return new int[]{};
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int[] res = new int[1010];
        int cursor = 0;
        while (!queue.isEmpty()) {
            TreeNode tmp = queue.poll();
            res[cursor++] = tmp.val;
            if (null != tmp.left) {
                queue.offer(tmp.left);
            }
            if (null != tmp.right) {
                queue.offer(tmp.right);
            }
        }
        return Arrays.copyOfRange(res, 0, cursor);
    }
}
