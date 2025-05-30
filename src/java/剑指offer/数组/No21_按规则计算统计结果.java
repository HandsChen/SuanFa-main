package 剑指offer.数组;

import java.util.*;

public class No21_按规则计算统计结果 {
    public static void main(String[] args) {
        System.out.println("args = " + Arrays.toString(statisticalResult2(new int[]{2, 4, 6, 8, 10})));
    }

    //暴力法 时间复杂度O(n^2)
    public static int[] statisticalResult1(int[] arrayA) {
        int length = arrayA.length;
        if (length == 1) {
            return new int[]{0};
        }
        //构建缓存
        Map<Integer, Integer> cache = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            //首先判断缓存中有没有，如果没有才计算
            int tmp = 1;
            if (cache.containsKey(arrayA[i])) {
                tmp = cache.get(arrayA[i]);
            } else {
                for (int j = 0; j < length; j++) {
                    if (i != j) {
                        tmp = tmp * arrayA[j];
                    }
                }
            }
            res.add(tmp);
            cache.put(arrayA[i], tmp);
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    //本质上就是两个dp ,整体思路，结果集中任何一个元素 = 其左边所有元素的乘积 * 其右边所有元素的乘积。一轮循环构建左边的乘积并保存在结果集
    // 中，二轮循环 构建右边乘积的过程，乘以左边的乘积，并将最终结果保存
    public static int[] statisticalResult2(int[] arrayA) {
        int len = arrayA.length;
        int[] dp1 = new int[len];
        int[] dp2 = new int[len];
        //i左边数组的乘积
        for (int i = 0; i < arrayA.length; i++) {
            if (i == 0) {
                dp1[i] = 1;
            } else {
                dp1[i] = dp1[i - 1] * arrayA[i - 1];
            }
        }
        //i右边数组的乘积
        for (int i = len - 1; i >= 0; i--) {
            if (i == len - 1) {
                dp2[i] = 1;
            } else {
                dp2[i] = dp2[i + 1] * arrayA[i + 1];
            }
        }
        // i左边数组的乘积与右边的相乘
        for (int i = 0; i < arrayA.length; i++) {
            dp1[i] = dp1[i] * dp2[i];
        }
        return dp1;
    }
}
