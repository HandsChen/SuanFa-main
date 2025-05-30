package 剑指offer.树;

import common.TreeNode;

import java.util.*;

public class No47_从上到下打印二叉树2 {
    public static void main(String[] args) {
        TreeNode node_8 = new TreeNode(8);
        TreeNode node_17 = new TreeNode(17);
        TreeNode node_21 = new TreeNode(21);
        TreeNode node_18 = new TreeNode(18);
        TreeNode node_6 = new TreeNode(6);

        node_8.left = node_17;
        node_8.right = node_21;
        node_17.left = node_18;
        node_21.right = node_6;
        System.out.println("decorateRecord() = " + decorateRecord2(node_8));
    }

    //1.自己想的方法，有点暴力，且花的时间长
    public static List<List<Integer>> decorateRecord(TreeNode root) {
        if (null == root) {
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<List<Integer>> res = new ArrayList<>();
        Map<TreeNode, Integer> record = new HashMap<>();
        record.put(root, 1);
        while (!queue.isEmpty()) {
            TreeNode tmp = queue.poll();
            Integer layer = record.get(tmp); //判断当前节点在第几层
            if (layer <= res.size()) {
                res.get(layer - 1).add(tmp.val);
            } else {
                List<Integer> initList = new ArrayList<>();
                initList.add(tmp.val);
                res.add(layer - 1, initList);
            }
            if (null != tmp.left) {
                queue.offer(tmp.left);
                record.put(tmp.left, layer + 1);
            }
            if (null != tmp.right) {
                queue.offer(tmp.right);
                record.put(tmp.right, layer + 1);
            }
        }
        return res;
    }

    //2.看了参考答案,关键在于queue.size()就是当前层的个数
    public static List<List<Integer>> decorateRecord2(TreeNode root) {
        if (null == root) {
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<List<Integer>> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            for (int i = queue.size(); i > 0; i--) { //for倒着来可以预防 queue因为poll导致size变动的问题
                TreeNode curNode = queue.poll();
                tmp.add(curNode.val);
                if (null != curNode.left) {
                    queue.offer(curNode.left);
                }
                if (null != curNode.right) {
                    queue.offer(curNode.right);
                }
            }
            res.add(tmp);
        }
        return res;
    }
}
