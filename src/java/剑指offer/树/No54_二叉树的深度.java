package 剑指offer.树;

import common.TreeNode;

public class No54_二叉树的深度 {
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) {

    }

    public static int calculateDepth(TreeNode root) {
        if(null==root){
            return 0;
        }
        dfs(root, 1);
        return max;
    }

    public static void dfs(TreeNode root, int layer) {
        if (null == root) {
            return;
        }
        dfs(root.left, layer + 1);
        dfs(root.right, layer + 1);
        max = Math.max(max, layer);
    }

}
