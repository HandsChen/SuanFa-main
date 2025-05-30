package 剑指offer.树;

import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//注意，二叉树和二叉搜索树是不同的
public class No57_二叉树的最近公共祖先 {
    public static void main(String[] args) {
        // 构建二叉树 [-1, 0, 3, -2, 4, null, null, 8]
        TreeNode root = new TreeNode(-1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(-2);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(8);

        // 设置要查找的节点
        TreeNode p = root.right;    // 节点3
        TreeNode q = root.left.left.left;  // 节点8

        System.out.println("res = " + lowestCommonAncestor2(root, p, q));
    }

    //1.自己想的比较粗暴的方法，
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (null == root) {
            return null;
        }
        //如果p,q均在右子树
        if (findTargetSubTreeFromRootTree(root.right, p) && findTargetSubTreeFromRootTree(root.right, q)) {
            return lowestCommonAncestor(root.right, p, q);
        }
        //如果p,q均在左子树
        else if (findTargetSubTreeFromRootTree(root.left, p) && findTargetSubTreeFromRootTree(root.left, q)) {
            return lowestCommonAncestor(root.left, p, q);
        } else {
            return root; //如果p和q在不同树上，那么返回当前节点
        }
    }

    public boolean findTargetSubTreeFromRootTree(TreeNode root, TreeNode target) {
        if (root == null) {
            return false;
        }
        if (root.val == target.val) {
            return true;
        } else {
            return findTargetSubTreeFromRootTree(root.left, target) || findTargetSubTreeFromRootTree(root.right, target);
        }
    }

    //2.对自己的算法进行优化

    public static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (null == root) {
            return null;
        }
        LinkedList<Integer> pPath = new LinkedList<>();
        LinkedList<Integer> qPath = new LinkedList<>();
        findTargetSubTreeFromRootTree2(root, p, pPath);
        findTargetSubTreeFromRootTree2(root, q, qPath);
        int cursor = 0;
        int res = Integer.MIN_VALUE;
        while (cursor < pPath.size() && cursor < qPath.size()) {
            if (!pPath.get(cursor).equals(qPath.get(cursor))) {
                break;
            } else {
                res = pPath.get(cursor);
            }
            cursor++;
        }
        return res == Integer.MIN_VALUE ? null : new TreeNode(res);
    }

    public static boolean findTargetSubTreeFromRootTree2(TreeNode root, TreeNode target, LinkedList<Integer> path) {
        if (root == null) {
            return false;
        }
        path.offer(root.val);
        if (root.val == target.val) {
            return true;
        } else {
            //如果左右子树都找不到目标节点，那么当前节点回退
            if (!findTargetSubTreeFromRootTree2(root.left, target, path) && !findTargetSubTreeFromRootTree2(root.right, target, path)) {
                path.removeLast();
                return false;
            }
            return true;
        }
    }

    //3.查看答案
    public static TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        if (null == root || p == root || q == root) {
            return root;
        }
        TreeNode left = lowestCommonAncestor3(root.left, p, q);
        TreeNode right = lowestCommonAncestor3(root.right, p, q);
        return null == left ? right : (null == right ? left : root);
    }

}
