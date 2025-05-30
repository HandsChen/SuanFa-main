package 剑指offer.数组;

public class No20_设计机械累加器 {
    public static void main(String[] args) {
        System.out.println("args = " + mechanicalAccumulator1(5));
    }

    // 不具备乘除、if-else、switch-case、for 循环、while 循环 ，那就考虑使用递归进行计算
    public static int mechanicalAccumulator(int target) {
        if (target == 1) {
            return target;
        }
        return target + mechanicalAccumulator(target - 1);
    }

    public static int mechanicalAccumulator1(int target) {
        boolean flag = (target == 1) || (target += mechanicalAccumulator(target - 1)) > 0;
        return target;
    }

}
