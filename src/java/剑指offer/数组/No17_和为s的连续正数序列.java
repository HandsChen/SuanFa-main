package 剑指offer.数组;

import java.util.*;
import java.util.stream.IntStream;

public class No17_和为s的连续正数序列 {
    public static void main(String[] args) {
        System.out.println("args = " + fileCombination2(18));
    }

    //暴力法
//    public static int[][] fileCombination(int target) {
//        List<int[]> res = new ArrayList<>();
//        for (int i = 1; i <= target / 2; i++) {  //起始值最多走到一半
//            int sum = 0;
//            List<Integer> tmp = new ArrayList<>();
//            boolean isHit = false; //判断是否命中
//            for (int j = i; j < target; j++) {
//                tmp.add(j);
//                sum += j;
//                if (sum == target) {
//                    isHit = true;
//                    break;
//                } else if (sum > target) {
//                    break;
//                }
//            }
//            //结果命中
//            if (isHit) {
//                res.add(tmp.stream().mapToInt(Integer::intValue).toArray());
//            }
//        }
//        return res.toArray(new int[res.size()][]);
//    }

    //双指针法
    public static int[][] fileCombination2(int target) {
        int left = 1;
        int right = 2;

        List<int[]> res = new ArrayList<>();
        while (left < right && left < target - 1 && right < target) {
            //计算区间的和
            int sum = (right + left) * (right - left + 1) / 2;
            if (sum < target) {
                right++; //向右增加一位
            } else if (sum > target) {
                left++; //向右减小一位
            } else { //如果找到了对应数列
                // 使用流快速生成连续数组
                int[] tmp = IntStream.rangeClosed(left, right).toArray();
                res.add(tmp); //存储结果

                //移动指针
                left++;
            }
        }
        return res.toArray(new int[res.size()][]);
    }

    // 1  2 3 4 5 6 7 8

    // target = 9

    // 2  3  4

}
