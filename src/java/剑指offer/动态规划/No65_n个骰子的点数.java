package 剑指offer.动态规划;
//1+1 【1+2】 【1+3】 【1+4】 【1+5】 【1+6】
//【2+1】 2+2 【2+3】 【2+4】 【2+5】 【2+6】
// 【3+1】 【3+2】 3+3 【3+4】 【3+5】 【3+6】
//【4+1】 【4+2】 【4+3】 4+4 【4+5】 【4+6】
//【5+1】 【5+2】 【5+3】 【5+4】 5+5 5+6
//【6+1】 【6+2】 【6+3】 【6+4】 【6+5】 6+6


// 1. 1
// 2. num
// 3. num+1

// 1+1+1 1+1+2 1+1+3

//2 3 4.5 7. 12
//1 2 3 4 .6. 1

//3 4 5... 18
//1 3 3+3

//1.明显无法使用暴力法
public class No65_n个骰子的点数 {
    public static void main(String[] args) {

    }

    //2.尝试使用动态规划法
    public static double[] statisticsProbability(int num) {
        int[][] dp = new int[num + 1][num * 6 + 1]; //动态规划表 dp[i][j]，表示使用 i 个骰子得到总和 j 的方式数目
        //初始化
        for (int i = 1; i <= 6; i++) {
            dp[1][i] = 1;
        }
        for (int i = 2; i <= num; i++) { //投掷第i枚色子的个数
            for (int j = i; j <= i * 6; j++) { //投掷第i枚时累积的色子组合总方式
                for (int k = 1; k <= 6 && j - k >= i - 1; k++) { //第i枚色子的号码  j - k >= i - 1 表示方式数据至少要为i-1个色子，因为其每个最小为1
                    dp[i][j] += dp[i - 1][j - k];
                }
            }
        }
        double[] res = new double[num * 6 - num + 1];
        int cursor = 0;
        for (int j = num; j <= num * 6; j++) {
            res[cursor] = dp[num][j] / Math.pow(6, num); //   每种情况的次数/总次数
            cursor++;
        }
        return res;
    }
}
