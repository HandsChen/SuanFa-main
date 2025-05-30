package 剑指offer.数组;

import java.math.BigDecimal;

//主要难点在于大数求模，然后分解时候需要记得以3为基底
public class No5_砍竹子2 {

    public static void main(String[] args) {
//        System.out.println("cuttingBamboo(1000) = " + cuttingBamboo(120));
        System.out.println("cuttingBamboo(12) = " + cuttingBamboo2(12));
    }

    //这种方式还是偏暴力，编译器可能无法通过,因为BigDecimal不存在
    static public int cuttingBamboo(int n) {
        if (n == 0 || n == 1) { //当n为0或者1时无法分解
            return 0;
        }
        //构建DP
        BigDecimal[] dp = new BigDecimal[n + 1];
        //初始化
        dp[0] = BigDecimal.valueOf(0);
        dp[1] = BigDecimal.valueOf(0);
        //当n>=2时代表可以被分解
        for (int i = 2; i <= n; i++) {
            BigDecimal curMax = BigDecimal.valueOf(0); //当前的最大结果
            //将n拆分为j和i-j
            //此时i-j有两个选项，1是继续分解，2是直接乘
            for (int j = 1; j < i; j++) {
                BigDecimal a = BigDecimal.valueOf((long) j * (i - j));
                BigDecimal b = BigDecimal.valueOf(j).multiply(dp[i - j]);
                BigDecimal tmp = a.compareTo(b) > 0 ? a : b;
                curMax = tmp.compareTo(curMax) > 0 ? tmp : curMax;
            }
            dp[i] = curMax;
        }
        BigDecimal modeTarget = new BigDecimal("1000000007");
        BigDecimal remainder = dp[n].remainder(modeTarget);
        return remainder.intValue();
    }

    static public int cuttingBamboo2(int n) {
        if (n == 0 || n == 1) {
            return 0;
        }

        int mod = 1000000007;

        // 特殊处理：如果竹子长度是 2 或 3，直接返回
        if (n == 2) return 1;
        if (n == 3) return 2;
        //如果能够整除3
        if (n % 3 == 0) {
            long result = 1; //3^1-1
            for (int a = 1; a <= (n / 3); a++) {
                result = (result * 3) % mod;
            }
            return (int) result;
        }
        //如果余2
        if (n % 3 == 2) {
            long result = 1; //3^1-1
            for (int a = 1; a <= (n / 3); a++) {
                result = (result * 3) % mod;
            }
            return (int) ((result * 2) % mod);
        }
        //如果余1,需要把3-1组成为2-2
        if (n % 3 == 1) {
            long result = 1; //3^1-1
            for (int a = 1; a <= (n / 3 - 1); a++) {
                result = (result * 3) % mod;
            }
            return (int) ((result * 4) % mod);
        }
        return 0;
    }
}
