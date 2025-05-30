package 剑指offer.树;

import common.TreeNode;

public class No53_二叉搜索树的第k大节点 {
    static int res = -1;
    static int count = 0;

    public static void main(String[] args) {
        TreeNode A_7 = new TreeNode(7);
        TreeNode A_3 = new TreeNode(3);
        TreeNode A_9 = new TreeNode(9);
        TreeNode A_1 = new TreeNode(1);
        TreeNode A_5 = new TreeNode(5);

        A_7.left = A_3;
        A_7.right = A_9;

        A_3.left = A_1;
        A_3.right = A_5;

        System.out.println("A_5 = " + findTargetNode(A_7, 2));

    }

    public static int findTargetNode(TreeNode root, int cnt) {
        count = cnt;
        dfs(root);
        return res;
    }

    public static void dfs(TreeNode root) {
        if (null == root) { //到了0提早返回，相当于剪枝
            return;
        }
        //首先遍历右子树
        dfs(root.right);
        //中序遍历
        count--; //到达一个节点就减去1，应该是先减再判断，而不是先判断再减
        if (count == 0) {
            res = root.val;
            return;
        }
        //然后遍历左树
        dfs(root.left);
    }

}
