package 剑指offer.树;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class No43_树的子结构 {
    public static void main(String[] args) {
        TreeNode A_10 = new TreeNode(10);
        TreeNode A_12 = new TreeNode(12);
        TreeNode A_6 = new TreeNode(6);
        TreeNode A_8 = new TreeNode(8);
        TreeNode A_3 = new TreeNode(3);
        TreeNode A_11 = new TreeNode(11);
        A_10.left = A_12;
        A_10.right = A_6;

        A_12.left = A_8;
        A_12.right = A_3;

        A_6.left = A_11;
    }

    //1.自己想的,如果可以把下面两个方法整合，理论上只需要速度会更快一点
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (null == A || null == B) {
            return false;
        }
        //1.首先判断B树的头节点是否在A树中存在
        List<TreeNode> headNodeBucket = new ArrayList<>();
        findBTreeHeadNodeInATree(A, B.val, headNodeBucket);
        if (headNodeBucket.isEmpty()) { //如果在A树中没有找到B树的头节点，那么直接返回false
            return false;
        }
        //2.如果存在，那么需要同时从该节点对两树进行遍历，判断所有值相等
        for (TreeNode bTreeHeadNode : headNodeBucket) {
            boolean bTreeExistedInATree = isBTreeExistedInATree(bTreeHeadNode, B);
            if (bTreeExistedInATree) {
                return true;
            }
        }
        return false;
    }

    public void findBTreeHeadNodeInATree(TreeNode A, int bValue, List<TreeNode> headNodeBucket) {
        if (null == A) { //如果A树不存在，那么直接返回false
            return;
        }
        if (A.val == bValue) {
            headNodeBucket.add(A);
        }
        findBTreeHeadNodeInATree(A.left, bValue, headNodeBucket);
        findBTreeHeadNodeInATree(A.right, bValue, headNodeBucket);
    }

    public static boolean isBTreeExistedInATree(TreeNode A, TreeNode B) {
        if (null == A && null == B) { //遍历结束，如果都为null，那么就返回true
            return true;
        }
        if (null != A && null != B) { //如果一棵树均不为空
            if (A.val != B.val) {
                return false;
            }
            boolean leftRes = isBTreeExistedInATree(A.left, B.left);
            boolean rightRes = isBTreeExistedInATree(A.right, B.right);
            return leftRes && rightRes;
        } else { //如果有且只有一棵树不为空，如果仅仅是B树空了，A树没空，那么返回true,否则返回false
            return null == B;
        }
    }
    //1.参考了答案，将前两个方法中遍历A树寻找B树头节点的过程精简，边遍历边进行判断B是否在A中
    public static boolean isSubStructure2(TreeNode A, TreeNode B) {
        return (A != null && B != null) && (isBTreeExistedInATree(A, B) || isSubStructure2(A.left, B) || isSubStructure2(A.right, B));
    }
}
