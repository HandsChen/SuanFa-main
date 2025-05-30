package 剑指offer.栈与队列;

import java.util.*;

public class No40_栈的压入与弹出序列 {
    public static void main(String[] args) {
        System.out.println("validateStackSequences(new[]{1,0,2},new int[]{2,1,0}) = " + validateStackSequences(new int[]{1, 0, 2}, new int[]{2, 1, 0}));
    }

    /* //1.暴力法 (109/152 没有都通过 [1,0,3,2] [0,1,2,3] expect : true ，太蠢了，没继续下去)
     public static boolean validateStackSequences(int[] pushed, int[] popped) {
         int len = pushed.length;
         if (len == 1) return true;
         List<int[]> bucket = new ArrayList<>();

         for (int i = 0; i < pushed.length; i++) {
             for (int j = i + 1; j < pushed.length; j++) {
                 bucket.add(new int[]{pushed[i], pushed[j]});
             }
         }
         for (int i = 0; i < popped.length; i++) {
             for (int j = i + 1; j < popped.length; j++) {
                 bucket.add(new int[]{popped[i], popped[j]});
             }
         }
         bucket.sort(((a, b) -> {
             // 首先比较第一个元素
             if (a[0] != b[0]) {
                 return Integer.compare(a[0], b[0]);
             }
             // 如果第一个元素相同，则比较第二个元素
             return Integer.compare(a[1], b[1]);
         }));
         boolean res = true;
         for (int i = 0; i + 1 < bucket.size(); i++) {
             int[] first = bucket.get(i);
             int[] second = bucket.get(i + 1);
             if (first[0] == second[0] && first[1] == second[1] && first[1] != pushed[len - 1] && first[0] != popped[0]) {

                 return false;
             }
         }
         return res;
     }*/

    //2.建立一个栈去模拟要求的push和pop动作
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stack = new ArrayDeque<>(); //传统的stack因为使用vector ,其中大量使用了synchronized关键字，因此速度不快，java官方文档中更加推荐使用ArrayDeque来替代stack
        for (int i = 0, j = 0; i < pushed.length; i++) {
            stack.push(pushed[i]); //先尝试压栈
            while (!stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }
}
