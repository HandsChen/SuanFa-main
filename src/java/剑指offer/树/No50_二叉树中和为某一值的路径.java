package 剑指offer.树;

import common.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class No50_二叉树中和为某一值的路径 {
    public static void main(String[] args) {
        TreeNode A_5 = new TreeNode(5);
        TreeNode A_4 = new TreeNode(4);
        TreeNode A_8 = new TreeNode(8);
        TreeNode A_11 = new TreeNode(11);
        TreeNode A_13 = new TreeNode(13);

        A_5.left = A_4;
        A_5.right = A_8;

        A_4.left = A_11;
        A_8.left = A_13;

        System.out.println("A_13 = " + pathTarget(A_5, 26));

    }

    //1.自己想的方法
    public static List<List<Integer>> pathTarget(TreeNode root, int target) {
        if (null == root) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, target, new LinkedList<>(), res, 0);
        return res;
    }


    public static void dfs(TreeNode root, int target, List<Integer> curPath, List<List<Integer>> res, int curPathValueSum) {
        if (null == root) { //如果节点为空，那么直接返回
            return;
        }
        //将当前节点加入路径
        curPath.add(root.val);
        //更新当前记录路径的和
        curPathValueSum += root.val;

        //如果已经到了叶节点，如果路径和与目标值相等，那么就将路径所有节点加进来
        if (root.left == null && root.right == null && curPathValueSum == target) {
            res.add(new ArrayList<>(curPath)); //这里对curPath做了一层深拷贝
            return; //到了叶节点那么也就不继续向下遍历了，直接确定结果
        }
        //先序号遍历(先左树)
        if (null != root.left) {
            dfs(root.left, target, curPath, res, curPathValueSum);
            //进行回溯
            curPath.remove(curPath.size() - 1);
        }
        //先序号遍历(右树)
        if (null != root.right) {
            dfs(root.right, target, curPath, res, curPathValueSum);
            //进行回溯
            curPath.remove(curPath.size() - 1);
        }
    }

    //看答案后发现target和curPathValueSum本质上可以整合
    public void dfs2(TreeNode root, int target, List<Integer> curPath, List<List<Integer>> res) {
        if (null == root) { //如果节点为空，那么直接返回
            return;
        }
        //将当前节点加入路径
        curPath.add(root.val);
        //更新当前记录路径的和
        target -= root.val;

        //如果已经到了叶节点，如果路径和与目标值相等，那么就将路径所有节点加进来
        if (root.left == null && root.right == null && 0 == target) {
            res.add(new ArrayList<>(curPath)); //这里对curPath做了一层深拷贝,否则最后res中只包含第一个元素（指针）
            return; //到了叶节点那么也就不继续向下遍历了，直接确定结果
        }
        //先序遍历(先左树)
        if (null != root.left) {
            dfs2(root.left, target, curPath, res);
            //进行回溯
            curPath.remove(curPath.size() - 1);
        }
        //先序遍历(右树)
        if (null != root.right) {
            dfs2(root.right, target, curPath, res);
            //进行回溯
            curPath.remove(curPath.size() - 1);
        }

    }
}
