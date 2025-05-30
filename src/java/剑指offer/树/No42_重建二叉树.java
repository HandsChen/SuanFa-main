package 剑指offer.树;

import common.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class No42_重建二叉树 {

    static HashMap<Integer, Integer> hmap = new HashMap<>();
    static int[] preorder; //先序遍历

    public static void main(String[] args) {
//        TreeNode treeNode = deduceTree2(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        TreeNode treeNode = deduceTree2(new int[]{1, 2, 3}, new int[]{2, 3, 1});
        int c = 0;
    }

    //1. 自己想思路，递归, 本质上存在大量
    public static TreeNode deduceTree(int[] preorder, int[] inorder) {
        int length = preorder.length;
        if (length == 0) {
            return null;
        }
        if (length == 1) {
            return new TreeNode(preorder[0]);
        }
        int headIndex = linearSearch(inorder, preorder[0]);
        int[] newLeftPreOrder = Arrays.copyOfRange(preorder, 1, headIndex + 1);
        int[] newLeftInOrder = Arrays.copyOfRange(inorder, 0, headIndex);
        TreeNode leftSubNodeHead = deduceTree(newLeftPreOrder, newLeftInOrder);
        int[] newRightPreOrder = Arrays.copyOfRange(preorder, headIndex + 1, preorder.length);
        int[] newRightInOrder = Arrays.copyOfRange(inorder, headIndex + 1, inorder.length);
        TreeNode rightSubNodeHead = deduceTree(newRightPreOrder, newRightInOrder);
        return new TreeNode(inorder[headIndex], leftSubNodeHead, rightSubNodeHead);
    }

    public static int linearSearch(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1; // 没找到
    }

    //查看答案
    public static TreeNode deduceTree2(int[] preorder, int[] inorder) {
        No42_重建二叉树.preorder = preorder;
        //记录inorder中所有元素及其对应的index索引
        for (int i = 0; i < inorder.length; i++)
            hmap.put(inorder[i], i);
        return build(0, 0, preorder.length - 1);
    }

    public static TreeNode build(int rootIndex, int leftIndex, int rightIndex) {
        if (leftIndex > rightIndex) {
            return null;
        }
        //构建根节点
        TreeNode root = new TreeNode(preorder[rootIndex]);
        //寻找在中序遍历中根节点对应的索引
        int rootIndexAtInOrder = hmap.get(preorder[rootIndex]);
        //构建左子树
        root.left = build(rootIndex + 1, leftIndex, rootIndexAtInOrder - 1);
        //构建右子树
//        root.right = build(rootIndexAtInOrder + 1, rootIndexAtInOrder + 1, rightIndex);
        root.right = build(rootIndex + (rootIndexAtInOrder - leftIndex) + 1, rootIndexAtInOrder + 1, rightIndex); //右子树根节点位置=当前根节点位置+左子树长度+1
        return root;
    }
    // 3 9 20 15 7
    // 9 3 15 20 7
    // 1-> 左子树

    //1 2 3
    //2 3 1
    //2 3 left
}
