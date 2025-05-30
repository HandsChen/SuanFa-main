package 剑指offer.字符串;

import java.util.LinkedHashMap;
import java.util.Map;

public class No26_第一个只出现一次的字符 {
    public static void main(String[] args) {
        System.out.println("dismantlingAction() = " + dismantlingAction2("ccdd"));
    }

    // 1.整体速度还是不快
    public static char dismantlingAction(String arr) {
        char[] charArray = arr.toCharArray();
        LinkedHashMap<Character, Integer> count = new LinkedHashMap<>();
        for (char c : charArray) {
            if (!count.containsKey(c)) {
                count.put(c, 1);
            } else {
                Integer value = count.get(c);
                count.put(c, ++value);
            }
        }
        for (Map.Entry<Character, Integer> item : count.entrySet()) {
            if (item.getValue() == 1) {
                return item.getKey();
            }
        }
        return ' ';
    }

    //2.进一步优化,从30ms优化到了3ms,速度提高了10倍
    public static char dismantlingAction2(String arr) {
        char[] charArray = arr.toCharArray();
        int[] count = new int[26];
        for (char c : charArray) {
            count[c - 'a']++;
        }
        for (char c : charArray) {
            if (count[c - 'a'] == 1) {
                return c;
            }
        }
        return ' ';
    }
}
