package 剑指offer.字符串;

import java.util.*;

public class No24_字符串的排列_然后看看下一个排列 {
    public static void main(String[] args) {
        System.out.println("args = " + Arrays.toString(goodsOrder3("aab")));
    }

    //1. 暴力法 (248ms)
    public static String[] goodsOrder(String goods) {
        int len = goods.length();
        if (len == 1) {
            return new String[]{goods};
        }
        StringBuilder sb = new StringBuilder();
        Set<String> res = new HashSet<>();
        for (int i = 0; i < len; i++) {
            sb.append(goods, 0, i);
            sb.append(goods, i + 1, len);
            String[] tmp = goodsOrder(sb.toString());
            for (String s : tmp) {
                res.add(goods.charAt(i) + s);
                res.add(s + goods.charAt(i));
            }
            sb.setLength(0); //清空sb
        }
        return res.toArray(new String[0]);
    }

    //2. 使用回溯法进行优化
    public static String[] goodsOrder2(String goods) {
        int len = goods.length();
        if (len == 1) {
            return new String[]{goods};
        }
        Set<String> res = new HashSet<>();
        char[] charArray = goods.toCharArray();
        /*Arrays.sort(charArray);*/
        String newGoods = new String(charArray);
        backtrack2(newGoods, new ArrayList<>(), new StringBuilder(), res);
        return res.toArray(new String[0]);
    }

    public static void backtrack2(String goods, List<Integer> used, StringBuilder sb, Set<String> res) {
        int len = goods.length();
        //1.(回溯截止条件)
        if (len == sb.length()) {
            res.add(sb.toString());
            return;
        }
        for (int i = 0; i < len; i++) {
            //(！！！！为了减少不必要的运算，可以进行剪枝，如果是去重一般需要搭配排序来使用)
           /* if (i > 0 && goods.charAt(i) == goods.charAt(i - 1)) {
                continue;
            }*/
            if (used.contains(i)) { //如果被使用了(2.不满足的约束条件)
                continue;
            }
            used.add(i);
            sb.append(goods.charAt(i)); // （3.修改状态变量）
            backtrack2(goods, used, sb, res); // （4.递归回溯 新的状态变量）
            //(5,撤销选择)
            used.remove(used.size() - 1);
            sb.delete(sb.length() - 1, sb.length());
        }
    }


    //3. 对回溯法进行进一步优化，减去多余的枝干（通常由重复元素产生）
    /*
    *   关键区别
        错误的剪枝：只要 charAt(i) == charAt(i-1) 就跳过，导致某些合法排列被遗漏（如 "aba"）。
        正确的剪枝：只有当 charAt(i) == charAt(i-1) 且前一个字符未被使用时才跳过。这样可以确保：
        如果前一个相同的字符未被使用，说明它在当前递归层还未被选择，跳过当前字符避免重复。
        如果前一个相同的字符已被使用，说明它属于当前路径的一部分，不能跳过，否则会遗漏排列。

        结论
        你的初始思路（不加 !used.contains(i - 1)）是错误的，因为它会跳过所有重复字符，导致某些合法排列被遗漏。正确的剪枝条件是：
    * */
    public static String[] goodsOrder3(String goods) {
        int len = goods.length();
        if (len == 1) {
            return new String[]{goods};
        }
        List<String> res = new ArrayList<>();
        char[] goodsCharArray = goods.toCharArray();
        Arrays.sort(goodsCharArray);
        backtrack3(goodsCharArray, new boolean[len], new StringBuilder(), res);
        return res.toArray(new String[0]);
    }

    public static void backtrack3(char[] goods, boolean[] used, StringBuilder sb, List<String> res) {
        int len = goods.length;
        //1.(回溯截止条件)
        if (len == sb.length()) {
            res.add(sb.toString());
            return;
        }
        for (int i = 0; i < len; i++) {
            if (used[i]) { //如果被使用了(2.不满足的约束条件)
                continue;
            }
            //(！！！！为了减少不必要的运算，可以进行剪枝，如果是去重一般需要搭配排序来使用)
            if (i > 0 && goods[i] == goods[i - 1] && !used[i - 1]) {
                continue;
            }
            used[i] = true;
            sb.append(goods[i]); // （3.修改状态变量）
            backtrack3(goods, used, sb, res); // （4.递归回溯 新的状态变量）
            //(5,撤销选择)
            used[i] = false;
            sb.deleteCharAt(sb.length() - 1);
        }
    }

}
