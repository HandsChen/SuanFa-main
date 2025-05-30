package 剑指offer.树;

import common.TreeNode;

public class No55_判断是否为平衡二叉树 {
    boolean res = true;

    public static void main(String[] args) {

    }

    //1.自己想的比较粗暴的一种方法，时间复杂度为O(N^2)
    public boolean isBalanced(TreeNode root) {
        if (null == root) {
            return true;
        }
        //首先判断左右子树分别是否是平衡树
        boolean leftBalance = isBalanced(root.left);
        boolean rightBalance = isBalanced(root.right);
        //然后再判断当前树是否是平衡树
        int leftDepth = treeDepth(root.left);
        int rightDepth = treeDepth(root.right);
        return leftBalance && rightBalance && Math.abs(leftDepth - rightDepth) <= 1;
    }

    public int treeDepth(TreeNode root) {
        if (null == root) {
            return 0;
        }
        int leftDepth = treeDepth(root.left);
        int rightDepth = treeDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    //2.自己想的第二种方法，时间复杂度应该是O(n)
    public boolean isBalanced2(TreeNode root) {
        treeDepth2(root);
        return res;
    }

    public int treeDepth2(TreeNode root) {
        if (null == root) {
            return 0;
        }
        int leftDepth = treeDepth2(root.left);
        int rightDepth = treeDepth2(root.right);
        if (Math.abs(leftDepth - rightDepth) > 1) {
            res = false;
        }
        return Math.max(leftDepth, rightDepth) + 1;
    }
    //3.复杂度为3
    public boolean isBalanced3(TreeNode root) {

        return -1 != treeDepth3(root);
    }

    public int treeDepth3(TreeNode root) {
        if (null == root) { //!res为了快速从堆栈中退出
            return 0;
        }
        int leftDepth = treeDepth3(root.left);
        if (leftDepth == -1) {
            return -1;
        }
        int rightDepth = treeDepth3(root.right);
        if (rightDepth == -1) {
            return -1;
        }
        if (Math.abs(leftDepth - rightDepth) > 1) {
            return -1;
        }
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
