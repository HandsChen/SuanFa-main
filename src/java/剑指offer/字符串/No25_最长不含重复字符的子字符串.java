package 剑指offer.字符串;

import java.util.HashSet;
import java.util.Set;

public class No25_最长不含重复字符的子字符串 {
    public static void main(String[] args) {
        System.out.println("lengthOfLongestSubstring() = " + lengthOfLongestSubstring2("abcabcbb"));
    }

    // 1.暴力法，当所有数均一样是最暴力
    public static int lengthOfLongestSubstring(String s) {
        if (null == s || s.isEmpty()) {
            return 0;
        }
        char[] charArray = s.toCharArray();
        int len = charArray.length;
        int finalMaxLen = len;
        int index = 0;
        while (finalMaxLen > 0) {
            while (index < len - finalMaxLen + 1) {
                if (!isMulti(charArray, index, index + finalMaxLen - 1)) {
                    return finalMaxLen;
                } else {
                    index++;
                }
            }
            finalMaxLen--;
            index = 0;
        }
        return finalMaxLen;
    }

    //判断数组中，是否有字符重复，哈希法
    public static boolean isMulti(char[] chars, int start, int end) {
        Set<Character> bucket = new HashSet<>();
        for (int i = start; i <= end; i++) {
            if (bucket.contains(chars[i])) {
                return true;
            } else {
                bucket.add(chars[i]);
            }
        }
        return false;
    }

    //滑动窗口和hash法（去重）
    public static int lengthOfLongestSubstring2(String s) {
        if (null == s || s.isEmpty()) {
            return 0;
        }
        int len = s.length();
        char[] charArray = s.toCharArray();
        Set<Character> bucket = new HashSet<>();
        int rPtr = -1;
        int maxWindowLen = 0;
        for (int i = 0; i < charArray.length; i++) {
            //窗口左指针向右移动
            if (0 != i) {
                bucket.remove(charArray[i - 1]); //因为不重复，所以可以这样删除
            }
            //移动窗口右指针，将所有数包含进来
            while (rPtr + 1 < len && !bucket.contains(charArray[rPtr + 1])) {
                bucket.add(charArray[rPtr + 1]);
                rPtr++;
            }
            //记录当前窗口的长度
            maxWindowLen = Math.max(maxWindowLen, rPtr - i + 1);
        }
        return maxWindowLen;
    }
}
