package 剑指offer.字符串;

import java.util.ArrayDeque;
import java.util.Deque;

public class No27_反转字符串中的单词 {
    public static void main(String[] args) {
        System.out.println("reverseWords() = " + reverseWords2("a good   example"));

    }

    //1.常规解法，使用栈
    public static String reverseWords(String s) {
        s = s.trim(); //去除两边的空格
        String[] stringArray = s.split(" ");
        Deque<String> stack = new ArrayDeque<>();
        for (String string : stringArray) {
            String trim = string.trim();
            if (!trim.isEmpty()) {
                stack.push(trim);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.poll());
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    //2.试试看看有没有O(1)空间复杂度的解法
    public static String reverseWords2(String s) {
        //去除首尾和单词中间的空格
        s = s.trim().replaceAll(" +", " ");
        char[] charArray = s.toCharArray();
        int lPtr = 0;
        int rPtr = charArray.length - 1;
        //逆转整体的序列
        while (lPtr < rPtr) {
            swap(charArray, lPtr, rPtr);
            lPtr++;
            rPtr--;
        }
        //逆转单词内部的序列
        for (int i = 0; i < charArray.length; ) {
            if (charArray[i] == ' ') {
                i++;
                continue;
            }
            rPtr = i;
            lPtr = i;
            while (rPtr + 1 < charArray.length && charArray[rPtr + 1] != ' ') {
                rPtr++;
            }
            i = rPtr + 1;
            while (lPtr < rPtr) {
                swap(charArray, lPtr, rPtr);
                lPtr++;
                rPtr--;
            }
        }
        return new String(charArray);
    }

    public static void swap(char[] charArray, int left, int right) {
        char tmp = charArray[left];
        charArray[left] = charArray[right];
        charArray[right] = tmp;
    }
}
