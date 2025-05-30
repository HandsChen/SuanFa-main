package 剑指offer.数组;

import java.util.Arrays;

public class No7_训练计划1 {
    public static void main(String[] args) {
        int[] actions = {1, 2, 3, 4, 5};
        System.out.println("trainingPlan(actions) = " + Arrays.toString(trainingPlan1(actions)));
    }

    static public int[] trainingPlan(int[] actions) {
        int len = actions.length;
        if (len == 0) {
            return new int[0];
        }
        if (len == 1) {
            return actions;
        }
        //开始操作 （构建双指针）
        int firstPointer = 0;
        int endPointer = len - 1;
        do { //当两指针相遇时退出
            int a = actions[firstPointer];
            int b = actions[endPointer];
            if (!isEven(b)) { //如果b是奇数
                if (!isEven(a)) { //如果a也是奇数
                    firstPointer++;
                } else { //如果a是偶数,b是奇数，那么执行交换
                    swap(actions, firstPointer, endPointer);
                    //移动指针
                    firstPointer++;
                    endPointer--;
                }
            } else { //如果是偶数
                endPointer--;
            }
        } while (firstPointer < endPointer);
        return actions;
    }

    //进行交换
    static public void swap(int[] actions, int i, int j) {
        int tmp = actions[i];
        actions[i] = actions[j];
        actions[j] = tmp;
    }

    //判断是否为偶数
    static public boolean isEven(int i) {
        return i % 2 == 0;
    }


    static public int[] trainingPlan1(int[] actions) {
        int len = actions.length;
        if (len == 0) {
            return new int[0];
        }
        if (len == 1) {
            return actions;
        }
        //开始操作 （构建双指针）
        int firstPointer = 0;
        int endPointer = len - 1;
        while (true) {
            //从做向右找偶数
            while (firstPointer < len - 1 && actions[firstPointer] % 2 != 0) {
                firstPointer++;
            }
            //从右向左找奇数
            while (endPointer > 0 && actions[endPointer] % 2 == 0) {
                endPointer--;
            }
            if (firstPointer < endPointer) {
                swap(actions, firstPointer, endPointer);
            } else {
                break;
            }
        }
        return actions;
    }
}
