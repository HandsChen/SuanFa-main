package 剑指offer.位运算;

import java.util.Arrays;

public class No56_数组中数字出现的次数 {
    public static void main(String[] args) {
        System.out.println("sockCollocation() = " + Arrays.toString(sockCollocation2(new int[]{1, 2, 4, 1, 4, 3, 12, 3})));

    }

    // 自己尝试双指针法 （实际上不满足要求，即时间复杂度 O(n)，空间复杂度O(1)）双指针法的时间复杂度为O(nlog^n) ,空间复杂度为O(log^n)
    public static int[] sockCollocation(int[] sockets) {
        //首先进行排序
        Arrays.sort(sockets);
        //定义前后指针
        int lPtr = 0;
        int rPtr = sockets.length - 1;

        //找到左边第一个单值
        while (lPtr < sockets.length && lPtr + 1 < sockets.length && sockets[lPtr] == sockets[lPtr + 1]) {
            lPtr = lPtr + 2;
        }
        //找到右边的单值
        while (rPtr >= 0 && rPtr - 1 >= 0 && sockets[rPtr] == sockets[rPtr - 1]) {
            rPtr = rPtr - 2;
        }

        return new int[]{sockets[lPtr], sockets[rPtr]};
    }

    //2.还可以基于hashSet 时间复杂度为O(n) ,空间复杂度为O(n)，也不满足需求

    //3. 使用位运算
    public static int[] sockCollocation2(int[] sockets) {
        //首先遍历socket对所有数据进行异或
        int res = 0;
        for (int socket : sockets) {
            res = res ^ socket;
        }
        //寻找res中的不同位，以做区分
        int factor = 1;
        while ((res & factor) == 0) { // ==0 而不是!=1 很关键
            factor = factor << 1;
        }
        //根据区分位将sockets分为两组，分别进行组内异或
        int first = 0;
        int second = 0;
        for (int socket : sockets) {
            if ((socket & factor) == 0) {  // ==0 而不是!=1 很关键
                first = first ^ socket;
            } else {
                second = second ^ socket;
            }
        }
        return new int[]{first, second};
    }

}
