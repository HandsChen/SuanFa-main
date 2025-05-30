package 剑指offer.动态规划;

import java.util.Arrays;

public class No61_正则表达式匹配 {
    public static void main(String[] args) {
        System.out.println("args = " + isMatch("aab", "c*a*b"));
    }
    //1.自己想的，没有完全通过，大概通过2/3
    public static boolean isMatch(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        int cursor = 0;
        boolean[] dp = new boolean[sLen];
        Arrays.fill(dp, false);
        char[] sCharArray = s.toCharArray();
        char[] pCharArray = p.toCharArray();
        for (int i = 0; i < sLen && cursor < pLen; i++) {
            char sCh = sCharArray[i];
            char pCh = pCharArray[cursor];
            if (pCh == '.') {
                dp[i] = true;
                cursor++; //只能匹配一次
            } else if (sCh == pCh) {
                dp[i] = true;
                cursor++; //只能匹配一次
            } else if (pCh == '*' && cursor - 1 >= 0) {
                if (pCharArray[cursor - 1] == '.') {
                    dp[i] = true;
                } else {
                    if (sCh == pCharArray[cursor - 1]) {
                        dp[i] = true;
                    } else {
                        cursor++;
                        i = i - 1;
                    }
                }
                //可以匹配多次
            } else { //如果第一个字母就不想等
                while (cursor < pLen && pCharArray[cursor] != '*') {
                    cursor++;
                }
                while (cursor < pLen && pCharArray[cursor] == '*') {
                    cursor++;
                }
                i = i - 1;
            }
        }
        return dp[sLen - 1];
    }
}
