package 剑指offer.动态规划;

public class No60_青蛙跳台阶问题 {
    public static void main(String[] args) {
        System.out.println("args = " + climbStairs(2));
    }

    public static int climbStairs(int n) {
        if (n == 0 || n == 1 || n == 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }
        return dp[n];
    }
}
