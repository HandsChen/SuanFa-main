package 剑指offer.数组;

import java.util.*;

public class No12_丑数 {

    public static void main(String[] args) {
        System.out.println("nthUglyNumber(10) = " + nthUglyNumber1(10));
    }

    static public int nthUglyNumber(int n) {
        if (n == 1) {
            return 1;
        }

        Set<Long> seen = new TreeSet<>();

        seen.add(1L);

        long currUglyNumber = 1;

        // 循环n-1次来生成第n个丑数
        for (int i = 1; i <= n; i++) {
            Iterator<Long> iterator = seen.iterator();
            if (iterator.hasNext()) {
                currUglyNumber = iterator.next();
                seen.remove(currUglyNumber);
            }
            // 生成3个可能的新的丑数
            seen.add(currUglyNumber * 2);
            seen.add(currUglyNumber * 3);
            seen.add(currUglyNumber * 5);
        }

        return (int) currUglyNumber;
    }

    static public int nthUglyNumber1(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1]; //进行dp算法构造
        dp[1] = 1; //第一个丑数字为1;
        //生成三个指针
        int p2 = 1, p3 = 1, p5 = 1;
        //生成第n个丑数
        for (int i = 2; i <= n; i++) {
            int num1 = dp[p2] * 2;
            int num2 = dp[p3] * 3;
            int num3 = dp[p5] * 5;
            //寻找出最小的
            int res = Math.min(num1, Math.min(num2, num3));
            dp[i] = res; //最小结果就作为该指针处生成的丑数
            //寻找是否到底生成的丑数是由哪个指针生成的
            if (num1 == res) {
                p2++;
            }if (num2 == res) {
                p3++;
            }if (num3 == res) {
                p5++;
            }
        }
        return dp[n];
    }
}
