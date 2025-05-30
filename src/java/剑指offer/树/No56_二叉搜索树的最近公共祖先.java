package 剑指offer.树;

import common.TreeNode;

public class No56_二叉搜索树的最近公共祖先 {
    public static void main(String[] args) {

    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (null == root) {
            return null;
        }
        int rootValue = root.val;
        int pValue = p.val;
        int qValue = q.val;
        if (pValue > rootValue && qValue > rootValue) {
            return lowestCommonAncestor(root.right, p, q);
        } else if (pValue < rootValue && qValue < rootValue) {
            return lowestCommonAncestor(root.left, p, q);
        } else {
            return root;
        }
    }
}
