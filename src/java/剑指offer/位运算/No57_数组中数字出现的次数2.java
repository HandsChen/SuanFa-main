package 剑指offer.位运算;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class No57_数组中数字出现的次数2 {
    public static void main(String[] args) {
        System.out.println("trainingPlan3() = " + trainingPlan3(new int[]{5, 7, 5, 5}));

    }

    //1. 暴力法
    public static int trainingPlan(int[] actions) {
        Arrays.sort(actions);
        int len = actions.length;
        int index = 0;
        while (index < len && index + 1 < len && actions[index] == actions[index + 1]) {
            index = index + 3;
        }
        return actions[index];
    }

    //2. hash法 (性能不如暴力法)
    public static int trainingPlan2(int[] actions) {
        Map<Integer, Integer> cache = new HashMap<>();
        for (int action : actions) {
            cache.putIfAbsent(action, 0);
            cache.put(action, cache.get(action) + 1);
        }
        int res = actions[0];
        for (Map.Entry<Integer, Integer> item : cache.entrySet()) {
            if (item.getValue() == 1) {
                res = item.getKey();
            }
        }
        return res;
    }

    //去猜只出现一次的数字的二进制中1的出现位置
    public static int trainingPlan3(int[] actions) {
        //一个int 一共32位，从最低位开始检查
        int targetValue = 0;
        for (int i = 0; i < 32; i++) {
            int countOneOfTarget = 0;
            for (int action : actions) {
                if (((action >> i) & 1) == 1) {
                    countOneOfTarget++;
                }
            }
            //计算出目标数据在该位是否存在1,如果是学生，那么1应该是3的倍数
            if (countOneOfTarget % 3 != 0) { //
                targetValue = targetValue | (1 << i);
            }
        }
        return targetValue;
    }

    //有限状态自动机（代码及其简单）
    /*public static  int trainingPlan4(int[] actions) {

    }*/
}
