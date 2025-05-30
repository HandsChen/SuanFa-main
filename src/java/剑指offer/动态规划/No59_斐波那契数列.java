package 剑指offer.动态规划;

public class No59_斐波那契数列 {
    public static void main(String[] args) {
        System.out.println("fib(45) = " + fib(48));

    }

    public static int fib(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 1] % 1000000007 + dp[i - 2] % 1000000007) % 1000000007;
        }
        return dp[n];
    }
}
