package 剑指offer.树;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class No45_对称的二叉树 {
    public static void main(String[] args) {

    }

    public static boolean checkSymmetricTree(TreeNode root) {
        if (null == root) {
            return true;
        }
        return search(root.left, root.right);
    }

    public static boolean search(TreeNode leftRoot, TreeNode rightRoot) {
        if (null == leftRoot && null == rightRoot) {
            return true;
        }
        if (null == leftRoot) {
            return false;
        }
        if (null == rightRoot) {
            return false;
        }
        if (leftRoot.val != rightRoot.val) {
            return false;
        }
        boolean leftEqual = search(leftRoot.left, rightRoot.right);
        boolean rightEqual = search(leftRoot.right, rightRoot.left);
        return leftEqual && rightEqual;
    }


}
