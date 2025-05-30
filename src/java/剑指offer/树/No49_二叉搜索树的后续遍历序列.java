package 剑指offer.树;

import java.util.LinkedList;
import java.util.List;

public class No49_二叉搜索树的后续遍历序列 {
    public static void main(String[] args) {

    }

    //1.看答案，递归分治
    public static boolean verifyTreeOrder(int[] postorder) {
        return recur(postorder, 0, postorder.length - 1);
    }

    public static boolean recur(int[] postorder, int i, int j) {
        if (i >= j) {
            return true;
        }
        //寻找第一个大于根节点的数
        int p = i;
        //寻找左子树边界
        while (postorder[p] < postorder[j]) {
            p++;
        }
        int m = p; //左子树右边界
        //寻找右子树边界
        while (postorder[p] > postorder[j]) {
            p++;
        }
        //递归判断左子树和右子树
        return p == j && recur(postorder, i, m - 1) && recur(postorder, m, j - 1);
    }

    //2.看答案，尝试构造一个二叉树，因为是手续遍历，所以构建的顺序应该为： 根节点->右树 ->左树
    public static boolean verifyTreeOrder2(int[] postorder) {
        if (null == postorder || postorder.length == 0) {
            return false;
        }
        List<Integer> bucket = new LinkedList<>();
        for (int item : postorder) {
            bucket.add(item);
        }
        build(bucket, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return bucket.isEmpty();
    }

    public static void build(List<Integer> bucket, int min, int max) {
        //如果链表为空则直接返回
        if (bucket.isEmpty()) {
            return;
        }
        //1.获得根节点
        int root = bucket.get(bucket.size() - 1);
        //如果根节点越界，那么返回
        if (root > max || root < min) {
            return;
        }
        //弹出根节点
        bucket.remove(bucket.size() - 1);
        //2.构建右树
        build(bucket, root, max);
        //3.构建左树
        build(bucket, min, root);
    }
}
