package 剑指offer.数组;


public class No4_整数拆分乘积最大化 {
    public static void main(String[] args) {
        System.out.println("args = " + integerBreak(10));
    }

    /**
     * 暴力法
     */
    /*static public int integerBreak(int n) {
        if (n <= 1) {
            return 0;
        }
        int startIndex = 2;  //首先尝试从2开始分解n
        int res = 0;
        while ((n / startIndex) > 0) { //代表可以被分解为startIndex份
            int factor = n / startIndex;
            int leave = n % startIndex; //分解完毕后还剩下的数
            res = Math.max((int) ((Math.pow(factor, startIndex - leave)) * Math.pow((factor + 1), leave)), res);
            startIndex++; //尝试分解更多份
        }
        // 获取 List 中的最大值
        return res;
    }*/

    /*动态规划*/
    static public int integerBreak(int n) {
        if (n == 0 || n == 1) { //当n为0或者1时无法分解
            return 0;
        }
        //构建DP
        int[] dp = new int[n + 1];
        //初始化
        dp[0] = 0;
        dp[1] = 0;
        //当n>=2时代表可以被分解
        for (int i = 2; i <= n; i++) {
            int curMax = 0; //当前的最大结果
            //将n拆分为j和i-j
            //此时i-j有两个选项，1是继续分解，2是直接乘
            for (int j = 1; j < i; j++) {
                curMax = Math.max(curMax, Math.max(j * (i - j), j * dp[i - j]));
            }
            dp[i] = curMax;
        }
        return dp[n];
    }
}
