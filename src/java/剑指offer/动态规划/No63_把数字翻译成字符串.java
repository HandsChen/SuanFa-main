package 剑指offer.动态规划;

public class No63_把数字翻译成字符串 {
    public static void main(String[] args) {
        System.out.println("crackNumber2() = " + crackNumber2(216612));
    }

    //动态规划 （自己完成）
    public static int crackNumber(int ciphertext) {
        String input = String.valueOf(ciphertext);
        int len = input.length();
        if (len == 1) {
            return 1;
        }
        int[] dp = new int[len];
        dp[0] = 1;
        int a = (input.charAt(0) - '0') * 10 + (input.charAt(1) - '0');
        dp[1] = a <= 25 && a >= 0 ? 2 : 1;
        if (len == 2) {
            return dp[len - 1];
        }
        for (int i = 2; i < input.length(); i++) {
            int tmp = (input.charAt(i - 1) - '0') * 10 + (input.charAt(i) - '0');
            if (tmp <= 25 && tmp >= 10) {
                dp[i] = dp[i - 1] + dp[i - 2];
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[input.length() - 1];
    }

    //将自己写的进一步优化 （使用滚动数组）
    public static int crackNumber2(int ciphertext) {
        String input = String.valueOf(ciphertext);
        int pre = 0, cur = 1;
        for (int i = 0; i < input.length(); i++) {
            if (isValidNumber(input, i)) {
                int next = cur + pre;
                pre = cur;
                cur = next;
            } else {
                pre = cur;
            }
        }
        return cur;
    }

    public static boolean isValidNumber(String input, int i) {
        if (i - 1 < 0) {
            return false;
        }
        int tmp = (input.charAt(i - 1) - '0') * 10 + (input.charAt(i) - '0');
        return tmp <= 25 && tmp >= 10;
    }

}
