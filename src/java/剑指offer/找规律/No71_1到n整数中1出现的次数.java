package 剑指offer.找规律;

public class No71_1到n整数中1出现的次数 {
    public static void main(String[] args) {
        System.out.println("args = " + digitOneInNumber3(101));
    }

    //1. 这个题的难点应该主要是在超时，因此暴力法是肯定超时的
    public static int digitOneInNumber(int num) {
        int sum = 0;
        for (int i = 0; i <= num; i++) {
            sum += digit(i);
        }
        return sum;
    }

    public static int digit(int num) {
        int sum = 0;
        while (num > 0) {
            if (num % 10 == 1) {
                sum++;
            }
            num /= 10;
        }
        return sum;
    }
    //2.尝试分析一下
    // 0-9 之间1出现的个数是 1 ，即1
    // 10-19 之间1出现的个数是 11 ，即10-19 10+1
    // 20-29 之间1出现的个数是 1 ，即21
    // 30-39 之间1出现的个数是 1 ，即31
    // 40-49 之间1出现的个数是 1 ，即41
    // 50-59 之间1出现的个数是 1 ，即51
    // 60-69 之间1出现的个数是 1 ，即61
    // 70-79 之间1出现的个数是 1 ，即71
    // 80-89 之间1出现的个数是 1 ，即81
    // 90-99 之间1出现的个数是 1 ，即91
    // 100-109 之间1出现的个数是 11 ，即100-109 10+1
    // 110-119 之间1出现的个数是 21 ，即110-119 10+10+1
    // 120-129 之间1出现的个数是 10 ，即120-129 10

    // 0-9  1
    // 0-99  20 10*1+1*10 = 20
    // 0-999 10*10+20*10 = 300
    // 0-9999 10*100+300*10 = 4000
    //还是没做出来
    public static int digitOneInNumber2(int num) {
        String strOfNum = String.valueOf(num);
        int len = strOfNum.length(); //获取num一共有多少位
        int[] dp = new int[9];
        dp[0] = 0;
        dp[1] = 1; //0-9
        dp[2] = 20; //0-99
        //dp[3] = 300 10*10+20*10
        for (int i = 3; i <= 8; i++) {
            dp[i] = (int) (Math.pow(10, i - 1) + dp[i - 1] * 10);
        }
        int sum = 0;
        for (int i = 0; i < len; i++) {
            int a = 0;
            int bit = strOfNum.charAt(i) - '0';
            if (i == len - 1) { //如果是最后一位
                sum += (bit >= 1) ? 1 : 0;
                break;
            }
            int base = dp[len - 1 - i];
            for (int j = 1; j <= bit; j++) {
                if (j == 1 && bit == 1) {
                    sum += (int) Math.pow(10, len - i - 2) * (Integer.parseInt(strOfNum.substring(i + 1, len)));
                    sum++;
                }
                if (j == 1 && bit != 1) {
                    sum += (int) Math.pow(10, len - i - 1);
                }
                sum += base;
            }
            int b = 0;
        }
        return sum;
    }

    //查看大佬递归题解https://leetcode.cn/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof/
    public static int digitOneInNumber3(int num) {
        if (num <= 0) {
            return 0;
        }
        String strOfNum = String.valueOf(num); //获取数字长度
        int len = strOfNum.length();
        int high = strOfNum.charAt(0) - '0'; //获取最高位
        int pow = (int) Math.pow(10, len - 1); //获取基底幂
        int last = num - high * pow; //除了最高位剩余部分
        if (high == 1) { //如果最高位是1
            return digitOneInNumber3(pow - 1) + last + 1 + digitOneInNumber3(last);
        } else { //如果最高位不是1
            return pow + high * digitOneInNumber(pow - 1) + digitOneInNumber(last);
        }
    }
}

//3499
// 1999
