package 剑指offer.位运算;

public class No55_数值的整数次方 {
    public static void main(String[] args) {
        System.out.println("myPow() = " + myPow(2.00000, -2147483648));

    }

    //1.暴力法
    public static double myPow(double x, int n) {
        double res = 1;
        if (x == 1) {
            return res;
        }
        if (x == -1) {
            if (n == Integer.MIN_VALUE) {
                return 1;
            }
            if (n < 0) {
                n = -n;
            }
            if (n % 2 == 0) {
                return 1;
            } else {
                return -1;
            }
        }
        if (n > 0) {
            while (n > 0) {
                res = res * x;
                n--;
            }
        } else if (n < 0) {
            double tmp = 1.0 / x;
            if (n == Integer.MIN_VALUE) {
                return 0;
            } else {
                n = -n;
            }
            while (n > 0) {
                res = res * tmp;
                n--;
            }
        } else { //n==0
            return 1;
        }
        return res;
    }

    //快速幂（本质上就是分治）递归方式实现·
    public static double myPow2(double x, int n) {
        //这里n有正数和负数两种形态
        if (n >= 0) {
            return quickMul2(x, n);
        } else { //负数
            return 1.0 / quickMul2(x, -n);
        }
    }

    public static double quickMul2(double x, long N) {
        if (N == 0) { //截止条件，最终N会递归到0,0作为指数其结果为1
            return 1.0;
        }
        double v = quickMul2(x, N / 2); //n/2之前的乘积
        return N % 2 == 0 ? v * v : v * v * x;
    }
}
