package 剑指offer.动态规划;

public class No62_连续子数组的最大和 {
    public static void main(String[] args) {
        System.out.println("maxSubArray() = " + maxSubArray2(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    // 自己想的，大概只过了一半
    public static int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            //每次新加进来一个数，如果这个数是正值，那么是对最大和连续子数组是有正增益的
            if (nums[i] > 0) {
                if (dp[i - 1] >= nums[i]) {
                    dp[i] = dp[i - 1] + nums[i];
                    int cursor = i;
                    while (cursor >= 2 && dp[cursor - 1] == dp[cursor - 2] && nums[cursor - 1] < 0) {
                        dp[i] = dp[i] + nums[cursor - 1];
                        cursor--;
                    }
                    dp[i] = Math.max(dp[i], dp[i - 1]);
                } else {
                    dp[i] = nums[i];
                }
            } else { //如果小等于0，其实有负增益
                dp[i] = Math.max(dp[i - 1], nums[i]);
            }
        }
        return dp[nums.length - 1];
    }

    //查看题解后，发现自己对动态规划的状态转移的定义有问题
    public static int maxSubArray2(int[] nums) {
        int[] dp = new int[nums.length]; //以num[i]结尾的最大连续序列和
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else { //负增益
                dp[i] = nums[i];
            }
        }
        //寻找最大的连续训练和
        int res = Integer.MIN_VALUE;
        for (int item : dp) {
            res = Math.max(res, item);
        }
        return res;
    }
    //除上述方法以外，还可以使用分治的思路求解
}
