package 剑指offer.树;

import common.TreeNode;

public class No44_二叉树的镜像 {
    public static void main(String[] args) {

    }

    public static TreeNode flipTree(TreeNode root) {
        doFlip(root);
        return root;
    }

    public static void doFlip(TreeNode root) {
        if (null == root) {
            return;
        }
        doFlip(root.left);
        doFlip(root.right);
        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = tmp;
    }
}
