package 剑指offer.动态规划;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class No66_圆圈中最后剩下的数字 {
    public static void main(String[] args) {

        System.out.println("iceBreakingGame() = " + iceBreakingGame3(7, 4));

    }

    //1.暴力法 (容易超时)
    public static int iceBreakingGame(int num, int target) {
        LinkedList<Integer> list = new LinkedList<>();
        //所有数据进入数组
        for (int i = 0; i < num; i++) {
            list.offer(i);
        }
        int cursor = 0;
        int res = -1;
        while (!list.isEmpty()) {
            int delIndex = (cursor + target - 1) % list.size();
            if (delIndex == list.size() - 1) {
                cursor = 0;
            } else {
                cursor = delIndex;
            }
            res = list.get(delIndex);
            list.remove(delIndex); //删除元素
            //删除完以后cursor自动移位到下一位，如果cursor不在边界，那么删除后后面的元素会自动补位，如果cursor在右边界，那么需要删除后cursor需要回到首位
        }
        return res;
    }

    //动态规划法 f(num,target)表示序列为num长度时，删除后剩余的数。x= f(num-1,target)表示序列为num-1长度时，删除后剩余的数。 f(num,target) = (x+target)%num;
    public static int iceBreakingGame2(int num, int target) {
        if (num == 1) {
            return 0;
        }
        int x = iceBreakingGame2(num - 1, target);
        return (x + target) % num;
    }

    public static int iceBreakingGame3(int num, int target) {
        if (num == 1) {
            return 0;
        }
        int res = 0;
        for (int i = 2; i <= num; i++) {
            res = (res + target) % i;
        }
        return res;
    }
}


//0 1 2 3 4  3
// 2 (0+3-1)%5
// 0  (3+3-1)%5
// 4  (1+3-1+1)%5
//1  (4+3-1)%5
//3 ()%5